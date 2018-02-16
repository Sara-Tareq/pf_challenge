package test.com.pfchallenge;

import java.util.ArrayList;

import test.com.pfchallenge.entities.Property;

public interface MainView {

	void showProgress();
	void hideProgress();
	void updateList(ArrayList<Property> properties);
}
