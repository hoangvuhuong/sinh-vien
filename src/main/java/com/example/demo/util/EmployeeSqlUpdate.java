package com.example.demo.util;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class EmployeeSqlUpdate extends SqlUpdate {
	public EmployeeSqlUpdate(DataSource ds) {
        setDataSource(ds);
        setSql("update employee set employee_name = ? where employee_id = ?");
        declareParameter(new SqlParameter("employee_name", Types.VARCHAR));
        declareParameter(new SqlParameter("employee_id", Types.NUMERIC));
        compile();
    }
    public int execute(int id, String name) {
        return update(name, id);
    }
    public int delete(int id) {
    	setSql("delete from employee where employee_id = ?");
    	declareParameter(new SqlParameter("employee_id", Types.NUMERIC));
    	compile();
        return update(id);
    }
}
