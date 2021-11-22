package com.hserp.work.domain;

import com.hserp.company.domain.Company;
import com.hserp.global.domain.Address;
import com.hserp.global.domain.CommonTime;
import com.hserp.global.domain.Remark;
import com.hserp.person.domain.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
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

    @Embedded
    private Address address;

    @Embedded
    private Remark remark;

    @Column(name="work_date", nullable = true)
    private LocalDate workDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "work_type_id", nullable = false)
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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_status_id", nullable = false)
    private PaymentStatus paymentStatus;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expenditure_status_id", nullable = false)
    private ExpenditureStatus expenditureStatus;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tax_status_id", nullable = false)
    private TaxStatus taxStatus;

    public void changeContent(String newContent) {
        this.content = newContent;
    }

    public void changePrice(Integer price) {
        this.price = price;
    }

    public void changeCommunicatorToYes() {
        this.communicator = 'Y';
    }

    public void changeCommunicatorToNo() {
        this.communicator = 'N';
    }

    public void changeWorkDate(LocalDate newWorkDate) {
        this.workDate = newWorkDate;
    }

    public void changeAddress(Address newAddress) {
        if(newAddress != null && !newAddress.equals(this.address)) {
            this.address = newAddress;
        }
    }

    public void changeRemark(Remark newRemark) {
        if(newRemark != null && !newRemark.equals(this.remark)) {
            this.remark = newRemark;
        }
    }

    public void changeCompany(Company newCompany) {
        this.company = newCompany;
    }

    public void changeCustomer(Company newCustomer) {
        this.customer = newCustomer;
    }

    public void changeWorker(Person newWorker) {
        this.worker = newWorker;
    }

    public void changeDispatcher(Person newDispatcher) {
        this.dispatcher = newDispatcher;
    }

    public void changeWorkType(WorkType newWorkType) {
        if(newWorkType != null && !newWorkType.equals(this.workType)) {
            this.workType = newWorkType;
        }
    }

    public void changePaymentStatus(PaymentStatus newPaymentStatus) {
        if(newPaymentStatus != null && !newPaymentStatus.equals(this.paymentStatus)) {
            this.paymentStatus = newPaymentStatus;
        }
    }

    public void changeExpenditureStatus(ExpenditureStatus newExpenditureStatus) {
        if(newExpenditureStatus != null && !newExpenditureStatus.equals(this.expenditureStatus)) {
            this.expenditureStatus = newExpenditureStatus;
        }
    }

    public void changeTaxStatus(TaxStatus newTaxStatus) {
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
}
