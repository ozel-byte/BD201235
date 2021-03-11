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
import persistencia.Medicamento;
import persistencia.MedicamentoDAO;

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
    private ListView<?> listMascota;

    @FXML
    private AnchorPane anchorDuenos;

    @FXML
    private ListView<?> listDuenos;

    @FXML
    private Button agendarCita;

    @FXML
    private Button registro;

    @FXML
    private ImageView Home;

    @FXML
    private ImageView pets;

    @FXML
    private ImageView medicamentos;

    @FXML
    private ImageView users;

    @FXML
    private Button btHome;

    @FXML
    private Button btMascota;

    @FXML
    private Button btDueno;

    @FXML
    private Button btMedi;

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
        anchorHome.setVisible(false);
        anchorMascota.setVisible(true);
        anchorDuenos.setVisible(false);
        anchorMedi.setVisible(false);
        agregarMed.setVisible(false);
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

}
