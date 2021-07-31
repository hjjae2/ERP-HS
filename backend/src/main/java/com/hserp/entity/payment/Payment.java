package com.hserp.entity.payment;

import com.hserp.entity.CommonTime;
import com.hserp.entity.person.Person;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Entity
public class Payment extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer targetPrice;

    @Column(nullable = false)
    private Integer currentPrice;

    @Column(length = 255, nullable = true)
    private String remark;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status", nullable = false)
    private PaymentStatus status;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "worker", nullable = false)
    private Person worker;
}
