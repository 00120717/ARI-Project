package com.uca.encryption;


public class Vigenere {

    char[][] matriz = new char[10][10];
    String criptograma = null;

    public Vigenere() {
        int codigo = 48;//valor de la A mayuscula
        int interno = 0;

        for (int i = 0; i < 10; i++) {
            //con interno llenaremos los renglones y con codigo las columnas
            interno = codigo;

            //Nuestro alfabeto tendra  26 letras
            for (int j = 0; j < 10; j++) {
                matriz[i][j] = (char) interno;
                interno++;
                //preguntamos si llegamos a la Z
                if (interno > 57) {
                    //SI es asi regresa y comienza de nuevo con A
                    interno = 48;
                }
            }

            codigo++;
            //Preguntamos si llegamos a la Z
            if (codigo > 57) {
                //SI es asi regresa y comienza con la A
                codigo = 48;
            }
        }
    }

    String quitaEspacios(String problema) {
        char[] auxiliar = problema.toCharArray();
        problema = "";
        for (int i = 0; i < auxiliar.length; i++) {
            if (auxiliar[i] != ' ') {
                problema += auxiliar[i];

            }
        }
        return problema;
    }

    public String completa(String faltante, int tamano) {
        int diferencia = 0, indice = 0;
        if (faltante.length() != tamano) {
            //preguntamos si es menor la clave al mensaje
            if (faltante.length() < tamano) {
                diferencia = tamano - faltante.length();
                while (diferencia != 0) {
                    faltante += faltante.charAt(indice);
                    //preguntamos si es el final de la cadena
                    if (indice == (faltante.length() - 1)) {
                        indice = -1;
                    }
                    indice++;
                    diferencia--;
                }
            } else {
                diferencia = faltante.length() - tamano;
                //cortamos la cadena
                faltante = faltante.substring(0, faltante.length() - diferencia);
            }
        }
        return faltante;
    }

    char getCaracter(char mensaje, char clave) {
        int posicionMensaje = 0, posicionClave = 0, indice = 0;
        boolean encontrado = false;
        //buscamos el caracter del mensaje en la columna del criptograma
        while (indice < 10 && !encontrado) {
            if (matriz[0][indice] == mensaje) {
                encontrado = true;
                posicionMensaje = indice;
            }

            indice++;
        }
        encontrado = false;
        indice = 0;
        //buscamos el caracter en el renglon de las claves
        while (indice < 10 && !encontrado) {
            if (matriz[indice][0] == clave) {
                encontrado = true;
                posicionClave = indice;
            }
            indice++;
        }
        //Nos aseguramos que hayamos obtenido algun resultado
        return matriz[posicionClave][posicionMensaje];
    }


    /**
     * cifrado
     **/
    public String cifra(String mensaje, String clave) {
        criptograma = " ";
        //Quitamos espacios en blanco de la clave y del mensaje
        mensaje = quitaEspacios(mensaje);
        clave = quitaEspacios(clave);

        //Convertimos a mayusculas el mensaje y la clave
        mensaje = mensaje.toUpperCase();
        clave = clave.toUpperCase();

        //Completamos el tamano de la clave al tamano del mensaje
        clave = completa(clave, mensaje.length());

        //aplicamos el algoritmo de Vigenere
        for (int i = 0; i < mensaje.length(); i++) {

            criptograma += getCaracter(mensaje.charAt(i), clave.charAt(i));
        }


        return criptograma;
    }

    char getCaracterDescifrado(char cripto, char clave) {

        int posicionMensaje = 0, posicionClave = 0, indice = 0;
        boolean encontrado = false;
        //buscamos el caracter del mensaje en la columna del criptograma
        while (indice < 10 && !encontrado) {
            if (matriz[0][indice] == clave) {
                encontrado = true;
                posicionClave = indice;
            }

            indice++;
        }
        encontrado = false;
        indice = 0;
        //buscamos el caracter en el renglon de las claves
        while (indice < 10 && !encontrado) {
            if (matriz[indice][posicionClave] == cripto) {
                encontrado = true;
                posicionMensaje = indice;
            }
            indice++;
        }
        //Nos aseguramos que hayamos obtenido algun resultado
        return matriz[0][posicionMensaje];
    }

    /**
     * descifra
     **/
    public String descifra(String mensaje, String clave) {
        criptograma = " ";
        //Quitamos espacios en blanco de la clave y del mensaje
        mensaje = quitaEspacios(mensaje);
        clave = quitaEspacios(clave);

        //Convertimos a mayusculas el mensaje y la clave
        mensaje = mensaje.toUpperCase();
        clave = clave.toUpperCase();

        //Completamos el tamano de la clave al tamano del mensaje
        clave = completa(clave, mensaje.length());

        //aplicamos el algoritmo de Vigenere
        for (int i = 0; i < mensaje.length(); i++) {
            criptograma += getCaracterDescifrado(mensaje.charAt(i), clave.charAt(i));
        }


        return criptograma;
    }

}