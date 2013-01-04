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

public class ServMon {
	public static long avg = 0L;
	public static void main (String[] args) {
		ServMon servmon = new ServMon();
		servmon.initialize();
	}
	public void initialize() {
		EmailBlaster emb = new EmailBlaster();
		AutoConfig ac = new AutoConfig();
		avg = ac.autoConfig(15);
		emb.monitor(900, 1800);
	}
}
