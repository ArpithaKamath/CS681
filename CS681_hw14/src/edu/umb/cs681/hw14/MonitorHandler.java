package edu.umb.cs681.hw14;

public class MonitorHandler implements Runnable {
	private AdmissionControl admissionControl=new AdmissionControl();
	public void run() {
		admissionControl.countCurrentVisitors();
	}
}
