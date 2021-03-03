package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Classes;
import com.example.demo.model.IdentityCard;

@Repository
public class IdentityCardRepository {
	@Autowired
	DataSource dataSource;
	
	public IdentityCard getIdentityCard(int id) {
		try {
			String sql = "SELECT * FROM identity_cards WHERE identity_card_id = ?";
			MappingSqlQuery<IdentityCard> mappingSql = new MappingSqlQuery<IdentityCard>(){
				@Override
				protected IdentityCard mapRow(ResultSet rs, int rowNum) throws SQLException {
					IdentityCard identityCard = new IdentityCard();
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
}
