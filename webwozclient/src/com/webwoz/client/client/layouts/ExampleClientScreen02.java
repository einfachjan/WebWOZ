package com.webwoz.client.client.layouts;

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
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.client.client.DatabaseAccess;
import com.webwoz.client.client.DatabaseAccessAsync;
import com.webwoz.client.client.Screen;

public class ExampleClientScreen02 implements Screen {

	// Panels
	private VerticalPanel layoutPanel = new VerticalPanel();
	private VerticalPanel outputPanel = new VerticalPanel();
	private HTML outputText = new HTML("");

	// Refresh interval
	private static final int REFRESH_INTERVAL = 1000;
	private Timer refreshTimer;
	private boolean reload;

	// RPC
	private DatabaseAccessAsync databaseAccessSvc = GWT
			.create(DatabaseAccess.class);

	// Other variables
	private int currentID = 0;
	private int user;
	private int exp;
	private String origText;
	private String audioFile;
	private String mmFile;

	public ExampleClientScreen02(int userId, int expId) {
		user = userId;
		exp = expId;
		reload = true;
		outputText.setWidth("300px");

		outputPanel.add(outputText);
		layoutPanel.add(outputPanel);
		layoutPanel.setStyleName("textoutput");

		// turn reload on
		reload = true;

		// Start timer to refresh list automatically.
		refreshTimer = new Timer() {
			@Override
			public void run() {
				if (reload) {
					getData();
				}
			}
		};

		// run refresh
		refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	}

	public VerticalPanel getScreen() {
		return layoutPanel;
	}

	private void getData() {
		String sql = "Select * from output where experiment = " + exp
				+ " and receiver = " + user
				+ " and played = 0 order by id desc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					reload = false;
					currentID = Integer.parseInt(result[0][0]);
					origText = result[0][1];
					audioFile = result[0][2];
					mmFile = result[0][3];
					String output = "";

					outputText.setHTML("");

					// text
					if (!origText.equals("null")) {
						output = output + origText;
					}

					// audio
					if (!audioFile.equals("null")) {
						output = output + audioFile;
					}

					// multimedia
					if (!mmFile.equals("null")) {

					}

					outputText.setHTML(output);

					updatePlaylist();

				} else {

				}

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void updatePlaylist() {

		String sql = "Update output set played = 1 where id = " + currentID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {
				reload = true;
			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	public void stopReload() {
		refreshTimer.cancel();
		reload = false;
	}

}
