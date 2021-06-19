package com.uca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

//@Controller
//@RequestMapping("/file/")
public class FileController {
  //  @RequestMapping("upload")
    //@ResponseBody
    public String upload(MultipartFile file) {
        // Obtén el nombre original
        String fileName = file.getOriginalFilename();

        // Obtener el nombre del sufijo
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = "clientes" + suffixName;

        // Ruta para guardar el archivo
        //String filePath = "/app/src/main/resources/pruebaArchivos/";
        String filePath = "/Users/rober/IdeaProjects/ARI-Project/src/main/resources/subidaArchivos/";

        // Cambiar el nombre del archivo para evitar la duplicación
        fileName = filePath + fileName;

        // objeto de archivo
        File dest = new File(fileName);

        // Determinar si la ruta existe y crearla si no existe
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // Guardar en el servidor
            file.transferTo(dest);
            return "Subido con éxito";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al cargar";
    }

    //@RequestMapping("download")
    public void download(HttpServletResponse response) throws Exception {
        // Dirección del archivo, el entorno real se almacena en la base de datos
        File file = new File("/app/src/main/resources/pruebaArchivos/file.txt");
        // Llevando objeto de entrada
        FileInputStream fis = new FileInputStream(file);
        // Establecer el formato relevante
        response.setContentType("application/force-download");
        // Establecer el nombre y el encabezado del archivo descargado
        response.addHeader("Content-disposition", "attachment;fileName=" + "a.txt");
        // Crear objeto de salida
        OutputStream os = response.getOutputStream();
        // operación normal
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }


}


