package service;

import dao.ResumeDao;
import dto.NewResumeDto;
import entity.ResumeEntity;
import entity.UsersEntity;
import jdk.jshell.spi.ExecutionControl;
import org.glassfish.jersey.spi.AbstractThreadPoolProvider;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ResumeService {
    private final ResumeDao resumeDao;
    private final UserService userService;

    public ResumeService(ResumeDao resumeDao, UserService userService) {
        this.resumeDao = resumeDao;
        this.userService = userService;
    }

    @Transactional
    public List<ResumeEntity> getResumes() {
        var ret = resumeDao.getResumes();
        return ret;
    }

    @Transactional
    public Integer newResume(NewResumeDto resumeDto) {
        UsersEntity user = userService.getUserById(resumeDto.getUserId());
        if (user == null)
            return -1;

        if (user.getCompany() == true)
            return -1;
        ResumeEntity resume = new ResumeEntity(resumeDto);
        var newResume = resumeDao.newResume(resume);
        return  newResume;
    }

    @Transactional
    public ResumeEntity getResumeById(Integer id) {
        return resumeDao.getResumeById(id);
    }
}
