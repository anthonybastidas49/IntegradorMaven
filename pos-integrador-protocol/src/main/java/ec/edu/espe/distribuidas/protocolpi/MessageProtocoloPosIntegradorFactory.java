/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Protocolo
 * Creado: 10/11/2019 - 0:57:43
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi;
/**
 * Clase MessageProtocolPosIntegradorFactory, utilizada para obtener los protocolos 
 * segun el código.
 *
 * @author Paspuel
 * @author Torres
 */
public class MessageProtocoloPosIntegradorFactory {
    
    public static MensajeProtocolo instanceMessage(Integer codigo, CabeceraPosIntegrador cabecera){
        switch(codigo){
            case MessagesPosIntegrador.REGISTRO_POS_RQ:
                return new RegistroPosIntegradorReq(cabecera);
            case MessagesPosIntegrador.REGISTRO_POS_RS:
                return new RegistroPosIntegradorRes(cabecera);
            case MessagesPosIntegrador.COMPRA_POS_RQ:
                return new CompraPosIntegradorReq(cabecera);
            case MessagesPosIntegrador.COMPRA_POS_RS:
                return new CompraPosIntegradorRes(cabecera);
            case MessagesPosIntegrador.CANCELACION_POS_RQ:
                return new CancelacionPosIntegradorReq(cabecera);
            case MessagesPosIntegrador.CANCELACION_POS_RS:
                return new CancelacionPosIntegradorRes(cabecera);
            default:
                break;
        }
        return null;
    }
}
