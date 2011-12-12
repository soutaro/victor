package com.dhemery.victor.tests;

import static com.dhemery.matchers.Matchers.is;
import static com.dhemery.victor.ApplicationAttributes.orientation;
import static com.dhemery.victor.ApplicationDriver.Orientation.LANDSCAPE;
import static com.dhemery.victor.ApplicationDriver.Orientation.PORTRAIT;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.dhemery.victor.ApplicationDriver;
import com.dhemery.victor.PhoneDriver;
import com.dhemery.victor.test.VictorTest;

public class PhoneTests extends VictorTest {
	private ApplicationDriver application;
	private PhoneDriver phone;

	@Before
	public void setUp() {
		application = application();
		phone = phone();		
	}

	@Test
	public void orientationTests() throws InterruptedException, IOException {
		assertThat(application).has(orientation(), equalTo(PORTRAIT));
		assertThat(application).has(orientation()).that(is(equalTo(PORTRAIT)));
		waitUntil(application).has(orientation()).that(is(equalTo(PORTRAIT)));
		when(application).has(orientation()).that(is(equalTo(PORTRAIT)));

		phone.rotateLeft();
		assertThat(application).eventually().has(orientation(), equalTo(LANDSCAPE));

		phone.rotateRight();
		assertThat(application).eventually().has(orientation(), equalTo(PORTRAIT));

		phone.rotateRight();
		assertThat(application).eventually().has(orientation(), equalTo(LANDSCAPE));

		phone.rotateRight();
		assertThat(application).eventually().has(orientation(), equalTo(PORTRAIT));
	}
}
