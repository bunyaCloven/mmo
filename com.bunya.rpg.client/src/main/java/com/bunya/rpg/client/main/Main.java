package com.bunya.rpg.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Main extends SimpleApplication {
	Material mat;
	CloseableHttpClient client = HttpClients.createDefault();
	// CloseableHttpResponse resp;
	Socket s;

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		Main app = new Main();
		// HttpGet get = new HttpGet("http://localhost:8080/DaRpg");
		// app.resp = app.client.execute(get);
		app.s = new Socket("127.0.0.1", 8080);
		PrintWriter w = new PrintWriter(app.s.getOutputStream(), true);
		w.write("HELLO");
		app.start();
	}

	public void getData() {
		synchronized (this) {
			while (true) {
				String color = "FF00FFFF";
				try {
					BufferedReader input = new BufferedReader(
							new InputStreamReader(s.getInputStream()));
					color = input.readLine();
				} catch (IOException e) {
				}
				if (color.startsWith("ffffffff") && !color.endsWith("ffffffff")) {
					color = color.substring(8);
				}
				if (color.length() < 8) {
					color = "00000000".substring(color.length()) + color;
				}
				System.out.println(color);
				float red = Integer.parseInt(color.substring(0, 2), 16);
				float green = Integer.parseInt(color.substring(2, 4), 16);
				float blue = Integer.parseInt(color.substring(4, 6), 16);
				float alpha = Integer.parseInt(color.substring(6, 8), 16);
				System.out
						.println(red + " " + green + " " + blue + " " + alpha);
				mat.setColor("Color", new ColorRGBA(red / 0xff, green / 0xff,
						blue / 0xff, 1));
			}
		}
	}

	@Override
	public void simpleInitApp() {
		Box b = new Box(1, 1, 1);
		Geometry geom = new Geometry("Box", b);

		mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

		geom.setMaterial(mat);
		Thread t;
		t = new Thread(() -> getData());
		t.start();
		rootNode.attachChild(geom);

	}

	@Override
	public void simpleUpdate(float tpf) {
		// try {
		// System.out.println(EntityUtils.toString(resp.getEntity()));
		// } catch (ParseException e) {
		// // e.printStackTrace();
		// } catch (IOException e) {
		// // e.printStackTrace();
		// }

	}

	@Override
	public void simpleRender(RenderManager rm) {
	}
}
