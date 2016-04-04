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

    public SimpleGUIPresenter(){
        createMockApiaryList();
    }

    private void createMockApiaryList(){
        Apiary apiary0 = new Apiary(0);
        Apiary apiary1 = new Apiary(1);

        apiaryList.add(apiary0);
        apiaryList.add(apiary1);
    }

    public List<Apiary> getApiaryList(){
        return apiaryList;
    }

    //Zwraca pasiekę o konkretnym ID
    public Apiary getApiaryWithID(int id){
        return apiaryList.get(id);
    }

    public Beehive getBeehiveWithID(int id){
        return new Beehive(id, 0, 0, 0, 0);
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
