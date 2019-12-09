/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.integrador.hilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 *
 * @author Anthony
 */
public class Integrador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket socketServidor=new ServerSocket(1234);
        ExecutorService service=Executors.newFixedThreadPool(100);
        LOG.info("ESPERANDO CONEXIÓN.........");
        int index=1;
        while(true){
            try{
            service.execute(new Worker(socketServidor.accept()));
            LOG.info("cliente: ".concat(String.valueOf(index)));
            index++;}catch(Exception e){}
        }
    }
    private static final Logger LOG = Logger.getLogger(Integrador.class.getName());
    
}