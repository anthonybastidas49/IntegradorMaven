/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: protocolpi
 * Creado: 29/11/2019 - 23:20:19
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi.banco;

/**
 * Clase MessageFormat, empleada para realizar el marshal del mensaje.
 *
 * @author Torres
 * @author Paspuel
 */
public class MessageFormat {

    public static String format(MensajeProtocolo message) throws ProtocolParserException {
        CabeceraIntegradorBanco cabecera = message.getCabecera();
        StringBuilder sb = new StringBuilder();
        String body = message.format();
        cabecera.setLongitudCuerpo(body.length());
        sb.append(cabecera.getTipoMensaje());
        sb.append(Protocol.SEPARADOR);
        sb.append(cabecera.getCodigoMensaje());
        sb.append(Protocol.SEPARADOR);
        sb.append(cabecera.getFecha());
        sb.append(Protocol.SEPARADOR);
        sb.append(cabecera.getLongitudCuerpo().toString());
        sb.append(Protocol.SEPARADOR);
        sb.append(body);
        sb.append(Protocol.SEPARADOR);
        return sb.toString();
    }
}
