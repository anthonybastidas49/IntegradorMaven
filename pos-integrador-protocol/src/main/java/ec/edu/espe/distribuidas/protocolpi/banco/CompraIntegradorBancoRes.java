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
 * Clase CompraIntegradorBancoRes Creacion de la clase CompraIntegradorBancoRes
 * con metodos set y get para la implementación del protocolo Compra en Response
 *
 * @author Paspuel
 * @author Torres
 */
public class CompraIntegradorBancoRes extends MensajeProtocolo {

    private BigDecimal valorCuota;
    private String estado;
    private String referenciaVoucher;

    public CompraIntegradorBancoRes() {
    }

    public CompraIntegradorBancoRes(CabeceraIntegradorBanco cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public CompraIntegradorBancoRes(CabeceraIntegradorBanco cabecera, BigDecimal valorCuota, String estado, String referenciaVoucher) {
        super();
        super.cabecera = cabecera;
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
        String partesCompra[] = text.split(ec.edu.espe.distribuidas.protocolpi.pos.Protocol.SEPARADOR);
        if (partesCompra.length != 7) {
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        } else {
            //this.setCabecera(new CabeceraIntegradorBanco(partesCompra[0], Integer.parseInt(partesCompra[1]), partesCompra[2], Integer.parseInt(partesCompra[3])));
            try {
                this.setValorCuota(new BigDecimal(partesCompra[4]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Valor Cuota. Valor Cuota recibido: " + partesCompra[5].toString());
            }
            if (partesCompra[5].length()!=3) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Estado recibido:" + partesCompra[5].toString());
            } else {
                this.setEstado(partesCompra[5]);
            }
            if (partesCompra[6].length() != 5) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Referencia Voucher recibido:" + partesCompra[6].toString());
            } else {
                this.setReferenciaVoucher(partesCompra[6]);
            }
        }
    }

    @Override
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getValorCuota());
        sb.append(Protocol.SEPARADOR);
        sb.append(getEstado());
        sb.append(Protocol.SEPARADOR);
        sb.append(getReferenciaVoucher());
        return sb.toString();
    }

}