package com.mjc.school.repository.aspect;

import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.source.DataSource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class RepositoryAspect {
    private final DataSource dataSource;

    public RepositoryAspect(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Before("@annotation(com.mjc.school.repository.annotation.OnDeleteCascade) && args(id)")
    public void CascadeOnDelete(Long id){
        List<NewsModel> modelList = dataSource.getAllNews();
        List<NewsModel> removeList = modelList.stream()
                .filter(e -> Objects.equals(e.getAuthorId(),id)).toList();
        modelList.removeAll(removeList);
    }

    @Before("@annotation(com.mjc.school.repository.annotation.OnDeleteSetNullForeignKey) && args(id)")
    public void setNullForeignKeyOnDelete(Long id){
        List<NewsModel> modelList = dataSource.getAllNews();
        for(NewsModel news : modelList){
            if(news.getAuthorId().equals(id)){
                news.setAuthorId(null);
            }
        }
    }
}
