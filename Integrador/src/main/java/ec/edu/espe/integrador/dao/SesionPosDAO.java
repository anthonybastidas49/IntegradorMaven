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

import ec.edu.espe.distribuidas.dbutils.bdd.AbstractDAO;
import ec.edu.espe.integrador.modelo.SesionPos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase SesionPosDAO. Realizacion del CRUD en la tabla SESION_POS
 *
 * @author Paspuel-Torres
 */
public class SesionPosDAO extends AbstractDAO<SesionPos>{

    private final String INSERT = "INSERT INTO SESION_POS(COD_SESION,FECHA_CREACION,FECHA_ULTIMO_ACCESO) VALUES (?,?,?)";
    private final String FIND_PK = "SELECT * FROM SESION_POS WHERE COD_SESION=?";
    private final String FIND_LAST_ACCESS = "SELECT * FROM SESION_POS WHERE COD_SESION=? AND FECHA_ULTIMO_ACCESO>?";
    private final String FIND_LAST_INSERT="SELECT * FROM SESION_POS ORDER BY COD_SESION DESC LIMIT 1";
    private final String UPDATE = "UPDATE SESION_POS SET FECHA_ULTIMO_ACCESO=? WHERE COD_SESION=?";
    
    private static final Logger LOG = Logger.getLogger(SesionPosDAO.class.getName());

    public SesionPosDAO() {
        super();
    }

    public SesionPos findByPk(Integer codigo) {
        try {
            return super.findByPK(new Object[]{codigo});
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "ERROR AL EJECUTAR EL METODO FINDBYPK", sqlEx);
            return null;
        } finally {
            super.closeConnection();
        }
    }
    public SesionPos findByLastInsert(){
        try {
            return super.findByParameter(this.FIND_LAST_INSERT,new Object[]{}).get(0);
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE,"ERROR AL EJECUTAR EL METODO FindByLastInsert",sqlEx);
            return null;
        }finally{
            super.closeConnection();
        }
    }
    public void insert(SesionPos sesionPos){
        sesionPos.setCodigo(null);
        try {
            Object parametros[] = new Object[]{
                sesionPos.getCodigo(),
                sesionPos.getFechaCreacion(),
                sesionPos.getFechaUltimoAcceso()
            };
            super.insert(parametros);
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el metodo insert ", sqlEx);
        } finally{
            //super.closeConnection();
        }
    }
    public SesionPos findBySesion(SesionPos sesion){
        System.out.println(sesion.getCodigo());
        try {
            return super.findByParameter(this.FIND_LAST_ACCESS,new Object[]{sesion.getCodigo(),sesion.getFechaUltimoAcceso()}).get(0);
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE,"ERROR AL EJECUTAR EL METODO FINDBYESTADO",sqlEx);
            return null;
        }catch(IndexOutOfBoundsException sqlEx){
           LOG.log(Level.SEVERE,"ERROR AL EJECUTAR EL METODO FINDBYESTADO",sqlEx);
            return null; 
        }
        finally{
            super.closeConnection();
        }
    }
    public void update(SesionPos sesionPos){
        try {
            Object parametros[] = new Object[]{
                sesionPos.getFechaUltimoAcceso(),
                sesionPos.getCodigo()
                
            };
            super.update(parametros);
        } catch (SQLException sqlEx) {
            LOG.log(Level.SEVERE, "Error al ejecutar el metodo insert ", sqlEx);
        } finally{
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
        return this.UPDATE;
    }

    @Override
    public SesionPos createObject(ResultSet rs) throws SQLException {
        SesionPos retorno =new SesionPos();
        retorno.setCodigo(rs.getInt(1));
        retorno.setFechaCreacion(rs.getDate(2));
        retorno.setFechaUltimoAcceso(rs.getDate(3));
        return retorno;
    }
    
}
