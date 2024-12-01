package factory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TeppichCsvProduct extends Product {

	private BufferedReader br;
	
	public TeppichCsvProduct() {
		try {
			br = new BufferedReader(new FileReader("TeppichAusgabe.csv"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	
	
	@Override
	public String[] leseAusDatei() throws IOException {
		ArrayList<String> teppiche = new ArrayList<String>();
		String line;
		
		while((line = br.readLine()) != null) {
			System.out.println("Read line: " + line);
			if(!line.trim().isEmpty()) {
				teppiche.add(line);
			}
		}
		return teppiche.toArray(new String[0]);
	}

	@Override
	public void schliesseDatei() throws IOException {
		br.close();

	}

}
