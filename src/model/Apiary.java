package model;

import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by atticus on 3/18/16.
 *
 */
public class Apiary {
    private static final int MAX_APIARY_X_SIZE = 100;
    private static final int MAX_APIARY_Y_SIZE = 100;

    @DatabaseField
    private int apiaryID;
    @DatabaseField
    private List<Beehive> listOfBeehives;
    @DatabaseField
    private String apiaryName;
    @DatabaseField
    private int xSize;
    @DatabaseField
    private int ySize;

    //Generuje mockowe pasieki dla id = 0 i id = 1;
    public Apiary(int apiaryID){
        this.apiaryID = apiaryID;
        if(apiaryID == 0) {
            listOfBeehives = mockListOfBeehives1();
            apiaryName = "Pierwsza Pasieka";
        } else {
            listOfBeehives = mockListOfBeehives2();
            apiaryName = "Druga Pasieka";
        }

        xSize = MAX_APIARY_X_SIZE;
        ySize = MAX_APIARY_Y_SIZE;
    }

    private List<Beehive> mockListOfBeehives1(){
        List<Beehive> beehiveArrayList= new ArrayList<>();
        Random generator = new Random();

        beehiveArrayList.add(new Beehive(
                0,
                0,
                10*generator.nextDouble(),
                generator.nextInt(10),
                generator.nextInt(10)));

        beehiveArrayList.add(new Beehive(
                1,
                0,
                10*generator.nextDouble(),
                generator.nextInt(10),
                generator.nextInt(10)));

        beehiveArrayList.add(new Beehive(
                2,
                0,
                10*generator.nextDouble(),
                generator.nextInt(10),
                generator.nextInt(10)));

        return beehiveArrayList;
    }

    private List<Beehive> mockListOfBeehives2(){
        List<Beehive> beehiveArrayList= new ArrayList<>();
        Random generator = new Random();

        beehiveArrayList.add(new Beehive(
                3,
                1,
                10*generator.nextDouble(),
                generator.nextInt(10),
                generator.nextInt(10)));

        beehiveArrayList.add(new Beehive(
                4,
                1,
                10*generator.nextDouble(),
                generator.nextInt(10),
                generator.nextInt(10)));

        beehiveArrayList.add(new Beehive(
                5,
                1,
                10*generator.nextDouble(),
                generator.nextInt(10),
                generator.nextInt(10)));

        return beehiveArrayList;
    }


    public String getApiaryName() {
        return apiaryName;
    }

    public void setApiaryName(String apiaryName) {
        this.apiaryName = apiaryName;
    }

    public List<Beehive> getListOfBehives() {
        return listOfBeehives;
    }

   
    

}
