package com.franki.apirest1.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
	
	/*
	 * Metodo que obtiene el archivo que se le pide por parametro del sistema de
	 * archivos
	 */
	public Resource cargarArchivo(String nombreArchivo) throws MalformedURLException;
	
	
	/*
	 * Metodo que guarda el archivo en el el sistema del archivos del sistema
	 */	
	public String guardarArchivo(MultipartFile archivo) throws IOException;
	
	/*
	 * Metodo que elimina el archivo que se le pasa por parametro del sistema de
	 * archivos
	 */	
	public boolean eliminarArchivo(String archivo);
	
	
	
	/*
	 * Metodo que "resuelve" un Path en el sistema de Archivos de la computaora en
	 * el que esta corriendo el servidor, obetiendo el path de la ruta absoluta de donde estan los archivos del la aplicacion 
	 * aparte del nombre del archivo que se le pasa por parametro
	 */		
	public Path getPath(String path,String nombreArchivo); 
	
}
