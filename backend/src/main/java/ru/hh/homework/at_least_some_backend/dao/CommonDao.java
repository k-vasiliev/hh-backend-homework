package ru.hh.homework.at_least_some_backend.dao;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.hh.homework.at_least_some_backend.Utils;

public abstract class CommonDao<TEntity>
{
    protected interface QueryDefinition<T> { void apply(CriteriaBuilder cBuilder, CriteriaQuery<T> cQuery, Root<T> root); }

    @Inject
    private SessionFactory sessionFactory;

    private final Class<TEntity> entityClass;

    protected CommonDao(Class<TEntity> entityClass)
    {
        this.entityClass = entityClass;
    }

    private Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public void saveEntity(TEntity entity)
    {
        getCurrentSession().save(entity);
    }

    protected List<TEntity> queryEntities(QueryDefinition<TEntity> queryDefinition)
    {
        var currentSession = getCurrentSession();
        var criteriaBuilder = currentSession.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(entityClass);
        var root = criteriaQuery.from(entityClass);

        queryDefinition.apply(criteriaBuilder, criteriaQuery, root);

        return currentSession
                .createQuery(criteriaQuery)
                .getResultList();
    }

    public List<TEntity> queryAllEntities()
    {
        return queryEntities((cBuilder, cQuery, root) ->
                cQuery.select(root)
        );
    }

    public TEntity queryEntityById(Long id)
    {
        return Utils.firstOrNull(queryEntities((cBuilder, cQuery, root) ->
                cQuery
                        .select(root)
                        .where(cBuilder.equal(root.get("id"), id))
        ));
    }
}
