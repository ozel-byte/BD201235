package persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

public class DuenoDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;


    public DuenoDAO(){
        try {
            Configuration configuration = new Configuration();
            System.err.println("Leyendo configuracion.");
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public void getDueno(){
        Session session = factory.openSession();
        List dueno = session.createQuery("from duenom").list();
        for (Iterator iterator = dueno.iterator(); iterator.hasNext();){
            Dueno dao = (Dueno) iterator.next();
            System.out.println( dao.getNombre()+ "\t" + dao.getCorreo()+"\t" + dao.getDireccion());
        }
    }



}
