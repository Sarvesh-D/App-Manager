package com.ds.appmanager.services.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.Atomic;
import org.javers.core.diff.changetype.ReferenceChange;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ds.appmanager.services.domain.Audit;
import com.ds.appmanager.services.util.UserContextHolder;

public class AuditInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	private static final Javers javers = JaversBuilder.javers().build();
	
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		try {
			// prepare a new blank instance to compare
			Object old = entity.getClass().newInstance();
			Diff diff = javers.compare(old, entity);
			
			List<ValueChange> changes = diff.getChangesByType(ValueChange.class);
			
			auditValueChanges(changes);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		// fetch the old object from db
		Object old = hibernateTemplate.getSessionFactory().openSession().get(entity.getClass(), id);

		try{
			// compare old and new object
			Diff diff = javers.compare(old, entity);
			
			List<ValueChange> changes = diff.getChangesByType(ValueChange.class);
			
			List<ReferenceChange> referenceChanges = diff.getChangesByType(ReferenceChange.class);
			
			// Audit Value Changes for simple types
			auditValueChanges(changes);
			
			// Audit Value Changes for embedded Objects
			for (ReferenceChange referenceChange : referenceChanges) {
				Object oldRef = (Object)referenceChange.getLeftObject();
				Object newRef = (Object)referenceChange.getRightObject();
				
				Diff refDiff = javers.compare(oldRef,newRef);
				List<ValueChange> refChanges = refDiff.getChangesByType(ValueChange.class);
				auditValueChanges(refChanges);
				
			}
		} catch (Exception e) {
			System.out.println("Exeption occured while auditing :"+e);
		}

		return false;
	}
	
	private void auditValueChanges(List<ValueChange> changes) {
		for (ValueChange change : changes) {
			doAudit(change);
		}
	}
	
	private void doAudit(ValueChange change) {
		Audit audit = new Audit();
		audit.setFieldName(change.getPropertyName());
		audit.setOldValue(change.getLeft().toString());
		audit.setNewValue(change.getRight().toString());
		audit.setUpdatedBy(UserContextHolder.getUser().getUserName());
		audit.setUpdateTs(new Date());
		hibernateTemplate.save(audit);
	}

}
