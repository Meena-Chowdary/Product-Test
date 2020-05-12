package com.cts.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entity.Product;
import com.cts.entity.Vendor;
import com.cts.repository.ProductManagementRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductManagementRepository repo;
	
	@Autowired
	private VendorServiceProxy proxy;
	
	public Vendor getVendorById(int vendorId) {
		
		return proxy.getVendorById(vendorId);
	}

	public Product getProductById(int productId) {
		Product p=repo.findById(productId).orElse(null);
		Product p1=p.setVendor(proxy.getVendorById(p.getVid()));
		return p;
	}

	public Product addProduct(Product product) {
		return repo.save(product);
	}

	public void deleteProduct(Integer productId) {
		repo.deleteById(productId);
		
	}

	public void updateProduct(Product product,Integer productId) {
		repo.save(product);
		
	}

	public List<Product> getAll() {
		List<Product> products= (List<Product>) repo.findAll();
		for(Product p:products) {
			p.setVendor(proxy.getVendorById(p.getVid()));
		}
		return products;
	}

	

}