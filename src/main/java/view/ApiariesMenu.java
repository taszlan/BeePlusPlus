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
		

		
		int selection = input.nextInt();
		
		input.hasNextLine();
	
		
		for (int i = 0; i < listOfApiaries.size(); i++) {
		 		Apiary element = new Apiary();
		 		
		 		element = listOfApiaries.get(i);
		
		  		int j  	;
		  		j = 1+i;
		  		
		  		System.out.println("apitest id 1 ,"+element.getApiaryID());
	
				if(selection == j){
					ApiaryMenu apiaryMenu = new ApiaryMenu();
					System.out.println("apiiary id 2 ,"+element.getApiaryID());
					
					apiaryMenu.display(simpleGUIPresenter.getApiaryWithID(element.getApiaryID()));
					
					
		
					break;
					}
					j++;
	
	
		}	
	

	}
	private void exit(){
		System.out.println("Exiting...");
		System.exit(1);
	}
	
	
	

}
