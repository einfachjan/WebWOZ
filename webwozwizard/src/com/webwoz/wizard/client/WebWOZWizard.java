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

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webwoz.wizard.client.DatabaseAccess;
import com.webwoz.wizard.client.wizardlayouts.ChooseLayoutInfoScreen;
import com.webwoz.wizard.client.wizardlayouts.HomeScreen;
import com.webwoz.wizard.client.wizardlayouts.DefaultWizardScreen;
import com.webwoz.wizard.client.wizardlayouts.NoLayoutScreen;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebWOZWizard implements EntryPoint, ValueChangeHandler<String> {

	// panels to structure layout
	private VerticalPanel headPanel;
	private HorizontalPanel titlePanel;
	private VerticalPanel contentPanel;
	private VerticalPanel contentWizardPanel;
	private VerticalPanel footPanel;
	private VerticalPanel containerPanel;
	private HorizontalPanel menuPanel;
	private DisclosurePanel menuSettingsPanel;
	private DisclosurePanel menuExperimentsPanel;
	private VerticalPanel wizardScreenPanel;
	private VerticalPanel layoutsPanel;
	private VerticalPanel settingsPanel;
	private VerticalPanel settingsClientOutputPanel;
	private static VerticalPanel settingsClientInputPanel;
	private static VerticalPanel settingsWizardOutputPanel;
	private static VerticalPanel settingsWizardOutMT;
	private HorizontalPanel experimentsPanel;
	private VerticalPanel experimentCollectionPanel;
	private HorizontalPanel loginPanel;
	private static VerticalPanel addExperimentPanel;
	private HorizontalPanel experimentNamePanel;
	private HorizontalPanel experimentLayoutPanel;
	private HorizontalPanel experimentSavePanel;
	private HorizontalPanel experimentMediaStoragePanel;
	private HorizontalPanel experimentUserStorePanel;
	private VerticalPanel userExperimentsCollectionPanel;
	private HorizontalPanel experimentClosePanel;
	private static VerticalPanel registerUserPanel;
	private HorizontalPanel registerUserUserPanel;
	private HorizontalPanel registerUserPassPanel;
	private HorizontalPanel registerUserButtonPanel;
	private VerticalPanel registerUserContainerPanel;
	private HorizontalPanel experimentEditPanel;
	private VerticalPanel experimentUserEditPanel;
	private HorizontalPanel newUserHeadingPanel;
	private HorizontalPanel addNewUserPanel;
	private HorizontalPanel copyExperimentDataPanel;
	private HorizontalPanel errorUserRegistrationPanel;
	private HorizontalPanel errorPrimaryUserPanel;
	private HorizontalPanel errorAdditionalUserPanel;
	private HorizontalPanel noExperimentsPanel;

	// Vectors
	private Vector<HorizontalPanel> userExperimentsPanels;
	private Vector<Label> userExperimentLabels;
	private Vector<Button> userExperimentDelButtons;
	private Vector<Button> userExperimentEditButtons;
	private Vector<HorizontalPanel> experimentUserPanels;
	private Vector<Label> experimentUserUserTextBoxes;
	private Vector<TextBox> experimentUserPassTextBoxes;
	private Vector<Label> experimentUserPassLabels;
	private Vector<Button> experimentUserChangeUserButtons;
	private Vector<Button> experimentUserDeleteUserButtons;

	// list boxes
	private ListBox layoutList;
	private ListBox experimentList;
	private ListBox clientListA;
	private ListBox clientListB;
	private ListBox experimentLayoutList;
	private ListBox experimentDataList;

	// labels
	private Label headingLabel;
	private Label userLabel;
	private Label pwLabel;
	private Label experimentNameLabel;
	private Label experimentLayoutLabel;
	private Label experimentMediaStorageLabel;
	private Label experimentUserStoreHeadingLabel;
	private Label experimentUserStoreNameLabel;
	private Label experimentUserStorePassLabel;
	private Label userExperimentsHeading;
	private Label userNewExperimentHeading;
	private Label registerUserUserLabel;
	private Label registerUserPassLabel;
	private Label newUserStorePassLabel;
	private Label newUserHeadingLabel;
	private Label clientOutputLabel;
	private Label clientInputLabel;
	private Label wizardOutputLabel;
	private Label loginMessage;
	private Label errorUserRegistrationLabel;
	private Label errorPrimaryUserRegistrationLabel;
	private Label errorAdditionalUserRegistrationLabel;
	private Label noExperimentsLabel;
	private Label existingUserHeadingLabel;

	// buttons
	private Button loginButton;
	private Button logoutButton;
	private Button newExperimentButton;
	private Button saveNewExperimentButton;
	private Button cancelNewExperimentButton;
	private Button registerUserRegsiterButton;
	private Button registerUserCloseButton;
	private Button changeExperimentNameButton;
	private Button changeExperimentMediaServerButton;
	private Button changeExperimentLayoutButton;
	private Button cancelChangeExperimentButton;
	private Button addUserButton;
	private Button saveChangeExperimentButton;

	// hyperlinks
	private Hyperlink registerLink;

	// text boxes
	private TextBox userTextBox;
	private PasswordTextBox pwTextBox;
	private TextBox experimentNameTextBox;
	private TextBox experimentMediaStorageTextBox;
	private TextBox experimentUserStoreNameTextBox;
	private TextBox experimentUserStorePassTextBox;
	private TextBox registerUserUserTextBox;
	private TextBox registerUserPassTextBox;
	private TextBox newUserStoreNameTextBox;
	private TextBox newUserStorePassTextBox;

	// Check boxes
	private CheckBox copyExperimentDataCheckBox;
	private CheckBox freeTextTabCheckBox;

	// Popups
	private AddExperimentPopUp addExperimentPopUp;
	private RegisterUserPopUp registerUserPopUp;

	// other variables
	private int[][] components;
	private int[] experimentLayouts;
	private int newExpId;
	private int newUserId;
	private int user;
	private int experiment;
	private int editExpId;
	private String sqlRecording;
	private String sqlTab;
	private String sqlDomainData;
	private String sqlSlot;
	private String sqlDomainDataSlot;
	private String sqlComponents;
	private int[] domaindataNew;
	private int[] oldSlots;
	private int[] newSlots;
	private int slotCountCopy;
	private String[] experimentPaths;
	private String[] experimentSSLangs;
	private String[] experimentMTSrc;
	private String[] experimentMTTrg;
	private String[] experimentMToSrc;
	private String[] experimentMToTrg;
	private String[] experimentASRlangs;
	private String selectedExperimentPath;
	private String selectedSSLang;
	private String selectedMTSrc;
	private String selectedMTTrg;
	private String selectedMToSrc;
	private String selectedMToTrg;
	private String selectedASRlang;
	private Screen wizardInterface;
	private int experimentToCopy;
	private static SettingsMTout mtOut;
	private static SettingsMTin mtIn;
	private static SettingsASR asrIn;
	private static SettingsSS ssOut;
	private int asrMod;
	private int mtCorMod;
	private int mtOutComp;
	private String newUser;
	private String newPwd;
	private Date date;
	private int editExpPanel;

	// RPC
	private DatabaseAccessAsync databaseAccessSvc;
	private ComponentFactoryAsync componentFactorySvc;

	public void onModuleLoad() {
		stopSession();
	}

	@SuppressWarnings("deprecation")
	private void startSession() {
		// instantiate variables

		// main panels
		headPanel = new VerticalPanel();
		titlePanel = new HorizontalPanel();
		contentPanel = new VerticalPanel();
		footPanel = new VerticalPanel();
		contentWizardPanel = new VerticalPanel();
		containerPanel = new VerticalPanel();
		menuPanel = new HorizontalPanel();
		menuSettingsPanel = new DisclosurePanel("Settings");
		menuExperimentsPanel = new DisclosurePanel("Experiments");
		wizardScreenPanel = new VerticalPanel();
		layoutsPanel = new VerticalPanel();
		settingsPanel = new VerticalPanel();
		settingsClientOutputPanel = new VerticalPanel();
		settingsClientInputPanel = new VerticalPanel();
		settingsWizardOutputPanel = new VerticalPanel();
		settingsWizardOutMT = new VerticalPanel();
		experimentsPanel = new HorizontalPanel();
		experimentCollectionPanel = new VerticalPanel();
		loginPanel = new HorizontalPanel();
		addExperimentPanel = new VerticalPanel();
		experimentNamePanel = new HorizontalPanel();
		experimentLayoutPanel = new HorizontalPanel();
		experimentSavePanel = new HorizontalPanel();
		experimentMediaStoragePanel = new HorizontalPanel();
		experimentUserStorePanel = new HorizontalPanel();
		userExperimentsCollectionPanel = new VerticalPanel();
		experimentClosePanel = new HorizontalPanel();
		registerUserPanel = new VerticalPanel();
		registerUserUserPanel = new HorizontalPanel();
		registerUserPassPanel = new HorizontalPanel();
		registerUserButtonPanel = new HorizontalPanel();
		registerUserContainerPanel = new VerticalPanel();
		experimentEditPanel = new HorizontalPanel();
		experimentUserEditPanel = new VerticalPanel();
		newUserHeadingPanel = new HorizontalPanel();
		addNewUserPanel = new HorizontalPanel();
		copyExperimentDataPanel = new HorizontalPanel();
		errorUserRegistrationPanel = new HorizontalPanel();
		errorPrimaryUserPanel = new HorizontalPanel();
		errorAdditionalUserPanel = new HorizontalPanel();
		noExperimentsPanel = new HorizontalPanel();

		// Vectors
		userExperimentsPanels = new Vector<HorizontalPanel>();
		userExperimentLabels = new Vector<Label>();
		userExperimentDelButtons = new Vector<Button>();
		userExperimentEditButtons = new Vector<Button>();
		experimentUserPanels = new Vector<HorizontalPanel>();
		experimentUserUserTextBoxes = new Vector<Label>();
		experimentUserPassTextBoxes = new Vector<TextBox>();
		experimentUserPassLabels = new Vector<Label>();
		experimentUserChangeUserButtons = new Vector<Button>();
		experimentUserDeleteUserButtons = new Vector<Button>();

		// list boxes
		layoutList = new ListBox();
		experimentList = new ListBox();
		clientListA = new ListBox();
		clientListB = new ListBox();
		experimentLayoutList = new ListBox();
		experimentDataList = new ListBox();

		// labels
		headingLabel = new Label();
		userLabel = new Label("User Name: ");
		pwLabel = new Label("Password: ");
		experimentNameLabel = new Label("Experiment Name: ");
		experimentLayoutLabel = new Label("Wizard Interface: ");
		experimentMediaStorageLabel = new Label("Path to Media: ");
		experimentUserStoreHeadingLabel = new Label(
				"(Further users can then be added via 'Edit'!)");
		experimentUserStoreNameLabel = new Label("User Name:");
		experimentUserStorePassLabel = new Label("Password:");
		userExperimentsHeading = new Label("Your Existing Experiments");
		userNewExperimentHeading = new Label("Add a New Experiment");
		registerUserUserLabel = new Label("User Name:");
		registerUserPassLabel = new Label("Password:");
		newUserStorePassLabel = new Label("Password: ");
		newUserHeadingLabel = new Label("Add User: ");
		clientOutputLabel = new Label("Client");
		clientInputLabel = new Label("Input");
		wizardOutputLabel = new Label("Output");
		loginMessage = new Label();
		errorUserRegistrationLabel = new Label();
		errorPrimaryUserRegistrationLabel = new Label();
		errorAdditionalUserRegistrationLabel = new Label();
		noExperimentsLabel = new Label("< Empty >");
		existingUserHeadingLabel = new Label("Experiment Users");

		// buttons
		loginButton = new Button("Login");
		logoutButton = new Button("Logout");
		newExperimentButton = new Button("Organise Experiments");
		saveNewExperimentButton = new Button("Add Experiment");
		cancelNewExperimentButton = new Button("Close");
		registerUserRegsiterButton = new Button("Register");
		registerUserCloseButton = new Button("Cancel");
		changeExperimentNameButton = new Button("Change");
		changeExperimentMediaServerButton = new Button("Change");
		changeExperimentLayoutButton = new Button("Change");
		cancelChangeExperimentButton = new Button("Close");
		saveChangeExperimentButton = new Button("Save Changes");
		addUserButton = new Button("Add");

		// hyperlinks
		registerLink = new Hyperlink("Register...", "1");

		// text boxes
		userTextBox = new TextBox();
		pwTextBox = new PasswordTextBox();
		experimentNameTextBox = new TextBox();
		experimentMediaStorageTextBox = new TextBox();
		experimentUserStoreNameTextBox = new TextBox();
		experimentUserStorePassTextBox = new TextBox();
		registerUserUserTextBox = new TextBox();
		registerUserPassTextBox = new TextBox();
		newUserStoreNameTextBox = new TextBox();
		newUserStorePassTextBox = new TextBox();

		// Check boxes
		copyExperimentDataCheckBox = new CheckBox(
				"Copy utterances and domain data from existing experiment");
		freeTextTabCheckBox = new CheckBox("Include Free-Text tab");

		// Popups
		addExperimentPopUp = null;
		registerUserPopUp = null;
		addExperimentPopUp = new AddExperimentPopUp();
		registerUserPopUp = new RegisterUserPopUp();

		// RPC
		databaseAccessSvc = GWT.create(DatabaseAccess.class);
		componentFactorySvc = GWT.create(ComponentFactory.class);

		// add handler
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				login();
			}
		});

		logoutButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				logout();
			}
		});

		pwTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}

		});

		userTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					login();
				}
			}

		});

		newExperimentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				date = new Date();
				long userId = (long) (date.getTime());
				loadExperimentLayouts();
				copyExperimentDataCheckBox.setChecked(false);
				experimentNameTextBox.setText("New Experiment");
				experimentMediaStorageTextBox.setText("http://...");
				experimentUserStoreNameTextBox.setText("u" + userId);
				experimentUserStorePassTextBox.setText("Password01");
				addExperimentPopUp.center();
			}
		});

		addUserButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String newUser = clearHiven(newUserStoreNameTextBox.getText());
				String newPwd = clearHiven(newUserStorePassTextBox.getText());
				checkUserExists(newUser, newPwd, 3);
			}
		});

		saveNewExperimentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String newUser = clearHiven(experimentUserStoreNameTextBox
						.getText());
				String newPwd = clearHiven(experimentUserStorePassTextBox
						.getText());
				checkUserExists(newUser, newPwd, 2);
			}
		});

		cancelChangeExperimentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				closeChangeExperiment();
			}
		});

		saveChangeExperimentButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				changeExperimentData();
			}

		});

		changeExperimentNameButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				changeExperimentData();
			}

		});

		changeExperimentLayoutButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				changeExperimentData();
			}

		});

		changeExperimentMediaServerButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				changeExperimentData();
			}

		});

		cancelNewExperimentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loadExperiments();
				registerUserUserTextBox.setText("");
				registerUserPassTextBox.setText("");
				addExperimentPopUp.hide();

				// change visibility of tabs
				if (wizardInterface != null) {
					wizardInterface.changeVisibility();
				}
			}
		});

		copyExperimentDataCheckBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).isChecked();
				if (checked) {
					experimentDataList.setVisible(true);
				} else {
					experimentDataList.setVisible(false);
				}
			}
		});

		menuSettingsPanel.addCloseHandler(new CloseHandler<DisclosurePanel>() {
			public void onClose(CloseEvent<DisclosurePanel> event) {
				changeTabVisibility();
			}

		});

		// Reset History: Application starts with no history
		String initToken = History.getToken();
		if (initToken.length() == 0) {
			History.newItem("0");
		}
		// Add history listener
		History.addValueChangeHandler(this);

		// hide popup
		registerUserPopUp.hide();
		// load register panel
		buildRegisterUserPanel();
		addExperimentPopUp.hide();

		// headPanel
		headPanel.clear();
		String header = "<div id='header'></div>";
		headPanel.add(new HTML(header));

		// titlePanel
		titlePanel.clear();
		headingLabel.setText("Welcome!");
		headingLabel.setWidth("1080px");
		titlePanel.add(headingLabel);
		titlePanel.addStyleName("title");
		titlePanel.add(logoutButton);

		// contentPanel
		contentPanel.clear();
		loginMessage.setText("");
		contentPanel.add(loginPanel);
		loginMessage.setStyleName("loginMessage");
		contentPanel.add(loginMessage);

		// footPanel
		footPanel.clear();
		String foot = "<div style=\"margin-bottom: 15px; margin-top:10px\">&nbsp;</div>";
		footPanel.add(new HTML(foot));
		footPanel.setWidth("1160px");

		// load login screen
		loadLoginScreen();
		RootPanel.get("head").add(headPanel);
		RootPanel.get("title").add(titlePanel);
		RootPanel.get("content").add(contentPanel);
		RootPanel.get("foot").add(footPanel);
	}

	private void stopSession() {

		// stop reload in wizardInterface
		if (wizardInterface != null) {
			wizardInterface.stopReload();
			wizardInterface = null;
		}

		// clear elements
		RootPanel.get("head").clear();
		RootPanel.get("title").clear();
		RootPanel.get("content").clear();
		RootPanel.get("foot").clear();
		RootPanel.get().clear();

		// start new session
		startSession();

	}

	private void login() {

		String sql = "Select * from user where name = '"
				+ userTextBox.getText() + "' and pw = '" + pwTextBox.getText()
				+ "' and role = 1";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					setUser(Integer.parseInt(result[0][0]));
					loginPanel.clear();
					registerUserPanel.clear();
					logoutButton.setVisible(true);
					loginMessage.setText("");
					// load wizard screen
					loadWizardScreen();

				} else {
					// clear login if no user is found
					pwTextBox.setText("");
					userTextBox.setText("");
					loginMessage.setText("Sorry! Wrong user name or password ");
				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void logout() {
		stopSession();
	}

	private void loadLoginScreen() {
		loginPanel.clear();
		loginPanel.add(userLabel);
		userLabel.setStyleName("label");
		loginPanel.add(userTextBox);
		userTextBox.setStyleName("text");
		loginPanel.add(pwLabel);
		pwLabel.setStyleName("label");
		loginPanel.add(pwTextBox);
		pwTextBox.setStyleName("text");
		loginPanel.add(loginButton);
		loginButton.setStyleName("button");
		logoutButton.setStyleName("button");
		loginPanel.add(registerLink);

		// deactivate registration in case you want to control the amount of
		// wizards using the system
		// registerLink.setVisible(false);

		registerLink.setStyleName("link");
		logoutButton.setVisible(false);
		loginPanel.setStyleName("loginPanel");
		loginPanel.setVisible(true);
		headingLabel.setText("Welcome!");
		pwTextBox.setText("");
		userTextBox.setText("");
	}

	private void loadWizardScreen() {

		// load experiment edit panel
		loadExperimentEditPanel();

		// load menu
		loadMenu();

		// wizardScreenPanel
		wizardScreenPanel.clear();
		headingLabel.setText("WebWOZ Wizard of Oz Prototyping Platform");
		wizardScreenPanel.add(new HomeScreen().getScreen());

		// containerPanel
		containerPanel.clear();
		containerPanel.add(wizardScreenPanel);
		containerPanel.setWidth("930px");

		// contentWizardPanel
		contentWizardPanel.clear();
		contentWizardPanel.add(menuPanel);
		contentWizardPanel.add(containerPanel);
		contentWizardPanel.addStyleName("contentPanel");

		contentPanel.clear();
		contentPanel.add(contentWizardPanel);

		// wizard layout handler
		layoutList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeLayout();
				menuPanel.setVisible(false);
			}

		});

		// clientA layout handler
		clientListA.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				// changeClient();
			}

		});

		// clientB layout handler
		clientListB.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				// changeClient();
			}

		});

		// experiment handler
		experimentList.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				changeExperiment();
			}

		});

	}

	private void loadMenu() {
		menuSettingsPanel.clear();
		menuSettingsPanel.add(settingsPanel);
		menuSettingsPanel.setOpen(false);
		menuSettingsPanel.setVisible(false);
		menuExperimentsPanel.clear();
		experimentCollectionPanel.clear();
		experimentCollectionPanel.setStyleName("marginBottom");
		experimentCollectionPanel.add(experimentsPanel);
		menuExperimentsPanel.add(experimentCollectionPanel);
		menuExperimentsPanel.setOpen(true);
		menuPanel.clear();
		menuPanel.add(menuExperimentsPanel);
		menuPanel.add(menuSettingsPanel);
		menuPanel.setStyleName("menuPanel");
		menuPanel.setVisible(true);
	}

	private void loadExperiments() {

		String sql = "Select * from experiment where userid = " + user
				+ " order by id asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				experimentList.clear();

				if (result != null) {
					experimentLayouts = new int[result.length + 1];
					experimentPaths = new String[result.length + 1];
					experimentSSLangs = new String[result.length + 1];
					experimentMTSrc = new String[result.length + 1];
					experimentMTTrg = new String[result.length + 1];
					experimentMToSrc = new String[result.length + 1];
					experimentMToTrg = new String[result.length + 1];
					experimentASRlangs = new String[result.length + 1];

					experimentList.addItem("-", "0");
					experimentLayouts[0] = 0;
					experimentSSLangs[0] = "-";
					experimentPaths[0] = "-";
					experimentMTSrc[0] = "-";
					experimentMTTrg[0] = "-";
					experimentMToSrc[0] = "-";
					experimentMToTrg[0] = "-";
					experimentASRlangs[0] = "-";

					for (int i = 0; i < result.length; i++) {

						experimentLayouts[i + 1] = Integer
								.parseInt(result[i][6]);
						experimentPaths[i + 1] = result[i][5];
						experimentSSLangs[i + 1] = result[i][8];
						experimentMTSrc[i + 1] = result[i][9];
						experimentMTTrg[i + 1] = result[i][10];
						experimentMToSrc[i + 1] = result[i][11];
						experimentMToTrg[i + 1] = result[i][12];
						experimentASRlangs[i + 1] = result[i][13];

						experimentList.addItem(result[i][1] + " ("
								+ result[i][0] + ")", result[i][0]);

					}
					noExperimentsPanel.setVisible(false);
					userExperimentsCollectionPanel.setVisible(true);

				} else {
					userExperimentsCollectionPanel.setVisible(false);
					noExperimentsPanel.setVisible(true);
					experimentList.addItem("-", "0");
				}
				// add list to panel
				experimentsPanel.clear();
				experimentsPanel.add(experimentList);
				experimentList.setStyleName("list");
				// add possibility to create a new experiment
				experimentsPanel.add(newExperimentButton);
				newExperimentButton.setStyleName("button");
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void addExperimentDelButton(Button b, final int expid,
			final int panel) {
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				userExperimentsPanels.get(panel).clear();
				deleteExperiment(expid);
			}
		});

	}

	private void addExperimentEditButton(Button b, final int expid,
			final int panel) {
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editExpId = expid;
				editExpPanel = panel;
				getExperimentData();
			}
		});

	}

	private void buildSettingsPanel(final int experimentid) {
		String sql = "Select * from experimentcomponent where experimentid = "
				+ experimentid + " order by rank asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				settingsPanel.clear();
				settingsClientOutputPanel.clear();
				clientOutputLabel.setStyleName("strong");
				settingsClientOutputPanel.setStyleName("marginBottom");
				settingsClientOutputPanel.add(clientOutputLabel);
				settingsClientInputPanel.clear();
				clientInputLabel.setStyleName("strong");
				settingsClientInputPanel
						.setStyleName("settingsComponentsPanel");
				settingsClientInputPanel.add(clientInputLabel);
				settingsWizardOutputPanel.clear();
				wizardOutputLabel.setStyleName("strong");
				settingsWizardOutputPanel
						.setStyleName("settingsComponentsPanel");

				settingsWizardOutputPanel
						.addStyleName("settingsComponentsPanel");
				settingsWizardOutputPanel.add(wizardOutputLabel);
				settingsPanel.add(settingsClientInputPanel);
				settingsPanel.add(settingsWizardOutputPanel);
				settingsPanel.add(settingsClientOutputPanel);

				if (result != null) {

					// Output
					settingsClientOutputPanel.add(new SettingsClient(
							experimentid).getScreen());

					asrMod = 0;
					mtCorMod = 0;
					mtOutComp = 0;

					for (int i = 0; i < result.length; i++) {
						switch (Integer.parseInt(result[i][2])) {
						case 1: // ASR
							asrIn = new SettingsASR(experimentid,
									selectedExperimentPath, selectedSSLang,
									selectedMTSrc, selectedMTTrg,
									selectedMToSrc, selectedMToTrg,
									selectedASRlang, getUser());

							settingsClientInputPanel.add(asrIn.getScreen(
									Integer.parseInt(result[i][3]),
									Integer.parseInt(result[i][4]),
									Integer.parseInt(result[i][5])));
							asrMod = Integer.parseInt(result[i][4]);
							break;
						case 2: // MT in
							mtIn = new SettingsMTin(experimentid,
									selectedExperimentPath, selectedSSLang,
									selectedMTSrc, selectedMTTrg,
									selectedMToSrc, selectedMToTrg,
									selectedASRlang, getUser());

							settingsClientInputPanel.add(mtIn.getScreen(
									Integer.parseInt(result[i][3]),
									Integer.parseInt(result[i][4]),
									Integer.parseInt(result[i][5])));

							mtCorMod = Integer.parseInt(result[i][4]);
							break;
						case 5: // MT out
							mtOut = new SettingsMTout(experimentid,
									selectedExperimentPath, selectedSSLang,
									selectedMTSrc, selectedMTTrg,
									selectedMToSrc, selectedMToTrg,
									selectedASRlang, getUser());

							settingsWizardOutMT = mtOut.getScreen(
									Integer.parseInt(result[i][3]),
									Integer.parseInt(result[i][4]),
									Integer.parseInt(result[i][5]));
							settingsWizardOutputPanel.add(settingsWizardOutMT);
							mtOutComp = Integer.parseInt(result[i][3]);
							break;
						case 6: // SS
							ssOut = new SettingsSS(experimentid,
									selectedExperimentPath, selectedSSLang,
									selectedMTSrc, selectedMTTrg,
									selectedMToSrc, selectedMToTrg,
									selectedASRlang, getUser());

							settingsWizardOutputPanel.add(ssOut.getScreen(
									Integer.parseInt(result[i][3]),
									Integer.parseInt(result[i][4]),
									Integer.parseInt(result[i][5])));
							break;
						default:
							break;
						}

					}

					if (asrMod > 0) {
						changeVisiblility(2);
					} else {
						changeVisiblility(3);
					}

					if (asrMod > 0 | mtCorMod > 0) {
						changeVisiblility(4);
					} else {
						changeVisiblility(5);
					}

					if (mtOutComp > 0) {
						changeVisiblility(6);
					} else {
						changeVisiblility(7);
					}

					settingsClientInputPanel.add(new HTML("<hr />"));
					settingsWizardOutputPanel.add(new HTML("<hr />"));
					menuSettingsPanel.setVisible(true);

				} else {

				}
			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void changeLayout() {

		// clear wizard screen and stop reload
		wizardScreenPanel.clear();

		if (wizardInterface != null) {
			wizardInterface.stopReload();
			wizardInterface = null;
		}

		menuSettingsPanel.setVisible(false);

		int i = experimentLayouts[experimentList.getSelectedIndex()];

		switch (i) {

		case 0:
			wizardInterface = new ChooseLayoutInfoScreen();
			wizardScreenPanel.add(wizardInterface.getScreen());
			break;
		case 1:
			wizardInterface = new DefaultWizardScreen(
					Integer.parseInt(experimentList.getValue(experimentList
							.getSelectedIndex())), user);
			wizardScreenPanel.add(wizardInterface.getScreen());
			menuExperimentsPanel.setOpen(false);
			menuSettingsPanel.setVisible(true);
			menuSettingsPanel.setOpen(false);
			break;

		// space for more screen layouts

		default:
			wizardInterface = new NoLayoutScreen();
			wizardScreenPanel.add(wizardInterface.getScreen());
			break;
		}

	}

	private void changeExperiment() {

		// set experiment variables
		selectedExperimentPath = experimentPaths[experimentList
				.getSelectedIndex()];
		selectedSSLang = experimentSSLangs[experimentList.getSelectedIndex()];
		selectedMTSrc = experimentMTSrc[experimentList.getSelectedIndex()];
		selectedMTTrg = experimentMTTrg[experimentList.getSelectedIndex()];
		selectedMToSrc = experimentMToSrc[experimentList.getSelectedIndex()];
		selectedMToTrg = experimentMToTrg[experimentList.getSelectedIndex()];
		selectedASRlang = experimentASRlangs[experimentList.getSelectedIndex()];
		experiment = Integer.parseInt(experimentList.getValue(experimentList
				.getSelectedIndex()));

		// load layout for experiment
		changeLayout();

		// load LTCs for the experiment
		clearComponents();
		loadLTCs();

		// load LTC settings for experiment
		buildSettingsPanel(experiment);

	}

	private void loadLTCs() {
		String sql = "Select * from experimentcomponent where experimentid = "
				+ experiment + " order by rank asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					components = new int[result.length][3];
					for (int i = 0; i < result.length; i++) {
						// add components to local array
						components[i][0] = Integer.parseInt(result[i][2]);
						components[i][1] = Integer.parseInt(result[i][3]);
						components[i][2] = Integer.parseInt(result[i][4]);
					}

					addComponents();

				} else {
					// System.out.println("No components defined for this experiment!");
				}

				layoutList.clear();
				layoutsPanel.clear();
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

		componentFactorySvc.pushComponents(components, Integer
				.parseInt(experimentList.getValue(experimentList
						.getSelectedIndex())), selectedExperimentPath,
				selectedSSLang, selectedMTSrc, selectedMTTrg, selectedMToSrc,
				selectedMToTrg, selectedASRlang, getUser(), callback);

	}

	private void clearComponents() {

		// clear local field
		components = null;

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

		componentFactorySvc.clearComponents(callback);
	}

	public void saveExperiment(final String us, final String pw) {

		String expName = clearHiven(experimentNameTextBox.getText());
		String expServer = clearHiven(experimentMediaStorageTextBox.getText());

		String sql = "Insert into experiment (name, mediaserver, layout, userid) values ('"
				+ expName
				+ "', '"
				+ expServer
				+ "', "
				+ experimentLayoutList.getValue(experimentLayoutList
						.getSelectedIndex()) + ", " + user + ");";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				newExpId = Integer.parseInt(result);
				// System.out.println("Experiment: " + newExpId);
				saveUser(us, pw);
			}

		};

		databaseAccessSvc.storeData(sql, callback);

	}

	public void saveUser(String us, String pw) {

		String sql = "Insert into user (name, pw, role) values ('" + us
				+ "', '" + pw + "', 2);";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				newUserId = Integer.parseInt(result);
				// System.out.println("User: " + newUserId);
				insertExperimentUser();
			}

		};

		databaseAccessSvc.storeData(sql, callback);

	}

	private void insertExperimentUser() {

		String sql = "Insert into experimentuser values (" + newExpId + ", "
				+ newUserId + ");";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			@SuppressWarnings("deprecation")
			public void onSuccess(String result) {
				// System.out.println("ExperimentUser inserted!");
				if (!copyExperimentDataCheckBox.isChecked()) {
					createNewExperimentEnvironment();
				} else {
					addExperiment();
				}
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void loadExperimentLayouts() {

		String sql = "Select * from layout";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				experimentLayoutList.clear();

				if (result != null) {
					;
					for (int i = 0; i < result.length; i++) {
						experimentLayoutList
								.addItem(result[i][1], result[i][0]);
					}
				} else {

				}

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getUser() {
		return user;
	}

	private static class AddExperimentPopUp extends PopupPanel {
		public AddExperimentPopUp() {
			// Set the dialog box's caption.
			setWidth("430px");
			setHeight("270px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(addExperimentPanel);
		}
	}

	private static class RegisterUserPopUp extends PopupPanel {
		public RegisterUserPopUp() {
			// Set the dialog box's caption.
			setWidth("300px");
			setHeight("100px");
			// Enable animation.
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setWidget(registerUserPanel);
		}
	}

	private void createNewExperimentEnvironment() {
		String sql = " Insert into recording (expid, semkey, section, rank, origtext, origaudiofile, origmmfile, transtext, transaudiofile, transmmfile) values ("
				+ newExpId
				+ ", 'Welcome', 1, 7, 'Welcome to ABC Bank. To start, please say your account number or say I do not have one.' , 'http://kdeg-vm-14.cs.tcd.ie:8180/mediaexamples/Scenario01_01.mp3', '', '', '', '');";
		sql = sql
				+ " Insert into tab (tabname, exp, rank, notes) values ('Stored Utterances',"
				+ newExpId
				+ ", 0, 'Please welcome and identify the customer.');";

		sql = sql
				+ " Insert into recording (expid, semkey, section, rank, origtext, origaudiofile, origmmfile, transtext, transaudiofile, transmmfile) values ("
				+ newExpId
				+ ", 'Correct', 0, 7, 'Is that correct?' , 'http://kdeg-vm-14.cs.tcd.ie:8180/mediaexamples/Scenario02_03.mp3', '', '', '', '');";

		// components
		sql = sql
				+ " Insert into experimentcomponent (experimentid, rank, componenttype, component, componentmode) values ("
				+ newExpId + ", 0, 0, 0, 0);"; // Wizard
		sql = sql
				+ " Insert into experimentcomponent (experimentid, rank, componenttype, component, componentmode) values ("
				+ newExpId + ", 1, 1, 0, 0);"; // ASR
		sql = sql
				+ " Insert into experimentcomponent (experimentid, rank, componenttype, component, componentmode) values ("
				+ newExpId + ", 2, 2, 0, 0);"; // MT in
		sql = sql
				+ " Insert into experimentcomponent (experimentid, rank, componenttype, component, componentmode) values ("
				+ newExpId + ", 3, 3, 0, 0);"; // NLU
		sql = sql
				+ " Insert into experimentcomponent (experimentid, rank, componenttype, component, componentmode) values ("
				+ newExpId + ", 4, 4, 0, 0);"; // NLG
		sql = sql
				+ " Insert into experimentcomponent (experimentid, rank, componenttype, component, componentmode) values ("
				+ newExpId + ", 5, 5, 0, 0);"; // MT out
		sql = sql
				+ " Insert into experimentcomponent (experimentid, rank, componenttype, component, componentmode) values ("
				+ newExpId + ", 6, 6, 1, 0);"; // SS

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				// System.out.println("Experiment Environment created (Recording, Tab, Standard Experiment Components!");
				addExperiment();
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void deleteExperiment(int id) {

		String sql = "Delete from recording where expid =" + id + ";";
		sql = sql + " Delete from domaindata where expid =" + id + ";";
		sql = sql + " Delete from tab where exp =" + id + ";";
		sql = sql + " Delete from experimentuser where expid=" + id + ";";
		sql = sql + " Delete from slot where expid =" + id + ";";
		sql = sql + " Delete from experiment where id =" + id + ";";
		sql = sql + " Delete from experimentcomponent where experimentid ="
				+ id + ";";

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

	@SuppressWarnings("deprecation")
	private void addExperiment() {

		int i = userExperimentsPanels.size();
		userExperimentsPanels.add(i, new HorizontalPanel());
		userExperimentLabels.add(i, new Label(experimentNameTextBox.getText()));
		userExperimentDelButtons.add(i, new Button("Delete"));
		userExperimentEditButtons.add(i, new Button("Edit"));

		addExperimentDelButton(userExperimentDelButtons.get(i), newExpId, i);
		addExperimentEditButton(userExperimentEditButtons.get(i), newExpId, i);
		userExperimentLabels.get(i).setWidth("150px");
		userExperimentDelButtons.get(i).setStyleName("button");
		userExperimentEditButtons.get(i).setStyleName("button");

		userExperimentsPanels.get(i).add(userExperimentLabels.get(i));
		userExperimentsPanels.get(i).add(userExperimentDelButtons.get(i));
		userExperimentsPanels.get(i).add(userExperimentEditButtons.get(i));

		// add them to the collection panel
		userExperimentsCollectionPanel.add(userExperimentsPanels.get(i));
		// end list

		// resest textbox values
		experimentNameTextBox.setText("New Experiment");
		experimentMediaStorageTextBox.setText("http://...");
		experimentLayoutList.setItemSelected(0, true);

		date = new Date();
		long newUserId = (long) (date.getTime());
		experimentUserStoreNameTextBox.setText("u" + newUserId);
		experimentUserStorePassTextBox.setText("Password01");

		noExperimentsPanel.setVisible(false);
		userExperimentsCollectionPanel.setVisible(true);

		if (copyExperimentDataCheckBox.isChecked()) {
			copyRecording();
		} else {

		}
	}

	public void copyRecording() {
		experimentToCopy = Integer.parseInt(experimentDataList
				.getValue(experimentDataList.getSelectedIndex()));

		String sql = "Select * from recording where expid = "
				+ experimentToCopy + " order by id";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				sqlRecording = "";
				for (int i = 0; i < result.length; i++) {
					sqlRecording = sqlRecording
							+ " Insert into recording (expid, semkey, section, rank, origtext, origaudiofile, origmmfile, transtext, transaudiofile, transmmfile) values ("
							+ newExpId + ", '" + result[i][2] + "', "
							+ result[i][3] + ", " + result[i][4] + ", '"
							+ result[i][5] + "', '" + result[i][6] + "', '"
							+ result[i][7] + "', '" + result[i][8] + "', '"
							+ result[i][9] + "', '" + result[i][10] + "');";
				}
				System.out.println("Copied recording!");
				copyComponents();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void copyComponents() {
		String sql = "Select * from experimentcomponent where experimentid = "
				+ experimentToCopy;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				sqlComponents = "";
				for (int i = 0; i < result.length; i++) {
					sqlComponents = sqlComponents
							+ " Insert into experimentcomponent (experimentid, rank, componenttype, component, componentmode) values ("
							+ newExpId + ", " + result[i][1] + ", "
							+ result[i][2] + ", " + result[i][3] + ", "
							+ result[i][4] + ");";
				}
				System.out.println("Copied components!");
				copyTab();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void copyTab() {
		String sql = "Select * from tab where exp = " + experimentToCopy;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			@SuppressWarnings("deprecation")
			public void onSuccess(String[][] result) {
				sqlTab = "";
				for (int i = 0; i < result.length; i++) {
					sqlTab = sqlTab
							+ " Insert into tab (tabname, exp, rank) values ('"
							+ result[i][1] + "', " + newExpId + ", "
							+ result[i][3] + ");";

				}

				// add free text tab if requested
				if (freeTextTabCheckBox.isChecked()) {

					sqlTab = sqlTab
							+ " Update experiment set freetext = 1 where id = "
							+ newExpId + ";";
				}

				// System.out.println("Copied tabs!");
				copyDomainData();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void copyDomainData() {
		String sql = "Select * from domaindata where expid = "
				+ experimentToCopy;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				sqlDomainData = "";
				for (int i = 0; i < result.length; i++) {
					sqlDomainData = sqlDomainData
							+ " Insert into domaindata (expid, semkey, section, rank, origtext, origaudiofile, origmmfile, transtext, transaudiofile, transmmfile) values ("
							+ newExpId + ", '" + result[i][2] + "', "
							+ result[i][3] + ", " + result[i][4] + ", '"
							+ result[i][5] + "', '" + result[i][6] + "', '"
							+ result[i][7] + "', '" + result[i][8] + "', '"
							+ result[i][9] + "', '" + result[i][10] + "');";
				}
				// System.out.println("Copied domain data!");
				copySlot();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void copySlot() {
		String sql = "Select * from slot where expid = " + experimentToCopy;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				sqlSlot = "";
				for (int i = 0; i < result.length; i++) {
					sqlSlot = sqlSlot
							+ " Insert into slot (expid, name, value, type, rank) values ("
							+ newExpId + ", '" + result[i][2] + "', '"
							+ result[i][3] + "', " + result[i][4] + ", "
							+ result[i][5] + ");";
				}
				// System.out.println("Copied filter!");
				getSlotCount();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	public void getSlotCount() {
		String sql = "Select distinct(name) from slot where expid = "
				+ experimentToCopy;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				setSlotCountCopy(result.length);
				// System.out.println("Slot count to copy: " + slotCountCopy);
				insertDomainData();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void insertDomainData() {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				// System.out.println("Domain data inserted!");
				getNewDomainDataIds();
			}

		};

		databaseAccessSvc.storeData(sqlDomainData, callback);
	}

	public void getNewDomainDataIds() {
		String sql = "Select * from domaindata where expid = " + newExpId
				+ " order by id";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				sqlDomainDataSlot = "";
				domaindataNew = new int[result.length];
				for (int i = 0; i < result.length; i++) {
					domaindataNew[i] = Integer.parseInt(result[i][0]);
				}
				// System.out.println("New domain data ids retrieved!");
				insertSlotData();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void insertSlotData() {

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				// System.out.println("Filters inserted!");
				getSlotIds();
			}

		};

		databaseAccessSvc.storeData(sqlSlot, callback);
	}

	public void getSlotIds() {
		String sql = "Select * from slot where expid in (" + newExpId + ", "
				+ experimentToCopy + ") order by id";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				oldSlots = new int[result.length / 2];
				newSlots = new int[result.length / 2];
				int x = 0;
				for (int i = 0; i < result.length / 2; i++) {
					oldSlots[x] = Integer.parseInt(result[i][0]);
					x++;
				}
				x = 0;
				for (int j = result.length / 2; j < result.length; j++) {
					newSlots[x] = Integer.parseInt(result[j][0]);
					x++;
				}
				// System.out.println("Slot comparison created!");
				copyDomainDataSlot();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	public void copyDomainDataSlot() {
		String sql = "Select * from domaindata inner join domaindataslot where domaindata.id = domaindataslot.dataid and domaindata.expid = "
				+ experimentToCopy + " order by domaindata.id";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {
				sqlDomainDataSlot = "";
				int k = 0;
				for (int i = 0; i < result.length; i++) {

					int x = 0;
					for (int j = 0; j < oldSlots.length; j++) {
						if (oldSlots[j] == Integer.parseInt(result[i][12])) {
							x = j;
						}
					}

					sqlDomainDataSlot = sqlDomainDataSlot
							+ " Insert into domaindataslot (dataid, slotid) values ("
							+ domaindataNew[k] + ", " + newSlots[x] + ");";
					// only got to the next domaindata id if data for all slots
					// are inserted
					if ((i + 1) % slotCountCopy == 0) {
						k++;
					}

				}
				// System.out.println("Slots copied!");
				insertCopy();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);
	}

	private void insertCopy() {
		String sql = sqlRecording + sqlTab + sqlDomainDataSlot + sqlComponents;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			@SuppressWarnings("deprecation")
			public void onSuccess(String result) {
				System.out
						.println("Recordings, Tabs, DomaindataSlots and Components inserted!");
				// set check boxes visible true and uncheck them
				copyExperimentDataCheckBox.setVisible(true);
				copyExperimentDataCheckBox.setChecked(false);
				experimentDataList.setVisible(false);
				freeTextTabCheckBox.setVisible(true);
				freeTextTabCheckBox.setChecked(false);

			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void buildRegisterUserPanel() {

		registerUserPanel.clear();
		registerUserUserPanel.clear();
		registerUserPassPanel.clear();
		registerUserButtonPanel.clear();
		registerUserContainerPanel.clear();

		registerUserUserPanel.clear();
		registerUserUserPanel.add(registerUserUserLabel);
		registerUserUserLabel.setWidth("100px");
		registerUserUserPanel.add(registerUserUserTextBox);
		registerUserUserTextBox.setWidth("150px");
		registerUserUserPanel.setStyleName("registerUser");

		registerUserPassPanel.clear();
		registerUserPassPanel.add(registerUserPassLabel);
		registerUserPassLabel.setWidth("100px");
		registerUserPassPanel.add(registerUserPassTextBox);
		registerUserPassTextBox.setWidth("150px");
		registerUserPassPanel.setStyleName("registerUser");

		errorUserRegistrationPanel.clear();
		errorUserRegistrationPanel.add(errorUserRegistrationLabel);
		errorUserRegistrationLabel.setStyleName("errorUserRegistrationLabel");
		errorUserRegistrationLabel.setText("");

		registerUserButtonPanel.clear();
		registerUserButtonPanel.add(registerUserCloseButton);
		registerUserCloseButton.setStyleName("button");
		registerUserButtonPanel.add(registerUserRegsiterButton);
		registerUserRegsiterButton.setStyleName("button");
		registerUserButtonPanel.setStyleName("registerUserButtons");

		registerUserContainerPanel.clear();
		registerUserContainerPanel.add(registerUserUserPanel);
		registerUserContainerPanel.add(registerUserPassPanel);
		registerUserContainerPanel.add(errorUserRegistrationPanel);
		registerUserContainerPanel.add(registerUserButtonPanel);

		registerUserContainerPanel.setStyleName("registerUser");

		registerUserPanel.clear();
		registerUserPanel.add(registerUserContainerPanel);

		registerUserCloseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("0");
				registerUserPopUp.hide();
			}
		});

		registerUserRegsiterButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("0");
				newUser = clearHiven(registerUserUserTextBox.getText());
				newPwd = clearHiven(registerUserPassTextBox.getText());
				checkUserExists(newUser, newPwd, 1);
			}
		});

	}

	public void onValueChange(ValueChangeEvent<String> event) {

		int i = Integer.parseInt(event.getValue());

		switch (i) {
		case 1:
			registerUserUserTextBox.setText("");
			registerUserPassTextBox.setText("");
			registerUserPopUp.center();
			registerUserPopUp.show();
			break;
		default:
			break;
		}
	}

	private void checkUserExists(final String user, final String pwd,
			final int mode) {
		String sql = "Select * from user where name = '" + user + "';";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {

					switch (mode) {
					case 1:
						registerUserUserTextBox.setText("");
						registerUserPassTextBox.setText("");
						errorUserRegistrationLabel
								.setText("Error: User Name already exists!");
						break;
					case 2:
						experimentUserStorePassTextBox.setText("");
						experimentUserStoreNameTextBox.setText("");
						errorPrimaryUserRegistrationLabel
								.setText("Error: User Name already exists!");
						break;
					case 3:
						newUserStoreNameTextBox.setText("");
						newUserStorePassTextBox.setText("");
						errorAdditionalUserRegistrationLabel
								.setText("Error: User Name already exists!");
						break;
					}

				} else {
					switch (mode) {
					case 1:
						errorUserRegistrationLabel.setText("");
						registerUser(user, pwd);
						registerUserPopUp.hide();
						break;
					case 2:
						errorPrimaryUserRegistrationLabel.setText("");
						saveExperiment(user, pwd);
						break;
					case 3:
						errorAdditionalUserRegistrationLabel.setText("");
						addUser(user, pwd);
						break;
					}

				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void registerUser(String user, String pw) {

		String sql = "Insert into user (name, pw, role) values ('" + user
				+ "', '" + pw + "', 1)";

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

	private void closeChangeExperiment() {
		userExperimentsCollectionPanel.setVisible(true);
		userExperimentsHeading.setText("Your Existing Experiments");
		userNewExperimentHeading.setVisible(true);

		userExperimentLabels.get(editExpPanel).setText(
				experimentNameTextBox.getText());

		existingUserHeadingLabel.setVisible(false);
		experimentNameTextBox.setText("New Experiment");
		experimentMediaStorageTextBox.setText("http://...");
		experimentLayoutList.setItemSelected(0, true);
		experimentUserStoreHeadingLabel
				.setText("(Further users can then be added via 'Edit'!)");
		experimentUserEditPanel.setVisible(false);
		experimentUserStorePanel.setVisible(true);
		experimentSavePanel.setVisible(true);
		experimentClosePanel.setVisible(true);
		experimentEditPanel.setVisible(false);

		errorPrimaryUserPanel.setVisible(true);
		errorAdditionalUserPanel.setVisible(false);

		copyExperimentDataPanel.setVisible(true);

		changeExperimentNameButton.setVisible(false);
		changeExperimentLayoutButton.setVisible(false);
		changeExperimentMediaServerButton.setVisible(false);
		newUserHeadingPanel.setVisible(false);
		addNewUserPanel.setVisible(false);
		saveChangeExperimentButton.setVisible(false);

	}

	@SuppressWarnings("deprecation")
	private void openChangeExperiment() {
		userExperimentsHeading.setText("General Settings");
		userExperimentsCollectionPanel.setVisible(false);
		userNewExperimentHeading.setVisible(false);
		saveChangeExperimentButton.setVisible(true);
		existingUserHeadingLabel.setVisible(true);
		experimentUserStoreHeadingLabel.setVisible(false);
		experimentUserEditPanel.setVisible(true);
		experimentUserStorePanel.setVisible(false);
		experimentSavePanel.setVisible(false);
		experimentClosePanel.setVisible(false);
		experimentEditPanel.setVisible(true);
		errorPrimaryUserPanel.setVisible(false);
		errorAdditionalUserPanel.setVisible(true);
		changeExperimentNameButton.setVisible(true);
		changeExperimentLayoutButton.setVisible(true);
		changeExperimentMediaServerButton.setVisible(true);
		newUserHeadingPanel.setVisible(true);
		addNewUserPanel.setVisible(true);
		copyExperimentDataPanel.setVisible(false);
		if (copyExperimentDataCheckBox.isChecked()) {
			copyExperimentDataCheckBox.setChecked(false);
		}
		experimentDataList.setVisible(false);

		newUserStoreNameTextBox.setText("");
		newUserStorePassTextBox.setText("");
	}

	private void getExperimentData() {

		String sql = "Select * from experiment where id = " + editExpId;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {

					// get name and media server
					experimentNameTextBox.setText(result[0][1]);
					experimentMediaStorageTextBox.setText(result[0][5]);
					// get layout
					for (int i = 0; i < experimentLayoutList.getItemCount(); i++) {
						if (experimentLayoutList.getValue(i).equals(
								result[0][6])) {
							experimentLayoutList.setItemSelected(i, true);
						}

					}
					// get users
					getExperimentUsers();

				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void getExperimentUsers() {

		String sql = "Select * from user inner join experimentuser where user.id = experimentuser.userid and experimentuser.expid = "
				+ editExpId + " and role = 2 order by id asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				experimentUserEditPanel.clear();
				experimentUserPanels.clear();
				experimentUserUserTextBoxes.clear();
				experimentUserPassTextBoxes.clear();
				experimentUserPassLabels.clear();
				experimentUserChangeUserButtons.clear();
				experimentUserDeleteUserButtons.clear();

				if (result != null) {

					for (int i = 0; i < result.length; i++) {

						experimentUserPanels.add(i, new HorizontalPanel());

						experimentUserUserTextBoxes.add(i, new Label());
						experimentUserUserTextBoxes.get(i)
								.setText(result[i][1]);
						experimentUserUserTextBoxes.get(i).setStyleName(
								"textleft");
						experimentUserUserTextBoxes.get(i).setWidth("136px");
						experimentUserPassLabels
								.add(i, new Label("Password: "));
						experimentUserPassLabels.get(i).setStyleName(
								"labelMiddle");
						experimentUserPassTextBoxes.add(i, new TextBox());
						experimentUserPassTextBoxes.get(i)
								.setText(result[i][2]);
						experimentUserPassTextBoxes.get(i).setStyleName(
								"textLeft");

						experimentUserChangeUserButtons.add(i, new Button(
								"Change"));
						experimentUserChangeUserButtons.get(i).setStyleName(
								"button");
						addUserChangeButton(
								experimentUserChangeUserButtons.get(i),
								Integer.parseInt(result[i][0]), i);

						experimentUserDeleteUserButtons.add(i, new Button(
								"Delete"));
						experimentUserDeleteUserButtons.get(i).setStyleName(
								"button");
						addUserDelButton(
								experimentUserDeleteUserButtons.get(i),
								Integer.parseInt(result[i][0]), i);

						// turn delete button of for the first user
						if (i == 0) {
							experimentUserDeleteUserButtons.get(i).setVisible(
									false);
						}

						experimentUserPanels.get(i).add(
								experimentUserUserTextBoxes.get(i));
						experimentUserPanels.get(i).add(
								experimentUserPassLabels.get(i));
						experimentUserPanels.get(i).add(
								experimentUserPassTextBoxes.get(i));
						experimentUserPanels.get(i).add(
								experimentUserChangeUserButtons.get(i));
						experimentUserPanels.get(i).add(
								experimentUserDeleteUserButtons.get(i));

						experimentUserEditPanel
								.add(experimentUserPanels.get(i));

					}

					// get free-text tab
					getExperimentFreeTextTab();

				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void getExperimentFreeTextTab() {

		String sql = "Select freetext from experiment where id = " + editExpId;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			@SuppressWarnings("deprecation")
			public void onSuccess(String[][] result) {

				if (result != null) {
					if (Integer.parseInt(result[0][0]) == 1) {
						freeTextTabCheckBox.setChecked(true);
					} else {
						freeTextTabCheckBox.setChecked(false);
					}
				}

				openChangeExperiment();
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void addUserDelButton(Button b, final int userid, final int panel) {
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				experimentUserEditPanel.remove(panel);
				deleteUserData(userid);
			}
		});

	}

	private void addUserChangeButton(Button b, final int userid, final int panel) {
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				changeUserData(userid, panel);
			}
		});

	}

	private void changeExperimentData() {

		String expName = clearHiven(experimentNameTextBox.getText());
		String expServer = clearHiven(experimentMediaStorageTextBox.getText());

		String sql = "Update experiment set name = '"
				+ expName
				+ "', mediaserver = '"
				+ expServer
				+ "', layout = "
				+ experimentLayoutList.getValue(experimentLayoutList
						.getSelectedIndex()) + " where id = " + editExpId;

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				updateFreeTextTab();
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void changeUserData(int uId, int pId) {

		String sql = "Update user set name = '"
				+ experimentUserUserTextBoxes.get(pId).getText() + "', pw = '"
				+ experimentUserPassTextBoxes.get(pId).getText()
				+ "' where id = " + uId;

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

	private void deleteUserData(int uId) {

		String sql = "Delete from user where id = " + uId + ";";
		sql = sql + "Delete from experimentuser where userid = " + uId + ";";

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

	private void addUser(String user, String pw) {

		String sql = "Insert into user (name, pw, role) values ('" + user
				+ "', '" + pw + "', 2); ";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				getLastUser();
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void getLastUser() {

		String sql = "Select id from user order by id desc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				if (result != null) {
					insertExperimentUser(Integer.parseInt(result[0][0]));
				}
			}
		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	private void insertExperimentUser(final int uId) {

		String sql = "Insert into experimentuser values (" + editExpId + ", "
				+ uId + "); ";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String result) {
				getExperimentUsers();
				addNewUser(uId);
				newUserStoreNameTextBox.setText("");
				newUserStorePassTextBox.setText("");
			}

		};

		databaseAccessSvc.storeData(sql, callback);
	}

	private void addNewUser(int user) {
		// start experiment list for organizing experiments
		// create elements
		int i = experimentUserPanels.size();

		experimentUserPanels.add(i, new HorizontalPanel());

		experimentUserUserTextBoxes.add(i, new Label());
		experimentUserUserTextBoxes.get(i).setText(
				newUserStoreNameTextBox.getText());
		experimentUserUserTextBoxes.get(i).setStyleName("textleft");
		experimentUserUserTextBoxes.get(i).setWidth("250px");
		experimentUserPassLabels.add(i, new Label("Password: "));
		experimentUserPassLabels.get(i).setStyleName("labelMiddle");
		experimentUserPassTextBoxes.add(i, new TextBox());
		experimentUserPassTextBoxes.get(i).setText(
				newUserStorePassTextBox.getText());
		experimentUserPassTextBoxes.get(i).setStyleName("textLeft");

		experimentUserChangeUserButtons.add(i, new Button("Change"));
		experimentUserChangeUserButtons.get(i).setStyleName("button");
		addUserChangeButton(experimentUserChangeUserButtons.get(i), user, i);

		experimentUserDeleteUserButtons.add(i, new Button("Delete"));
		experimentUserDeleteUserButtons.get(i).setStyleName("button");
		addUserDelButton(experimentUserDeleteUserButtons.get(i), user, i);

		experimentUserPanels.get(i).add(experimentUserUserTextBoxes.get(i));
		experimentUserPanels.get(i).add(experimentUserPassLabels.get(i));
		experimentUserPanels.get(i).add(experimentUserPassTextBoxes.get(i));
		experimentUserPanels.get(i).add(experimentUserChangeUserButtons.get(i));
		experimentUserPanels.get(i).add(experimentUserDeleteUserButtons.get(i));

		experimentUserEditPanel.add(experimentUserPanels.get(i));
	}

	public void setSlotCountCopy(int slotCountCopy) {
		this.slotCountCopy = slotCountCopy;
	}

	public int getSlotCountCopy() {
		return slotCountCopy;
	}

	private void loadExperimentEditPanel() {

		String sql = "Select * from experiment where userid = " + user
				+ " order by id asc";

		// Initialize the service remote procedure call
		if (databaseAccessSvc == null) {
			databaseAccessSvc = GWT.create(DatabaseAccess.class);
		}

		AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(String[][] result) {

				userExperimentsPanels.clear();
				userExperimentLabels.clear();
				userExperimentDelButtons.clear();
				userExperimentEditButtons.clear();
				userExperimentsCollectionPanel.clear();
				experimentDataList.clear();

				if (result != null) {

					for (int i = 0; i < result.length; i++) {

						// start experiment list for organizing experiments
						// create elements
						userExperimentsPanels.add(i, new HorizontalPanel());
						userExperimentLabels.add(i, new Label(result[i][1]));
						userExperimentDelButtons.add(i, new Button("Delete"));
						userExperimentEditButtons.add(i, new Button("Edit"));
						// add click handler and style
						addExperimentDelButton(userExperimentDelButtons.get(i),
								Integer.parseInt(result[i][0]), i);
						addExperimentEditButton(
								userExperimentEditButtons.get(i),
								Integer.parseInt(result[i][0]), i);
						userExperimentLabels.get(i).setWidth("150px");
						userExperimentDelButtons.get(i).setStyleName("button");
						userExperimentEditButtons.get(i).setStyleName("button");

						// add them to the panel
						userExperimentsPanels.get(i).add(
								userExperimentLabels.get(i));
						userExperimentsPanels.get(i).add(
								userExperimentDelButtons.get(i));
						userExperimentsPanels.get(i).add(
								userExperimentEditButtons.get(i));

						// add them to the collection panel
						userExperimentsCollectionPanel
								.add(userExperimentsPanels.get(i));
						// end list

						// allow copying experiment data
						experimentDataList.addItem(result[i][1], result[i][0]);
					}

				}

				experimentNamePanel.clear();
				experimentNamePanel.add(experimentNameLabel);
				experimentNameLabel.setStyleName("label");
				experimentNameLabel.setWidth("120px");
				experimentNamePanel.add(experimentNameTextBox);
				experimentNameTextBox.setStyleName("text");
				changeExperimentNameButton.setStyleName("button");
				changeExperimentNameButton.setVisible(false);
				experimentLayoutPanel.clear();
				experimentLayoutPanel.add(experimentLayoutLabel);
				experimentLayoutLabel.setStyleName("label");
				experimentLayoutLabel.setWidth("120px");
				experimentLayoutPanel.add(experimentLayoutList);
				experimentLayoutList.setStyleName("list");
				changeExperimentLayoutButton.setStyleName("button");
				changeExperimentLayoutButton.setVisible(false);

				experimentMediaStoragePanel.clear();
				experimentMediaStoragePanel.add(experimentMediaStorageLabel);
				experimentMediaStorageLabel.setStyleName("label");
				experimentMediaStorageLabel.setWidth("120px");
				experimentMediaStoragePanel.add(experimentMediaStorageTextBox);
				experimentMediaStorageTextBox.setWidth("221px");
				changeExperimentMediaServerButton.setStyleName("button");
				changeExperimentMediaServerButton.setVisible(false);

				experimentUserStorePanel.clear();
				experimentUserStorePanel.add(experimentUserStoreNameLabel);
				experimentUserStoreNameLabel.setStyleName("label");
				experimentUserStoreNameLabel.setWidth("70px");
				experimentUserStorePanel.add(experimentUserStoreNameTextBox);
				experimentUserStoreNameTextBox.setWidth("100px");
				experimentUserStoreNameTextBox.setStyleName("textLeft");
				experimentUserStorePanel.add(experimentUserStorePassLabel);
				experimentUserStorePassLabel.setStyleName("labelMiddle");
				experimentUserStorePassLabel.setWidth("50px");
				experimentUserStorePanel.add(experimentUserStorePassTextBox);
				experimentUserStorePassTextBox.setWidth("100px");
				experimentUserStorePassTextBox.setStyleName("textLeft");

				errorPrimaryUserPanel.clear();
				errorPrimaryUserPanel.add(errorPrimaryUserRegistrationLabel);
				errorPrimaryUserRegistrationLabel
						.setStyleName("errorUserRegistrationLabel");
				errorPrimaryUserRegistrationLabel.setText("");

				// add user
				addNewUserPanel.clear();
				addNewUserPanel.add(newUserStoreNameTextBox);
				newUserStoreNameTextBox.setStyleName("text");
				addNewUserPanel.add(newUserStorePassLabel);
				newUserStorePassLabel.setStyleName("labelMiddle");
				addNewUserPanel.add(newUserStorePassTextBox);
				newUserStorePassTextBox.setStyleName("textLeft");
				addNewUserPanel.add(addUserButton);
				addUserButton.setStyleName("button");

				errorAdditionalUserPanel.clear();
				errorAdditionalUserPanel
						.add(errorAdditionalUserRegistrationLabel);
				errorAdditionalUserRegistrationLabel
						.setStyleName("errorUserRegistrationLabel");
				errorAdditionalUserRegistrationLabel.setText("");

				errorPrimaryUserPanel.clear();
				errorPrimaryUserPanel.add(errorPrimaryUserRegistrationLabel);
				errorPrimaryUserRegistrationLabel
						.setStyleName("errorUserRegistrationLabel");
				errorPrimaryUserRegistrationLabel.setText("");

				experimentSavePanel.clear();
				experimentSavePanel.setStyleName("experimentSavePanel");
				experimentSavePanel.add(saveNewExperimentButton);
				saveNewExperimentButton.setStyleName("button");

				experimentEditPanel.clear();
				experimentEditPanel.setStyleName("experimentEditClosePanel");
				cancelChangeExperimentButton.setStyleName("button");
				experimentEditPanel.add(cancelChangeExperimentButton);
				experimentEditPanel.setVisible(false);

				experimentClosePanel.clear();
				experimentClosePanel.setStyleName("experimentClosePanel");
				experimentClosePanel.add(cancelNewExperimentButton);
				cancelNewExperimentButton.setStyleName("button");

				userExperimentsHeading.setStyleName("userExperimentHeading");
				userNewExperimentHeading.setStyleName("userExperimentHeading");

				newUserHeadingPanel.clear();
				newUserHeadingPanel.add(newUserHeadingLabel);

				// copying experiment data
				copyExperimentDataPanel.clear();
				copyExperimentDataPanel.add(copyExperimentDataCheckBox);
				copyExperimentDataPanel.add(experimentDataList);
				experimentDataList.setStyleName("experimentDataList");
				experimentDataList.setVisible(false);

				addExperimentPanel.clear();
				addExperimentPanel.add(userExperimentsHeading);
				addExperimentPanel.add(userExperimentsCollectionPanel);
				userExperimentsCollectionPanel.setStyleName("marginBottom20");
				noExperimentsPanel.add(noExperimentsLabel);
				addExperimentPanel.add(noExperimentsPanel);
				noExperimentsPanel.setStyleName("marginBottom20");
				addExperimentPanel.add(userNewExperimentHeading);
				addExperimentPanel.add(experimentNamePanel);
				experimentNamePanel.setStyleName("marginBottom05");
				addExperimentPanel.add(freeTextTabCheckBox);
				freeTextTabCheckBox.setStyleName("marginBottom05");
				saveChangeExperimentButton
						.setStyleName("saveChangeExperimentButton");
				addExperimentPanel.add(saveChangeExperimentButton);
				addExperimentPanel.add(experimentUserStorePanel);
				experimentUserStorePanel.setStyleName("marginTop05");
				addExperimentPanel.add(experimentUserStoreHeadingLabel);
				addExperimentPanel.add(existingUserHeadingLabel);
				addExperimentPanel.add(errorPrimaryUserPanel);
				addExperimentPanel.add(experimentUserEditPanel);
				existingUserHeadingLabel
						.setStyleName("existingUserHeadingLabel");
				addExperimentPanel.add(newUserHeadingPanel);
				addExperimentPanel.add(addNewUserPanel);
				addExperimentPanel.add(errorAdditionalUserPanel);
				addExperimentPanel.add(experimentSavePanel);
				addExperimentPanel.add(experimentClosePanel);
				addExperimentPanel.add(experimentEditPanel);
				addExperimentPanel.setStyleName("addExperimentPanel");

				newUserHeadingPanel.setVisible(false);
				addNewUserPanel.setVisible(false);
				existingUserHeadingLabel.setVisible(false);
				saveChangeExperimentButton.setVisible(false);

				loadExperiments();

			}

		};

		databaseAccessSvc.retrieveData(sql, callback);

	}

	@SuppressWarnings("deprecation")
	private void updateFreeTextTab() {

		String sql = "";

		if (freeTextTabCheckBox.isChecked()) {
			sql = sql + "Update experiment set freetext = 1 where id = "
					+ editExpId;

		} else {
			sql = sql + "Update experiment set freetext = 0 where id = "
					+ editExpId;
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

	public static void changeVisiblility(int mode) {
		switch (mode) {
		case 0:
			settingsWizardOutMT.setVisible(false);
			mtOut.turnOffComponent();
			break;
		case 1:
			settingsWizardOutMT.setVisible(true);
			break;
		case 2:
			mtIn.turnOffComponent();
			ssOut.turnOffRecSpeech();
			break;
		case 3:
			mtIn.turnOnVisibility(1);
			ssOut.turnOnRecSpeech();
			break;
		case 4:
			asrIn.turnOffCorrectionMode();
			ssOut.turnOffRecSpeech();
			break;
		case 5:
			asrIn.turnOnCorrVisibility(1);
			ssOut.turnOnRecSpeech();
			break;
		case 6:
			ssOut.turnOffRecSpeech();
			break;
		case 7:
			ssOut.turnOnRecSpeech();
			break;
		}
	}

	public void changeTabVisibility() {
		wizardInterface.changeVisibility();
	}

	private String clearHiven(String source) {
		source = source.replaceAll("\"", "");
		return source.replaceAll("'", " ");
	}

}
