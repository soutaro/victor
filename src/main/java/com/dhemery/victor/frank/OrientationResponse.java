package com.dhemery.victor.frank;

/**
 * A response to a Frank server orientation() request.
 * @author Dale Emery
 *
 */
public class OrientationResponse {
	private final String orientation;
	
	public OrientationResponse(String orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * @return the application's orientation, as reported by the application server.
	 */
	public String orientation() { return orientation; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[orientationResponse:");
		builder.append(String.format("[orientation:%s]", orientation()));
		builder.append("]");
		return builder.toString();
	}
}
