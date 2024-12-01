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


public class TeppichhandelModel{
	
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
		this.teppiche.add(th);
		
	}
	
	public void addTeppich(Teppich tep) {
		
	}



	public void leseAusDatei(String typ) {
	    System.out.println(typ);
	    try {
	        teppiche.clear();
	        
	        if (typ != null && (typ.toLowerCase().contains("csv") || typ.toLowerCase().contains("txt"))) {
	            Product reader;
	            if (typ.toLowerCase().contains("csv")) {
	                reader = new factory.TeppichCsvCreator().factoryMethod();
	            } else {
	                reader = new factory.TeppichTxtCreator().factoryMethod();
	            }
	            
	            String[] teppiche = reader.leseAusDatei();

	            for (String x : teppiche) {
	                System.out.println("READ THIS ONE:" + x);
	                String[] zeile = x.split(";");
	                this.th = new Teppich(zeile[0], Float.parseFloat(zeile[1]), Float.parseFloat(zeile[2]), zeile[3], zeile[4].split(","));
	                this.teppiche.add(th);
	            }
	            reader.schliesseDatei();
	           
	        } else {
	            thc.zeigeInformationsfensterAn("Noch nicht implementiert! haha noob");
	        }
	    }
	    catch(IOException exc) {
	        thc.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
	    }
	    catch(Exception exc) {
	        exc.printStackTrace();
	        thc.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
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
					= new BufferedWriter(new FileWriter("TeppichAusgabe.txt", true));
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

