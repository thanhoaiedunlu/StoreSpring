package org.example.storespring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "category_Name", columnDefinition = "nvarchar(1111)")
	private String category_Name;
	
	@Column(name = "category_Image", columnDefinition = "nvarchar(1111)")
	private String category_Image;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> product;
}