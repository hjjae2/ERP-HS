package com.hserp.mapper.work;

import com.hserp.CommonVariable;
import com.hserp.work.dto.WorkRequestDto;
import com.hserp.work.dto.WorkResponseDto;
import com.hserp.company.domain.Company;
import com.hserp.person.domain.Person;
import com.hserp.work.domain.Work;
import com.hserp.work.WorkMapper;
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
        Assertions.assertThat(workRequestDto.getCommunicator()).isEqualTo(work.getCommunicator());
        Assertions.assertThat(workRequestDto.getAddress()).isEqualTo(work.getAddress().getValue());
        Assertions.assertThat(workRequestDto.getRemark()).isEqualTo(work.getRemark().getValue());
        Assertions.assertThat(workRequestDto.getWorkType()).isEqualTo(work.getWorkType().getName());
        Assertions.assertThat(workRequestDto.getPaymentStatus()).isEqualTo(work.getPaymentStatus().getName());
        Assertions.assertThat(workRequestDto.getExpenditureStatus()).isEqualTo(work.getExpenditureStatus().getName());
        Assertions.assertThat(workRequestDto.getCompany()).isNull();
        Assertions.assertThat(workRequestDto.getCustomer()).isNull();
        Assertions.assertThat(workRequestDto.getDispatcher()).isNull();
        Assertions.assertThat(workRequestDto.getWorker()).isNull();
    }

    @Test
    void workToWorkRequestDto_2() {
        // given
        Person worker = CommonVariable.getPerson();
        Person dispatcher = CommonVariable.getPerson();
        Company company = CommonVariable.getCompany();
        Company customer = CommonVariable.getCompany();
        Work work = CommonVariable.getWork(worker, company, dispatcher, customer);

        // when
        WorkRequestDto workRequestDto = WorkMapper.INSTANCE.workToWorkRequestDto(work);

        // then
        Assertions.assertThat(workRequestDto.getPrice()).isEqualTo(work.getPrice());
        Assertions.assertThat(workRequestDto.getContent()).isEqualTo(work.getContent());
        Assertions.assertThat(workRequestDto.getCommunicator()).isEqualTo(work.getCommunicator());
        Assertions.assertThat(workRequestDto.getAddress()).isEqualTo(work.getAddress().getValue());
        Assertions.assertThat(workRequestDto.getRemark()).isEqualTo(work.getRemark().getValue());
        Assertions.assertThat(workRequestDto.getWorkType()).isEqualTo(work.getWorkType().getName());
        Assertions.assertThat(workRequestDto.getPaymentStatus()).isEqualTo(work.getPaymentStatus().getName());
        Assertions.assertThat(workRequestDto.getExpenditureStatus()).isEqualTo(work.getExpenditureStatus().getName());
        Assertions.assertThat(workRequestDto.getCompany()).isEqualTo(work.getCompany().getName());
        Assertions.assertThat(workRequestDto.getCustomer()).isEqualTo(work.getCustomer().getName());
        Assertions.assertThat(workRequestDto.getDispatcher()).isEqualTo(work.getDispatcher().getName());
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
        Assertions.assertThat(workResponseDto.getCommunicator()).isEqualTo(work.getCommunicator());
        Assertions.assertThat(workResponseDto.getAddress()).isEqualTo(work.getAddress().getValue());
        Assertions.assertThat(workResponseDto.getRemark()).isEqualTo(work.getRemark().getValue());
        Assertions.assertThat(workResponseDto.getWorkType()).isEqualTo(work.getWorkType().getName());
        Assertions.assertThat(workResponseDto.getPaymentStatus()).isEqualTo(work.getPaymentStatus().getName());
        Assertions.assertThat(workResponseDto.getExpenditureStatus()).isEqualTo(work.getExpenditureStatus().getName());
        Assertions.assertThat(workResponseDto.getCompany()).isNull();
        Assertions.assertThat(workResponseDto.getCustomer()).isNull();
        Assertions.assertThat(workResponseDto.getDispatcher()).isNull();
        Assertions.assertThat(workResponseDto.getWorker()).isNull();
    }

    @Test
    void workToWorkResponseDto_2() {
        // given
        Person worker = CommonVariable.getPerson();
        Person dispatcher = CommonVariable.getPerson();
        Company company = CommonVariable.getCompany();
        Company customer = CommonVariable.getCompany();
        Work work = CommonVariable.getWork(worker, company, dispatcher, customer);

        // when
        WorkResponseDto workResponseDto = WorkMapper.INSTANCE.workToWorkResponseDto(work);

        // then
        Assertions.assertThat(workResponseDto.getPrice()).isEqualTo(work.getPrice());
        Assertions.assertThat(workResponseDto.getContent()).isEqualTo(work.getContent());
        Assertions.assertThat(workResponseDto.getCommunicator()).isEqualTo(work.getCommunicator());
        Assertions.assertThat(workResponseDto.getAddress()).isEqualTo(work.getAddress().getValue());
        Assertions.assertThat(workResponseDto.getRemark()).isEqualTo(work.getRemark().getValue());
        Assertions.assertThat(workResponseDto.getWorkType()).isEqualTo(work.getWorkType().getName());
        Assertions.assertThat(workResponseDto.getPaymentStatus()).isEqualTo(work.getPaymentStatus().getName());
        Assertions.assertThat(workResponseDto.getExpenditureStatus()).isEqualTo(work.getExpenditureStatus().getName());
        Assertions.assertThat(workResponseDto.getCompany()).isEqualTo(work.getCompany().getName());
        Assertions.assertThat(workResponseDto.getCustomer()).isEqualTo(work.getCustomer().getName());
        Assertions.assertThat(workResponseDto.getDispatcher()).isEqualTo(work.getDispatcher().getName());
        Assertions.assertThat(workResponseDto.getWorker()).isEqualTo(work.getWorker().getName());
    }

    @Test
    void workRequestDtoToWork() {
        // given
        WorkRequestDto workRequestDto = WorkRequestDto.builder()
                .content(CommonVariable.getContent())
                .price(CommonVariable.getPrice())
                .communicator(CommonVariable.getCommunicator())
                .workDate(LocalDate.now())
                .address(CommonVariable.getAddress().getValue())
                .remark(CommonVariable.getRemark().getValue())
                .workType(CommonVariable.getWorkType().getName())
                .worker(null)
                .company(null)
                .customer(null)
                .dispatcher(null)
                .paymentStatus(CommonVariable.getPaymentStatus().getName())
                .expenditureStatus(CommonVariable.getExpenditureStatus().getName())
                .taxStatus(CommonVariable.getTaxStatus().getName())
                .build();

        // when
        Work work = WorkMapper.INSTANCE.workRequestDtoToWork(workRequestDto);

        // then
        Assertions.assertThat(work.getPrice()).isEqualTo(workRequestDto.getPrice());
        Assertions.assertThat(work.getContent()).isEqualTo(workRequestDto.getContent());
        Assertions.assertThat(work.getCommunicator()).isEqualTo(workRequestDto.getCommunicator());
        Assertions.assertThat(work.getAddress().getValue()).isEqualTo(workRequestDto.getAddress());
        Assertions.assertThat(work.getRemark().getValue()).isEqualTo(workRequestDto.getRemark());

        Assertions.assertThat(work.getWorker()).isNull();
        Assertions.assertThat(work.getCompany()).isNull();
        Assertions.assertThat(work.getCustomer()).isNull();
        Assertions.assertThat(work.getDispatcher()).isNull();
        Assertions.assertThat(work.getWorkType()).isNull();
        Assertions.assertThat(work.getPaymentStatus()).isNull();
        Assertions.assertThat(work.getExpenditureStatus()).isNull();
        Assertions.assertThat(work.getTaxStatus()).isNull();
    }

    @Test
    void workResponseDtoToWork() {
        // given
        WorkResponseDto workResponseDto = WorkResponseDto.builder()
                .content(CommonVariable.getContent())
                .price(CommonVariable.getPrice())
                .communicator(CommonVariable.getCommunicator())
                .workDate(LocalDate.now())
                .address(CommonVariable.getAddress().getValue())
                .remark(CommonVariable.getRemark().getValue())
                .workType(CommonVariable.getWorkType().getName())
                .worker(null)
                .company(null)
                .customer(null)
                .dispatcher(null)
                .paymentStatus(CommonVariable.getPaymentStatus().getName())
                .expenditureStatus(CommonVariable.getExpenditureStatus().getName())
                .taxStatus(CommonVariable.getTaxStatus().getName())
                .build();

        // when
        Work work = WorkMapper.INSTANCE.workResponseDtoToWork(workResponseDto);

        // then
        Assertions.assertThat(work.getPrice()).isEqualTo(workResponseDto.getPrice());
        Assertions.assertThat(work.getContent()).isEqualTo(workResponseDto.getContent());
        Assertions.assertThat(work.getCommunicator()).isEqualTo(workResponseDto.getCommunicator());
        Assertions.assertThat(work.getAddress().getValue()).isEqualTo(workResponseDto.getAddress());
        Assertions.assertThat(work.getRemark().getValue()).isEqualTo(workResponseDto.getRemark());

        Assertions.assertThat(work.getWorker()).isNull();
        Assertions.assertThat(work.getCompany()).isNull();
        Assertions.assertThat(work.getCustomer()).isNull();
        Assertions.assertThat(work.getDispatcher()).isNull();
        Assertions.assertThat(work.getWorkType()).isNull();
        Assertions.assertThat(work.getPaymentStatus()).isNull();
        Assertions.assertThat(work.getExpenditureStatus()).isNull();
        Assertions.assertThat(work.getTaxStatus()).isNull();
    }
}