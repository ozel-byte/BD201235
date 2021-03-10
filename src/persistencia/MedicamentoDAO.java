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

public class MedicamentoDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public MedicamentoDAO(){
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

    public void agregar(String nombre, int codigo, String sustancia, String caducidad){
        Session session = factory.openSession();
        session.beginTransaction();

        Medicamento medicamento = new Medicamento(nombre, codigo, sustancia, caducidad);
        session.save(medicamento);

        session.getTransaction().commit();
        session.close();

        System.out.println("Agregados");
    }

    public List<Medicamento> getMedicamento(){
        Session session = factory.openSession();
        Criteria cri = session.createCriteria(Medicamento.class);
        List mediacamento = cri.list();
        if(mediacamento.size()>0){
            for (Iterator iterator = mediacamento.iterator(); iterator.hasNext();) {
                Medicamento dao = (Medicamento) iterator.next();
                System.out.println(dao.getNombre() + " " + dao.getCodigo() + " " + dao.getSustancia() + " " + dao.getFecha_Cad());
            }
        } else {
            System.out.println("No hay datos");
        }

        return mediacamento;
    }

    public void deleteMedicamento(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Medicamento dao = (Medicamento) session.get(Medicamento.class, id);
        System.out.println(dao.getNombre());
        session.delete(dao);

        tx.commit();

    }

    public void actualizar(int id){
        Session session = factory.openSession();

        Medicamento dao = (Medicamento)session.get(Medicamento.class, id);
        dao.setNombre("prueba1");
        dao.setCodigo(111111);
        dao.setSustancia("prueba1");
        dao.setFecha_Cad("10/10/2028");

        session.update(dao);
        session.getTransaction().commit();
        session.close();

    }

    /*//Obtienes el objeto a modificar
    MiClase objeto = (MiClase)sesion.get(MiClase.class, idDelObjeto);
//Lo modificas
objeto.setAlgunCampo(nuevoValor);
//Guardas los cambios
sesion.update(objeto);*/


}
