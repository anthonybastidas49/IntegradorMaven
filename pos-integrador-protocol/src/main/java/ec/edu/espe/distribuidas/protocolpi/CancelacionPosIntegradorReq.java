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

/**
 * Clase CancelacionPosIntegradorReq Creacion de la clase
 * CancelacionPosIntegradorReq con metodos set y get para la implementación del
 * protocolo Concelacion en request
 *
 * @author Paspuel
 * @author Torres
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
    public void parse(String text) throws ProtocolParserException{
        String partesCancelacion[]=text.split(CabeceraPosIntegrador.SEPARADOR);
        if(partesCancelacion.length!=11){
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        }else{
            this.setCabecera(new CabeceraPosIntegrador(partesCancelacion[0],partesCancelacion[1],Integer.parseInt(partesCancelacion[2]),partesCancelacion[3], Integer.parseInt(partesCancelacion[4])));
            try{
                this.setCodigoSesion(Integer.parseInt(partesCancelacion[5]));
            }catch(Exception ex){
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Codigo Sesion. Codigo Sesion recibido: " + partesCancelacion[5].toString());
            }
            try{
                this.setCodigoBanco(Integer.parseInt(partesCancelacion[6]));
            }catch(Exception ex){
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Codigo Banco. Codigo Banco recibido: " + partesCancelacion[6].toString());
            }
            if(partesCancelacion[7].length()!=6){
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Codigo Establecimiento recibido:" + partesCancelacion[7].toString());
            }else{
                this.setCodigoEstablecimiento(partesCancelacion[7]);
            }
            try{
                this.setPin(Integer.parseInt(partesCancelacion[8]));
            }catch(Exception ex){
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en pin. Pin recibido: " + partesCancelacion[8].toString());
            }
            if(partesCancelacion[9].length()!=16 || partesCancelacion[9].matches("[0-9]*")){
              throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Numero Tarjeta recibido:" + partesCancelacion[9].toString());
            }else{
                this.setNumTarjeta(partesCancelacion[9]);
            }
            if(partesCancelacion[10].length()!=5){
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Referencia Voucher recibido:" + partesCancelacion[10].toString());
            }else{
                this.setReferenciaVoucher(partesCancelacion[10]);
            }
        }
    }

    @Override
    public String format() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
