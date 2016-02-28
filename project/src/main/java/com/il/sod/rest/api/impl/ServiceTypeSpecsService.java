package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.ServiceTypeSpec;
import com.il.sod.db.model.repositories.ServiceTypeSpecRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceTypeSpecDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/service-type-specs")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/service-type-specs", tags = { "service" })
public class ServiceTypeSpecsService extends AbstractServiceMutations {
	@Autowired
	ServiceTypeSpecRepository serviceTypeSpecRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = ServiceTypeSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveServiceTypeSpec(ServiceTypeSpecDTO dto) throws SODAPIException {
		try {
			ServiceTypeSpec entity = converter.map(dto, ServiceTypeSpec.class);
			this.saveEntity(serviceTypeSpecRepository, entity);
			dto = converter.map(entity, ServiceTypeSpecDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = ServiceTypeSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateServiceTypeSpec(ServiceTypeSpecDTO dto) throws SODAPIException {
		try {
			ServiceTypeSpec entity = converter.map(dto, ServiceTypeSpec.class);
			this.updateEntity(serviceTypeSpecRepository, entity);
			dto = converter.map(entity, ServiceTypeSpecDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = ServiceTypeSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteServiceTypeSpec(ServiceTypeSpecDTO dto) throws SODAPIException {
		try {
			ServiceTypeSpec entity = converter.map(dto, ServiceTypeSpec.class);
			this.deleteEntity(serviceTypeSpecRepository, entity.getIdServiceTypeSpecs());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeSpecDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getServiceTypeSpecList() throws SODAPIException {
		List<ServiceTypeSpec> rentityList = this.getEntityList(serviceTypeSpecRepository);
		List<ServiceTypeSpecDTO> list = rentityList.stream().map((i) -> {
			ServiceTypeSpecDTO dto = converter.map(i, ServiceTypeSpecDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
