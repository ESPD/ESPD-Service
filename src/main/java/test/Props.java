package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class Props {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File in = new File("C:\\Users\\lukasal.NET1\\AppData\\LocalLow\\Home\\workspace.ecertis\\espd\\src\\main\\java\\test\\messages.properties");
		File out = new File("C:\\Users\\lukasal.NET1\\AppData\\LocalLow\\Home\\workspace.ecertis\\espd\\src\\main\\java\\test\\sql.txt");

		FileOutputStream fos = new FileOutputStream(out);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		bw.newLine();
		bw.write("DROP TABLE LABEL;");bw.newLine();
		bw.write("DROP TABLE LABEL_TRANSLATION;");bw.newLine();
		bw.write("DROP TABLE LANGUAGE;");bw.newLine();
		bw.newLine();
		bw.write("CREATE TABLE LANGUAGE (");bw.newLine();
		bw.write("	language_id NUMBER(10),");bw.newLine();
		bw.write("	language_code VARCHAR(45 CHAR),");bw.newLine();
		bw.write("	language_name VARCHAR(150 CHAR),");bw.newLine();
		bw.write("	date_modified DATE,");bw.newLine();
		bw.write("	date_added DATE);");bw.newLine();
		bw.newLine();
		bw.write("CREATE TABLE LABEL (");bw.newLine();
		bw.write("	label_id NUMBER(10),");bw.newLine();
		bw.write("	label_code VARCHAR(45 CHAR),");bw.newLine();
		bw.write("	date_modified DATE,");bw.newLine();
		bw.write("	date_added DATE);");bw.newLine();
		bw.newLine();
		bw.write("CREATE TABLE LABEL_TRANSLATION (");bw.newLine();
		bw.write("	label_translation_id NUMBER(10),");bw.newLine();
		bw.write("	label_id NUMBER(10),");bw.newLine();
		bw.write("	language_id NUMBER(10),");bw.newLine();
		bw.write("	label_translation_text VARCHAR(1000),");bw.newLine();
		bw.write("	date_modified DATE,");bw.newLine();
		bw.write("	date_added DATE);");bw.newLine();
		bw.newLine();bw.newLine();
		
		String [] codes = {"bg","cs","da","de","et","el","en","es","fr","ga","hr","it","lv","lt","hu","mt","nl","pl","pt","ro","sk","sl","fl","sv","no"};
		String [] languages = {"български","čeština","dansk","Deutsch","eesti keel","ελληνικά","English","español","français","Gaeilge","hrvatski","italiano","latviešu valoda","lietuvių kalba","magyar","Malti","Nederlands","polski","portugês","română","slovenčina","slovenščina","suomi","svsvenska","norsk"};

		{//write languages
			for(int i = 0 ; i < codes.length;i++) {
				bw.write("INSERT INTO LANGUAGE (LANGUAGE_ID, LANGUAGE_CODE, LANGUAGE_NAME, DATE_MODIFIED, DATE_ADDED) VALUES ("+i+", '"+codes[i]+"', '"+languages[i]+"', SYSDATE, SYSDATE);");bw.newLine();
			}
			bw.newLine();bw.newLine();
		}
		
		List<String> labels = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		{//read labels with text
			BufferedReader br = new BufferedReader(new FileReader(in));
			{
			    for(String line; (line = br.readLine()) != null; ) {
			    	if(StringUtils.hasText(line) && (!line.startsWith("#"))) {
			    		labels.add(line.split("=", 2)[0]);
			    		values.add(line.split("=", 2)[1]);
			    	}
			    }
			}
		}
		
		//write labels sql
		{
			for(int i = 0 ; i < labels.size(); i++) {
				bw.write("INSERT INTO LABEL (LABEL_ID, LABEL_CODE, DATE_MODIFIED, DATE_ADDED) VALUES ("+i+", '"+labels.get(i)+"', SYSDATE, SYSDATE);");bw.newLine();
			}
			bw.newLine();bw.newLine();
		}
		
		//write labels translations sql
		{ 
			for(int i = 0 ; i < labels.size();i++) {
				for(int j = 0 ; j < codes.length; j++) {
					String translation = "en".equals(codes[j]) ? values.get(i) : ("[" + values.get(i) + "]" + codes[j]);
					bw.write("INSERT INTO LABEL_TRANSLATION (LABEL_TRANSLATION_ID, LABEL_ID, LABEL_TRANSLATION_TEXT, LANGUAGE_ID, DATE_MODIFIED, DATE_ADDED) VALUES ("+(i*codes.length + j)+", "+i+", '"+translation+"', "+j+", SYSDATE, SYSDATE);");bw.newLine();
				}
				bw.newLine();
			}
			bw.newLine();bw.newLine();
		}
		
		
		bw.close();
	}

}
