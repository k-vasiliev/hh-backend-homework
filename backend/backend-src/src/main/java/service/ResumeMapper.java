package service;

import dto.ResumeDto;
import entity.ResumeEntity;
import org.springframework.stereotype.Service;

@Service
public class ResumeMapper {
    public static ResumeDto toResumeDto(ResumeEntity resume) {
        return new ResumeDto(
            resume.getTitle(),
            resume.getUser().getName(),
            resume.getCreated().toString()
        );
    }
}
