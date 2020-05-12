package com.cts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;

@ApiModel(description = "This is the product model")
@Entity(name="products")
//@Data
public class Product {
	
	@ApiModelProperty(value = "A unique key for each product")
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private int productId;
	
	@ApiModelProperty(value = "Name of the product")
	@NotNull(message = "Name cannot be null")
	@Pattern(regexp = "[a-zA-Z]{2}[A-Za-z\\s]*", message = "name can only have alphabets with minimum length 2")
	@Size(min = 2, message = "name must have atleast 2 characters")
	@Column(name="name")
	private String productName;
	
	@Pattern(regexp = "[a-zA-Z]{2}[A-Za-z\\s]*", message = "description can only have alphabets")
	@ApiModelProperty(value = "Description of the product")
	@Column(name="description")
	private String productDescription;
	
	@ApiModelProperty(value = "Price of product")
	@Column
	private float price;

	private int  vid;
	
	@Transient
	private Vendor vendor;
	public Product() {
	
	}
	public Product(int productId,
			@NotNull(message = "Name cannot be null") @Pattern(regexp = "[a-zA-Z]{2}[A-Za-z\\s]*", message = "name can only have alphabets with minimum length 2") @Size(min = 2, message = "name must have atleast 2 characters") String productName,
			@Pattern(regexp = "[a-zA-Z]{2}[A-Za-z\\s]*", message = "description can only have alphabets") String productDescription,
			float price, int vid) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.price = price;
		this.vid = vid;
	}
	
	public Product(int productId, String productName, String productDescription, float price, int vid, Vendor vendor) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.price = price;
		this.vid = vid;
		this.vendor = vendor;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public Product setVendor(Vendor vendor) {
		this.vendor = vendor;
		return null;
	}


	
	
}