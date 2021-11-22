package com.hserp.company;

import com.hserp.company.domain.Company;
import com.hserp.company.dto.CompanyRequestDto;
import com.hserp.company.dto.CompanyResponseDto;
import com.hserp.global.domain.Address;
import com.hserp.global.domain.Email;
import com.hserp.global.domain.Phone;
import com.hserp.global.domain.Remark;
import com.hserp.person.domain.Person;
import java.time.LocalDateTime;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-23T00:13:45+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (AdoptOpenJDK)"
)
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyRequestDto companyToCompanyRequestDto(Company company) {
        if ( company == null ) {
            return null;
        }

        String phone = null;
        String email = null;
        String address = null;
        String remark = null;
        String manager = null;
        String name = null;

        String value = companyPhoneValue( company );
        if ( value != null ) {
            phone = value;
        }
        String value1 = companyEmailValue( company );
        if ( value1 != null ) {
            email = value1;
        }
        String value2 = companyAddressValue( company );
        if ( value2 != null ) {
            address = value2;
        }
        String value3 = companyRemarkValue( company );
        if ( value3 != null ) {
            remark = value3;
        }
        String name1 = companyManagerName( company );
        if ( name1 != null ) {
            manager = name1;
        }
        if ( company.getName() != null ) {
            name = company.getName();
        }

        CompanyRequestDto companyRequestDto = new CompanyRequestDto( name, phone, email, address, remark, manager );

        return companyRequestDto;
    }

    @Override
    public CompanyResponseDto companyToCompanyResponseDto(Company company) {
        if ( company == null ) {
            return null;
        }

        String phone = null;
        String email = null;
        String address = null;
        String remark = null;
        String manager = null;
        Integer id = null;
        String name = null;
        LocalDateTime createdDate = null;
        LocalDateTime updatedDate = null;

        String value = companyPhoneValue( company );
        if ( value != null ) {
            phone = value;
        }
        String value1 = companyEmailValue( company );
        if ( value1 != null ) {
            email = value1;
        }
        String value2 = companyAddressValue( company );
        if ( value2 != null ) {
            address = value2;
        }
        String value3 = companyRemarkValue( company );
        if ( value3 != null ) {
            remark = value3;
        }
        String name1 = companyManagerName( company );
        if ( name1 != null ) {
            manager = name1;
        }
        if ( company.getId() != null ) {
            id = company.getId();
        }
        if ( company.getName() != null ) {
            name = company.getName();
        }
        if ( company.getCreatedDate() != null ) {
            createdDate = company.getCreatedDate();
        }
        if ( company.getUpdatedDate() != null ) {
            updatedDate = company.getUpdatedDate();
        }

        CompanyResponseDto companyResponseDto = new CompanyResponseDto( id, name, phone, email, address, remark, manager, createdDate, updatedDate );

        return companyResponseDto;
    }

    @Override
    public Company companyRequestDtoToCompany(CompanyRequestDto companyRequestDto) {
        if ( companyRequestDto == null ) {
            return null;
        }

        Phone phone = null;
        Email email = null;
        Address address = null;
        Remark remark = null;
        String name = null;

        phone = companyRequestDtoToPhone( companyRequestDto );
        email = companyRequestDtoToEmail( companyRequestDto );
        address = companyRequestDtoToAddress( companyRequestDto );
        remark = companyRequestDtoToRemark( companyRequestDto );
        if ( companyRequestDto.getName() != null ) {
            name = companyRequestDto.getName();
        }

        Person manager = null;
        Integer id = null;

        Company company = new Company( id, name, phone, email, address, remark, manager );

        return company;
    }

    @Override
    public Company companyResponseDtoToCompany(CompanyResponseDto companyResponseDto) {
        if ( companyResponseDto == null ) {
            return null;
        }

        Phone phone = null;
        Email email = null;
        Address address = null;
        Remark remark = null;
        Integer id = null;
        String name = null;

        phone = companyResponseDtoToPhone( companyResponseDto );
        email = companyResponseDtoToEmail( companyResponseDto );
        address = companyResponseDtoToAddress( companyResponseDto );
        remark = companyResponseDtoToRemark( companyResponseDto );
        if ( companyResponseDto.getId() != null ) {
            id = companyResponseDto.getId();
        }
        if ( companyResponseDto.getName() != null ) {
            name = companyResponseDto.getName();
        }

        Person manager = null;

        Company company = new Company( id, name, phone, email, address, remark, manager );

        return company;
    }

    private String companyPhoneValue(Company company) {
        if ( company == null ) {
            return null;
        }
        Phone phone = company.getPhone();
        if ( phone == null ) {
            return null;
        }
        String value = phone.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String companyEmailValue(Company company) {
        if ( company == null ) {
            return null;
        }
        Email email = company.getEmail();
        if ( email == null ) {
            return null;
        }
        String value = email.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String companyAddressValue(Company company) {
        if ( company == null ) {
            return null;
        }
        Address address = company.getAddress();
        if ( address == null ) {
            return null;
        }
        String value = address.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String companyRemarkValue(Company company) {
        if ( company == null ) {
            return null;
        }
        Remark remark = company.getRemark();
        if ( remark == null ) {
            return null;
        }
        String value = remark.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String companyManagerName(Company company) {
        if ( company == null ) {
            return null;
        }
        Person manager = company.getManager();
        if ( manager == null ) {
            return null;
        }
        String name = manager.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Phone companyRequestDtoToPhone(CompanyRequestDto companyRequestDto) {
        if ( companyRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( companyRequestDto.getPhone() != null ) {
            value = companyRequestDto.getPhone();
        }

        Phone phone = new Phone( value );

        return phone;
    }

    protected Email companyRequestDtoToEmail(CompanyRequestDto companyRequestDto) {
        if ( companyRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( companyRequestDto.getEmail() != null ) {
            value = companyRequestDto.getEmail();
        }

        Email email = new Email( value );

        return email;
    }

    protected Address companyRequestDtoToAddress(CompanyRequestDto companyRequestDto) {
        if ( companyRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( companyRequestDto.getAddress() != null ) {
            value = companyRequestDto.getAddress();
        }

        Address address = new Address( value );

        return address;
    }

    protected Remark companyRequestDtoToRemark(CompanyRequestDto companyRequestDto) {
        if ( companyRequestDto == null ) {
            return null;
        }

        String value = null;

        if ( companyRequestDto.getRemark() != null ) {
            value = companyRequestDto.getRemark();
        }

        Remark remark = new Remark( value );

        return remark;
    }

    protected Phone companyResponseDtoToPhone(CompanyResponseDto companyResponseDto) {
        if ( companyResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( companyResponseDto.getPhone() != null ) {
            value = companyResponseDto.getPhone();
        }

        Phone phone = new Phone( value );

        return phone;
    }

    protected Email companyResponseDtoToEmail(CompanyResponseDto companyResponseDto) {
        if ( companyResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( companyResponseDto.getEmail() != null ) {
            value = companyResponseDto.getEmail();
        }

        Email email = new Email( value );

        return email;
    }

    protected Address companyResponseDtoToAddress(CompanyResponseDto companyResponseDto) {
        if ( companyResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( companyResponseDto.getAddress() != null ) {
            value = companyResponseDto.getAddress();
        }

        Address address = new Address( value );

        return address;
    }

    protected Remark companyResponseDtoToRemark(CompanyResponseDto companyResponseDto) {
        if ( companyResponseDto == null ) {
            return null;
        }

        String value = null;

        if ( companyResponseDto.getRemark() != null ) {
            value = companyResponseDto.getRemark();
        }

        Remark remark = new Remark( value );

        return remark;
    }
}
