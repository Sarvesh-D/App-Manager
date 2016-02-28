package com.ds.appmanager.services.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ds.appmanager.services.domain.Application;

@Repository
public class AuditDaoImpl implements AuditDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Application> getApplicationAudit(int applicationId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		AuditQuery query = AuditReaderFactory.get(session).createQuery().forRevisionsOfEntity(Application.class, true, true)
							.add(AuditEntity.id().eq(applicationId));
		
		System.out.println("QUERY RESULTS : "+query.getResultList());
		return query.getResultList();
		/*AuditQuery query = AuditReaderFactory.get(session).createQuery().forEntitiesAtRevision(Application.class, 3);
		System.out.println("QUERY RESULTS : "+query.getResultList());
		return query.getResultList();*/
	}

}
