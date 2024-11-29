package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import factory.Product;
import factory.TeppichCsvCreator;
import factory.TeppichTxtCreator;
import gui.TeppichhandelControl;

public class TeppichhandelModel {
	
	TeppichhandelControl thc;
	Teppich th;
	
	private ArrayList<Teppich> teppiche;
	

	public TeppichhandelModel(TeppichhandelControl teppichhandelControl) {
		this.thc = teppichhandelControl;
		this.th = null;
		teppiche = new ArrayList<Teppich>();
	}

	


	public ArrayList<Teppich> getTeppiche() {
		return teppiche;
	}




	public void setTeppiche(ArrayList<Teppich> teppiche) {
		this.teppiche = teppiche;
	}




	public Teppich getTh() {
		return th;
	}




	public void setTh(Teppich th) {
		this.th = th;
	}




	public void leseAusDatei(String typ){
		System.out.println(typ);
    	try {
    		teppiche.clear();
      		if("csv".equals(typ)){
      			TeppichCsvCreator creator = new factory.TeppichCsvCreator();
      			Product reader = creator.factoryMethod();
//      			BufferedReader ein = new BufferedReader(new FileReader("Teppiche.csv"));
      			String[] teppiche = reader.leseAusDatei();
      			String[] zeile = teppiche[0].split(";");
//      			Teppich av = new Teppich(zeile[0], 
//      				Float.parseFloat(zeile[1]), 
//      				Float.parseFloat(zeile[2]), 
//      				zeile[3], zeile[4].split("_"));
//      				ein.close();
//      				setTh(av);
//      				thc.zeigeInformationsfensterAn("Die Teppiche wurden gelesen!"); 
      					this.th = new Teppich(zeile[0], Float.parseFloat(zeile[1]), Float.parseFloat(zeile[2]), zeile[3], zeile[4].split(","));
      					this.teppiche.add(th);
      					reader.schliesseDatei();
      		} else if(typ.equals("txt")) {
      			TeppichTxtCreator creator = new factory.TeppichTxtCreator();
      			Product reader = creator.factoryMethod();
      			String[] zeile = reader.leseAusDatei()[0].split(";");
      			
      			for(String s: zeile) {
      				System.out.println(s);
      			}
      			
      			this.th = new Teppich(zeile[0], Float.parseFloat(zeile[1]), Float.parseFloat(zeile[2]), zeile[3], zeile[4].split(","));
				this.teppiche.add(th);
				reader.schliesseDatei();
      		}
       		else{
	   			thc.zeigeInformationsfensterAn("Noch nicht implementiert! haha noob");
	   		}
		}
		catch(IOException exc){
			thc.zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			exc.printStackTrace();
			thc.zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}
		
	public void schreibeTeppicheInCsvDatei() {
		if(getTh() != null) {
			try {
				System.out.println(getTh().gibTeppichZurueck(';'));
				BufferedWriter aus 
					= new BufferedWriter(new FileWriter("TeppichAusgabe.csv", true));
				aus.write(th.gibTeppichZurueck(';'));
				aus.close();
				System.out.println(th.gibTeppichZurueck(';'));
	   			thc.zeigeInformationsfensterAn(
		   			"Die Teppiche wurden gespeichert!");
			}	
			catch(IOException exc){
				thc.zeigeFehlermeldungsfensterAn(
					"IOException beim Speichern!");
			}
			catch(Exception exc){
				System.out.println(exc.getMessage());
				thc.zeigeFehlermeldungsfensterAn(
					"Unbekannter Fehler beim Speichern!");
			} 
			
		} else {
		thc.zeigeFehlermeldungsfensterAn(
				"Die Teppiche wurde nicht gesetzt");
		}
	}
	
	public void schreibeTeppichInTxtDatei() {
		if(getTh() != null) {
			try {
				System.out.println(getTh().gibTeppichZurueck(';'));
				BufferedWriter aus 
					= new BufferedWriter(new FileWriter("TeeladenAusgabe.txt", true));
				aus.write(th.gibTeppichZurueck(';'));
				aus.close();
				System.out.println(th.gibTeppichZurueck(';'));
	   			thc.zeigeInformationsfensterAn(
		   			"Die Teesorten wurden gespeichert!");
			}	
			catch(IOException exc){
				thc.zeigeFehlermeldungsfensterAn(
					"IOException beim Speichern!");
			}
			catch(Exception exc){
				System.out.println(exc.getMessage());
				thc.zeigeFehlermeldungsfensterAn(
					"Unbekannter Fehler beim Speichern!");
			} 
			
		} else {
			thc.zeigeFehlermeldungsfensterAn(
				"Teeladen wurde nicht gesetzt");
		}
	}

	
}

