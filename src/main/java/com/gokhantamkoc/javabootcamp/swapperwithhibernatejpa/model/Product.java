package com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;

	@Column(name="name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	public String toString() {
		return String.format("id %d, name %s, description %s", this.id, this.name, this.description);
	}
}
