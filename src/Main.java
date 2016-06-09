
import com.google.api.services.calendar.model.*;
import com.google.api.services.calendar.model.Event;
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
import presenter.ApiaryPresenter;
import org.joda.time.DateTime;
import presenter.EventPresenter;
import presenter.SimpleGUIPresenter;
import presenter.utilities.CalendarQuickstart;
import presenter.utilities.CalendarThread;
import presenter.utilities.GoogleCalendarHelper;
import view.SimpleGIUMainView;
import java.awt.*;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by atticus on 3/5/16.
 */
public class Main {

    private static Settings settings;
    private static JdbcPooledConnectionSource connectionSource;
    public static void main(String args[]){

        initializeGlobalVariables();
        LinkedBlockingQueue<InternalEvent> queue = new LinkedBlockingQueue<>();
        DatabaseAccessObject<InternalEvent> internalEventDatabaseAccessObject;
        CalendarThread calendarThread = null;
        if(settings.isgCalMode()){
            try {
                internalEventDatabaseAccessObject = new DatabaseAccessObjectFactory(connectionSource).getDAO(InternalEvent.class);
                calendarThread = new CalendarThread(queue, internalEventDatabaseAccessObject);
                calendarThread.start();
                //EventPresenter eventPresenter = new EventPresenter(connectionSource);
                //eventPresenter.testEventPresenter();
            } catch (FactoryUnableToCreateDaoException e) {
                e.printStackTrace();
            }

            try {
                queue.put(new InternalEvent("Dodawanie obiektów",
                        "Prosty test dodawania obiektów",
                        new DateTime().plusDays(1),
                        new DateTime().plusDays(1).plusHours(2),
                        new DateTime()));

                queue.put(new InternalEvent("Inne dodawanie obiektów",
                        "Prosty test dodawania obiektów",
                        new DateTime().plusDays(2),
                        new DateTime().plusDays(2).plusHours(2),
                        new DateTime().plusDays(1)));

                queue.put(new InternalEvent(true));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(settings.isDeleteEventsFromGoogle()){
                GoogleCalendarHelper googleCalendarHelper = new GoogleCalendarHelper();
                googleCalendarHelper.getEvents();
                googleCalendarHelper.deleteAllEvents();
            }
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
                    simpleGUIMain.setApiaryPresenter(new ApiaryPresenter(connectionSource));

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

    }
}
