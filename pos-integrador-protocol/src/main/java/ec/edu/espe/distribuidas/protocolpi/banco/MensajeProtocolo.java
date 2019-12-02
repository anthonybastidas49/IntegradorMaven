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
package ec.edu.espe.distribuidas.protocolpi.banco;

import ec.edu.espe.distribuidas.protocolpi.pos.*;

/**
 * Clase MensajeProtocolo. Sirve para tratar la cabecera del mensaje del
 * protocolo
 *
 * @author Paspuel
 * @author Torres
 */
public abstract class MensajeProtocolo implements Protocol {

    protected CabeceraIntegradorBanco cabecera;

    public MensajeProtocolo() {
    }

    public CabeceraIntegradorBanco getCabecera() {
        return cabecera;
    }

    public void setCabecera(CabeceraIntegradorBanco cabecera) {
        this.cabecera = cabecera;
    }

}
