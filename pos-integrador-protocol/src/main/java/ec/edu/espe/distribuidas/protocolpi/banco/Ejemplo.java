/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: protocolpi
 * Creado: 09/12/2019 - 0:30:16
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi.banco;

/**
 *
 * @author Anthony
 */
public class Ejemplo {
    public static void main(String[] args) throws ProtocolParserException {
        MensajeProtocolo men=MessageParser.parse("RS|3010|20191109113825|16|115.14|TOK|00005");
        if(men instanceof CompraIntegradorBancoReq){
            CompraIntegradorBancoReq m=(CompraIntegradorBancoReq)men;
            System.out.println(m.getCodigoEstablecimiento());
            System.out.println(m.getImpuesto());
            System.out.println(MessageFormat.format(m));
        }else if(men instanceof CancelacionIntegradorBancoReq){
            CancelacionIntegradorBancoReq m=(CancelacionIntegradorBancoReq)men;
            System.out.println(m.getReferenciaVoucher());

            System.out.println(MessageFormat.format(m));
        }else if(men instanceof CancelacionIntegradorBancoRes){
            CancelacionIntegradorBancoRes m=(CancelacionIntegradorBancoRes) men;
            System.out.println(m.getMontoCancelado());
            System.out.println(MessageFormat.format(m));
        }else if(men instanceof CompraIntegradorBancoRes){
            CompraIntegradorBancoRes m=(CompraIntegradorBancoRes)men;
            System.out.println(m.getValorCuota());
            System.out.println(MessageFormat.format(m));
        }
    }
}
