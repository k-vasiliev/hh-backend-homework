package ru.hh.school.mapper;

import ru.hh.school.dto.salary.SalaryDto;
import ru.hh.school.entity.Salary;

import javax.inject.Singleton;

@Singleton
public class SalaryMapper {

    public SalaryDto map(Salary area) {
        return new SalaryDto(
                area.getTo(),
                area.getFrom(),
                area.getCurrency(),
                area.getGross()
        );
    }

    public Salary map(SalaryDto area) {
        return new Salary(
                area.getTo(),
                area.getFrom(),
                area.getCurrency(),
                area.getGross()
        );
    }
}
