/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: protocolpi
 * 
 * Creado: 01/12/2019 - 20:13:57
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por la licencia BSD.
 * 
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi.banco;

/**
 *
 * @author mayra
 */
public class CancelaciónIntegradorBancoReq extends MensajeProtocolo {

    private String codigoEstablecimiento;
    private String numTarjeta;
    private String referenciaVoucher;

    public CancelaciónIntegradorBancoReq() {
    }

    public CancelaciónIntegradorBancoReq(CabeceraIntegradorBanco cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public CancelaciónIntegradorBancoReq(CabeceraIntegradorBanco cabecera, String codigoEstablecimiento, String numTarjeta, String referenciaVoucher) {
        super();
        super.cabecera = cabecera;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.numTarjeta = numTarjeta;
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
