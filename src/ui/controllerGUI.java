package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import model.*;

public class ControllerGUI {
	
	private Enterprise enterprise;
	
	public ControllerGUI() {
		enterprise = new Enterprise();
	}
	
	//MainPane-----------------------------------------------------------------------------------------------------------------------
	
    @FXML
    private BorderPane mainPane;

    @FXML
    void openDataPane(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("pane-sending-data.fxml"));
		loader.setController(this);
		Parent parent = loader.load();
		mainPane.setCenter(parent);
    }

    @FXML
    void openGraphPane(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("function-pane.fxml"));
		loader.setController(this);
		Parent parent = loader.load();
		mainPane.setCenter(parent);
    }

    @FXML
    void openSuggestionsPane(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("pane-suggestions.fxml"));
		loader.setController(this);
		Parent parent = loader.load();
		mainPane.setCenter(parent);
    }
    
    
    //FunctionsPane-----------------------------------------------------------------------------------------------------------------------
    
    @FXML
    private BorderPane functionPane;

    @FXML
    private Label labFunction;

    @FXML
    void openPaneToAdd(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("pane-to-add.fxml"));
		loader.setController(this);
		Parent parent = loader.load();
		functionPane.setCenter(parent);
		
		adyacents = new ArrayList<>();
		pings = new ArrayList<>();
    }

    @FXML
    void openPaneToSearch(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("pane-to-search.fxml"));
		loader.setController(this);
		Parent parent = loader.load();
		functionPane.setCenter(parent);
    }
    
    //Pane to add-----------------------------------------------------------------------------------------------------------------------
    
    @FXML
    private TextField txtOffice;

    @FXML
    private TextField txtFloor;

    @FXML
    private TextField txtSerialDevice;

    @FXML
    private TableView<Computer> tableAdyacents;

    @FXML
    private TableColumn<String, Computer> columnSerials2;

    @FXML
    private TableColumn<Double, Computer> columnPing;

    @FXML
    private TextField txtSerialAdyacent;

    @FXML
    private TextField txtPing;

    @FXML
    private TableView<Computer> tableAllDevices1;

    @FXML
    private TableColumn<String, Computer> columnSerials1;
    
    private ArrayList<String> adyacents;
    private ArrayList<Double> pings;

    @FXML
    void addAdyacent(ActionEvent event) {
    	try {
    		
    		String serialNumber = txtSerialAdyacent.getText();
    		double ping = Double.parseDouble(txtPing.getText());
    		
    		if(enterprise.searchComputer(serialNumber)==null) {
    			throw new Exception("This computer does not exist: "+serialNumber);
    		}
    		adyacents.add(serialNumber);
    		pings.add(ping);
    		
    	}catch(NumberFormatException e) {
    		txtPing.setText("");
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setContentText("Please, type the ping in milliseconds");
    		alert.showAndWait();
    	} catch (Exception e) {
    		txtSerialAdyacent.getText();
    		txtPing.setText("");
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setContentText(e.getMessage());
    		alert.showAndWait();
		}
    }

    @FXML
    void cancelAdd(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("function-pane.fxml"));
		loader.setController(this);
		Parent parent = loader.load();
		mainPane.setCenter(parent);
    }

    @FXML
    void confirmAdd(ActionEvent event) {
    	try {
    		
    		String office = txtOffice.getText();
    		int floor = Integer.parseInt(txtFloor.getText());
    		String serialNumber = txtSerialDevice.getText();
    	
    		
    		enterprise.addComputer(serialNumber, office, floor);
    		
    		for(int i=0; i<adyacents.size(); i++) {
    			enterprise.addConection(serialNumber, adyacents.get(i), pings.get(i));
    		}
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setContentText("Computer "+serialNumber+ " was added correctly");
    		alert.showAndWait();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("function-pane.fxml"));
    		loader.setController(this);
    		Parent parent = loader.load();
    		mainPane.setCenter(parent);
    		
    	}catch(Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setContentText(e.getMessage());
    		alert.showAndWait();
    	}
    }
    
    //Pane to search---------------------------------------------------------------------------------------------------------------------
    
    @FXML
    private TableView<?> tableAllDevices2;

    @FXML
    private TableColumn<?, ?> columnSerials3;

    @FXML
    private TableColumn<?, ?> columnOffice;

    @FXML
    private TableColumn<?, ?> columnFloor;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<?> tableAllDevices21;

    @FXML
    private TableColumn<?, ?> columnSerials31;

    @FXML
    private TableColumn<?, ?> columnFloor1;

    @FXML
    private Label labSerial;

    @FXML
    private Label labOffice;

    @FXML
    private Label labFloor;

    @FXML
    void removeDevice(ActionEvent event) {

    }
    
    //Sending Data
    
    @FXML
    private TextField txtSerialDeviceA;

    @FXML
    private TextField txtSerialDeviceB;

    @FXML
    private Label labOfficeA;

    @FXML
    private Label labFloorA;

    @FXML
    private Label labOfficeB;

    @FXML
    private Label labFloorB;

    @FXML
    private TextArea txtAreaRoute;

    @FXML
    private TextField txtMinimunPing;
    
    @FXML
    private RadioButton rdbGraphA;

    @FXML
    private ToggleGroup graph;

    @FXML
    private RadioButton rdbGraphB;

    @FXML
    void calculateMinimunLatency(ActionEvent event) {

    }
}