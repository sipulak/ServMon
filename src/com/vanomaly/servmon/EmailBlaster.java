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
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.vanomaly.jutils.SendEmail;

public class EmailBlaster {
	public void  monitor(int delay, int acDelay) {
		Timer timer = new Timer("Emailer");
		MyTask t = new MyTask();
		RedoAutoConfig redoAC = new RedoAutoConfig();
		timer.schedule(redoAC, 0, (acDelay * 1000));
		timer.schedule(t, 0, (delay * 1000));
		
	}
}
class RedoAutoConfig extends TimerTask {
	AutoConfig ac = new AutoConfig();
	public void run() {
		System.out.println("RUNNING AC!");
		ServMon.avg = ac.autoConfig(15);
	}
}
class MyTask extends TimerTask {
	private int status = 0;
	private int times = 0;
	EmailTemplate emt = new EmailTemplate();
	SendEmail sm = new SendEmail();
	Config c = new Config();
	HTTPMon httpmon = new HTTPMon();
	String message = "";
	boolean sendAlert = false;
	public void run() {
		WebsiteObj websiteObj = c.getConfig();
		System.out.println("\nTesting " + websiteObj.website + " Now...\n");
		// prime to make sure jvm is running well before actual test
		httpmon.pingUrl(websiteObj.website);
		// run test
		long response = httpmon.pingUrl(websiteObj.website);
		if (response == 333666333L) {
			message = emt.badResponseCode(websiteObj.website, response);
			sendAlert = true;
			status = 2;
		} else if (response == 9999L) {
			message = emt.noResponse(websiteObj.website, response);
			sendAlert = true;
			status = 2;
		} else if (response > (ServMon.avg * 1.40)) {
			message = emt.highLatency(websiteObj.website, response);
			sendAlert = true;
			status = 2;
		} else if (response <= ServMon.avg) {
			message = emt.recovery(websiteObj.website, response);
			sendAlert = true;
			status = 1;
		}
		if (sendAlert) {
			times++;
			if (times <= 1) {
				try {
					System.out.println("");
					System.out.println(new Date());
					for (int i = 0; i < websiteObj.email.length; i++) {
						if (websiteObj.email[i] == null) {
							continue;
						}
						if (status == 2) {
							sm.sendGmail(
									emt.subjectLine(websiteObj.website), 
									message,
									websiteObj.email[i]);
							status = 0;
						} else if (status == 1) {
							sm.sendGmail(
									emt.subjectLineRecovery(websiteObj.website), 
									message,
									websiteObj.email[i]);
							status = 0;
						}
					}
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			} else {
				// timer stop
				//this.cancel();
				sendAlert = false;
				times = 0;
			}
		}
	}
}
