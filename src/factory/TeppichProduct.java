package factory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TeppichProduct extends Product {

	private BufferedReader br;
	
	public TeppichProduct() {
		try {
			br = new BufferedReader(new FileReader("TeppichAusgabe.csv"));
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public String[] leseAusDatei() throws IOException {
		String[] result = null;
		try {
			String content = "";
			String line = "";
			
			while((line = br.readLine()) != null) {
				content += line;
			}
			result = content.split(";");
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			br.close();
		}
		return result;
	}

	@Override
	public void schliesseDatei() throws IOException {
		// TODO Auto-generated method stub
		br.close();
	}

}
