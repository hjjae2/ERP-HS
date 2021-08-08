package com.hserp;

import com.hserp.entity.Address;
import com.hserp.entity.Email;
import com.hserp.entity.Phone;
import com.hserp.entity.Remark;
import com.hserp.entity.company.Company;
import com.hserp.entity.person.Person;

public class CommonVariable {
    public static final String name = "홍길동";
    public static final String phone = "010-1234-5678";
    public static final String address = "대왕판교로 660 유스페이스 A동 5층";
    public static final String email = "hjjae2@gmail.com";
    public static final String remark = "비고";
    public static final String company = "가비아";

    public static final Phone getPhone() {
        return new Phone(phone);
    }

    public static final Address getAddress() {
        return new Address(address);
    }

    public static final Email getEmail() {
        return new Email(email);
    }

    public static final Remark getRemark() {
        return new Remark(remark);
    }

    public static final Person getPerson() {
        return new Person(null, name, getPhone(), getEmail(), getAddress(), getRemark(), null);
    }

    public static final Person getPerson(Company company) {
        return new Person(null, name, getPhone(), getEmail(), getAddress(), getRemark(), company);
    }

    public static final Company getCompany() {
        return new Company(null, company, getPhone(), getEmail(), getAddress(), getRemark(), null);
    }

    public static final Company getCompany(Person person) {
        return new Company(null, company, getPhone(), getEmail(), getAddress(), getRemark(), person);
    }
}
