package view;

import presenter.ApiaryPresenter;
import presenter.BeehivePresenter;
import java.util.Scanner;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

public class BeehiveOptions {
	
	private BeehivePresenter beehivePresenter;
	private ApiaryPresenter apiaryPresenter;
	private Beehive behiveForDetails;
	
	

	public void setBeehivePresenter(BeehivePresenter beehivePresenter) {
		this.beehivePresenter = beehivePresenter;
	}
	
	public BeehivePresenter getSimpleGuiPresenter() {
	        return beehivePresenter;
	    }
	
	public void setApiaryPresenter(ApiaryPresenter apiaryPresenter) {
		this.apiaryPresenter = apiaryPresenter;
	}
	
	public ApiaryPresenter getApiaryiPresenter() {
	        return apiaryPresenter;
	    }
	
	public void display(int beehiveID){
		
		behiveForDetails = beehivePresenter.getBeehiveWithID(beehiveID);
		System.out.println("Ul printout");
		System.out.println(behiveForDetails);
		System.out.println(
				"---------------- \n" +
				"Powrót do menu pasieki \n" +
				"-------------------"
			
				);
		
		
		
	

		ApiaryMenu apiaryMenu = new ApiaryMenu();
		apiaryMenu.setBeehivePresenter(new BeehivePresenter(beehivePresenter.getConnectionSource()));
		apiaryMenu.display(apiaryPresenter.getApiaryWithID(behiveForDetails.getApiaryId()));
	
	}
	
}
