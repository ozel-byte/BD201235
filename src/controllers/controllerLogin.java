package controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import persistencia.Usuario;
import persistencia.UsuarioDAO;



public class controllerLogin {

    @FXML
    private TextField inputnombre;

    @FXML
    private PasswordField inputpassword;

    @FXML
    private Label msgErrorPass;

    @FXML
    private Label msgErrorNombre;

    @FXML
    private Label msgUserError;

    @FXML
    private Button buttonsignin;
    @FXML
    private ProgressIndicator loading;
    Usuario resp;

    Task task = new Task<Void>() {
        @Override
        public Void call(){
            UsuarioDAO userdao = new UsuarioDAO();
            resp = userdao.getUsuario(inputnombre.getText(),inputpassword.getText());
            return null;
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            if (resp!=null){
                System.out.println("entro aqui");
                if (resp.getRol().equals("admin")){
                    System.out.println("aqui 2");
                    ventananew("../view/Admin.fxml");
                }else if (resp.getRol().equals("user")){
                    ventananew("../view/Home.fxml");
                }

            }else {
                 msgUserError.setVisible(true);
            }
            updateMessage("done!");

        }
        @Override
        protected void failed() {
            super.failed();
            loading.setVisible(false);
        }
    };

    public void ventananew(String ruta){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ruta));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            System.out.println("aqui33");
            Stage stage2 = (Stage) buttonsignin.getScene().getWindow();
            stage2.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void iniciarSesion(){

        inputnombre.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldProperty, Boolean newProperty) {
                if (newProperty){
                    msgErrorNombre.setVisible(false);
                    msgErrorPass.setVisible(false);
                    msgUserError.setVisible(false);
                }
            }
        });
        inputpassword.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldProperty, Boolean newProperty) {
                if (newProperty){
                    msgErrorNombre.setVisible(false);
                    msgErrorPass.setVisible(false);
                    msgUserError.setVisible(false);
                }
            }
        });

        if (inputnombre.getText().length()>0 && inputpassword.getText().length()>0){

            Thread t = new Thread(task);
            t.setDaemon(true);
            t.start();
            loading.setVisible(true);


        }else{
            if (inputnombre.getText().length()==0 && inputpassword.getText().length()==0){
                msgErrorNombre.setVisible(true);
                msgErrorPass.setVisible(true);
            }else if (inputnombre.getText().length()==0){
                msgErrorNombre.setVisible(true);
            }else if (inputpassword.getText().length()==0){
                msgErrorPass.setVisible(true);
            }

        }
    }

}