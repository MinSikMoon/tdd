package module;

import java.io.FileReader;
import java.io.Reader;

public class GitLogParser {

	public String getCommitLogAsString(String commitLog) throws Exception {
		Reader reader = new FileReader(commitLog);
		String result = "";
		char[] buffer = new char[1000];
		try {
			while(reader.read(buffer) != -1) 
				result += new String(buffer);
		} finally {
			reader.close();
		}
		return result;
	}

}
