/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: protocolpi
 * 
 * Creado: 01/12/2019 - 20:13:18
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por la licencia BSD.
 * 
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi.banco;

import java.math.BigDecimal;

/**
 *
 * @author mayra
 */
public class CancelacionIntegradorBancoRes extends MensajeProtocolo{
    private BigDecimal montoCancelado;
    private String estado;

    public CancelacionIntegradorBancoRes() {
    }

    public CancelacionIntegradorBancoRes(CabeceraIntegradorBanco cabecera) {
        super();
        super.cabecera=cabecera;
    }

    public CancelacionIntegradorBancoRes(CabeceraIntegradorBanco cabecera,BigDecimal montoCancelado, String estado) {
        super();
        super.cabecera=cabecera;
        this.montoCancelado = montoCancelado;
        this.estado = estado;
    }
    
    
    

    @Override
    public void parse(String text) throws ProtocolParserException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String format() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
