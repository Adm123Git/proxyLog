package ru.adm123.proxyLog;

import ru.adm123.logAgent.annotation.Log;

public class TestLogging {

	@Log
	public void calculation(int param) {
		System.out.println("calculating...");
	}

}
