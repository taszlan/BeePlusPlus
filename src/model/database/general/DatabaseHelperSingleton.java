package model.database.general;

import model.database.general.interfaces.IDatabaseHelper;

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
