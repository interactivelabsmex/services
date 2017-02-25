package com.il.sod.rest.dto.db;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Set;

public class ServiceTypeDTO {
	private int idServiceType;
	private String description;
	private String name;
	private double price;
	private int time;
	private Set<Integer> services;
	private Set<SpecDTO> specs;
	private List<ServiceTypeTaskDTO> serviceTypeTasks;
	private int idServiceCategory;
	private String serviceTypeCategoryName;
	private Set<ProductTypeDTO> productTypes;
	private boolean calculator;
	
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Set<Integer> getServices() {
		return services;
	}
	public void setServices(Set<Integer> services) {
		this.services = services;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getIdServiceCategory() {
		return idServiceCategory;
	}
	public void setIdServiceCategory(int idServiceCategory) {
		this.idServiceCategory = idServiceCategory;
	}

	public List<ServiceTypeTaskDTO> getServiceTypeTasks() {
		return serviceTypeTasks;
	}

	public void setServiceTypeTasks(List<ServiceTypeTaskDTO> serviceTypeTasks) {
		this.serviceTypeTasks = serviceTypeTasks;
	}

	public String getServiceTypeCategoryName() {
		return serviceTypeCategoryName;
	}
	public void setServiceTypeCategoryName(String serviceTypeCategoryName) {
		this.serviceTypeCategoryName = serviceTypeCategoryName;
	}

	public Set<ProductTypeDTO> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(Set<ProductTypeDTO> productTypes) {
		this.productTypes = productTypes;
	}

	public boolean isCalculator() {
		return calculator;
	}

	public void setCalculator(boolean calculator) {
		this.calculator = calculator;
	}

	public Set<SpecDTO> getSpecs() {
		return specs;
	}

	public void setSpecs(Set<SpecDTO> specs) {
		this.specs = specs;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("idServiceType", idServiceType)
				.add("description", description)
				.add("name", name)
				.add("price", price)
				.add("time", time)
				.add("services", services)
				.add("idServiceCategory", idServiceCategory)
				.add("serviceTypeCategoryName", serviceTypeCategoryName)
				.add("calculator", calculator)
				.toString();
	}
}
