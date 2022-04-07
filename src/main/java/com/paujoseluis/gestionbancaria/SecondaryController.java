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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
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
    private Spinner<?> cantidadIngresar;
    @FXML
    private ChoiceBox<?> motivoIngreso;
    @FXML
    private Button botonRetirar;
    @FXML
    private Spinner<?> cantidadRetirar;
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
        cuenta.ingresar(150000);
        cuenta.sacar(200);
        mostrarInfo();
    }

    private void mostrarInfo() {
        numCuenta.setText(cuenta.getNumCuenta() + "");
        titular.setText(cuenta.getTitular() + "");
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
    }

    @FXML
    private void retirar(ActionEvent event) {
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

}
