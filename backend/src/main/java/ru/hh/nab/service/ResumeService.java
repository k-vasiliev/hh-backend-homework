package ru.hh.nab.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.ResumeDAO;
import ru.hh.nab.dto.ResponseResumeDTO;
import ru.hh.nab.entity.Resume;

import java.util.List;

public class ResumeService {

    private final ResumeDAO resumeDAO;

    public ResumeService(ResumeDAO resumeDAO) {
        this.resumeDAO = resumeDAO;
    }

    @Transactional
    public Resume createResume(int userId, String exp, String heading, String contacts) {
        return resumeDAO.addResume(userId, exp, heading, contacts);
    }

    @Transactional
    public List<ResponseResumeDTO> getAllResume() {
        return resumeDAO.getAllResume();
    }
}
