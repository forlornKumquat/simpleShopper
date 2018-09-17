package com.kokoszka.hebsd2.database;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.kokoszka.hebsd2.serialization.LocalDateSerializer;
import com.kokoszka.hebsd2.serialization.LocalDateDeserializer;

@Entity
public class Product {

	@Id
	@Column(name="ID")
    private Integer ID;
	
    @Column(name="Description")
    private String Description;
    
    @Column(name="lastSold")
    @JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class)  
    private LocalDate lastSold;
    
    @Column(name="ShelfLife")
    private String ShelfLife;
    
    @Column(name="Department")
    private String Department;

    @Column(name="Price")
    private String Price;

    @Column(name="Unit")
    private String Unit;

    @Column(name="xFor")
    private Integer xFor;

    @Column(name="Cost")
    private String Cost;
    
    
    public Integer getID() {
    	return ID;
    }
    public void setID(Integer id) {
    	this.ID = id;
    }
    
    public String getDescription() {
    	return Description;
    }
    public void setDescription(String description) {
    	this.Description = description;
    }
    
    public LocalDate getLastSold() {
    	return lastSold;
    }
    public void setLastSold(LocalDate lastSold) {
    	this.lastSold = lastSold;
    }
    
    public String getShelfLife() {
    	return ShelfLife;
    }
    public void setShelfLife(String shelfLife) {
    	this.ShelfLife = shelfLife;
    }
    
    public String getDepartment() {
    	return Department;
    }
    public void setDepartment(String department) {
    	this.Department = department;
    }
    
    public String getPrice() {
    	return Price;
    }
    public void setPrice(String price) {
    	this.Price = price;
    }
    
    public String getUnit() {
    	return Unit;
    }
    public void setUnit(String unit) {
    	this.Unit = unit;
    }
    
    public Integer getXFor() {
    	return xFor;
    }
    public void setXFor(Integer xfor) {
    	this.xFor = xfor;
    }

    public String getCost() {
    	return Cost;
    }
    public void setCost(String cost) {
    	this.Cost = cost;
    }   
}
