package com.hserp.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Email {

    @Column(name = "email", length = 32, nullable = true)
    private String value;
}
