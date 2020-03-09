package ru.hh.homework.at_least_some_backend.dao;

import javax.inject.Singleton;
import java.util.List;

import ru.hh.homework.at_least_some_backend.entity.HHUser;

@Singleton
public class HHUserDao extends CommonDao<HHUser>
{
    public HHUserDao() { super(HHUser.class); }

    public List<HHUser> queryAllByType(HHUser.UserType type)
    {
        return queryEntities((cBuilder, cQuery, root) -> cQuery
                .select(root)
                .where(cBuilder.equal(root.get("type"), type))
        );
    }
}
