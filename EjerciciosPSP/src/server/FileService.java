package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FileService extends Thread{
	Socket s;
	Scanner in;
	PrintWriter out;
	File ficheroEnviar;
	FileInputStream fis;
	OutputStream os;
	BufferedInputStream bis;
	
	public FileService(Socket s) {
		this.s = s;
		this.ficheroEnviar = new File("/home/dam18-20/eclipse-workspace/PSP/FileServer/megumin_wallpaper.jpg");
		this.start();
	}
	
	public void run() {
		try {
			byte[] sDatos = new byte[(int) ficheroEnviar.length()];
			fis = new FileInputStream(ficheroEnviar);
			bis = new BufferedInputStream(fis);
			bis.read(sDatos);
			os = s.getOutputStream();
			System.out.println("Enviando archivo...");
			os.write(sDatos);
			
			//Cerrada 
			os.flush();
			os.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
