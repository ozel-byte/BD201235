package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import persistencia.Usuario;
import persistencia.UsuarioDAO;

public class controllerAdmin {

    @FXML
    private TableView<Usuario> listUser;

    @FXML
    private TableColumn<Usuario, String> nombreUser;

    @FXML
    private TableColumn<Usuario, String> passUser;

    @FXML
    private TableColumn<Usuario, String> statusUser;

    @FXML
    private TableColumn<Usuario, String> rolUser;

    @FXML
    private TableColumn<Usuario, String> buttons;

    @FXML
    private Pane fondoVentaCrearCuenta;

    @FXML
    private Pane crearCuentaVentana;

    @FXML
    private TextField inputNombre;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private ImageView checkPass;

    @FXML
    private Button closeVentana;


    @FXML
    private void initialize(){
        ObservableList<Usuario> user = FXCollections.observableArrayList();
        UsuarioDAO userdao= new UsuarioDAO();
           user = userdao.getUsuarios();
           listUser.setItems(user);
           nombreUser.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
           passUser.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
           statusUser.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
           rolUser.setCellValueFactory(cellData -> cellData.getValue().rolProperty());
        Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>> cellFactory = new Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>>(){
            @Override
            public TableCell call(final TableColumn<Usuario, String> param) {
                final TableCell<Usuario, String> cell = new TableCell<Usuario, String>() {

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
                                Usuario user = getTableView().getItems().get(getIndex());
                                System.out.println(user.getNombre()
                                        + "   " + user.getRol());
                            });
                            btnEliminar.setOnAction(event -> {
                                Usuario user = getTableView().getItems().get(getIndex());
                                System.out.println(user.getNombre()
                                        + "   " + user.getRol());
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
        buttons.setCellFactory(cellFactory);
    }

    @FXML
    public void abrirVentanaDeCrearCuenta(){
        fondoVentaCrearCuenta.setVisible(true);
        crearCuentaVentana.setVisible(true);
    }
    @FXML
    public void agregarUsuario(){

    }
    @FXML
    public void cerrarVenta(){
        fondoVentaCrearCuenta.setVisible(false);
        crearCuentaVentana.setVisible(false);
    }

}
