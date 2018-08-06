package main.modes.server.run;

import main.modes.server.server.Server;

public class ServerMain implements Runnable {

	private static Server srv;
	static int openPort;
	private static String file;
	
	public ServerMain(String file) {
	this.file = file;
	}

	/**
	 * Attempts to start server prints a successful or unsuccessful attempt
	 */
	private static void startServer() {
		int port = 8080;
		srv = new Server(file);
		boolean blResult = false;

		while (!blResult) {
			blResult = srv.open(port);
			if (blResult) {
				System.out.println("server started on " + port);
				openPort = port;
			} else {
				port++;
				System.out.println("server start error");
			}
		}
	} 
	
	private static void stopServer(){ 
		srv.close(openPort);
	}

	@Override
	public void run() {
		startServer();
	} 
	
	public static int getOpenPort() {
		return openPort;
	}
	
	
}
