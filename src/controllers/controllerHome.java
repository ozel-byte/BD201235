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
import javafx.stage.Stage;
import javafx.util.Callback;
import persistencia.Medicamento;
import persistencia.MedicamentoDAO;

import java.time.format.DateTimeFormatter;


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

    @FXML
    private void initialize(){
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
    private void addMedi(){

        Medicamento m = new Medicamento();

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GestionMedicamentos.fxml"));
            Parent root = (Parent)loader.load();
            controllerGestionMedicamentos cgm = loader.getController();
            //cgm.pasarDatosUsuario(m);

            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void obeserbaleMedicamentos(){
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
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
                                //gestionMedi(2);

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
