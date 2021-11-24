package com.hserp.person.domain;

import com.hserp.company.domain.Company;
import com.hserp.global.domain.CommonTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Person extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8, nullable = false, unique = true)
    private String name;

    @Column(length = 32, nullable = false)
    private String phone;

    @Column(length = 32, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String address;

    @Column(length = 255, nullable = false)
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    private void changeName(String newName) {
        if(newName != null) {
            this.name = newName;
        }
    }

    private void changePhone(String newPhone) {
        this.phone = newPhone;
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

    private void changeCompany(Company newCompany) {
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
