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

public class AutoConfig {
	public long autoConfig(int numSetupTest) {
		HTTPMon httpMon = new HTTPMon();
		Config c = new Config();
		WebsiteObj websiteObj = c.getConfig();
		long[] response = new long[numSetupTest];
		long avg = 0L;
		// prime so we don't get any outlier data
		for (int i = 0; i < 5; i++) {
			httpMon.pingUrl(websiteObj.website);
		}
		// run tests and return the average
		for (int i = 0; i < numSetupTest; i++) {
			response[i] = httpMon.pingUrl(websiteObj.website);
		}
		for (int i = 0; i < response.length; i++) {
			avg += response[i];
		}
		return (avg / numSetupTest);
	}
}
