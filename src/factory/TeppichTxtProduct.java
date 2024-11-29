package factory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TeppichTxtProduct extends Product {

	private BufferedReader br;
	
	public TeppichTxtProduct() {
		try {
			br = new BufferedReader(new FileReader("TeppichAusgabe.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String[] leseAusDatei() throws IOException {
		String[] ergLine = new String[5];
		int i = 0;
		String line = br.readLine();
		while(i<ergLine.length) {
			ergLine[i] = line;
			line = br.readLine();
			i++;
		}
		return ergLine;
	}

	@Override
	public void schliesseDatei() throws IOException {
		// TODO Auto-generated method stub
		br.close();
	}

}
