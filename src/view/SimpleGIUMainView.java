package view;

import presenter.SimpleGUIPresenter;
import java.util.List;

import model.Apiary;
import model.Beehive;

import java.util.ArrayList;

/**
 * Created by malinuss on 24.03.16.
 */
public class SimpleGIUMainView {


	private List<Apiary> listOfApiaries;
	private SimpleGUIPresenter simpleGUIPresenter;

	public void setSimpleGUIPresenter(SimpleGUIPresenter simpleGUIPresenter) {
		this.simpleGUIPresenter = simpleGUIPresenter;
	}
	
	public SimpleGUIPresenter getSimpleGuiPresenter() {
	        return simpleGUIPresenter;
	    }

	    public void setSimpleGuiPresenter(SimpleGUIPresenter simpleGuiPresenter) {
	        this.simpleGUIPresenter = simpleGUIPresenter;
	    }
	 public void showMenu(){
		 listOfApiaries = simpleGUIPresenter.getApiaryList() ;
		
		 
		 for(Apiary a : listOfApiaries){
			 System.out.println(a.getApiaryName());
			 
			
			 for( Beehive b : a.getListOfBehives()){
				 System.out.println(b.getId()); 
				 
			 }
			 
			
		 }
		 
		 
	 }
	 
}
