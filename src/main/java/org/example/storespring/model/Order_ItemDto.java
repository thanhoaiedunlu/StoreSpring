package org.example.storespring.model;


import lombok.Data;

@Data
public class Order_ItemDto{
	private int id;
	private int count;
	private ProductDto product;
//	private OrderDto order;
}
