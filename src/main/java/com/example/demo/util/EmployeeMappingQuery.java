package com.example.demo.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.example.demo.model.Employee;
public class EmployeeMappingQuery extends MappingSqlQuery<Employee>{
	public EmployeeMappingQuery(DataSource ds) {
        super(ds, "select employee_id, employee_name, employee_email,employee_phone, employee_birthday from employee where employee_id = ?");
        declareParameter(new SqlParameter("id", Types.INTEGER));
        compile();
    }

    @Override
    protected Employee mapRow(ResultSet rs, int rowNumber) throws SQLException {
    	Employee employee = new Employee();
    	employee.setId(rs.getInt("employee_id"));
    	employee.setName(rs.getString("employee_name"));
    	employee.setEmail(rs.getString("employee_email"));
    	employee.setPhone(rs.getString("employee_phone"));
    	employee.setBirthday(rs.getDate("employee_birthday"));
        return employee;
    }

}
