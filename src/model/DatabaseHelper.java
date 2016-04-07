package model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by atticus on 07.04.16.
 */
public class DatabaseHelper {

    private static DatabaseHelper databaseHelper;

    private DatabaseHelper(){
        String databaseUrl = "jdbc:h2:beeplusplus";
        // create a connection source to our database
        //ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

        //Dao<Apiary, String> accountDao =  DaoManager.createDao(connectionSource, Apiary.class);

        //TableUtils.createTable(connectionSource, Apiary.class);
        //Once we have configured our database objects, we can use them to persist an Account to the database and query for it from the database by its ID:


        // create an instance of Account
        //Account account = new Account();
        //account.setName("Jim Coakley");

        // persist the account object to the database
        //accountDao.create(account);

        // retrieve the account from the database by its id field (name)
        //Account account2 = accountDao.queryForId("Jim Coakley");
        //System.out.println("Account: " + account2.getName());

        // close the connection source
        //connectionSource.close();


    }

    public static DatabaseHelper getDatabaseHelper(){
        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper();
        }

        return databaseHelper;
    }

}
