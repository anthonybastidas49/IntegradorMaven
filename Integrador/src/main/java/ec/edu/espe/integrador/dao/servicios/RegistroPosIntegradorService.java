/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 09/12/2019 - 20:21:54
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.integrador.dao.servicios;

import ec.edu.espe.distribuidas.protocolpi.banco.CabeceraIntegradorBanco;
import ec.edu.espe.distribuidas.protocolpi.banco.CancelacionIntegradorBancoReq;
import ec.edu.espe.distribuidas.protocolpi.banco.CancelacionIntegradorBancoRes;
import ec.edu.espe.distribuidas.protocolpi.banco.CompraIntegradorBancoReq;
import ec.edu.espe.distribuidas.protocolpi.banco.CompraIntegradorBancoRes;
import ec.edu.espe.distribuidas.protocolpi.pos.CabeceraPosIntegrador;
import ec.edu.espe.distribuidas.protocolpi.pos.CancelacionPosIntegradorReq;
import ec.edu.espe.distribuidas.protocolpi.pos.CancelacionPosIntegradorRes;
import ec.edu.espe.distribuidas.protocolpi.pos.CompraPosIntegradorReq;
import ec.edu.espe.distribuidas.protocolpi.pos.CompraPosIntegradorRes;
import ec.edu.espe.distribuidas.protocolpi.pos.MensajeProtocolo;
import ec.edu.espe.distribuidas.protocolpi.pos.MessageFormat;
import ec.edu.espe.distribuidas.protocolpi.pos.MessageParser;
import ec.edu.espe.distribuidas.protocolpi.pos.ProtocolParserException;
import ec.edu.espe.distribuidas.protocolpi.pos.RegistroPosIntegradorReq;
import ec.edu.espe.distribuidas.protocolpi.pos.RegistroPosIntegradorRes;
import ec.edu.espe.integrador.dao.BancoDAO;
import ec.edu.espe.integrador.dao.PosDAO;
import ec.edu.espe.integrador.dao.SesionPosDAO;
import ec.edu.espe.integrador.dao.TransaccionDAO;
import ec.edu.espe.integrador.modelo.Banco;
import ec.edu.espe.integrador.modelo.Pos;
import ec.edu.espe.integrador.modelo.SesionPos;
import ec.edu.espe.integrador.modelo.Transaccion;
import ec.edu.espe.integrador.started.Worker;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Anthony
 */
public class RegistroPosIntegradorService {

    private static String REQUEST_I = "RQ";
    private static String RESPONSE_I = "RS";
    private static Integer RESPONSE_RESGISTRO = 2010;
    private static Integer RESPONSE_COMPRA = 3010;
    private static Integer RESPONSE_CANCELACION = 4010;
    private static Integer REQUEST_COMPRA = 3000;
    private static Integer REQUEST_CANCELACION = 4000;
    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public String responseRegistro(RegistroPosIntegradorReq request) throws ProtocolParserException {
        PosDAO posDAO = new PosDAO();
        SesionPosDAO sesionPosDAO = new SesionPosDAO();
        Pos pos = posDAO.findByCodigoDispositivos(request.getCabecera().getDispositivo());
        RegistroPosIntegradorRes response = new RegistroPosIntegradorRes();
        CabeceraPosIntegrador cabecera=new CabeceraPosIntegrador(this.RESPONSE_I, request.getCabecera().getDispositivo(), this.RESPONSE_RESGISTRO, request.getCabecera().getFecha(), 0);
        response.setCabecera(cabecera);
        if (pos != null) {
            SesionPos insertSesion = new SesionPos();
            insertSesion.setCodigo(null);
            insertSesion.setFechaCreacion(calendar.getTime());
            calendar.add(Calendar.MINUTE, 5);
            insertSesion.setFechaUltimoAcceso(calendar.getTime());
            sesionPosDAO.insert(insertSesion);
            SesionPos codigoSesion = sesionPosDAO.findByLastInsert();
            System.out.println(codigoSesion.getCodigo());
            response.setCodigoSesion(codigoSesion.getCodigo());
            response.setEstado(pos.getEstado());
            System.out.println(MessageFormat.format(response));
            return MessageFormat.format(response);
        }
        response.setCodigoSesion(-1);
        response.setEstado("INA");
        return MessageFormat.format(response);
    }

    public String responseCompra(CompraPosIntegradorReq request) throws ec.edu.espe.distribuidas.protocolpi.banco.ProtocolParserException, ProtocolParserException {
        BancoDAO bancoDAO = new BancoDAO();
        Banco banco = new Banco();
        TransaccionDAO transaccionDAO = new TransaccionDAO();
        Transaccion transaccion = new Transaccion();
        SesionPosDAO sesionDAO = new SesionPosDAO();
        SesionPos sesionPos = new SesionPos();
        sesionPos.setCodigo(request.getCodigoSesion());
        sesionPos.setFechaUltimoAcceso(calendar.getTime());
        SesionPos sesionNueva = new SesionPos();
        sesionNueva = sesionDAO.findBySesion(sesionPos);
        if (sesionNueva != null) {
            calendar.add(Calendar.MINUTE, 5);
            sesionNueva.setFechaUltimoAcceso(calendar.getTime());
            sesionDAO.update(sesionNueva);
            banco = bancoDAO.findByPk(request.getCodigoBanco().toString());
            if (banco != null) {
                CompraIntegradorBancoReq compraInBanReq = new CompraIntegradorBancoReq();
                compraInBanReq.setCabecera(new CabeceraIntegradorBanco(REQUEST_I, REQUEST_COMPRA, request.getCabecera().getFecha(), 0));
                compraInBanReq.setCodigoEstablecimiento(request.getCodigoEstablecimiento());
                compraInBanReq.setTipo(request.getTipo());
                compraInBanReq.setNumTarjeta(request.getNumTarjeta());
                compraInBanReq.setCvv(request.getCvv());
                compraInBanReq.setFechaExpiracion(request.getFechaExpiracion());
                compraInBanReq.setValorCompra(request.getValorCompra());
                compraInBanReq.setImpuesto(request.getImpuesto());
                compraInBanReq.setMonto(request.getMonto());
                compraInBanReq.setMeses(request.getMeses());
                RedBancoService red = new RedBancoService();
                //String response = red.redireccionamiento(compraInBanReq.format().concat("\n"), banco);
                String response="RS|3010|20191109113825|16|115.14|TOK|00005";
                ec.edu.espe.distribuidas.protocolpi.banco.MensajeProtocolo mensajeProtocolo = ec.edu.espe.distribuidas.protocolpi.banco.MessageParser.parse(response);
                if(mensajeProtocolo instanceof CompraIntegradorBancoRes){
                    CompraIntegradorBancoRes responseBanco = (CompraIntegradorBancoRes) mensajeProtocolo;
                    CompraPosIntegradorRes nuevoResponse=new CompraPosIntegradorRes();
                    nuevoResponse.setCabecera(new CabeceraPosIntegrador(RESPONSE_I,request.getCabecera().getDispositivo(),RESPONSE_COMPRA,request.getCabecera().getFecha(),0));
                    nuevoResponse.setValorCuota(responseBanco.getValorCuota());
                    nuevoResponse.setEstado(responseBanco.getEstado());
                    nuevoResponse.setReferenciaVoucher(responseBanco.getReferenciaVoucher());
                    
                    transaccion.setCodigo(null);
                    transaccion.setCodigoBanco(banco.getCodigo());
                    transaccion.setCodigoDispositivo(request.getCabecera().getDispositivo());
                    transaccion.setRequest(request.format());
                    transaccion.setResponse(response);
                    transaccion.setFecha(calendar.getTime());
                    transaccion.setIsoPais("ECU");
                    transaccion.setEstado(null);
                    transaccionDAO.insert(transaccion);
                    return MessageFormat.format(nuevoResponse);
                }
            }
        }
        CompraPosIntegradorRes compraResponse = new CompraPosIntegradorRes();
        compraResponse.setCabecera(new CabeceraPosIntegrador(RESPONSE_I, request.getCabecera().getDispositivo(), RESPONSE_COMPRA, request.getCabecera().getFecha(), 0));
        compraResponse.setValorCuota(new BigDecimal(-1));
        compraResponse.setEstado("SNP");
        compraResponse.setReferenciaVoucher("00000");
        
        transaccion.setCodigo(null);
        transaccion.setCodigoBanco(banco.getCodigo());
        transaccion.setCodigoDispositivo(request.getCabecera().getDispositivo());
        transaccion.setRequest(request.format());
        transaccion.setResponse(compraResponse.format());
        transaccion.setFecha(calendar.getTime());
        transaccion.setIsoPais("ECU");
        transaccion.setEstado(null);
        transaccionDAO.insert(transaccion);
        
        return MessageFormat.format(compraResponse);
    }
    
    
    public String responseCancelacion(CancelacionPosIntegradorReq request) throws ProtocolParserException, ec.edu.espe.distribuidas.protocolpi.banco.ProtocolParserException {
        
        BancoDAO bancoDAO = new BancoDAO();
        Banco banco = new Banco();
        TransaccionDAO transaccionDAO = new TransaccionDAO();
        Transaccion transaccion = new Transaccion();
        PosDAO posDAO=new PosDAO();
        Pos pos=new Pos();
        SesionPosDAO sesionDAO = new SesionPosDAO();
        SesionPos sesionPos = new SesionPos();
        sesionPos.setCodigo(request.getCodigoSesion());
        sesionPos.setFechaUltimoAcceso(calendar.getTime());
        SesionPos sesionNueva = new SesionPos();
        sesionNueva = sesionDAO.findBySesion(sesionPos);
        if (sesionNueva != null) {
            calendar.add(Calendar.MINUTE, 5);
            sesionNueva.setFechaUltimoAcceso(calendar.getTime());
            sesionDAO.update(sesionNueva);
            pos=posDAO.findByCodigoDispositivos(request.getCabecera().getDispositivo());
            if(request.getPin().equals(pos.getPin())){
                banco = bancoDAO.findByPk(request.getCodigoBanco().toString());
                if (banco != null) {
                    CancelacionIntegradorBancoReq cancelacionInBan = new CancelacionIntegradorBancoReq();
                    cancelacionInBan.setCabecera(new CabeceraIntegradorBanco(REQUEST_I, REQUEST_CANCELACION, request.getCabecera().getFecha(), 0));
                    cancelacionInBan.setCodigoEstablecimiento(request.getCodigoEstablecimiento());
                    cancelacionInBan.setNumTarjeta(request.getNumTarjeta());
                    cancelacionInBan.setReferenciaVoucher(request.getReferenciaVoucher());
                    RedBancoService red = new RedBancoService();
                    //String response = red.redireccionamiento(cancelacionInBan.format().concat("\n"), banco);
                    String response="RS|4010|20191109113825|10|575.82|TOK";
                    ec.edu.espe.distribuidas.protocolpi.banco.MensajeProtocolo mensajeProtocolo = ec.edu.espe.distribuidas.protocolpi.banco.MessageParser.parse(response);
                    if(mensajeProtocolo instanceof CancelacionIntegradorBancoRes){
                        CancelacionIntegradorBancoRes responseBanco = (CancelacionIntegradorBancoRes) mensajeProtocolo;
                        CancelacionPosIntegradorRes nuevoResponse= new CancelacionPosIntegradorRes();
                        nuevoResponse.setCabecera(new CabeceraPosIntegrador(RESPONSE_I, request.getCabecera().getDispositivo(), RESPONSE_COMPRA, request.getCabecera().getFecha(),0));
                        nuevoResponse.setMontoCancelado(responseBanco.getMontoCancelado());
                        nuevoResponse.setEstado(responseBanco.getEstado());
                        transaccion.setCodigo(null);
                        transaccion.setCodigoBanco(banco.getCodigo());
                        transaccion.setCodigoDispositivo(request.getCabecera().getDispositivo());
                        transaccion.setRequest(request.format());
                        transaccion.setResponse(response);
                        transaccion.setFecha(calendar.getTime());
                        transaccion.setIsoPais("ECU");
                        transaccion.setEstado(null);
                        transaccionDAO.insert(transaccion);
                        return MessageFormat.format(nuevoResponse);
                    }
                }
            }          
        }
        CancelacionPosIntegradorRes cancelacionResponse = new CancelacionPosIntegradorRes();
        cancelacionResponse.setCabecera(new CabeceraPosIntegrador(RESPONSE_I, request.getCabecera().getDispositivo(), RESPONSE_COMPRA, request.getCabecera().getFecha(), 0));
        cancelacionResponse.setMontoCancelado(new BigDecimal(-1));
        cancelacionResponse.setEstado(REQUEST_I);
        
        transaccion.setCodigo(null);
        transaccion.setCodigoBanco(banco.getCodigo());
        transaccion.setCodigoDispositivo(request.getCabecera().getDispositivo());
        transaccion.setRequest(request.format());
        transaccion.setResponse(cancelacionResponse.format());
        transaccion.setFecha(calendar.getTime());
        transaccion.setIsoPais("ECU");
        transaccion.setEstado(null);
        transaccionDAO.insert(transaccion);
        
        return MessageFormat.format(cancelacionResponse);
    }
    
    

}
