
import ec.edu.espe.integrador.dao.BancoDAO;
import ec.edu.espe.integrador.modelo.Banco;

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
    public static void main(String[] args) {
        BancoDAO dao=new BancoDAO();
        Banco b=dao.findByPk("001");
        System.out.println(b.getNombre());
    }
}
