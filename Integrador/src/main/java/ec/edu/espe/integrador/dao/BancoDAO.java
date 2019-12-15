/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 10/11/2019 - 14:25:23
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.integrador.dao;

import ec.edu.espe.distribuidas.dbutils.bdd.AbstractDAO;
import ec.edu.espe.integrador.modelo.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase BancoDAO. Realizacion del CRUD en la tabla BANCO
 *
 * @author Paspuel-Torres
 */
public class BancoDAO extends AbstractDAO<Banco>{
    private static final String BUSCAR_PK="SELECT * FROM BANCO WHERE COD_BANCO=?";   
    private static final Logger LOG = Logger.getLogger(BancoDAO.class.getName());
        

    public BancoDAO() {
        super();
    }


    public Banco findByPk(String codigo){
        try {
            return super.findByPK(new Object[]{codigo});
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE,"ERROR AL EJECUTAR EL METODO FINDBYPK",sqlEx);
            return null;
        } finally{
            super.closeConnection();
        }
    }
        @Override
    public String getPK() {
        return  BancoDAO.BUSCAR_PK;
    }
    @Override
    public String getInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Banco createObject(ResultSet rs) throws SQLException {
        return null;
    }

    
}
