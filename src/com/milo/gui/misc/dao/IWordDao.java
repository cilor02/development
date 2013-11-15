package com.milo.gui.misc.dao;

import java.io.Serializable;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


/**
 * Entity implementation class for Entity: Term
 *
 */
public interface IWordDao  {
	
   public List<String> selectAll();
   public List<String> selectAllIdioms();
   public List<String> SelectAllByTableName(String tableName);
   public List<String> SelectAllFrenchVerbs();
   public List<String> SelectAllFrenchTenses();
   public List<String> SelectTensesExceptPresentContandNrPast();
   public List<String> SelectAllFrenchModals();
   public List<String> SelectAllEnglishFeelingAdjectives();
   List<DbRowPair> SelectAllFrenchVerbsandIds();

}
