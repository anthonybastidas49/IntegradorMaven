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

import ec.edu.espe.distribuidas.dbutils.bdd.ConnectionManager;
import ec.edu.espe.integrador.modelo.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase BancoDAO. Realizacion del CRUD en la tabla BANCO
 *
 * @author Paspuel-Torres
 */
public class BancoDAO {
    private static final String BUSCAR_PK="SELECT * FROM BANCO WHERE COD_BANCO=?";   
    private static final Logger LOG = Logger.getLogger(BancoDAO.class.getName());
        
    private final ConnectionManager connectionManager;
    private final Connection conn;

    public BancoDAO() {
        this.connectionManager = ConnectionManager.getInstance();
        this.conn = this.connectionManager.getConnection();
    }
    public Banco buscarPorPK(String codigo){
        try {
            PreparedStatement pstm = conn.prepareStatement(BUSCAR_PK);
            pstm.setString(1, codigo);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            Banco banco=new Banco();
            banco.setCodigo(rs.getString(1));
            banco.setNombre(rs.getString(2));
            banco.setIp(rs.getString(3));
            banco.setPuerto(rs.getInt(4));
            return banco;
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el método bucarPorPK", sqlEx);
            return null;
        } finally {
            this.connectionManager.releaseConnection(this.conn);
        }
    }
    
}
