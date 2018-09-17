package com.kokoszka.hebsd2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kokoszka.hebsd2.database.Product;
import com.kokoszka.hebsd2.repository.HebProductRepo;
import com.kokoszka.hebsd2.repository.RepoUtil;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

	@Autowired
	HebProductRepo repo;
	
	@Autowired
	RepoUtil repoUtil;

	@GetMapping(path="/products")
	@ResponseBody
	public ResponseEntity<List<Product>> getAll(
			@RequestParam(value = "search", required = false) String search){
		List<Product> allProducts;
		if(StringUtils.isEmpty(search)) {
			allProducts = (List<Product>) repo.findAll();
		}else {
			allProducts = repoUtil.searchString(search);
		}
		return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
	}
	
	@GetMapping(path="/filter")
	@ResponseBody
	public ResponseEntity<List<Product>> getCategories(
			@RequestParam(value="pr") boolean produce,
			@RequestParam(value="gr") boolean grocery,
			@RequestParam(value="ph") boolean pharmacy){
		
		List<Product> resp = new ArrayList<>();
		if(produce) {
			resp.addAll(repo.findByCustomDepartment("Produce"));
		}
		if(grocery) {
			resp.addAll(repo.findByCustomDepartment("Grocery"));
		}
		if(pharmacy) {
			resp.addAll(repo.findByCustomDepartment("Pharmacy"));
		}
		return new ResponseEntity<List<Product>>(resp, HttpStatus.OK);
	}
	
	@GetMapping(path="/popDb")
	public @ResponseBody String popDb() {
		repoUtil.fillDB();
		return "OK";
	}
}
