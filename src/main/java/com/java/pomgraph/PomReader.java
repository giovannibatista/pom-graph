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

	private final String pomNameFile = "pom";
	private final String pomExtensionFile="xml";

	public Model readPomFile(String pomFile) {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = null;
		try {
			model = reader.read(new FileReader(pomFile));
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado");
		} catch (IOException | XmlPullParserException e) {
			e.printStackTrace();
			System.err.println("Erro ao manipular o arquivo!");
			throw new RuntimeException(e);
		}
		return model;
	}

	public List<Model> readPomFileFromDirectory(String path) {
		List<Model> models = new ArrayList<Model>();
		
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return models;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader
				.read(new FileReader("/Users/giovannicarlos/dev/pom-utils/cer/CER.App.Java.Cert-Procergs/pom.xml"));
		System.out.println(model.getId());
		System.out.println(model.getGroupId());
		System.out.println(model.getArtifactId());
		System.out.println(model.getVersion());
		System.out.println(model.getDependencies().toString());

		Gav gav = new Gav.Builder().setGroupId(model.getGroupId()).setArtifactId(model.getArtifactId())
				.setVersion(model.getVersion()).build();

		System.out.println(gav.toString());

	}

}
