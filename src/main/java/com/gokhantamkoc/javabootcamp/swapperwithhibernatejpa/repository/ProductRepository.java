package com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa.model.Product;

// import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class ProductRepository {


	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// insert into product (name, description) values (?, ?);
	public Integer createProduct(Product product) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		try {
			Integer id = (Integer) session.save(product);
			session.getTransaction().commit();
			System.out.println("Product is created!");
			return id;
		} catch (Exception ex) {
			System.out.println("Product cannot be created! " + ex.getMessage());
			session.getTransaction().rollback();
		}
		return null;
	}

	// select * from product where id = ?
	public Product getProduct(int id) throws Exception {
		Session session = this.sessionFactory.openSession();
		Product foundProduct = session.get(Product.class, id);
		if (foundProduct != null) {
			return foundProduct;
		}
		throw new Exception("Product does not exist!");
	}

	// select * from product
	public List<Product> getAllProduct() {
		try {
			Session session = this.sessionFactory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
			Root<Product> root = criteriaQuery.from(Product.class);

			CriteriaQuery<Product> criteriaQuery2 = criteriaQuery.select(root);
			Query<Product> query = session.createQuery(criteriaQuery2);
			List<Product> productList = query.getResultList();
			return productList;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ArrayList<Product>();
		}
	}


	public boolean updateProductById(Product updatedProduct) {
		try {
			Session session = this.sessionFactory.openSession();
			Product foundProduct = session.get(Product.class, updatedProduct.getId());
			if (foundProduct != null) {
				foundProduct.setName(updatedProduct.getName());
				foundProduct.setDescription(updatedProduct.getDescription());
				session.beginTransaction();
				session.update(foundProduct);
				session.getTransaction().commit();
				return true;
			}
			System.out.println("Product is not found!");
			return false;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
}
