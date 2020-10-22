/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.snmp4j.spring.boot;

import java.util.ArrayList;
import java.util.List;

public class SnmpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		SnmpTest test = new SnmpTest();
		test.testGet();

	}

	public void testGet() {
		String ip = "127.0.0.1";
		String community = "public";
		String oidval = "1.3.6.1.2.1.1.6.0";
		SnmpData.snmpGet(ip, community, oidval);
	}

	public void testGetList() {
		String ip = "127.0.0.1";
		String community = "public";
		List<String> oidList = new ArrayList<String>();
		oidList.add("1.3.6.1.2.1.1.5.0");
		oidList.add("1.3.6.1.2.1.1.7.0");
		SnmpData.snmpGetList(ip, community, oidList);
	}

	public void testGetAsyList() {
		String ip = "127.0.0.1";
		String community = "public";
		List<String> oidList = new ArrayList<String>();
		oidList.add("1.3.6.1.2.1");
		oidList.add("1.3.6.1.2.12");
		SnmpData.snmpAsynGetList(ip, community, oidList);
		System.out.println("i am first!");
	}

	public void testWalk() {
		String ip = "127.0.0.1";
		String community = "public";
		String targetOid = "1.3.6.1.2.1.1.5.0";
		SnmpData.snmpWalk(ip, community, targetOid);
	}

	public void testAsyWalk() {
		String ip = "127.0.0.1";
		String community = "public";
		// 异步采集数据
		SnmpData.snmpAsynWalk(ip, community, "1.3.6.1.2.1.25.4.2.1.2");
	}

	public void testSetPDU() throws Exception {
		String ip = "127.0.0.1";
		String community = "public";
		SnmpData.setPDU(ip, community, "1.3.6.1.2.1.1.6.0", "jianghuiwen");
	}

	public void testVersion() {
		System.out.println(org.snmp4j.version.VersionInfo.getVersion());
	}
}