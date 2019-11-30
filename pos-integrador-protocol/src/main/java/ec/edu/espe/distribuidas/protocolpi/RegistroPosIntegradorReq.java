/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Protocolo
 * Creado: 09-nov-2019 - 17:19:10
 * Modificado: 09-nov-2019 - 17:19:10 
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 * 
 */
package ec.edu.espe.distribuidas.protocolpi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase RegistroPosIntegradorReq. Creacion de la clase RegistroPosIntegradorReq
 * con metodos set y get para la implementación del protocolo Registro en
 * Response
 *
 * @author Paspuel
 * @author Torres
 */
public class RegistroPosIntegradorReq extends MensajeProtocolo {

    private Integer pin;

    public RegistroPosIntegradorReq() {
    }

    public RegistroPosIntegradorReq(CabeceraPosIntegrador cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public RegistroPosIntegradorReq(CabeceraPosIntegrador cabecera, Integer pin) {
        super();
        super.cabecera = cabecera;
        this.pin = pin;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    @Override
    public void parse(String text) throws ProtocolParserException {
        String partesRegistro[] = text.split(CabeceraPosIntegrador.SEPARADOR);
        if (partesRegistro.length != 6) {
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        } else {
            this.setCabecera(new CabeceraPosIntegrador(partesRegistro[0], partesRegistro[1], Integer.parseInt(partesRegistro[2]), partesRegistro[3], Integer.parseInt(partesRegistro[4])));
            try {
                this.setPin(Integer.parseInt(partesRegistro[5]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en pin. Pin recibido: " + partesRegistro[5].toString());
            }
        }
    }

    @Override
    public String format() {
        return this.getPin().toString();
    }
}
