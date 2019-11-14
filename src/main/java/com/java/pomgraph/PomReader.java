package com.java.pomgraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class PomReader {

	private final String POM_NAME_FILE = "pom.xml";

	public Model readPomFile(Path pomPath) {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = null;
		try {
			model = reader.read(new FileReader(pomPath.toFile()));
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado");
		} catch (IOException | XmlPullParserException e) {
			e.printStackTrace();
			System.err.println("Erro ao manipular o arquivo!");
			throw new RuntimeException(e);
		}
		return model;
	}

	public List<Model> readPomFileFromDirectory(String directoryPath) {
		List<Model> models = new ArrayList<Model>();

		try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
			paths.filter(p -> p.getFileName().toString().contains(POM_NAME_FILE))
					.forEach(p -> models.add(readPomFile(p)));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao manipular a lista de arquivos");
			throw new RuntimeException(e);
		}
		return models;
	}

	

}
