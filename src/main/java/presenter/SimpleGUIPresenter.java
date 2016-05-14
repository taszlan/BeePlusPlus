package presenter;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

/**
 * Created by atticus on 24.03.16.
 */
public class SimpleGUIPresenter extends DatabasePresenter {

    public SimpleGUIPresenter(JdbcPooledConnectionSource connectionSource){
        super(connectionSource);
    }
}
