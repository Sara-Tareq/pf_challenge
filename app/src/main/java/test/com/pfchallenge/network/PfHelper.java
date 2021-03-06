package test.com.pfchallenge.network;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import test.com.pfchallenge.activities.MainView;
import test.com.pfchallenge.entities.Property;
import test.com.pfchallenge.entities.PropertyFinder;


public class PfHelper {
	private static final String API_BASE_URL = "https://www.propertyfinder.ae/mobileapi?";
	private static final String PG_PARAM = "page=";
	private static final String ORDER_PARAM = "&order=";

	/**
	 * requests the properties list
	 *
	 * @param mainView handles the view interactions
	 * @param context  calling activity
	 * @param pageNum  the page number to be requested
	 * @param order    the sorting required for the properties list
	 **/
	public static void getPropertiesList(MainView mainView, Context context, int pageNum, String order) {
		PfRequestHandler.getInstance(context).requestPropertiesList(getApiUrl(pageNum, order), getPropertiesListResponseHandler(mainView));
	}

	/**
	 * provides the response handler
	 *
	 * @param mainView handles the view interactions
	 */
	private static ResponseHandler getPropertiesListResponseHandler(final MainView mainView) {
		return new ResponseHandler() {
			@Override
			public void onSuccess(Object response) {
				ArrayList<Property> properties = ((PropertyFinder) response).getProperties();
				if (properties != null && !properties.isEmpty()) {
					mainView.updateList(properties,((PropertyFinder) response).getTotal());
				}
			}

			@Override
			public void onError(Exception e) {
				mainView.showError();
			}
		};
	}

	/**
	 * generates the api url to be requested
	 *
	 * @param pageNum the page number to be requested
	 * @param order   the sorting required for the properties list
	 */
	private static String getApiUrl(int pageNum, String order) {
		String url = API_BASE_URL.concat(PG_PARAM).concat(String.valueOf(pageNum));
		return order != null && !order.isEmpty() ? url.concat(ORDER_PARAM).concat(order) : url;
	}
}
