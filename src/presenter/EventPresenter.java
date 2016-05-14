package presenter;

import com.google.api.services.calendar.model.Event;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import general.exceptions.FactoryUnableToCreateDaoException;
import model.InternalEvent;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.interfaces.DatabaseAccessObject;
import org.joda.time.DateTime;
import presenter.utilities.GoogleCalendarHelper;

import java.io.IOException;

/**
 * Created by atticus on 5/14/16.
 */
public class EventPresenter extends DatabasePresenter{

    public EventPresenter(JdbcPooledConnectionSource jdbcPooledConnectionSource){
        super(jdbcPooledConnectionSource);
    }

    public void saveInternalEventToDatabaseAndGoogleCalendar(InternalEvent internalEvent){
        internalEventDao.create(internalEvent);

    }

    public void testEventPresenter(){
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
        try {
            Event event = googleCalendarHelper.pushEventAndReturnResponse(internalEvent);
            System.out.printf("Event created: %s\n", event.getHtmlLink());
            System.out.println("Event googleId: " + event.getId());
        } catch (IOException e){
            e.printStackTrace();
        }
        googleCalendarHelper.getEvents();
        if(settings.isDeleteEventsFromGoogle()){
            googleCalendarHelper.deleteAllEvents();
        }

    }
}
