package br.com.b2w.starwars.persistencia.dao.mongo;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.persistencia.dao.GenericDAO;
import br.com.b2w.starwars.util.Constants;

public abstract class MongoGenericDao<T> implements GenericDAO<T> {

	@PersistenceContext(unitName = "ogmjpaPU")
	protected EntityManager em;

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public MongoGenericDao() {
		try {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		} catch (java.lang.ClassCastException e) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
	}

	public MongoGenericDao(EntityManager em) {
		this();
		this.em = em;
	}

	protected EntityManager getEntityManager() {
		return em;
	}

	public void incluir(T entity) {
		em.persist(entity);
	}

	public void alterar(T entity) {
		em.merge(entity);
	}

	public void excluir(T entity) throws BasicException {
		if (entity != null) {
			em.remove(em.contains(entity) ? entity : em.merge(entity));
			em.flush();
		}
	}

	public int excluirTodos() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<T> delete = builder.createCriteriaDelete(entityClass);
		delete.from(entityClass);

		return em.createQuery(delete).executeUpdate();
	}

	public T buscar(Object id) {
		try {
			TypedQuery<T> query = em.createQuery("from " + entityClass.getSimpleName() + " where id=:id",
					entityClass);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	public List<T> listar() {
		TypedQuery<T> query = em.createQuery("from " + entityClass.getSimpleName(), entityClass);
		return query.getResultList();
	}

	@Override
	public long contarTodos() {
		try {
			Query query = em.createNativeQuery("db." + entityClass.getSimpleName() + ".count({})");
			return (Long)query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}
		/*try {
			TypedQuery<Long> query = em.createQuery("select count(nome) from " + entityClass.getSimpleName(), Long.class);
			return query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}*/
	}

	@Override
	public List<T> listar(Integer page) {
		TypedQuery<T> query = em.createQuery("from " + entityClass.getSimpleName(), entityClass);
		query.setMaxResults(Constants.SIZE);
		query.setFirstResult((page - 1) * Constants.SIZE);
		return query.getResultList();
	}

}
