package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            Usuario resp;
            UsuarioDAO userdao = new UsuarioDAO();
          resp = userdao.getUsuario(inputnombre.getText(),inputpassword.getText());
          if (resp!=null){
              if (resp.getRol().equals("admin")){
                  try{
                      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Admin.fxml"));
                      Parent root1 = (Parent) fxmlLoader.load();
                      Stage stage = new Stage();
                      stage.setScene(new Scene(root1));
                      stage.show();
                      Stage stage2 = (Stage) buttonsignin.getScene().getWindow();
                      stage2.close();
                  }catch (Exception e){

                  }
              }else if (resp.getRol().equals("user")){
                  try {

                      System.out.println("entro---");
                      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Home.fxml"));
                      Parent root1 = (Parent) fxmlLoader.load();
                      Stage stage = new Stage();
                      stage.setScene(new Scene(root1));
                      stage.show();
                      Stage stage2 = (Stage) buttonsignin.getScene().getWindow();
                      stage2.close();
                  }catch (Exception e){
                      System.out.println(e);
                  }
              }

          }else {
              msgUserError.setVisible(true);
          }
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