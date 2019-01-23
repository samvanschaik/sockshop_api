package nl.hsleiden.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import nl.hsleiden.model.Sock;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class SockDAO
{   
    public SockDAO(){
    }
    
    public List<Sock> getAll(){
        List<Sock> socks = new ArrayList<>();
        try {   
            Connection c = JDBC.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM socks");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                socks.add(extractFromResultSet(rs));
            }
            c.close();
        } catch (SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socks;
    }
    
    public Sock get(String sock_name){
        Sock sock = new Sock();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM socks WHERE sock_name = ?";
        
        try {
            c = JDBC.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, sock_name);
            rs = ps.executeQuery();
            
            if(rs.next()){
                sock = extractFromResultSet(rs);
            }
            
        } catch(SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return sock;
    }
    
    public void add(Sock sock){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO socks (sock_name, sock_size, sock_color, sock_price, sock_amount_stocked, sock_url)\n" +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            c = JDBC.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, sock.getSock_name());
            ps.setString(2, sock.getSock_size());
            ps.setString(3, sock.getSock_color());
            ps.setInt(4, sock.getSock_price());
            ps.setInt(5, sock.getSock_amount_stocked());
            ps.setString(6, sock.getSock_url());
            ps.execute();
        } catch(SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void update(String sock_name, Sock sock){ 
        Connection c = null;
        PreparedStatement ps = null;
        
        String sql = "UPDATE socks SET sock_name = ?,"
                + "sock_size = ?,"
                + "sock_color = ?,"
                + "sock_price = ?,"
                + "sock_amount_stocked = ?,"
                + "sock_url = ? "
                + "WHERE sock_name = ?";
        
        try {
            c = JDBC.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, sock.getSock_name());
            ps.setString(2, sock.getSock_size());
            ps.setString(3, sock.getSock_color());
            ps.setInt(4, sock.getSock_price());
            ps.setInt(5, sock.getSock_amount_stocked());
            ps.setString(6, sock.getSock_url());
            ps.setString(7, sock_name);
            ps.execute();
        } catch(SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void delete(String sock_name){
        Connection c = null;
        PreparedStatement ps = null;
        
        String sql = "DELETE FROM socks WHERE sock_name = ?";
        
        try {
            c = JDBC.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, sock_name);
            ps.execute();
        } catch(SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public Sock extractFromResultSet(ResultSet rs){
        Sock sock = new Sock();
        try {
            sock.setSock_name(rs.getString("sock_name"));
            sock.setSock_size(rs.getString("sock_size"));
            sock.setSock_color(rs.getString("sock_color"));
            sock.setSock_price(rs.getInt("sock_price"));
            sock.setSock_amount_stocked(rs.getInt("sock_amount_stocked"));
            sock.setSock_url(rs.getString("sock_url"));
        } catch (SQLException ex) {
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sock;
    }
}
