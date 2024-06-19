package org.example.storespring.model;

import lombok.Data;

import java.util.List;
@Data
public class CategoryDto{
	private int id;
	private String category_Name;
	private String category_Image;
	private List<ProductDto> product;
}