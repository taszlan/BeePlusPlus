package view;
import presenter.SimpleGUIPresenter;

import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class ApiaryMenu {
	
	private List<Beehive> listOfApiaryBeehives;
	private List<Beehive> listOfBeehives;
	private List<Apiary> listOfApiaries;
	private SimpleGUIPresenter simpleGUIPresenter;
	private Scanner input = new Scanner(System.in);
	private Scanner newBeehiveInput = new Scanner(System.in);
	
	private int index;
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


	
public void display(Apiary apiaryName){
		
			listOfApiaryBeehives = simpleGUIPresenter.getBeehivesFromApiary(apiaryName);
		
		

			
			index = 1;
			for(Beehive a : listOfApiaryBeehives){
				
				System.out.println(String.valueOf(index++)+" Ul NR "+a.getDatabaseId());
			}
			
			System.out.println(String.valueOf(index++)+"Dodaj Ul");
			System.out.println(String.valueOf(index++)+"Powr�t do pasiek");
			
			int selection = input.nextInt();
			
			input.hasNextLine();
		
			
			for (int i = 0; i < listOfApiaryBeehives.size(); i++) {
			 	
			 		Beehive beehiveForMenu = new Beehive();
			 		beehiveForMenu = listOfApiaryBeehives.get(i);
			
			  		int j  	;
			  		j = 1+i;
			  		
			  		if(selection == listOfApiaryBeehives.size()+2 ){ 
			  			
			  			ApiariesMenu apiariesMenu = new ApiariesMenu();
						apiariesMenu.setSimpleGUIPresenter(new SimpleGUIPresenter(simpleGUIPresenter.getConnectionSource()));
						apiariesMenu.display();
						break;
						
					
			  			
			  		}
		
			  	
			  		if(selection == listOfApiaryBeehives.size()+1 ){ 
			  			
			  			int apiaryID = apiaryName.getApiaryID();
			  			double beehiveWeight = 10;
			  			System.out.println("Podaj X Ula");
			  			int beehiveX = newBeehiveInput.nextInt();
			  			
			  			System.out.println("Podaj Y Ula");
			  			int beehiveY = newBeehiveInput.nextInt();
			  			
			  			simpleGUIPresenter.newBeehive(apiaryID, beehiveWeight ,beehiveX, beehiveY);
			  			
			  			ApiaryMenu apiaryMenu = new ApiaryMenu();
						apiaryMenu.setSimpleGUIPresenter(new SimpleGUIPresenter(simpleGUIPresenter.getConnectionSource()));
						apiaryMenu.display(apiaryName);
						
						break;
			  			
			  		}
		
					if(selection == j){
																								
			  			BeehiveOptions beehiveOptions = new BeehiveOptions();
						beehiveOptions.setSimpleGUIPresenter(new SimpleGUIPresenter(simpleGUIPresenter.getConnectionSource()));
						beehiveOptions.display(beehiveForMenu.getDatabaseId());
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
