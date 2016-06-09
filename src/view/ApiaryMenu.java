package view;
import presenter.ApiaryPresenter;
import presenter.BeehivePresenter;

import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class ApiaryMenu {
	
	private List<Beehive> listOfApiaryBeehives;
	
	private BeehivePresenter beehivePresenter;
	private ApiaryPresenter apiaryPresenter;
	private Scanner input = new Scanner(System.in);
	private Scanner newBeehiveInput = new Scanner(System.in);
	
	private int index;
	

	public void setBeehivePresenter(BeehivePresenter beehivePresenter) {
		this.beehivePresenter = beehivePresenter;
	}
	
	public BeehivePresenter getSimpleGuiPresenter() {
	        return beehivePresenter;
	    }
	
	public void setApiaryPresenter(ApiaryPresenter apiaryPresenter) {
		this.apiaryPresenter = apiaryPresenter;
	}
	
	public ApiaryPresenter getApiaryPresenter() {
	        return apiaryPresenter;
	    }


	
public void display(Apiary apiaryName){
		
			listOfApiaryBeehives = beehivePresenter.getBeehivesFromApiary(apiaryName);
		
			System.out.println("Pasieka "+ apiaryName.getApiaryName());
			System.out.println(" ---------------- ");

			
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
						apiariesMenu.setApiaryPresenter(new ApiaryPresenter(beehivePresenter.getConnectionSource()));
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
			  			
			  			beehivePresenter.newBeehive(apiaryID, beehiveWeight ,beehiveX, beehiveY);
			  			
			  			ApiaryMenu apiaryMenu = new ApiaryMenu();
						apiaryMenu.setBeehivePresenter(new BeehivePresenter(beehivePresenter.getConnectionSource()));
						apiaryMenu.setApiaryPresenter(new ApiaryPresenter(beehivePresenter.getConnectionSource()));
						apiaryMenu.display(apiaryName);
						
						break;
			  			
			  		}
		
					if(selection == j){
																								
			  			BeehiveOptions beehiveOptions = new BeehiveOptions();
						beehiveOptions.setBeehivePresenter(new BeehivePresenter(beehivePresenter.getConnectionSource()));
						beehiveOptions.setApiaryPresenter(new ApiaryPresenter(beehivePresenter.getConnectionSource()));
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
