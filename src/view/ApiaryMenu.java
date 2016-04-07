package view;
import presenter.SimpleGUIPresenter;
import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class ApiaryMenu {

	private Scanner input = new Scanner(System.in);
	
	public void display(){
		
		System.out.println(
				"Select menu: \n" +
				"1) Ul \n" +
				"2) Exit \n"
				);
	int selection = input.nextInt();
	input.hasNextLine();
	
	
	switch (selection){
	
	
	case 1:
		this.openUlMenu();
		break;
	case 2:
		this.exit();
		break;
	default:
		System.out.println("Niepoprawny wyb�r");
		break;
	}
	}
	private void openUlMenu(){
		System.out.println("will get Ul menu here");
	}
	private void exit(){
		System.out.println("Exiting...");
		System.exit(1);
	}
	
	
}
