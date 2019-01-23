package nl.hsleiden.persistence;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import nl.hsleiden.model.Sock;
import nl.hsleiden.model.User;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class UserDAO
{   
    public UserDAO()
    {
    }
    
    public List<User> getAll()
    {
        List<User> users = new ArrayList<>();
        try {   
            Connection c = JDBC.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM user_account");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                users.add(extractFromResultSet(rs));
            }
            c.close();
        } catch (SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public User getByEmailAddress(String user_email){
        User user = new User();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user_account WHERE user_email = ?";
        
        try {
            c = JDBC.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, user_email);
            rs = ps.executeQuery();
            
            if(rs.next()){
                user.setUser_name(rs.getString("user_name"));
                user.setUser_postcode(rs.getString("user_postcode"));
                user.setUser_streetnumber(rs.getString("user_streetnumber"));
                user.setUser_email(rs.getString("user_email"));
                user.setUser_password(rs.getString("user_password"));
                Array a = rs.getArray("user_roles");
                String[] roles = (String[])a.getArray();
                user.setUser_roles(roles);
            }
        } catch(SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return user;
      
    }
    
    public void add(User user){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO user_account (user_name, user_postcode, user_streetnumber, user_email, user_password, user_roles)\n" +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            c = JDBC.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getUser_postcode());
            ps.setString(3, user.getUser_streetnumber());
            ps.setString(4, user.getUser_email());
            ps.setString(5, user.getUser_password());
            ps.setArray(6, c.createArrayOf("varchar",user.getUser_roles()));
            ps.execute();
        } catch(SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void update(String user_email, User user){ 
        Connection c = null;
        PreparedStatement ps = null;
        
        String sql = "UPDATE user_account SET user_name = ?,"
                + "user_postcode = ?,"
                + "user_streetnumber = ?,"
                + "user_email = ?,"
                + "user_password = ?,"
                + "user_roles = ? "
                + "WHERE user_email = ?";
        
        try {
            c = JDBC.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getUser_postcode());
            ps.setString(3, user.getUser_streetnumber());
            ps.setString(4, user.getUser_email());
            ps.setString(5, user.getUser_password());
            ps.setArray(6, c.createArrayOf("varchar",user.getUser_roles()));
            ps.setString(7,user_email);
            ps.execute();
        } catch(SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void delete(String user_email){
        Connection c = null;
        PreparedStatement ps = null;
        
        String sql = "DELETE FROM user_account WHERE user_email = ?";
        
        try {
            c = JDBC.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, user_email);
            ps.execute();
        } catch(SQLException ex){
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public User extractFromResultSet(ResultSet rs) throws SQLException{
        User user = new User();
        try {
            user.setUser_name(rs.getString("user_name"));
            user.setUser_postcode(rs.getString("user_postcode"));
            user.setUser_streetnumber(rs.getString("user_streetnumber"));
            user.setUser_email(rs.getString("user_email"));
            user.setUser_password(rs.getString("user_password"));
            Array a = rs.getArray("user_roles"); 
            String[] rolesstring = (String[])a.getArray();
            user.setUser_roles(rolesstring);
        } catch (SQLException ex) {
            Logger.getLogger(SockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
