package ui;

import java.io.IOException;
import java.util.ArrayList;

import datastructure.Edge;
import datastructure.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
		initializeTable1();
    }

    @FXML
    void openPaneToSearch(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("pane-to-search.fxml"));
		loader.setController(this);
		Parent parent = loader.load();
		functionPane.setCenter(parent);
		enterprise.deselectComputer();
		initializeTableComputers();
    }
    
    //Pane to add-----------------------------------------------------------------------------------------------------------------------
    
    @FXML
    private TextField txtOffice;

    @FXML
    private TextField txtFloor;

    @FXML
    private TextField txtSerialDevice;

    @FXML
    private TableView<Edge> tableAdyacents;

    @FXML
    private TableColumn<String, Edge> columnSerials2;
    
    @FXML
    private TableColumn<Double, Edge> columnPing;

    @FXML
    private TextField txtSerialAdyacent;

    @FXML
    private TextField txtPing;

    @FXML
    private TableView<Computer> tableAllDevices1;

    @FXML
    private TableColumn<String, Computer> columnSerials1;
    
    private ArrayList<Edge> adyacents;

    @FXML
    void addAdyacent(ActionEvent event) {
    	try {
    		
    		String serialNumber = txtSerialAdyacent.getText();
    		double ping = Double.parseDouble(txtPing.getText());
    		
    		if(enterprise.searchComputer(serialNumber)==null) {
    			throw new Exception("This computer does not exist: "+serialNumber);
    		}
    		
    		adyacents.add(new Edge(0, 0, ping, serialNumber));
    		
    		refreshTableAdyacents();
    		
    		txtSerialAdyacent.setText("");
    		txtPing.setText("");
    	}catch(NumberFormatException e) {
    		txtPing.setText("");
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setContentText("Please, type the ping in milliseconds");
    		alert.showAndWait();
    	} catch (Exception e) {
    		txtSerialAdyacent.setText("");
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
    			enterprise.addConection(serialNumber, adyacents.get(i).getIndexS(), adyacents.get(i).getWeight());
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
    
    public void initializeTable1() {
    	ObservableList<Computer> computers = FXCollections.observableArrayList(enterprise.getComputersList());
    	tableAllDevices1.setItems(computers);
    	columnSerials1.setCellValueFactory(new PropertyValueFactory<String,Computer>("serialNumber"));
    }
    
    public void refreshTableAdyacents() {
    	ObservableList<Edge> edges = FXCollections.observableArrayList(adyacents);
    	tableAdyacents.setItems(edges);
    	columnSerials2.setCellValueFactory(new PropertyValueFactory<String,Edge>("indexS"));
    	columnPing.setCellValueFactory(new PropertyValueFactory<Double,Edge>("weight"));
    }
    
    //Pane to search---------------------------------------------------------------------------------------------------------------------
    
    @FXML
    private TableView<Computer> tableAllDevices2;

    @FXML
    private TableColumn<String, Computer> columnSerials3;

    @FXML
    private TableColumn<String, Computer> columnOffice;

    @FXML
    private TableColumn<Integer, Computer> columnFloor;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Edge> tableAdyacents2;

    @FXML
    private TableColumn<String, Edge> columnSerialsAdy;

    @FXML
    private TableColumn<Double, Edge> columnPing2;

    @FXML
    private Label labSerial;

    @FXML
    private Label labOffice;

    @FXML
    private Label labFloor;

    @FXML
    void removeDevice(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you really want to remove this computer?", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Alert");
		
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        
        if (ButtonType.YES.equals(result)) {
        	enterprise.removeComputer(enterprise.getComputerSelected().getKey());
        	labSerial.setText("");
    		labOffice.setText("");
    		labFloor.setText("");
    		txtSearch.setText("");
    		initializeTableAdyacents();
    		initializeTableComputers();
        }
    }
    
    @FXML
    void searchComputer(ActionEvent event) {
    	enterprise.searchComputer(txtSearch.getText());
    	Node<String, Computer> computer = enterprise.getComputerSelected();
    	if(computer!=null) {
    		labSerial.setText(computer.getValue().getSerialNumber());
    		labOffice.setText(computer.getValue().getOffice());
    		labFloor.setText(computer.getValue().getFloor()+"");
    		txtSearch.setText("");
    		initializeTableAdyacents();
    	}
    }
    
    public void initializeTableComputers() {
    	ObservableList<Computer> computers = FXCollections.observableArrayList(enterprise.getComputersList());
    	tableAllDevices2.setItems(computers);
    	columnSerials3.setCellValueFactory(new PropertyValueFactory<String,Computer>("serialNumber"));
    	columnOffice.setCellValueFactory(new PropertyValueFactory<String,Computer>("office"));
    	columnFloor.setCellValueFactory(new PropertyValueFactory<Integer,Computer>("floor"));
    }
    
    public void initializeTableAdyacents() {
    	ObservableList<Edge> edges = FXCollections.observableArrayList(enterprise.getComputersAdyacents());
    	tableAdyacents2.setItems(edges);
    	columnSerialsAdy.setCellValueFactory(new PropertyValueFactory<String,Edge>("indexS"));
    	columnPing2.setCellValueFactory(new PropertyValueFactory<Double,Edge>("weight"));
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
    	try {
		
    		char type = rdbGraphB.isSelected() ? Enterprise.ADYACENT_LIST : Enterprise.MATRIX_LIST;
    		
    		String serial1 = txtSerialDeviceA.getText();
    		String serial2 = txtSerialDeviceB.getText();
    		
    		Computer computerA = enterprise.searchComputer(serial1);
    		Computer computerB = enterprise.searchComputer(serial2);
    		
    		labOfficeA.setText(computerA.getOffice());
    		labOfficeB.setText(computerB.getOffice());
    		labFloorA.setText(computerA.getFloor()+"");
    		labFloorB.setText(computerB.getFloor()+"");
    		
    		
    		ArrayList<String> serialsPath = enterprise.minimunPath(serial1, serial2, type);
    		
    		txtAreaRoute.setText("The data are sended following the next path: \n");
    		
    		for(int i=0; i<serialsPath.size();i++) {
    			txtAreaRoute.setText(txtAreaRoute.getText() + "\n"+ serialsPath.get(i));
    		}
    		
    		txtMinimunPing.setText(enterprise.getPingFounded()+" ms");
    		
    	}catch(Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setContentText(e.getMessage());
    		alert.showAndWait();
    	}
    	
    }
}