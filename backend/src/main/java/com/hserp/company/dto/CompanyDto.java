package com.hserp.company.dto;

import com.hserp.company.domain.Company;
import com.hserp.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

public class CompanyDto {
    public static class Create {
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class RequestDto {
            private String name;
            private String phone;
            private String email;
            private String address;
            private String remark;
            private String manager;

            public Company toEntity(Person manager) {
                return Company.builder()
                        .name(name)
                        .phone(phone)
                        .email(email)
                        .address(address)
                        .remark(remark)
                        .manager(manager)
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
            private String name;
            private String phone;
            private String email;
            private String address;
            private String remark;
            private String manager;
            private LocalDateTime createdDate;
            private LocalDateTime updatedDate;

            public static ResponseDto of(Company company) {
                String manager = (company.getManager() == null) ? null : company.getManager().getName();
                return ResponseDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .phone(company.getPhone())
                        .email(company.getEmail())
                        .address(company.getAddress())
                        .remark(company.getRemark())
                        .manager(manager)
                        .createdDate(company.getCreatedDate())
                        .updatedDate(company.getUpdatedDate())
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
            private String name;
            private String phone;
            private String email;
            private String address;
            private String remark;
            private String manager;
            private LocalDateTime createdDate;
            private LocalDateTime updatedDate;

            public static ResponseDto of(Company company) {
                String manager = (company.getManager() == null) ? null : company.getManager().getName();
                return ResponseDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .phone(company.getPhone())
                        .email(company.getEmail())
                        .address(company.getAddress())
                        .remark(company.getRemark())
                        .manager(manager)
                        .createdDate(company.getCreatedDate())
                        .updatedDate(company.getUpdatedDate())
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
            private String name;
            private String phone;
            private String email;
            private String address;
            private String remark;
            private String manager;

            public Company toEntity(Person manager) {
                return Company.builder()
                        .name(name)
                        .phone(phone)
                        .email(email)
                        .address(address)
                        .remark(remark)
                        .manager(manager)
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
            private String name;
            private String phone;
            private String email;
            private String address;
            private String remark;
            private String manager;
            private LocalDateTime createdDate;
            private LocalDateTime updatedDate;

            public static ResponseDto of(Company company) {
                String manager = (company.getManager() == null) ? null : company.getManager().getName();
                return ResponseDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .phone(company.getPhone())
                        .email(company.getEmail())
                        .address(company.getAddress())
                        .remark(company.getRemark())
                        .manager(manager)
                        .createdDate(company.getCreatedDate())
                        .updatedDate(company.getUpdatedDate())
                        .build();
            }
        }
    }
}
