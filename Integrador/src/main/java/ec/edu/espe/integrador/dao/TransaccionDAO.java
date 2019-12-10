/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 10/11/2019 - 14:25:41
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.integrador.dao;

import ec.edu.espe.distribuidas.dbutils.bdd.AbstractDAO;
import ec.edu.espe.integrador.modelo.SesionPos;
import ec.edu.espe.integrador.modelo.Transaccion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase TransaccionDAO. Realizacion del CRUD en la tabla TRANSACCION
 *
 * @author Paspuel-Torres
 */
public class TransaccionDAO extends AbstractDAO<Transaccion> {

    private static final String INSERT = "INSERT INTO TRANSACCION(COD_TRANSACCION,COD_BANCO,COD_DISPOSITIVO,"
            + "REQUEST,RESPONSE,ESTADO,FECHA) VALUES (NULL,?,?,?,?,?,?)";
    private static final String FIND_PK = "SELECT * FROM TRANSACCION WHERE COD_TRANSACCION=?";
    private static final String FIND_BY_BANCO = "SELECT * FROM TRANSACCION WHERE COD_BANCO=?";
    private static final String FIND_BY_DATE = "SELECT * FROM TRANSACCION WHERE FECHA BETWEEN ? AND ?";
    private static final Logger LOG = Logger.getLogger(TransaccionDAO.class.getName());

    public TransaccionDAO() {
        super();
    }

    public Transaccion findByPk(Integer codigo) {
        try {
            return super.findByPK(new Object[]{codigo});
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "ERROR AL EJECUTAR EL METODO FINDBYPK", sqlEx);
            return null;
        } finally {
            super.closeConnection();
        }
    }

    public void insert(Transaccion transaccion) {
        try {
            Object parametros[] = new Object[]{
                transaccion.getCodigo(),
                transaccion.getCodigoBanco(),
                transaccion.getCodigoDispositivo(),
                transaccion.getRequest(),
                transaccion.getResponse(),
                transaccion.getEstado(),
                transaccion.getFecha()
            };
            super.insert(parametros);
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el metodo insert ", sqlEx);
        } finally {
            super.closeConnection();
        }
    }

    @Override
    public String getPK() {
        return this.FIND_PK;
    }

    @Override
    public String getInsert() {
        return this.INSERT;
    }

    @Override
    public String getUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaccion createObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
