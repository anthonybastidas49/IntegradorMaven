/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.integrador.dao.servicios;

import ec.edu.espe.integrador.modelo.Banco;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author mayra
 */
public class RedBancoService {
    public String redireccionamiento(String request, Banco banco){
        InputStream inp = null;
        String respuesta="";
        try (Socket socketBanco = new Socket(banco.getIp(), banco.getPuerto())) {
            PrintWriter printWriter = new PrintWriter(socketBanco.getOutputStream());
            printWriter.write(request);
            printWriter.flush();
            respuesta = (new BufferedReader(new InputStreamReader(socketBanco.getInputStream()))).readLine();
            socketBanco.close();
        }catch(Exception e){
            
        }
        return respuesta;
    }
}
