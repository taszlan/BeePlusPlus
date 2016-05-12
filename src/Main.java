import com.j256.ormlite.logger.LocalLog;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.DecoratedBeehiveDAO;
import model.database.access.DecoratedStorageDAO;
import model.database.general.DatabaseCreator;
import model.database.general.DatabaseHelperSingleton;
import model.database.access.interfaces.DatabaseAccessObject;
import model.database.general.interfaces.IDatabaseHelper;
import presenter.SimpleGUIPresenter;
import view.SimpleGIUMainView;

import java.awt.*;

/**
 * Created by atticus on 3/5/16.
 */
public class Main {
    public static final boolean GUI_MODE = false;

    public static void main(String args[]){
        //Wyłącza logowanie z ORMLite DEBUG/ERROR
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");

        final IDatabaseHelper databaseHelper = DatabaseHelperSingleton.getDatabaseHelper();

        DatabaseCreator databaseCreator = new DatabaseCreator();
        databaseCreator.createDatabase();
        databaseCreator.printLogUsingGenerics();
        //databaseTestingMethod(databaseHelper);
        DatabaseAccessObjectFactory databaseAccessObjectFactory = DatabaseAccessObjectFactory.getInstance();

        //Nie wiem czemu uruchamiają przez to EventQueue, trzeba będize rozkminić co to za czort :D
        if(GUI_MODE) {
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {

                    SimpleGIUMainView simpleGUIMain = new SimpleGIUMainView();
                    simpleGUIMain.setSimpleGUIPresenter(new SimpleGUIPresenter());
                    simpleGUIMain.display();
                }
            });
        }


        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("BeePlusPlus shutting down");
                databaseHelper.closeConnection();
            }
        });
    }

    public static void databaseTestingMethod(IDatabaseHelper databaseHelper){

        DatabaseAccessObject<Queen> queenDao = DatabaseAccessObjectFactory.getInstance().getDAO(Queen.class);
        DatabaseAccessObject<Apiary> apiaryDao = DatabaseAccessObjectFactory.getInstance().getDAO(Apiary.class);
        DatabaseAccessObject<Beehive> beehiveDao = DatabaseAccessObjectFactory.getInstance().getDAO(Beehive.class);
        DecoratedBeehiveDAO decoratedBeehiveDAO = new DecoratedBeehiveDAO(beehiveDao);
        DecoratedStorageDAO decoratedStorageDAO = new DecoratedStorageDAO(DatabaseAccessObjectFactory.getInstance().getDAO(Storage.class));

        //Testowanie zapytań do bazy danych
        System.out.println("-------AFTER-ADDING-NEW-APIARY---------");
        apiaryDao.create(new Apiary("Testowa pasieka", 10, 10));

        for(Apiary a : apiaryDao.getAll()){
            System.out.println(a);
        }

        System.out.println("--------BEFORE-UPDATING-BEEHIVE---------");
        for (Beehive beehive : decoratedBeehiveDAO.getBeehivesFromApiary(apiaryDao.getAll().get(0))){
            System.out.println(beehive);
        }

        Beehive beehive1 = decoratedBeehiveDAO.getBeehivesFromApiary(apiaryDao.getAll().get(0)).get(0);
        beehive1.setInStorage(true);
        beehiveDao.update(beehive1);

        System.out.println("--------AFTER-UPDATING-BEEHIVE---------");

        for (Beehive beehive : decoratedBeehiveDAO.getBeehivesFromApiary(apiaryDao.getAll().get(0))){
            System.out.println(beehive);
        }

    }
}
