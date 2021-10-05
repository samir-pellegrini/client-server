/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.net.*;

/**
 *
 * @author Giorgio Del Rocca
 */
public class Serverstr {
    ServerSocket server = null;
    Socket client = null;
    String Stringaricevuta = null;
    String StringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi() {
        try {
            System.out.println("1 SEREVER partito in esecuzione ...");
            server = new ServerSocket(6789);// apre la porta
            client = server.accept();// si mette in ascolto la porta
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        return client;
    }

    public void comunica() {
        try {
            System.out.println("3 benvenuto client,scrivi una frase e la trasformo in maiuscolo ,Attendo");
            Stringaricevuta = inDalClient.readLine(); // legge cio che arriva dal client
            System.out.println("6 ricevuta la stringa del cliente:" + Stringaricevuta);
            StringaModificata = Stringaricevuta.toUpperCase();// modifica la stringa in entrata e la mette in maiuscolo
            System.out.println("7 invio la stringa modificata al client");
            outVersoClient.writeBytes(StringaModificata + '\n');// manda lo stream al client
            System.out.println("9 SERVER:fine elaborazione .... buona notte!");
            client.close();// chiude il socket
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1);
        }

    }
}