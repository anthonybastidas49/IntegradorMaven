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

import ec.edu.espe.distribuidas.dbutils.bdd.ConnectionManager;
import ec.edu.espe.integrador.modelo.Transaccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase TransaccionDAO. Realizacion del CRUD en la tabla TRANSACCION
 *
 * @author Paspuel-Torres
 */
public class TransaccionDAO {

    private static final String INSERT = "INSERT INTO TRANSACCION(COD_TRANSACCION,COD_BANCO,COD_DISPOSITIVO,"
            + "REQUEST,RESPONSE,ESTADO,FECHA) VALUES (NULL,?,?,?,?,?,?)";
    private static final String FIND_PK = "SELECT * FROM TRANSACCION WHERE COD_TRANSACCION=?";
    private static final String FIND_BY_BANCO = "SELECT * FROM TRANSACCION WHERE COD_BANCO=?";
    private static final String FIND_BY_DATE = "SELECT * FROM TRANSACCION WHERE FECHA BETWEEN ? AND ?";    
    private static final Logger LOG = Logger.getLogger(TransaccionDAO.class.getName());

    private final ConnectionManager connectionManager;
    private final Connection conn;

    public TransaccionDAO() {
        this.connectionManager = ConnectionManager.getInstance();
        this.conn = this.connectionManager.getConnection();
    }

    public void insert(Transaccion transaccion) {
        try {
            PreparedStatement pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, transaccion.getCodigoBanco());
            pstm.setString(2, transaccion.getCodigoDispositivo());
            pstm.setString(3, transaccion.getRequest());
            pstm.setString(4, transaccion.getResponse());
            pstm.setString(5, transaccion.getEstado());
            pstm.setDate(6, transaccion.getFecha());
            pstm.executeUpdate();
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método insert", sqlEx);
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }

    }
    
    public Transaccion findByPK(Integer codigo) {
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND_PK);
            pstm.setInt(1, codigo);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            Transaccion transaccion = new Transaccion();
            transaccion.setCodigo(rs.getInt(1));
            transaccion.setCodigoBanco(rs.getString(2));
            transaccion.setCodigoDispositivo(rs.getString(3));
            transaccion.setRequest(rs.getString(4));
            transaccion.setResponse(rs.getString(5));
            transaccion.setEstado(rs.getString(6));
            transaccion.setFecha(rs.getDate(7));
            transaccion.setIsoPais(rs.getString(8));
            return transaccion;

        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método findByPk", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }

    public List<Transaccion> findByBanco(String codigoBanco) {
        List<Transaccion> listaTransaccion = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_BANCO);
            pstm.setString(1, codigoBanco);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setCodigo(rs.getInt(1));
                transaccion.setCodigoBanco(rs.getString(2));
                transaccion.setCodigoDispositivo(rs.getString(3));
                transaccion.setRequest(rs.getString(4));
                transaccion.setResponse(rs.getString(5));
                transaccion.setEstado(rs.getString(6));
                transaccion.setFecha(rs.getDate(7));
                transaccion.setIsoPais(rs.getString(8));
                listaTransaccion.add(transaccion);
            }
            return listaTransaccion;
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método findByBanco", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }
    
    public List<Transaccion> findByDate(Date fechaInicio, Date fechaFin) {
        List<Transaccion> listaTransaccion = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_DATE);
            pstm.setDate(1, fechaInicio);
            pstm.setDate(2, fechaFin);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setCodigo(rs.getInt(1));
                transaccion.setCodigoBanco(rs.getString(2));
                transaccion.setCodigoDispositivo(rs.getString(3));
                transaccion.setRequest(rs.getString(4));
                transaccion.setResponse(rs.getString(5));
                transaccion.setEstado(rs.getString(6));
                transaccion.setFecha(rs.getDate(7));
                transaccion.setIsoPais(rs.getString(8));
                listaTransaccion.add(transaccion);
            }
            return listaTransaccion;
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método buscarTransacciones", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }
}
