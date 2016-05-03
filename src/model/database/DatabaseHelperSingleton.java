package model.database;

import model.database.interfaces.IDatabaseHelper;

/**
 * Created by atticus on 5/3/16.
 */
public class DatabaseHelperSingleton {

    private static IDatabaseHelper iDatabaseHelper;

    private DatabaseHelperSingleton(){

    }

    public static IDatabaseHelper getDatabaseHelper(){
        if(iDatabaseHelper == null){
            iDatabaseHelper = new DatabaseHelper();
        }

        return iDatabaseHelper;
    }
}
