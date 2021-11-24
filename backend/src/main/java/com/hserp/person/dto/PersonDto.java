package com.hserp.person.dto;

import com.hserp.company.domain.Company;
import com.hserp.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

public class PersonDto {
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
            private String company;

            public Person toEntity(Company company) {
                return Person.builder()
                        .name(name)
                        .phone(phone)
                        .email(email)
                        .address(address)
                        .remark(remark)
                        .company(company)
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
            private String company;
            private LocalDateTime createdDate;
            private LocalDateTime updatedDate;

            public static ResponseDto of(Person person) {
                String company = (person.getCompany() == null) ? null : person.getCompany().getName();

                return ResponseDto.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .phone(person.getPhone())
                        .email(person.getEmail())
                        .address(person.getAddress())
                        .remark(person.getRemark())
                        .company(company)
                        .createdDate(person.getCreatedDate())
                        .updatedDate(person.getUpdatedDate())
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
            private String company;
            private LocalDateTime createdDate;
            private LocalDateTime updatedDate;

            public static ResponseDto of(Person person) {
                String company = (person.getCompany() == null) ? null : person.getCompany().getName();
                return ResponseDto.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .phone(person.getPhone())
                        .email(person.getEmail())
                        .address(person.getAddress())
                        .remark(person.getRemark())
                        .company(company)
                        .createdDate(person.getCreatedDate())
                        .updatedDate(person.getUpdatedDate())
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
            private String company;

            public Person toEntity(Company company) {
                return Person.builder()
                        .name(name)
                        .phone(phone)
                        .email(email)
                        .address(address)
                        .remark(remark)
                        .company(company)
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
            private String company;
            private LocalDateTime createdDate;
            private LocalDateTime updatedDate;

            public static ResponseDto of(Person person) {
                String company = (person.getCompany() == null) ? null : person.getCompany().getName();
                return ResponseDto.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .phone(person.getPhone())
                        .email(person.getEmail())
                        .address(person.getAddress())
                        .remark(person.getRemark())
                        .company(company)
                        .createdDate(person.getCreatedDate())
                        .updatedDate(person.getUpdatedDate())
                        .build();
            }
        }
    }
}
