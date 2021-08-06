package com.hserp.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Phone {

    @Column(name = "phone", length = 32, nullable = true)
    private String value;
}
