/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Protocolo
 * 
 * Creado: 21/11/2019 - 11:16:41
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por la licencia BSD.
 * 
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi.pos;

import java.util.logging.Logger;

/**
 * Clase MessageParser utilizada para obtener la cabecera y cuerpo del mensaje
 *
 * @author Paspuel
 * @author Torres
 */
public class MessageParser {

    public static MensajeProtocolo parse(String message) throws ProtocolParserException {
        if (message == null || message.length() < 1) {
            throw new ProtocolParserException(ErrorCodesParser.MENSAJE_VACIO, "El mensaje es vacío o nulo.");
        }
        CabeceraPosIntegrador cabeceraMensaje = MessageParser.parseCabecera(message);
        MensajeProtocolo mensaje = MessageProtocoloPosIntegradorFactory.instanceMessage(cabeceraMensaje.getCodigoMensaje(), cabeceraMensaje);
        mensaje.parse(message);
        return mensaje;
    }

    private static CabeceraPosIntegrador parseCabecera(String message) throws ProtocolParserException {
        String partes[] = message.split(Protocol.SEPARADOR2);
        CabeceraPosIntegrador cabecera = new CabeceraPosIntegrador();
        if (partes.length <= 5) {
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + message.length());
        } else {
            if (partes[0].equals("RQ") || partes[0].equals("RS")) {
                cabecera.setTipoMensaje(partes[0]);
            } else {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Tipo Mensaje recibido:" + partes[0].toString());
            }
            if (partes[1].length() == 15) {
                cabecera.setDispositivo(partes[1]);
            } else {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Dispositivo recibido:" + partes[1].toString());
            }

            try {
                cabecera.setCodigoMensaje(Integer.parseInt(partes[2]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en cod_mensaje. Codigo de mensaje recibido: " + partes[2].toString());
            }
            if (partes[3].matches("[0-9]*")) {
                cabecera.setFecha(partes[3]);
            } else {
                throw new ProtocolParserException(ErrorCodesParser.FECHA_SIN_FORMATO,
                        "El mensaje recibido no contiene el formato correcto en fecha. Fecha recibida:" + partes[3].toString());
            }
            try {
                cabecera.setLongitudCuerpo(Integer.parseInt(partes[4]));
                if (cabecera.getLongitudCuerpo()<4) {
                    throw new ProtocolParserException(ErrorCodesParser.LONGITUD_CUERPO_INSUFICIENTE,
                            "El mensaje recibido no contiene la longitud de cuerpo adecuada: Longitud recibida" + partes[4].toString());
                }
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en longitud del cuerpo : Longitud de cuerpo recibido" + partes[4].toString());
            }
        }
        return cabecera;
    }

}
