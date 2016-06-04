package view;
import presenter.ApiaryPresenter;
import presenter.StoragePresenter;
import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;
import model.Storage;

import java.util.ArrayList;

public class StorageMenu {
	
			private Scanner input = new Scanner(System.in);

			

			private List<Beehive> listOfApiariesInStorage;
			private Storage  storage;
			
			private StoragePresenter storagePresenter;
			



			public void setStoragePresenter(StoragePresenter storagePresenter) {
						this.storagePresenter = storagePresenter;
						}

			public StoragePresenter getSimpleGuiPresenter() {
					return storagePresenter;
					}





public void display(){
			
		storage = storagePresenter.getStorage();
		listOfApiariesInStorage = storagePresenter.getBeehivesFromStorage();
				
		
				System.out.println(
						"Wybierz numer menu: \n" +
						"1) Wyœwietl zawartoœæ magazynu \n" +
						"2) Ule w magazynie \n" +
					//	"3) Dodaj ule do magazynu \n" +
					//	"4) Usun ule z magazynu \n" +
						"5) Powrot do glownego menu \n"+
						"6) Exit \n"
					);
		int selection = input.nextInt();
		
		switch (selection){
		
		case 1:
			System.out.println(storage);
			break;
		case 2:
			System.out.println(listOfApiariesInStorage);
			break;
	//	case 3:
		
	//	case 4:
			
		case 5:
			SimpleGIUMainView simpleGUIMain = new SimpleGIUMainView();
            simpleGUIMain.setApiaryPresenter(new ApiaryPresenter(storagePresenter.getConnectionSource()));
            simpleGUIMain.display();
            break;
		case 6:
			this.exit();
			break;
		default:
			System.out.println("Niepoprawny wybï¿½r");
			break;
		}
}
		
		private void exit(){
			System.out.println("Exiting...");
			System.exit(1);
		}	
	
	


}
