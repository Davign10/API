package com.example.emprego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class candidatoControler2 {

    @FXML
    private Button botaoAvancar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField campoCargo;

    @FXML
    private TextField campoCargos;

    @FXML
    private TextField campoPretensao;

    @FXML
    private TextField campoDescricao;

    @FXML
    private TextField campoEmpresa;

    @FXML
    private DatePicker campoInicio;

    @FXML
    private DatePicker campoTermino;

    @FXML
    void AvancarTela(ActionEvent event) throws FileNotFoundException {
        String empresa = campoEmpresa.getText();
        String cargo = campoCargo.getText();
        String cargos = campoCargos.getText();
        LocalDate inicioinicial = campoInicio.getValue();
        LocalDate terminoinicial = campoTermino.getValue();
        String inicio = String.valueOf(inicioinicial);
        String termino = String.valueOf(terminoinicial);
        String descricao = campoDescricao.getText();
        String pretensao = campoPretensao.getText();

        if (cargos.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("PREENCHA O CARGO DE INTERESSE!");
            alert.showAndWait();
        } else if (pretensao.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("PREENCHA A PRETENSÃO SALARIAL!");
            alert.showAndWait();
        } else {
            // Validacao pretensão salarial, mínimo 3 digitos
            String PRETENSAO_REGEX = "\\d{3,}";
            Pattern PRETENSAO_PATTERN = Pattern.compile(PRETENSAO_REGEX);
            if (PRETENSAO_PATTERN.matcher(pretensao).matches()) {
                Candidato candidato = new Candidato();
                candidato.setExpEmpresa(empresa);
                candidato.setCargo(cargo);
                candidato.setExpInicio(inicio);
                candidato.setExpTermino(termino);
                candidato.setDescricao(descricao);
                candidato.setCargoInteresse(cargo);
                candidato.setPretSalarial(pretensao);

                HelloApplication.ChangeScene("candidato3");

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("FORMATO DE PRETENSÃO SALARIAL INVÁLIDO!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void VoltarTela(ActionEvent event) {
        HelloApplication.ChangeScene("candidato");
    }

}
