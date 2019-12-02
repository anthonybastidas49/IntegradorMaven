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
package ec.edu.espe.distribuidas.protocolpi.banco;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase MessageProtocolPosIntegradorFactory, utilizada para obtener los
 * protocolos segun el código.
 *
 * @author Paspuel
 * @author Torres
 */
public class MessageProtocoloIntegradorBancoFactory {

    private static final String REQUEST = "RQ";
    private static final String RESPONSE = "RS";

    public static MensajeProtocolo instanceMessage(Integer codigo, CabeceraIntegradorBanco cabecera) {
        switch (codigo) {
            case MessagesIntegradorBanco.COMPRA_BANCO_RQ:
                return new CompraIntegradorBancoReq(cabecera);
            case MessagesIntegradorBanco.COMPRA_BANCO_RS:
                return new CompraIntegradorBancoRes(cabecera);
            case MessagesIntegradorBanco.CANCELACION_BANCO_RQ:
                return new CancelacionIntegradorBancoReq(cabecera);
            case MessagesIntegradorBanco.CANCELACION_BANCO_RS:
                return new CancelacionIntegradorBancoRes(cabecera);
            default:
                break;
        }
        return null;
    }

    public static MensajeProtocolo instanceMessageFormat(Integer codigo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        CabeceraIntegradorBanco cabecera = new CabeceraIntegradorBanco();
        cabecera.setCodigoMensaje(codigo);
        cabecera.setFecha(sdf.format(new Date()));
        switch (codigo) {
            case MessagesIntegradorBanco.COMPRA_BANCO_RQ:
                cabecera.setTipoMensaje(REQUEST);
                return new CompraIntegradorBancoReq(cabecera);
            case MessagesIntegradorBanco.COMPRA_BANCO_RS:
                cabecera.setTipoMensaje(RESPONSE);
                return new CompraIntegradorBancoRes(cabecera);
            case MessagesIntegradorBanco.CANCELACION_BANCO_RQ:
                cabecera.setTipoMensaje(REQUEST);
                return new CancelacionIntegradorBancoReq(cabecera);
            case MessagesIntegradorBanco.CANCELACION_BANCO_RS:
                cabecera.setTipoMensaje(RESPONSE);
                return new CancelacionIntegradorBancoRes(cabecera);
            default:
                break;
        }
        return null;
    }
}
