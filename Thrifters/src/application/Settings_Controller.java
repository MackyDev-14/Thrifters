package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Util.databaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;




public class Settings_Controller {
	
	@FXML
	Button reset_btn;
	
	
	Connection con = databaseConnection.getConnection();
	
	
	/////
	public void reset(ActionEvent e) throws SQLException {
	
		String sql = "Delete From accHistory";
		String sql2 = "Delete From userTb";
		String sql3= "Delete from sqlite_sequence where name = 'userTb'";
		
		PreparedStatement state = con.prepareStatement(sql);
		state.executeUpdate();
		
		PreparedStatement state2 = con.prepareStatement(sql2);
		state2.executeUpdate();
		
		PreparedStatement state3 = con.prepareStatement(sql3);
		state3.executeUpdate();
		
		Stage currentStage = (Stage) reset_btn.getScene().getWindow();
		currentStage.close();
		
	}
	
	
}
