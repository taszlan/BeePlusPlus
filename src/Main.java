import model.Apiary;
import model.Beehive;
import model.database.DatabaseHelper;
import model.database.IDatabaseHelper;
import presenter.SimpleGUIPresenter;
import view.SimpleGIUMainView;

import java.awt.*;

/**
 * Created by atticus on 3/5/16.
 */
public class Main {

    public static void main(String args[]){
        System.out.println("Main");

        final IDatabaseHelper databaseHelper = DatabaseHelper.getDatabaseHelper();

        //Testowanie zapytań do bazy danych
        databaseHelper.createNewApiary(new Apiary("Testowa pasieka", 10, 10));

        for(Apiary a : databaseHelper.getAllApiaries()){
            System.out.println(a);
        }

        for (Beehive beehive : databaseHelper.getBeehivesFromApiary(databaseHelper.getAllApiaries().get(0).getApiaryID())){
            System.out.println(beehive);
        }

        //Nie wiem czemu uruchamiają przez to EventQueue, trzeba będize rozkminić co to za czort :D
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
//                TestView testView = new TestView();
//                testView.setTestPresenter(new TestPresenter(new TestModel(), testView));
//                testView.setVisible(true);

                SimpleGIUMainView simpleGUIMain = new SimpleGIUMainView();
                simpleGUIMain.setSimpleGUIPresenter(new SimpleGUIPresenter());
                simpleGUIMain.display();
            }
        });


        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("BeePlusPlus shutting down");
                databaseHelper.closeConnection();
            }
        });
    }
}
