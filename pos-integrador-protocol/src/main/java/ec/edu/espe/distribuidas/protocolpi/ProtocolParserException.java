/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Protocolo
 * Creado: 10/11/2019 - 0:28:29
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi;

/**
 * Clase ProtocolParserException. Creacion de la clase PosIntegradorException
 * con constructores
 *
 * @author Paspuel
 * @author Torres
 */
public class ProtocolParserException extends Exception{
    private Integer codigo;

    public ProtocolParserException(Integer codigo) {
        this.codigo = codigo;
    }

    public ProtocolParserException(Integer codigo, String message) {
        super(message);
        this.codigo = codigo;
    }

    public ProtocolParserException(Integer codigo, String message, Throwable cause) {
        super(message, cause);
        this.codigo = codigo;
    } 
}
