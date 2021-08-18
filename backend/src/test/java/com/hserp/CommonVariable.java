package com.hserp;

import com.hserp.entity.Address;
import com.hserp.entity.Email;
import com.hserp.entity.Phone;
import com.hserp.entity.Remark;
import com.hserp.entity.company.Company;
import com.hserp.entity.person.Person;
import com.hserp.entity.work.ExpenditureStatus;
import com.hserp.entity.work.PaymentStatus;
import com.hserp.entity.work.TaxStatus;
import com.hserp.entity.work.Work;
import com.hserp.entity.work.WorkStatus;
import com.hserp.entity.work.WorkType;

import java.time.LocalDate;

public class CommonVariable {
    public static String getName() {
        return "홍길동";
    }

    public static String getCompanyName() {
        return "HS-ERP";
    }

    public static String getContent() {
        return "content 영역 입니다.";
    }

    public static Integer getPrice() {
        return 10000;
    }

    public static Character getCommunicator() {
        return 'N';
    }

    public static Phone getPhone() {
        return new Phone("010-1234-5678");
    }

    public static Address getAddress() {
        return new Address("대왕판교로 660 유스페이스 A동 5층");
    }

    public static Email getEmail() {
        return new Email("hjjae2@gmail.com");
    }

    public static Remark getRemark() {
        return new Remark("비고");
    }

    public static WorkType getWorkType() {
        return new WorkType(null, "일반");
    }

    public static WorkStatus getWorkStatus() {
        return new WorkStatus(null, "미완료");
    }

    public static PaymentStatus getPaymentStatus() {
        return new PaymentStatus(null, "미완료");
    }

    public static ExpenditureStatus getExpenditureStatus() {
        return new ExpenditureStatus(null, "미완료");
    }

    public static TaxStatus getTaxStatus() {
        return new TaxStatus(null, "발급미완료");
    }

    public static Person getPerson() {
        return new Person(null, getName(), getPhone(), getEmail(), getAddress(), getRemark(), null);
    }

    public static Person getPerson(String name) {
        return new Person(null, name, getPhone(), getEmail(), getAddress(), getRemark(), null);
    }

    public static Person getPerson(Company company) {
        return new Person(null, getName(), getPhone(), getEmail(), getAddress(), getRemark(), company);
    }

    public static Person getPerson(String name, Company company) {
        return new Person(null, name, getPhone(), getEmail(), getAddress(), getRemark(), company);
    }

    public static Company getCompany() {
        return new Company(null, getCompanyName(), getPhone(), getEmail(), getAddress(), getRemark(), null);
    }

    public static Company getCompany(String name) {
        return new Company(null, name, getPhone(), getEmail(), getAddress(), getRemark(), null);
    }

    public static Company getCompany(Person person) {
        return new Company(null, "HS-ERP", getPhone(), getEmail(), getAddress(), getRemark(), person);
    }

    public static Company getCompany(String name, Person person) {
        return new Company(null, name, getPhone(), getEmail(), getAddress(), getRemark(), person);
    }

    public static Work getWork() {
        return Work.builder()
                .id(null)
                .content(getContent())
                .communicator(getCommunicator())
                .price(getPrice())
                .address(getAddress())
                .remark(getRemark())
                .worker(null)
                .company(null)
                .customer(null)
                .dispatcher(null)
                .workType(getWorkType())
                .workDate(LocalDate.now())
                .paymentStatus(getPaymentStatus())
                .expenditureStatus(getExpenditureStatus())
                .taxStatus(getTaxStatus())
                .build();
    }

    public static Work getWork(Person worker) {
        return Work.builder()
                .id(null)
                .content(getContent())
                .communicator(getCommunicator())
                .price(getPrice())
                .address(getAddress())
                .remark(getRemark())
                .worker(worker)
                .company(null)
                .customer(null)
                .dispatcher(null)
                .workType(getWorkType())
                .workDate(LocalDate.now())
                .paymentStatus(getPaymentStatus())
                .expenditureStatus(getExpenditureStatus())
                .taxStatus(getTaxStatus())
                .build();
    }

    public static Work getWork(Company company) {
        return Work.builder()
                .id(null)
                .content(getContent())
                .communicator(getCommunicator())
                .price(getPrice())
                .address(getAddress())
                .remark(getRemark())
                .worker(null)
                .company(company)
                .customer(null)
                .dispatcher(null)
                .workType(getWorkType())
                .workDate(LocalDate.now())
                .paymentStatus(getPaymentStatus())
                .expenditureStatus(getExpenditureStatus())
                .taxStatus(getTaxStatus())
                .build();
    }

    public static Work getWork(Person worker, Company company, Person dispatcher, Company customer) {
        return Work.builder()
                .id(null)
                .content(getContent())
                .communicator(getCommunicator())
                .price(getPrice())
                .address(getAddress())
                .remark(getRemark())
                .worker(worker)
                .company(company)
                .customer(customer)
                .dispatcher(dispatcher)
                .workType(getWorkType())
                .workDate(LocalDate.now())
                .paymentStatus(getPaymentStatus())
                .expenditureStatus(getExpenditureStatus())
                .taxStatus(getTaxStatus())
                .build();
    }
}
