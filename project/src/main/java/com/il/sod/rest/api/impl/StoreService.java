package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.Store;
import com.il.sod.db.model.repositories.StoreRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.StoreInfoMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.StoreDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/store")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/store", tags = { "app-utils" })
public class StoreService extends AbstractServiceMutations {

	@Autowired
	StoreRepository storeRepository;

	@POST
	@ApiOperation(value = "Create Store", response = StoreDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveStore(StoreDTO dto) throws SODAPIException {
		try {
			Store entity = StoreInfoMapper.INSTANCE.map(dto);
			this.saveEntity(storeRepository, entity);
			dto = StoreInfoMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Store", response = StoreDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateStore(StoreDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Store", response = StoreDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateStoreById(@PathParam("id") String id, StoreDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(StoreDTO dto) throws SODAPIException {
		try {
			Store entity = StoreInfoMapper.INSTANCE.map(dto);
			this.updateEntity(storeRepository, entity);
			dto = StoreInfoMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		Store entity = storeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(storeRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Store list", response = StoreDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getStoreList() throws SODAPIException {
		List<Store> rentityList = storeRepository.findAll();
		List<StoreDTO> list = rentityList.stream().map((i) -> {
			StoreDTO dto = StoreInfoMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/byId/{id}")
	@ApiOperation(value = "Get Store by ID", response = StoreDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getStoreById(@PathParam("id") Integer id) throws SODAPIException {
		Store entity = storeRepository.findOne(id);
		StoreDTO result = StoreInfoMapper.INSTANCE.map(entity);
		result.getDistanceInfos();
		return castEntityAsResponse(result);
	}

}