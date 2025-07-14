package com.tss.behavioural.observer.model;

public class WhatsappNotifier implements INotifier {

	@Override
	public void sendNotification(Account account, String message) {
		// TODO Auto-generated method stub
		System.out.println("[WhatsApp] " + message + " | Balance : " + account.getBalance());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "WhatsApp";
	}

}
