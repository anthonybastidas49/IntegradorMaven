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
package ec.edu.espe.distribuidas.protocolpi;

/**
 * 
 * @author Torres
 * @author Paspuel
 */
public class MessageFormat {
    public String format(MensajeProtocolo message) throws ProtocolParserException {
        CabeceraPosIntegrador cabecera=message.getCabecera();
        try{
                   return new StringBuilder(cabecera.getTipoMensaje().concat("|").concat(cabecera.getDispositivo()).concat("|")
                .concat(cabecera.getCodigoMensaje().toString()).concat("|").concat(cabecera.getFecha()).concat("|")
                .concat(cabecera.getLongitudCuerpo().toString()).concat("|")).append(message.format()).toString(); 
        }catch(Exception ex){
            throw new ProtocolParserException(ErrorCodesParser.MENSAJE_VACIO, "El mensaje es vacío o nulo.");
        }

        
    }
}