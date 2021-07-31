package com.hserp.entity.work;

import com.hserp.entity.CommonTime;
import com.hserp.entity.company.Company;
import com.hserp.entity.expenditure.Expenditure;
import com.hserp.entity.payment.Payment;
import com.hserp.entity.person.Person;
import com.hserp.entity.tax.Tax;
import lombok.Getter;

import javax.persistence.Column;
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

    @Column(length = 64, nullable = true)
    private String address;

    @Column(length = 255, nullable = true)
    private String content;

    @Column(length = 255, nullable = true)
    private String remark;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = true)
    private LocalDateTime workDate;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "payment", nullable = true)
    private Payment payment;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "expenditure")
    private Expenditure expenditure;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "tax", nullable = true)
    private Tax tax;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company", nullable = false)
    private Company company;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "worker", nullable = false)
    private Person worker;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type", nullable = false)
    private WorkType type;
}
