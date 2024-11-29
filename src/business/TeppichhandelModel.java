package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import gui.TeppichhandelControl;

public class TeppichhandelModel {
	
	TeppichhandelControl thc;
	Teppich th;
	

	public TeppichhandelModel(TeppichhandelControl teppichhandelControl) {
		this.thc = teppichhandelControl;
		this.th = null;
	}



	public Teppich getTh() {
		return th;
	}




	public void setTh(Teppich th) {
		this.th = th;
	}




	public void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Teppiche.csv"));
      			String[] zeile = ein.readLine().split(";");
      			Teppich av = new Teppich(zeile[0], 
      				Float.parseFloat(zeile[1]), 
      				Float.parseFloat(zeile[2]), 
      				zeile[3], zeile[4].split("_"));
      				ein.close();
      				setTh(av);
      				thc.zeigeInformationsfensterAn("Die Teppiche wurden gelesen!");      
      		}
       		else{
	   			thc.zeigeInformationsfensterAn("Noch nicht implementiert!");
	   		}
		}
		catch(IOException exc){
			thc.zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
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
	

}
