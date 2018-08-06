package main.modes.server.run;

public class SrvPersistanceVariables {

	boolean serverSwitchedOn; 
	int port; 
	String url; 
	
	public boolean isServerSwitchedOn() {
		return serverSwitchedOn;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setServerSwitchedOn(boolean serverSwitchedOn) {
		this.serverSwitchedOn = serverSwitchedOn;
	} 
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setUrl(String url) {
		this.url = url;
	} 
	
}
