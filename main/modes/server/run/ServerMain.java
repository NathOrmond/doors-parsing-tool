package main.modes.server.run;

import main.modes.server.server.Server;

public class ServerMain implements Runnable {

	private static Server srv;
	static int openPort;
	String file;
	
	
	public ServerMain(String file, int port) {
	this.openPort = port;	
	this.file = file;
	}

	/**
	 * Attempts to start server prints a successful or unsuccessful attempt
	 */
	public void startServer() {
		srv = new Server(file);
		boolean blResult = false;
		blResult = srv.open(openPort);	
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
