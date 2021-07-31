package com.hserp.entity.company;

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
public class Company extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8, nullable = false)
    private String name;

    @Column(length = 255, nullable = true)
    private String remark;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rep", nullable = false)
    private Person rep;
}
