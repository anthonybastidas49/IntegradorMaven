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
 * Clase CancelacionIntegradorBancoReq Creacion de la clase
 * CancelacionPosIntegradorReq con metodos set y get para la implementación del
 * protocolo Cancelacion en request
 *
 * @author Paspuel
 * @author Torres
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

    public String getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(String codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
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
    public void parse(String text) throws ProtocolParserException {
        String partesCancelacion[] = text.split(Protocol.SEPARADOR);
        if (partesCancelacion.length != 7) {
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        } else {
            this.setCabecera(new CabeceraIntegradorBanco(partesCancelacion[0], Integer.parseInt(partesCancelacion[1]), partesCancelacion[2], Integer.parseInt(partesCancelacion[3])));
            if (partesCancelacion[4].length() != 6) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Codigo Establecimiento recibido:" + partesCancelacion[4].toString());
            } else {
                this.setCodigoEstablecimiento(partesCancelacion[4]);
            }
            if (partesCancelacion[5].length() != 16 || partesCancelacion[5].matches("[0-9]*")) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Numero Tarjeta recibido:" + partesCancelacion[5].toString());
            } else {
                this.setNumTarjeta(partesCancelacion[5]);
            }
            if (partesCancelacion[6].length() != 5) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Referencia Voucher recibido:" + partesCancelacion[6].toString());
            } else {
                this.setReferenciaVoucher(partesCancelacion[6]);
            }
            
        }
    }

    @Override
    public String format() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
