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

/**
 * Clase CancelacionPosIntegradorReq. Creacion de la clase
 * CancelacionPosIntegradorReq con metodos set y get para la implementación del
 * protocolo Concelacion en request
 *
 * @author Paspuel-Torres
 */
public class CancelacionPosIntegradorReq extends MensajeProtocolo {

    private Integer codigoSesion;
    private Integer codigoBanco;
    private String codigoEstablecimiento;
    private Integer pin;
    private String numTarjeta;
    private String referenciaVoucher;

    public CancelacionPosIntegradorReq() {
    }

    public CancelacionPosIntegradorReq(CabeceraPosIntegrador cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public CancelacionPosIntegradorReq(CabeceraPosIntegrador cabecera, Integer codigoSesion, Integer codigoBanco, String codigoEstablecimiento, Integer pin, String numTarjeta, String referenciaVoucher) {
        super();
        super.cabecera = cabecera;
        this.codigoSesion = codigoSesion;
        this.codigoBanco = codigoBanco;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.pin = pin;
        this.numTarjeta = numTarjeta;
        this.referenciaVoucher = referenciaVoucher;
    }

    public Integer getCodigoSesion() {
        return codigoSesion;
    }

    public void setCodigoSesion(Integer codigoSesion) {
        this.codigoSesion = codigoSesion;
    }

    public Integer getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(Integer codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(String codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getReferenciaVoucher() {
        return referenciaVoucher;
    }

    public void setReferenciaVoucher(String referenciaVoucher) {
        this.referenciaVoucher = referenciaVoucher;
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
