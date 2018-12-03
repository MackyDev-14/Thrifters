package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db.Util.databaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;





public class ThriftOne_Controller implements Initializable{

    
    @FXML
    private AnchorPane ParentAnchor;
    
    @FXML
    private Label welcome;
    
	public void ThriftiesBank(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Thrifters.fxml"));
		Parent root = (Parent) loader.load();	
		ParentAnchor.getChildren().setAll(root);
	}

	
	public void Account(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
		Parent root = (Parent) loader.load();	
		ParentAnchor.getChildren().setAll(root);
	}
	
	public void Settings(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
		Parent root = (Parent) loader.load();	
		ParentAnchor.getChildren().setAll(root);
	}
	
	public void Logout(ActionEvent e) throws SQLException {
	 databaseConnection.logout();
	 Stage currentStage = (Stage) welcome.getScene().getWindow();
	 currentStage.close();
	 
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
						
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Thrifters.fxml"));
		Parent root = null;
		try {
			root = (Parent) loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ParentAnchor.getChildren().setAll(root);
		
		try {
			welcome.setText("Welcome " + databaseConnection.welcomeUser() + "!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
