import model.TestModel;
import presenter.TestPresenter;
import view.TestView;

import java.awt.*;

/**
 * Created by atticus on 3/5/16.
 */
public class Main {

    public static void main(String args[]){
        System.out.println("Main");

        //Nie wiem czemu uruchamiają przez to EventQueue, trzeba będize rozkminić co to za czort :D
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                TestView testView = new TestView();
                testView.setTestPresenter(new TestPresenter(new TestModel(), testView));
                testView.setVisible(true);
            }
        });
    }
}
