package com.example.emprego;

import AcessoDAO.CandidatoDAO;
import AcessoDAO.FuncionarioDAO;
import AcessoDAO.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class loginControler implements Initializable {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoEntrar;

    @FXML
    private ToggleButton botaoSair;

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private TextField campoSenha2;

    @FXML
    private ToggleButton tgbtMostrarSenha;

    @FXML
    private ToggleButton tgbtModo;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView imgModo;

    public static String emailrh;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Tema tema = new Tema();
        if(tema.getLuz()==false){
            Image image = new Image("file:src/main/resources/imagens/Lua.png",25,25,true,true);
            anchor.getStylesheets().add("file:src/main/resources/styles/ModoEscuro.css");
            imgModo.setImage(image);
        }else{
            Image image = new Image("file:src/main/resources/imagens/Sol.png",25,25,true,true);
            anchor.getStylesheets().add("file:src/main/resources/styles/ModoClaro.css");
            imgModo.setImage(image);
        }
    }

    @FXML
    void cadastrarLogin(ActionEvent event) throws Exception {
        HelloApplication.ChangeScene("candidato");
    }

    @FXML
    void entrarLogin(ActionEvent event) throws Exception{

        try {

            Usuario emf = new Usuario();
            Usuario.setEmail(campoEmail.getText());


            //validação de campos em branco
            String senhaMd5 = Md5.getHashMd5(campoSenha.getText());

            if (campoEmail.getText() == "" || campoSenha.getText() == ""){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("campo vazio !");
                alert.showAndWait();

            } else {

                // conexão com banco

                //busca candidato
                CandidatoDAO dao = new CandidatoDAO();
                if( dao.checklogin(campoEmail.getText(), senhaMd5)){
                    Usuario usuario = new Usuario();
                    UsuarioDAO.Usua(campoEmail.getText());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Bem Vindo!");
                    alert.showAndWait();
                    HelloApplication.ChangeScene("home");

                } else{

                    // busca funcionario
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    if( funcionarioDAO.checklogin(campoEmail.getText(), senhaMd5)) {
                        funcionarioDAO.Usua(campoEmail.getText());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Bem Vindo Funcionario!");
                        alert.showAndWait();
                        HelloApplication.ChangeScene("homerh");
                    }else{

                        // USUARIO NÃO ENCONTRADO
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("E-mail ou Senha incorretos !");
                        alert.showAndWait();

                    }
                }
            }

        }catch (Exception e){

            // USUARIO NÃO ENCONTRADO
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("FALHA NA CONEXÃO");
            alert.showAndWait();

        }


    }

    //imagem ação
    @FXML
    Image img_MostrarSenha = new Image("file:src/main/resources/imagens/iconMostrar.png",25,25,true,true);
    Image img_OcultarSenha = new Image("file:src/main/resources/imagens/iconApagar.png",25,25,true,true);


    //ocultar/mostrar senha
    @FXML
    void mostrarSenha(ActionEvent event){
        if(tgbtMostrarSenha.isSelected()){
            tgbtMostrarSenha.setGraphic(new ImageView(img_MostrarSenha));
            campoSenha2.setText(campoSenha.getText());
            campoSenha2.setVisible(true);
            campoSenha.setVisible(false);
        }else{
            tgbtMostrarSenha.setGraphic(new ImageView(img_OcultarSenha));
            campoSenha.setText(campoSenha2.getText());
            campoSenha.setVisible(true);
            campoSenha2.setVisible(false);
        }
    }

    public void alterarModo(ActionEvent event) {
        Tema tema = new Tema();
        if (tema.getLuz() == false) {
            Image image = new Image("file:src/main/resources/imagens/Sol.png", 25, 25, true, true);
            anchor.getStylesheets().add("file:src/main/resources/styles/ModoClaro.css");
            anchor.getStylesheets().remove("file:src/main/resources/styles/ModoEscuro.css");
            imgModo.setImage(image);
            tema.setLuz(true);
        } else {
            Image image = new Image("file:src/main/resources/imagens/Lua.png", 25, 25, true, true);
            anchor.getStylesheets().add("file:src/main/resources/styles/ModoEscuro.css");
            anchor.getStylesheets().remove("file:src/main/resources/styles/ModoClaro.css");
            imgModo.setImage(image);
            tema.setLuz(false);
        }
    }
}



