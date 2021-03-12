package controllers;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import persistencia.*;

public class controllerRegistros
{
   @FXML
   private TextField txtnombre;

   @FXML
   private TextField txtdireccion;

   @FXML
   private TextField txttelefono;

   @FXML
   private TextField txtcorreo;

   @FXML
   private Button btnguardar;
   @FXML
   private TextField nombreMascota;

   @FXML
   private TextField especieMascota;

   @FXML
   private TextField razaMascota;

   @FXML
   private RadioButton checkH;

   @FXML
   private RadioButton checkM;
   @FXML
   private AnchorPane cardRegistroMascota;
   @FXML
   private AnchorPane cardRegistroDueno;
    @FXML
    private Button botonRegistroDueno;
    controllerHome ch;
    controllerRegistros cr;

   boolean validarIrACita =false;
   String sexo="";
   TranslateTransition translateTransitioncard2 = new TranslateTransition();
   TranslateTransition translateTransitioncard1 = new TranslateTransition();
    Dueno duenoGuardar;
    Mascota mascotaGuardar;
    TipoMascota tipoMascota;
   @FXML
   private void initialize(){
        cr=this;
       checkM.setSelected(true);
       checkH.setOnAction(event -> {
           checkM.setSelected(false);
       });
       checkM.setOnAction(actionEvent -> {
           checkH.setSelected(false);
       });
   }
   @FXML
   public void guardarDueno()
   {

      if(txtnombre.getText().length()>0 && txtdireccion.getText().length()>0 && txttelefono.getText().length()>0 && txtcorreo.getText().length()>0)
      {
          duenoGuardar= new Dueno(txtnombre.getText(),txtdireccion.getText(),txttelefono.getText(),txtcorreo.getText());
            //DuenoDAO nuevo = new DuenoDAO();
           // nuevo.agregarDueno(txtnombre.getText(), txtdireccion.getText() , txttelefono.getText(), txtcorreo.getText());
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("REGISTRO DE DUEÑO");
            mensaje.setHeaderText("Registro Exitoso  Registre su mascota en la sigueinte Tarjeta:)");
            mensaje.show();
            limpiar();
            botonRegistroDueno.setDisable(true);
            cardRegistroMascota.setVisible(true);
            translateTransitioncard2.setDuration(Duration.millis(1000));
            translateTransitioncard2.setNode(cardRegistroDueno);
            translateTransitioncard2.setByX(177);
            translateTransitioncard1.setDuration(Duration.millis(1000));
            translateTransitioncard1.setNode(cardRegistroMascota);
            translateTransitioncard1.setByX(cardRegistroMascota.getLayoutX()+300);
           translateTransitioncard1.setCycleCount(1);
           translateTransitioncard2.setCycleCount(1);
           translateTransitioncard2.play();
           translateTransitioncard1.play();
           cardRegistroDueno.setVisible(false);
      }
      else
      {
         Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
         mensaje.setTitle("ERROR DE REGISTRO");
         mensaje.setHeaderText("Debe llenar todos los campos*");
         mensaje.show();
      }
   }

   @FXML
   public void guardarMascota(){
      if (checkH.isSelected()){
          checkM.setSelected(false);
         sexo = checkH.getText();
          System.out.println(sexo);
      }else if (checkM.isSelected()){
          checkH.setSelected(false);
         sexo = checkM.getText();
          System.out.println(sexo);
      }
      if(sexo.length()>0 && nombreMascota.getText().length()>0 && especieMascota.getText().length()>0 && razaMascota.getText().length()>0){
          //TipoMascotaDAO tipoMascotaDAO = new TipoMascotaDAO();
          tipoMascota = new TipoMascota(especieMascota.getText(),sexo,razaMascota.getText());
         // tipoMascotaDAO.crearTipo(tipoMascota);
          mascotaGuardar = new Mascota(duenoGuardar,tipoMascota,nombreMascota.getText());
      }
   }
   @FXML
   void limpiar()
   {
      txtnombre.setText(null);
      txtcorreo.setText(null);
      txttelefono.setText(null);
      txtdireccion.setText(null);
   }
    @FXML
    public void generarCita(){
        System.out.println(duenoGuardar.getNombre()+" "+mascotaGuardar.getNombre()+" "+tipoMascota.getSexo());
        try {

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GenerarVistaUsuarioNuevo.fxml"));
            Parent root = (Parent)loader.load();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
           controllerGenerarCitaUsuariosNuevos controlador = (controllerGenerarCitaUsuariosNuevos) loader.getController();
          controlador.parametros(duenoGuardar,mascotaGuardar,tipoMascota,this);

            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }

}


//Trabajó este controller Hernández Alvarado Kevin