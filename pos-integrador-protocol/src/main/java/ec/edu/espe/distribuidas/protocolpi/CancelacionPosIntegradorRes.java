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
    private static final String SEPARADOR="|";
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
    public void parse(String text) throws ProtocolParserException{
        String partesCancelacion[]=text.split(SEPARADOR);
        if(partesCancelacion.length!=7){
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        }else{
            this.setCabecera(new CabeceraPosIntegrador(partesCancelacion[0],partesCancelacion[1],Integer.parseInt(partesCancelacion[2]),partesCancelacion[3], Integer.parseInt(partesCancelacion[4])));
            try{
                this.setMontoCancelado(new BigDecimal(partesCancelacion[5]));
            }catch(Exception ex){
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Valor Cuota. Valor Cuota recibido: " + partesCancelacion[5].toString());
            }
            if(!partesCancelacion[6].equals("TOK") || !partesCancelacion[6].equals("REP")||!partesCancelacion[6].equals("EPN")){
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Estado recibido:" + partesCancelacion[6].toString());
            }else{
                this.setEstado(partesCancelacion[6]);
            }
        }
    }

    @Override
    public String format() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
