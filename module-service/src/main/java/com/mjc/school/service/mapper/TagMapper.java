package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagModel tagFromDtoRequest(TagDtoRequest request);

    @Mapping(target = "news", ignore = true)
    TagDtoResponse tagToDtoResponse(TagModel tagModel);
}
