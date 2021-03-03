package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Classes;
import com.example.demo.model.Employee;
import com.example.demo.util.EmployeeMappingQuery;
import com.example.demo.util.EmployeeSqlUpdate;

@Repository
public class EmployeeRepository {
	private EmployeeMappingQuery employeeMapping;
	private EmployeeSqlUpdate employeeUpdate;
	@Autowired
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	    this.employeeMapping = new EmployeeMappingQuery(dataSource);
	    this.employeeUpdate = new EmployeeSqlUpdate(dataSource);
	}
	public Employee getEmployee(int id) {
		try {
			String sql = "SELECT * FROM employee WHERE employee_id = ?";
			MappingSqlQuery<Employee> mappingSql = new MappingSqlQuery<Employee>(){
				@Override
				protected Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
					Employee emp = new Employee();
					emp.setId(rs.getInt("employee_id"));
					emp.setName(rs.getString("employee_name"));
					return emp;
				}
			};
			mappingSql.setDataSource(dataSource);
			mappingSql.setSql(sql);
			mappingSql.declareParameter(new SqlParameter("employee_id", Types.INTEGER));
			mappingSql.compile();
			return mappingSql.findObject(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Employee> searchListEmployee(int age, String namePattern) {
	    List<Employee> employee = employeeMapping.execute(age, namePattern);
	    return employee;
	}
	
	public int update(int id, String name) {
		String sql = "update employee set employee_name = ? where employee_id = ?";
		SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
	      sqlUpdate.declareParameter(new SqlParameter("employee_name", Types.VARCHAR));
	      sqlUpdate.declareParameter(new SqlParameter("employee_id", Types.INTEGER));
	      sqlUpdate.compile();
		return sqlUpdate.update(name,id);
	}
	public int delete(int id) {
		String sql = "delete from employee  where employee_id = ?";
		SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
	      sqlUpdate.declareParameter(new SqlParameter("employee_id", Types.INTEGER));
	      sqlUpdate.compile();
		return sqlUpdate.update(id);
	}
	public int insert(Employee emp) {
		String sql = "INSERT INTO demo.employee (employee_name, employee_phone, employee_birthday, employee_email)"
				+ " VALUES (?, ?, ?, ?)";
		SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
		sqlUpdate.declareParameter(new SqlParameter("employee_name", Types.VARCHAR));
		sqlUpdate.declareParameter(new SqlParameter("employee_phone", Types.VARCHAR));
		sqlUpdate.declareParameter(new SqlParameter("employee_birthday", Types.DATE));
		sqlUpdate.declareParameter(new SqlParameter("employee_email", Types.VARCHAR));
	      sqlUpdate.compile();
		return sqlUpdate.update(emp.getName(), emp.getPhone(),emp.getBirthday(), emp.getEmail());
	}
}
