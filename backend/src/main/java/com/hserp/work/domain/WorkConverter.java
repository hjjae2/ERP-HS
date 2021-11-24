package com.hserp.work.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public class WorkConverter {

    @Converter
    public static class WorkTypeConverter implements AttributeConverter<Work.WorkType, String> {
        @Override
        public String convertToDatabaseColumn(Work.WorkType attribute) {
            return attribute.value();
        }

        @Override
        public Work.WorkType convertToEntityAttribute(String dbData) {
            return Work.WorkType.getByValue(dbData);
        }
    }

    @Converter
    public static class PaymentStatusConverter implements AttributeConverter<Work.PaymentStatus, String> {
        @Override
        public String convertToDatabaseColumn(Work.PaymentStatus attribute) {
            return attribute.value();
        }

        @Override
        public Work.PaymentStatus convertToEntityAttribute(String dbData) {
            return Work.PaymentStatus.getByValue(dbData);
        }
    }

    @Converter
    public static class ExpenditureStatusConverter implements AttributeConverter<Work.ExpenditureStatus, String> {
        @Override
        public String convertToDatabaseColumn(Work.ExpenditureStatus attribute) {
            return attribute.value();
        }

        @Override
        public Work.ExpenditureStatus convertToEntityAttribute(String dbData) {
            return Work.ExpenditureStatus.getByValue(dbData);
        }
    }

    @Converter
    public static class TaxStatusConverter implements AttributeConverter<Work.TaxStatus, String> {
        @Override
        public String convertToDatabaseColumn(Work.TaxStatus attribute) {
            return attribute.value();
        }

        @Override
        public Work.TaxStatus convertToEntityAttribute(String dbData) {
            return Work.TaxStatus.getByValue(dbData);
        }
    }
}
