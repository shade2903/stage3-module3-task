package com.mjc.school.service;


import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.TagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {

    private final Long EXPECTED_ID = 3L;
    private final String EXPECTED_TITLE = "Test Title";
    private final String EXPECTED_CONTENT = "TEST CONTENT";

    private final Long EXPECTED_AUTHOR_ID = 1L;
    @Mock
    private  NewsRepository newsRepository;
    @InjectMocks
    private  NewsService newsService;
    private NewsDtoRequest newsDtoRequest;

    private NewsModel newsModel;

    @BeforeEach
    public void init(){
        newsDtoRequest =  new NewsDtoRequest(EXPECTED_ID, EXPECTED_TITLE,
                EXPECTED_CONTENT, 1L, List.of(2L,3L,4L));
        newsModel = NewsMapper.INSTANCE.newsFromDtoRequest(newsDtoRequest);
    }

    @Test
    void readAllTest(){
        int expectedSize = 2;
        NewsDtoRequest newsDtoRequest1 = new NewsDtoRequest(2L, "Test title2", "Test content2",
                3L,List.of(1L,4L));
        NewsModel newsModel = NewsMapper.INSTANCE.newsFromDtoRequest(newsDtoRequest);
        NewsModel newsModel1 = NewsMapper.INSTANCE.newsFromDtoRequest(newsDtoRequest1);
        when(newsRepository.readAll()).thenReturn(List.of(newsModel,newsModel1));
        List<NewsDtoResponse> newsDtoResponses = newsService.readAll();
        assertNotNull(newsDtoResponses);
        assertEquals(2, newsDtoResponses.size());
    }

    @Test
    void createTest(){
//        given(newsRepository.create(newsModel)).willReturn(newsModel);
//        NewsDtoResponse actual = newsService.create(newsDtoRequest);
//        System.out.println(actual);
//        assertNotNull(actual);
//        assertEquals(EXPECTED_ID, actual.getId());
//        assertEquals(EXPECTED_TITLE, actual.getTitle());
//        assertEquals(EXPECTED_CONTENT, actual.getContent());
    }


}
