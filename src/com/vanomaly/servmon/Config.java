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

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Config {
	int emailCount = 0;
	public static void main (String[] args) {
		Config c = new Config();
//		RuntimeObj runtimeObj = c.getConfig();
		WebsiteObj websiteobj = c.getConfig();
		System.out.println(websiteobj.website);
		for (int i = 0; i < websiteobj.email.length; i++) {
			System.out.println(websiteobj.email[i]);
		}
//		int count = runtimeObj.count;
//		for (int i = 0; i < count; i++) {
//			System.out.println(runtimeObj.websiteobj[i].website);
//			for (int j = 0; j < runtimeObj.websiteobj[i].email.length; j++) {
//				System.out.println(runtimeObj.websiteobj[i].email[j]);
//			}
//		}
//		for (int i = 0; i < count; i++) {
//			System.out.println(runtimeObj.websiteobj[i].website[i]);
//			System.out.println(runtimeObj.emailobj[i].email[i]);
//		}
	}
//	public RuntimeObj getConfig() {
	public WebsiteObj getConfig() {
//		final RuntimeObj runtimeObj = new RuntimeObj();
		final WebsiteObj websiteObj = new WebsiteObj();
		try {
			
//			final WebsiteObj websiteObj = new WebsiteObj();
			
//			websiteObj.website = "";
//			runtimeObj.websiteobj = new WebsiteObj[100];
//			runtimeObj.count = 0;
			
//			runtimeObj.websiteobj[runtimeObj.count] = new WebsiteObj();
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {
			
			boolean bwebsite = false;
			boolean bemail = false;
			
			public void startElement(String uri, String localName, String qName,
					Attributes attributes) throws SAXException {
				if (qName.equalsIgnoreCase("WEBSITE")) {
					bwebsite = true;
				}
				if (qName.equalsIgnoreCase("EMAIL")) {
					bemail = true;
//					emailCount++;
				}
			}
			public void endElement(String uri, String localName,
					String qName) throws SAXException {
				if (qName.equalsIgnoreCase("MONITOR")) {
					
//					runtimeObj.websiteobj[runtimeObj.count] = websiteObj;
					
//					runtimeObj.count++;
//					emailCount = 0;
				}
			}
			public void characters(char ch[], int start, int length) throws SAXException {
				if (bwebsite) {
					websiteObj.website = (new String(ch, start, length));
					bwebsite = false;
				}
				if (bemail) {
					websiteObj.email[emailCount] = (new String(ch, start, length));
					emailCount++;
					bemail = false;
				}
			}
		};
		saxParser.parse("config.xml", handler);
		emailCount = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return runtimeObj;
		return websiteObj;
	}
}
