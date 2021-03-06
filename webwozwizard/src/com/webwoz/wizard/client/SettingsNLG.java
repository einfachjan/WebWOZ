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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SettingsNLG implements Screen {

	private VerticalPanel layoutPanel = new VerticalPanel();
	private HorizontalPanel settingsPanel = new HorizontalPanel();
	private HTML heading = new HTML("NLG");
	private ListBox compList = new ListBox();
	private CheckBox wizardOverride = new CheckBox("Wizard Correction");
	private int compID;

	// other variables
	private int[][] components;
	private int expId;
	private String mediapath;
	private String ssLang;
	private String mtSrc;
	private String mtTrg;
	private String mtoSrc;
	private String mtoTrg;
	private String asrLang;
	private int wiz;

	// RPC
	private DatabaseAccessAsync databaseAccessSvc = GWT
			.create(DatabaseAccess.class);
	private ComponentFactoryAsync componentFactorySvc = GWT
			.create(ComponentFactory.class);

	public SettingsNLG(int exp, String path, String ss, String mts, String mtt,
			String mtos, String mtot, String asrl, int w) {
		// store experimentId
		expId = exp;
		mediapath = path;
		ssLang = ss;
		mtSrc = mts;
		mtTrg = mtt;
		mtoSrc = mtos;
		mtoTrg = mtot;
		asrLang = asrl;
		wiz = w;

		// build layout
		heading.setWidth("30px");
		settingsPanel.clear();
		settingsPanel.add(heading);
		settingsPanel.add(compList);
		settingsPanel.add(wizardOverride);
		layoutPanel.clear();
		layoutPanel.add(settingsPanel);
		layoutPanel.addStyleName("compSet");

		// list of components
		compList.clear();
		compList.addItem("Off", "0");

		// component handler
		compList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeComponent();
			}

		});

		wizardOverride.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				@SuppressWarnings("deprecation")
				boolean checked = ((CheckBox) event.getSource()).isChecked();
				if (checked) {
					changeMode(1);
				} else {
					changeMode(0);
				}
			}
		});

	}

	public VerticalPanel getScreen() {
		// overload Method!
		return null;
	}

	@SuppressWarnings("deprecation")
	public VerticalPanel getScreen(int comp, int mode, int id) {
		compID = id;
		// load the right Component
		compList.setItemSelected(comp, true);
		// load the right mode for the component
		if (mode == 1) {
			wizardOverride.setChecked(true);
		} else {
			wizardOverride.setChecked(false);
		}
		return layoutPanel;
	}

	private void changeComponent() {
		String sql = "Update experimentcomponent set component = "
				+ compList.getValue(compList.getSelectedIndex())
				+ " where id = " + compID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				reloadSettings();
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void changeMode(int newMode) {

		String sql = "Update experimentcomponent set componentmode = "
				+ newMode + " where id = " + compID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				reloadSettings();
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	// reload LTCs
	private void reloadLTCs() {
		String sql = "Select * from experimentcomponent where experimentid = "
				+ expId + " order by rank asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					components = null;
					components = new int[result.length][3];
					for (int i = 0; i < result.length; i++) {
						// add components to local array
						components[i][0] = Integer.parseInt(result[i][2]);
						components[i][1] = Integer.parseInt(result[i][3]);
						components[i][2] = Integer.parseInt(result[i][4]);
					}

					clearComponents();

				} else {
					// System.out.println("No components defined for this experiment!");
				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void addComponents() {

		// Initialize the service remote procedure call
		if (componentFactorySvc == null) {
			componentFactorySvc = GWT.create(ComponentFactory.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {

			}

		};

		componentFactorySvc.pushComponents(components, expId, mediapath,
				ssLang, mtSrc, mtTrg, mtoSrc, mtoTrg, asrLang, wiz, callback);

	}

	private void clearComponents() {

		// Initialize the service remote procedure call
		if (componentFactorySvc == null) {
			componentFactorySvc = GWT.create(ComponentFactory.class);
		}

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Void result) {
				addComponents();
			}

		};

		componentFactorySvc.clearComponents(callback);

	}

	public void stopReload() {

	}

	public void turnOffComponent() {
		String sql = "Update experimentcomponent set component = 0 where id = "
				+ compID;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				reloadSettings();
			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	private void reloadSettings() {

		String sql = "Select * from experiment where id = " + expId;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				// update settings
				ssLang = result[0][8];
				mtSrc = result[0][9];
				mtTrg = result[0][10];
				mtoSrc = result[0][11];
				mtoTrg = result[0][12];
				asrLang = result[0][13];
				reloadLTCs();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	public void changeVisibility() {

	}

}
