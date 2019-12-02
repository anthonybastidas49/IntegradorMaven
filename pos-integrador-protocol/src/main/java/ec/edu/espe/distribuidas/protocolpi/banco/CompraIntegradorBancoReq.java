/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: protocolpi
 * 
 * Creado: 01/12/2019 - 20:13:05
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por la licencia BSD.
 * 
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi.banco;

import java.math.BigDecimal;

/**
 *Clase CompraIntegradorBancoReq. Creacion de la clase CompraIntegradorBancoReq con
 * metodos set y get para la implementación del protocolo Compra en Request
 *
 * @author Paspuel
 * @author Torres
 */
public class CompraIntegradorBancoReq extends MensajeProtocolo{
    private String codigoEstablecimiento;
    private String tipo;
    private String numTarjeta;
    private Integer cvv;
    private String fechaExpiracion;
    private BigDecimal valorCompra;
    private BigDecimal impuesto;
    private BigDecimal monto;
    private Integer meses;

    public CompraIntegradorBancoReq() {
    }
    
    public CompraIntegradorBancoReq(CabeceraIntegradorBanco cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public CompraIntegradorBancoReq(CabeceraIntegradorBanco cabecera,String codigoEstablecimiento, String tipo, String numTarjeta, Integer cvv, String fechaExpiracion, BigDecimal valorCompra, BigDecimal impuesto, BigDecimal monto, Integer meses) {
        super();
        super.cabecera = cabecera;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.tipo = tipo;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.fechaExpiracion = fechaExpiracion;
        this.valorCompra = valorCompra;
        this.impuesto = impuesto;
        this.monto = monto;
        this.meses = meses;
    }

    public String getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(String codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getMeses() {
        return meses;
    }

    public void setMeses(Integer meses) {
        this.meses = meses;
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
