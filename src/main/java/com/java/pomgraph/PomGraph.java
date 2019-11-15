package com.java.pomgraph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.model.Model;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.SimpleGraph;

import com.java.pomgraph.ProjectFactory.FilterType;
import com.java.pomgraph.gml.Edge;
import com.java.pomgraph.gml.GraphFactory;
import com.java.pomgraph.gml.Node;
import com.java.pomgraph.model.Gavt;
import com.java.pomgraph.model.Project;

public class PomGraph {

	public static void main(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {

		// Read pom file
		PomReader pomReader = new PomReader();
		List<Model> models = pomReader.readPomFileFromDirectory("/Users/giovannicarlos/dev/pom-utils/cer");

		// Filter the dependencies and create a list of projects(pom files)
		List<Project> projects = ProjectFactory.fromModelsWithFilters(models, FilterType.STARTSWITH, "com.procergs",
				"com.acrs");

		StringBuilder contentFile = new StringBuilder();
		for (Project project : projects) {
			contentFile.append(project).append(System.getProperty("line.separator"));
			for (Gavt dependency : project.getDependencies()) {
				contentFile.append("--->" + dependency).append(System.getProperty("line.separator"));
			}
			contentFile.append(System.getProperty("line.separator"));
		}

		// Write to file
		FileWriter fileWriter = FileWriter.Builder().withOutputPath("/Users/giovannicarlos/dev/pom-utils/cer/output")
				.build();
		fileWriter.writer("pom_desc.txt", contentFile.toString());
		
		
		GraphFactory graphFactory = new GraphFactory();
		
		graphFactory.fromProjects(projects);
		
		

		// Create graph
		SimpleGraph<Node, Edge> simpleGraph = new SimpleGraph<Node, Edge>(Edge.class);
		
		for (Node node : graphFactory.getNodes()) {
			simpleGraph.addVertex(node);
			//TODO: Refactor edge...
		}
		
	}

}
