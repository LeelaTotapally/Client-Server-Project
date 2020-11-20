package com.acme.students.server;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.students.IRequest;
import com.acme.students.Request;
import com.acme.students.Response;

public class Server {
	private static Logger LOG = null;

	static {
		// Configure logger
		System.setProperty("log4j.configurationFile", "resources/log4j2.xml");
		LOG = LogManager.getLogger();
	}

	public static void process(IRequest request, Response response) throws IOException {
		String name = request.getObject("Name");
		String age = request.getObject("age");
		String gender = request.getObject("gender");
		String result = name + age + gender;
		//System.out.println(result);
		LOG.debug(result);
		FileWriter fw = new FileWriter("/C:/Users/Dell15/Desktop/java/leela.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(result);
		bw.flush();

		response = new Response("Request has been Processed");
		String output = response.getStatus();
		//System.out.println(output);
		LOG.debug(output);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		IRequest request = new Request();
		Properties props = new Properties();
		String propsFileName = args[0];
		File file = new File(propsFileName);
		props.load(new FileReader(file));
		String port = props.getProperty("portnumber");
		Integer finalPort = Integer.parseInt(port);

		ServerSocket ss = new ServerSocket(finalPort);

		while (true) {
			Socket s = null;
			try {
				s = ss.accept();

				//System.out.println("A new client is connected : " + s);
				LOG.debug("A new client is connected : " + s);
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String str = (String) dis.readUTF();
				LOG.debug(str);
				//System.out.println(str);
				String string[] = str.split("&");

				for (int i = 0; i < string.length; i++) {
					String[] finalArray = string[i].split("=");

					// hashMap.put(finalArray[j].trim(), finalArray[j + 1].trim());
					request.addParam(finalArray[0].trim(), finalArray[1].trim());

				}
				Response response = null;
				Server.process(request, response);

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				s.close();
			}
		}

	}
}
