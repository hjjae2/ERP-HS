package com.hserp.entity.work;

import com.hserp.entity.company.Company;
import com.hserp.entity.Address;
import com.hserp.entity.CommonTime;
import com.hserp.entity.Remark;
import com.hserp.entity.person.Person;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Entity
public class Work extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Address address;

    @Column(length = 255, nullable = true)
    private String content;

    @Embedded
    private Remark remark;

    @Column(nullable = false)
    private Integer price;

    @Column(name="work_date", nullable = true)
    private LocalDateTime workDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "work_type_id", nullable = false)
    private WorkType type;

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
}
