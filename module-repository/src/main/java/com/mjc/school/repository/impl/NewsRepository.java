package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.source.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    private final DataSource dataSource;

    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getAllNews();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getAllNews().stream()
                .filter(e -> Objects.equals(e.getId(), id)).findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        List<NewsModel> modelList = dataSource.getAllNews();
        if (modelList.isEmpty()) {
            entity.setId(1L);
        } else {
            entity.setId(modelList.get(readAll().size() - 1).getId() + 1L);
        }
        entity.setCreateDate(LocalDateTime.now());
        entity.setLastUpdateDate(LocalDateTime.now());
        modelList.add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel updatedNews = readById(entity.getId()).get();
        if (updatedNews != null) {
            updatedNews.setTitle(entity.getTitle());
            updatedNews.setContent(entity.getContent());
            updatedNews.setLastUpdateDate(LocalDateTime.now());
            updatedNews.setAuthorId(entity.getAuthorId());
            return updatedNews;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        List<NewsModel> removeList = new ArrayList<>();
        NewsModel news = readById(id).get();
        removeList.add(news);
        return dataSource.getAllNews().removeAll(removeList);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getAllNews()
                .stream().anyMatch(e -> Objects.equals(e.getId(), id));
    }
}
