package com.cts.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.Product;
import com.cts.entity.Vendor;
import com.cts.service.ProductService;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/products")
@Api(value = "The Product Controller", description = "Rest controller for product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@ApiOperation(value = "Get all products", produces = "A list of products", notes = "Hit this URL to get all products")
	@GetMapping("/all")
	List<Product> getAll() {
		return service.getAll();
	}

	@ApiOperation(value = "Retrieve a product's details", produces = "A product's details if it exists", notes = "Hit this URL to get a product's details")
	@GetMapping("/{productId}")
	Product getById(@PathVariable int productId) {
		return service.getProductById(productId);
	}

	@ApiOperation(value = "Add a product", consumes = "A new product is JSON", notes = "Hit this URL to insert a new product's details")
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	void addProduct(@Valid @RequestBody Product product) {
		service.addProduct(product);
	}

	@ApiOperation(value = "Update a product's details", consumes = "An existing product in JSON", notes = "Hit this URL to update a product's details")
	@RequestMapping(method=RequestMethod.PUT,value="/update/{productId}")
	void updateProduct(@Valid @RequestBody Product product, @PathVariable int productId) {
		service.updateProduct(product,productId);
	}

	
	 @RequestMapping("/dashboard/feign/vendors")
	 @HystrixCommand(fallbackMethod = "fallback")
	   public  List<Product> findPeers(){
	        return service.getAll();
	   }
	 
	 public  Collection<Vendor> fallback(){
		 System.out.println("server down");
	        return Arrays.asList(new Vendor(100, "Fallback vendor", "This is fallback vendor,means server is down", "7896541230", "meena@gmail.com"));    		         
	   }
	

	@ApiOperation(value = "Delete a product", consumes = "An existing product id", notes = "Hit this URL to delete a product's details")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
	void deleteProductById(@PathVariable int productId) {
		service.deleteProduct(productId);
	}
	
	@RequestMapping("/vendor/feign/{vendorId}")
	   public Vendor getVendorById(@PathVariable int vendorId) {
		return service.getVendorById(vendorId);
		 
	 }
	
}