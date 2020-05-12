package com.cts.service;

import java.util.Collection;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.entity.Product;
import com.cts.entity.Vendor;

@FeignClient(name = "vendor-service")
public interface VendorServiceProxy {

	 @RequestMapping("/vendor/getAll")
	   public Collection<Vendor> getAll();
	 
	 @RequestMapping("/vendor/get/{vendorId}")
	   public Vendor getVendorById(@PathVariable int vendorId);

}