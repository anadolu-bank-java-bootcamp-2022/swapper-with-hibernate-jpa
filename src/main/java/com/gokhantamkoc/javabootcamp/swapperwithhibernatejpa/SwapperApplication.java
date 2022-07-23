package com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa;

import com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa.model.Product;
import com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa.service.ProductService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;


public class SwapperApplication {

	public static void main(String... args) {
		AbstractApplicationContext ctx = null;
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			ProductService productService = ctx.getBean(ProductService.class);
			//  productService.create(createJediFigureSet());
			// System.out.println(productService.get(2));
			// productService.updateById(updatedJediFigureSet());

			List<Product> productList = productService.list();
			Iterator productIterator = productList.listIterator();
			while (productIterator.hasNext()) {
				System.out.println(productIterator.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ctx != null)
				ctx.close();
		}
	}

	public static Product createJediFigureSet() {
		return new Product(
				0,
				"Jedi Figure Set",
				"May the 4th be with you!"
		);
	}

	public static Product updatedJediFigureSet() {
		return new Product(
				2,
				"Sith Figure Set",
				"May the 4th be with you!"
		);
	}
}
