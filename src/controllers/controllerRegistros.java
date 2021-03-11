package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import persistencia.DuenoDAO;

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
   public void guardarDueno()
   {

      if(txtnombre.getText().length()>0 && txtdireccion.getText().length()>0 && txttelefono.getText().length()>0 && txtcorreo.getText().length()>0)
      {
            DuenoDAO nuevo = new DuenoDAO();
            nuevo.agregarDueno(txtnombre.getText(), txtdireccion.getText() , txttelefono.getText(), txtcorreo.getText());
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("REGISTRO DE DUEÑO");
            mensaje.setHeaderText("Registro Exitoso :)");
            mensaje.show();
            limpiar();
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
   void limpiar()
   {
      txtnombre.setText(null);
      txtcorreo.setText(null);
      txttelefono.setText(null);
      txtdireccion.setText(null);
   }
}