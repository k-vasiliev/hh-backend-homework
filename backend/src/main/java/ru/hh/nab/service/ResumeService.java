package ru.hh.nab.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.ResumeDAO;
import ru.hh.nab.dto.ResponseResumeDTO;
import ru.hh.nab.entity.Resume;
import ru.hh.nab.entity.User;

import java.time.LocalDate;
import java.util.List;

public class ResumeService {

    private final ResumeDAO resumeDAO;

    private final UserService userService;


    public ResumeService(ResumeDAO resumeDAO, UserService userService) {
        this.resumeDAO = resumeDAO;
        this.userService = userService;
    }

    @Transactional
    public Resume createResume(int userId, String exp, String heading, String contacts) {
        User user = userService.getUsersById(userId);
        return resumeDAO.addResume(new Resume(user, exp, heading, contacts, true, LocalDate.now()));
    }

    @Transactional
    public List<ResponseResumeDTO> getAllResume() {
        return resumeDAO.getAllResume();
    }

    @Transactional
    public ResponseResumeDTO getResponseResumeById(Integer id) {
        return resumeDAO.getResponseResumeById(id);
    }

    @Transactional
    public Resume getResumeById(Integer id) {
        return resumeDAO.getResumeById(id);
    }
}
