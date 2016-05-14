package model.database.access;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import model.Apiary;
import model.Beehive;
import model.database.access.interfaces.DatabaseAccessObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by atticus on 5/12/16.
 */
public class DecoratedBeehiveDAO extends GenericDAODecorator{

    public DecoratedBeehiveDAO(DatabaseAccessObject DAOToBeDecorated){
        super(DAOToBeDecorated);
    }

    public List<Beehive> getBeehivesFromApiary(Apiary apiary){
        List<Beehive> beehiveList= new ArrayList<>();
        try {
            QueryBuilder<Beehive, Integer> beehiveIntegerQueryBuilder= getGenericDao().queryBuilder();
            PreparedQuery<Beehive> preparedQuery = beehiveIntegerQueryBuilder.where().eq(Beehive.APIARY_ID, apiary.getApiaryID()).prepare();
            beehiveList = getGenericDao().query(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }

        //Usuwa z listy Ule dla których isInStorage = true;
        Iterator<Beehive> beehiveIterator = beehiveList.iterator();
        Beehive beehive;
        while(beehiveIterator.hasNext()){
            beehive = beehiveIterator.next();
            if(beehive.isInStorage()){
                beehiveIterator.remove();
            }
        }
        return beehiveList;
    }

}
