package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db.Util.databaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountController implements Initializable{

	@FXML
	TableView<modelTable> accTable;
	
	@FXML
	TableColumn<modelTable, String> dateCol, timeCol, amntCol, shCol, actCol;

	ObservableList<modelTable> oblist = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Connection con = databaseConnection.getConnection();
		
		try {
			ResultSet res = con.createStatement().executeQuery("Select * From accHistory");
			while(res.next()) {
				oblist.add(new modelTable(res.getString("date"), res.getString("time"), Double.toString(res.getDouble("amount")),
						Double.toString(res.getDouble("rSaving")), res.getString("sos")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dateCol.setCellValueFactory(new PropertyValueFactory<>("dt"));
		timeCol.setCellValueFactory(new PropertyValueFactory<>("tm"));
		amntCol.setCellValueFactory(new PropertyValueFactory<>("am"));
		shCol.setCellValueFactory(new PropertyValueFactory<>("sh"));
		actCol.setCellValueFactory(new PropertyValueFactory<>("act"));
		
		accTable.setItems(oblist);
		
	}
	
	
}
