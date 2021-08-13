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
    @Mapping(source = "workType.name", target = "workType")
    @Mapping(source = "workStatus.name", target = "workStatus")
    @Mapping(source = "paymentStatus.name", target = "paymentStatus")
    @Mapping(source = "expenditureStatus.name", target = "expenditureStatus")
    @Mapping(source = "taxStatus.name", target = "taxStatus")
    WorkRequestDto workToWorkRequestDto(Work work);

    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "worker.name", target = "worker")
    @Mapping(source = "company.name", target = "company")
    @Mapping(source = "workType.name", target = "workType")
    @Mapping(source = "workStatus.name", target = "workStatus")
    @Mapping(source = "paymentStatus.name", target = "paymentStatus")
    @Mapping(source = "expenditureStatus.name", target = "expenditureStatus")
    @Mapping(source = "taxStatus.name", target = "taxStatus")
    WorkResponseDto workToWorkResponseDto(Work work);

    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(source = "worker", target = "worker.name")
    @Mapping(source = "company", target = "company.name")
    @Mapping(source = "workType", target = "workType.name")
    @Mapping(source = "workStatus", target = "workStatus.name")
    @Mapping(source = "paymentStatus", target = "paymentStatus.name")
    @Mapping(source = "expenditureStatus", target = "expenditureStatus.name")
    @Mapping(source = "taxStatus", target = "taxStatus.name")
    Work workRequestDtoToWork(WorkRequestDto workRequestDto);

    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(source = "worker", target = "worker.name")
    @Mapping(source = "company", target = "company.name")
    @Mapping(source = "workType", target = "workType.name")
    @Mapping(source = "workStatus", target = "workStatus.name")
    @Mapping(source = "paymentStatus", target = "paymentStatus.name")
    @Mapping(source = "expenditureStatus", target = "expenditureStatus.name")
    @Mapping(source = "taxStatus", target = "taxStatus.name")
    Work workResponseDtoToWork(WorkResponseDto workResponseDto);
}
