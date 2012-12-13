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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPMon {
	public long pingUrl(final String address) {
		try {
			final URL url = new URL("http://" + address);
			final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setConnectTimeout(9999); // Timeout in MilliSeconds
			final long startTime = System.currentTimeMillis();
			urlConn.connect();
			final long endTime = System.currentTimeMillis();
			if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				long responseTime = (endTime - startTime);
				return responseTime;
			} else if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return 333666333L;
			}
			if (urlConn != null) {
				urlConn.disconnect();
			}
		} catch (final MalformedURLException e1) {
			System.out.println("You have entered an invalid url, please try again...");
			System.exit(0);
		} catch (final IOException e) {
		//	System.out.println("You have entered an invalid url, please try again...");
		//	System.exit(0);
			return 333666333L;
		}
		return 9999L;
	}
}
