package com.ppol.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.ppol.common.dao.GenericDao;

@Transactional(readOnly=false)
public class GenericDaoImpl<I extends Serializable, T> implements GenericDao<I, T>
{	
	@Autowired
	private HibernateJpaSessionFactoryBean sessionFactory;
	
	public T get(Class<T> type, I id)
    {
    	EntityManager	entityManager = sessionFactory.getEntityManagerFactory().createEntityManager();
    	
    	return entityManager.find(type, id);
    }

    public void save(T value)
    {
    	EntityManager	entityManager = sessionFactory.getEntityManagerFactory().createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	entityManager.persist(value);
    	
    	entityManager.flush();
    	entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public void update(T value, I id)
    {
    	EntityManager	entityManager = sessionFactory.getEntityManagerFactory().createEntityManager();
    	T 				oldValue = get((Class<T>) value.getClass(), id);
    	
    	entityManager.getTransaction().begin();

    	entityManager.merge(oldValue);
    	entityManager.merge(value);
    	
    	entityManager.flush();
    	entityManager.getTransaction().commit();
    }

    public void delete(T value)
    {
    	EntityManager	entityManager = sessionFactory.getEntityManagerFactory().createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	entityManager.remove(entityManager.contains(value) ? value : entityManager.merge(value));
    	
    	entityManager.flush();
    	entityManager.getTransaction().commit();
    }
    
	@SuppressWarnings("unchecked")
    public List<T> find(String query)
    {
    	EntityManager	entityManager = sessionFactory.getEntityManagerFactory().createEntityManager();
    	
    	Query	queryCmd = entityManager.createQuery(query);
    	
    	return queryCmd.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<T> find(String query, Object value)
    {
    	EntityManager	entityManager = sessionFactory.getEntityManagerFactory().createEntityManager();
    	
    	Query	queryCmd = entityManager.createQuery(query);
    	
    	return queryCmd.getResultList();
    }
}
