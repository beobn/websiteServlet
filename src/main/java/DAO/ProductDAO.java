package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Category;
import model.Product;
import model.User;
import utils.JpaUtil;

public class ProductDAO {
	private EntityManager em;
	public ProductDAO() {
		this.em = JpaUtil.getEntityManager();
	}
	public Product create(Product entity) throws Exception {
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

	public Product update(Product entity) throws Exception {
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

	public Product delete(Product entity) throws Exception {
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

	public List<Product> all() {
		String jpql = "select obj from Product obj";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class);
		return  query.getResultList();
	}

	public Product findBy(int id) {
		return this.em.find(Product.class, id);
	}
	public List<Product> findBycate(int id) {
		String jpql = "SELECT obj FROM Product obj WHERE obj.categoryByCategoryId.id  =:category_id ";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class);

		query.setParameter("category_id", id);
		List<Product> list = query.getResultList();
		return list;
	}

	
}
