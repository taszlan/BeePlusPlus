package view;

import presenter.SimpleGUIPresenter;
import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class BeehiveOptions {
	
	private SimpleGUIPresenter simpleGUIPresenter;
	private Beehive behiveForDetails;

	public void setSimpleGUIPresenter(SimpleGUIPresenter simpleGUIPresenter) {
		this.simpleGUIPresenter = simpleGUIPresenter;
	}
	
	public SimpleGUIPresenter getSimpleGuiPresenter() {
	        return simpleGUIPresenter;
	    }
	
	public void display(int beehiveID){
		
		behiveForDetails = simpleGUIPresenter.getBeehiveWithID(beehiveID);
		System.out.println("Ul printout");
		System.out.println(behiveForDetails);
	
	}
	
}
