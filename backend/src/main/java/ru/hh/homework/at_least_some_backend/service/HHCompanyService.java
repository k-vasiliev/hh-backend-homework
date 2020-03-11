package ru.hh.homework.at_least_some_backend.service;

import ru.hh.homework.at_least_some_backend.dao.HHCompanyDao;
import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertCompanyDto;
import ru.hh.homework.at_least_some_backend.entity.HHCompany;
import ru.hh.homework.at_least_some_backend.entity.HHUser;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;

@Singleton
public class HHCompanyService
{
    @Inject
    private HHCompanyDao dao;
    @Inject
    private HHUserService userService;

    @Transactional
    public HHCompany queryById(Long id)
    {
        return dao.queryEntityById(id);
    }

    @Transactional
    public List<HHCompany> queryAll() { return dao.queryAllEntities(); }

    @Transactional
    public void insertCompany(HHCompany newCompany) { dao.saveEntity(newCompany); }

    /*
    Создание сущности компании, даже до ее сохранения, требует запроса сущности пользователя.
    Мне кажется, что будет логично, если именно сервис будет ходить в другой сервис.
     */
    @Transactional
    public HHCompany createCompany(HHInsertCompanyDto dto)
    {
        if (dto == null) return null;

        var name = dto.getName();
        if (name == null) throw new BadRequestException("'name' must be not null.");

        var userId = dto.getUserId();
        if (userId == null) throw new BadRequestException("'userId' must be not null.");

        var user = userService.queryById(userId);
        if (user == null) throw new BadRequestException("Couldn't find user with the specified 'userId'.");
        if (user.getType() != HHUser.UserType.EMPLOYER) throw new BadRequestException("The user must be of type 'EMPLOYER'.");

        var entity = new HHCompany();

        entity.setName(name);
        entity.setUser(user);

        return entity;
    }
}
