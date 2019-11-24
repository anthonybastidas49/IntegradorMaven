/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
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

import java.math.BigDecimal;

/**
 * Clase CancelacionPosIntegradorRes. Creacion de la clase
 * CancelacionPosIntegradorRes con metodos set y get para la implementación del
 * protocolo Concelacion en Response
 *
 * @author Paspuel-Torres
 */
public class CancelacionPosIntegradorRes extends MensajeProtocolo {

    private BigDecimal montoCancelado;
    private String estado;

    public CancelacionPosIntegradorRes() {
    }

    public CancelacionPosIntegradorRes(CabeceraPosIntegrador cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public CancelacionPosIntegradorRes(CabeceraPosIntegrador cabecera, BigDecimal montoCancelado, String estado) {
        super();
        super.cabecera = cabecera;
        this.montoCancelado = montoCancelado;
        this.estado = estado;
    }

    public BigDecimal getMontoCancelado() {
        return montoCancelado;
    }

    public void setMontoCancelado(BigDecimal montoCancelado) {
        this.montoCancelado = montoCancelado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void parse(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String format() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
