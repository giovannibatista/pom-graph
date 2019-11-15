package com.java.pomgraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class FileWriter {
	
	private String outputPath;

	private FileWriter(Builder builder) {
		this.outputPath = builder.outputPath;
	}

	/**
	 * Creates builder to build {@link FileWriter}.
	 * @return created builder
	 */
	public static Builder Builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link FileWriter}.
	 */
	public static final class Builder {
		private String outputPath;

		private Builder() {
		}

		public Builder withOutputPath(String outputPath) {
			this.outputPath = outputPath;
			return this;
		}

		public FileWriter build() {
			return new FileWriter(this);
		}
	}
	
	public void writer(String nameFile, String content) {
        new File(this.outputPath).mkdir();
        File outputFile=new File(this.outputPath+File.separator+nameFile);
        try (Writer output = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile), "utf-8"))) {
        	 PrintWriter out = new PrintWriter(output);
        	 out.println(content);
        	 out.flush();        	
        } catch (IOException e ) {
        	System.err.println("Ocorreu um problema ao escrever o arquivo: " + nameFile);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        
        System.out.println("Exported to: "+outputFile.getAbsolutePath());
	}
	
	

}
