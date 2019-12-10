/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 10/11/2019 - 14:25:48
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.integrador.dao;

import ec.edu.espe.distribuidas.dbutils.bdd.AbstractDAO;
import ec.edu.espe.integrador.modelo.Pos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase PosDAO. Realizacion del CRUD en la tabla POS
 *
 * @author Paspuel-Torres
 */
public class PosDAO extends AbstractDAO<Pos>{

    private static final String FIND_PK = "SELECT * FROM POS WHERE COD_POS=?";
    private static final String FIND_BY_CODIGO_DISPOSITIVO = "SELECT * FROM POS WHERE COD_DISPOSITIVO=?";
    private static final String FIND_BY_ESTADO = "SELECT * FROM POS WHERE ESTADO=?";
    private static final Logger LOG = Logger.getLogger(PosDAO.class.getName());

    public PosDAO() {
            super();
    }

    public Pos findByPk(Integer codigo){
        try {
            return super.findByPK(new Object[]{codigo});
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE,"ERROR AL EJECUTAR EL METODO FINDBYPK",sqlEx);
            return null;
        } finally{
            super.closeConnection();
        }
    }
    public List<Pos> findByEstado(String estado){
        try {
            return super.findByParameter(this.FIND_BY_ESTADO,new Object[]{estado});
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE,"ERROR AL EJECUTAR EL METODO FINDBYESTADO",sqlEx);
            return null;
        }finally{
            super.closeConnection();
        }
    }
    @Override
    public String getPK() {
        return FIND_PK;
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
    public Pos createObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
