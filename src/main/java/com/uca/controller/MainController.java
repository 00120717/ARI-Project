package com.uca.controller;

import com.uca.convert.Convert_to_delim;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.uca.convert.TxtToJson;
import com.uca.convert.TxtToXml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


@Controller
public class MainController {

    static String flagFormatD = "none";
    static String encriptKey = "none";

    TxtToJson txtToJson = new TxtToJson();
    TxtToXml txtToXml = new TxtToXml();
    Convert_to_delim convert_to_delim = new Convert_to_delim();

    FileController fileController = new FileController();

    @RequestMapping("/")
    public ModelAndView initMain() throws Exception {
        ModelAndView mav = new ModelAndView();

        TxtToXml conv = new TxtToXml();
        TxtToJson cvt = new TxtToJson();
		/*conv.generate("03423423;rober;fuentes;04534534532;GOLD;2343243523\n" + 
				"353452323;alberto;alfaro;0534534523;PLATINUM;3423523432", ';');*/
        //System.out.println(cvt.TextToJson("03423423;rober;fuentes;04534534532;GOLD;2343243523" , ';'));

        mav.setViewName("home2");
        return mav;
    }

    
    @RequestMapping("/send")
    public ModelAndView formTextJson(@RequestParam(value = "adjunto") MultipartFile file, @RequestParam(value = "format") String formatTo, @RequestParam(value = "delimitador") char delim, @RequestParam(value = "Llave") String key) throws Exception {
        ModelAndView mav = new ModelAndView();
        //	System.out.println(format.getContentType() + " " + formatTo );
        
        encriptKey = key; //para jwt

       

        if (file.getContentType().equals("text/plain")) {
            if (formatTo.equals("xml")) {
                //System.out.println("hello txtToXml");
                flagFormatD = "xml";
                txtToXml.generate("03423423;rober;fuentes;04534534532;GOLD;2343243523\n" +
                        "353452323;alberto;alfaro;0534534523;PLATINUM;3423523432", ';');
                
                String generated = "";
                File fr=new File("src/main/resources/descargaArchivos/clientes.xml");
                BufferedReader br = new BufferedReader(new FileReader(fr));
                String st;
                while ((st = br.readLine()) != null) {
                  generated = generated + st + "\n";
                }
                System.out.println("xml " + generated);
             
                
            } if (formatTo.equals("json"))
                //System.out.println("hello txtToJson");
                txtToJson.TextToJson("03423423;rober;fuentes;04534534532;GOLD;2343243523\n" +
                        "353452323;alberto;alfaro;0534534523;PLATINUM;3423523432", ';');
            	flagFormatD = "json";
            	String generated = "";
            	File fr=new File("src/main/resources/descargaArchivos/clientes.json");
                BufferedReader br = new BufferedReader(new FileReader(fr));
                String st;
                while ((st = br.readLine()) != null) {
                  generated = generated + st + "\n";
                }
                System.out.println("json "  +generated);
                
        } else if (file.getContentType().equals("application/json")) {
            //System.out.println("hello jsonToTxt");
            convert_to_delim.jsonToTxt(delim);
            flagFormatD = "txt";
            String generated = "";
            File fr=new File("src/main/resources/descargaArchivos/clientes.txt");
            BufferedReader br = new BufferedReader(new FileReader(fr));
            String st;
            while ((st = br.readLine()) != null) {
              generated = generated + st + "\n";
            }
            System.out.println("jtxt "+generated);

        } else {
            //System.out.println("hello xmlToTxt");
            convert_to_delim.xmlToTxt(delim);
            flagFormatD = "txt";
            String generated = "";
            File fr=new File("src/main/resources/descargaArchivos/clientes.txt");
            BufferedReader br = new BufferedReader(new FileReader(fr));
            String st;
            while ((st = br.readLine()) != null) {
              generated = generated + st + "\n";
            }
            System.out.println("xtxt "+generated);
            
        }

        mav.setViewName("home2");
        mav.addObject("jsontext", "Json text");
        return mav;
    }

}
