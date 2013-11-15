package com.milo.gui.misc.manager;

public interface WordGroupManager {
	
	void addWord(String word);
	void deleteWord(String word);
	void addWordtoGroup(String word, String group);
	void removeWordfromGroup(String word, String group);
	void addGroup(String group);
	void removeGroup(String group);
	void saveGroup(String group);
	String[] listAllWords();
	String[] listAllGroupWords();
	String[] listAllGroups();

}
