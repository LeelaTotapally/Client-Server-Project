package com.acme.students.client;

import java.io.DataOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
	private static Logger LOG = null;

	static {
		// Configure logger
		System.setProperty("log4j.configurationFile", "resources/log4j2.xml");
		LOG = LogManager.getLogger();
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		   Properties props = new Properties();
		   String propsFileName = args[0];
		   File file = new File(propsFileName);
		   props.load(new FileReader(file));
		   String hostName = props.getProperty("hostname");
		   String port = props.getProperty("portnumber");
		   Integer finalPort = Integer.parseInt(port);

		try {
			Socket s = new Socket(hostName, finalPort);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			String string =  "action = create" + " &" + "Name = " + "Leela" + " &" + "gender = " + "F" + " &" + "age = 21";                              
			LOG.debug(string);
			dout.writeUTF(string);
			dout.flush();
			dout.close();
			s.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
