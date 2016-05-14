package model.database.general.interfaces;

import model.database.general.DatabaseVersion;
import org.h2.jdbc.JdbcSQLException;

import java.util.List;

/**
 * Created by atticus on 4/30/16.
 */
public interface IDatabaseHelper {

    //DatabaseVersion
    List<DatabaseVersion> getDatabaseVersions() throws JdbcSQLException;

    void updateDatabaseVersion(DatabaseVersion databaseVersion);

    void createDatabaseVersion(DatabaseVersion databaseVersion);

    //Other methods
    void closeConnection();

    void createTables();

    void clearTables();
}
