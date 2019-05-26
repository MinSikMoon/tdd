package test;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import module.GitLogParser;

public class GitLogParser_Test {
	//테스트용 준비물들
	Reader reader;
	Writer writer;
	String compareText = "";
	char[] buffer = new char[1000];
	GitLogParser gitLogParser;
	
	@Before
	public void 준비() throws Exception {
		reader = new FileReader("param.txt");
		writer = new FileWriter("test.txt");
		while(reader.read(buffer) != -1) 
			compareText += new String(buffer);
		writer.write(compareText);
		
		gitLogParser = new GitLogParser();
	}
	/**
	 * param.txt를 잘 읽어오는 가?
	 * @throws Exception 
	 */
	@Test
	public void tc1_param_txt_() throws Exception {
		String result = gitLogParser.getCommitLogAsString("param.txt");
		assertEquals(compareText, result);
	}
	
	
	@After
	public void 리더닫기() throws IOException {
		reader.close();
		writer.close();
	}
}
