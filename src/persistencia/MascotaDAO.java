package persistencia;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

public class MascotaDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public MascotaDAO(){
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

 /*
    public Integer integerMascota(String nombre, TipoMascota tipoMascota, Dueno dueno){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer daoID = null;
        try{
            tx = session.beginTransaction();
            Mascota mascota = new Mascota(nombre, tipoMascota, dueno);
            daoID = (Integer) session.save(mascota);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return daoID;
    }
*/

    public void getMascota(){
        Session session = factory.openSession();
        List mascota = session.createQuery("from Mascota").list();
        for (Iterator iterator = mascota.iterator(); iterator.hasNext();){
            Mascota dao = (Mascota) iterator.next();
            System.out.println( dao.getNombre()+" " + dao.getDueno().getIdDueno()+ " "+dao.getTipoMascota().getIdtipo());
        }
    }


    public void CrearMascota(TipoMascota tipo, Dueno dueno){

        Session session = factory.openSession();
        session.beginTransaction();

        /*TipoMascota tp = tipo;
        session.save(tp);

        Dueno d = dueno;
        session.save(d);*/


        /*TipoMascota tp = new TipoMascota();
        tp.setEspecie("Gato");
        tp.setRaza("Siberiano");
        tp.setSexo("Macho");
        session.save(tp);*/

        Mascota mascota = new Mascota("Chop");
        mascota.setTipoMascota(tipo);
        mascota.setDueno(dueno);
        session.save(mascota);

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

        /*public void modificarMascota(){
            Session session = factory.openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Mascota mascota = (Mascota) session.get(Mascota.class, 1);
                session.update(mascota);
                tx.commit();
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();
            }
        }*/

}