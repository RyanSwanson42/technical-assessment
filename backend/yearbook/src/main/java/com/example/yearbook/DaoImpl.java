package com.example.yearbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements Dao {
	@Autowired JdbcTemplate jdbcTemplate;
	
	public SubmitResponse submit(OrderRequest orderRequest) {
		//INSERT INTO orders(first_name, last_name, grade, state, address, zip)
		//VALUES('John', 'Smith', 'Kindergarten', 'New York', '1600 Pennsylvania Avenue', '11239');
		SubmitResponse response = new SubmitResponse();
		
		String sql = "INSERT INTO orders(first_name, last_name, grade, state, city, address, zip)"
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
//		int updated = jdbcTemplate.update(sql, orderRequest.getFirstName(), orderRequest.getLastName(), orderRequest.getGrade(),
//				orderRequest.getState(), orderRequest.getAddress(), orderRequest.getZip());
		
		
		jdbcTemplate.update(new PreparedStatementCreator() {	
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "orderid" });
				ps.setString(1, orderRequest.getFirstName());
				ps.setString(2, orderRequest.getLastName());
				ps.setString(3, orderRequest.getGrade());
				ps.setString(4, orderRequest.getState());
				ps.setString(5, orderRequest.getCity());
				ps.setString(6, orderRequest.getAddress());
				ps.setString(7, orderRequest.getZip());
				return ps;
			}		
		}, keyHolder);
		
		response.setOrderId(keyHolder.getKey().toString());
		response.setLastName(orderRequest.getLastName());
		response.setMessage("made it this far");
		
		return response;
			
	}
	
	public LookupResponse lookup(LookupRequest lookupRequest) {
		List<LookupResponse> resultSet = new ArrayList<LookupResponse>();
		LookupResponse response = new LookupResponse();
		String sql = "SELECT * FROM ORDERS o "
				+ "WHERE o.orderId = ? and o.last_name = ?";
		
		String orderId = lookupRequest.getOrderId();
		String lastName = lookupRequest.getLastName();
		
		resultSet = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, orderId);
				preparedStatement.setString(2, lastName);
			}
			
		}, BeanPropertyRowMapper.newInstance(LookupResponse.class));
			
		if(!resultSet.isEmpty()) {
			response = resultSet.get(0);
		} else {
			response.setOrderid("-1");
		}
		return response;
	}
}
