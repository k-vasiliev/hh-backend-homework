package ru.hh.homework.at_least_some_backend.entity;

import org.hibernate.HibernateException;
import org.hibernate.TypeMismatchException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PostgresEnum extends EnumType
{
    private String getEnumValNameOrNull(Object value) throws TypeMismatchException
    {
        if (value == null) return null;

        else if (!value.getClass().isEnum())
            throw new TypeMismatchException(String.format(
                    "The provided type '%s' is not an enumeration.",
                    value.getClass().getName())
            );

        else return ((Enum<?>) value).name();
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException
    {
        st.setObject(
                index,
                getEnumValNameOrNull(value),
                Types.OTHER
        );
    }
}
