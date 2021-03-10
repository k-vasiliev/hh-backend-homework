package ru.hh.school.mapper;

import ru.hh.school.dto.request.FavoritesEmployerRequestDto;
import ru.hh.school.dto.response.FavoritesEmployerResponseDto;
import ru.hh.school.entity.FavoritesEmployer;

import javax.inject.Singleton;

@Singleton
public class FavoritesEmployerMapper {

    private final AreaMapper areaMapper;

    public FavoritesEmployerMapper(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    public FavoritesEmployerResponseDto map(FavoritesEmployer favoritesEmployer) {
        return favoritesEmployer == null ? null : new FavoritesEmployerResponseDto(
                favoritesEmployer.getEmployerId(),
                favoritesEmployer.getEmployer() == null ? null : favoritesEmployer.getEmployer().getName(),
                favoritesEmployer.getCreated() == null ? null : favoritesEmployer.getCreated().toString(),
                favoritesEmployer.getEmployer() == null ? null : favoritesEmployer.getEmployer().getDescription(),
                areaMapper.map(favoritesEmployer.getEmployer() == null ? null : favoritesEmployer.getEmployer().getArea()),
                favoritesEmployer.getComment(),
                favoritesEmployer.getPopularityValue(favoritesEmployer.getViewsCount()),
                favoritesEmployer.getViewsCount());
    }

    public FavoritesEmployer map(FavoritesEmployerRequestDto favoritesEmployerRequestDto) {
        return favoritesEmployerRequestDto == null ? null : new FavoritesEmployer(
                favoritesEmployerRequestDto.getEmployerId(),
                favoritesEmployerRequestDto.getComment());
    }
}
