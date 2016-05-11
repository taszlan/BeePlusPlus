import com.j256.ormlite.logger.LocalLog;
import model.Apiary;
import model.Beehive;
import model.database.DatabaseCreator;
import model.database.DatabaseHelper;
import model.database.DatabaseHelperSingleton;
import model.database.interfaces.IDatabaseHelper;
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
        databaseCreator.printLog();
        databaseTestingMethod(databaseHelper);

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

        //Testowanie zapytań do bazy danych
        System.out.println("-------AFTER-ADDING-NEW-APIARY---------");
        databaseHelper.createNewApiary(new Apiary("Testowa pasieka", 10, 10));

        for(Apiary a : databaseHelper.getAllApiaries()){
            System.out.println(a);
        }

        System.out.println("--------BEFORE-UPDATING-BEEHIVE---------");
        for (Beehive beehive : databaseHelper.getBeehivesFromApiary(databaseHelper.getAllApiaries().get(0))){
            System.out.println(beehive);
        }

        Beehive beehive1 = (databaseHelper.getBeehivesFromApiary(databaseHelper.getAllApiaries().get(0))).get(0);
        beehive1.setInStorage(true);
        databaseHelper.updateBeehive(beehive1);

        System.out.println("--------AFTER-UPDATING-BEEHIVE---------");

        for (Beehive beehive : databaseHelper.getBeehivesFromApiary(databaseHelper.getAllApiaries().get(0))){
            System.out.println(beehive);
        }

    }
}
