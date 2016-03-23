package com.il.sod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceSpec;
import com.il.sod.db.model.entities.ServiceTask;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.ServiceTypeSpec;
import com.il.sod.db.model.entities.ServiceTypeTask;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.db.ServiceTypeDTO;
import com.il.sod.rest.dto.db.ServiceTypeSpecDTO;
import com.il.sod.rest.dto.db.ServiceTypeTaskDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum ServiceMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private ServiceMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("serviceListConverter", new ServiceListConverter());
		converterFactory.registerConverter("serviceTypeSpecListConverter", new ServiceTypeSpecListConverter());
		converterFactory.registerConverter("serviceTypeTaskListConverter", new ServiceTypeTaskListConverter());
		converterFactory.registerConverter("serviceSpecListConverter", new ServiceSpecListConverter());
		converterFactory.registerConverter("serviceTaskListConverter", new ServiceTaskListConverter());
		converterFactory.registerConverter("orderTypeListConverter", new OrderTypeListConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceDTO.class, Service.class)
			.fieldMap("serviceSpecs", "serviceSpecs").converter("serviceSpecListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTasks", "serviceTasks").converter("serviceTaskListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("serviceType", "serviceType.idServiceType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeDTO.class, ServiceType.class)
			.fieldMap("services", "services").converter("serviceListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypeSpecs", "serviceTypeSpecs").converter("serviceTypeSpecListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypeTasks", "serviceTypeTasks").converter("serviceTypeTaskListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("orderTypes", "orderTypes").converter("orderTypeListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeSpecDTO.class, ServiceTypeSpec.class)
			.field("serviceType", "serviceType.idServiceType")
			.field("spec", "serviceType.idSpecs")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeTaskDTO.class, ServiceTypeTask.class)
			.field("serviceType", "serviceType.idServiceType")
			.field("task", "task.idSpecs")
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Service map(ServiceDTO dto) {
		return this.mapperFacade.map(dto, Service.class);
	}

	public ServiceDTO map(Service entity) {
		return this.mapperFacade.map(entity, ServiceDTO.class);
	}
	
	public ServiceType map(ServiceTypeDTO dto) {
		return this.mapperFacade.map(dto, ServiceType.class);
	}
	
	public ServiceTypeDTO map(ServiceType entity) {
		return this.mapperFacade.map(entity, ServiceTypeDTO.class);
	}
	
	public ServiceTypeSpec map(ServiceTypeSpecDTO dto) {
		return this.mapperFacade.map(dto, ServiceTypeSpec.class);
	}
	
	public ServiceTypeSpecDTO map(ServiceTypeSpec entity) {
		return this.mapperFacade.map(entity, ServiceTypeSpecDTO.class);
	}
	
	public ServiceTypeTask map(ServiceTypeTaskDTO dto) {
		return this.mapperFacade.map(dto, ServiceTypeTask.class);
	}
	
	public ServiceTypeTaskDTO map(ServiceTypeTask entity) {
		return this.mapperFacade.map(entity, ServiceTypeTaskDTO.class);
	}
}

class ServiceListConverter extends BidirectionalConverter<List<Service>, List<Integer>> {
	@Override
	public List<Service> convertFrom(List<Integer> source, Type<List<Service>> arg1) {
		return source.stream().map(p -> (new Service()).setId(p)).collect(Collectors.toList());
	}

	@Override
	public List<Integer> convertTo(List<Service> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class ServiceTypeSpecListConverter extends BidirectionalConverter<List<ServiceTypeSpec>, List<Integer>> {
	@Override
	public List<ServiceTypeSpec> convertFrom(List<Integer> source, Type<List<ServiceTypeSpec>> arg1) {
		return source.stream().map(p -> (new ServiceTypeSpec()).setId(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> convertTo(List<ServiceTypeSpec> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class ServiceTypeTaskListConverter extends BidirectionalConverter<List<ServiceTypeTask>, List<Integer>> {
	@Override
	public List<ServiceTypeTask> convertFrom(List<Integer> source, Type<List<ServiceTypeTask>> arg1) {
		return source.stream().map(p -> (new ServiceTypeTask()).setId(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> convertTo(List<ServiceTypeTask> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class ServiceSpecListConverter extends BidirectionalConverter<List<ServiceSpec>, List<Integer>> {
	@Override
	public List<ServiceSpec> convertFrom(List<Integer> source, Type<List<ServiceSpec>> arg1) {
		return source.stream().map(p -> (new ServiceSpec()).setId(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> convertTo(List<ServiceSpec> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class ServiceTaskListConverter extends BidirectionalConverter<List<ServiceTask>, List<Integer>> {
	@Override
	public List<ServiceTask> convertFrom(List<Integer> source, Type<List<ServiceTask>> arg1) {
		return source.stream().map(p -> (new ServiceTask()).setId(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> convertTo(List<ServiceTask> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class OrderTypeListConverter extends BidirectionalConverter<List<OrderType>, List<Integer>> {
	@Override
	public List<OrderType> convertFrom(List<Integer> source, Type<List<OrderType>> arg1) {
		return source.stream().map(p -> (new OrderType()).setId(p)).collect(Collectors.toList());
	}

	@Override
	public List<Integer> convertTo(List<OrderType> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

