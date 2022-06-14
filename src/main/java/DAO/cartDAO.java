package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Cart;
import model.Category;
import model.Product;
import utils.JpaUtil;

public class cartDAO {
	private EntityManager em;
	public cartDAO(){
		this.em = JpaUtil.getEntityManager();
	}
	public Cart create(Cart entity)throws Exception{
		try {
			this.em.getTransaction().begin();
			this.em.persist(entity);
			this.em.getTransaction().commit();

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	public Cart update(Cart entity) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.merge(entity);
			this.em.getTransaction().commit();

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

	public Cart delete(Cart entity) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.remove(entity);
			this.em.getTransaction().commit();

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	public List<Cart> all() {
		String jpql = "select obj from Cart obj";
		TypedQuery<Cart> query = this.em.createQuery(jpql, Cart.class);
		List<Cart> result = query.getResultList();
		return result;
	}
	public Cart findBy(int id) {
		return this.em.find(Cart.class, id);
	}
}
