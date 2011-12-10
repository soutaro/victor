package com.dhemery.victor.tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.dhemery.poller.PollTimeoutException;
import com.dhemery.victor.ViewDriver;
import com.dhemery.victor.test.VictorTest;
import com.dhemery.victor.test.fixtures.DetailDisplay;
import com.dhemery.victor.test.fixtures.MasterDisplay;

public class ApplicationTests extends VictorTest {
	private MasterDisplay master;
	private DetailDisplay detail;
	private ViewDriver masterView;
	private ViewDriver detailLabel;
	private ViewDriver detailView;
	private ViewDriver masterButton;

	@Before
	public void setUp() {
		master = new MasterDisplay(application());
		detail = new DetailDisplay(application());		
		masterView = master.masterView();
		detailLabel = master.detailLabel();
		detailView = detail.detailView();
		masterButton = detail.masterButton();
	}

	@Test
	public void navigation() throws IOException, PollTimeoutException {
		verifyThat(masterView).eventually().isPresent();
		verifyThat(masterView).eventually().isVisible();
		when(masterView).isPresent().flash();
		verifyThat(detailLabel).eventually().isPresent();
		verifyThat(detailLabel).eventually().isVisible();
		when(detailLabel).isPresent().flash();

		when(detailLabel).isPresent().touch();

		verifyThat(detailView).eventually().isPresent();
		verifyThat(detailView).eventually().isVisible();
		when(detailView).isPresent().flash();
		verifyThat(masterButton).eventually().isPresent();
		verifyThat(masterButton).eventually().isVisible();
		when(masterButton).isPresent().flash();

		when(masterButton).isPresent().touch();

		verifyThat(masterView).eventually().isPresent();
		verifyThat(masterView).eventually().isVisible();
		when(masterView).isPresent().flash();
		verifyThat(detailLabel).eventually().isPresent();
		verifyThat(detailLabel).eventually().isVisible();
		when(detailLabel).isPresent().flash();
	}
}
