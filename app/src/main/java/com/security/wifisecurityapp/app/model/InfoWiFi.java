package com.security.wifisecurityapp.app.model;

public class InfoWiFi{
	private String ssid;
	private int frequency;
	private String capabilities;
	private long timestamp;
	private int rssi;
	private float securityofRouter;
	
	public float getSecurityofRouter() {
		return securityofRouter;
	}
	public void setSecurityofRouter(float securityofRouter) {
		this.securityofRouter = securityofRouter;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(String encryption) {
		this.capabilities = encryption;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

}
