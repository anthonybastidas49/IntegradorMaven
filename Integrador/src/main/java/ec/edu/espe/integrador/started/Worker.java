/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.integrador.started;

import ec.edu.espe.distribuidas.protocolpi.banco.CancelacionIntegradorBancoReq;
import ec.edu.espe.distribuidas.protocolpi.banco.CancelacionIntegradorBancoRes;
import ec.edu.espe.distribuidas.protocolpi.banco.CompraIntegradorBancoReq;
import ec.edu.espe.distribuidas.protocolpi.banco.CompraIntegradorBancoRes;
import ec.edu.espe.distribuidas.protocolpi.pos.CancelacionPosIntegradorReq;
import ec.edu.espe.distribuidas.protocolpi.pos.CancelacionPosIntegradorRes;
import ec.edu.espe.distribuidas.protocolpi.pos.CompraPosIntegradorReq;
import ec.edu.espe.distribuidas.protocolpi.pos.CompraPosIntegradorRes;
import ec.edu.espe.distribuidas.protocolpi.pos.MensajeProtocolo;
import ec.edu.espe.distribuidas.protocolpi.pos.MessageParser;
import ec.edu.espe.distribuidas.protocolpi.pos.ProtocolParserException;
import ec.edu.espe.distribuidas.protocolpi.pos.RegistroPosIntegradorReq;
import ec.edu.espe.distribuidas.protocolpi.pos.RegistroPosIntegradorRes;
import ec.edu.espe.integrador.dao.PosDAO;
import ec.edu.espe.integrador.dao.servicios.RegistroPosIntegradorService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anthony
 */
public class Worker extends Thread {

    private static final Logger LOG = Logger.getLogger(PosDAO.class.getName());

    private Socket socketClientePos;
    InputStream inp = null;
    BufferedReader brinp = null;
    PrintWriter out = null;

    public Worker(Socket socketCliente) {
        this.socketClientePos = socketCliente;
    }

    @Override
    public void run() {
        try {
            inp = socketClientePos.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new PrintWriter(socketClientePos.getOutputStream());
            String line = brinp.readLine();
            out.write(this.instanciaPos(line));
            out.flush();
            socketClientePos.close();
            /*switch (parts[0]) {
                case "RGP":
                    /*res.marshalRegistroPos();
                    out.write(res.getMensaje());
                    out.flush();
                    break;
                case "CMP":
                    /*CompraReq reqC = new CompraReq(line);
                    reqC.unmarshalCompra();
                    resultadoT = db.verificarTransaccion(reqC.getIdTransaccion());
                    CompraRes resC = null;
                    if (resultadoT > 0) {
                        RedBanco red = new RedBanco(reqC.getMensaje());
                        db.insertarTransaccion(reqC.getCodLocal(), "CMP", reqC.getNumTarjeta(), reqC.getMonto(), reqC.getIva(), reqC.getMontoTotal(), calendar.getTime(), "CMP");
                        String respuestBanco = "";
                        switch (reqC.getNumTarjeta().charAt(0)) {
                            case '4':
                                respuestBanco = red.redirecionarBancos(IP_BANCOA, PORT_BANCOA);
                                break;
                            case '5':
                                respuestBanco = red.redirecionarBancos(IP_BANCOB, PORT_BANCOB);
                                break;
                        }
                        resC = new CompraRes(respuestBanco);
                    } else {
                        resC = new CompraRes("-1", "-1", "NK");
                    }
                    resC.marshallCompras();
                    out.write(resC.getMensaje());
                    out.flush();*/
 /* break;
                case "CNP":
                    /*CancelacionReq reqCa = new CancelacionReq(line);
                    reqCa.unmarshallCancelacion();
                    CancelacionRes resCa;
                    resultadoT = db.verificarTransaccionCancelar(reqCa);
                    if (resultadoT > 0) {
                        db.insertarTransaccion(reqCa.getCodPos(), "CNP", reqCa.getNumTarjeta(),0.0,0.0,0.0, calendar.getTime(), "CNP");
                        RedBanco red = new RedBanco(reqCa.getMensaje());
                        String respuestBanco = "";
                        switch (reqCa.getNumTarjeta().charAt(0)) {
                            case '4':
                                respuestBanco = red.redirecionarBancos(IP_BANCOA, PORT_BANCOA);
                                break;
                            case '5':
                                respuestBanco = red.redirecionarBancos(IP_BANCOB, PORT_BANCOB);
                                break;
                        }
                        resCa = new CancelacionRes(respuestBanco);
                    } else {
                        resCa = new CancelacionRes("-1", "-1", 0.0, "N/A", "NK");
                    }
                    resCa.marshalCancelacion();
                    out.write(resCa.getMensaje());
                    out.flush();
                    break;
            }
            socketClientePos.close();
        } catch (Exception ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          
        }*/
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ec.edu.espe.distribuidas.protocolpi.banco.ProtocolParserException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String instanciaPos(String mensaje) throws ec.edu.espe.distribuidas.protocolpi.banco.ProtocolParserException {
        RegistroPosIntegradorService servicio = new RegistroPosIntegradorService();
        try {
            MensajeProtocolo mensajeProtocolo = MessageParser.parse(mensaje);
            if (mensajeProtocolo instanceof RegistroPosIntegradorReq) {
                RegistroPosIntegradorReq request = (RegistroPosIntegradorReq) mensajeProtocolo;
                return servicio.responseRegistro(request).concat("\n");
            } else if (mensajeProtocolo instanceof RegistroPosIntegradorRes) {
                RegistroPosIntegradorRes response = (RegistroPosIntegradorRes) mensajeProtocolo;
            } else if (mensajeProtocolo instanceof CompraPosIntegradorReq) {
                CompraPosIntegradorReq request = (CompraPosIntegradorReq) mensajeProtocolo;
                return servicio.responseCompra(request).concat("\n");
            } else if (mensajeProtocolo instanceof CompraPosIntegradorRes) {
                CompraPosIntegradorRes response = (CompraPosIntegradorRes) mensajeProtocolo;
            } else if (mensajeProtocolo instanceof CancelacionPosIntegradorReq) {
                CancelacionPosIntegradorReq request = (CancelacionPosIntegradorReq) mensajeProtocolo;
                return servicio.responseCancelacion(request).concat("\n");
            } else if (mensajeProtocolo instanceof CancelacionPosIntegradorRes) {
                CancelacionPosIntegradorRes response = (CancelacionPosIntegradorRes) mensajeProtocolo;
            }
        } catch (ProtocolParserException sqlEx) {
            LOG.log(Level.SEVERE, "ERROR AL EJECUTAR EL METODO instancePos", sqlEx);
        }
        return "";
    }

    public static String instanciaBanco(String mensaje) {
        try {
            ec.edu.espe.distribuidas.protocolpi.banco.MensajeProtocolo mensajeProtocolo = ec.edu.espe.distribuidas.protocolpi.banco.MessageParser.parse(mensaje);
            if (mensajeProtocolo instanceof CompraIntegradorBancoReq) {
                CompraIntegradorBancoReq request = (CompraIntegradorBancoReq) mensajeProtocolo;
            } else if (mensajeProtocolo instanceof CompraIntegradorBancoRes) {
                CompraIntegradorBancoRes response = (CompraIntegradorBancoRes) mensajeProtocolo;
                //return Worker.instanciaPos(response.format());
            } else if (mensajeProtocolo instanceof CancelacionIntegradorBancoReq) {
                CancelacionIntegradorBancoReq request = (CancelacionIntegradorBancoReq) mensajeProtocolo;
            } else if (mensajeProtocolo instanceof CancelacionIntegradorBancoRes) {
                CancelacionIntegradorBancoRes response = (CancelacionIntegradorBancoRes) mensajeProtocolo;
            }
        } catch (ec.edu.espe.distribuidas.protocolpi.banco.ProtocolParserException sqlEx) {
            LOG.log(Level.SEVERE, "ERROR AL EJECUTAR EL METODO instanceBancos", sqlEx);
        }
        return "";
    }
}
