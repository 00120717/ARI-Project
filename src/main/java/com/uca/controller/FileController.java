package com.uca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
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

    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        // Dirección del archivo, el entorno real se almacena en la base de datos
        // File file = new File("/app/src/main/resources/pruebaArchivos/file.txt");
        File file = null;
        System.out.println(MainController.flagFormatD);

        switch (MainController.flagFormatD) {
            case "txt":
                file = new File("src/main/resources/descargaArchivos/clientes.txt");
                break;
            case "xml":
                file = new File("src/main/resources/descargaArchivos/clientes.xml");
                break;
            case "json":
                file = new File("src/main/resources/descargaArchivos/clientes.json");
                break;
            default:
                System.out.println("Not Found File");
        }
        //File file = new File("/app/src/main/resources/pruebaArchivos/file.txt");
        // Llevando objeto de entrada
        FileInputStream fis = new FileInputStream(file);
        // Establecer el formato relevante
        response.setContentType("application/force-download");
        // Establecer el nombre y el encabezado del archivo descargado
       // response.addHeader("Content-disposition", "attachment;fileName=" + "a.txt");

        switch (MainController.flagFormatD) {
            case "txt":
                response.addHeader("Content-disposition", "attachment;fileName=" + "clientes.txt");
                break;
            case "xml":
                response.addHeader("Content-disposition", "attachment;fileName=" + "clientes.xml");
                break;
            case "json":
                response.addHeader("Content-disposition", "attachment;fileName=" + "clientes.json");
                break;
            default:
                System.out.println("Not Found File");
        }


        // Crear objeto de salida
        OutputStream os = response.getOutputStream();
        // operación normal
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
        //mav.setViewName("home2");
        //return mav;
    }


}


