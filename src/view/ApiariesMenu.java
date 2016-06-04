package view;
import presenter.ApiaryPresenter;
import presenter.BeehivePresenter;

import java.util.Scanner;
import java.util.List;

import model.Apiary;




public class ApiariesMenu {
	
	private Scanner input = new Scanner(System.in);
	private Scanner newApiaryInput = new Scanner(System.in);
	
	private List<Apiary> listOfApiaries;
	
	private ApiaryPresenter apiaryPresenter;
	private BeehivePresenter beehivePresenter;
	private int index ;

	public void setApiaryPresenter(ApiaryPresenter apiaryPresenter) {
		this.apiaryPresenter = apiaryPresenter;
	}
	
	public ApiaryPresenter getApiaryiPresenter() {
	        return apiaryPresenter;
	    }
	
    
	
	
	
	public void display(){
		
		listOfApiaries = apiaryPresenter.getApiaryList() ;
		
		
		
		

		
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
		  			apiaryPresenter.newApiary(apiaryName, apiaryX, apiaryY);
		  			ApiariesMenu apiariesMenu = new ApiariesMenu();
					apiariesMenu.setApiaryPresenter(new ApiaryPresenter(apiaryPresenter.getConnectionSource()));
					apiariesMenu.display();
					break;
		  			
		  		}
		  		
		  		if(selection == listOfApiaries.size()+2 ){
		  			SimpleGIUMainView simpleGUIMain = new SimpleGIUMainView();
                    simpleGUIMain.setApiaryPresenter(new ApiaryPresenter(apiaryPresenter.getConnectionSource()));
                    simpleGUIMain.display();
				break;
		  		}
	
				if(selection == j){
													 		
			 		apiaryForMenu = listOfApiaries.get(i);
			
					
				ApiaryMenu apiaryMenu = new ApiaryMenu();
				apiaryMenu.setBeehivePresenter(new BeehivePresenter(apiaryPresenter.getConnectionSource()));
				apiaryMenu.display(apiaryPresenter.getApiaryWithID(apiaryForMenu.getApiaryID()));
					
					
		
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
