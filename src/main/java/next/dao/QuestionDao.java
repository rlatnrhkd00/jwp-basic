package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import next.model.Question;
import next.model.User;

public class QuestionDao {
	    @SuppressWarnings("unchecked")
		public List<Question> findAll() throws SQLException {
	      
	    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	    	
	        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";
	        return (List<Question>) jdbcTemplate.query(sql,(ResultSet rs)->{
	      		return new Question(rs.getLong("questionId"),rs.getString("writer"),rs.getString("title"),rs.getString("contents"),rs.getTimestamp("createdDate"),rs.getInt("countOfAnswer"));
	     	   
	      	});
	    }

	    public Question findById(String questionId) throws SQLException {
	    	 JdbcTemplate jdbcTemplate = new JdbcTemplate();
	    	
	          	String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId=?";
	          	return (Question) jdbcTemplate .queryForObject(sql,(ResultSet rs)->{
		      		return new Question(rs.getLong("questionId"),rs.getString("writer"),rs.getString("title"),rs.getString("contents"),rs.getTimestamp("createdDate"),rs.getInt("countOfAnswer"));},questionId);
	          	
	    }
}