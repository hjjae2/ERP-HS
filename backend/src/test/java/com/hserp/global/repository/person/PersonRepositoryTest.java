package com.hserp.global.repository.person;

import com.hserp.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class PersonRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    PersonRepository personRepository;
}