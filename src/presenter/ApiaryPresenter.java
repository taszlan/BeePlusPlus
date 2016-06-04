package presenter;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.DecoratedBeehiveDAO;
import model.database.access.DecoratedStorageDAO;
import model.database.access.interfaces.DatabaseAccessObject;


import java.util.List;
public class ApiaryPresenter {
	
	DatabaseAccessObjectFactory databaseAccessObjectFactory;
    DatabaseAccessObject<Apiary> apiaryDao;
   
    
  
     	
	
	 public JdbcPooledConnectionSource getConnectionSource() {
	        return connectionSource;
	    }

	    JdbcPooledConnectionSource connectionSource;
	    
	    public ApiaryPresenter(JdbcPooledConnectionSource connectionSource){
	            databaseAccessObjectFactory = new DatabaseAccessObjectFactory(connectionSource);
	            apiaryDao = databaseAccessObjectFactory.getDAO(Apiary.class);
	            
	            
	           
	            
	            this.connectionSource = connectionSource;
	        }
	    
	    public List<Apiary> getApiaryList(){
	        return apiaryDao.getAll();
	    }
	    public Apiary getApiaryWithID(int id){
	        return apiaryDao.getWithID(id);
	    }
	    public void updateApiary(Apiary apiary){
	        apiaryDao.update(apiary);
	    }
	   
	  //Ogólna metoda dodawania nowych, gotowych obiektów do bazy danych
	    public void addNewObjectToDatabase(Object object){
	        if (object.getClass() == Apiary.class) apiaryDao.create((Apiary) object);
	    }
	    
	  //Konstruktor na sterydach - tworzą nowe obiekty oraz dodają je do bazy danych.
	    public Apiary newApiary(String name, int xSize, int ySize){
	        Apiary apiary = new Apiary(name, xSize, ySize);
	        apiaryDao.create(apiary);
	        return apiary;
	    }

}
