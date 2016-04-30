package model.database;

import model.Apiary;
import model.Beehive;

import java.util.List;

/**
 * Created by atticus on 4/30/16.
 */
public interface IDatabaseHelper {

    void createNewApiary(Apiary apiary);

    List<Beehive> getBeehivesFromApiary(int apiaryId);

    List<Apiary> getAllApiaries();

    Apiary getApiaryWithId(int apiaryId);

    Beehive getBeehiveWithId(int beehiveId);

    void closeConnection();
}
