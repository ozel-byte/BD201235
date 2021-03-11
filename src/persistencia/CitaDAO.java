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

public class CitaDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public CitaDAO(){
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

    public void agregar(Mascota mascota, Servicio servicio, String fecha){
        Session session = factory.openSession();
        session.beginTransaction();

        Cita nuevaCita = new Cita(fecha);
        nuevaCita.setMascotaC(mascota);
        nuevaCita.setServicioC(servicio);
        session.save(nuevaCita);

        session.getTransaction().commit();
        session.close();

        System.out.println("Agregados");
    }

    public ObservableList<Cita> etCita(){
        Session session = factory.openSession();
        ObservableList<Cita> citas = FXCollections.observableArrayList();

        Criteria cri = session.createCriteria(Cita.class);
        List cita = cri.list();
        for (Iterator iterator = cita.iterator(); iterator.hasNext();) {
            Cita dao = (Cita) iterator.next();
            citas.add(dao);
            System.out.println(dao.getIdcita() + " " + dao.getMascotaC().getNombre() + " " + dao.getServicioC().getTipo());
        }
        return citas;
    }

    public void deleteCita(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Cita dao = (Cita) session.get(Medicamento.class, id);
        System.out.println(dao.getMascotaC().getNombre());
        session.delete(dao);

        tx.commit();

    }


}
