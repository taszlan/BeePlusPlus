package model;

import com.j256.ormlite.field.DatabaseField;
import model.database.DatabaseHelperSingleton;
import model.database.interfaces.IDatabaseHelper;

import java.util.Date;

/**
 * Created by atticus on 5/1/16.
 */
public class Queen {
    public static final String QUEEN_ID = "QUEEN_ID";

    @DatabaseField(generatedId = true, columnName = QUEEN_ID)
    int queenId;
    @DatabaseField
    String queenRace;
    @DatabaseField
    String queenOrigin;
    @DatabaseField
    Date queenBirthDate;

    public Queen(){

    }

    public Queen(String queenRace, String queenOrigin, Date queenBirthDate) {
        this.queenRace = queenRace;
        this.queenOrigin = queenOrigin;
        this.queenBirthDate = queenBirthDate;

        IDatabaseHelper databaseHelper = DatabaseHelperSingleton.getDatabaseHelper();
        databaseHelper.createNewQueen(this);
    }

    public int getQueenId() {
        return queenId;
    }

    public String getQueenRace() {
        return queenRace;
    }

    public void setQueenRace(String queenRace) {
        this.queenRace = queenRace;
    }

    public String getQueenOrigin() {
        return queenOrigin;
    }

    public void setQueenOrigin(String queenOrigin) {
        this.queenOrigin = queenOrigin;
    }

    public Date getQueenBirthDate() {
        return queenBirthDate;
    }

    public void setQueenBirthDate(Date queenBirthDate) {
        this.queenBirthDate = queenBirthDate;
    }

    @Override
    public String toString() {
        return "Queen{" +
                "queenId=" + queenId +
                ", queenRace='" + queenRace + '\'' +
                ", queenOrigin='" + queenOrigin + '\'' +
                ", queenBirthDate=" + queenBirthDate +
                '}';
    }
}
