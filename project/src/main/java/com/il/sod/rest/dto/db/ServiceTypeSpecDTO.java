package com.il.sod.rest.dto.db;

public class ServiceTypeSpecDTO {
	private int idServiceTypeSpecs;
	private String comments;
	private Integer serviceType;
	private SpecDTO spec;
	public int getIdServiceTypeSpecs() {
		return idServiceTypeSpecs;
	}
	public void setIdServiceTypeSpecs(int idServiceTypeSpecs) {
		this.idServiceTypeSpecs = idServiceTypeSpecs;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public SpecDTO getSpec() {
		return spec;
	}
	public void setSpec(SpecDTO spec) {
		this.spec = spec;
	}
}
