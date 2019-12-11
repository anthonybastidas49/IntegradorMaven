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

import ec.edu.espe.distribuidas.protocolpi.pos.CabeceraPosIntegrador;
import ec.edu.espe.distribuidas.protocolpi.pos.RegistroPosIntegradorReq;
import ec.edu.espe.distribuidas.protocolpi.pos.RegistroPosIntegradorRes;
import ec.edu.espe.integrador.dao.PosDAO;
import ec.edu.espe.integrador.dao.SesionPosDAO;
import ec.edu.espe.integrador.modelo.Pos;
import ec.edu.espe.integrador.modelo.SesionPos;

/**
 *
 * @author Anthony
 */
public class RegistroPosIntegradorService {
    private static String REQUEST_I="RQ";
    private static String RESPONSE_I="RS";
    private static Integer RESPONSE_RESGISTRO=2010;
    public String responseRegistro(RegistroPosIntegradorReq request){
        PosDAO posDAO=new PosDAO();
        SesionPosDAO sesionPosDAO=new SesionPosDAO();
        Pos pos=posDAO.findByCodigoDispositivos(request.getCabecera().getDispositivo());
        RegistroPosIntegradorRes response=new RegistroPosIntegradorRes();
        response.setCabecera(new CabeceraPosIntegrador(RESPONSE_I,request.getCabecera().getDispositivo(),RESPONSE_RESGISTRO,request.getCabecera().getFecha(),0));
        response.setCabecera(new CabeceraPosIntegrador());
        if(pos!=null){
            SesionPos sesionPos=new SesionPos();
            //sesionPos.setFechaCreacion(fechaCreacion);
            //sesionPos.setFechaUltimoAcceso(fechaUltimoAcceso);
            sesionPosDAO.insert(sesionPos);
            //Codigo Sesion
            sesionPos=sesionPosDAO.findByLastInsert();
        }else{
            
        }
       return "";
    }
}
