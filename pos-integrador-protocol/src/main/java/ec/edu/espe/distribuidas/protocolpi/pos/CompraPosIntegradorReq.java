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
package ec.edu.espe.distribuidas.protocolpi.pos;

import java.math.BigDecimal;

/**
 * Clase CompraPosIntegradorReq. Creacion de la clase CompraPosIntegradorReq con
 * metodos set y get para la implementación del protocolo Compra en Request
 *
 * @author Paspuel
 * @author Torres
 */
public class CompraPosIntegradorReq extends MensajeProtocolo {

    private Integer codigoSesion;
    private Integer codigoBanco;
    private String codigoEstablecimiento;
    private String tipo;
    private String numTarjeta;
    private Integer cvv;
    private String fechaExpiracion;
    private BigDecimal valorCompra;
    private BigDecimal impuesto;
    private BigDecimal monto;
    private Integer meses;
    private String referenciaVoucher;

    public CompraPosIntegradorReq() {
    }

    public CompraPosIntegradorReq(CabeceraPosIntegrador cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public CompraPosIntegradorReq(CabeceraPosIntegrador cabecera, Integer codigoSesion, Integer codigoBanco, String codigoEstablecimiento, String tipo, String numTarjeta, Integer cvv, String fechaExpiracion, BigDecimal valorCompra, BigDecimal impuesto, BigDecimal monto, Integer meses, String referenciaVoucher) {
        super();
        super.cabecera = cabecera;
        this.codigoSesion = codigoSesion;
        this.codigoBanco = codigoBanco;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.tipo = tipo;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.fechaExpiracion = fechaExpiracion;
        this.valorCompra = valorCompra;
        this.impuesto = impuesto;
        this.monto = monto;
        this.meses = meses;
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

    public String getReferenciaVoucher() {
        return referenciaVoucher;
    }

    public void setReferenciaVoucher(String referenciaVoucher) {
        this.referenciaVoucher = referenciaVoucher;
    }

    @Override
    public void parse(String text) throws ProtocolParserException {
        String partesCompra[] = text.split(Protocol.SEPARADOR);
        if (partesCompra.length != 16) {
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        } else {
            this.setCabecera(new CabeceraPosIntegrador(partesCompra[0], partesCompra[1], Integer.parseInt(partesCompra[2]), partesCompra[3], Integer.parseInt(partesCompra[4])));
            try {
                this.setCodigoSesion(Integer.parseInt(partesCompra[5]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Codigo Sesion. Codigo Sesion recibido: " + partesCompra[5].toString());
            }
            try {
                this.setCodigoBanco(Integer.parseInt(partesCompra[6]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Codigo Banco. Codigo Banco recibido: " + partesCompra[6].toString());
            }
            if (partesCompra[7].length() != 6) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Codigo Establecimiento recibido:" + partesCompra[7].toString());
            } else {
                this.setCodigoEstablecimiento(partesCompra[8]);
            }
            if (!partesCompra[8].equals("DIF") || !partesCompra[8].equals("COR")) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Tipo recibido:" + partesCompra[8].toString());
            } else {
                this.setTipo(partesCompra[8]);
            }
            if (partesCompra[9].length() != 16 || partesCompra[9].matches("[0-9]*")) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Numero Tarjeta recibido:" + partesCompra[9].toString());
            } else {
                this.setNumTarjeta(partesCompra[9]);
            }
            try {
                this.setCvv(Integer.parseInt(partesCompra[10]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en CVV. CVV recibido: " + partesCompra[10].toString());
            }
            if (partesCompra[11].length() != 5) {
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Fecha Expiracion recibido:" + partesCompra[11].toString());
            } else {
                this.setFechaExpiracion(partesCompra[11]);
            }
            try {
                this.setValorCompra(new BigDecimal(partesCompra[12]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Valor Compra. Valor Compra recibido: " + partesCompra[12].toString());
            }
            try {
                this.setImpuesto(new BigDecimal(partesCompra[13]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Valor Compra. Valor Compra recibido: " + partesCompra[13].toString());
            }
            try {
                this.setMonto(new BigDecimal(partesCompra[14]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Monto. Monto recibido: " + partesCompra[14].toString());
            }
            try {
                this.setMeses(Integer.parseInt(partesCompra[15]));
            } catch (Exception ex) {
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Meses. Meses recibido: " + partesCompra[14].toString());
            }

        }
    }

    @Override
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodigoSesion());
        sb.append(Protocol.SEPARADOR);
        sb.append(getCodigoBanco());
        sb.append(Protocol.SEPARADOR);
        sb.append(getCodigoEstablecimiento());
        sb.append(Protocol.SEPARADOR);
        sb.append(getTipo());
        sb.append(Protocol.SEPARADOR);
        sb.append(getNumTarjeta());
        sb.append(Protocol.SEPARADOR);
        sb.append(getCvv());
        sb.append(Protocol.SEPARADOR);
        sb.append(getFechaExpiracion());
        sb.append(Protocol.SEPARADOR);
        sb.append(getValorCompra());
        sb.append(Protocol.SEPARADOR);
        sb.append(getImpuesto());
        sb.append(Protocol.SEPARADOR);
        sb.append(getMonto());
        sb.append(Protocol.SEPARADOR);
        sb.append(getMeses());
        return sb.toString();
    }
}
