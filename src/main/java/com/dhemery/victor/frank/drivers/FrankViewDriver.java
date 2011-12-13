package com.dhemery.victor.frank.drivers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;

import com.dhemery.victor.Query;
import com.dhemery.victor.ViewDriver;
import com.dhemery.victor.frank.FrankClient;
import com.dhemery.victor.frank.Operation;
import com.dhemery.victor.frank.ResultsResponse;

/**
 * A view driver that interacts with a view through a Frank server.
 * @author Dale Emery
 *
 */
public class FrankViewDriver implements ViewDriver {
	private final FrankClient frank;
	private final Query query;

	/**
	 * @param frank a Frank client that can interact with this view.
	 * @param query a query that identifies the views driven by this driver.
	 */
	public FrankViewDriver(FrankClient frank, Query query) {
		this.frank = frank;
		this.query = query;
	}

	private ResultsResponse call(String method, String...arguments) throws IOException {
		return perform(new Operation(method, arguments));
	}

	@Override
	public void flash() throws IOException {
		call("flash");
	}

	@Override
	public boolean isPresent() {
		ResultsResponse response = property("accessibilityLabel");
		if(!response.succeeded()) return false;
		return isSingular(response.results());
	}

	@Override
	public boolean isVisible() {
		ResultsResponse response = property("isHidden");
		if(!response.succeeded()) return false;
		List<String> results = response.results();
		return isSingular(results) && isFalse(results.get(0));
	}

	public ResultsResponse property(String property) {
		return perform(new Operation(property));
	}

	private boolean isFalse(String result) {
		return !Boolean.parseBoolean(result);
	}

	private boolean isSingular(List<String> results) {
		return results.size() == 1;
	}

	private ResultsResponse perform(Operation operation) {
		ResultsResponse response;
		try {
			response = frank.perform(query, operation);
		} catch (IOException e) {
			response = new ResultsResponse(false, new ArrayList<String>(), null, null);
		}
		return response;
	}

	@Override
	public Query query() {
		return query;
	}

	@Override
	public void touch() throws IOException {
		call("touch");
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(toString());
	}

	@Override
	public String toString() {
		return query().toString();
	}

}
