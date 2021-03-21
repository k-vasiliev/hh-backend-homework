package ru.hh.school.service;

import org.springframework.stereotype.Service;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    private final EmployerDao employerDao;
    private final HhApiService hhApiService;

    public EmployerService(EmployerDao employerDao, HhApiService hhApiService) {
        this.employerDao = employerDao;
        this.hhApiService = hhApiService;
    }

    private Employer fromEmployerDtoToEmployer(EmployerDto employerDto, String comment, Integer views) {
        Employer employer = new Employer();
        Area area = new Area();

        area.setId(employerDto.getAreaDto().getId());
        area.setName(employerDto.getAreaDto().getName());

        employer.setId(employerDto.getId());
        employer.setName(employerDto.getName());
        employer.setDateCreate(LocalDateTime.now());
        employer.setDescription(employerDto.getDescription());
        employer.setArea(area);
        employer.setComment(comment);
        employer.setViewsCount(views);

        return employer;
    }

    public List<Employer> getEmployersFromFavorites(Integer page, Integer perPage) {
        Optional<List<Employer>> employers = employerDao.getEmployers(page, perPage);

        employers.ifPresent(employerList -> employerList.forEach(employer ->
                employerDao.updateEmployerViews(employer.getId())));
        return employers.orElse(null);
    }

    public void saveEmployerToFavorites(Integer id, String comment) throws IOException, InterruptedException {
        EmployerDto employerDto = hhApiService.getEmployer(id);

        employerDao.saveEmployer(fromEmployerDtoToEmployer(employerDto, comment, 0));
    }

    public Employer editEmployerFavorites(Integer id, String comment) {
        Optional<Employer> employer = employerDao.getEmployer(id);

        return employer.map(value -> employerDao.editEmployer(id, comment).orElse(value)).orElse(null);
    }

    public Employer deleteEmployerFavorites(Integer id) {
        Optional<Employer> employer = employerDao.getEmployer(id);

        if (employer.isPresent()){
            employerDao.deleteEmployer(employer.get());
            return employer.get();
        }
        return null;
    }

    public Employer refreshEmployerFavorites(Integer id) throws IOException, InterruptedException {
        Optional<Employer> employer = employerDao.getEmployer(id);

        if (employer.isPresent()) {
            Employer updatedEmployer = fromEmployerDtoToEmployer(hhApiService.getEmployer(id),
                    employer.get().getComment(),
                    employer.get().getViewsCount());
            employerDao.saveEmployer(updatedEmployer);
            return updatedEmployer;
        }
        return null;
    }
}
