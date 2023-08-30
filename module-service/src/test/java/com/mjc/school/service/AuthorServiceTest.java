
package com.mjc.school.service;


import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.impl.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    private final  List<AuthorModel> authorList = new ArrayList<>();
    private final List<NewsModel> newsList = new ArrayList<>();

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void init(){
        authorList.add(new AuthorModel(1L,"Maik Herst", LocalDateTime.now(), LocalDateTime.now()));
        authorList.add(new AuthorModel(2L,"Jodi Foster", LocalDateTime.now(), LocalDateTime.now()));
        authorList.add(new AuthorModel(3L,"Henry Cavil", LocalDateTime.now(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("test method readAll()")
  void testReadAllAuthors(){
        Mockito.when(authorRepository.readAll()).thenReturn(authorList);
        List<AuthorDtoResponse> authorDtoResponses = authorService.readAll();
        Assertions.assertNotNull(authorDtoResponses);
        assertEquals(authorList.size(), authorDtoResponses.size());
    }






}

