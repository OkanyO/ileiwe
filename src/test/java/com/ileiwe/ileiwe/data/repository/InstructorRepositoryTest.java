package com.ileiwe.ileiwe.data.repository;

import com.ileiwe.ileiwe.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;


import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Sql(scripts = ("/db/insert.sql"))

class InstructorRepositoryTest {
    @Autowired
    InstructorRepository instructorRepository;
    private Object savedInstructor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveInstructorsAsLearningPartyTest() {
        //create a learning party
        LearningParty user =
                new LearningParty("nuelteacher@gmail.com",
                        "okany123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder()
                .firstname("Nuel")
                .lastname("Okany")
                .learningParty(user).build();
        log.info("Instructor before saving -> {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after  saving ->{}", instructor);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testInstructorAfterCreatingTest() {
        //create a learning party
        LearningParty user =
                new LearningParty("nuelteacher@gmail.com",
                        "okany123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder().firstname("Nuel")
                .lastname("Okany")
                .learningParty(user).build();
        log.info("Instructor before saving -> {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after  saving ->{}", instructor);

        Instructor savedInstructor =
                instructorRepository.findById(instructor.getId()).orElse(null);
        assertThat("savedInstructor").isNotNull();
        assertThat(savedInstructor.getBio()).isNull();
        assertThat(savedInstructor.getGender()).isNull();


        savedInstructor.setBio("i am a Java instructor");
        savedInstructor.setGender(Gender.MALE);
        instructorRepository.save(savedInstructor);
        assertThat(savedInstructor.getBio()).isNotNull();
        assertThat(savedInstructor.getGender()).isNotNull();

    }

    @Test
    void createInstructorWithNullValueTest() {
        //create a learning party
        LearningParty user =
                new LearningParty("nuelteacher@gmail.com",
                        "okany123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder()
                .firstname(null)
                .lastname(null)
                .learningParty(user).build();
        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));

    }

    @Test
    void createInstructorWithEmptyValueTest() {
        //create a learning party
        LearningParty user =
                new LearningParty("nuelteacher@gmail.com",
                        "okany123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder()
                .firstname(" ")
                .lastname(" ")
                .learningParty(user).build();
        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));

    }
}