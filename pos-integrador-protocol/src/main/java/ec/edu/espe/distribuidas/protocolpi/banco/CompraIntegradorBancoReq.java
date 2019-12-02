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
        String partesCompra[] = text.split(Protocol.SEPARADOR);
        if (partesCompra.length != 13) {
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        } else {
            this.setCabecera(new CabeceraIntegradorBanco(partesCompra[0], Integer.parseInt(partesCompra[1]),partesCompra[2],Integer.parseInt(partesCompra[3])));
            if (partesCompra[4].length() != 6) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Codigo Establecimiento recibido:" + partesCompra[4].toString());
            } else {
                this.setCodigoEstablecimiento(partesCompra[4]);
            }
            if (!partesCompra[5].equals("DIF") || !partesCompra[5].equals("COR")) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Tipo recibido:" + partesCompra[5].toString());
            } else {
                this.setTipo(partesCompra[5]);
            }
            if (partesCompra[6].length() != 16 || partesCompra[6].matches("[0-9]*")) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Numero Tarjeta recibido:" + partesCompra[6].toString());
            } else {
                this.setNumTarjeta(partesCompra[6]);
            }
            try {
                this.setCvv(Integer.parseInt(partesCompra[7]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en CVV. CVV recibido: " + partesCompra[7].toString());
            }
            if (partesCompra[8].length() != 5) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Fecha Expiracion recibido:" + partesCompra[8].toString());
            } else {
                this.setFechaExpiracion(partesCompra[8]);
            }
            try {
                this.setValorCompra(new BigDecimal(partesCompra[9]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Valor Compra. Valor Compra recibido: " + partesCompra[9].toString());
            }
            try {
                this.setImpuesto(new BigDecimal(partesCompra[10]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Valor Compra. Valor Compra recibido: " + partesCompra[10].toString());
            }
            try {
                this.setMonto(new BigDecimal(partesCompra[11]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Monto. Monto recibido: " + partesCompra[11].toString());
            }
            try {
                this.setMeses(Integer.parseInt(partesCompra[12]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Meses. Meses recibido: " + partesCompra[12].toString());
            }
        } 
    }

    @Override
    public String format() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
