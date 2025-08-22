package com.tss.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tss.model.Computer;
import com.tss.model.Harddisk;

public class ComputerTest {
	public static void main(String[] args) {
//        Harddisk harddisk = new Harddisk(90); //tightly coupled
//        Computer computer = new Computer("Apple", harddisk);
//
//        System.out.println(computer);

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		Harddisk harddisk = context.getBean("harddisk", Harddisk.class);
		Computer computer = context.getBean("computer", Computer.class);

		System.out.println(harddisk);
		System.out.println(computer);

	}
}
