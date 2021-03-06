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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SettingsMTin implements Screen {

	private VerticalPanel layoutPanel = new VerticalPanel();
	private HorizontalPanel settingsPanel = new HorizontalPanel();
	private HTML heading = new HTML("MT");
	private ListBox compList = new ListBox();
	private ListBox srcLangList = new ListBox();
	private ListBox trgLangList = new ListBox();
	private ListBox corrList = new ListBox();
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

	private Label srcLangLabel = new Label("Source Language");
	private Label trgLangLabel = new Label("Target Language");
	private Label corrLabel = new Label("Correction Mode");

	// RPC
	private DatabaseAccessAsync databaseAccessSvc = GWT
			.create(DatabaseAccess.class);
	private ComponentFactoryAsync componentFactorySvc = GWT
			.create(ComponentFactory.class);

	public SettingsMTin(int exp, String path, String ss, String mts,
			String mtt, String mtos, String mtot, String asrl, int w) {

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

		compList.setStyleName("drp");

		srcLangList.clear();
		srcLangList.setStyleName("drp");
		srcLangList.addItem("de", "de");
		srcLangList.addItem("en", "en");
		srcLangList.addItem("es", "es");
		srcLangList.addItem("fr", "fr");
		srcLangList.addItem("it", "it");

		trgLangList.clear();
		trgLangList.setStyleName("drp");
		trgLangList.addItem("de", "de");
		trgLangList.addItem("en", "en");
		trgLangList.addItem("es", "es");
		trgLangList.addItem("fr", "fr");
		trgLangList.addItem("it", "it");

		corrList.clear();
		corrList.setStyleName("drp");
		corrList.addItem("Off");
		corrList.addItem("Wizard Correction");
		corrList.addItem("N-best List");

		// build layout
		heading.setWidth("30px");
		settingsPanel.clear();
		settingsPanel.add(heading);
		settingsPanel.add(compList);
		settingsPanel.add(srcLangList);
		settingsPanel.add(srcLangLabel);
		settingsPanel.add(trgLangList);
		settingsPanel.add(trgLangLabel);
		settingsPanel.add(corrList);
		settingsPanel.add(corrLabel);
		layoutPanel.clear();
		layoutPanel.add(settingsPanel);
		layoutPanel.addStyleName("compSet");

		// list of components
		compList.clear();
		compList.addItem("Off", "0");
		compList.addItem("Bing Translate", "1");
		// stop Google translate support
		// compList.addItem("Google Translate", "2");

		// get settings
		getSettings();

		// component handler
		compList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				if (compList.isItemSelected(0)) {
					// turn on correction possibility for ASR
					WebWOZWizard.changeVisiblility(5);
				} else {
					// change correction list item
					corrList.setItemSelected(0, true);
				}

				changeComponent();
			}

		});

		corrList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeMode(corrList.getSelectedIndex());
				wiz = corrList.getSelectedIndex();
			}
		});

		srcLangList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLang(1);
			}

		});

		trgLangList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLang(2);
			}

		});

	}

	public VerticalPanel getScreen() {
		// overload Method!
		return null;
	}

	public VerticalPanel getScreen(int comp, int mode, int id) {
		compID = id;
		// load the right Component
		compList.setItemSelected(comp, true);
		// get visibility
		checkVisibility();
		corrList.setItemSelected(mode, true);
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
				checkVisibility();
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void changeMode(final int newMode) {

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

				changeWizardInteraction(newMode);

			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void changeWizardInteraction(final int m) {

		String sql = "";

		switch (m) {
		case 0:
			sql = "Update experiment set wizardcorrection = 0, nbestlist = 0 where id = "
					+ expId;
			break;
		case 1:
			sql = "Update experiment set wizardcorrection = 1, nbestlist = 0 where id = "
					+ expId;
			break;
		case 2:
			sql = "Update experiment set wizardcorrection = 0, nbestlist = 1 where id = "
					+ expId;
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

				if (m > 0) {
					WebWOZWizard.changeVisiblility(4);
				} else {
					WebWOZWizard.changeVisiblility(5);
				}

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

	private void changeLang(int type) {

		String sql = "";

		switch (type) {

		case 1:
			sql = "Update experiment set mtinsrc = '"
					+ srcLangList.getValue(srcLangList.getSelectedIndex())
					+ "' where id = " + expId;
			mtSrc = srcLangList.getValue(srcLangList.getSelectedIndex());

			break;
		case 2:
			sql = "Update experiment set mtintrg = '"
					+ trgLangList.getValue(trgLangList.getSelectedIndex())
					+ "' where id = " + expId;
			mtTrg = trgLangList.getValue(trgLangList.getSelectedIndex());
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
				// reloadLTCs();
				reloadSettings();
			}
		};

		databaseAccessSvc.storeData(sql, callback);
	}

	public void stopReload() {

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

			public void onSuccess(String[][] result) {

				// SRC Lang
				for (int i = 0; i < srcLangList.getItemCount(); i++) {
					if (result[0][9].equals(srcLangList.getItemText(i))) {
						srcLangList.setItemSelected(i, true);
						break;
					}
				}

				// TRG Lang
				for (int i = 0; i < trgLangList.getItemCount(); i++) {
					if (result[0][10].equals(trgLangList.getItemText(i))) {
						trgLangList.setItemSelected(i, true);
						break;
					}
				}

				// wizard setting
				if (result[0][18].equals("1")) {
					corrList.setItemSelected(2, true);
				} else {
					if (result[0][19].equals("1")) {
						corrList.setItemSelected(1, true);
					} else {
						corrList.setItemSelected(0, true);
					}
				}

			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
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

	private void checkVisibility() {
		if (compList.getSelectedIndex() == 0) {
			srcLangList.setVisible(false);
			trgLangList.setVisible(false);
			srcLangLabel.setVisible(false);
			trgLangLabel.setVisible(false);
			corrList.setVisible(false);
			corrLabel.setVisible(false);
			settingsPanel.setWidth("180px");
		} else {
			srcLangList.setVisible(true);
			trgLangList.setVisible(true);
			srcLangLabel.setVisible(true);
			trgLangLabel.setVisible(true);
			corrList.setVisible(true);
			corrLabel.setVisible(true);
			settingsPanel.setWidth("670px");
		}
	}

	public void turnOffComponent() {
		String sql = "Update experimentcomponent set component = 0 and componentmode = 0 where id = "
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
				turnOnVisibility(0);
				compList.setItemSelected(0, true);
				// get visibility
				checkVisibility();
			}
		};

		databaseAccessSvc.storeData(sql, callback);

	}

	public void turnOnVisibility(int mode) {
		if (mode == 0) {
			layoutPanel.setVisible(false);
		} else {
			layoutPanel.setVisible(true);
		}

	}

	public void changeVisibility() {

	}

}
