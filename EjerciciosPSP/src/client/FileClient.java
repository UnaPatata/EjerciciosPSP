package client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class FileClient {

	public static void main(String[] args) {
		final String SERVER_IP = "127.0.0.1";
		final int SERVER_PORT = 4020;

		final String FICHERO = "download/foto.jpg";
		final int FICHERO_MAX = 104857600; //100MiB
		
		int bytesLeidos;
		FileOutputStream fos;
		BufferedOutputStream bos;
		
		InputStream is;
		Socket s = null;
		byte[] buffer = new byte[FICHERO_MAX];
		
		//recibir archivo
		try {
			//Conecto con el server
			s = new Socket(SERVER_IP,SERVER_PORT);
			is = s.getInputStream();
			
			//Abro el fichero local a grabar
			fos = new FileOutputStream(FICHERO);
			bos = new BufferedOutputStream(fos);
			
			//Leer bytes del socket
			bytesLeidos = is.read(buffer,0,buffer.length);
			int actual = bytesLeidos;
			while (bytesLeidos > -1) {
				bytesLeidos = is.read(buffer,actual,(buffer.length-actual));
				if (bytesLeidos >= 0) {
					actual += bytesLeidos;
				}
			} 			
			//Grabar el buffer
			bos.write(buffer, 0, actual);
			bos.flush();
			System.out.println("Fichero " + FICHERO + " descargado\n" + actual + " bytes leidos");
			bos.close();
			fos.close();
			is.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
