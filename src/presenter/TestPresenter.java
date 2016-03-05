package presenter;

import model.TestModel;
import view.TestView;

/**
 * Created by atticus on 3/5/16.
 */
public class TestPresenter {
    private TestModel testModel;
    private TestView testView;

    public TestPresenter(TestModel testModel, TestView testView) {
        this.testModel = testModel;
        this.testView = testView;
    }

    public void welcome(){
        testView.updateWelcomeText(testModel.getWelcomeString());
    }
}
