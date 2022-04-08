package com.paujoseluis.gestionbancaria;

import com.paujoseluis.gestionbancaria.Modelo.CuentaBancaria;
import com.paujoseluis.gestionbancaria.Modelo.Persona;
import com.paujoseluis.gestionbancaria.Modelo.Recibo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class SecondaryController implements Initializable {

    private CuentaBancaria cuenta;

    @FXML
    private Label saldo;
    @FXML
    private Label numCuenta;
    @FXML
    private Label titular;
    @FXML
    private Button botonIngresar;
    @FXML
    private Spinner<Integer> cantidadIngresar;
    @FXML
    private ChoiceBox<String> motivoIngreso;
    @FXML
    private Button botonRetirar;
    @FXML
    private Spinner<Integer> cantidadRetirar;
    @FXML
    private ChoiceBox<?> motivoRetirada;
    @FXML
    private ProgressBar donacionONG;
    @FXML
    private ListView<?> listaAutorizados;
    @FXML
    private ProgressIndicator totalAutorizados;
    @FXML
    private TextField nombreAutorizado;
    @FXML
    private TextField nifAutorizado;
    @FXML
    private Button botonAutorizar;
    @FXML
    private TextField nifAutorizadoEliminar;
    @FXML
    private Button botonEliminar;
    @FXML
    private TextField cif;
    @FXML
    private TextField nombreEmpresa;
    @FXML
    private TextField conceptoRecibo;
    @FXML
    private Spinner<?> importeRecibo;
    @FXML
    private RadioButton rb_menusal;
    @FXML
    private ToggleGroup periodicidad;
    @FXML
    private RadioButton rb_trimestral;
    @FXML
    private RadioButton rb_anual;
    @FXML
    private Button botonDomiciliar;
    @FXML
    private ListView<Recibo> listaRecibos;
    @FXML
    private Button botonFiltrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Persona titular = new Persona("123456789A", "Jose Luis Coloma");
        cuenta = new CuentaBancaria(123456789, titular);
        mostrarInfo();
        cargarIngresar();
        cargarRetirar();
    }

    private void mostrarInfo() {
        numCuenta.setText(cuenta.getNumCuenta() + "");
        titular.setText(cuenta.getTitular() + "");
        mostrarSaldo();
    }

    private void mostrarSaldo() {
        saldo.setText(cuenta.getSaldoFormateado() + " €");
        if (cuenta.getSaldo() < 0) {
            saldo.setStyle("-fx-text-fill: red;");
        } else if (cuenta.getSaldo() > 0) {
            saldo.setStyle("-fx-text-fill: LimeGreen;");
        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void ingresar(ActionEvent event) {
        double cantidad = (double) (cantidadIngresar.getValue());
        switch (cuenta.ingresar(cantidad)) {
            case 0:
                mostrarSaldo();
                break;
            case -1:
                alertaOperacionNoRealizada(event);
                break;
            case 1:
                warningAvisoHacienda(event);
                mostrarSaldo();
                break;
        }
    }

    @FXML
    private void retirar(ActionEvent event) {
        double cantidad = (double) (cantidadRetirar.getValue());
        
        if (cuenta.getSaldo() == cuenta.sacar(cantidad)){
            alertaOperacionNoRealizada(event);
        } else if (cuenta.getSaldo() > cuenta.sacar(cantidad)){
            if(cuenta.getSaldo() < 0){
                warningAvisoSaldoNegatico(event);
            }
            mostrarSaldo();
        }
    }

    @FXML
    private void autorizar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void domiciliar(ActionEvent event) {
    }

    @FXML
    private void filtrarPeriodicidad(ActionEvent event) {
    }

    @FXML
    private void cambioCantidad(MouseEvent event) {
    }

    private void alertaOperacionNoRealizada(ActionEvent event) {
        Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
        dialogoAlerta.setTitle("ERROR");
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.setContentText("Operacion no realizada");
        dialogoAlerta.showAndWait();
    }

    private void warningAvisoHacienda(ActionEvent event) {
        Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
        dialogoAlerta.setTitle("Peligro Aviso Hacienda");
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.setContentText("AVISO: NOTIFICAR A HACIENDA por ingresar " + cantidadIngresar.getValue() + " €");
        dialogoAlerta.showAndWait();
    }
    
    private void warningAvisoSaldoNegatico(ActionEvent event) {
        Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
        dialogoAlerta.setTitle("Peligro Saldo Negativo");
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.setContentText("AVISO: SALDO NEGATIVO");
        dialogoAlerta.showAndWait();
    }

    private void cargarIngresar() {
        SpinnerValueFactory.IntegerSpinnerValueFactory ingreso = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, 0, 10);
        cantidadIngresar.setValueFactory(ingreso);

        motivoIngreso.getItems().add("Nómina");
        motivoIngreso.getItems().add("Regalo");
        motivoIngreso.getItems().add("Donación");
        motivoIngreso.getItems().add("Otros");
    }

    private void cargarRetirar() {
        SpinnerValueFactory.IntegerSpinnerValueFactory retirada = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, 0, 10);
        cantidadRetirar.setValueFactory(retirada);

        motivoIngreso.getItems().add("Nómina");
        motivoIngreso.getItems().add("Regalo");
        motivoIngreso.getItems().add("Donación");
        motivoIngreso.getItems().add("Otros");
    }

}
