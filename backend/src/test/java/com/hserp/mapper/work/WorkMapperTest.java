package com.hserp.mapper.work;

import com.hserp.CommonVariable;
import com.hserp.dto.work.WorkRequestDto;
import com.hserp.dto.work.WorkResponseDto;
import com.hserp.entity.company.Company;
import com.hserp.entity.person.Person;
import com.hserp.entity.work.Work;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class WorkMapperTest {

    @Test
    void workToWorkRequestDto_1() {
        // given
        Work work = CommonVariable.getWork();

        // when
        WorkRequestDto workRequestDto = WorkMapper.INSTANCE.workToWorkRequestDto(work);

        // then
        Assertions.assertThat(workRequestDto.getPrice()).isEqualTo(work.getPrice());
        Assertions.assertThat(workRequestDto.getContent()).isEqualTo(work.getContent());
        Assertions.assertThat(workRequestDto.getAddress()).isEqualTo(work.getAddress().getValue());
        Assertions.assertThat(workRequestDto.getRemark()).isEqualTo(work.getRemark().getValue());
        Assertions.assertThat(workRequestDto.getWorkType()).isEqualTo(work.getWorkType().getName());
        Assertions.assertThat(workRequestDto.getWorkStatus()).isEqualTo(work.getWorkStatus().getName());
        Assertions.assertThat(workRequestDto.getPaymentStatus()).isEqualTo(work.getPaymentStatus().getName());
        Assertions.assertThat(workRequestDto.getExpenditureStatus()).isEqualTo(work.getExpenditureStatus().getName());
        Assertions.assertThat(workRequestDto.getCompany()).isNull();
        Assertions.assertThat(workRequestDto.getWorker()).isNull();
    }

    @Test
    void workToWorkRequestDto_2() {
        // given
        Person worker = CommonVariable.getPerson();
        Company company = CommonVariable.getCompany();
        Work work = CommonVariable.getWork(worker, company);

        // when
        WorkRequestDto workRequestDto = WorkMapper.INSTANCE.workToWorkRequestDto(work);

        // then
        Assertions.assertThat(workRequestDto.getPrice()).isEqualTo(work.getPrice());
        Assertions.assertThat(workRequestDto.getContent()).isEqualTo(work.getContent());
        Assertions.assertThat(workRequestDto.getAddress()).isEqualTo(work.getAddress().getValue());
        Assertions.assertThat(workRequestDto.getRemark()).isEqualTo(work.getRemark().getValue());
        Assertions.assertThat(workRequestDto.getWorkType()).isEqualTo(work.getWorkType().getName());
        Assertions.assertThat(workRequestDto.getWorkStatus()).isEqualTo(work.getWorkStatus().getName());
        Assertions.assertThat(workRequestDto.getPaymentStatus()).isEqualTo(work.getPaymentStatus().getName());
        Assertions.assertThat(workRequestDto.getExpenditureStatus()).isEqualTo(work.getExpenditureStatus().getName());
        Assertions.assertThat(workRequestDto.getCompany()).isEqualTo(work.getCompany().getName());
        Assertions.assertThat(workRequestDto.getWorker()).isEqualTo(work.getWorker().getName());
    }

    @Test
    void workToWorkResponseDto_1() {
        // given
        Work work = CommonVariable.getWork();

        // when
        WorkResponseDto workResponseDto = WorkMapper.INSTANCE.workToWorkResponseDto(work);

        // then
        Assertions.assertThat(workResponseDto.getPrice()).isEqualTo(work.getPrice());
        Assertions.assertThat(workResponseDto.getContent()).isEqualTo(work.getContent());
        Assertions.assertThat(workResponseDto.getAddress()).isEqualTo(work.getAddress().getValue());
        Assertions.assertThat(workResponseDto.getRemark()).isEqualTo(work.getRemark().getValue());
        Assertions.assertThat(workResponseDto.getWorkType()).isEqualTo(work.getWorkType().getName());
        Assertions.assertThat(workResponseDto.getWorkStatus()).isEqualTo(work.getWorkStatus().getName());
        Assertions.assertThat(workResponseDto.getPaymentStatus()).isEqualTo(work.getPaymentStatus().getName());
        Assertions.assertThat(workResponseDto.getExpenditureStatus()).isEqualTo(work.getExpenditureStatus().getName());
        Assertions.assertThat(workResponseDto.getCompany()).isNull();
        Assertions.assertThat(workResponseDto.getWorker()).isNull();
    }

    @Test
    void workToWorkResponseDto_2() {
        // given
        Person worker = CommonVariable.getPerson();
        Company company = CommonVariable.getCompany();
        Work work = CommonVariable.getWork(worker, company);

        // when
        WorkResponseDto workResponseDto = WorkMapper.INSTANCE.workToWorkResponseDto(work);

        // then
        Assertions.assertThat(workResponseDto.getPrice()).isEqualTo(work.getPrice());
        Assertions.assertThat(workResponseDto.getContent()).isEqualTo(work.getContent());
        Assertions.assertThat(workResponseDto.getAddress()).isEqualTo(work.getAddress().getValue());
        Assertions.assertThat(workResponseDto.getRemark()).isEqualTo(work.getRemark().getValue());
        Assertions.assertThat(workResponseDto.getWorkType()).isEqualTo(work.getWorkType().getName());
        Assertions.assertThat(workResponseDto.getWorkStatus()).isEqualTo(work.getWorkStatus().getName());
        Assertions.assertThat(workResponseDto.getPaymentStatus()).isEqualTo(work.getPaymentStatus().getName());
        Assertions.assertThat(workResponseDto.getExpenditureStatus()).isEqualTo(work.getExpenditureStatus().getName());
        Assertions.assertThat(workResponseDto.getCompany()).isEqualTo(work.getCompany().getName());
        Assertions.assertThat(workResponseDto.getWorker()).isEqualTo(work.getWorker().getName());
    }

    @Test
    void workRequestDtoToWork() {
        // given
        WorkRequestDto workRequestDto = WorkRequestDto.builder()
                .content(CommonVariable.getContent())
                .price(CommonVariable.getPrice())
                .workDate(LocalDate.now())
                .address(CommonVariable.getAddress().getValue())
                .remark(CommonVariable.getRemark().getValue())
                .workType(CommonVariable.getWorkType().getName())
                .workStatus(CommonVariable.getWorkStatus().getName())
                .worker(null)
                .company(null)
                .paymentStatus(CommonVariable.getPaymentStatus().getName())
                .expenditureStatus(CommonVariable.getExpenditureStatus().getName())
                .taxStatus(CommonVariable.getTaxStatus().getName())
                .build();

        // when
        Work work = WorkMapper.INSTANCE.workRequestDtoToWork(workRequestDto);

        // then
        Assertions.assertThat(work.getPrice()).isEqualTo(workRequestDto.getPrice());
        Assertions.assertThat(work.getContent()).isEqualTo(workRequestDto.getContent());
        Assertions.assertThat(work.getAddress().getValue()).isEqualTo(workRequestDto.getAddress());
        Assertions.assertThat(work.getRemark().getValue()).isEqualTo(workRequestDto.getRemark());
        Assertions.assertThat(work.getWorkType().getName()).isEqualTo(workRequestDto.getWorkType());
        Assertions.assertThat(work.getWorkStatus().getName()).isEqualTo(workRequestDto.getWorkStatus());
        Assertions.assertThat(work.getPaymentStatus().getName()).isEqualTo(workRequestDto.getPaymentStatus());
        Assertions.assertThat(work.getExpenditureStatus().getName()).isEqualTo(workRequestDto.getExpenditureStatus());
        Assertions.assertThat(work.getCompany().getName()).isEqualTo(workRequestDto.getCompany());
        Assertions.assertThat(work.getWorker().getName()).isEqualTo(workRequestDto.getWorker());
    }

    @Test
    void workResponseDtoToWork() {
        // given
        WorkResponseDto workResponseDto = WorkResponseDto.builder()
                .content(CommonVariable.getContent())
                .price(CommonVariable.getPrice())
                .workDate(LocalDate.now())
                .address(CommonVariable.getAddress().getValue())
                .remark(CommonVariable.getRemark().getValue())
                .workType(CommonVariable.getWorkType().getName())
                .workStatus(CommonVariable.getWorkStatus().getName())
                .worker(null)
                .company(null)
                .paymentStatus(CommonVariable.getPaymentStatus().getName())
                .expenditureStatus(CommonVariable.getExpenditureStatus().getName())
                .taxStatus(CommonVariable.getTaxStatus().getName())
                .build();

        // when
        Work work = WorkMapper.INSTANCE.workResponseDtoToWork(workResponseDto);

        // then
        Assertions.assertThat(work.getPrice()).isEqualTo(workResponseDto.getPrice());
        Assertions.assertThat(work.getContent()).isEqualTo(workResponseDto.getContent());
        Assertions.assertThat(work.getAddress().getValue()).isEqualTo(workResponseDto.getAddress());
        Assertions.assertThat(work.getRemark().getValue()).isEqualTo(workResponseDto.getRemark());
        Assertions.assertThat(work.getWorkType().getName()).isEqualTo(workResponseDto.getWorkType());
        Assertions.assertThat(work.getWorkStatus().getName()).isEqualTo(workResponseDto.getWorkStatus());
        Assertions.assertThat(work.getPaymentStatus().getName()).isEqualTo(workResponseDto.getPaymentStatus());
        Assertions.assertThat(work.getExpenditureStatus().getName()).isEqualTo(workResponseDto.getExpenditureStatus());
        Assertions.assertThat(work.getCompany().getName()).isEqualTo(workResponseDto.getCompany());
        Assertions.assertThat(work.getWorker().getName()).isEqualTo(workResponseDto.getWorker());
    }
}