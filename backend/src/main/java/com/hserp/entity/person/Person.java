package com.hserp.entity.person;

import com.hserp.entity.Address;
import com.hserp.entity.CommonTime;
import com.hserp.entity.Email;
import com.hserp.entity.Phone;
import com.hserp.entity.Remark;
import com.hserp.entity.company.Company;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

    @Embedded
    private Phone phone;

    @Embedded
    private Email email;

    @Embedded
    private Address address;

    @Embedded
    private Remark remark;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;
}
