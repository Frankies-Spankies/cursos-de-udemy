package com.franki.apirest1.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements IUploadService {

	@Value("${file.directory}")
	private String fileDirectory;

	private static final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);

	public final static String IMAGENES_PUBLICAS = "src/main/resources/static/images";

	@Override
	public Resource cargarArchivo(String nombreArchivo) throws MalformedURLException {
		Path rutaImagen = getPath(fileDirectory, nombreArchivo);

		log.info(rutaImagen.toString());

		Resource recurso = new UrlResource(rutaImagen.toUri());

		// En caso de que por alguna razon se haya borrado la imagen del servidor
		if (!recurso.exists() && !recurso.isReadable()) {

			rutaImagen = getPath(IMAGENES_PUBLICAS, "desconocido.png");

			recurso = new UrlResource(rutaImagen.toUri());

			log.error("No se pudo cargar la imagen" + nombreArchivo);

		}
		return recurso;
	}

	@Override
	public String guardarArchivo(MultipartFile archivo) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString() + '-' + archivo.getOriginalFilename().replace(" ", "");

		/* Nombre random sin espacios en blanco */

		Path ruta = getPath(fileDirectory, nombreArchivo);

		/* Hacer log de la ruta */
		log.info(ruta.toString());

		Files.copy(archivo.getInputStream(), ruta);

		return nombreArchivo;
	}

	@Override
	public boolean eliminarArchivo(String archivo) {
		if (archivo != null && archivo.length() > 0) {

			Path rutaimagenAnterior = Paths
					.get("/Users/francisco/Documents/cursos-de-udemy/spring-angular/spring/files").resolve(archivo)
					.toAbsolutePath();
			File archivoimagenAnterior = rutaimagenAnterior.toFile();
			if (archivoimagenAnterior.exists() && archivoimagenAnterior.canRead()) {
				archivoimagenAnterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String path, String nombreArchivo) {
		return Paths.get(path).resolve(nombreArchivo).toAbsolutePath();
	}

}
