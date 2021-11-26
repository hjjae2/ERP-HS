package com.hserp;

import com.hserp.company.domain.Company;
import com.hserp.person.domain.Person;
import com.hserp.work.domain.Work;

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

    public static String getPhone() {
        return "010-1234-5678";
    }

    public static String getAddress() {
        return "대왕판교로 660 유스페이스 A동 5층";
    }

    public static String getEmail() {
        return "hjjae2@gmail.com";
    }

    public static String getRemark() {
        return "비고";
    }

    public static Work.WorkType getWorkType() {
        return Work.WorkType.NORMAL;
    }

    public static Work.PaymentStatus getPaymentStatus() {
        return Work.PaymentStatus.COMPLETION;
    }

    public static Work.ExpenditureStatus getExpenditureStatus() {
        return Work.ExpenditureStatus.COMPLETION;
    }

    public static Work.TaxStatus getTaxStatus() {
        return Work.TaxStatus.COMPLETION;
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
