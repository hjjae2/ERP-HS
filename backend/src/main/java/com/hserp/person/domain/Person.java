package com.hserp.person.domain;

import com.hserp.global.domain.Address;
import com.hserp.global.domain.CommonTime;
import com.hserp.global.domain.Email;
import com.hserp.global.domain.Phone;
import com.hserp.global.domain.Remark;
import com.hserp.company.domain.Company;
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
import javax.persistence.ManyToOne;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Person extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8, nullable = false, unique = true)
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

    public void changeCompany(Company newCompany) {
        this.company = newCompany;
    }

    public void changeTo(Person newPerson) {
        this.changeName(newPerson.getName());
        this.changePhone(newPerson.getPhone());
        this.changeEmail(newPerson.getEmail());
        this.changeAddress(newPerson.getAddress());
        this.changeRemark(newPerson.getRemark());
        this.changeCompany(newPerson.getCompany());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Person)) {
            return false;
        }

        Person person = (Person) obj;
        return Objects.equals(name, person.name)
                && Objects.equals(phone, person.phone)
                && Objects.equals(email, person.email)
                && Objects.equals(address, person.address)
                && Objects.equals(remark, person.remark)
                && Objects.equals(company, person.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, remark, company);
    }
}
