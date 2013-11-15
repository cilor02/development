package com.milo.gui.misc.dao;



import java.io.Serializable;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


/**
 * Entity implementation class for Entity: Term
 *
 */
public class JDBCThemeDao {
	
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

  public void insertTheme (String theme)
  {
	  StringBuilder sbSql = new StringBuilder();
	  sbSql.append("insert into theme (name) values (").append(theme).append(")");
	  jdbcTemplate.update(sbSql.toString());
  }
    
  
  public void addWordtoTheme (Integer themeId,Integer wordId)
  {
	  StringBuilder sbSql = new StringBuilder();
	  sbSql.append("insert into  theme (theme_id, word_id) values (")
	  .append(themeId).append(",")
	  .append(wordId).append(")");
	  jdbcTemplate.update(sbSql.toString());
  }
    
   public List<String> selectAll()
   {
	    String sql = "select name from theme";
	    
	    RowMapper<String> mapper = new RowMapper<String>() {
	    
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new String(rs.getString("name"));
	        }
	    };

	   return jdbcTemplate.query(sql, mapper);
   }
   
   public List<DbRowPair> selectAllThemesandIds()
   {
	    String sql = "select idtheme, name from theme";
	    	    	    
	    RowMapper<DbRowPair> mapper = new RowMapper<DbRowPair>() {
		    
	        public DbRowPair mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new DbRowPair(rs.getInt("idtheme"), rs.getString("name"));
	        }
	    };
	    return jdbcTemplate.query(sql, mapper);
   }
   
   public List<DbRowPair> selectWordsByTheme(String themeName)
   {
	    String sql = "select tw.idtheme, thm.name from theme thm, theme_word tw where theme.name = " + themeName + " and tw.theme_id = thm.idtheme  ";
	    
	    RowMapper<DbRowPair> mapper = new RowMapper<DbRowPair>() {
	    
	        public DbRowPair mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new DbRowPair(rs.getInt("idtheme"), rs.getString("name"));
	        }
	    };
	    return jdbcTemplate.query(sql, mapper);
   }
   
   public List<DbRowPair> selectWordsNotinTheme(String themeName)
   {
	    String sql = "select idtheme, name from theme where name not in " +
 "(select tw.idtheme, thm.name from theme thm, theme_word tw where theme.name = " + themeName + " and tw.theme_id = thm.idtheme ";
	    
	    RowMapper<DbRowPair> mapper = new RowMapper<DbRowPair>() {
	    
	        public DbRowPair mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new DbRowPair(rs.getInt("idtheme"), rs.getString("name"));
	        }
	    };
	    return jdbcTemplate.query(sql, mapper);
   }
   
   public List<String> selectAllIdioms()
   {
	    String sql = "select idiom from idioms_list";
	    
	    RowMapper<String> mapper = new RowMapper<String>() {
	    
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new String(rs.getString("idiom"));
	        }
	    };

	   return jdbcTemplate.query(sql, mapper);
   }
   
   public List<String> SelectAllFrenchVerbs()
   {
	    String sql = "select verb from french_verb";
	    RowMapper<String> mapper = new RowMapper<String>() {
		    
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new String(rs.getString("verb"));
	        }
	    };
	    return jdbcTemplate.query(sql, mapper);
   }
   
   
   public List<String> SelectAllFrenchTenses()
   {
	    String sql = "select name from french_tense";
	    RowMapper<String> mapper = new RowMapper<String>() {
		    
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new String(rs.getString("name"));
	        }
	    };
	    return jdbcTemplate.query(sql, mapper);
   }
   
   public List<String> SelectTensesExceptPresentContandNrPast()
   {
	    String sql = "select name from french_tense where name not in ('present continuous','near past')";
	    RowMapper<String> mapper = new RowMapper<String>() {
		    
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new String(rs.getString("name"));
	        }
	    };
	    return jdbcTemplate.query(sql, mapper);
   }
   
   public List<String> SelectAllFrenchModals()
   {
	    String sql = "select verb from french_modal";
	    RowMapper<String> mapper = new RowMapper<String>() {
		    
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new String(rs.getString("modal"));
	        }
	    };
	    return jdbcTemplate.query(sql, mapper);
   }
   
   public List<String> SelectAllEnglishFeelingAdjectives()
   {
	    String sql = "select english from infinitive_adjectives where personal = '1'";
	    RowMapper<String> mapper = new RowMapper<String>() {
		    
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            return new String(rs.getString("english"));
	        }
	    };
	    return jdbcTemplate.query(sql, mapper);
   }

}
