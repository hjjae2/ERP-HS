package com.hserp.company.domain;

import com.hserp.global.domain.Address;
import com.hserp.global.domain.CommonTime;
import com.hserp.global.domain.Email;
import com.hserp.global.domain.Phone;
import com.hserp.global.domain.Remark;
import com.hserp.person.domain.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Getter
@Entity
public class Company extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 32, nullable = false, unique = true)
    private String name;

    @Embedded
    private Phone phone;

    @Embedded
    private Email email;

    @Embedded
    private Address address;

    @Embedded
    private Remark remark;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "person_id", nullable = true)
    private Person manager;

    public void changeName(String newName) {
        if(newName != null && !newName.equals(this.name)) {
            this.name = newName;
        }
    }

    public void changePhone(Phone newPhone) {
        this.phone = newPhone;
    }

    public void changeEmail(Email newEmail) {
        this.email = newEmail;
    }

    public void changeAddress(Address newAddress) {
        this.address = newAddress;
    }

    public void changeRemark(Remark newRemark) {
        this.remark = newRemark;
    }

    public void changeManager(Person newManager) {
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
