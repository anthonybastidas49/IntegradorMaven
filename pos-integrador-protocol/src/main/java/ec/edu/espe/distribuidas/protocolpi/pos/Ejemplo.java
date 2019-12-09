/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: protocolpi
 * Creado: 08/12/2019 - 23:18:31
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.distribuidas.protocolpi.pos;


/**
 *
 * @author Anthony
 */
public class Ejemplo {
    public static void main(String[] args) throws ProtocolParserException {
        MensajeProtocolo men=MessageParser.parse("RQ|POSA00000000001|4000|20191109111925|43|1547|003|EST001|1234|6547123685214563|00001");
        if(men instanceof RegistroPosIntegradorReq){
            RegistroPosIntegradorReq m=(RegistroPosIntegradorReq) men;
            System.out.println(m.getCabecera().getCodigoMensaje());
            System.out.println(m.getPin());
            String ma=MessageFormat.format(m);
            System.out.println(ma);
        }else if(men instanceof RegistroPosIntegradorRes){
            RegistroPosIntegradorRes m=(RegistroPosIntegradorRes)men;
            System.out.println(m.getCodigoSesion());
            String ma=MessageFormat.format(m);
            System.out.println(ma);
        }else if(men instanceof CompraPosIntegradorRes){
            CompraPosIntegradorRes m=(CompraPosIntegradorRes)men;
            System.out.println(m.getValorCuota());
            String ma=MessageFormat.format(m);
            System.out.println(ma);
        }else if(men instanceof CancelacionPosIntegradorRes){
            CancelacionPosIntegradorRes m=(CancelacionPosIntegradorRes)men;
            System.out.println(m.getMontoCancelado());
            String ma=MessageFormat.format(m);
            System.out.println(ma);
        }
        else if(men instanceof CompraPosIntegradorReq){
            CompraPosIntegradorReq m=(CompraPosIntegradorReq)men;
            System.out.println(m.getNumTarjeta());
            System.out.println(m.getValorCompra());
            String ma=MessageFormat.format(m);
            System.out.println(ma);
        }
        else if(men instanceof CancelacionPosIntegradorReq){
            CancelacionPosIntegradorReq m=(CancelacionPosIntegradorReq)men;
            System.out.println(m.getReferenciaVoucher());
            System.out.println(m.getNumTarjeta());
            String ma=MessageFormat.format(m);
            System.out.println(ma);
        }
        else if(men instanceof RegistroPosIntegradorRes){
            RegistroPosIntegradorRes m=(RegistroPosIntegradorRes) men;
            System.out.println(m.getEstado());
            String ma=MessageFormat.format(m);
            System.out.println(ma);
        }
                  
    }
     
}
