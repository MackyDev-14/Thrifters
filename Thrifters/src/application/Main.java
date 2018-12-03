package application;
	
import java.sql.Connection;
import java.sql.ResultSet;

import db.Util.databaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	String fxml, newUser;
	String stat, stat2;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Connection con = databaseConnection.getConnection();
			
			ResultSet res1 = con.createStatement().executeQuery(
					"Select status From userTb where id = 1");
			while(res1.next()) {
				stat = res1.getString(1);
			}
			
			ResultSet res = con.createStatement().executeQuery(
					"Select status2 From userTb where id = 1");
			while(res.next()) {
				stat2 = res.getString(1);
			}
			
			
				if(stat == null && stat2 == null) {
					fxml = "newUser.fxml";
				} else if(stat == null && stat2 != null){
					fxml = "LogIn.fxml";
				}else {
					fxml ="Thrift_one.fxml";
				}
		
			
			Parent root = FXMLLoader.load(getClass().getResource(fxml));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
