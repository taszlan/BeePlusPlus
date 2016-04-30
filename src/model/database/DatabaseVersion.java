package model.database;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by atticus on 4/30/16.
 */
public class DatabaseVersion {
    @DatabaseField (id = true)
    int id;
    @DatabaseField
    int version;

    public DatabaseVersion(){}


    public DatabaseVersion(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
