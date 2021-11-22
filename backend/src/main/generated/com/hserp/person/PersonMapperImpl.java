package com.hserp.person;

import com.hserp.company.domain.Company;
import com.hserp.global.domain.Address;
import com.hserp.global.domain.Email;
import com.hserp.global.domain.Phone;
import com.hserp.global.domain.Remark;
import com.hserp.person.domain.Person;
import com.hserp.person.dto.PersonRequestDto;
import com.hserp.person.dto.PersonResponseDto;
import java.time.LocalDateTime;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-23T00:13:44+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (AdoptOpenJDK)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonRequestDto personToPersonRequestDto(Person person) {
        if ( person == null ) {
            return null;
        }

        String phone = null;
        String email = null;
        String address = null;
        String remark = null;
        String company = null;
        String name = null;

        String value = personPhoneValue( person );
        if ( value != null ) {
            phone = value;
        }
        String value1 = personEmailValue( person );
        if ( value1 != null ) {
            email = value1;
        }
        String value2 = personAddressValue( person );
        if ( value2 != null ) {
            address = value2;
        }
        String value3 = personRemarkValue( person );
        if ( value3 != null ) {
            remark = value3;
        }
        String name1 = personCompanyName( person );
        if ( name1 != null ) {
            company = name1;
        }
        if ( person.getName() != null ) {
            name = person.getName();
        }

        PersonRequestDto personRequestDto = new PersonRequestDto( name, phone, email, address, remark, company );

        return personRequestDto;
    }

    @Override
    public PersonResponseDto personToPersonResponseDto(Person person) {
        if ( person == null ) {
            return null;
        }

        String phone = null;
        String email = null;
        String address = null;
        String remark = null;
        String company = null;
        Integer id = null;
        String name = null;
        LocalDateTime createdDate = null;
        LocalDateTime updatedDate = null;

        String value = personPhoneValue( person );
        if ( value != null ) {
            phone = value;
        }
        String value1 = personEmailValue( person );
        if ( value1 != null ) {
            email = value1;
        }
        String value2 = personAddressValue( person );
        if ( value2 != null ) {
            address = value2;
        }
        String value3 = personRemarkValue( person );
        if ( value3 != null ) {
            remark = value3;
        }
        String name1 = personCompanyName( person );
        if ( name1 != null ) {
            company = name1;
        }
        if ( person.getId() != null ) {
            id = person.getId();
        }
        if ( person.getName() != null ) {
            name = person.getName();
        }
        if ( person.getCreatedDate() != null ) {
            createdDate = person.getCreatedDate();
        }
        if ( person.getUpdatedDate() != null ) {
            updatedDate = person.getUpdatedDate();
        }

        PersonResponseDto personResponseDto = new PersonResponseDto( id, name, phone, email, address, remark, company, createdDate, updatedDate );

        return personResponseDto;
    }

    @Override
    public Person personRequestDtoToPerson(PersonRequestDto personRequestDto) {
        if ( personRequestDto == null ) {
            return null;
        }

        Phone phone = null;
        Email email = null;
        Address address = null;
        Remark remark = null;
        String name = null;

        phone = personRequestDtoToPhone( personRequestDto );
        email = personRequestDtoToEmail( personRequestDto );
        address = personRequestDtoToAddress( personRequestDto );
        remark = personRequestDtoToRemark( personRequestDto );
        if ( personRequestDto.getName() != null ) {
            name = personRequestDto.getName();
        }

        Company company = null;
        Integer id = null;

        Person person = new Person( id, name, phone, email, address, remark, company );

        return person;
    }

    @Override
    public Person personResponseDtoToPerson(PersonResponseDto personResponseDto) {
        if ( personResponseDto == null ) {
            return null;
        }

        Phone phone = null;
        Email email = null;
        Address address = null;
        Remark remark = null;
        Integer id = null;
        String name = null;

        phone = personResponseDtoToPhone( personResponseDto );
        email = personResponseDtoToEmail( personResponseDto );
        address = personResponseDtoToAddress( personResponseDto );
        remark = personResponseDtoToRemark( personResponseDto );
        if ( personResponseDto.getId() != null ) {
            id = personResponseDto.getId();
        }
        if ( personResponseDto.getName() != null ) {
            name = personResponseDto.getName();
        }

        Company company = null;

        Person person = new Person( id, name, phone, email, address, remark, company );

        return person;
    }

    private String personPhoneValue(Person person) {
        if ( person == null ) {
            return null;
        }
        Phone phone = person.getPhone();
        if ( phone == null ) {
            return null;
        }
        String value = phone.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String personEmailValue(Person person) {
        if ( person == null ) {
            return null;
        }
        Email email = person.getEmail();
        if ( email == null ) {
            return null;
        }
        String value = email.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String personAddressValue(Person person) {
        if ( person == null ) {
            return null;
        }
        Address address = person.getAddress();
        if ( address == null ) {
            return null;
        }
        String value = address.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String personRemarkValue(Person person) {
        if ( person == null ) {
            return null;
        }
        Remark remark = person.getRemark();
        if ( remark == null ) {
            return null;
        }
        String value = remark.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String personCompanyName(Person person) {
        if ( person == null ) {
            return null;
        }
        Company company = person.getCompany();
        if ( company == null ) {
            return null;
        }
        String name = company.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Phone personRequestDtoToPhone(PersonRequestDto personRequestDto) {
        if ( personRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( personRequestDto.getPhone() != null ) {
            value = personRequestDto.getPhone();
        }

        Phone phone = new Phone( value );

        return phone;
    }

    protected Email personRequestDtoToEmail(PersonRequestDto personRequestDto) {
        if ( personRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( personRequestDto.getEmail() != null ) {
            value = personRequestDto.getEmail();
        }

        Email email = new Email( value );

        return email;
    }

    protected Address personRequestDtoToAddress(PersonRequestDto personRequestDto) {
        if ( personRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( personRequestDto.getAddress() != null ) {
            value = personRequestDto.getAddress();
        }

        Address address = new Address( value );

        return address;
    }

    protected Remark personRequestDtoToRemark(PersonRequestDto personRequestDto) {
        if ( personRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( personRequestDto.getRemark() != null ) {
            value = personRequestDto.getRemark();
        }

        Remark remark = new Remark( value );

        return remark;
    }

    protected Phone personResponseDtoToPhone(PersonResponseDto personResponseDto) {
        if ( personResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( personResponseDto.getPhone() != null ) {
            value = personResponseDto.getPhone();
        }

        Phone phone = new Phone( value );

        return phone;
    }

    protected Email personResponseDtoToEmail(PersonResponseDto personResponseDto) {
        if ( personResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( personResponseDto.getEmail() != null ) {
            value = personResponseDto.getEmail();
        }

        Email email = new Email( value );

        return email;
    }

    protected Address personResponseDtoToAddress(PersonResponseDto personResponseDto) {
        if ( personResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( personResponseDto.getAddress() != null ) {
            value = personResponseDto.getAddress();
        }

        Address address = new Address( value );

        return address;
    }

    protected Remark personResponseDtoToRemark(PersonResponseDto personResponseDto) {
        if ( personResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( personResponseDto.getRemark() != null ) {
            value = personResponseDto.getRemark();
        }

        Remark remark = new Remark( value );

        return remark;
    }
}
