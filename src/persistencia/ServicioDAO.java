package persistencia;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class ServicioDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public ServicioDAO(){
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


    public Integer agregarServicio(String nombreServicio, int precio){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer servicioId = null;
        try {
            tx = session.beginTransaction();
            Servicio user = new Servicio(nombreServicio,precio);
            servicioId = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return servicioId;
    }
    public void eliminarServicio(int id){

        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Servicio servicio = (Servicio) session.get(Servicio.class, id);
        session.delete(servicio);
        tx.commit();
        session.close();

    }
    public void obtenerServicio(){
        Session session = factory.openSession();
        Criteria c = session.createCriteria(Usuario.class);
        List results = c.list();
        for(int i=0; i<results.size(); i++){
            Servicio servicio = (Servicio) results.get(i);
        }
        session.close();
    }
}

/*Ozel_struct*/
