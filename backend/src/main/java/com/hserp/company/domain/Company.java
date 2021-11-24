package com.hserp.company.domain;

import com.hserp.global.domain.CommonTime;
import com.hserp.person.domain.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Company extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 32, nullable = false, unique = true)
    private String name;

    @Column(length = 32, nullable = false)
    private String phone;

    @Column(length = 32, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String address;

    @Column(length = 255, nullable = false)
    private String remark;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "person_id", nullable = true)
    private Person manager;

    private void changeName(String newName) {
        if(newName != null) {
            this.name = newName;
        }
    }

    private void changePhone(String newPhone) {
        if(newPhone != null) {
            this.phone = newPhone;
        }
    }

    private void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    private void changeAddress(String newAddress) {
        this.address = newAddress;
    }

    private void changeRemark(String newRemark) {
        this.remark = newRemark;
    }

    private void changeManager(Person newManager) {
        this.manager = newManager;
    }

    public void changeTo(Company newCompany) {
        this.changeName(newCompany.getName());
        this.changePhone(newCompany.getPhone());
        this.changeEmail(newCompany.getEmail());
        this.changeAddress(newCompany.getAddress());
        this.changeRemark(newCompany.getRemark());
        this.changeManager(newCompany.getManager());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Company)) {
            return false;
        }

        Company company = (Company) obj;
        return Objects.equals(name, company.name)
                && Objects.equals(phone, company.phone)
                && Objects.equals(email, company.email)
                && Objects.equals(address, company.address)
                && Objects.equals(remark, company.remark)
                && Objects.equals(manager, company.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, remark, manager);
    }
}
