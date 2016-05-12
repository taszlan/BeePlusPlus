package model.database.general;

import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.DecoratedBeehiveDAO;
import model.database.access.DecoratedStorageDAO;
import model.database.access.interfaces.DatabaseAccessObject;
import model.database.general.interfaces.IDatabaseHelper;

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
                System.out.println
                        (
                            "----------------------------------------------------- \n"+
                            "-------THIS-EXCEPTION-TRIGGERS-CREATION-------------- \n"+
                            "-------OF-THE-DATABASE-TABLES------------------------ \n"+
                            "-------NOTHING-TO-FEAR----WORKS-AS-INTENDED---------- \n"+
                            "----------------------------------------------------- \n"
                        );
                e.printStackTrace();
                if (DATABASE_LOGGIGNG_ENABLED) System.out.println("Creating tables");

                databaseHelper.createTables();
                databaseHelper.createDatabaseVersion(new DatabaseVersion(0, 0));
                DatabaseAccessObject<Storage> storageDao = DatabaseAccessObjectFactory.getInstance().getDAO(Storage.class);
                storageDao.create(new Storage(0,0,0,0,0));
            }


            databaseVersion = databaseHelper.getDatabaseVersions().get(0);
            if (databaseVersion.version < 2) {
                databaseVersion.version = 2;
                databaseHelper.updateDatabaseVersion(databaseVersion);
                databaseVersionHasChanged = true;
            }

            if (ALWAYS_CLEAR_DATABASE || databaseVersionHasChanged) fillDatabaseWithExampleDataUsingGenerics();

            }catch(Exception e){
            e.printStackTrace();
            }finally{

            }
        }

    public void fillDatabaseWithExampleDataUsingGenerics(){
        try{
            IDatabaseHelper databaseHelper = DatabaseHelperSingleton.getDatabaseHelper();
            DatabaseAccessObject<Queen> queenDao = DatabaseAccessObjectFactory.getInstance().getDAO(Queen.class);
            DatabaseAccessObject<Apiary> apiaryDao = DatabaseAccessObjectFactory.getInstance().getDAO(Apiary.class);
            DatabaseAccessObject<Beehive> beehiveDao = DatabaseAccessObjectFactory.getInstance().getDAO(Beehive.class);
            DecoratedStorageDAO decoratedStorageDAO = new DecoratedStorageDAO(DatabaseAccessObjectFactory.getInstance().getDAO(Storage.class));
            if (DATABASE_LOGGIGNG_ENABLED) System.out.println("----CREATING-SAMPLE-OBJECTS------");
            Random generator = new Random();

            //Najpierw trzeba wyczyścić tabele
            databaseHelper.clearTables();

            //Potem dodać testowe obiekty - Apiary
            Apiary apiary1 = new Apiary("Pierwsza Pasieka", 100, 100);
            Apiary apiary2 = new Apiary("Druga Pasieka", 100, 100);
            apiaryDao.create(apiary1);
            apiaryDao.create(apiary2);

            Queen queen;
            for (int i = 0; i < 6; i++){
                queen = new Queen("Królowa nr " + i, "Miejsce " +i, new Date());
                queenDao.create(queen);
            }
            List<Apiary> listOfApiaries = apiaryDao.getAll();

            //Testowe Beehives
            Beehive beehive1 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.getAll().get(0).getQueenId());
            Beehive beehive2 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.getAll().get(1).getQueenId());
            Beehive beehive3 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.getAll().get(2).getQueenId());
            Beehive beehive4 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.getAll().get(3).getQueenId());
            Beehive beehive5 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.getAll().get(4).getQueenId());
            Beehive beehive6 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.getAll().get(5).getQueenId());
            Beehive beehive7 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), true, 0);
            Beehive beehive8 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), true, 0);

            beehiveDao.create(beehive1);
            beehiveDao.create(beehive2);
            beehiveDao.create(beehive3);
            beehiveDao.create(beehive4);
            beehiveDao.create(beehive5);
            beehiveDao.create(beehive6);
            beehiveDao.create(beehive7);
            beehiveDao.create(beehive8);

            decoratedStorageDAO.create(new Storage(0,0,0,0,0));

            Storage storage = decoratedStorageDAO.getStorage();
            storage.setNumberOfBodies(generator.nextInt(100));
            storage.setNumberOfBottoms(generator.nextInt(100));
            storage.setNumberOfFrames(generator.nextInt(100));
            storage.setNumberOfRoofs(generator.nextInt(100));
            decoratedStorageDAO.update(storage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printLogUsingGenerics(){
        try{
            DatabaseAccessObjectFactory databaseAccessObjectFactory = DatabaseAccessObjectFactory.getInstance();
            DatabaseAccessObject<Apiary> apiaryDao = databaseAccessObjectFactory.getDAO(Apiary.class);
            DatabaseAccessObject<Queen> queenDao = databaseAccessObjectFactory.getDAO(Queen.class);
            DatabaseAccessObject<Beehive> beehiveDao = databaseAccessObjectFactory.getDAO(Beehive.class);
            DecoratedBeehiveDAO decoratedBeehiveDao = new DecoratedBeehiveDAO(beehiveDao);
            DecoratedStorageDAO decoratedStorageDAO = new DecoratedStorageDAO(databaseAccessObjectFactory.getDAO(Storage.class));

            List<Beehive> listOfBeehives = decoratedBeehiveDao.getAll();
            List<Apiary> listOfApiaries = apiaryDao.getAll();
            List<Queen> queenList = queenDao.getAll();

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
                List<Beehive> beehiveList = decoratedBeehiveDao.getBeehivesFromApiary(a);
                for(Beehive b : beehiveList){
                    System.out.println("----> " + b);
                }
            }

            System.out.println("------LISTING-STORAGE ---------------------");
            System.out.println(decoratedStorageDAO.getStorage());

            System.out.println("------LISTING-BEEHIVES-IN-STORAGE---------");
            System.out.println(decoratedStorageDAO.getBeehivesFromStorage());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
