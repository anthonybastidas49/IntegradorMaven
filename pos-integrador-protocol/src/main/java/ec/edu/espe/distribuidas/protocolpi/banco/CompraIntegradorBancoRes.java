/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: protocolpi
 * 
 * Creado: 01/12/2019 - 20:12:33
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
public class CompraIntegradorBancoRes extends MensajeProtocolo{
    private BigDecimal valorCuota;
    private String estado;
    private String referenciaVoucher;

    public CompraIntegradorBancoRes() {
    }

    public CompraIntegradorBancoRes(CabeceraIntegradorBanco cabecera) {
        super();
        super.cabecera=cabecera;
    }
    
    public CompraIntegradorBancoRes(CabeceraIntegradorBanco cabecera, BigDecimal valorCuota, String estado, String referenciaVoucher) {
        super();
        super.cabecera=cabecera;
        this.valorCuota = valorCuota;
        this.estado = estado;
        this.referenciaVoucher = referenciaVoucher;
    }

    public BigDecimal getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(BigDecimal valorCuota) {
        this.valorCuota = valorCuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferenciaVoucher() {
        return referenciaVoucher;
    }

    public void setReferenciaVoucher(String referenciaVoucher) {
        this.referenciaVoucher = referenciaVoucher;
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
