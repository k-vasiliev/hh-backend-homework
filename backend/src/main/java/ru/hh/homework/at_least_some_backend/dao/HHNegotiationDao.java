package ru.hh.homework.at_least_some_backend.dao;

import ru.hh.homework.at_least_some_backend.entity.HHNegotiation;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class HHNegotiationDao extends CommonDao<HHNegotiation>
{
    public HHNegotiationDao() { super(HHNegotiation.class); }

    public List<HHNegotiation> queryAllByVacancyId(Long vacancyId)
    {
        return queryEntities((cBuilder, cQuery, root) -> cQuery
                .select(root)
                .where(cBuilder.equal(root.get("vacancy").get("id"), vacancyId))
        );
    }
}
