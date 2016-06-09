package view;

import presenter.ApiaryPresenter;
import presenter.StoragePresenter;

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
	private ApiaryPresenter apiaryPresenter;
	private StoragePresenter storagePresenter;

	public void setApiaryPresenter(ApiaryPresenter apiaryPresenter) {
		this.apiaryPresenter = apiaryPresenter;
	}
	
	public ApiaryPresenter getApiaryPresenter() {
	        return apiaryPresenter;
	    }
	
	public void setStoragePresenter(StoragePresenter storagePresenter) {
		this.storagePresenter = storagePresenter;
		}

	public StoragePresenter getStoragePresenter() {
	return storagePresenter;
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
			apiariesMenu.setApiaryPresenter(new ApiaryPresenter(apiaryPresenter.getConnectionSource()));

			apiariesMenu.display();
			break;
		case 2:
			StorageMenu storageMenu = new StorageMenu();
			storageMenu.setStoragePresenter(new StoragePresenter(apiaryPresenter.getConnectionSource()));
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
