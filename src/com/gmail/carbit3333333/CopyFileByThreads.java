package com.gmail.carbit3333333;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CopyFileByThreads {
	private static ArrayList<String> list = new ArrayList<>();

	// метод возвращает список имен файлов в папке
	private static ArrayList<String> fileList(String initFolder) {
		File title = new File(initFolder);
		String[] tutles = title.list();
		for (String st : tutles) {
			list.add(st);
		}
		return list;
	}

	// метод копирует файлы из ....в....
	private static void copyBytes(File from, File to) {
		try (FileInputStream inputStream = new FileInputStream(from);
				FileOutputStream outputStream = new FileOutputStream(to)) {
			byte[] buffer = new byte[1024];
			int reader;
			do {
				reader = inputStream.read(buffer);
				outputStream.write(buffer);
			} while (reader > 0);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// основной метод который вызывается в потоке RUN
	public static void copyFiles(int start, int end, String initFolder, String destFolder) {
		for (int i = start; i < end; i++) {
			File initFile = new File(initFolder + "/" + list.get(i));
			File destFile = new File(destFolder + "/" + list.get(i));
			copyBytes(initFile, destFile);
			System.out.println(list.get(i) + " is coping from " + initFolder + " to " + destFolder);
		}
	}
	//основной метод который вызывается в MAIN
	public static void Threads(String from, String to, int threads) {
		int start;
		int end;
		fileList(from);
		SingleThread[] pool = new SingleThread[threads];

		int quant = list.size() / threads;
		for (int i = 0; i < threads; i++) {
			start = quant * i;
			end = quant * (i + 1);
			if (i == threads - 1) {
				end = list.size();
			}
			pool[i] = new SingleThread(start, end, from, to);
		}
		for (int i = 0; i < pool.length; i++) {
			try {
				pool[i].getThr().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Files has been copied from " + from + " to " + to);

	}

}
