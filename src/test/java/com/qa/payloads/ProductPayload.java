package com.qa.payloads;

import java.util.HashMap;
import java.util.Map;

public class ProductPayload {
	
	public static Map<String, Object> createProduct(){
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("title", "New Product");
		payload.put("price", 99.99);
		payload.put("description", "Created via API");
		payload.put("image", "https://i.pravatar.cc");
		payload.put("category", "electronics");
		
		return payload;
	}
	
	public static Map<String, Object> updateProductP(){
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("title", "Updated Product");
		payload.put("price", 199.99);
		payload.put("description", "Updated via API");
		payload.put("image", "https://i.pravatar.cc");
		payload.put("category", "electronics");
		
		return payload;
		
	}
	
	
	public static Map<String, Object> patchProductPrice(){
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("price", 149.99);
		
		return payload;	
	}


}
