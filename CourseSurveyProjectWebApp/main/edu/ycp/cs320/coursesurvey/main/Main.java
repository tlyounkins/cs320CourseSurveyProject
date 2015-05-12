package edu.ycp.cs320.coursesurvey.main;

import java.util.Scanner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import edu.ycp.cs320.coursesurvey.persistence.SQLTest;

public class Main {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8081);

		// Create and register a web app context
		WebAppContext handler = new WebAppContext();
		handler.setContextPath("/survey");
		handler.setWar("./war"); // web app is in the war directory of the project
		server.setHandler(handler);
		
		// Use 20 threads to handle requests
		server.setThreadPool(new QueuedThreadPool(20));
		
		// Start the server
		server.start();
		SQLTest.test1();
		//SQLTest.test2();
		//SQLTest.test3();
		//SQLTest.test4();
		//SQLTest.test5();
		//SQLTest.test6();
		//SQLTest.test7();
		//SQLTest.test8();

		
		// Wait for the user to type "quit"
		System.out.println("Web server started, type quit to shut down");
		Scanner keyboard = new Scanner(System.in);
		while (keyboard.hasNextLine()) {
			String line = keyboard.nextLine();
			if (line.trim().toLowerCase().equals("quit")) {
				break;
			}
		}
		
		System.out.println("Shutting down...");
		server.stop();
		server.join();
		System.out.println("Server has shut down, exiting");
	}
}
