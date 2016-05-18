package view;

import presenter.SimpleGUIPresenter;
import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;
import view.ApiariesMenu;

/**
 * Created by malinuss on 24.03.16.
 */
public class SimpleGIUMainView {


	private List<Apiary> listOfApiaries;
	private SimpleGUIPresenter simpleGUIPresenter;

	public void setSimpleGUIPresenter(SimpleGUIPresenter simpleGUIPresenter) {
		this.simpleGUIPresenter = simpleGUIPresenter;
	}
	
	public SimpleGUIPresenter getSimpleGuiPresenter() {
	        return simpleGUIPresenter;
	    }


	    
	    private Scanner input = new Scanner(System.in);
	    public void display(){
			
			System.out.println(
					"Select menu: \n" +
					"1) Pasieki \n" +
					"2) Magazyn \n" +
					"3) Exit \n"
					);
		int selection = input.nextInt();
		input.hasNextLine();
		
		
		switch (selection){
		
		case 1:
			ApiariesMenu apiariesMenu = new ApiariesMenu();
			apiariesMenu.setSimpleGUIPresenter(new SimpleGUIPresenter(simpleGUIPresenter.getConnectionSource()));
			apiariesMenu.display();
			break;
		case 2:
			StorageMenu storageMenu = new StorageMenu();
			storageMenu.setSimpleGUIPresenter(new SimpleGUIPresenter(simpleGUIPresenter.getConnectionSource()));
			storageMenu.display();
			break;
		case 3:
			this.exit();
			break;
		default:
			System.out.println("Niepoprawny wyb�r");
			break;
		
		}
		}
		private void exit(){
			System.out.println("Exiting...");
			System.exit(1);
		}
		
		private void openMagazynMenu(){
			System.out.println("will get Magazyn menu here");
		}
		

	 
}
