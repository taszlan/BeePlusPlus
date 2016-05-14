
import com.google.api.services.calendar.model.*;
import com.j256.ormlite.logger.LocalLog;
import general.exceptions.FactoryUnableToCreateDaoException;
import model.*;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import general.Settings;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.DecoratedBeehiveDAO;
import model.database.access.DecoratedStorageDAO;
import model.database.general.DatabaseCreator;
import model.database.general.DatabaseHelper;
//import model.database.general.DatabaseHelperSingleton;
import model.database.access.interfaces.DatabaseAccessObject;
import model.database.general.interfaces.IDatabaseHelper;
import org.joda.time.DateTime;
import presenter.SimpleGUIPresenter;
import presenter.utilities.CalendarQuickstart;
import presenter.utilities.GoogleCalendarHelper;
import view.SimpleGIUMainView;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by atticus on 3/5/16.
 */
public class Main {
    private static Settings settings;
    private static JdbcPooledConnectionSource connectionSource;

    public static void main(String args[]){

        initializeGlobalVariables();

        //CalendarQuickstart calendarQuickstart = new CalendarQuickstart();
        //calendarQuickstart.runQuickstart();
        GoogleCalendarHelper googleCalendarHelper = new GoogleCalendarHelper();
        //googleCalendarHelper.pushTestEvent();

        InternalEvent internalEvent = new InternalEvent("Dodawanie obiektów",
                "Prosty test dodawania obiektów",
                new DateTime().plusDays(1),
                new DateTime().plusDays(1).plusHours(2),
                new DateTime());

        DatabaseAccessObjectFactory databaseAccessObjectFactory = new DatabaseAccessObjectFactory(connectionSource);
        DatabaseAccessObject<InternalEvent> internalEventDao = null;
        try {
            internalEventDao = databaseAccessObjectFactory.getDAO(InternalEvent.class);
        } catch (FactoryUnableToCreateDaoException e){
            e.printStackTrace();
        }
        internalEventDao.create(internalEvent);
        System.out.println(internalEventDao.getAll().get(0));
        googleCalendarHelper.pushEvent(internalEvent);
        googleCalendarHelper.getEvents();
        if(settings.isDeleteEventsFromGoogle()){
            googleCalendarHelper.deleteAllEvents();
        }

        final IDatabaseHelper databaseHelper = new DatabaseHelper(connectionSource);

        if(settings.isDatabaseMode()) {
            //Wyłącza logowanie z ORMLite DEBUG/ERROR
            System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");

            DatabaseCreator databaseCreator = new DatabaseCreator(connectionSource);
            databaseCreator.createDatabase();
            databaseCreator.printLogUsingGenerics();
            //databaseTestingMethod(databaseHelper);
        }
        //Nie wiem czemu uruchamiają przez to EventQueue, trzeba będize rozkminić co to za czort :D
        if(settings.isGuiMode()) {
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    SimpleGIUMainView simpleGUIMain = new SimpleGIUMainView();
                    simpleGUIMain.setSimpleGUIPresenter(new SimpleGUIPresenter(connectionSource));
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

    private static void initializeGlobalVariables(){
        settings = new Settings();

        try{
            connectionSource = new JdbcPooledConnectionSource(new Settings().getDatabaseUrl());
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void databaseTestingMethod(IDatabaseHelper databaseHelper){
        DatabaseAccessObjectFactory databaseAccessObjectFactory = new DatabaseAccessObjectFactory(connectionSource);
        DatabaseAccessObject<Apiary> apiaryDao = null;
        DatabaseAccessObject<Beehive> beehiveDao = null;
        DecoratedBeehiveDAO decoratedBeehiveDAO = null;
        DatabaseAccessObject<Queen> queenDao;
        DecoratedStorageDAO decoratedStorageDao;
        try {
            queenDao = databaseAccessObjectFactory.getDAO(Queen.class);
            apiaryDao = databaseAccessObjectFactory.getDAO(Apiary.class);
            beehiveDao = databaseAccessObjectFactory.getDAO(Beehive.class);
            decoratedBeehiveDAO = new DecoratedBeehiveDAO(beehiveDao);
            decoratedStorageDao = new DecoratedStorageDAO(databaseAccessObjectFactory.getDAO(Storage.class));
        } catch (FactoryUnableToCreateDaoException e) {
            e.printStackTrace();
        }

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
