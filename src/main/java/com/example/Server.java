package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        String input = in.readLine();
        
        System.out.println(input);
        File cartella = new File("documenti");
        File file = new File(cartella, input);

        System.out.println(file.getAbsolutePath());
        boolean presente = file.isFile();

        if (presente) {
            BufferedReader lettoreFile = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            out.println("OK FILE");
            out.println();

            String riga;
            while ((riga = lettoreFile.readLine()) != null) {
                out.println(riga);
            }
        }
        else{
            out.println("ERR NOTFOUND");
        }

        s.close();
        ss.close();
    }
}