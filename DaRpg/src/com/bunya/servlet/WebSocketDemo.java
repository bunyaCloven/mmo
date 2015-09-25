package com.bunya.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class WebSocketDemo {
	public static void main(String[] args) throws IOException {
		new WebSocketDemo().run();
	}

	public void run() throws IOException {
		final Random random = new Random();
		ServerSocket listener = new ServerSocket(9090);
		System.out.println("Started at " + listener.getLocalPort());
		while (true) {
			final Socket socket = listener.accept();
			// BufferedReader reader = new BufferedReader(
			// new InputStreamReader(socket.getInputStream()));
			// System.out.println(reader.readLine());
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							synchronized (this) {
								wait(1000);
								PrintWriter out = new PrintWriter(
										socket.getOutputStream(), true);
								String lng = Long.toHexString(random.nextLong()
										% (0xFFL * 0xFF * 0xFF * 0xFF));
								out.println(lng);
								System.out.println(lng);
							}
						} catch (Exception e) {
						}
					}
				}
			}).start();
		}
		// listener.close();
	}
}