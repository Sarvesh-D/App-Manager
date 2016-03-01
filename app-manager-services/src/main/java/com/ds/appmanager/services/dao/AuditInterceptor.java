package com.ds.appmanager.services.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ds.appmanager.services.domain.Audit;

public class AuditInterceptor extends EmptyInterceptor {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		Javers javers = JaversBuilder.javers().build();
		Object old = hibernateTemplate.get(entity.getClass(), id);
		
		Diff diff = javers.compare(old, entity);
		
		List<ValueChange> changes = diff.getChangesByType(ValueChange.class);
		
		for (ValueChange change : changes) {
			Audit audit = new Audit();
			audit.setFieldName(change.getPropertyName().toUpperCase());
			audit.setOldValue(change.getLeft().toString());
			audit.setNewValue(change.getRight().toString());
		}
		
		/*for (int i = 0; i < propertyNames.length; i++) {
			Audit audit = new Audit();
			audit.setFieldName(propertyNames[i]);
			audit.setOldValue(previousState[i].toString());
			audit.setNewValue(currentState[i].toString());
			hibernateTemplate.save(audit);
		}*/
		
		return false;
	}

}
