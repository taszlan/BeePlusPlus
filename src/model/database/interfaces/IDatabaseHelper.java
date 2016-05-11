package model.database.interfaces;

import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.DatabaseVersion;
import org.h2.jdbc.JdbcSQLException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by atticus on 4/30/16.
 */
public interface IDatabaseHelper {

    //Apiary methods
    void createNewApiary(Apiary apiary);

    void updateApiary(Apiary apiary);

    void deleteApiary(Apiary apiary);

    List<Apiary> getAllApiaries();

    Apiary getApiaryWithId(int apiaryId);


    //Beehive methods
    void createNewBeehive(Beehive beehive);

    void updateBeehive(Beehive beehive);

    void deleteBeehive(Beehive beehive);

    List<Beehive> getBeehivesFromApiary(Apiary apiary);

    List<Beehive> getAllBeehives();

    Beehive getBeehiveWithId(int beehiveId);


    //Queen methods
    void createNewQueen(Queen queen);

    void updateQueen(Queen queen);

    List<Queen> getAllQueens();

    Queen getQueenWithId(int queenId);

    void deleteQueen(Queen queen);


    //Storage methods
    void createStorage(Storage storage);

    Storage getStorage();

    void updateStorage(Storage storage);

    List<Beehive> getBeehivesFromStorage();

    //DatabaseVersion
    List<DatabaseVersion> getDatabaseVersions() throws JdbcSQLException;

    void updateDatabaseVersion(DatabaseVersion databaseVersion);

    void createDatabaseVersion(DatabaseVersion databaseVersion);

    //Other methods
    void closeConnection();

    void createTables();

    void clearTables();
}
