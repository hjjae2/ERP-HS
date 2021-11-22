package com.hserp.work;

import com.hserp.company.domain.Company;
import com.hserp.global.domain.Address;
import com.hserp.global.domain.Remark;
import com.hserp.person.domain.Person;
import com.hserp.work.domain.ExpenditureStatus;
import com.hserp.work.domain.PaymentStatus;
import com.hserp.work.domain.TaxStatus;
import com.hserp.work.domain.Work;
import com.hserp.work.domain.WorkType;
import com.hserp.work.dto.WorkRequestDto;
import com.hserp.work.dto.WorkResponseDto;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-23T00:13:45+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (AdoptOpenJDK)"
)
public class WorkMapperImpl implements WorkMapper {

    @Override
    public WorkRequestDto workToWorkRequestDto(Work work) {
        if ( work == null ) {
            return null;
        }

        String address = null;
        String remark = null;
        String worker = null;
        String company = null;
        String customer = null;
        String dispatcher = null;
        String workType = null;
        String paymentStatus = null;
        String expenditureStatus = null;
        String taxStatus = null;
        LocalDate workDate = null;
        String content = null;
        Integer price = null;
        Character communicator = null;

        String value = workAddressValue( work );
        if ( value != null ) {
            address = value;
        }
        String value1 = workRemarkValue( work );
        if ( value1 != null ) {
            remark = value1;
        }
        String name = workWorkerName( work );
        if ( name != null ) {
            worker = name;
        }
        String name1 = workCompanyName( work );
        if ( name1 != null ) {
            company = name1;
        }
        String name2 = workCustomerName( work );
        if ( name2 != null ) {
            customer = name2;
        }
        String name3 = workDispatcherName( work );
        if ( name3 != null ) {
            dispatcher = name3;
        }
        String name4 = workWorkTypeName( work );
        if ( name4 != null ) {
            workType = name4;
        }
        String name5 = workPaymentStatusName( work );
        if ( name5 != null ) {
            paymentStatus = name5;
        }
        String name6 = workExpenditureStatusName( work );
        if ( name6 != null ) {
            expenditureStatus = name6;
        }
        String name7 = workTaxStatusName( work );
        if ( name7 != null ) {
            taxStatus = name7;
        }
        if ( work.getWorkDate() != null ) {
            workDate = work.getWorkDate();
        }
        if ( work.getContent() != null ) {
            content = work.getContent();
        }
        if ( work.getPrice() != null ) {
            price = work.getPrice();
        }
        if ( work.getCommunicator() != null ) {
            communicator = work.getCommunicator();
        }

        WorkRequestDto workRequestDto = new WorkRequestDto( workDate, workType, worker, company, customer, dispatcher, address, content, remark, paymentStatus, expenditureStatus, taxStatus, price, communicator );

        return workRequestDto;
    }

    @Override
    public WorkResponseDto workToWorkResponseDto(Work work) {
        if ( work == null ) {
            return null;
        }

        String address = null;
        String remark = null;
        String worker = null;
        String company = null;
        String customer = null;
        String dispatcher = null;
        String workType = null;
        String paymentStatus = null;
        String expenditureStatus = null;
        String taxStatus = null;
        Integer id = null;
        LocalDate workDate = null;
        String content = null;
        Integer price = null;
        Character communicator = null;

        String value = workAddressValue( work );
        if ( value != null ) {
            address = value;
        }
        String value1 = workRemarkValue( work );
        if ( value1 != null ) {
            remark = value1;
        }
        String name = workWorkerName( work );
        if ( name != null ) {
            worker = name;
        }
        String name1 = workCompanyName( work );
        if ( name1 != null ) {
            company = name1;
        }
        String name2 = workCustomerName( work );
        if ( name2 != null ) {
            customer = name2;
        }
        String name3 = workDispatcherName( work );
        if ( name3 != null ) {
            dispatcher = name3;
        }
        String name4 = workWorkTypeName( work );
        if ( name4 != null ) {
            workType = name4;
        }
        String name5 = workPaymentStatusName( work );
        if ( name5 != null ) {
            paymentStatus = name5;
        }
        String name6 = workExpenditureStatusName( work );
        if ( name6 != null ) {
            expenditureStatus = name6;
        }
        String name7 = workTaxStatusName( work );
        if ( name7 != null ) {
            taxStatus = name7;
        }
        if ( work.getId() != null ) {
            id = work.getId();
        }
        if ( work.getWorkDate() != null ) {
            workDate = work.getWorkDate();
        }
        if ( work.getContent() != null ) {
            content = work.getContent();
        }
        if ( work.getPrice() != null ) {
            price = work.getPrice();
        }
        if ( work.getCommunicator() != null ) {
            communicator = work.getCommunicator();
        }

        WorkResponseDto workResponseDto = new WorkResponseDto( id, workDate, workType, worker, company, customer, dispatcher, address, content, remark, paymentStatus, expenditureStatus, taxStatus, price, communicator );

        return workResponseDto;
    }

    @Override
    public Work workRequestDtoToWork(WorkRequestDto workRequestDto) {
        if ( workRequestDto == null ) {
            return null;
        }

        Address address = null;
        Remark remark = null;
        Integer price = null;
        Character communicator = null;
        String content = null;
        LocalDate workDate = null;

        address = workRequestDtoToAddress( workRequestDto );
        remark = workRequestDtoToRemark( workRequestDto );
        if ( workRequestDto.getPrice() != null ) {
            price = workRequestDto.getPrice();
        }
        else {
            price = 0;
        }
        if ( workRequestDto.getCommunicator() != null ) {
            communicator = workRequestDto.getCommunicator();
        }
        else {
            communicator = 'N';
        }
        if ( workRequestDto.getContent() != null ) {
            content = workRequestDto.getContent();
        }
        if ( workRequestDto.getWorkDate() != null ) {
            workDate = workRequestDto.getWorkDate();
        }

        Person worker = null;
        Company company = null;
        Company customer = null;
        Person dispatcher = null;
        WorkType workType = null;
        PaymentStatus paymentStatus = null;
        ExpenditureStatus expenditureStatus = null;
        TaxStatus taxStatus = null;
        Integer id = null;

        Work work = new Work( id, content, price, communicator, address, remark, workDate, workType, worker, dispatcher, company, customer, paymentStatus, expenditureStatus, taxStatus );

        return work;
    }

    @Override
    public Work workResponseDtoToWork(WorkResponseDto workResponseDto) {
        if ( workResponseDto == null ) {
            return null;
        }

        Address address = null;
        Remark remark = null;
        Integer price = null;
        Character communicator = null;
        Integer id = null;
        String content = null;
        LocalDate workDate = null;

        address = workResponseDtoToAddress( workResponseDto );
        remark = workResponseDtoToRemark( workResponseDto );
        if ( workResponseDto.getPrice() != null ) {
            price = workResponseDto.getPrice();
        }
        else {
            price = 0;
        }
        if ( workResponseDto.getCommunicator() != null ) {
            communicator = workResponseDto.getCommunicator();
        }
        else {
            communicator = 'N';
        }
        if ( workResponseDto.getId() != null ) {
            id = workResponseDto.getId();
        }
        if ( workResponseDto.getContent() != null ) {
            content = workResponseDto.getContent();
        }
        if ( workResponseDto.getWorkDate() != null ) {
            workDate = workResponseDto.getWorkDate();
        }

        Person worker = null;
        Company company = null;
        Company customer = null;
        Person dispatcher = null;
        WorkType workType = null;
        PaymentStatus paymentStatus = null;
        ExpenditureStatus expenditureStatus = null;
        TaxStatus taxStatus = null;

        Work work = new Work( id, content, price, communicator, address, remark, workDate, workType, worker, dispatcher, company, customer, paymentStatus, expenditureStatus, taxStatus );

        return work;
    }

    private String workAddressValue(Work work) {
        if ( work == null ) {
            return null;
        }
        Address address = work.getAddress();
        if ( address == null ) {
            return null;
        }
        String value = address.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String workRemarkValue(Work work) {
        if ( work == null ) {
            return null;
        }
        Remark remark = work.getRemark();
        if ( remark == null ) {
            return null;
        }
        String value = remark.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String workWorkerName(Work work) {
        if ( work == null ) {
            return null;
        }
        Person worker = work.getWorker();
        if ( worker == null ) {
            return null;
        }
        String name = worker.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String workCompanyName(Work work) {
        if ( work == null ) {
            return null;
        }
        Company company = work.getCompany();
        if ( company == null ) {
            return null;
        }
        String name = company.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String workCustomerName(Work work) {
        if ( work == null ) {
            return null;
        }
        Company customer = work.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String name = customer.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String workDispatcherName(Work work) {
        if ( work == null ) {
            return null;
        }
        Person dispatcher = work.getDispatcher();
        if ( dispatcher == null ) {
            return null;
        }
        String name = dispatcher.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String workWorkTypeName(Work work) {
        if ( work == null ) {
            return null;
        }
        WorkType workType = work.getWorkType();
        if ( workType == null ) {
            return null;
        }
        String name = workType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String workPaymentStatusName(Work work) {
        if ( work == null ) {
            return null;
        }
        PaymentStatus paymentStatus = work.getPaymentStatus();
        if ( paymentStatus == null ) {
            return null;
        }
        String name = paymentStatus.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String workExpenditureStatusName(Work work) {
        if ( work == null ) {
            return null;
        }
        ExpenditureStatus expenditureStatus = work.getExpenditureStatus();
        if ( expenditureStatus == null ) {
            return null;
        }
        String name = expenditureStatus.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String workTaxStatusName(Work work) {
        if ( work == null ) {
            return null;
        }
        TaxStatus taxStatus = work.getTaxStatus();
        if ( taxStatus == null ) {
            return null;
        }
        String name = taxStatus.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Address workRequestDtoToAddress(WorkRequestDto workRequestDto) {
        if ( workRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( workRequestDto.getAddress() != null ) {
            value = workRequestDto.getAddress();
        }

        Address address = new Address( value );

        return address;
    }

    protected Remark workRequestDtoToRemark(WorkRequestDto workRequestDto) {
        if ( workRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( workRequestDto.getRemark() != null ) {
            value = workRequestDto.getRemark();
        }

        Remark remark = new Remark( value );

        return remark;
    }

    protected Address workResponseDtoToAddress(WorkResponseDto workResponseDto) {
        if ( workResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( workResponseDto.getAddress() != null ) {
            value = workResponseDto.getAddress();
        }

        Address address = new Address( value );

        return address;
    }

    protected Remark workResponseDtoToRemark(WorkResponseDto workResponseDto) {
        if ( workResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( workResponseDto.getRemark() != null ) {
            value = workResponseDto.getRemark();
        }

        Remark remark = new Remark( value );

        return remark;
    }
}
