/*******************************************************************************
 *  Copyright 2012 Vanomaly.com and Jason Sipula                               *
 *                                                                             *
 *  Licensed under the Apache License, Version 2.0 (the "License");            *
 *  you may not use this file except in compliance with the License.           *
 *  You may obtain a copy of the License at                                    *
 *                                                                             *
 *      http://www.apache.org/licenses/LICENSE-2.0                             *
 *                                                                             *
 *  Unless required by applicable law or agreed to in writing, software        *
 *  distributed under the License is distributed on an "AS IS" BASIS,          *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 *  See the License for the specific language governing permissions and        *
 *  limitations under the License.                                             *
 *******************************************************************************/

package com.vanomaly.servmon;

import java.util.Date;

public class EmailTemplate {
	public String subjectLine(String websiteName) {
		return "ALERT! WEBSITE " + websiteName + " DOWN! | " + new Date();
	}
	public String subjectLineRecovery(String websiteName) {
		return "RECOVERY! Website " + websiteName + " OK! | " + new Date();
	}
	public String recovery(String websiteName, long responseT) {
		return	"\n\t +--------------------------------------+" +
				"\n\t      " + new Date() + "       " +
				"\n\t +--------------------------------------+" +
				"\n\t      WEBSITE RECOVERY: OK !!            " +
				"\n\t +--------------------------------------+" +
				"\n\t      Site: " + websiteName + "          " +
				"\n\t +--------------------------------------+" +
				"\n\t      Response Time: " + responseT + "(ms)" +
				"\n\t +--------------------------------------+" +
				"\n\t      AVG Respones Time: " + ServMon.avg + "(ms)" +
				"\n\t +--------------------------------------+";
	}
	public String noConnection(String websiteName, long responseT) {
		return	"\n\t +--------------------------------------+" +
				"\n\t      " + new Date() + "       " +
				"\n\t +--------------------------------------+" +
				"\n\t      WEBSITE DOWN: No Connection !!     " +
				"\n\t +--------------------------------------+" +
				"\n\t      Site: " + websiteName + "          " +
				"\n\t +--------------------------------------+" +
				"\n\t      Response Time: " + responseT + "(ms)" +
				"\n\t +--------------------------------------+" +
				"\n\t      AVG Respones Time: " + ServMon.avg + "(ms)" +
				"\n\t +--------------------------------------+";
	}
	public String noResponse(String websiteName, long responseT) {
		return "\n\t +--------------------------------------+" +
				"\n\t      " + new Date() + "       " +
				"\n\t +--------------------------------------+" +
				"\n\t      WEBSITE DOWN: No Response !!       " +
				"\n\t +--------------------------------------+" +
				"\n\t      Site: " + websiteName + "          " +
				"\n\t +--------------------------------------+" +
				"\n\t      Response Time: " + responseT + "(ms)" +
				"\n\t +--------------------------------------+" +
				"\n\t      AVG Respones Time: " + ServMon.avg + "(ms)" +
				"\n\t +--------------------------------------+";
	}
	public String badResponseCode(String websiteName, long responseT) {
		return "\n\t +--------------------------------------+" +
				"\n\t      " + new Date() + "       " +
				"\n\t +--------------------------------------+" +
				"\n\t      WEBSITE DOWN: Bad Response !!      " +
				"\n\t +--------------------------------------+" +
				"\n\t      Site: " + websiteName + "          " +
				"\n\t +--------------------------------------+" +
				"\n\t      Response Time: " + responseT + "(ms)" +
				"\n\t +--------------------------------------+" +
				"\n\t      AVG Respones Time: " + ServMon.avg + "(ms)" +
				"\n\t +--------------------------------------+";
	}
	public String highLatency(String websiteName, long responseT) {
		return "\n\t +--------------------------------------+" +
				"\n\t      " + new Date() + "       " +
				"\n\t +--------------------------------------+" +
				"\n\t      WEBSITE DOWN: High Latency !!      " +
				"\n\t +--------------------------------------+" +
				"\n\t      Site: " + websiteName + "          " +
				"\n\t +--------------------------------------+" +
				"\n\t      Response Time: " + responseT + "(ms)" +
				"\n\t +--------------------------------------+" +
				"\n\t      AVG Respones Time: " + ServMon.avg + "(ms)" +
				"\n\t +--------------------------------------+";
	}
}
