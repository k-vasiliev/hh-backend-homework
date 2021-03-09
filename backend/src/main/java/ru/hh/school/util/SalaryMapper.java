package ru.hh.school.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dto.SalaryDto;
import ru.hh.school.entity.Salary;

@Service
public class SalaryMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    public SalaryDto mapToDto(Salary salary) {
        return objectMapper.convertValue(salary, SalaryDto.class);
    }

    public Salary mapToEntity(SalaryDto salaryDto) {
        return objectMapper.convertValue(salaryDto, Salary.class);
    }

}
