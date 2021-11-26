package com.hserp.work.dto;

import com.hserp.company.domain.Company;
import com.hserp.person.domain.Person;
import com.hserp.work.domain.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

public class WorkDto {

    public static class Create {

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class RequestDto {
            private LocalDate workDate;
            private String workType;
            private String worker;
            private String company;
            private String customer;
            private String dispatcher;
            private String address;
            private String content;
            private String remark;
            private String paymentStatus;
            private String expenditureStatus;
            private String taxStatus;
            private Integer price;
            private Character communicator;

            public Work toEntity(Person worker, Person dispatcher, Company company, Company customer) {
                Work.WorkType workType = Work.WorkType.getByValue(this.workType);
                Work.PaymentStatus paymentStatus = Work.PaymentStatus.getByValue(this.paymentStatus);
                Work.ExpenditureStatus expenditureStatus = Work.ExpenditureStatus.getByValue(this.expenditureStatus);
                Work.TaxStatus taxStatus = Work.TaxStatus.getByValue(this.taxStatus);

                return Work.builder()
                        .workDate(workDate)
                        .address(address)
                        .content(content)
                        .remark(remark)
                        .workType(workType)
                        .worker(worker)
                        .company(company)
                        .customer(customer)
                        .dispatcher(dispatcher)
                        .paymentStatus(paymentStatus)
                        .expenditureStatus(expenditureStatus)
                        .taxStatus(taxStatus)
                        .price(price)
                        .communicator(communicator)
                        .build();
            }
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto {
            private Integer id;
            private LocalDate workDate;
            private String workType;
            private String worker;
            private String company;
            private String customer;
            private String dispatcher;
            private String address;
            private String content;
            private String remark;
            private String paymentStatus;
            private String expenditureStatus;
            private String taxStatus;
            private Integer price;
            private Character communicator;

            public static ResponseDto of(Work work) {
                String worker = Objects.isNull(work.getWorker()) ? null : work.getWorker().getName();
                String company = Objects.isNull(work.getCompany()) ? null : work.getCompany().getName();
                String customer = Objects.isNull(work.getCustomer()) ? null : work.getCustomer().getName();
                String dispatcher = Objects.isNull(work.getDispatcher()) ? null : work.getDispatcher().getName();

                return ResponseDto.builder()
                        .id(work.getId())
                        .workDate(work.getWorkDate())
                        .workType(work.getWorkType().value())
                        .worker(worker)
                        .company(company)
                        .customer(customer)
                        .dispatcher(dispatcher)
                        .address(work.getAddress())
                        .content(work.getContent())
                        .remark(work.getRemark())
                        .paymentStatus(work.getPaymentStatus().value())
                        .expenditureStatus(work.getExpenditureStatus().value())
                        .taxStatus(work.getTaxStatus().value())
                        .price(work.getPrice())
                        .communicator(work.getCommunicator())
                        .build();
            }
        }
    }

    public static class Read {

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto {
            private Integer id;
            private LocalDate workDate;
            private String workType;
            private String worker;
            private String company;
            private String customer;
            private String dispatcher;
            private String address;
            private String content;
            private String remark;
            private String paymentStatus;
            private String expenditureStatus;
            private String taxStatus;
            private Integer price;
            private Character communicator;

            public static ResponseDto of(Work work) {
                String worker = Objects.isNull(work.getWorker()) ? null : work.getWorker().getName();
                String company = Objects.isNull(work.getCompany()) ? null : work.getCompany().getName();
                String customer = Objects.isNull(work.getCustomer()) ? null : work.getCustomer().getName();
                String dispatcher = Objects.isNull(work.getDispatcher()) ? null : work.getDispatcher().getName();

                return ResponseDto.builder()
                        .id(work.getId())
                        .workDate(work.getWorkDate())
                        .workType(work.getWorkType().value())
                        .worker(worker)
                        .company(company)
                        .customer(customer)
                        .dispatcher(dispatcher)
                        .address(work.getAddress())
                        .content(work.getContent())
                        .remark(work.getRemark())
                        .paymentStatus(work.getPaymentStatus().value())
                        .expenditureStatus(work.getExpenditureStatus().value())
                        .taxStatus(work.getTaxStatus().value())
                        .price(work.getPrice())
                        .communicator(work.getCommunicator())
                        .build();
            }
        }
    }

    public static class Update {

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class RequestDto {
            private Integer id;
            private LocalDate workDate;
            private String workType;
            private String worker;
            private String company;
            private String customer;
            private String dispatcher;
            private String address;
            private String content;
            private String remark;
            private String paymentStatus;
            private String expenditureStatus;
            private String taxStatus;
            private Integer price;
            private Character communicator;

            public Work toEntity(Person worker, Person dispatcher, Company company, Company customer) {
                Work.WorkType workType = Work.WorkType.getByValue(this.workType);
                Work.PaymentStatus paymentStatus = Work.PaymentStatus.getByValue(this.paymentStatus);
                Work.ExpenditureStatus expenditureStatus = Work.ExpenditureStatus.getByValue(this.expenditureStatus);
                Work.TaxStatus taxStatus = Work.TaxStatus.getByValue(this.taxStatus);

                return Work.builder()
                        .workDate(workDate)
                        .address(address)
                        .content(content)
                        .remark(remark)
                        .workType(workType)
                        .worker(worker)
                        .company(company)
                        .customer(customer)
                        .dispatcher(dispatcher)
                        .paymentStatus(paymentStatus)
                        .expenditureStatus(expenditureStatus)
                        .taxStatus(taxStatus)
                        .price(price)
                        .communicator(communicator)
                        .build();
            }
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto {
            private Integer id;
            private LocalDate workDate;
            private String workType;
            private String worker;
            private String company;
            private String customer;
            private String dispatcher;
            private String address;
            private String content;
            private String remark;
            private String paymentStatus;
            private String expenditureStatus;
            private String taxStatus;
            private Integer price;
            private Character communicator;

            public static ResponseDto of(Work work) {
                String worker = Objects.isNull(work.getWorker()) ? null : work.getWorker().getName();
                String company = Objects.isNull(work.getCompany()) ? null : work.getCompany().getName();
                String customer = Objects.isNull(work.getCustomer()) ? null : work.getCustomer().getName();
                String dispatcher = Objects.isNull(work.getDispatcher()) ? null : work.getDispatcher().getName();

                return ResponseDto.builder()
                        .id(work.getId())
                        .workDate(work.getWorkDate())
                        .workType(work.getWorkType().value())
                        .worker(worker)
                        .company(company)
                        .customer(customer)
                        .dispatcher(dispatcher)
                        .address(work.getAddress())
                        .content(work.getContent())
                        .remark(work.getRemark())
                        .paymentStatus(work.getPaymentStatus().value())
                        .expenditureStatus(work.getExpenditureStatus().value())
                        .taxStatus(work.getTaxStatus().value())
                        .price(work.getPrice())
                        .communicator(work.getCommunicator())
                        .build();
            }
        }
    }
}
