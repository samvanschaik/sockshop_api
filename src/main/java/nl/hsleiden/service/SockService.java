package nl.hsleiden.service;

import java.sql.SQLException;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.Sock;
import nl.hsleiden.model.Sock;
import nl.hsleiden.persistence.SockDAO;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class SockService extends BaseService<Sock>
{
    private final SockDAO dao;
    
    @Inject
    public SockService(SockDAO dao)
    {
        this.dao = dao;
    }
    
    public Collection<Sock> getAll()
    {
        return dao.getAll();
    }
    
    public Sock get(String sock_name)
    {
        return requireResult(dao.get(sock_name));
    }
    
    public void add(Sock sock)
    {
        dao.add(sock);
    }
    
    public void update(String sock_name, Sock sock)
    {
        dao.update(sock_name, sock);
    }
    
    public void delete(String sock_name)
    {
        dao.delete(sock_name);
    }
}
