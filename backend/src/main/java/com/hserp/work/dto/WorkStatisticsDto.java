package com.hserp.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WorkStatisticsDto {

    public static class Month {
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto {
            int totalSummationOfPrices;
            int totalNumberOfWorks;
            List<WorkDto.Read.ResponseDto> works;

            public static Month.ResponseDto of(List<WorkDto.Read.ResponseDto> works, int totalSummationOfPrices) {
                return ResponseDto.builder()
                        .totalSummationOfPrices(totalSummationOfPrices)
                        .totalNumberOfWorks(works.size())
                        .works(works)
                        .build();
            }
        }
    }

    public static class Monthly {
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class RequestDto {
            List<String> months;

            public LocalDate stringToLocalDate(String monthAsString) {
                return LocalDate.parse(String.format("%s-%s", monthAsString, "01"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto {
            String month;
            int totalSummationOfPrices;
            int totalNumberOfWorks;

            public static Monthly.ResponseDto of(LocalDate month, int totalSummationOfPrices, int totalSummationOfWorks) {
                return ResponseDto.builder()
                        .month(month.format(DateTimeFormatter.ofPattern("yyyy-MM")))
                        .totalSummationOfPrices(totalSummationOfPrices)
                        .totalNumberOfWorks(totalSummationOfWorks)
                        .build();
            }
        }
    }
}
