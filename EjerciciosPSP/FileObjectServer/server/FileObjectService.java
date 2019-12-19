package server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelo.Archivo;

public class FileObjectService extends Thread{
	
	Socket s;
	
	public FileObjectService(Socket cliente) {
		this.s = cliente;
	}
	
	public void run() {
		System.out.println("Cliente conectado "  + s.getRemoteSocketAddress());
		try {
			ObjectOutputStream sendObject = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream receiveObject;
			//Creamos y enviamos un objeto
			//Dato de prueba: upload/megumin_wallpaper.jpg
			File archivoEnviar;
			Archivo archivo = new Archivo();
			sendObject.writeObject(archivo);
			boolean archivoEnviado = false;
			while (!archivoEnviado) {
				receiveObject = new ObjectInputStream(s.getInputStream());
				archivo = (Archivo) receiveObject.readObject();
				archivoEnviar = new File(archivo.getRuta());
				if (archivoEnviar.exists()) {
					archivo.setArchivo(archivoEnviar);
					archivo.setNombre(archivoEnviar.getName());
					archivoEnviado = true;
					sendObject.writeObject(archivo);
					
				} else {
					archivo.setRuta(null);
					archivo.setArchivo(null);
					archivo.setNombre(null);
					sendObject.writeObject(archivo);
				}
			}
			System.out.println("Server enviando " + archivo.getNombre() + " ...");
			
			sendObject.close();
			s.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
