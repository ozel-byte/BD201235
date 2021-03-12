package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import persistencia.*;

public class controllerHome {

    @FXML
    private AnchorPane anchorHome;

    @FXML
    private DatePicker fecha;

    @FXML
    private ListView<?> listHome;

    @FXML
    private AnchorPane anchorMedi;

    @FXML
    private TableView<Medicamento> tableMed;

    @FXML
    private TableColumn<Medicamento, String> cNombre;

    @FXML
    private TableColumn<Medicamento, Integer> cCodigo;

    @FXML
    private TableColumn<Medicamento, String> cSustancia;

    @FXML
    private TableColumn<Medicamento, String> cFecha;

    @FXML
    private TableColumn<Medicamento, String> cBotones;

    @FXML
    private Button agregarMed;

    @FXML
    private AnchorPane anchorMascota;
    @FXML
    private TableView<Mascota> tablaMascota;

    @FXML
    private TableColumn<Mascota, String> nombreMascota;

    @FXML
    private TableColumn<Mascota, String> sexoMascota;

    @FXML
    private TableColumn<Mascota, String> razaMascota;

    @FXML
    private TableColumn<Mascota, String> duenoid;
    @FXML
    private TableColumn<Mascota, String> mascotaCita;

    @FXML
    private TableColumn<Mascota, String> botones;
    @FXML
    private AnchorPane anchorDuenos;
    @FXML
    private TableView <Dueno> tbvDuenos;
    @FXML
    private TableColumn<Dueno, String> colNombre;
    @FXML
    private TableColumn<Dueno, String> colDireccion;
    @FXML
    private TableColumn<Dueno, String> colTel;
    @FXML
    private TableColumn<Dueno, String> colCorreo;
    @FXML
    private TableColumn<Dueno, String> colDelete;
    @FXML
    private TableView<Cita> tablaCita;

    @FXML
    private TableColumn<Cita, String> fechaCita2;

    @FXML
    private TableColumn<Cita, String> nombreCita;

    @FXML
    private TableColumn<Cita, String> servicioCita;

    @FXML
    private TableColumn<Cita, String> costocita;

    @FXML
    private TableColumn<Cita, String> buttonsCita;

    ObservableList<Dueno> duenoObservableList = FXCollections.observableArrayList();
    DuenoDAO duenoDAO = new DuenoDAO();

    ObservableList<Medicamento> mediObservable = FXCollections.observableArrayList();
    controllerHome h;
    MedicamentoDAO medicamentoDAO = new MedicamentoDAO();


    @FXML
    private void initialize(){
        h=this;
    }

    @FXML
    private void btHome(){
        anchorHome.setVisible(true);
        anchorMascota.setVisible(false);
        anchorDuenos.setVisible(false);
        anchorMedi.setVisible(false);
        agregarMed.setVisible(false);
    }

    @FXML
    private void btMascota(){
        rellenarListaMascota();
    }
    public void rellenarListaMascota(){
        ObservableList<Mascota> masc = FXCollections.observableArrayList();
        MascotaDAO mascotaDAO = new MascotaDAO();
        masc=  mascotaDAO.obtenerMascota();
        anchorHome.setVisible(false);
        anchorMascota.setVisible(true);
        anchorDuenos.setVisible(false);
        anchorMedi.setVisible(false);
        agregarMed.setVisible(false);
        nombreMascota.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        sexoMascota.setCellValueFactory(cellData -> cellData.getValue().getTipoMascota().sexoProperty());
        razaMascota.setCellValueFactory(cellData -> cellData.getValue().getTipoMascota().razaProperty());
        duenoid.setCellValueFactory(cellData -> cellData.getValue().getDueno().nombreProperty());
        Callback<TableColumn<Mascota, String>, TableCell<Mascota, String>> cellFactory = new Callback<TableColumn<Mascota, String>, TableCell<Mascota, String>>()
        {
            @Override
            public TableCell call(final TableColumn<Mascota, String> param)
            {

                final TableCell<Mascota, String> cell = new TableCell<Mascota, String>()
                {
                    final Button botonEliminar = new Button("Eliminar");
                    final Button botonActualizar = new Button("actualizar");
                    @Override
                    public void updateItem(String item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        }
                        else
                        {
                            botonEliminar.setOnAction(event -> {
                                Mascota mas = getTableView().getItems().get(getIndex());
                                MascotaDAO masdao = new MascotaDAO();
                                masdao.EliminarMascota(mas.getIdmascota());
                                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                                mensaje.setTitle("Baja de dueño");
                                mensaje.setHeaderText("Se eliminó correctamente");
                                mensaje.show();
                               rellenarListaMascota();
                            });
                            botonActualizar.setOnAction(event -> {

                            });
                            HBox h = new HBox();
                            h.setSpacing(15);
                            h.getChildren().addAll(botonEliminar,botonActualizar);
                            setGraphic(h);
                            setText(null);
                        }
                    }

                };
                return cell;
            }
        };
        botones.setCellFactory(cellFactory);
        tablaMascota.setItems(masc);
    }

    @FXML
    private void btDueno(){
        anchorHome.setVisible(false);
        anchorMascota.setVisible(false);
        anchorDuenos.setVisible(true);
        anchorMedi.setVisible(false);
        agregarMed.setVisible(false);
    }

    @FXML
    private void btMedicamento(){
        obeserbaleMedicamentos();
        anchorHome.setVisible(false);
        anchorMascota.setVisible(false);
        anchorDuenos.setVisible(false);
        anchorMedi.setVisible(true);
        agregarMed.setVisible(true);
    }

    @FXML
    private void registrar(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Registros.fxml"));
            Parent root = (Parent)loader.load();
            //controllerGestionMedicamentos cgm = loader.getController();
            //cgm.recibir(h);

            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void agendar(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GenerarCita.fxml"));
            Parent root = (Parent)loader.load();
            //controllerGestionMedicamentos cgm = loader.getController();
            //cgm.recibir(h);

            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void addMedi(){
        Medicamento medicamento = new Medicamento();
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GestionMedicamentos.fxml"));
            Parent root = (Parent)loader.load();
            controllerGestionMedicamentos cgm = loader.getController();
            cgm.recibir(h,1, medicamentoDAO, medicamento);

            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void obeserbaleMedicamentos(){

        mediObservable = medicamentoDAO.getMedicamento();
        tableMed.setItems(mediObservable);
        cNombre.setCellValueFactory(Celldata -> Celldata.getValue().nombreProperty());
        cSustancia.setCellValueFactory(Celldata -> Celldata.getValue().sustanciaProperty());
        cFecha.setCellValueFactory(Celldata -> Celldata.getValue().fecha_CadProperty());
        cCodigo.setCellValueFactory(new PropertyValueFactory<Medicamento, Integer>("codigo"));

        Callback<TableColumn<Medicamento, String>, TableCell<Medicamento, String>> cellFactory = new Callback<TableColumn<Medicamento, String>, TableCell<Medicamento, String>>(){
            @Override
            public TableCell call(final TableColumn<Medicamento, String> param) {
                final TableCell<Medicamento, String> cell = new TableCell<Medicamento, String>() {

                    final Button btn = new Button("Editar");
                    final Button btnEliminar = new Button("Eliminar");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Medicamento user = getTableView().getItems().get(getIndex());
                                System.out.println(user.getNombre()
                                        + "   " + user.getIdmedicamento());

                                try {
                                    Stage stage = new Stage();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GestionMedicamentos.fxml"));
                                    Parent root = (Parent)loader.load();
                                    controllerGestionMedicamentos cgm = loader.getController();
                                    cgm.recibir(h,2, medicamentoDAO, user);

                                    stage.setScene(new Scene(root));
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.show();
                                }catch (Exception e){
                                    System.out.println(e);
                                }

                            });
                            btnEliminar.setOnAction(event -> {
                                Medicamento user = getTableView().getItems().get(getIndex());
                                System.out.println(user.getNombre()
                                        + "   " + user.getIdmedicamento());
                                medicamentoDAO.deleteMedicamento(user.getIdmedicamento());
                                obeserbaleMedicamentos();
                            });
                            HBox h = new HBox();
                            h.setSpacing(15);

                            h.getChildren().addAll(btn,btnEliminar);
                            setGraphic(h);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        cBotones.setCellFactory(cellFactory);
    }

    //para visualizar los dueños   Hernandez Alvarado Kevin


    @FXML
    public void mostrarDuenos()
    {
        anchorHome.setVisible(false);
        anchorMascota.setVisible(false);
        anchorDuenos.setVisible(false);
        anchorMedi.setVisible(false);
        agregarMed.setVisible(false);
        anchorDuenos.setVisible(true);
        duenoObservableList = duenoDAO.getDueno();
        tbvDuenos.setItems(duenoObservableList);
        colNombre.setCellValueFactory(new PropertyValueFactory<Dueno, String>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Dueno, String>("direccion"));
        colTel.setCellValueFactory(new PropertyValueFactory<Dueno, String>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<Dueno, String>("correo"));

        Callback<TableColumn<Dueno, String>, TableCell<Dueno, String>> cellFactory = new Callback<TableColumn<Dueno, String>, TableCell<Dueno, String>>()
        {
            @Override
            public TableCell call(final TableColumn<Dueno, String> param)
            {

                final TableCell<Dueno, String> cell = new TableCell<Dueno, String>()
                {
                    final Button botonEliminar = new Button("Eliminar");
                    @Override
                    public void updateItem(String item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        }
                        else
                        {
                           botonEliminar.setOnAction(event -> {
                               Dueno user = getTableView().getItems().get(getIndex());
                               duenoDAO.deleteDueno(user.getIdDueno());
                               Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                               mensaje.setTitle("Baja de dueño");
                               mensaje.setHeaderText("Se eliminó correctamente");
                               mensaje.show();
                               mostrarDuenos();
                           });
                            HBox h = new HBox();
                            h.setSpacing(15);
                            h.getChildren().addAll(botonEliminar);
                            setGraphic(h);
                            setText(null);
                        }
                    }

                };
            return cell;
            }
        };
        colDelete.setCellFactory(cellFactory);
    }





}
