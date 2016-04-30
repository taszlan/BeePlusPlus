package presenter;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atticus on 24.03.16.
 */
public class SimpleGUIPresenter {
    List<Apiary> apiaryList = new ArrayList<>();
    List<Beehive> beehiveList = new ArrayList<>();

    public SimpleGUIPresenter(){
    }
    
    
    
    public List<Apiary> getApiaryList(){
        return apiaryList;
    }
    public List<Beehive> getBeehiveList(int apiaryId){
    	
    	
    	
    	return beehiveList;
   }
    //Zwraca pasiekę o konkretnym ID
    public Apiary getApiaryWithID(int id){
        return apiaryList.get(id);
    }

    public Beehive getBeehiveWithID(int id){
  //      return new Beehive(id, 0, 0, 0, 0);
    	return beehiveList.get(id);
    }

    public void createNewBeehive(){

    }

    public void createNewApiary(int xSize, int ySize){

    }

    public void updateApiary(int apiaryID){

    }

    public void updateBeehive(int beehiveID){

    }
}
