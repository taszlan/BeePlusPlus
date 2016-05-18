package view;
import presenter.SimpleGUIPresenter;
import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class ApiariesMenu {
	
	private Scanner input = new Scanner(System.in);
	private Scanner newApiaryInput = new Scanner(System.in);
	
	private List<Apiary> listOfApiaries;
	
	private SimpleGUIPresenter simpleGUIPresenter;
	
	private int index ;

	public void setSimpleGUIPresenter(SimpleGUIPresenter simpleGUIPresenter) {
		this.simpleGUIPresenter = simpleGUIPresenter;
	}
	
	public SimpleGUIPresenter getSimpleGuiPresenter() {
	        return simpleGUIPresenter;
	    }
	
    
	
	
	
	public void display(){
		
		listOfApiaries = simpleGUIPresenter.getApiaryList() ;
		
		

		
		index = 1;
		for(Apiary a : listOfApiaries){
			
			System.out.println(String.valueOf(index++)+a.getApiaryName());
		}
		
		System.out.println(String.valueOf(index++)+"Dodaj Pasieke");
		System.out.println(String.valueOf(index++)+"Powrott do glownego Menu");
		
		int selection = input.nextInt();
		
		
	
		
		for (int i = 0; i < listOfApiaries.size(); i++) {
		 		Apiary apiaryForMenu = new Apiary();
		 		
		 		apiaryForMenu = listOfApiaries.get(i);
		
		  		int j  	;
		  		j = 1+i;
		  	
		  		if(selection == listOfApiaries.size()+1 ){ 
		  			
		  			System.out.println("Podaj Nazw�pasieki");
		  			String apiaryName = newApiaryInput.nextLine();
		  			

		  	
		 
		  			System.out.println("Podaj X pasieki");
		  			int apiaryX = newApiaryInput.nextInt();
		  			input.hasNextLine();
		  			System.out.println("Podaj Y pasieki");
		  			int apiaryY = newApiaryInput.nextInt();
		  			newApiaryInput.hasNextLine();
		  			simpleGUIPresenter.newApiary(apiaryName, apiaryX, apiaryY);
		  			ApiariesMenu apiariesMenu = new ApiariesMenu();
					apiariesMenu.setSimpleGUIPresenter(new SimpleGUIPresenter(simpleGUIPresenter.getConnectionSource()));
					apiariesMenu.display();
					break;
		  			
		  		}
		  		
		  		if(selection == listOfApiaries.size()+2 ){
		  			SimpleGIUMainView simpleGUIMain = new SimpleGIUMainView();
                    simpleGUIMain.setSimpleGUIPresenter(new SimpleGUIPresenter(simpleGUIPresenter.getConnectionSource()));
                    simpleGUIMain.display();
				break;
		  		}
	
				if(selection == j){
													 		
			 		apiaryForMenu = listOfApiaries.get(i);
			
					
					ApiaryMenu apiaryMenu = new ApiaryMenu();
					apiaryMenu.setSimpleGUIPresenter(new SimpleGUIPresenter(simpleGUIPresenter.getConnectionSource()));
					apiaryMenu.display(simpleGUIPresenter.getApiaryWithID(apiaryForMenu.getApiaryID()));
					
					
		
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
