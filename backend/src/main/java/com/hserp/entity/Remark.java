package com.hserp.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Remark {

    @Column(name = "remark", length = 255, nullable = true)
    private String value;
}
