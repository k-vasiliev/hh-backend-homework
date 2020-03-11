package ru.hh.homework.at_least_some_backend.service;

import ru.hh.homework.at_least_some_backend.dao.CommonDao;
import ru.hh.homework.at_least_some_backend.dto.insert.InsertDto;
import ru.hh.homework.at_least_some_backend.entity.HHEntity;

import javax.transaction.Transactional;
import java.util.List;

public abstract class HHEntityService<TEntity extends HHEntity, TInsertDto extends InsertDto<TEntity>>
{
    protected abstract CommonDao<TEntity> getDao();

    @Transactional
    public List<TEntity> queryAll() { return getDao().queryAllEntities(); }

    @Transactional
    public TEntity queryById(Long id) { return getDao().queryEntityById(id); }

    @Transactional
    public void saveEntity(TEntity entity) { getDao().saveEntity(entity); }

    /*
    Так как в основном для создания сущности нужно предварительно пойти в сервисы
    и эту роль мне показалось логичным дать сервису,
    то и в общем я сделал, что EntityService - это и фабричный класс для соответстующих сущностей.
     */
    public abstract TEntity createEntity(TInsertDto dto);
}
