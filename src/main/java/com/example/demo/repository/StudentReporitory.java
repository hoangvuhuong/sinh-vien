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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Classes;
import com.example.demo.model.IdentityCard;
import com.example.demo.model.Student;

@Repository
public class StudentReporitory {
	@Autowired
	DataSource dataSource;

	public Student getStudentById(int id) {
		try {
			String sql = "SELECT * FROM students WHERE student_id = ?";
			MappingSqlQuery<Student> mappingSql = new MappingSqlQuery<Student>() {
				@Override
				protected Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student student = new Student();
					student.setId(rs.getInt("student_id"));
					student.setName(rs.getString("student_name"));
					student.setEmail(rs.getString("student_email"));
					student.setPhone(rs.getString("student_phone"));
					student.setClassId(rs.getInt("class_id"));
					student.setIdentityCardId(rs.getInt("identity_card_id"));
					student.setStudentBirth(rs.getDate("student_birth"));
					student.setSex(rs.getBoolean("student_sex"));
					return student;
				}
			};
			mappingSql.setDataSource(dataSource);
			mappingSql.setSql(sql);
			mappingSql.declareParameter(new SqlParameter("student_id", Types.INTEGER));
			mappingSql.compile();
			Student student = mappingSql.findObject(id);
			IdentityCard card = this.getIdentityCard(student.getIdentityCardId());
			student.setIdentityCard(card);
			return student;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Student> getStudentByClassId(int classId) {
		try {
			String sql = "SELECT * FROM students WHERE class_id = ?";
			MappingSqlQuery<Student> mappingSql = new MappingSqlQuery<Student>() {
				@Override
				protected Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student student = new Student();
					student.setId(rs.getInt("student_id"));
					student.setName(rs.getString("student_name"));
					student.setEmail(rs.getString("student_email"));
					student.setPhone(rs.getString("student_phone"));
					student.setClassId(rs.getInt("class_id"));
					student.setIdentityCardId(rs.getInt("identity_card_id"));
					student.setStudentBirth(rs.getDate("student_birth"));
					student.setSex(rs.getBoolean("student_sex"));
					return student;
				}
			};
			mappingSql.setDataSource(dataSource);
			mappingSql.setSql(sql);
			mappingSql.declareParameter(new SqlParameter("class_id", Types.INTEGER));
			mappingSql.compile();
			return mappingSql.execute(classId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Classes> getListClassByStudentId(int id) {
		try {
			String sql = "SELECT classes.class_id, classes.class_name, classes.class_slot FROM "
					+ "classes INNER JOIN students ON classes.student_id = students.student_id AND "
					+ "students.student_id = ?";
			MappingSqlQuery<Classes> mappingSql = new MappingSqlQuery<Classes>() {
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
			mappingSql.declareParameter(new SqlParameter("students.student_id", Types.INTEGER));
			mappingSql.compile();
			return mappingSql.execute(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insert(Student student) {
		try {
			IdentityCard iden = student.getIdentityCard();
			int key = this.insertIdentityCard(iden);
			student.setIdentityCardId(key);
			String sql = "INSERT INTO demo.students (student_name, student_email, student_phone,"
					+ " class_id, identity_card_id, student_birth, student_sex) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource, sql);
			sqlUpdate.declareParameter(new SqlParameter("student_name", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("student_email", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("student_phone", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("class_id", Types.INTEGER));
			sqlUpdate.declareParameter(new SqlParameter("identity_card_id", Types.INTEGER));
			sqlUpdate.declareParameter(new SqlParameter("student_birth", Types.DATE));
			sqlUpdate.declareParameter(new SqlParameter("student_sex", Types.BOOLEAN));
			sqlUpdate.setReturnGeneratedKeys(true);
			sqlUpdate.compile();
			Object[] arr = {student.getIdentityCard().getName(), student.getEmail(), student.getPhone(), student.getClassId(),
					student.getIdentityCardId(), student.getIdentityCard().getBirth(), student.getSex()};
			KeyHolder keyHolder = new GeneratedKeyHolder();
			sqlUpdate.update(arr, keyHolder);
			int keyStudent = keyHolder.getKey().intValue();
			this.updateCardKey(key, keyStudent);
			return keyStudent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int delete(int id) {
		this.deleteCardByStudentId(id);
		String sql = "DELETE FROM students WHERE student_id = ?";
		SqlUpdate sqlUpdate = new SqlUpdate(dataSource, sql);
		sqlUpdate.declareParameter(new SqlParameter("student_id", Types.INTEGER));
		sqlUpdate.compile();
		return sqlUpdate.update(id);
	}

	public IdentityCard getIdentityCard(int id) {
		try {
			String sql = "SELECT * FROM identity_cards WHERE identity_card_id = ?";
			MappingSqlQuery<IdentityCard> mappingSql = new MappingSqlQuery<IdentityCard>() {
				@Override
				protected IdentityCard mapRow(ResultSet rs, int rowNum) throws SQLException {
					IdentityCard identityCard = new IdentityCard();
					identityCard.setId(rs.getInt("identity_card_id"));
					identityCard.setCode(rs.getString("identity_card_code"));
					identityCard.setName(rs.getString("identity_card_name"));
					identityCard.setBirth(rs.getDate("identity_card_birth"));
					identityCard.setPlace(rs.getString("identity_card_palce"));
					identityCard.setStudentId(rs.getInt("student_id"));
					return identityCard;
				}
			};
			mappingSql.setDataSource(dataSource);
			mappingSql.setSql(sql);
			mappingSql.declareParameter(new SqlParameter("identity_card_id", Types.INTEGER));
			mappingSql.compile();
			return mappingSql.findObject(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int insertIdentityCard(IdentityCard identityCard) {
		try {
			String sql = "INSERT INTO demo.identity_cards (identity_card_code, identity_card_name, identity_card_birth,"
					+ " identity_card_palce, student_id) VALUES (?, ?, ?, ?, ?)";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource, sql);
			sqlUpdate.declareParameter(new SqlParameter("identity_card_code", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("identity_card_name", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("identity_card_birth", Types.DATE));
			sqlUpdate.declareParameter(new SqlParameter("identity_card_palce", Types.VARCHAR));
			sqlUpdate.declareParameter(new SqlParameter("student_id", Types.INTEGER));
			sqlUpdate.setReturnGeneratedKeys(true);
			sqlUpdate.compile();
			Object[] arr = {identityCard.getCode(), identityCard.getName(), identityCard.getBirth(),
					identityCard.getPlace(), identityCard.getStudentId()};
			KeyHolder keyHolder = new GeneratedKeyHolder();
			sqlUpdate.update(arr, keyHolder);
			return keyHolder.getKey().intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int updateCardKey(int keyCard, int keyStudent) {
		try {
			String sql = "UPDATE demo.identity_cards SET student_id = ? WHERE identity_card_id = ?";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
			sqlUpdate.declareParameter(new SqlParameter("student_id", Types.INTEGER));
			sqlUpdate.declareParameter(new SqlParameter("identity_card_id", Types.INTEGER));
			sqlUpdate.compile();
			 return sqlUpdate.update(keyStudent, keyCard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int deleteCardById(int id) {
		try {
			String sql = "DELETE FROM identity_cards WHERE identity_card_id = ?";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource,sql);
			sqlUpdate.declareParameter(new SqlParameter("identity_card_id", Types.INTEGER));
			sqlUpdate.compile();
			return sqlUpdate.update(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Student> getByCountry(String country) {
		try {
			String sql = "select * from students inner join identity_cards"
					+ " ON students.student_id = identity_cards.student_id "
					+ "AND identity_cards.identity_card_palce = ?";
			MappingSqlQuery<Student> mappingSql = new MappingSqlQuery<Student>() {
				@Override
				protected Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student student = new Student();
					student.setId(rs.getInt("student_id"));
					student.setName(rs.getString("student_name"));
					student.setEmail(rs.getString("student_email"));
					student.setPhone(rs.getString("student_phone"));
					student.setClassId(rs.getInt("class_id"));
					student.setIdentityCardId(rs.getInt("identity_card_id"));
					student.setStudentBirth(rs.getDate("student_birth"));
					student.setSex(rs.getBoolean("student_sex"));
					return student;
				}
			};
			mappingSql.setDataSource(dataSource);
			mappingSql.setSql(sql);
			mappingSql.declareParameter(new SqlParameter("identity_cards.identity_card_palce", Types.VARCHAR));
			mappingSql.compile();
			return mappingSql.execute(country);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int deleteCardByStudentId(int studentId) {
		try {
			String sql = "DELETE FROM identity_cards WHERE student_id = ?";
			SqlUpdate sqlUpdate = new SqlUpdate(dataSource, sql);
			sqlUpdate.declareParameter(new SqlParameter("student_id", Types.VARCHAR));
			sqlUpdate.compile();
			return sqlUpdate.update(studentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}