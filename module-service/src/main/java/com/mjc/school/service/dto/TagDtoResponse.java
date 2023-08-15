package com.mjc.school.service.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TagDtoResponse {
    private Long id;
    private Long name;

    public TagDtoResponse(Long id, Long name) {
        this.id = id;
        this.name = name;
    }

    public TagDtoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDtoResponse that = (TagDtoResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
