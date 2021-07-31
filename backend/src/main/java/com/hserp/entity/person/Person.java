package com.hserp.entity.person;

import com.hserp.entity.CommonTime;
import com.hserp.entity.company.Company;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class Person extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8, nullable = false)
    private String name;

    @Column(length = 255, nullable = true)
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company", nullable = true)
    private Company company;
}
