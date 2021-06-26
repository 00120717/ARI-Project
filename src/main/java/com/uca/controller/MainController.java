package com.uca.controller;

import com.uca.convert.Convert_to_delim;
import com.uca.convert.TxtToXml;
import com.uca.convert.textToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


@Controller
public class MainController {

    public static String generatedFile = "";
    static String flagFormatD = "none";
    public static String encriptKey = "none";

    TxtToXml txtToXml = new TxtToXml();
    Convert_to_delim convert_to_delim = new Convert_to_delim();
    textToJson textToJson = new textToJson();

    fileController fileController = new fileController();

    public String dataGenerated(String dir) throws Exception {

        String generated = "";
        File fr = new File(dir);
        BufferedReader br = new BufferedReader(new FileReader(fr));
        String st;
        while ((st = br.readLine()) != null) {
            generated = generated + st + "\n";
        }
        br.close();

        return generated;
    }

    @RequestMapping("/")
    public ModelAndView initMain() throws Exception {
        ModelAndView mav = new ModelAndView();

        TxtToXml conv = new TxtToXml();

        mav.setViewName("home2");
        return mav;
    }


    @RequestMapping("/send")
    public ModelAndView formTextJson(@RequestParam(value = "adjunto") MultipartFile file, @RequestParam(value = "format") String formatTo, @RequestParam(value = "delimitador") char delim, @RequestParam(value = "Llave") String key) throws Exception {
        ModelAndView mav = new ModelAndView();

        encriptKey = key; //para jwt

        System.out.println(fileController.upload(file));


        if (file.getContentType().equals("text/plain")) {
            if (formatTo.equals("xml")) {
                //System.out.println("hello txtToXml");
                flagFormatD = "xml";
                txtToXml.generate(dataGenerated("/app/src/main/resources/subidaArchivos/clientes.txt"), delim);

                String generated = dataGenerated("/app/src/main/resources/descargaArchivos/clientes.xml");
                System.out.println("xml " + generated);
                generatedFile = generated;
                mav.setViewName("home2");
                mav.addObject("textA_generated", generated);


            }
            if (formatTo.equals("json")) {
                //System.out.println("hello txtToJson");
                textToJson.TextToJson(dataGenerated("/app/src/main/resources/subidaArchivos/clientes.txt"), delim);
                flagFormatD = "json";

                String generated = dataGenerated("/app/src/main/resources/descargaArchivos/clientes.json");
                System.out.println("json " + generated);
                generatedFile = generated;
                mav.addObject("textA_generated", generated);
            }

        } else if (file.getContentType().equals("application/json")) {
            //System.out.println("hello jsonToTxt");
            convert_to_delim.jsonToTxt(delim);
            flagFormatD = "txt";
            String generated = dataGenerated("/app/src/main/resources/descargaArchivos/clientes.txt");
            System.out.println("jtxt " + generated);
            generatedFile = generated;
            mav.addObject("textA_generated", generated);

        } else {
            //System.out.println("hello xmlToTxt");
            convert_to_delim.xmlToTxt(delim);
            flagFormatD = "txt";
            String generated = dataGenerated("/app/src/main/resources/descargaArchivos/clientes.txt");
            System.out.println("xtxt " + generated);
            generatedFile = generated;
            mav.addObject("textA_generated", generated);

        }
        mav.setViewName("home2");
        mav.addObject("gen", generatedFile);


        return mav;
    }

}