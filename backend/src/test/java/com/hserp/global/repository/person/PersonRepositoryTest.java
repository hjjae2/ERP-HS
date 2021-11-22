package com.hserp.global.repository.person;

import com.hserp.CommonVariable;
import com.hserp.person.domain.Person;
import com.hserp.person.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired TestEntityManager entityManager;
    @Autowired
    PersonRepository personRepository;

    @Test
    public void save() {
        // given
        Person person = CommonVariable.getPerson();

        // when
        Integer id = personRepository.save(person).getId();
        entityManager.flush();
        entityManager.clear();

        // then
        Person savedPerson = personRepository.findById(id).orElse(null);
        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson).isEqualTo(person);
    }

    @Test
    public void findById() {
        // given
        Person person = CommonVariable.getPerson();
        Integer id = personRepository.save(person).getId();
        entityManager.flush();
        entityManager.clear();

        // when
        Person savedPerson = personRepository.findById(id).orElse(null);

        // then
        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson).isEqualTo(person);
    }

    @Test
    public void findAll() {
        // given
        Person person = CommonVariable.getPerson();
        personRepository.save(person);
        entityManager.flush();
        entityManager.clear();

        // when
        List<Person> persons = personRepository.findAll();

        // then
        Assertions.assertThat(persons.size()).isNotZero();
        Assertions.assertThat(persons.get(0)).isNotNull();
        Assertions.assertThat(persons.get(0)).isEqualTo(person);
    }

    @Test
    public void update() {
        // given
        String newName = "새로운이름";
        Person person = CommonVariable.getPerson();
        Person savedPerson = personRepository.save(person);

        // when
        savedPerson.changeName(newName);
        personRepository.save(savedPerson);
        entityManager.flush();
        entityManager.clear();

        // then
        Person updatedPerson = personRepository.findById(savedPerson.getId()).orElse(null);
        Assertions.assertThat(updatedPerson).isNotNull();
        Assertions.assertThat(updatedPerson.getName()).isEqualTo(newName);
    }

    @Test
    public void deleteById() {
        // given
        Person person = CommonVariable.getPerson();
        Person savedPerson = personRepository.save(person);

        // when
        personRepository.deleteById(savedPerson.getId());
        entityManager.flush();
        entityManager.clear();

        // then
        Person deletedPerson = personRepository.findById(savedPerson.getId()).orElse(null);
        Assertions.assertThat(deletedPerson).isNull();
    }
}