package com.kokoszka.hebsd2.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokoszka.hebsd2.database.Product;

@Component
public class RepoUtil {
	
	@Autowired
	HebProductRepo repo;
	
	public ArrayList<Product> searchString(String search){
		search.replace("[^a-zA-Z0-9 ]", "");
		StringTokenizer st = new StringTokenizer(search, " "); 
		
		Set<Product> productSet = new HashSet<>();
        while (st.hasMoreTokens()) {
        	// add queries to set
        	String token = st.nextToken();        	
        	if(NumberUtils.isDigits(token)) {
        		productSet.addAll(repo.searchLikeNumberValueAcrossID(Integer.parseInt(token)));
        	}
        	productSet.addAll(repo.searchLikeStringInDescription(token));
        	productSet.addAll(repo.searchLikeStringInDepartment(token));
        	productSet.addAll(repo.searchLikeStringInPrice(token));
        }
		return new ArrayList<Product>(productSet);
	}
	
	public void fillDB() {
		List<Product> prodList = (List<Product>) repo.findAll();
		
		if(prodList.size() > 0) {
			System.out.println("size0");
		}else {
			
			for(Product product: getSampleList()) {
				repo.save(product);
			}
		}
	}
	
	private List<Product> getSampleList(){
		InputStream inJson = Product.class.getResourceAsStream("/product.json");
		List<Product> sample = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			sample = objectMapper.readValue(inJson, new TypeReference<List<Product>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sample;
	}
	
}
