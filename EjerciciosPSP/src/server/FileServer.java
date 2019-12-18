package server;

import java.io.IOException;
import java.net.*;

public class FileServer {
	
	public static void main(String[] args) {
		try {
			int puerto = 4020;
			ServerSocket servidor = new ServerSocket(puerto);
			System.out.println("Esperando clientes...");
			while(true) {
				Socket cliente = servidor.accept();
				FileService servicio = new FileService(cliente);
				servicio.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}

