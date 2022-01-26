package ru.adm123.proxyLog;

import ru.adm123.logAgent.annotation.LogExecuteTime;

public class TestLogging {

	@LogExecuteTime
	public void calculation(int param) throws InterruptedException {
		System.out.println("calculating...");
		Thread.sleep((long) (Math.random() * 100));
	}

}
