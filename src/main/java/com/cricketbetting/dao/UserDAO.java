package com.cricketbetting.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * public UserDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;
	 * }
	 */
    public List<Map<String,Object>> findAll() 
    {
    	List<Map<String,Object>> list =null;
    	try
    	{
    		 String sql = "SELECT * FROM user_details";
    		 list =jdbcTemplate.queryForList(sql);
    	}
    	catch (Exception e) 
    	{
    		throw e;
		}
    	return list;
    }
    
    public void insertUserDetails(String username,String emailId,int mobileNumber,String userPassword)
    {
    	try
    	{
    		String sql="insert into user_details (username,email_id,mobile_number,user_password) "
    				+ "values('"+username+"','"+emailId+"',"+mobileNumber+",'"+userPassword+"')";
    		jdbcTemplate.update(sql);
    	}
    	catch (Exception e) 
    	{
    		throw e;
		}
    }
    
    public List<Map<String, Object>> getLogin(String mobileNumber,String userPassword)
    {
    	List<Map<String, Object>> users=null; 
    	try
    	{
    		String sql="select * from user_details where mobile_number="+mobileNumber+" and user_password='"+userPassword+"'";
    		users=jdbcTemplate.queryForList(sql);
    	}
    	catch (Exception e) 
    	{
    		throw e;
		}
    	return users;
    }

}
