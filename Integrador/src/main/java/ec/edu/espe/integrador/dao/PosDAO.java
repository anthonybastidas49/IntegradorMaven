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

import ec.edu.espe.distribuidas.dbutils.bdd.ConnectionManager;
import ec.edu.espe.integrador.modelo.Pos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase PosDAO. Realizacion del CRUD en la tabla POS
 *
 * @author Paspuel-Torres
 */
public class PosDAO {

    private static final String FIND_PK = "SELECT * FROM POS WHERE COD_POS=?";
    private static final String FIND_BY_CODIGO_DISPOSITIVO = "SELECT * FROM POS WHERE COD_DISPOSITIVO=?";
    private static final String FIND_BY_ESTADO = "SELECT * FROM POS WHERE ESTADO=?";
    private static final Logger LOG = Logger.getLogger(PosDAO.class.getName());

    private final ConnectionManager connectionManager;
    private final Connection conn;

    public PosDAO() {
        this.connectionManager = ConnectionManager.getInstance();
        this.conn = this.connectionManager.getConnection();
    }

    public Pos findByPK(Integer codigo) {
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND_PK);
            pstm.setInt(1, codigo);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            Pos pos = new Pos();
            pos.setCodigo(rs.getInt(1));
            pos.setCodigoDispositivo(rs.getString(2));
            pos.setPin(rs.getString(3));
            pos.setEstado(rs.getString(4));
            return pos;
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método findByPK", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }

    public Pos findByCodigoDispositivo(String codigoDispositivo) {
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_CODIGO_DISPOSITIVO);
            pstm.setString(1, codigoDispositivo);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            Pos pos = new Pos();
            pos.setCodigo(rs.getInt(1));
            pos.setCodigoDispositivo(rs.getString(2));
            pos.setPin(rs.getString(3));
            pos.setEstado(rs.getString(4));
            return pos;
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método buscarDispositivo", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }
    
    public List<Pos> findByEstado(String estado) {
        List<Pos> listaPos=new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_ESTADO);
            pstm.setString(0, estado);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Pos pos = new Pos();
                pos.setCodigo(rs.getInt(1));
                pos.setCodigoDispositivo(rs.getString(2));
                pos.setPin(rs.getString(3));
                pos.setEstado(rs.getString(4));
                listaPos.add(pos);
            }

            return listaPos;
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método findByEstado", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }
}
