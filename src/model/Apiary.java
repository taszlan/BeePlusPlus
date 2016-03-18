package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by atticus on 3/18/16.
 * 
 */
public class Apiary {
    private int apiaryID;
    private List<Beehive> listOfBeehives;

    //Generuje mockowe pasieki dla id = 0 i id = 1;
    public Apiary(int apiaryID){
        this.apiaryID = apiaryID;
        if(apiaryID == 0) {
            listOfBeehives = mockListOfBeehives1();
        } else {
            listOfBeehives = mockListOfBeehives2();
        }
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

}
