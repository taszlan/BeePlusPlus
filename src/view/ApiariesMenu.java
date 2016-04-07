package view;
import presenter.SimpleGUIPresenter;
import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class ApiariesMenu {
	
	private Scanner input = new Scanner(System.in);
	
	private List<Apiary> listOfApiaries;
	private SimpleGUIPresenter simpleGUIPresenter;

	public void setSimpleGUIPresenter(SimpleGUIPresenter simpleGUIPresenter) {
		this.simpleGUIPresenter = simpleGUIPresenter;
	}
	
	public SimpleGUIPresenter getSimpleGuiPresenter() {
	        return simpleGUIPresenter;
	    }
	
    
	
	
	
	public void display(){
		
		listOfApiaries = simpleGUIPresenter.getApiaryList() ;

		for(Apiary a : listOfApiaries){
			System.out.println(a.getApiaryName());
		}
		
//		System.out.println(
//				"Select menu: \n" +
//				"1) Pasieki \n" +
//				"2) Exit \n"
//				);
	int selection = input.nextInt();
	input.hasNextLine();
	
	
	switch (selection){
	
	case 1:
		ApiaryMenu apiaryMenu = new ApiaryMenu();
		apiaryMenu.display();
		break;
	
	case 2:
		this.exit();
		break;
	default:
		System.out.println("Niepoprawny wybór");
		break;
	
	}
	}
	private void exit(){
		System.out.println("Exiting...");
		System.exit(1);
	}
	
	
	

}
