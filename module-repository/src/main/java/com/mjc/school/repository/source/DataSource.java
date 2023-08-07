package com.mjc.school.repository.source;


import com.mjc.school.repository.constants.Constants;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSource {
    private final List<AuthorModel> authorModels;
    private final List<NewsModel> newsModels;


    public DataSource() {
        this.authorModels = initAuthorModels();
        this.newsModels = initNewsModels();
    }

    private List<AuthorModel> initAuthorModels() {
        List<AuthorModel> authors = new ArrayList<>();
        List<String> authorsNames = Utils.readResourceFile(Constants.PATH_AUTHOR);
        Long id = 1L;
        for (int i = 0; i < Constants.DATA_SOURCE_SIZE; i++) {
            LocalDateTime initDate = Utils.getRandomDate();
            authors.add(new AuthorModel(id++
                    ,authorsNames.get(i),
                    initDate,
                    initDate));
        }
        return authors;
    }

    private List<NewsModel> initNewsModels() {
        List<NewsModel> newsList = new ArrayList<>();
        List<String> news = Utils.readResourceFile(Constants.PATH_NEWS);
        List<String> content = Utils.readResourceFile(Constants.PATH_CONTENT);
        Long id = 1L;
        for (int i = 0; i <Constants.DATA_SOURCE_SIZE; i++) {
            LocalDateTime initDate = Utils.getRandomDate();
            newsList.add(
                    new NewsModel(id++,
                            news.get(i),
                            content.get(i),
                            initDate,
                            initDate,
                            authorModels.get(i).getId())
            );
        }
        return newsList;
    }
    public List<NewsModel> getAllNews() {
        return newsModels;
    }

    public List<AuthorModel> getAllAuthors() {
        return authorModels;
    }
}
