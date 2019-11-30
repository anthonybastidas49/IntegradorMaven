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

import java.math.BigDecimal;

/**
 * Clase CompraPosIntegradorRes Creacion de la clase CompraPosIntegradorRes con
 * metodos set y get para la implementación del protocolo Compra en Response
 *
 * @author Paspuel
 * @author Torres
 */
public class CompraPosIntegradorRes extends MensajeProtocolo {
    private static final String SEPARADOR="|";
    private BigDecimal valorCuota;
    private String estado;
    private String referenciaVoucher;

    public CompraPosIntegradorRes() {
    }

    public CompraPosIntegradorRes(CabeceraPosIntegrador cabecera) {
        super();
        super.cabecera = cabecera;
    }

    public CompraPosIntegradorRes(CabeceraPosIntegrador cabecera, BigDecimal valorCuota, String estado, String referenciaVoucher) {
        super();
        super.cabecera = cabecera;
        this.valorCuota = valorCuota;
        this.estado = estado;
        this.referenciaVoucher=referenciaVoucher;
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
    public void parse(String text) throws ProtocolParserException{
        String partesCompra[]=text.split(SEPARADOR);
        if(partesCompra.length!=8){
            throw new ProtocolParserException(ErrorCodesParser.CAMPOS_INSUFICIENTES,
                    "El mensaje recibido tiene menos campos de los necesarios para parsear la cabecera. Campos recibidos:" + text.length());
        }else{
            this.setCabecera(new CabeceraPosIntegrador(partesCompra[0],partesCompra[1],Integer.parseInt(partesCompra[2]),partesCompra[3], Integer.parseInt(partesCompra[4])));
            try{
                this.setValorCuota(new BigDecimal(partesCompra[5]));
            }catch(Exception ex){
                throw new ProtocolParserException(ErrorCodesParser.CASTING_NO_REALIZADO,
                        "El mensaje recibido no tiene el formato correcto en Valor Cuota. Valor Cuota recibido: " + partesCompra[5].toString());
            }
            if(!partesCompra[6].equals("TOK") || !partesCompra[6].equals("SNF") || !partesCompra[6].equals("EXP")
                    || !partesCompra[6].equals("REP")|| !partesCompra[6].equals("ECV")){
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Estado recibido:" + partesCompra[6].toString());
            }else{
                this.setEstado(partesCompra[6]);
            }
            if(partesCompra[7].length()!=5){
                throw new ProtocolParserException(ErrorCodesParser.VALORES_INCORRECTOS,
                        "El mensaje recibido no contiene información válida. Referencia Voucher recibido:" + partesCompra[7].toString());
            }else{
                this.setReferenciaVoucher(partesCompra[7]);
            }
        }
    }

    @Override
    public String format() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
