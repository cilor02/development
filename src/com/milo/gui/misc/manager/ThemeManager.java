package com.milo.gui.misc.manager;

import java.util.List;

import com.milo.gui.misc.dao.DbRowPair;
import com.milo.gui.misc.dao.JDBCThemeDao;

public class ThemeManager 
{	
//	void addTheme(String word);
//	void deleteTheme(String word);
//	void renameTheme(String oldName, String newName);
	
	private JDBCThemeDao jdbcThemeDao;
	
	public void setJdbcThemeDao(JDBCThemeDao jdbcThemeDao) {
		this.jdbcThemeDao = jdbcThemeDao;
	}

	public List<String> listAllThemes()
	{
		return this.jdbcThemeDao.selectAll();
	}
	
	public List<DbRowPair> selectAllThemesandIds ()
	{
		return jdbcThemeDao.selectAllThemesandIds();
	}
	
	public List<DbRowPair> selectWordsByTheme(String themeName)
	{
		return jdbcThemeDao.selectWordsByTheme(themeName);
	}
	
	public void addWordtoTheme(Integer themeId, Integer wordId)
	{
		jdbcThemeDao.addWordtoTheme(themeId, wordId);
	}
	
	public List<DbRowPair> selectWordsnotinTheme(String themeName)
	{
		return jdbcThemeDao.selectWordsNotinTheme(themeName);
	}
}
