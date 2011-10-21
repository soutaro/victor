package com.dhemery.victor.elements.polled;

import com.dhemery.poller.Condition;
import com.dhemery.victor.elements.ElementCommands;
import com.dhemery.victor.elements.ios.IOSElement;

public class PolledIOSElementCommands extends Polled implements ElementCommands {
	private final IOSElement element;
	private final Condition ready;

	public PolledIOSElementCommands(IOSElement element, Condition condition) {
		ready = condition;
		this.element = element;
	}

	public void whenReady() {
		pollUntil(ready);
	}

	@Override
	public void touch() {
		whenReady();
		element.touch();
	}
}