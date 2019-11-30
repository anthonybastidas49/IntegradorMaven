/*
 * ESPE - DCC - APLICACIONES DISTRIBUIDAS
 * Sistema: Integrador
 * Creado: 10/11/2019 - 14:26:01
 * 
 * Los contenidos de este archivo son propiedad privada y estan protegidos por 
 * la licencia BSD.
 * 
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package ec.edu.espe.integrador.dao;

import ec.edu.espe.integrador.bdd.ConnectionManager;
import ec.edu.espe.integrador.modelo.SesionPos;
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
 * Clase SesionPosDAO. Realizacion del CRUD en la tabla SESION_POS
 *
 * @author Paspuel-Torres
 */
public class SesionPosDAO {

    private final String INSERT = "INSERT INTO SESION_POS(COD_SESION,FECHA_CREACION,FECHA_ULTIMO_ACCESO) VALUES (NULL,?,?)";
    private final String FIND_PK = "SELECT * FROM SESION_POS WHERE COD_SESION=?";
    private final String FIND_LAST_ACCESS = "SELECT * FROM SESION_POS WHERE FECHA_ULTIMO_ACCESO<?";
    private final String UPDATE = "UPDATE SESION_POS SET FECHA_ULTIMO_ACCESO=? WHERE COD_SESION=?";
    
    private static final Logger LOG = Logger.getLogger(SesionPosDAO.class.getName());

    private final ConnectionManager connectionManager;
    private final Connection conn;

    public SesionPosDAO() {
        this.connectionManager = ConnectionManager.getInstance();
        this.conn = this.connectionManager.getConnection();
    }

    public void insert(SesionPos sesion) {
        try {
            PreparedStatement pstm = conn.prepareStatement(INSERT);
            pstm.setDate(1, sesion.getFechaCreacion());
            pstm.setDate(2, sesion.getFechaUltimoAcceso());
            pstm.executeUpdate();
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método insert", sqlEx);
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }
    
    public SesionPos findByPk(Integer codigo) {
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND_PK);
            pstm.setInt(1, codigo);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            SesionPos sesion = new SesionPos();
            sesion.setCodigo(rs.getInt(1));
            sesion.setFechaCreacion(rs.getDate(2));
            sesion.setFechaUltimoAcceso(rs.getDate(3));
            return sesion;
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método buscarSesión", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }

    public List<SesionPos> findByFechaUltimoAcceso(Date fecha) {
        List<SesionPos> sesiones = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND_LAST_ACCESS);
            pstm.setDate(1, fecha);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SesionPos sesion = new SesionPos();
                sesion.setCodigo(rs.getInt(1));
                sesion.setFechaCreacion(rs.getDate(2));
                sesion.setFechaUltimoAcceso(rs.getDate(3));
                sesiones.add(sesion);
            }
            return sesiones;
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método dinfByFechaUltimoAcceso", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }

    public void update(Integer codigo, Date fechaUltimoAcceso) {
        try {
            PreparedStatement pstm = conn.prepareStatement(UPDATE);
            pstm.setDate(1, fechaUltimoAcceso);
            pstm.setInt(2, codigo);
            ResultSet rs = pstm.executeQuery();
            rs.next();
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método update", sqlEx);
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }
}
