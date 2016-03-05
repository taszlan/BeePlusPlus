package view;

/**
 * Created by atticus on 3/5/16.
 */

import presenter.TestPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestView extends JFrame {

    private JButton malinButton;
    private TestPresenter testPresenter;

    public TestView() {
        System.out.println("Initializing TV");
        initializeUI();
    }

    private void initializeUI() {
        setTitle("MVP Test");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeButtons();
    }

    private void initializeButtons(){
        malinButton = new JButton();
        malinButton.setText("Hej Malin - tekst zainicjalizowany przez TestView");
        malinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                testPresenter.welcome();
            }
        });
        add(malinButton);
    }

    public void updateWelcomeText(String welcomeString){
        malinButton.setText(welcomeString);
    }

    public TestPresenter getTestPresenter() {
        return testPresenter;
    }

    public void setTestPresenter(TestPresenter testPresenter) {
        this.testPresenter = testPresenter;
    }


}
