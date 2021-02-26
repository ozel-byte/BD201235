package persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

public class TipoMascotaDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public TipoMascotaDAO(){
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


    public void getTipo(){
        Session session = factory.openSession();
        List mascota = session.createQuery("from Mascota").list();
        for (Iterator iterator = mascota.iterator(); iterator.hasNext();){
            TipoMascota dao = (TipoMascota) iterator.next();
            System.out.println( dao.getEspecie()+ "\t" + dao.getSexo()+"\t" + dao.getRaza());
        }
    }
}
