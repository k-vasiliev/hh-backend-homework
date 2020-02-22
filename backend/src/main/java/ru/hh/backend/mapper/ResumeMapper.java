package ru.hh.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.dto.request.ResumeRequestDto;
import ru.hh.backend.dto.response.ResumeResponseDto;
import ru.hh.backend.model.Resume;

@Mapper(componentModel = "spring", uses = {UserDao.class, DateMapper.class})
public interface ResumeMapper {

    @Mapping(source = "userId", target = "applicant")
    Resume map(ResumeRequestDto resumeRequestDto);

    ResumeResponseDto map(Resume resume);
}
