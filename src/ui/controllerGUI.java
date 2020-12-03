package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ControllerGUI {
	
	public ControllerGUI() {
		
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
    private TableView<?> tableAdyacents;

    @FXML
    private TableColumn<?, ?> columnSerials2;

    @FXML
    private TableColumn<?, ?> columnPing;

    @FXML
    private TextField txtSerialAdyacent;

    @FXML
    private TextField txtPing;

    @FXML
    private TableView<?> tableAllDevices1;

    @FXML
    private TableColumn<?, ?> columnSerials1;

    @FXML
    void addAdyacent(ActionEvent event) {

    }

    @FXML
    void cancelAdd(ActionEvent event) {

    }

    @FXML
    void confirmAdd(ActionEvent event) {

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
    void calculateMinimunLatency(ActionEvent event) {

    }
}