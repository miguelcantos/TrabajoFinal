/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DAW
 */
public class PantallaModificacionController implements Initializable {

    @FXML
    private Button salir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void abrirVentanaMateriales(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Pantalla3.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
    
        } catch (IOException e) {
        }
    }

    @FXML
    private void abrirVentanaPedidos(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Pantalla4.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
    
        } catch (IOException e) {
        }
    }

    @FXML
    private void abrirVentanaClientes(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Pantalla2.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
    
        } catch (IOException e) {
        }
    }

    @FXML
    private void abrirVentanaEmpleados(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Pantalla1.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
    
        } catch (IOException e) {
        }
    }
    
}
