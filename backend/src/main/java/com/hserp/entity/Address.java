package com.hserp.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {

    @Column(name = "address", length = 128, nullable = true)
    private String value;
}
