package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
public class AuthorServiceTest {

    @Configuration
    @ComponentScan({"com.mjc.school.repository",
            "com.mjc.school.service"})
    static class TestConfig{}

    @Autowired
    private BaseService<AuthorDtoRequest, AuthorDtoResponse,Long > authorService;


    @Test
    void readAllAuthorsTest(){
        assertNotNull(authorService.readAll());
    }

    @Test
    void readAuthorByIdTest(){
        Long getAuthorId = 5l;
        Long expectedIdAuthor = 5l;
        AuthorDtoResponse authorDtoResponse = authorService.readById(getAuthorId);
        assertNotNull(authorDtoResponse);
        assertEquals(expectedIdAuthor,authorDtoResponse.getId());
        assertNotNull(authorDtoResponse.getName());
        assertNotNull(authorDtoResponse.getCreateDate());
        assertNotNull(authorDtoResponse.getLastUpdateDate());
        assertThrows(NotFoundException.class, ()-> authorService.readById(-1l));
    }

    @Test
    void createAuthorTest(){
      String nameExpected = "Ilon Mask";
        AuthorDtoResponse createAuthor = authorService.create(
                new AuthorDtoRequest(null, "Ilon Mask")
        );
        assertNotNull(createAuthor);
        assertNotNull(createAuthor.getId());
        assertNotNull(createAuthor.getName());
        assertNotNull(createAuthor.getCreateDate());
        assertNotNull(createAuthor.getLastUpdateDate());
        assertEquals(nameExpected,createAuthor.getName());
    }

    @Test
    void updateAuthorTest(){
        String nameExpected = "Jack Black";
        Long expectedId = 12l;
        AuthorDtoRequest author = new AuthorDtoRequest(12l,"Jack Black");
        AuthorDtoResponse updatedAuthor = authorService.update(author);
        assertNotNull(updatedAuthor);
        assertNotNull(updatedAuthor.getId());
        assertNotNull(updatedAuthor.getName());
        assertNotNull(updatedAuthor.getCreateDate());
        assertNotNull(updatedAuthor.getLastUpdateDate());
        assertEquals(nameExpected,updatedAuthor.getName());
        assertEquals(expectedId,updatedAuthor.getId());
        assertNotEquals(updatedAuthor.getLastUpdateDate(), updatedAuthor.getCreateDate());
    }

    @Test
    void deleteAuthorById(){
        int actualListSizeBeforeOperation = authorService.readAll().size();
        assertTrue(authorService.deleteById(13l));
        int actualListSizeAfterOperation = authorService.readAll().size();
        assertEquals(actualListSizeBeforeOperation -1,actualListSizeAfterOperation);
        assertThrows(NotFoundException.class, ()-> authorService.deleteById(-1l));
    }
}
