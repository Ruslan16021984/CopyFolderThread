package com.gmail.carbit3333333;

public class SingleThread implements Runnable{
	
	private int start;
	private int end;
	private Thread thr;
	private String from;
	private String to;
	
	
	public SingleThread(int start, int end, String from, String to) {
		super();
		this.start = start;
		this.end = end;
		thr = new Thread(this);
		this.from = from;
		this.to = to;
		thr.start();
	}

	public Thread getThr() {
		return thr;
	}


	@Override
	public void run() {
		CopyFileByThreads.copyFiles(start, end, from, to);
		
	}

}
