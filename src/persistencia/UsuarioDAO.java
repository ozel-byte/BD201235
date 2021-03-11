package persistencia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryRegistry;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class UsuarioDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public UsuarioDAO(){
        try {
            Configuration configuration = new Configuration();
            System.err.println("Leyendo configuracion.");
            configuration.configure("Hibernate.cfg.xml");
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Integer addUsuario(String nombre, String password, String status,String rol){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userID = null;

        try {
            tx = session.beginTransaction();
            Usuario user = new Usuario(nombre, password, status,rol);
            userID = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;
    }
    public void deleteUsuario(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Usuario dao = (Usuario) session.get(Usuario.class, id);
        session.delete(dao);
        tx.commit();
      session.close();
    }
    public void updateUsuario(){}

    public Usuario getUsuario(String nombre,String password){
        Session s = factory.openSession();
        Criteria c = s.createCriteria(Usuario.class);
        Criterion nom = Restrictions.eq("nombre",nombre);
        Criterion pass = Restrictions.eq("password", password);
        LogicalExpression andExp = Restrictions.and(nom,pass);
        c.add(andExp);
        List results = c.list();
        for(int i=0; i<results.size(); i++){
            Usuario user = (Usuario) results.get(i);
            System.out.println(user.getNombre()+" "+user.getPassword()+" "+user.getRol()+" "+user.getStatus());
            return user;
        }

        s.close();

        return null;

    }

    public ObservableList<Usuario> getUsuarios(){
        ObservableList<Usuario> users = FXCollections.observableArrayList();
        Session s = factory.openSession();
        Criteria c = s.createCriteria(Usuario.class);
        List results = c.list();
        for(int i=0; i<results.size(); i++){
            Usuario user = (Usuario)results.get(i);
            users.add(user);
        }

        s.close();

        return users;
    }
}
/*Ozel_struct*/