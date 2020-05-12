package com.cts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.controller.ProductController;
import com.cts.entity.Product;
import com.cts.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@Test
	public void getAllTest() throws Exception{
		when(productService.getAll()).thenReturn(
				Arrays.asList(new Product(1,"ProductName","ProductDescription",1000,1)));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/products/all")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{productId:1,productName:ProductName,productDescription:ProductDescription,price:1000.0,vid:1}]"))
				.andReturn();
	}
	
	@Test
	public void addProductTest() throws Exception{
		
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/products/add")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"productId\":10, \"productName\": \"Perfume\", \"productDescription\": \"Fragrance\", \"price\": \"1000\", \"vid\": \"1\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is(200))
				.andReturn();
	}
	
	@Test
	public void deleteProductTest() throws Exception{
		
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/products/10")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"productId\":10, \"productName\": \"Perfume\", \"productDescription\": \"Fragrance\", \"price\": \"1000\", \"vid\": \"1\"}");
				
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is(200))
				.andReturn();
	}
	
	@Test
	public void updateProductTest() throws Exception{
		
		RequestBuilder request = MockMvcRequestBuilders
				.put("/products/update/10")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"productId\":10, \"productName\": \"Perfume\", \"productDescription\": \"Fragrance\", \"price\": \"1000\", \"vid\": \"1\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is(200))
				.andReturn();
	}
	
	
	
	
}
