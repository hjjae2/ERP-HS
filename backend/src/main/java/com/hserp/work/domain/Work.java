package com.hserp.work.domain;

import com.hserp.company.domain.Company;
import com.hserp.global.domain.CommonTime;
import com.hserp.person.domain.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Work extends CommonTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = true)
    private String content;

    @Column(nullable = false, columnDefinition = "default = 'N'")
    private Integer price;

    @Column(name="is_communicator", nullable = false, columnDefinition = "default = 'N'")
    private Character communicator;

    @Column(length = 128, nullable = true)
    private String address;

    @Column(length = 255, nullable = true)
    private String remark;

    @Column(name="work_date", nullable = true)
    private LocalDate workDate;

    @Convert(converter = WorkConverter.WorkTypeConverter.class)
    @Column(name="work_type", nullable = false)
    private WorkType workType;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "worker_id", nullable = true)
    private Person worker;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "dispatcher_id", nullable = true)
    private Person dispatcher;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_id", nullable = true)
    private Company customer;

    @Convert(converter = WorkConverter.PaymentStatusConverter.class)
    @Column(name="payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Convert(converter = WorkConverter.ExpenditureStatusConverter.class)
    @Column(name="expenditure_status", nullable = false)
    private ExpenditureStatus expenditureStatus;

    @Convert(converter = WorkConverter.TaxStatusConverter.class)
    @Column(name="tax_status", nullable = false)
    private TaxStatus taxStatus;

    private void changeContent(String newContent) {
        this.content = newContent;
    }

    private void changePrice(Integer price) {
        this.price = price;
    }

    private void changeCommunicatorToYes() {
        this.communicator = 'Y';
    }

    private void changeCommunicatorToNo() {
        this.communicator = 'N';
    }

    private void changeWorkDate(LocalDate newWorkDate) {
        this.workDate = newWorkDate;
    }

    private void changeAddress(String newAddress) {
        this.address = newAddress;
    }

    private void changeRemark(String newRemark) {
        this.remark = newRemark;
    }

    private void changeCompany(Company newCompany) {
        this.company = newCompany;
    }

    private void changeCustomer(Company newCustomer) {
        this.customer = newCustomer;
    }

    private void changeWorker(Person newWorker) {
        this.worker = newWorker;
    }

    private void changeDispatcher(Person newDispatcher) {
        this.dispatcher = newDispatcher;
    }

    private void changeWorkType(WorkType workType) {
        this.workType = workType;
    }

    private void changePaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    private void changeExpenditureStatus(ExpenditureStatus expenditureStatus) {
        this.expenditureStatus = expenditureStatus;
    }

    private void changeTaxStatus(TaxStatus newTaxStatus) {
        if(newTaxStatus != null && !newTaxStatus.equals(this.taxStatus)) {
            this.taxStatus = newTaxStatus;
        }
    }

    public void changeTo(Work newWork) {
        this.changeContent(newWork.getContent());
        this.changePrice(newWork.getPrice());
        this.changeWorkDate(newWork.workDate);
        this.changeAddress(newWork.getAddress());
        this.changeRemark(newWork.getRemark());
        this.changeCompany(newWork.getCompany());
        this.changeCustomer(newWork.getCustomer());
        this.changeWorker(newWork.getWorker());
        this.changeDispatcher(newWork.getDispatcher());
        this.changeWorkType(newWork.getWorkType());
        this.changePaymentStatus(newWork.getPaymentStatus());
        this.changeExpenditureStatus(newWork.expenditureStatus);
        this.changeTaxStatus(newWork.getTaxStatus());

        if(newWork.getCommunicator() == 'Y') {
            this.changeCommunicatorToYes();
        } else {
            this.changeCommunicatorToNo();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Work target = (Work) obj;
        return Objects.equals(content, target.content)
                && Objects.equals(price, target.price)
                && Objects.equals(address, target.address)
                && Objects.equals(communicator, target.communicator)
                && Objects.equals(remark, target.remark)
                && Objects.equals(workDate, target.workDate)
                && Objects.equals(workType, target.workType)
                && Objects.equals(worker, target.worker)
                && Objects.equals(dispatcher, target.dispatcher)
                && Objects.equals(company, target.company)
                && Objects.equals(customer, target.customer)
                && Objects.equals(paymentStatus, target.paymentStatus)
                && Objects.equals(expenditureStatus, target.expenditureStatus)
                && Objects.equals(taxStatus, target.taxStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, price, address, communicator, remark, workDate, workType, worker, dispatcher, company, customer, paymentStatus, expenditureStatus, taxStatus);
    }

    public enum WorkType {
        NORMAL("일반"),
        EXPENDITURE("지출"),
        EXPENSE("비용");

        private String value;

        static final Map<String, WorkType> nameMap = new HashMap<>();
        static final Map<String, WorkType> valueMap = new HashMap<>();
        static {
            for(WorkType workType : WorkType.values()) {
                nameMap.put(workType.name(), workType);
                valueMap.put(workType.value, workType);
            }
        }

        WorkType(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

        public static WorkType getByName(String name) {
            if(!nameMap.containsKey(name)) {
                return null;
            }
            return nameMap.get(name);
        }

        public static WorkType getByValue(String value) {
            if(!valueMap.containsKey(value)) {
                return null;
            }
            return valueMap.get(value);
        }
    }

    public enum PaymentStatus {
        COMPLETION("완료"),
        PARTIAL_COMPLETION("부분완료"),
        INCOMPLETION("미완료");

        private String value;

        static final Map<String, PaymentStatus> nameMap = new HashMap<>();
        static final Map<String, PaymentStatus> valueMap = new HashMap<>();
        static {
            for(PaymentStatus paymentStatus : PaymentStatus.values()) {
                nameMap.put(paymentStatus.name(), paymentStatus);
                valueMap.put(paymentStatus.value, paymentStatus);
            }
        }

        PaymentStatus(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

        public static PaymentStatus getByName(String name) {
            if(!nameMap.containsKey(name)) {
                return null;
            }
            return nameMap.get(name);
        }

        public static PaymentStatus getByValue(String value) {
            if(!valueMap.containsKey(value)) {
                return null;
            }
            return valueMap.get(value);
        }
    }

    public enum ExpenditureStatus {
        COMPLETION("완료"),
        PARTIAL_COMPLETION("부분완료"),
        INCOMPLETION("미완료");

        private String value;

        static final Map<String, ExpenditureStatus> nameMap = new HashMap<>();
        static final Map<String, ExpenditureStatus> valueMap = new HashMap<>();
        static {
            for(ExpenditureStatus expenditureStatus : ExpenditureStatus.values()) {
                nameMap.put(expenditureStatus.name(), expenditureStatus);
                valueMap.put(expenditureStatus.value, expenditureStatus);
            }
        }

        ExpenditureStatus(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

        public static ExpenditureStatus getByName(String name) {
            if(!nameMap.containsKey(name)) {
                return null;
            }
            return nameMap.get(name);
        }

        public static ExpenditureStatus getByValue(String value) {
            if(!valueMap.containsKey(value)) {
                return null;
            }
            return valueMap.get(value);
        }
    }

    public enum TaxStatus {
        COMPLETION("발급완료"),
        INCOMPLETION("발급미완료");

        private String value;

        static final Map<String, TaxStatus> nameMap = new HashMap<>();
        static final Map<String, TaxStatus> valueMap = new HashMap<>();
        static {
            for(TaxStatus taxStatus : TaxStatus.values()) {
                valueMap.put(taxStatus.value, taxStatus);
            }
        }

        TaxStatus(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

        public static TaxStatus getByName(String name) {
            if(!nameMap.containsKey(name)) {
                return null;
            }
            return nameMap.get(name);
        }

        public static TaxStatus getByValue(String value) {
            if(!valueMap.containsKey(value)) {
                return null;
            }
            return valueMap.get(value);
        }
    }
}
