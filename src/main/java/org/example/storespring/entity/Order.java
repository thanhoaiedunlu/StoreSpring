package org.example.storespring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;


@Entity
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "booking_Date")
	private Date booking_Date;
	
	@Column(name = "payment_Method", columnDefinition = "nvarchar(1111)")
	private String payment_Method;
	
	@Column(name = "status", columnDefinition = "nvarchar(1111)")
	private String status;
	
	@Column(name = "fullname", columnDefinition = "nvarchar(1111)")
	private String fullname;
	
	@Column(name = "country", columnDefinition = "nvarchar(1111)")
	private String country;
	
	@Column(name = "address", columnDefinition = "nvarchar(1111)")
	private String address;
	
	@Column(name = "phone", columnDefinition = "nvarchar(1111)")
	private String phone;
	
	@Column(name = "email", columnDefinition = "nvarchar(1111)")
	private String email;
	
	@Column(name = "note", columnDefinition = "nvarchar(1111)")
	private String note;
	
	@OneToMany(mappedBy = "order")
	private List<Order_Item> order_Item;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private User user;
}
