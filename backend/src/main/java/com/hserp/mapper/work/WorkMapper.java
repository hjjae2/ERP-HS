package com.hserp.mapper.work;

import com.hserp.dto.work.WorkRequestDto;
import com.hserp.dto.work.WorkResponseDto;
import com.hserp.entity.work.Work;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface WorkMapper {
    WorkMapper INSTANCE = Mappers.getMapper(WorkMapper.class);

    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "worker.name", target = "worker")
    @Mapping(source = "company.name", target = "company")
    @Mapping(source = "customer.name", target = "customer")
    @Mapping(source = "dispatcher.name", target = "dispatcher")
    @Mapping(source = "workType.name", target = "workType")
    @Mapping(source = "paymentStatus.name", target = "paymentStatus")
    @Mapping(source = "expenditureStatus.name", target = "expenditureStatus")
    @Mapping(source = "taxStatus.name", target = "taxStatus")
    WorkRequestDto workToWorkRequestDto(Work work);

    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "worker.name", target = "worker")
    @Mapping(source = "company.name", target = "company")
    @Mapping(source = "customer.name", target = "customer")
    @Mapping(source = "dispatcher.name", target = "dispatcher")
    @Mapping(source = "workType.name", target = "workType")
    @Mapping(source = "paymentStatus.name", target = "paymentStatus")
    @Mapping(source = "expenditureStatus.name", target = "expenditureStatus")
    @Mapping(source = "taxStatus.name", target = "taxStatus")
    WorkResponseDto workToWorkResponseDto(Work work);

    @Mapping(source = "price", target = "price", defaultValue = "0")
    @Mapping(source = "communicator", target = "communicator", defaultValue = "'N'")
    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(target = "worker", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "dispatcher", ignore = true)
    @Mapping(target = "workType", ignore = true)
    @Mapping(target = "paymentStatus", ignore = true)
    @Mapping(target = "expenditureStatus", ignore = true)
    @Mapping(target = "taxStatus", ignore = true)
    Work workRequestDtoToWork(WorkRequestDto workRequestDto);

    @Mapping(source = "price", target = "price", defaultValue = "0")
    @Mapping(source = "communicator", target = "communicator", defaultValue = "'N'")
    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(target = "worker", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "dispatcher", ignore = true)
    @Mapping(target = "workType", ignore = true)
    @Mapping(target = "paymentStatus", ignore = true)
    @Mapping(target = "expenditureStatus", ignore = true)
    @Mapping(target = "taxStatus", ignore = true)
    Work workResponseDtoToWork(WorkResponseDto workResponseDto);
}
