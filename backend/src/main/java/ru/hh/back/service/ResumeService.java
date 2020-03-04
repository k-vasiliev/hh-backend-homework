package ru.hh.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.dao.ResumeDao;
import ru.hh.back.dto.ResumeRequestDto;
import ru.hh.back.dto.ResumeResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {
    private ResumeDao resumeDao;

    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Transactional
    public List<ResumeResponseDto> getResume(){
        var resume = resumeDao.getResume();
        return resume.stream().map(Mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public Integer saveResume(ResumeRequestDto resume){
        return resumeDao.save(Mapper.map(resume));
    }
}
