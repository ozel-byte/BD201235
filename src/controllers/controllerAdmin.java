package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import persistencia.Usuario;
import persistencia.UsuarioDAO;

public class controllerAdmin {

    @FXML
    private TableView<Usuario> listUser;

    @FXML
    private TableColumn<Usuario, String> idUser;

    @FXML
    private TableColumn<Usuario, String> checkUser;

    @FXML
    private TableColumn<Usuario, String> nombreUser;

    @FXML
    private TableColumn<Usuario, String> passUser;

    @FXML
    private TableColumn<Usuario, String> statusUser;

    @FXML
    private TableColumn<Usuario, String> rolUser;

    @FXML
    private TableColumn<Usuario, String> editarUser;

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
    private Text total;

    @FXML
    private TableView<Usuario> listdelete;

    @FXML
    private TableColumn<Usuario, String> nombreListDeelete;

    @FXML
    private TableColumn<Usuario, String> passlistDelete;

    @FXML
    private TableColumn<Usuario, String> statusListDelete;

    @FXML
    private TableColumn<Usuario, String> rolListDelete;

    @FXML
    private Button buttoneliminar;
    @FXML
    private Pane ventanaeliminar;

    @FXML
    private Pane fondoEliminar;

    Integer contador=0;
    private Integer totalEliminar=0;
    boolean aux=false;
    int aux2;
    ObservableList<Usuario> listDelete = FXCollections.observableArrayList();
    Usuario user;


    @FXML
    private void initialize(){
        obtenerLista();

    }
    public void obtenerLista(){
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

                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        editarUser.setCellFactory(cellFactory);
        generarColumCheck();
        generarColumnaId();
    }

    public void generarColumCheck(){
        Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>> cellFactory = new Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>>(){
            @Override
            public TableCell call(final TableColumn<Usuario, String> param) {
                final TableCell<Usuario, String> cell = new TableCell<Usuario, String>() {

                   final CheckBox check = new CheckBox();


                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        check.selectedProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {

                                if (aBoolean){
                                    user = getTableView().getItems().get(getIndex());
                                    System.out.println(user.getNombre()+"true"+" old:");
                                }
                           if (t1.booleanValue()){
                               user = getTableView().getItems().get(getIndex());
                               System.out.println(user.getNombre()+"true");
                                if (listDelete.size()==0){
                                    totalEliminar=0;
                                }else{
                                    totalEliminar+=1;
                                }
                                listDelete.add(user);
                                total.setText(String.valueOf(listDelete.size()));
                           }else{
                               listDelete.remove(user);
                               total.setText(String.valueOf(listDelete.size()));
                               for (int i=0; i<listDelete.size(); i++){
                                   System.out.println(listDelete.get(i).getNombre()+" lista-eliminar");
                               }
                           }
                            }
                        });
                            setGraphic(check);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        checkUser.setCellFactory(cellFactory);
    }

    public void generarColumnaId(){
        Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>> cellFactory = new Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>>(){
            @Override
            public TableCell call(final TableColumn<Usuario, String> param) {
                final TableCell<Usuario, String> cell = new TableCell<Usuario, String>() {

                    Text t = new Text();

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            t.setText(contador.toString());
                            setGraphic(t);
                            contador+=1;
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        idUser.setCellFactory(cellFactory);
    }

    @FXML
    public void abrirVentanaDeCrearCuenta(){
        fondoVentaCrearCuenta.setVisible(true);
        crearCuentaVentana.setVisible(true);
    }
    @FXML
    public void agregarUsuario(){
            UsuarioDAO usuariodao = new UsuarioDAO();
            usuariodao.addUsuario(inputNombre.getText(),inputPassword.getText(),"false","user");
            obtenerLista();
            contador=0;
            crearCuentaVentana.setVisible(false);
            fondoVentaCrearCuenta.setVisible(false);
    }
    @FXML
    public void cerrarVenta(){
        fondoVentaCrearCuenta.setVisible(false);
        crearCuentaVentana.setVisible(false);
    }
    @FXML
    public void abrirventanaEliminar(){
        ventanaeliminar.setVisible(true);
        fondoEliminar.setVisible(true);
        nombreListDeelete.setCellValueFactory(callData -> callData.getValue().nombreProperty());
        passlistDelete.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        statusListDelete.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        rolListDelete.setCellValueFactory(cellData -> cellData.getValue().rolProperty());
        listdelete.setItems(listDelete);
    }



    public void eliminarUserLista(){

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    for (int i=0; i<listDelete.size(); i++){
        System.out.println(listDelete.get(i).getNombre()+" "+listDelete.get(i).getId());
        usuarioDAO.deleteUsuario(listDelete.get(i).getId());
    }
    obtenerLista();
    contador=0;
    ventanaeliminar.setVisible(false);
    fondoEliminar.setVisible(false);

    }
    public void cerrarVentaEliminar(){
        ventanaeliminar.setVisible(false);
        fondoEliminar.setVisible(false);
    }

}
