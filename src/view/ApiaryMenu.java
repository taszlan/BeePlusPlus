package view;
import presenter.SimpleGUIPresenter;
import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class ApiaryMenu {
	
	private List<Apiary> listOfApiaries;
	private List<Beehive> listOfBeehives;
	private SimpleGUIPresenter simpleGUIPresenter;

	public void setSimpleGUIPresenter(SimpleGUIPresenter simpleGUIPresenter) {
		this.simpleGUIPresenter = simpleGUIPresenter;
	}
	
	public SimpleGUIPresenter getSimpleGuiPresenter() {
	        return simpleGUIPresenter;
	    }

	private Scanner input = new Scanner(System.in);
	
	public void display(String apiaryName){
		
		
		System.out.println(apiaryName);
		
		listOfApiaries = simpleGUIPresenter.getApiaryList() ;
		//listOfBeehives = simpleGUIPresenter.getBeehivesFromApiary() ;

		for(Beehive a : listOfBeehives){
			
			//1System.out.println(a.getBehiveWithID(a));
		}
		

		
//		int selection = input.nextInt();
		
//		input.hasNextLine();
	
		
//		for (int i = 0; i < listOfBeehives.size(); i++) {
//		 		Beehive element = listOfBeehives.get(i);
		
//		  		int j  	;
//		  		j = 1+i;
	
//				if(selection == j){
//					BeehiveOptions beehiveOptions = new BeehiveOptions();
//					beehiveOptions.display(element.getBeeHiveId());
		
//					break;
//					}
//					j++;
//		}
	}
		
		
	//	System.out.println(
	//			"Select menu: \n" +
	//			"1) Ul \n" +
	//			"2) Exit \n"
//				);
	//int selection = input.nextInt();
//	input.hasNextLine();
	
	
//	switch (selection){
	
	
//	case 1:
//		this.openUlMenu();
//		break;
//	case 2:
//		this.exit();
//		break;
//	default:
//		System.out.println("Niepoprawny wyb�r");
//		break;
//	}
	
	private void openUlMenu(){
		System.out.println("will get Ul menu here");
	}
	private void exit(){
		System.out.println("Exiting...");
		System.exit(1);
	}
	
	
}
