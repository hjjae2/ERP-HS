package com.hserp.work.dto;

import com.hserp.work.domain.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

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

            public Work toEntity() {
                return Work.builder()
                        .workDate(workDate)
                        .address(address)
                        .content(content)
                        .remark(remark)
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
                return ResponseDto.builder()
                        .id(work.getId())
                        .workDate(work.getWorkDate())
                        .workType(work.getWorkType().getName())
                        .worker(work.getWorker().getName())
                        .company(work.getCompany().getName())
                        .customer(work.getCustomer().getName())
                        .dispatcher(work.getDispatcher().getName())
                        .address(work.getAddress())
                        .content(work.getContent())
                        .remark(work.getRemark())
                        .paymentStatus(work.getPaymentStatus().getName())
                        .expenditureStatus(work.getExpenditureStatus().getName())
                        .taxStatus(work.getTaxStatus().getName())
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
                return ResponseDto.builder()
                        .id(work.getId())
                        .workDate(work.getWorkDate())
                        .workType(work.getWorkType().getName())
                        .worker(work.getWorker().getName())
                        .company(work.getCompany().getName())
                        .customer(work.getCustomer().getName())
                        .dispatcher(work.getDispatcher().getName())
                        .address(work.getAddress())
                        .content(work.getContent())
                        .remark(work.getRemark())
                        .paymentStatus(work.getPaymentStatus().getName())
                        .expenditureStatus(work.getExpenditureStatus().getName())
                        .taxStatus(work.getTaxStatus().getName())
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

            public Work toEntity() {
                return Work.builder()
                        .workDate(workDate)
                        .address(address)
                        .content(content)
                        .remark(remark)
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
                return ResponseDto.builder()
                        .id(work.getId())
                        .workDate(work.getWorkDate())
                        .workType(work.getWorkType().getName())
                        .worker(work.getWorker().getName())
                        .company(work.getCompany().getName())
                        .customer(work.getCustomer().getName())
                        .dispatcher(work.getDispatcher().getName())
                        .address(work.getAddress())
                        .content(work.getContent())
                        .remark(work.getRemark())
                        .paymentStatus(work.getPaymentStatus().getName())
                        .expenditureStatus(work.getExpenditureStatus().getName())
                        .taxStatus(work.getTaxStatus().getName())
                        .price(work.getPrice())
                        .communicator(work.getCommunicator())
                        .build();
            }
        }
    }
}
