package com.java.pomgraph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.maven.model.Model;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.java.pomgraph.model.Gavt;
import com.java.pomgraph.model.Project;

public class PomGraph {

	public static void main(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {

		PomReader pomReader = new PomReader();
		List<Model> models = pomReader.readPomFileFromDirectory("C:\\Projetos\\pom-explorer\\cer");

		/*System.out.println(models.toString());

		for (Model model : models) {
			System.out.println("->" + model.toString());
			for (Dependency dep : model.getDependencies()) {
				System.out.println("----->" + dep);
			}
		}*/
		
		
		List<Project> projects = ProjectFactory.fromModels(models);
		
		for (Project project : projects) {
			System.out.println(project);
			for (Gavt dependency : project.getDependencies()) {
				System.out.println("--->" + dependency);
			}
		}
		
		/*
		 * MavenXpp3Reader reader = new MavenXpp3Reader(); Model model = reader
		 * .read(new FileReader(
		 * "/Users/giovannicarlos/dev/pom-utils/cer/CER.App.Java.Cert-Procergs/pom.xml"
		 * )); System.out.println(model.getId());
		 * System.out.println(model.getGroupId());
		 * System.out.println(model.getArtifactId());
		 * System.out.println(model.getVersion());
		 * System.out.println(model.getDependencies().toString());
		 * 
		 * Gavt gav = new
		 * Gavt.Builder().setGroupId(model.getGroupId()).setArtifactId(model.
		 * getArtifactId()) .setVersion(model.getVersion()).build();
		 * 
		 * System.out.println(gav.toString());
		 */
	}

}
