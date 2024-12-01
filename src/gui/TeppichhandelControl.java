package gui;

import business.Teppich;
import business.TeppichhandelModel;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.Observer;

public class TeppichhandelControl implements Observer {
	
	TeppichhandelView thv;
	public TeppichhandelModel thm;
	
	public TeppichhandelControl(Stage primaryStage) {
		this.thm = TeppichhandelModel.getInstance(this);
		this.thv = new TeppichhandelView(primaryStage, this);
		thm.addObserver(this);
	}
	
	
    public void nehmeTeppichAuf(String artikelnummer, String laenge, String breite, String kategorie, String farben){
    	try{
    		Teppich th = new Teppich(
    			artikelnummer,
   	            Float.parseFloat(laenge),
   	            Float.parseFloat(breite),
   	         kategorie,
   	        farben.split(";"));
    		thm.setTh(th);
    		zeigeInformationsfensterAn("Das Teppich wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		System.out.println(exc.getMessage());
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    public void zeigeTeppicheAn(){
    	if(thm.getTh() != null){
    		String text = "";
    		for(Teppich teppich : thm.getTeppiche()) {
    			text += teppich.gibTeppichZurueck(' ');
    		}
    		thv.txtAnzeige.setText(text);
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde kein Teppich aufgenommen!");
    	}
    	
    }    
    
    public void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    public void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }
    
    public void leseAusDatei(String typ) {
    	thm.leseAusDatei(typ);
    }
    
    public void schreibeTeppichInCsvDatei() {
    	thm.schreibeTeppicheInCsvDatei();
    }


	@Override
	public void update() {
		// TODO Auto-generated method stub
		zeigeTeppicheAn();
	}



}
