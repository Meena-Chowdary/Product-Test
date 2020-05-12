package com.cts.service;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.entity.Product;
import com.cts.entity.Vendor;
import com.cts.repository.ProductManagementRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTesting {
	
	@Mock
	private ProductManagementRepository repo;
	
	@InjectMocks
	private ProductService service;
	
	@Test
	public void addProductTest(){
	      Product product=new Product();
	      product.setProductId(1);
	      product.setProductName("AC");
	      product.setProductDescription("Electronics");
	      product.setPrice(67000);
	      product.setVid(1);
	      product.setVendor(new Vendor(1L,"Meena","Bengaluru","7896541230","meena@gmail.com"));
	      Mockito.when(repo.save(product)).thenReturn(product);	
	      
	      assertSame(1,service.addProduct(product).getProductId());
	}
		
	
}