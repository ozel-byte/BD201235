package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private TextField idtextPassowrd;

    @FXML
    private Pane fondoEliminar;
    @FXML
    private ImageView candado;

    @FXML
    private ImageView checkPass1;
    @FXML
    private ImageView candadoTextField;
    @FXML
    private Text numerocaracteres;
    @FXML
    private Button botoncrearCuenta;
    @FXML
    private ProgressIndicator loadingCrearCuenta;
    @FXML
    private ProgressIndicator loadingEliminar;
    @FXML
    private Button cerrarVentanaAdmin;
    Integer numerocarac;

    Integer contador=0;
    private Integer totalEliminar=0;
    boolean aux=false;
    boolean aux2=false;
    ObservableList<Usuario> listDelete = FXCollections.observableArrayList();
    Usuario user;

    boolean mostrarpass= true;

    Task task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            UsuarioDAO usuariodao = new UsuarioDAO();
            usuariodao.addUsuario(inputNombre.getText(),inputPassword.getText(),"false","user");
            return null;
        }
        @Override
        protected void succeeded() {
            super.succeeded();
            obtenerLista();
            contador=0;
            inputNombre.setText("");
            inputPassword.setText("");
            loadingCrearCuenta.setVisible(false);
            crearCuentaVentana.setVisible(false);
            fondoVentaCrearCuenta.setVisible(false);
        }
    };
    Task taskEliminar = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            for (int i=0; i<listDelete.size(); i++){
                System.out.println(listDelete.get(i).getNombre()+" "+listDelete.get(i).getId());
                usuarioDAO.deleteUsuario(listDelete.get(i).getId());
            }
            return null;
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            obtenerLista();
            contador=0;
            ventanaeliminar.setVisible(false);
            fondoEliminar.setVisible(false);
            loadingEliminar.setVisible(false);
        }
    };


    @FXML
    private void initialize(){
        checkPass.setOnMouseClicked(event -> {
            System.out.println("ingreso aqui");
            idtextPassowrd.setVisible(true);
            inputPassword.setVisible(false);
            candado.setVisible(true);
            candadoTextField.setVisible(true);
            idtextPassowrd.setText(inputPassword.getText());
            checkPass1.setVisible(true);
            checkPass.setVisible(false);
        });
        inputNombre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length()>2){
                    aux=true;
                    if (aux && aux2){
                        botoncrearCuenta.setDisable(false);
                    }
                }else{
                    botoncrearCuenta.setDisable(true);
                    aux=false;

                }
            }
        });
        inputPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println(t1);
                numerocarac = t1.length();
                if (numerocarac <4){
                    numerocaracteres.setText(String.valueOf(numerocarac));
                    numerocaracteres.setFill(Color.RED);
                    botoncrearCuenta.setDisable(true);
                }else{
                    numerocaracteres.setText(String.valueOf(numerocarac));
                    numerocaracteres.setFill(Color.GREEN);
                   if (aux){
                       botoncrearCuenta.setDisable(false);
                       aux2=true;
                   }
                }

            }
        });
        checkPass1.setOnMouseClicked(event -> {
            idtextPassowrd.setVisible(false);
            inputPassword.setVisible(true);
            candado.setVisible(true);
            idtextPassowrd.setText(inputPassword.getText());
            checkPass1.setVisible(false);
            checkPass.setVisible(true);
            candadoTextField.setVisible(false);
        });
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
          Thread t = new Thread(task);
          t.setDaemon(true);
          t.start();
          botoncrearCuenta.setText(" ");
          loadingCrearCuenta.setVisible(true);


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
        Thread t = new Thread(taskEliminar);
        t.setDaemon(true);
        t.start();
        loadingEliminar.setVisible(true);
        buttoneliminar.setText(" ");
    }
    public void cerrarVentaEliminar(){
        ventanaeliminar.setVisible(false);
        fondoEliminar.setVisible(false);
    }

    public void cerrarVentanaAdmin(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            System.out.println("aqui33");
            Stage stage2 = (Stage) cerrarVentanaAdmin.getScene().getWindow();
            stage2.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
