package persistencia;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
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


    public Dueno agregarDueno()
    {
        Session session = factory.openSession();
        session.beginTransaction();
        //instancia pa iniciar el registro
        Dueno registro = new Dueno();

        //Agregando
        registro.setNombre("x3");
        registro.setCorreo("ejemplo xd");
        registro.setDireccion("av abc");
        registro.setTelefono("96123456");

        //se guarda el objeto en la base de datos
        session.save(registro);
        session.getTransaction().commit();
        session.close();

        return registro;
    }

    public void modificar()
    {
        Dueno cambiar = new Dueno();

        cambiar.setNombre("hola");


        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(cambiar);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteDueno(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Dueno dao = (Dueno) session.get(Dueno.class, id);
        System.out.println(dao.getNombre());
        session.delete(dao);
        tx.commit();
    }
}
