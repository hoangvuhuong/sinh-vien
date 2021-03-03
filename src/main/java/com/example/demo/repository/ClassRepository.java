package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Classes;

@Repository
public class ClassRepository {
	@Autowired
	private DataSource dataSource;
	
	public int insert(Classes iclass) {
		try {
			String sql = "INSERT INTO demo.classes (class_name, class_slot)"
					+ " VALUES (?, ?)";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
			sqlUpdate.declareParameter(new SqlParameter("class_name", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("class_slot", Types.INTEGER));
		    sqlUpdate.compile();
			return sqlUpdate.update(iclass.getName(), iclass.getSlot());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int update(Classes iclass) {
		try {
			String sql = "UPDATE classes SET class_name = ?, class_slot = ? WHERE class_id = ?";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
			sqlUpdate.declareParameter(new SqlParameter("class_name", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("class_slot", Types.INTEGER));
			sqlUpdate.declareParameter(new SqlParameter("class_id", Types.INTEGER));
		    sqlUpdate.compile();
		    return sqlUpdate.update(iclass.getName(), iclass.getSlot(),iclass.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int delete(int id) {
		try {
			String sql = "DELETE FROM classes WHERE class_id = ?";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
			sqlUpdate.declareParameter(new SqlParameter("class_id", Types.INTEGER));
		    sqlUpdate.compile();
		    return sqlUpdate.update(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Classes getClasses(int id) {
		try {
			String sql = "SELECT * FROM classes WHERE class_id = ?";
			MappingSqlQuery<Classes> mappingSql = new MappingSqlQuery<Classes>(){
				@Override
				protected Classes mapRow(ResultSet rs, int rowNum) throws SQLException {
					Classes classes = new Classes();
					classes.setId(rs.getInt("class_id"));
					classes.setName(rs.getString("class_name"));
					classes.setSlot(rs.getInt("class_slot"));
					return classes;
				}
			};
			mappingSql.setDataSource(dataSource);
			mappingSql.setSql(sql);
			mappingSql.declareParameter(new SqlParameter("class_id", Types.INTEGER));
			mappingSql.compile();
			return mappingSql.findObject(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Classes> getAllClass() {
		try {
			String sql = "SELECT * FROM classes";
			MappingSqlQuery<Classes> mappingSql = new MappingSqlQuery<Classes>(){
				@Override
				protected Classes mapRow(ResultSet rs, int rowNum) throws SQLException {
					Classes classes = new Classes();
					classes.setId(rs.getInt("class_id"));
					classes.setName(rs.getString("class_name"));
					classes.setSlot(rs.getInt("class_slot"));
					return classes;
				}
			};
			mappingSql.setDataSource(dataSource);
			mappingSql.setSql(sql);
			mappingSql.compile();
			return mappingSql.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
