package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import db.Util.databaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Login_Controller implements Initializable{

	@FXML
	private JFXTextField loginUser_tf;
	@FXML
	private JFXPasswordField loginPass_tf;
	
	@FXML
	private Label warningLogin;
	
	String un,ps;
	ActionEvent ae;
	public void loginUser_btn(ActionEvent e) throws IOException, SQLException {
		un = loginUser_tf.getText();
		ps = loginPass_tf.getText();
		if(un.equals("") || ps.equals("")) {
			warningLogin.setText("Please fill up the fields correctly!");
			warningLogin.setVisible(true);
		}else {
			
			if(un.equals(databaseConnection.welcomeUser()) && ps.equals(databaseConnection.loginpass())){	
				databaseConnection.logIn();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Thrift_one.fxml"));
				Parent root = (Parent) loader.load();	
				Scene scene = new Scene(root);	
				Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();				
				stage.setScene(scene);
				stage.show();
			} else {
				warningLogin.setText("Wrong username or password!");
				warningLogin.setVisible(true);
			}
			
		}
	}

	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}


	

	
	
	
	
	
}
