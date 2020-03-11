package ru.hh.homework.at_least_some_backend.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

import ru.hh.homework.at_least_some_backend.dao.HHUserDao;
import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertUserDto;
import ru.hh.homework.at_least_some_backend.entity.HHUser;

@Singleton
public class HHUserService extends HHEntityService<HHUser, HHInsertUserDto>
{
    @Inject
    private HHUserDao dao;

    @Override
    protected HHUserDao getDao() { return dao; }

    @Override
    public HHUser createEntity(HHInsertUserDto dto)
    {
        if (dto == null) return null;

        // Ну вот этот кусок можно в конструктор, но пока опционально
        var entity = new HHUser();

        entity.setName(dto.getName());
        entity.setType(dto.getType());

        return entity;
    }

    @Transactional
    public List<HHUser> queryAllByType(HHUser.UserType type) { return getDao().queryAllByType(type); }
}
