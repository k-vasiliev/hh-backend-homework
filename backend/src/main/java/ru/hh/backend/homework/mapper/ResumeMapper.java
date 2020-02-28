package ru.hh.backend.homework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dao.UserDao;
import ru.hh.backend.homework.dto.request.ResumeRequestDto;
import ru.hh.backend.homework.dto.response.ResumeResponseDto;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Singleton;

@Service
public class ResumeMapper {
    UserDao userDao;

    @Autowired
    public ResumeMapper(UserDao userDao) {
        this.userDao = userDao;
    }

    public ResumeEntity map(ResumeRequestDto resumeRequestDto) {
        ResumeEntity resume = new ResumeEntity();
        resume.setTitle(resumeRequestDto.getTitle());
        resume.setWorkExperience(resumeRequestDto.getWorkExperience());
        resume.setContacts(resumeRequestDto.getContacts());
        resume.setUser(userDao.get(resumeRequestDto.getUserId()));
        return resume;
    }

    public ResumeResponseDto map(ResumeEntity resume) {
        return new ResumeResponseDto(resume.getId(),
                resume.getTitle(),
                resume.getContacts(),
                resume.getUser());
    }
}
