package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import db.Util.databaseConnection;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewUser_Controller implements Initializable, EventHandler<KeyEvent>{
	
	@FXML
    private JFXTextField newUser_Tf;

    @FXML
    private JFXPasswordField newPs_Tf;

    @FXML
    private JFXPasswordField rtPs_Tf;
    
    @FXML
    private Label fieldErr, fieldErr2, fieldErr3;

    Thread t1 = new Thread();
    String newUsername, newPassword;
    
    FadeTransition fadeOut = new FadeTransition(Duration.millis(2500));
    FadeTransition fadeOut2 = new FadeTransition(Duration.millis(2500));
    
	public void newUser_btn(ActionEvent e) throws SQLException, IOException {
		
		newUsername = newUser_Tf.getText();
		newPassword = newPs_Tf.getText() ;
		
		if(newUsername.equals("")) {
			fadeOut.setNode(fieldErr);
			fieldErr.setVisible(true);
				fadeOut.play();
		} else if(newUsername.length() <= 5) {
			fieldErr.setText("Username must have at least 6 characters!");
			fadeOut.setNode(fieldErr);
			fieldErr.setVisible(true);
				fadeOut.play();
		} else if(newUsername.length() > 19) {
			fieldErr.setText("Character limit reached!");
			fadeOut.setNode(fieldErr);
			fieldErr.setVisible(true);
				fadeOut.play();
		} else {
			
			if(newPassword.equals("")) {
				fadeOut2.setNode(fieldErr2);
				fieldErr2.setVisible(true);
				fadeOut2.play();	
			} else if(newPassword.length() < 8) {
				fieldErr2.setText("Password must have 8 characters!");
				fadeOut2.setNode(fieldErr2);
				fieldErr2.setVisible(true);
				fadeOut2.play();
			} else if(newPassword.length() > 19) {
				fieldErr2.setText("Character limit reached!");
				fadeOut2.setNode(fieldErr2);
				fieldErr2.setVisible(true);
				fadeOut2.play();
			} else if(rtPs_Tf.getText().equals(newPassword)) {
				
				databaseConnection.usTb(newUsername, newPassword, "online");
				databaseConnection.status2();
				Stage currentStage = (Stage) fieldErr.getScene().getWindow();
				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("Thrift_one.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				currentStage.close();
				stage.show();
				System.out.println("Logging in . . .");
			} else {
				fieldErr3.setText("Password not matched!");
				fadeOut2.setNode(fieldErr3);
				fieldErr3.setVisible(true);
				fadeOut2.play();
				System.out.println("mismatch");
			}
			
			
		}

		if(newPassword.equals("")) {
			fadeOut2.setNode(fieldErr2);
			fieldErr2.setVisible(true);
			fadeOut2.play();	
		} else if(newPassword.length() < 8) {
			fieldErr2.setText("Password must have 8 characters!");
			fadeOut2.setNode(fieldErr2);
			fieldErr2.setVisible(true);
			fadeOut2.play();
		}
		
	}

	@Override
	public void handle(KeyEvent e){
		if(e.getCode().toString() == "ENTER"){

			newUsername = newUser_Tf.getText();
			newPassword = newPs_Tf.getText() ;
			
			if(newUsername.equals("")) {
				fadeOut.setNode(fieldErr);
				fieldErr.setVisible(true);
					fadeOut.play();
			} else if(newUsername.length() <= 5) {
				fieldErr.setText("Username must have at least 6 characters!");
				fadeOut.setNode(fieldErr);
				fieldErr.setVisible(true);
					fadeOut.play();
			} else if(newUsername.length() > 19) {
				fieldErr.setText("Character limit reached!");
				fadeOut.setNode(fieldErr);
				fieldErr.setVisible(true);
					fadeOut.play();
			} else {
				
				if(newPassword.equals("")) {
					fadeOut2.setNode(fieldErr2);
					fieldErr2.setVisible(true);
					fadeOut2.play();	
				} else if(newPassword.length() < 8) {
					fieldErr2.setText("Password must have 8 characters!");
					fadeOut2.setNode(fieldErr2);
					fieldErr2.setVisible(true);
					fadeOut2.play();
				} else if(newPassword.length() > 19) {
					fieldErr2.setText("Character limit reached!");
					fadeOut2.setNode(fieldErr2);
					fieldErr2.setVisible(true);
					fadeOut2.play();
				} else if(rtPs_Tf.getText().equals(newPassword)) {
					
					try {
						databaseConnection.usTb(newUsername, newPassword, "online");
						databaseConnection.status2();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Stage currentStage = (Stage) fieldErr.getScene().getWindow();
					Stage stage = new Stage();
					Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("Thrift_one.fxml"));
						Scene scene = new Scene(root);
						stage.setScene(scene);
						currentStage.close();
						stage.show();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					System.out.println("Logging in . . .");
				} else {
					fieldErr3.setText("Password not matched!");
					fadeOut2.setNode(fieldErr3);
					fieldErr3.setVisible(true);
					fadeOut2.play();
					System.out.println("mismatch");
				}
				
				
			}

			if(newPassword.equals("")) {
				fadeOut2.setNode(fieldErr2);
				fieldErr2.setVisible(true);
				fadeOut2.play();	
			} else if(newPassword.length() < 8) {
				fieldErr2.setText("Password must have 8 characters!");
				fadeOut2.setNode(fieldErr2);
				fieldErr2.setVisible(true);
				fadeOut2.play();
			}
		}
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.setCycleCount(1);
		fadeOut.setAutoReverse(false);
		
		fadeOut2.setFromValue(1.0);
		fadeOut2.setToValue(0.0);
		fadeOut2.setCycleCount(1);
		fadeOut2.setAutoReverse(false);
	}


	

	
}
