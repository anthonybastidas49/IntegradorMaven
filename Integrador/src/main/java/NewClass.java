
import ec.edu.espe.integrador.dao.SesionPosDAO;
import ec.edu.espe.integrador.modelo.SesionPos;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 09/12/2019 - 15:50:56
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */

/**
 *
 * @author Anthony
 */
public class NewClass {
    public static void main(String[] args){

        //SesionPos sesion=new SesionPos();
        //sesion.setCodigo(2);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        /*sesion.setFechaUltimoAcceso(calendar.getTime());
        SesiCalendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        calendar.add(Calendar.MINUTE,5);
        System.out.println(calendar.toString());*/
        
        SesionPosDAO s=new SesionPosDAO();
        SesionPos sesion=new SesionPos();
        sesion.setFechaUltimoAcceso(calendar.getTime());
        sesion.setFechaCreacion(calendar.getTime());
        s.insert(sesion);
                SesionPos ss=s.findByLastInsert();
        System.out.println(ss.getCodigo());
        
    }}