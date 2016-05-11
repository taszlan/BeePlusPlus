package model.database;

import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.interfaces.IDatabaseHelper;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by atticus on 4/30/16.
 */
public class DatabaseCreator {
    private final static boolean DATABASE_LOGGIGNG_ENABLED = true;
    private final static boolean ALWAYS_CLEAR_DATABASE = true;


    public void createDatabase(){
        boolean databaseVersionHasChanged = false;
        IDatabaseHelper databaseHelper = DatabaseHelperSingleton.getDatabaseHelper();
        try {
            DatabaseVersion databaseVersion;

            try {
                databaseVersion = databaseHelper.getDatabaseVersions().get(0);
            } catch (Exception e) {
                e.printStackTrace();
                if (DATABASE_LOGGIGNG_ENABLED) System.out.println("Creating tables");

                databaseHelper.createTables();
                databaseHelper.createDatabaseVersion(new DatabaseVersion(0, 0));
                databaseHelper.createStorage(new Storage(0, 0, 0, 0, 0));
            }


            databaseVersion = databaseHelper.getDatabaseVersions().get(0);
            if (databaseVersion.version < 2) {
                databaseVersion.version = 2;
                databaseHelper.updateDatabaseVersion(databaseVersion);
                databaseVersionHasChanged = true;
            }

            if (ALWAYS_CLEAR_DATABASE || databaseVersionHasChanged) fillDatabaseWithExampleData();

            }catch(Exception e){
            e.printStackTrace();
            }finally{

            }
        }

    public void fillDatabaseWithExampleData(){
        try {
            IDatabaseHelper databaseHelper = DatabaseHelperSingleton.getDatabaseHelper();
            if (DATABASE_LOGGIGNG_ENABLED) System.out.println("----CREATING-SAMPLE-OBJECTS------");
            Random generator = new Random();

            //Najpierw trzeba wyczyścić tabele
            databaseHelper.clearTables();

            //Potem dodać testowe obiekty - Apiary
            Apiary apiary1 = new Apiary("Pierwsza Pasieka", 100, 100);
            Apiary apiary2 = new Apiary("Druga Pasieka", 100, 100);

            List<Apiary> listOfApiaries = databaseHelper.getAllApiaries();

            //Testowe Queens
            Queen queen;
            for (int i = 0; i < 6; i++){
                queen = new Queen("Królowa nr " + i, "Miejsce " +i, new Date());
            }

            //Testowe Beehives
            Beehive beehive1 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, databaseHelper.getAllQueens().get(0).getQueenId());
            Beehive beehive2 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, databaseHelper.getAllQueens().get(1).getQueenId());
            Beehive beehive3 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, databaseHelper.getAllQueens().get(2).getQueenId());
            Beehive beehive4 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, databaseHelper.getAllQueens().get(3).getQueenId());
            Beehive beehive5 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, databaseHelper.getAllQueens().get(4).getQueenId());
            Beehive beehive6 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, databaseHelper.getAllQueens().get(5).getQueenId());
            Beehive beehive7 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), true, 0);
            Beehive beehive8 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), true, 0);

            databaseHelper.createStorage(new Storage(0,0,0,0,0));

            Storage storage1 = databaseHelper.getStorage();
            storage1.setNumberOfBodies(generator.nextInt(100));
            storage1.setNumberOfBottoms(generator.nextInt(100));
            storage1.setNumberOfFrames(generator.nextInt(100));
            storage1.setNumberOfRoofs(generator.nextInt(100));
            databaseHelper.updateStorage(storage1);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printLog(){
        IDatabaseHelper databaseHelper = DatabaseHelperSingleton.getDatabaseHelper();
        List<Beehive> listOfBeehives = databaseHelper.getAllBeehives();
        List<Apiary> listOfApiaries = databaseHelper.getAllApiaries();
        List<Queen> queenList = databaseHelper.getAllQueens();

        System.out.println("----DATABASE-FILLED-WITH-SAMPLE-DATA-------");
        System.out.println("------LISTING-DATABASE-CONTENTS------------");
        System.out.println("------LISTING-APIARIES---------------------");

        for (Apiary a : listOfApiaries) {
            System.out.println(a);
        }

        System.out.println("------LISTING-QUEENS----------------------");

        for (Queen q : queenList){
            System.out.println(q);
        }

        System.out.println("------LISTING-BEEHIVES--------------------");

        for (Beehive b : listOfBeehives) {
            System.out.println(b);
        }

        System.out.println("------LISTING-BEEHIVES-IN-APIARIES--------");
        for (Apiary a : listOfApiaries) {
            System.out.println(a);
                    List<Beehive> beehiveList = databaseHelper.getBeehivesFromApiary(a);
                    for(Beehive b : beehiveList){
                        System.out.println("----> " + b);
                    }
        }

        System.out.println("------LISTING-STORAGE ---------------------");
        System.out.println(databaseHelper.getStorage());

        System.out.println("------LISTING-BEEHIVES-IN-STORAGE---------");
        System.out.println(databaseHelper.getBeehivesFromStorage());
    }
}
