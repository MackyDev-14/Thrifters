package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import db.Util.databaseConnection;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ThriftersController implements Initializable{
	
	@FXML
	Label crntSav, nException, nException1;
	
	@FXML
	JFXTextField depoTf,wDrawTf;
	
	double inValue;
	double crntValue;

	FadeTransition fadeOut = new FadeTransition(Duration.millis(2000));
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a");
	
	
	public void depo(ActionEvent e) throws SQLException{
		try {
		inValue = Double.parseDouble(depoTf.getText());
		crntValue = Double.parseDouble(crntSav.getText())  + inValue;
		databaseConnection.accDb(LocalDate.now().toString(), LocalTime.now().format(dtf).toString(), inValue, crntValue, "Deposited");
		crntSav.setText(Double.toString(crntValue));
		depoTf.setText("");		
		
		}catch(NumberFormatException nE) {
			
			depoTf.setText("");
			fadeOut.setNode(nException);
			nException.setVisible(true);
			fadeOut.play();
			
		}
	}
	
	public void wDraw(ActionEvent e) throws SQLException{
		try {
			inValue = Double.parseDouble(wDrawTf.getText());
			crntValue = Double.parseDouble(crntSav.getText()) - inValue ;
			
			if(crntValue < 0) {
				wDrawTf.setText("");
				fadeOut.setNode(nException1);
				nException1.setVisible(true);
				fadeOut.play();
			}else {
				databaseConnection.accDb(LocalDate.now().toString(), LocalTime.now().format(dtf).toString(), inValue, crntValue, "Withdrawn");
				crntSav.setText(Double.toString(crntValue));
				wDrawTf.setText(""); }
			
			}catch(NumberFormatException nE) {	
				fadeOut.setNode(nException);
				nException.setVisible(true);
				fadeOut.play();
			}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.setCycleCount(1);
		fadeOut.setAutoReverse(false);
		try {
			if(databaseConnection.setCrntSav() == null) crntSav.setText("0.00");
			else
				try {
					crntSav.setText(databaseConnection.setCrntSav());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
