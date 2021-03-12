package persistencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
            configuration.configure("Hibernate.cfg.xml");
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public TipoMascota crearTipo(TipoMascota tipoMascota){
        Session session = factory.openSession();
        session.beginTransaction();
        Integer tipoMascotaId = null;
        session.save(tipoMascota);
        session.getTransaction().commit();
        session.close();

       return tipoMascota;
    }

    //Metodo para obtener todos los tipos de mascotas
    public void getTipo(){
        Session session = factory.openSession();
        List tipomascota = session.createQuery("from TipoMascota").list();
        for (Iterator iterator = tipomascota.iterator(); iterator.hasNext();){
            TipoMascota dao = (TipoMascota) iterator.next();

        }
    }

    public void getTipo2(){
        Session session = factory.openSession();
        Criteria cri = session.createCriteria(TipoMascota.class);
        List tipomascota = cri.list();
        for (Iterator iterator = tipomascota.iterator(); iterator.hasNext();) {
            TipoMascota dao = (TipoMascota) iterator.next();

        }
    }

    //Metodo para traer datos de un tipo de mascota en especifico
    public void getTipoM(int id){
        Session session = factory.openSession();

        TipoMascota dao =(TipoMascota)session.get(TipoMascota.class, id);
        List aux = dao.getMascota();
        for(Iterator iterator = aux.iterator(); iterator.hasNext();){
            Mascota da = (Mascota) iterator.next();

        }
    }

    public void deleteTipo(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
            TipoMascota dao = (TipoMascota) session.get(TipoMascota.class, id);
            System.out.println(dao.getEspecie());
            session.delete(dao);

        tx.commit();

    }

    public void updateTipo(){
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
            TipoMascota dao = (TipoMascota) session.get(TipoMascota.class, 1);
            System.out.println(dao.getRaza());
            session.update("Husky", dao);

        tx.commit();
    }

}
