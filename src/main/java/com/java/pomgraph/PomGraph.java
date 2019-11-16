package com.java.pomgraph;

import static com.github.systemdir.gml.YedGmlWriter.PRINT_LABELS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.apache.maven.model.Model;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.jgrapht.graph.SimpleGraph;

import com.github.systemdir.gml.YedGmlWriter;
import com.java.pomgraph.ProjectFactory.FilterType;
import com.java.pomgraph.gml.Edge;
import com.java.pomgraph.gml.GraphFactory;
import com.java.pomgraph.gml.GraphProvider;
import com.java.pomgraph.gml.Group;
import com.java.pomgraph.gml.Node;
import com.java.pomgraph.model.Gavt;
import com.java.pomgraph.model.Project;

public class PomGraph {

	public static void main(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {

		// Read pom file
		PomReader pomReader = new PomReader();
		final String directoryPath = "/Users/giovannicarlos/dev/pom-utils/cer";
		List<Model> models = pomReader.readPomFileFromDirectory(directoryPath);

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
		
		// Create graph
		SimpleGraph<Node, Edge> simpleGraph = graphFactory.fillSimpleGraphfromProjects(projects);
		
		 // define the look and feel of the graph
        GraphProvider graphicsProvider = new GraphProvider();

        // get the gml writer
        YedGmlWriter<Node,Edge,Group> writer 
                = new YedGmlWriter.Builder<>(graphicsProvider, PRINT_LABELS)
                .setVertexLabelProvider(Node::getLabel)
                .setEdgeLabelProvider(Edge::getLabel)
                .build();

        
        // write to file
        if(! new File(directoryPath).exists()) {
        	new File(directoryPath).mkdir();//create folder
        }
        File outputFile=new File(directoryPath+File.separator+"pom.gml");
        try (Writer output = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile), "utf-8"))) {
            writer.export(output, simpleGraph);
        }
        
        System.out.println("Exported to: "+outputFile.getAbsolutePath());
		
	}

}
