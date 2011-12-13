package com.dhemery.victor.frank.frankly;

import com.dhemery.victor.frank.Operation;

/**
 * <p>A Frank request to invoke an operation on an application's "application delegate."</p>
 * @author Dale Emery
 */
public class PerformApplicationOperation extends FranklyRequest {
	public PerformApplicationOperation(FranklyOperation command) {
		super("app_exec", command);
	}

	/**
	 * @param operation the operation for the application delegate to perform.
	 */
	public PerformApplicationOperation(Operation operation) {
		this(new FranklyOperation(operation));
	}
}