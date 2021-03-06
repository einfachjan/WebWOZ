package com.webwoz.wizard.client;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.wizard.client.DatabaseAccess;

public class SettingsClient implements Screen {

	private VerticalPanel layoutPanel = new VerticalPanel();
	private VerticalPanel settingsPanel = new VerticalPanel();
	private HorizontalPanel outputPanel = new HorizontalPanel();
	private CheckBox textChk = new CheckBox("Text Output");
	private CheckBox audioChk = new CheckBox("Audio Output");
	private CheckBox mmChk = new CheckBox("Multi-Media Output");

	// other variables
	private int expId;

	// RPC
	private DatabaseAccessAsync databaseAccessSvc = GWT
			.create(DatabaseAccess.class);

	public SettingsClient(int exp) {
		// store experimentId and mediapath
		expId = exp;
		getSettings();

		// build layout
		outputPanel.clear();
		outputPanel.setWidth("320px");
		outputPanel.add(textChk);
		outputPanel.add(audioChk);
		outputPanel.add(mmChk);

		settingsPanel.clear();
		settingsPanel.add(outputPanel);

		layoutPanel.clear();
		layoutPanel.add(settingsPanel);
		layoutPanel.addStyleName("compSet");

		textChk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				@SuppressWarnings("deprecation")
				boolean checked = ((CheckBox) event.getSource()).isChecked();
				if (checked) {
					changeMode(2, 1);
				} else {
					changeMode(2, 0);
				}
			}
		});

		audioChk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				@SuppressWarnings("deprecation")
				boolean checked = ((CheckBox) event.getSource()).isChecked();
				if (checked) {
					changeMode(3, 1);
				} else {
					changeMode(3, 0);
				}
			}
		});

		mmChk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				@SuppressWarnings("deprecation")
				boolean checked = ((CheckBox) event.getSource()).isChecked();
				if (checked) {
					changeMode(4, 1);
				} else {
					changeMode(4, 0);
				}
			}
		});

	}

	public VerticalPanel getScreen() {
		return layoutPanel;
	}

	private void getSettings() {
		String sql = "Select * from experiment where id = " + expId;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			@SuppressWarnings("deprecation")
			public void onSuccess(String[][] result) {

				// Text
				if (result[0][15].equals("0")) {
					textChk.setChecked(false);
				} else {
					textChk.setChecked(true);
				}

				// Audio
				if (result[0][16].equals("0")) {
					audioChk.setChecked(false);
				} else {
					audioChk.setChecked(true);
				}

				// MM
				if (result[0][17].equals("0")) {
					mmChk.setChecked(false);
				} else {
					mmChk.setChecked(true);
				}

			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void changeMode(int type, int newMode) {

		String sql = "";

		switch (type) {

		case 1:
			sql = "Update experiment set asrin = " + newMode + " where id = "
					+ expId;
			break;
		case 2:
			sql = "Update experiment set textout = " + newMode + " where id = "
					+ expId;
			break;
		case 3:
			sql = "Update experiment set audioout = " + newMode
					+ " where id = " + expId;
			break;
		case 4:
			sql = "Update experiment set mmout = " + newMode + " where id = "
					+ expId;
			break;
		default:
			break;
		}

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {

			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	public void stopReload() {

	}

	public void turnOffComponent() {

	}

	public void changeVisibility() {

	}

}
