package com.hserp.global.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Remark {

    @Column(name = "remark", length = 255, nullable = true)
    private String value;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Remark remark = (Remark) obj;
        return Objects.equals(value, remark.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
