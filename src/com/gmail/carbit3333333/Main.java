package com.gmail.carbit3333333;

public class Main {

	public static void main(String[] args) {
		String from = "E:\\Работа\\Увви";
		String to = "E:\\Работа\\Увви\\folder";
		int threads = 4;
		CopyFileByThreads.Threads(from, to, threads);

	}

}
