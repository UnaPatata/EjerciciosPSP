package pruebas;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import modelo.Archivo;

public class ObjectClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		String server = "127.0.0.1";
		int puerto = 4022;
		Socket cliente = new Socket(server,puerto);
		//Flujo de entrada de objetos
		ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
		//Recibimos objeto
		Archivo archivo = (Archivo) ois.readObject();
		//Creación de los ficheros
		File FICHERO = new File("download/" + archivo.getNombre());
		File DOWNLOAD = new File("download");
		//Imprimo objeto
		System.out.println("Recibido > " + archivo.getNombre() + " ...");
		
		//Escribo el fichero
		DOWNLOAD.mkdir();
		FileOutputStream fos = new FileOutputStream(FICHERO);
		FileInputStream fis = new FileInputStream(archivo.getArchivo());
		byte[] buffer = new byte[(int) archivo.getArchivo().length()];
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		fis.read(buffer);
		bos.write(buffer);
		bos.flush();
		
		fis.close();
		fos.close();
		ois.close();
		cliente.close();
	}
}
