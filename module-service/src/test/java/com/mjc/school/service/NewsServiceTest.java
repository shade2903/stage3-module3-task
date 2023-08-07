package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig
public class NewsServiceTest {
    @Configuration
    @ComponentScan({"com.mjc.school.repository",
            "com.mjc.school.service"})
    static class TestNewsConfig{}

    @Autowired
    private BaseService<NewsDtoRequest, NewsDtoResponse,Long > newsService;


    @Test
    void readAllNewsTest(){
        assertNotNull(newsService.readAll());
    }

    @Test
    void readAuthorByIdTest(){
        Long getNewsId = 5l;
        Long expectedIdNews = 5l;
        NewsDtoResponse newsDtoResponse = newsService.readById(getNewsId);
        assertNotNull(newsDtoResponse);
        assertEquals(expectedIdNews,newsDtoResponse.getId());
        assertNotNull(newsDtoResponse.getContent());
        assertNotNull(newsDtoResponse.getTitle());
        assertNotNull(newsDtoResponse.getCreateDate());
        assertNotNull(newsDtoResponse.getLastUpdateDate());
        assertNotNull(newsDtoResponse.getAuthorId());
        assertThrows(NotFoundException.class, ()-> newsService.readById(-1l));
    }

    @Test
    void createAuthorTest(){
        String expectedContent = "New content";
        String expectedTitle = "New title";
        Long expectedAuthorId = 15l;
        NewsDtoResponse createNews = newsService.create(
                new NewsDtoRequest(
                        null, "" +
                        "New title",
                        "New content",
                        15l)
        );
        assertNotNull(createNews);
        assertNotNull(createNews.getId());
        assertNotNull(createNews.getTitle());
        assertNotNull(createNews.getContent());
        assertNotNull(createNews.getCreateDate());
        assertNotNull(createNews.getLastUpdateDate());
        assertNotNull(createNews.getId());
        assertEquals(expectedContent,createNews.getContent());
        assertEquals(expectedTitle,createNews.getTitle());
        assertEquals(expectedAuthorId,createNews.getAuthorId());
    }

    @Test
    void updateAuthorTest(){
        String expectedContent = "New content";
        String expectedTitle = "New title";
        Long expectedAuthorId = 15l;
        NewsDtoResponse updatedNews = newsService.update(
                new NewsDtoRequest(
                        5l, "" +
                        "New title",
                        "New content",
                        15l)
        );
        System.out.println(updatedNews);
        assertNotNull(updatedNews);
        assertNotNull(updatedNews.getId());
        assertNotNull(updatedNews.getTitle());
        assertNotNull(updatedNews.getContent());
        assertNotNull(updatedNews.getCreateDate());
        assertNotNull(updatedNews.getLastUpdateDate());
        assertNotNull(updatedNews.getId());
        assertEquals(expectedContent,updatedNews.getContent());
        assertEquals(expectedTitle,updatedNews.getTitle());
        assertEquals(expectedAuthorId,updatedNews.getAuthorId());
        assertNotEquals(updatedNews.getLastUpdateDate(), updatedNews.getCreateDate());
    }

    @Test
    void deleteAuthorById(){
        int actualListSizeBeforeOperation = newsService.readAll().size();
        assertTrue(newsService.deleteById(13l));
        int actualListSizeAfterOperation = newsService.readAll().size();
        assertEquals(actualListSizeBeforeOperation -1,actualListSizeAfterOperation);
        assertThrows(NotFoundException.class, ()-> newsService.deleteById(-1l));
    }
}
