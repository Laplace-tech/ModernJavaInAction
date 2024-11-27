package chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

	private static final String FILE_PATH = "src/chap03/home.txt";

	public static String processFile(BufferedReaderProcessor process) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			return process.process(reader);
		}
	}

//	public interface BufferedReaderProcessor {
//		public abstract String process(BufferedReader bufferedReader) throws IOException;
//	}

	public static void main(String[] args) throws IOException {
		
		BufferedReaderProcessor process1 = br -> {
			StringBuilder result = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) {
				result.append(line).append("\n");
			} // while
			if(result.length() > 0) {
				result.setLength(result.length() - 1);
			} // if
			return result.toString();
		}; // BufferedReaderProcessor process
		
		BufferedReaderProcessor process2 = br -> br.readLine();
		BufferedReaderProcessor process3 = br -> new StringBuilder().append(br.readLine()).append("\n")
														.append(br.readLine()).toString();

		System.out.println(processFile(process1));
		System.out.println("-------------------------------------------");
		System.out.println(processFile(process2));
		System.out.println("-------------------------------------------");
		System.out.println(processFile(process3));
		System.out.println("-------------------------------------------");
		
	} // main

}
