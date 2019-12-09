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
 * Clase CancelacionIntegradorBancoRes. Creacion de la clase
 * CancelacionIntegradorBancoRes con metodos set y get para la implementación del
 * protocolo Concelacion en Response
 *
 * @author Paspuel
 * @author Torres
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
    public void parse(String text) throws ProtocolParserException {
       String partesCancelacion[] = text.split(Protocol.SEPARADOR);
        if (partesCancelacion.length != 6) {
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        } else {
            //this.setCabecera(new CabeceraIntegradorBanco(partesCancelacion[0], Integer.parseInt(partesCancelacion[1]), partesCancelacion[2], Integer.parseInt(partesCancelacion[3])));
            try {
                this.setMontoCancelado(new BigDecimal(partesCancelacion[4]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Valor Cuota. Valor Cuota recibido: " + partesCancelacion[5].toString());
            }
            if (partesCancelacion[5].length()!=3) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Estado recibido:" + partesCancelacion[5].toString());
            } else {
                this.setEstado(partesCancelacion[5]);
            }
        }
    }

    @Override
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getMontoCancelado());
        sb.append(Protocol.SEPARADOR);
        sb.append(getEstado());
        return sb.toString();
    }
      
}
