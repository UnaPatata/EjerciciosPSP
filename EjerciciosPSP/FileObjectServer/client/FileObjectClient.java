package client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import modelo.Archivo;

public class FileObjectClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		String server = "127.0.0.1";
		int puerto = 4022;
		Socket cliente = new Socket(server,puerto);
		boolean objetoRecibido = false;
		//Flujo de entrada de objetos
		ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
		while(!objetoRecibido) {
			//Recibimos objeto
			Archivo archivo = (Archivo) ois.readObject();
			if (archivo.getNombre() == null) {
				enviarInformacion(cliente,archivo);
			} else {
				descargarArchivo(archivo);
				objetoRecibido = true;
			}
		}//end-while
		ois.close();
		cliente.close();
	}//end-main

	@SuppressWarnings("resource")
	private static void enviarInformacion(Socket s,Archivo archivo) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		System.out.println("Dime la ruta del archivo: ");
		Scanner teclado = new Scanner(System.in);
		archivo.setRuta(teclado.nextLine());
		oos.writeObject(archivo);
	}

	private static void descargarArchivo(Archivo archivo) throws IOException {
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

		bos.close();
		fis.close();
		fos.close();

	}
}
