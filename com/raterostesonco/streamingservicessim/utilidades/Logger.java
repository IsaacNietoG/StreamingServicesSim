package com.raterostesonco.streamingservicessim.utilidades;

import com.raterostesonco.streamingservicessim.Cliente;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final String LOG_FILE = "logs/%s.txt".formatted(formatoFecha.format(new Date()));

    public static void log(Object instancia, String mensaje) {

        // Imprimir primero en consola
        System.out.println(mensaje);

        // Escribir en el archivo txt
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = formatoFecha.format(new Date());
            if(instancia instanceof Cliente) {
                writer.println("[%s] <Cliente: %s> - %s".formatted(timestamp, ((Cliente)instancia).darNombre(), mensaje));
            } else {
                writer.println("[%s] <%s> - %s".formatted(timestamp, instancia.getClass().getSimpleName(), mensaje));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        log(new Object(), "Inicio del programa");
    }
}
