/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import DAO.Conexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DAW
 */
public class Pantalla2Controller implements Initializable {

    int eleccion = 0;
    @FXML
    private TextField lIdCliente;
    @FXML
    private TextField lNombre;
    @FXML
    private TextField lApellido;
    @FXML
    private TextField lDireccion;
    @FXML
    private TextField lCiudad;
    @FXML
    private TextField lTelefono;
    @FXML
    private TextField lEmail;
    @FXML
    private Button bModificar;
    @FXML
    private Button bEliminar;
    @FXML
    private Button bCrearNuevo;
    @FXML
    private Button bGuardar;
    @FXML
    private Button bcerrar;
    @FXML
    private TableView<Cliente> tableView;
    @FXML
    private TableColumn<Cliente, Integer> tID;
    @FXML
    private TableColumn<Cliente, String> tNombre;
    @FXML
    private TableColumn<Cliente, String> tApellido;
    @FXML
    private TableColumn<Cliente, String> tDireccion;
    @FXML
    private TableColumn<Cliente, String> tCiudad;
    @FXML
    private TableColumn<Cliente, String> tTelefono;
    @FXML
    private TableColumn<Cliente, String> tEmail;

    ObservableList<Cliente> lista = FXCollections.observableArrayList();

    private final ListChangeListener<Cliente> selectorTablaCliente = new ListChangeListener<Cliente>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Cliente> c) {
            ponerClienteSeleccionado();
        }
    };

    public Cliente getTablaClienteSeleccionado() {
        if (tableView != null) {
            List<Cliente> tabla = tableView.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Cliente clienteSeleccionado = tabla.get(0);
                return clienteSeleccionado;
            }
        }
        return null;
    }

    public void ponerClienteSeleccionado() {
        bGuardar.setVisible(false);
        final Cliente cliente = getTablaClienteSeleccionado();
        int posicionCliente = lista.indexOf(cliente);
        this.noEditable();
        if (cliente != null) {
            lIdCliente.setText(Integer.toString(cliente.getIdCliente()));
            lNombre.setText(cliente.getNombre());
            lApellido.setText(cliente.getApellido());
            lDireccion.setText(cliente.getDireccion());
            lCiudad.setText(cliente.getCiudad());
            lTelefono.setText(cliente.getTelefono());
            lEmail.setText(cliente.getEmail());

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rellenarTableView();

        final ObservableList<Cliente> tablaClienteSel = tableView.getSelectionModel().getSelectedItems();
        tablaClienteSel.addListener(selectorTablaCliente);

        this.noEditable();
        todoVacio();
        bGuardar.setVisible(false);
        lIdCliente.setEditable(false);

    }

    private void todoVacio() {
        lIdCliente.setText(null);
        lNombre.setText(null);
        lApellido.setText(null);
        lDireccion.setText(null);
        lCiudad.setText(null);
        lTelefono.setText(null);
        lEmail.setText(null);
    }

    private void noEditable() {

        lNombre.setEditable(false);
        lApellido.setEditable(false);
        lDireccion.setEditable(false);
        lCiudad.setEditable(false);
        lTelefono.setEditable(false);
        lEmail.setEditable(false);
    }

    private void rellenarTableView() {
        tableView.getItems().clear();
        Cliente.llenarClientes(lista);
        tableView.setItems(lista);

        tID.setCellValueFactory(new PropertyValueFactory< Cliente, Integer>("idCliente"));
        tNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        tApellido.setCellValueFactory(new PropertyValueFactory< Cliente, String>("apellido"));
        tDireccion.setCellValueFactory(new PropertyValueFactory< Cliente, String>("direccion"));
        tCiudad.setCellValueFactory(new PropertyValueFactory< Cliente, String>("ciudad"));
        tTelefono.setCellValueFactory(new PropertyValueFactory< Cliente, String>("telefono"));
        tEmail.setCellValueFactory(new PropertyValueFactory< Cliente, String>("email"));

    }

    private void siEditable() {

        lNombre.setEditable(true);
        lApellido.setEditable(true);
        lDireccion.setEditable(true);
        lCiudad.setEditable(true);
        lTelefono.setEditable(true);
        lEmail.setEditable(true);
    }

    private boolean comprobarCampos() {
        boolean solucion = false;
        if (lNombre.getText() == null
                || lApellido.getText() == null
                || lDireccion.getText() == null
                || lCiudad.getText() == null
                || lTelefono.getText() == null
                || lEmail.getText() == null) {

            solucion = true;

        }

        return solucion;
    }

    private boolean soloNumeros(String a) {

        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(a);
        boolean resultado = m.find();

        return resultado;
    }

    private boolean soloLetras(String a) {

        Pattern p = Pattern.compile("[^A-Za-z]");
        Matcher m = p.matcher(a);
        boolean resultado = m.find();

        return resultado;
    }

    @FXML
    private void botonModificar(ActionEvent event) {
        if (comprobarCampos() == true) {
            System.out.println("ERROR");
        } else {
            siEditable();
            bGuardar.setVisible(true);
            eleccion = 1;
        }
    }

    @FXML
    private void botonEliminar(ActionEvent event) {
        if (comprobarCampos() == true) {
            System.out.println("ERROR");
        } else {
            Integer id = Integer.parseInt(lIdCliente.getText());
            PreparedStatement stmt = null;
            PreparedStatement stmt2 = null;
            PreparedStatement stmt3 = null;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar");
            alert.setHeaderText(null);
            alert.setContentText("¿Seguro que quieres eliminar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("OK");
                Conexion conexion = new Conexion();
                Connection con = conexion.conectar();
                try {
                    stmt = con.prepareStatement("DELETE from lineasPedido where lineasPedido.idPedido IN (SELECT pedido.idPedido FROM pedido Where idCliente=?)");
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                    System.out.println("Hola");
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Correcto");
                    alert1.setHeaderText(null);
                    alert1.setContentText(" Se ha eliminado el Cliente ");
                    alert1.showAndWait();
                    try {
                        stmt2 = con.prepareStatement("DELETE from pedido where pedido.idCliente=?");
                        stmt2.setInt(1, id);
                        stmt2.executeUpdate();
                        Alert alert10 = new Alert(Alert.AlertType.INFORMATION);
                        alert10.setTitle("Correcto");
                        alert10.setHeaderText(null);
                        alert10.setContentText(" Se ha eliminado el elemento ");
                        alert10.showAndWait();
                        try {
                            stmt3 = con.prepareStatement("DELETE from cliente where idCliente=?");
                            stmt3.setInt(1, id);
                            stmt3.executeUpdate();
                            Alert alert30 = new Alert(Alert.AlertType.INFORMATION);
                            alert30.setTitle("Correcto");
                            alert30.setHeaderText(null);
                            alert30.setContentText(" Se ha eliminado el elemento ");
                            alert30.showAndWait();

                        } catch (SQLException e) {
                            Alert alert40 = new Alert(Alert.AlertType.INFORMATION);
                            alert40.setTitle("ERROR");
                            alert40.setHeaderText(null);
                            alert40.setContentText(" No se ha eliminado el Cliente ");
                            alert40.showAndWait();
                        } finally {
                            try {
                                if (stmt3 != null) {
                                    stmt3.close();
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } catch (SQLException e) {
                        Alert alert20 = new Alert(Alert.AlertType.INFORMATION);
                        alert20.setTitle("ERROR");
                        alert20.setHeaderText(null);
                        alert20.setContentText(" No se ha eliminado el Cliente ");
                        alert20.showAndWait();
                    } finally {
                        try {
                            if (stmt2 != null) {
                                stmt2.close();
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } catch (SQLException e) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("ERROR");
                    alert2.setHeaderText(null);
                    alert2.setContentText(" No se ha eliminado el Cliente ");
                    alert2.showAndWait();
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        conexion.desconectar(con);
                    }
                }
            } else {
                System.out.println("CANCEL");
            }

            rellenarTableView();
            tableView.refresh();
            noEditable();
            todoVacio();
        }
    }

    @FXML
    private void botonCrear(ActionEvent event) {
        todoVacio();
        siEditable();

        eleccion = 2;

        bGuardar.setVisible(true);
        lIdCliente.setVisible(false);
    }

    @FXML
    private void botonGuardar(ActionEvent event) {
        bGuardar.setVisible(false);

        if (eleccion == 1) {
            if (comprobarCampos() == true || soloNumeros(lTelefono.getText()) == true || soloLetras(lNombre.getText()) == true || soloLetras(lApellido.getText()) == true || soloLetras(lCiudad.getText()) == true) {
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("Error");
                alert0.setHeaderText(null);
                alert0.setContentText(" Tienes campos incorrectos A");
                alert0.showAndWait();

                todoVacio();
                noEditable();
            } else {

                Integer id = Integer.parseInt(lIdCliente.getText());
                PreparedStatement stmt = null;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Estas seguro?");
                alert.setHeaderText(null);
                alert.setContentText("¿Seguro que quieres modificar este cliente?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    System.out.println("OK");
                    Conexion conexion = new Conexion();
                    Connection con = conexion.conectar();
                    try {

                        stmt = con.prepareStatement("UPDATE cliente SET nombre=?, apellido=?, direccion=?, ciudad=?, telefono=?, email=?  WHERE idCliente=?");

                        stmt.setString(1, lNombre.getText());
                        stmt.setString(2, lApellido.getText());
                        stmt.setString(3, lDireccion.getText());
                        stmt.setString(4, lCiudad.getText());
                        stmt.setString(5, lTelefono.getText());
                        stmt.setString(6, lEmail.getText());
                        stmt.setInt(7, id);
                        stmt.executeUpdate();

                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Correcto");
                        alert1.setHeaderText(null);
                        alert1.setContentText(" Se ha modificado el cliente ");
                        alert1.showAndWait();

                        rellenarTableView();
                        tableView.refresh();
                        noEditable();
                        todoVacio();

                    } catch (SQLException e) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Error");
                        alert2.setHeaderText(null);
                        alert2.setContentText(" No se ha modificado el cliente ");
                        alert2.showAndWait();
                        rellenarTableView();
                        tableView.refresh();
                        noEditable();
                        todoVacio();

                    } finally {
                        try {
                            if (stmt != null) {
                                stmt.close();
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            conexion.desconectar(con);
                        }
                    }
                } else {
                    System.out.println("CANCEL");
                    noEditable();
                    todoVacio();
                }
            }
        } else if (eleccion == 2) {
            if (comprobarCampos() == true || soloNumeros(lTelefono.getText()) == true || soloLetras(lNombre.getText()) == true || soloLetras(lApellido.getText()) == true || soloLetras(lCiudad.getText()) == true) {
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("Error");
                alert0.setHeaderText(null);
                alert0.setContentText(" Tienes campos incorrectos A ");
                alert0.showAndWait();

                todoVacio();
                noEditable();
                lIdCliente.setVisible(true);
            } else {
                PreparedStatement stmt = null;
                Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                alert3.setTitle("Estas seguro");
                alert3.setHeaderText(null);
                alert3.setContentText("¿Seguro que quieres crear este cliente?");
                Optional<ButtonType> result1 = alert3.showAndWait();
                if (result1.isPresent() && result1.get() == ButtonType.OK) {
                    System.out.println("OK");
                    Conexion conexion = new Conexion();
                    Connection con = conexion.conectar();
                    try {

                        stmt = con.prepareStatement("INSERT INTO cliente (nombre, apellido, direccion, ciudad, telefono, email) VALUES (?, ?, ?, ?, ?, ?)");

                        stmt.setString(1, lNombre.getText());
                        stmt.setString(2, lApellido.getText());
                        stmt.setString(3, lDireccion.getText());
                        stmt.setString(4, lCiudad.getText());
                        stmt.setString(5, lTelefono.getText());
                        stmt.setString(6, lEmail.getText());
                        stmt.executeUpdate();

                        Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                        alert4.setTitle("Correcto");
                        alert4.setHeaderText(null);
                        alert4.setContentText(" Se ha creado el cliente ");
                        alert4.showAndWait();

                        rellenarTableView();
                        tableView.refresh();
                        todoVacio();
                        noEditable();
                        lIdCliente.setVisible(true);

                    } catch (SQLException e) {
                        Alert alert5 = new Alert(Alert.AlertType.CONFIRMATION);
                        alert5.setTitle("ERROR");
                        alert5.setHeaderText(null);
                        alert5.setContentText("No se ha creado el cliente, ¿quierer borrar todo?");
                        Optional<ButtonType> result2 = alert5.showAndWait();
                        if (result2.isPresent() && result2.get() == ButtonType.OK) {
                            System.out.println("OK");
                            rellenarTableView();
                            tableView.refresh();
                            noEditable();
                            todoVacio();
                            lIdCliente.setVisible(true);
                        } else {
                            bGuardar.setVisible(true);
                            siEditable();
                        }

                    } finally {
                        try {
                            if (stmt != null) {
                                stmt.close();
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            conexion.desconectar(con);
                        }
                    }
                } else {
                    System.out.println("CANCEL");
                    noEditable();
                    todoVacio();
                }

            }
        }
        lIdCliente.setVisible(true);
    }

    @FXML
    private void botonCerrar(ActionEvent event) {
        Stage stage = (Stage) bcerrar.getScene().getWindow();
        stage.close();
    }

}
