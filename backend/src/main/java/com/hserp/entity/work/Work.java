package com.hserp.entity.work;

import com.hserp.entity.company.Company;
import com.hserp.entity.Address;
import com.hserp.entity.CommonTime;
import com.hserp.entity.Remark;
import com.hserp.entity.person.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@Getter
@Entity
public class Work extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = true)
    private String content;

    @Column(nullable = false)
    private Integer price;

    @Embedded
    private Address address;

    @Embedded
    private Remark remark;

    @Column(name="work_date", nullable = true)
    private LocalDate workDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "work_type_id", nullable = false)
    private WorkType workType;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person worker;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "work_status_id", nullable = false)
    private WorkStatus workStatus;

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
        if(newCompany != null && !newCompany.equals(this.company)) {
            this.company = newCompany;
        }
    }

    public void changeWorker(Person newWorker) {
        if(newWorker != null && !newWorker.equals(this.worker)) {
            this.worker = newWorker;
        }
    }

    public void changeWorkType(WorkType newWorkType) {
        if(newWorkType != null && !newWorkType.equals(this.workType)) {
            this.workType = newWorkType;
        }
    }

    public void changeWorkStatus(WorkStatus newWorkStatus) {
        if(newWorkStatus != null && !newWorkStatus.equals(this.workStatus)) {
            this.workStatus = newWorkStatus;
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
        this.changeWorker(newWork.getWorker());
        this.changeWorkType(newWork.getWorkType());
        this.changeWorkStatus(newWork.getWorkStatus());
        this.changePaymentStatus(newWork.getPaymentStatus());
        this.changeExpenditureStatus(newWork.expenditureStatus);
        this.changeTaxStatus(newWork.getTaxStatus());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Work work = (Work) obj;
        return Objects.equals(content, work.content)
                && Objects.equals(price, work.price)
                && Objects.equals(address, work.address)
                && Objects.equals(remark, work.remark)
                && Objects.equals(workDate, work.workDate)
                && Objects.equals(workType, work.workType)
                && Objects.equals(worker, work.worker)
                && Objects.equals(company, work.company)
                && Objects.equals(workStatus, work.workStatus)
                && Objects.equals(paymentStatus, work.paymentStatus)
                && Objects.equals(expenditureStatus, work.expenditureStatus)
                && Objects.equals(taxStatus, work.taxStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, price, address, remark, workDate, workType, worker, company, workStatus, paymentStatus, expenditureStatus, taxStatus);
    }
}
