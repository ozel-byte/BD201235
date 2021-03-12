package persistencia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


import java.util.List;

public class MascotaDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public MascotaDAO(){
        try {
            Configuration configuration = new Configuration().configure("Hibernate.cfg.xml");
            System.err.println("Leyendo configuracion.");

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Mascota agregarMascota(Dueno dueno, TipoMascota tipoMascota, String nombre){
        Session session = factory.openSession();
        Mascota mascota = new Mascota();
        Transaction tx = null;
        Integer mascotaId = null;
        try {
            tx = session.beginTransaction();
             mascota = new Mascota(dueno,tipoMascota,nombre);
            mascotaId = (Integer) session.save(mascota);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return mascota;


    }

    public ObservableList<Mascota> obtenerMascota(){
        ObservableList<Mascota> mascotaList = FXCollections.observableArrayList();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Mascota.class);
        List results = criteria.list();
        for (int i=0; i<results.size(); i++){
            Mascota mascota = (Mascota) results.get(i);
            mascotaList.add(mascota);
        }
        return mascotaList;
    }

    public void EliminarMascota(int id) {

        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Mascota mascota = (Mascota) session.get(Mascota.class, id);
            session.delete(mascota);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void actualizarMascota(Mascota mascota){
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Mascota mascotaobject = (Mascota) session.get(Mascota.class, mascota.getIdmascota());
        mascotaobject.setNombre(mascota.getNombre());
        session.update(mascotaobject);
        tx.commit();
        session.close();
    }



}