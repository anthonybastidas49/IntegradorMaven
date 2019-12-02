/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Protocolo
 * Creado: 24/11/2019 - 1:13:44
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi.banco;
/**
 * Interface Protocol que contiene metodos para tratamiento del protocolo
 *
 * @author Paspuel
 * @author Torres
 */
public interface Protocol {

    public static final String SEPARADOR = "|";

    void parse(String text) throws ProtocolParserException;

    String format();
}
