package persistencia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public ObservableList<TipoMascota> getTipo2(){
        ObservableList<TipoMascota> tipoarray = FXCollections.observableArrayList();
        Session session = factory.openSession();
        Criteria cri = session.createCriteria(TipoMascota.class);
        List tipomascota = cri.list();
        for (Iterator iterator = tipomascota.iterator(); iterator.hasNext();) {
            TipoMascota dao = (TipoMascota) iterator.next();
            tipoarray.add(dao);
        }
        return tipoarray;
    }

    //Metodo para traer datos de un tipo de mascota en especifico
    public void getTipoM(int id){
        Session session = factory.openSession();
        Mascota da = new Mascota();
        TipoMascota dao =(TipoMascota)session.get(TipoMascota.class, id);
        List aux = dao.getMascota();
        for(int i=0; i<aux.size(); i++){
             da = (Mascota) aux.get(i);

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

    public void updateTipo(TipoMascota tipoMascota){
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
            TipoMascota dao = (TipoMascota) session.get(TipoMascota.class, tipoMascota.getIdtipo());
            dao.setSexo(tipoMascota.getSexo());
            dao.setEspecie(tipoMascota.getEspecie());
            dao.setRaza(tipoMascota.getRaza());
            session.update(dao);

        tx.commit();
    }

}
