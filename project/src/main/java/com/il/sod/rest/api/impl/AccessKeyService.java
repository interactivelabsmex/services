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

import com.il.sod.db.model.entities.AccessKey;
import com.il.sod.db.model.repositories.AccessKeyRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AccessKeyDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/access-key")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/access-key", tags = { "clients" })
public class AccessKeyService extends AbstractServiceMutations {
	@Autowired
	AccessKeyRepository accessKeyRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = AccessKeyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveAccessKey(AccessKeyDTO dto) throws SODAPIException {
		try {
			AccessKey entity = converter.map(dto, AccessKey.class);
			this.saveEntity(accessKeyRepository, entity);
			dto = converter.map(entity, AccessKeyDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = AccessKeyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAccessKey(AccessKeyDTO dto) throws SODAPIException {
		try {
			AccessKey entity = converter.map(dto, AccessKey.class);
			this.updateEntity(accessKeyRepository, entity);
			dto = converter.map(entity, AccessKeyDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = AccessKeyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteAccessKey(AccessKeyDTO dto) throws SODAPIException {
		try {
			AccessKey entity = converter.map(dto, AccessKey.class);
			this.deleteEntity(accessKeyRepository, entity.getIdAccessKey());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = AccessKeyDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAccessKeyList() throws SODAPIException {
		List<AccessKey> rentityList = this.getEntityList(accessKeyRepository);
		List<AccessKeyDTO> list = rentityList.stream().map((i) -> {
			AccessKeyDTO dto = converter.map(i, AccessKeyDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
