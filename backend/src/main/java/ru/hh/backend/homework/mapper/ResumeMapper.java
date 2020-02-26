package ru.hh.backend.homework.mapper;

import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dto.ResumeDto;
import ru.hh.backend.homework.entity.ResumeEntity;

@Service
public class ResumeMapper {
    public ResumeDto map(ResumeEntity resumeEntity) {
        return new ResumeDto(resumeEntity.getId(),
                resumeEntity.getCreationDate(),
                resumeEntity.getModificationDate(),
                resumeEntity.getTitle(),
                resumeEntity.getWorkExperience(),
                resumeEntity.getContacts(),
                resumeEntity.getApplicant());
    }
}
