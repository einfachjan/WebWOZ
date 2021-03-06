package com.webwoz.wizard.server.components;

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

import com.webwoz.wizard.server.Database;
import com.webwoz.wizard.server.SSComp;

public class SSRecordedSpeech extends SSComp {

	private Database db = new Database();
	private String uttText;
	private String uttAudio;
	private String uttMM;
	private String uttTransText;
	private String uttTransAudio;
	private String uttTransMM;

	public SSRecordedSpeech(int mode) {
		super.setName("Recorded Speech");
		super.setMode(mode);
	}

	public String getResult(String text, String ctrl) {
		// ctrl to control whether it is domaindata, recording, or free text
		String sql = "";
		String[][] data;
		switch (Integer.parseInt(ctrl.substring(0, 1))) {
		case 1: // recording
			sql = sql + "Select * from recording where id = " + text + "";
			data = db.retrieveData(sql);
			setUttText(data[0][5]);
			setUttAudio(data[0][6]);
			setUttMM(data[0][7]);
			// if no translation is set, insert source
			if (data[0][8].equals("")) {
				setUttTransText(data[0][5]);
			} else {
				setUttTransText(data[0][8]);
			}
			if (data[0][9].equals("")) {
				setUttTransAudio(data[0][6]);
			} else {
				setUttTransAudio(data[0][9]);
			}
			if (data[0][10].equals("")) {
				setUttTransMM(data[0][7]);
			} else {
				setUttTransMM(data[0][10]);
			}
			break;
		case 2: // dmomaindata
			sql = sql + "Select * from domaindata where id = " + text + "";
			data = db.retrieveData(sql);
			setUttText(data[0][5]);
			setUttAudio(data[0][6]);
			setUttMM(data[0][7]);
			// if no translation is set, insert source
			if (data[0][8].equals("")) {
				setUttTransText(data[0][5]);
			} else {
				setUttTransText(data[0][8]);
			}
			if (data[0][9].equals("")) {
				setUttTransAudio(data[0][6]);
			} else {
				setUttTransAudio(data[0][9]);
			}
			if (data[0][10].equals("")) {
				setUttTransMM(data[0][7]);
			} else {
				setUttTransMM(data[0][10]);
			}
			break;
		case 3: // free text
			setUttText(text);
			setUttAudio("-");
			setUttMM("-");
			setUttTransText(text);
			setUttTransAudio("-");
			setUttTransMM("-");
			break;
		}

		return getUttAudio();
	}

	public void setUttText(String uttText) {
		this.uttText = uttText;
	}

	public String getUttText() {
		return uttText;
	}

	public void setUttMM(String uttMM) {
		this.uttMM = uttMM;
	}

	public String getUttMM() {
		return uttMM;
	}

	public void setUttAudio(String uttAudio) {
		this.uttAudio = uttAudio;
	}

	public String getUttAudio() {
		return uttAudio;
	}

	public void setUttTransText(String uttTransText) {
		this.uttTransText = uttTransText;
	}

	public String getUttTransText() {
		return uttTransText;
	}

	public void setUttTransAudio(String uttTransAudio) {
		this.uttTransAudio = uttTransAudio;
	}

	public String getUttTransAudio() {
		return uttTransAudio;
	}

	public void setUttTransMM(String uttTransMM) {
		this.uttTransMM = uttTransMM;
	}

	public String getUttTransMM() {
		return uttTransMM;
	}

}
