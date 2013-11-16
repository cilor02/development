package com.milo.gui.misc;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.event.ListDataListener;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.milo.gui.misc.dao.DbRowPair;
import com.milo.gui.misc.dao.IWordDao;
import com.milo.gui.misc.dao.JDBCDao;
import com.milo.gui.misc.dao.JDBCThemeDao;
import com.milo.gui.misc.manager.ThemeManager;

public class GroupManager  implements ActionListener{

	List repositoryList;
	List groupList;
	Choice groups;
	private static Frame frame;
	private Button selectButton;
	private Button deSelectButton;
	private Button saveButton;
	private Map<String, Integer> repositoryMap;
	private Map<String, Integer> groupMap;
	private JDBCThemeDao themeDao;
	private JDBCDao wordDao;

	public GroupManager()
	{
		
	}
	
	public void init()
	{
		repositoryList = new List();
		groupList = new List();
		groups = new Choice();
		
		java.util.List<DbRowPair> themes = themeDao.selectAllThemesandIds();
		for(DbRowPair theme : themes)
		{
			groups.add(theme.getData());
		}
		
		java.util.List<DbRowPair> words = wordDao.SelectAllFrenchVerbsandIds();
		


		for(DbRowPair word :words)
		{
			repositoryList.add(word.getData());
		}		
		
		formatLayout();

	}

	private void resetThemeLists(String themeName) {
		java.util.List<DbRowPair> themeWords = themeDao.selectWordsByTheme(themeName);
		for(DbRowPair themeWord : themeWords)
		{
			groupList.add(themeWord.getData());
		}
		
		java.util.List<DbRowPair> nonThemeWords = themeDao.selectWordsNotinTheme(themeName);

		for(DbRowPair nonThemeWord : nonThemeWords)
		{
			repositoryList.add(nonThemeWord.getData());
		}
	}

	public GroupManager(Map<String, Integer> lstRepository, Map<String, Integer> lstGroup , Map<String, Integer> groupings) {
		
		formatLayout(lstRepository, lstGroup, groupings);
	}

	private void formatLayout(Map<String, Integer> lstRepository,
			Map<String, Integer> lstGroup, Map<String, Integer> groupings) {
		repositoryMap = lstRepository;
		groupMap = lstGroup;
		frame = new Frame();
		
		repositoryList.setBounds(0, 70, 100, 500);
		repositoryList.setVisible(true);
        repositoryList.setMultipleMode(true);

		repositoryList.addItemListener(new RepositoryListener());
		
		groupList.setBounds(230,70,100,500);
		groupList.setVisible(true);
		groupList.setMultipleMode(true);
		
		groups.setBounds(230,30,100,30);

		
		groups.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent itemEvent)
			{
		        String item = (String)itemEvent.getSource();
		        refreshInterface((item));
		        
			}
		});
		
		selectButton = new Button(">>");
		
		selectButton.addActionListener(this);
		selectButton.setBounds(130,30, 25, 10);
		
		deSelectButton = new Button("<<");
		
		deSelectButton.addActionListener(this);
		deSelectButton.setBounds(130,40, 25, 10);
		
		saveButton = new Button("Save List");
		saveButton.addActionListener(this);
		saveButton.setBounds(130,340, 50, 15);
		
		frame.add(repositoryList);
		frame.add(groupList);
		frame.add(selectButton);
		frame.add(deSelectButton);
		frame.add(saveButton);

		frame.add(groups);
		frame.setBounds(0, 20, 600, 800);
	}
	
	private void formatLayout() {

		frame = new Frame();
		
		repositoryList.setBounds(0, 70, 100, 500);
		repositoryList.setVisible(true);
        repositoryList.setMultipleMode(true);

		repositoryList.addItemListener(new RepositoryListener());
		
		groupList.setBounds(230,70,100,500);
		groupList.setVisible(true);
		groupList.setMultipleMode(true);
		
		groups.setBounds(230,30,100,30);

		
		groups.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent itemEvent)
			{
		        String item = (String)itemEvent.getSource();
		        refreshInterface((item));
		        
			}
		});
		
		selectButton = new Button(">>");
		
		selectButton.addActionListener(this);
		selectButton.setBounds(130,30, 25, 10);
		
		deSelectButton = new Button("<<");
		
		deSelectButton.addActionListener(this);
		deSelectButton.setBounds(130,40, 25, 10);
		
		saveButton = new Button("Save List");
		saveButton.addActionListener(this);
		saveButton.setBounds(130,340, 50, 15);
		
		frame.add(repositoryList);
		frame.add(groupList);
		frame.add(selectButton);
		frame.add(deSelectButton);
		frame.add(saveButton);

		frame.add(groups);
		frame.setBounds(0, 20, 600, 800);
	}
	
	
	
	public void addItem( String item){
		
	}
	
	
	public void refreshInterface( String group){
		resetThemeLists(group);
	}
	
	public static void launch ()
	{
		
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

		});
		frame.setVisible(true);
	}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			ResourceBundle bundle = ResourceBundle.getBundle("config");
	        //load a properties file 		
	        System.out.println(bundle.getString("french.adjective.with.infinitives.output.location"));

	        
	        String conjugationOutputDir = bundle.getString("french.adjective.with.infinitives.output.location");
	        String springContextFile = bundle.getString("spring.context.location");
	        

			org.springframework.context.ApplicationContext ctx = new FileSystemXmlApplicationContext(springContextFile);
			
			
			
			
		    IWordDao iWordDao = (IWordDao)ctx.getBean("wordDao");
		    System.out.println("iWordDao " + iWordDao);
		    
		    JDBCThemeDao themeDao = (JDBCThemeDao)ctx.getBean("themeDao");
		    System.out.println("themeDao " + themeDao);
		    
		    
		    
		    /*			java.util.List<DbRowPair> listRepository = iWordDao.SelectAllFrenchVerbsandIds();
			Map<String, Integer> testDataRepository = new HashMap<String, Integer>();
			for(DbRowPair duo : listRepository)
			{
				testDataRepository.put(duo.getData(),duo.getId());
			}

			Map<String, Integer> testGroupDataRepository = new HashMap<String, Integer>();
			testGroupDataRepository.put("itemg1",11);
			testGroupDataRepository.put("itemg2",22);
			testGroupDataRepository.put("itemg3",33);
			testGroupDataRepository.put("itemg4",44);
			testGroupDataRepository.put("itemg5",55);
			testGroupDataRepository.put("itemg6",66);
			testGroupDataRepository.put("itemg7",77);
			testGroupDataRepository.put("itemg8",88);
			testGroupDataRepository.put("itemg9",99);
			
            ThemeManager themeManager = (ThemeManager)ctx.getBean("themeManager");

            java.util.List<String> themes = themeManager.listAllThemes();
            java.util.List<DbRowPair> themesandIds = themeManager.selectAllThemesandIds();
			
            Map<String, Integer> testThemeRepository = new HashMap<String, Integer>();

			for(DbRowPair themeIdPair : themesandIds)
			{
				testThemeRepository.put(themeIdPair.getData(),themeIdPair.getId());
			}
            
            
            String[] groupsArray = new String[themes.size()];
            themes.toArray(groupsArray);

			String[] grpArray = new String[]{"1","2","3","4","5"};
			String[] repositoryArray = new String[]{"a","b","c","d","e","a","b","c"};
//			String[] groupsArray = new String[]{"group1","group2","group3","group4","group5"};

            
			GroupManager groupManager = new GroupManager(testDataRepository,testGroupDataRepository,testThemeRepository);
*/
			GroupManager groupManager = (GroupManager)ctx.getBean("groupManager");
//			GroupManager groupManager = new GroupManager();
			groupManager.init();
			groupManager.launch();
			

		}
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(e.getSource().equals(selectButton))
			{
			  for(String item: repositoryList.getSelectedItems())
			   {
				groupList.add(item);
				repositoryList.remove(item);
			   }
			}
			if(e.getSource().equals(deSelectButton))

			{
				  for(String item: groupList.getSelectedItems())
				   {
					  repositoryList.add(item);
					  groupList.remove(item);
				   }
			}
			
			if(e.getSource().equals(saveButton))
			{

				//compare groupMap and groupList to determine adds and deletes
				
				
			}
		}
		
		private void saveChanges(Map <String,Integer> oldGroup, Map <String, Integer>newGroup)
		{
			for(String word : newGroup.keySet() )
			{
				if (!oldGroup.containsKey(word))
				{
					//add new word to link table store in category
					themeDao.addWordtoTheme(themeId, oldGroup.get(word));
				}
			}
			
			for(String word : oldGroup.keySet() )
			{
				if (!newGroup.containsKey(word))
				{
					//delete word from link table remove from category
				}
			}
			//reload interface			
		}

		public void setThemeDao(JDBCThemeDao themeDao) {
			this.themeDao = themeDao;
		}

		public void setWordDao(JDBCDao wordDao) {
			this.wordDao = wordDao;
		}
}
