package ru.hh.homework.at_least_some_backend.service;

import ru.hh.homework.at_least_some_backend.dao.HHResumeDao;
import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertResumeDto;
import ru.hh.homework.at_least_some_backend.entity.HHResume;
import ru.hh.homework.at_least_some_backend.entity.HHUser;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

public class HHResumeService extends HHEntityService<HHResume, HHInsertResumeDto>
{
    @Inject
    private HHResumeDao dao;
    @Inject
    private HHUserService userService;

    @Override
    protected HHResumeDao getDao() { return dao; }

    protected HHUserService getUserService() { return userService; }

    @Override
    @Transactional
    public HHResume createEntity(HHInsertResumeDto dto)
    {
        if (dto == null) return null;

        var title = dto.getTitle();
        if (title == null) throw new BadRequestException("'title' must be not null.");

        var experience = dto.getExperience();
        if (experience == null) throw new BadRequestException("'experience' must be not null.");

        var contacts = dto.getContacts();
        if (contacts == null) throw new BadRequestException("'contacts' must be not null.");

        var userId = dto.getUserId();
        if (userId == null) throw new BadRequestException("'userId' must be not null.");

        var user = getUserService().queryById(userId);
        if (user == null) throw new BadRequestException("Couldn't find user with the specified 'userId'.");
        if (user.getType() != HHUser.UserType.APPLICANT) throw new BadRequestException("The user must be of type 'APPLICANT'.");

        var entity = new HHResume();

        entity.setTitle(title);
        entity.setExperience(experience);
        entity.setContacts(contacts);
        entity.setUser(user);

        return entity;
    }
}
