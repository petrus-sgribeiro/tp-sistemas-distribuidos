package client;

import client.view.ChatApp;
import client.view.viewApp;
import client.view.viewLogin;
import common.User;

public class main {
	public static void main(String[] args)
	{
		ChatApp window = new ChatApp();
		window.setScreen(new viewLogin(window));
		//window.setScreen(new viewApp(new User("","","")));
	}
	
}
