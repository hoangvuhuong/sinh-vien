package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
			String sql = "INSERT INTO demo.classes (class_name, class_slot, time_stamp)"
					+ " VALUES (?, ?, CURRENT_TIMESTAMP())";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
			sqlUpdate.declareParameter(new SqlParameter("class_name", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("class_slot", Types.INTEGER));
		    sqlUpdate.compile();
			return sqlUpdate.update(iclass.getName(), iclass.getSlot());
	}
	public int update(Classes iclass) {
		
			String sql = "UPDATE classes SET time_stamp = CURRENT_TIMESTAMP(), class_name = ?,"
					+ " class_slot = ? WHERE class_id = ? AND time_stamp = ?";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
			sqlUpdate.declareParameter(new SqlParameter("class_name", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("class_slot", Types.INTEGER));
			sqlUpdate.declareParameter(new SqlParameter("class_id", Types.INTEGER));
			sqlUpdate.declareParameter(new SqlParameter("time_stamp", Types.TIMESTAMP));
		    sqlUpdate.compile();
		    return sqlUpdate.update(iclass.getName(), iclass.getSlot(),iclass.getId(), iclass.getTimeStamp());
		
		
	}
	
	public int delete(int id) {
			String sql = "DELETE FROM classes WHERE class_id = ?";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
			sqlUpdate.declareParameter(new SqlParameter("class_id", Types.INTEGER));
		    sqlUpdate.compile();
		    return sqlUpdate.update(id);
	}
	
	public Classes getClasses(int id) {
			String sql = "SELECT * FROM classes WHERE class_id = ?";
			MappingSqlQuery<Classes> mappingSql = new MappingSqlQuery<Classes>(){
				@Override
				protected Classes mapRow(ResultSet rs, int rowNum) throws SQLException {
					Classes classes = new Classes();
					classes.setId(rs.getInt("class_id"));
					classes.setName(rs.getString("class_name"));
					classes.setSlot(rs.getInt("class_slot"));
					classes.setTimeStamp(rs.getTimestamp("time_stamp"));
					return classes;
				}
			};
			mappingSql.setDataSource(dataSource);
			mappingSql.setSql(sql);
			mappingSql.declareParameter(new SqlParameter("class_id", Types.INTEGER));
			mappingSql.compile();
			return mappingSql.findObject(id);
	}
	
	public List<Classes> getAllClass(int limit, int offset) {
		try {
			String sql = "SELECT * FROM classes LIMIT " + limit + " OFFSET " + offset ;
			MappingSqlQuery<Classes> mappingSql = new MappingSqlQuery<Classes>(){
				@Override
				protected Classes mapRow(ResultSet rs, int rowNum) throws SQLException {
					Classes classes = new Classes();
					classes.setId(rs.getInt("class_id"));
					classes.setName(rs.getString("class_name"));
					classes.setSlot(rs.getInt("class_slot"));
					classes.setTimeStamp(rs.getTimestamp("time_stamp"));
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
