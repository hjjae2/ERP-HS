package com.hserp.entity.person;

import com.hserp.entity.Address;
import com.hserp.entity.CommonTime;
import com.hserp.entity.Email;
import com.hserp.entity.Phone;
import com.hserp.entity.Remark;
import com.hserp.entity.company.Company;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

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
@ToString
@Getter
@Entity
public class Person extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8, nullable = false)
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

    public void changeName(String name) {
        this.name = name;
    }

    public void changePhone(Phone newPhone) {
        if(newPhone != null && !newPhone.equals(this.phone)) {
            this.phone = newPhone;
        }
    }

    public void changeEmail(Email newEmail) {
        if(newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
        }
    }

    public void changeAddress(Address newAddress) {
        if(newAddress != null && !newAddress.equals(this.address)) {
            this.address = newAddress;
        }
    }

    public void changeRemark(Remark newRemark) {
        if(newRemark != null && !newRemark.equals(this.remark)) {
            this.remark = newRemark;
        }
    }

    public void changeTo(Person newPerson) {
        this.changeName(newPerson.getName());
        this.changePhone(newPerson.getPhone());
        this.changeEmail(newPerson.getEmail());
        this.changeAddress(newPerson.getAddress());
        this.changeRemark(newPerson.getRemark());
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
        return Objects.equals(id, person.id)
                && Objects.equals(name, person.name)
                && Objects.equals(phone, person.phone)
                && Objects.equals(email, person.email)
                && Objects.equals(address, person.address)
                && Objects.equals(remark, person.remark)
                && Objects.equals(company, person.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, address, remark, company);
    }
}
