package com.dhemery.victor.frank;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.dhemery.victor.application.server.ResultsResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Constructs a {@link ResultsResponse} from a properly formatted JSON string.
 * @author Dale Emery
 *
 */
public class ResultsResponseParser implements JsonDeserializer<ResultsResponse> {
	@Override
	public ResultsResponse deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		List<String> results = new ArrayList<String>();
		String reason = "";
		String details = "";

		JsonObject body = element.getAsJsonObject();
		boolean succeeded = body.get("outcome").getAsString().equals("SUCCESS");

		if(succeeded) {
			for(JsonElement result : body.get("results").getAsJsonArray()) {
				if(!result.isJsonNull()) results.add(result.getAsString());
			}
		} else {
			reason = body.get("reason").getAsString();
			details = body.get("details").getAsString();
		}
		return new ResultsResponse(succeeded, results, reason, details);
	}
}