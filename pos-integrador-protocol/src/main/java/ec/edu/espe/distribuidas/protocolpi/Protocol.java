/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 24/11/2019 - 1:13:44
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi;

/**
 * Interface Protocol que contiene metodos para tratamiento del protocolo
 * @author Paspuel-Torres
 */
public interface Protocol {
    void parse(String text)throws ProtocolParserException;
    String format();
}
