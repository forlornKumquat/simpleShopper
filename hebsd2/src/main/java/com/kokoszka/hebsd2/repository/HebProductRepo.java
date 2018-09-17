package com.kokoszka.hebsd2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kokoszka.hebsd2.database.Product;

public interface HebProductRepo extends CrudRepository<Product, Integer>{
	@Query("SELECT p FROM Product p where p.Department=:dep")
	public List<Product> findByCustomDepartment(@Param("dep") String dep);	
	
	@Query("SELECT p FROM Product p WHERE p.Description LIKE CONCAT('%',:val,'%') ")
	public List<Product> searchLikeStringInDescription(@Param("val") String val);
	
	@Query("SELECT p FROM Product p WHERE p.Department LIKE CONCAT('%',:val,'%') ")
	public List<Product> searchLikeStringInDepartment(@Param("val") String val);
	
	@Query("SELECT p FROM Product p WHERE p.Price LIKE CONCAT('%',:val,'%') ")
	public List<Product> searchLikeStringInPrice(@Param("val") String val);
		
	@Query("SELECT p FROM Product p WHERE p.ID LIKE CONCAT('%',:val,'%')")
	public List<Product> searchLikeNumberValueAcrossID(@Param("val") Integer val);
}

