package view;
import presenter.SimpleGUIPresenter;

import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class ApiaryMenu {
	
	private List<Beehive> listOfApiaryBeehives;
	private List<Apiary> listOfApiaries;
	private SimpleGUIPresenter simpleGUIPresenter;
	
	private int apiaryIDTest;
	private int temp;
	private int test;
	private int i;
	private int j;
	private Apiary apiary;

	public void setSimpleGUIPresenter(SimpleGUIPresenter simpleGUIPresenter) {
		this.simpleGUIPresenter = simpleGUIPresenter;
	}
	
	public SimpleGUIPresenter getSimpleGuiPresenter() {
	        return simpleGUIPresenter;
	    }

//	private Scanner input = new Scanner(System.in);
	
public void display(Apiary apiaryName){
	

	//	Apiary apiaryTest = new Apiary();
	//	apiaryTest = apiaryName;
		System.out.println(apiaryName);
	//	System.out.println(apiaryTest);
	
	
		listOfApiaryBeehives = simpleGUIPresenter.getBeehivesFromApiary(apiaryName);
				
		
		

	for(Beehive b : listOfApiaryBeehives){
			
	System.out.println(b);
	}
	
	}
	
	

		
	//	int selection = input.nextInt();
		
	//	input.hasNextLine();
	
		
	//	for (int i = 0; i < listOfApiaries.size(); i++) {
	//	 		Apiary element = listOfApiaries.get(i);
		
	//	  		int j  	;
	//	  		j = 1+i;
	
	//			if(selection == j){
	//				ApiaryMenu apiaryMenu = new ApiaryMenu();
	//				apiaryMenu.display(element.getApiaryName());
		
	//				break;
	//				}
	//				j++;
	
	
			



 
	
	

	
	private void exit(){
		System.out.println("Exiting...");
		System.exit(1);
	}
	
	
}
