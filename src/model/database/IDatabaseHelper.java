package model.database;

import model.Apiary;
import model.Beehive;

import java.util.List;

/**
 * Created by atticus on 4/30/16.
 */
public interface IDatabaseHelper {

    void createNewApiary(Apiary apiary);

    void updateApiary(Apiary apiary);

    void deleteApiary(Apiary apiary);

    void createNewBeehive(Beehive beehive);

    void updateBeehive(Beehive beehive);

    void deleteBeehive(Beehive beehive);

    List<Beehive> getBeehivesFromApiary(Apiary apiary);

    List<Apiary> getAllApiaries();

    List<Beehive> getAllBeehives();

    Apiary getApiaryWithId(int apiaryId);

    Beehive getBeehiveWithId(int beehiveId);

    void closeConnection();
}
