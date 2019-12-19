package modelo;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Archivo implements Serializable{
	private String nombre,ruta;
	private File archivo;
	
	
	public Archivo(File archivo) {
		this.nombre = archivo.getName();
		this.ruta = archivo.getAbsolutePath();
		this.archivo = archivo;
	}
	
	public Archivo() {
		this.nombre = null;
		this.ruta = null;
		this.archivo = null;
	}

	
	//Getters and Setters
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public File getArchivo() {
		return archivo;
	}


	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}
	
}//end-class
