package pruebas;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelo.Archivo;

public class ServicioCliente extends Thread{
	
	Socket s;
	
	public ServicioCliente(Socket cliente) {
		this.s = cliente;
	}
	
	public void run() {
		System.out.println("Cliente conectado "  + s.getRemoteSocketAddress());
		try {
			ObjectOutputStream sendObject = new ObjectOutputStream(s.getOutputStream());
			//Creamos y enviamos un objeto
			File archivoEnviar = new File("/home/dam18-20/eclipse-workspace/EjerciciosPSP/megumin_wallpaper.jpg");
			Archivo archivo = new Archivo(archivoEnviar);
			sendObject.writeObject(archivo);
			System.out.println("Server enviando " + archivo.getNombre() + " ...");
			
			sendObject.close();
			s.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
