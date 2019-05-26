package test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;

public class GitLogParser_Test {
	//테스트용 준비물들
	Reader reader;
	Writer writer;
	String compareText = "";
	char[] buffer = new char[1000];
	
	@Before
	public void 준비() throws Exception {
		reader = new FileReader("param.txt");
		writer = new FileWriter("test.txt");
		while(reader.read(buffer) != -1) 
			compareText += new String(buffer);
		writer.write(compareText);
	}
	
	//로직 테스트할 곳
	
	
	@After
	public void 리더닫기() throws IOException {
		reader.close();
		writer.close();
	}
}
