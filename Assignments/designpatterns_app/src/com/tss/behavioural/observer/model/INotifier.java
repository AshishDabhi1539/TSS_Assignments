package com.tss.behavioural.observer.model;

public interface INotifier {
	void sendNotification(Account account, String message);
	String getName();
}
