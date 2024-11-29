package gui;
   
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Teppich;
import business.TeppichhandelModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class TeppichhandelView {
	  
    //---Anfang Attribute der grafischen Oberflaeche---
    public Pane pane     					= new  Pane();
    public Label lblEingabe    	 		= new Label("Eingabe");
    public Label lblAnzeige   	 	    	= new Label("Anzeige");
    public Label lblArtikelnummer 					= new Label("Artikelnummer:");
    public Label lblKategorie   		= new Label("Kategorie:");
    public Label lblBreite  	 		= new Label("Breite:");
    public Label lblLaenge   			= new Label("Lange:");
    public Label lblFarben			 		= new Label("Farben:");
    public TextField txtArtikelnummer 	 			= new TextField();
    public TextField txtKategorie		= new TextField();
    public TextField txtBreite		= new TextField();
    public TextField txtLaenge			= new TextField();
    public TextField txtFarben	= new TextField();
    public TextArea txtAnzeige  			= new TextArea();
    public Button btnEingabe 		 		= new Button("Eingabe");
    public Button btnAnzeige 		 		= new Button("Anzeige");
    public MenuBar mnbrMenuLeiste  		= new MenuBar();
    public Menu mnDatei             		= new Menu("Datei");
    public MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    public MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    public MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");   
    //-------Ende Attribute der grafischen Oberflaeche-------
    

    
    public TeppichhandelView(Stage primaryStage, TeppichhandelControl thc) {
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Teppichenn");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener(thc);
    }
    
    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblArtikelnummer.setLayoutX(20);
    	lblArtikelnummer.setLayoutY(90);
    	lblKategorie.setLayoutX(20);
    	lblKategorie.setLayoutY(130);
    	lblBreite.setLayoutX(20);
    	lblBreite.setLayoutY(170);
    	lblLaenge.setLayoutX(20);
    	lblLaenge.setLayoutY(210);
    	lblFarben.setLayoutX(20);
    	lblFarben.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblArtikelnummer, lblKategorie, lblBreite,
       		lblLaenge, lblFarben);
    
    	// Textfelder
     	txtArtikelnummer.setLayoutX(170);
    	txtArtikelnummer.setLayoutY(90);
    	txtArtikelnummer.setPrefWidth(200);
    	txtKategorie.setLayoutX(170);
    	txtKategorie.setLayoutY(130);
    	txtKategorie.setPrefWidth(200);
    	txtBreite.setLayoutX(170);
    	txtBreite.setLayoutY(170);
    	txtBreite.setPrefWidth(200);
      	txtLaenge.setLayoutX(170);
    	txtLaenge.setLayoutY(210);
    	txtLaenge.setPrefWidth(200);
    	txtFarben.setLayoutX(170);
    	txtFarben.setLayoutY(250);
    	txtFarben.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtArtikelnummer, txtKategorie, txtBreite,
     		txtLaenge, txtFarben);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener(TeppichhandelControl thc) {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {      

			@Override
            public void handle(ActionEvent e) {
            	thc.nehmeTeppichAuf(txtArtikelnummer.getText(), txtLaenge.getText(), txtBreite.getText(), txtKategorie.getText(), txtFarben.getText());
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            thc.zeigeTeppicheAn();
	        } 
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	thc.leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	thc.leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				thc.schreibeTeppichInCsvDatei();
			}	
	    });
    }
    

}
