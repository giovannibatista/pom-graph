package com.java.pomgraph;

import static com.github.systemdir.gml.YedGmlWriter.PRINT_LABELS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	private static final String POM_GML_FILENAME = "pom.gml";
	private static final String POM_DESC_FILENAME = "pom_desc.txt";
	private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");

	public static void main(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {
		Scanner sc = new Scanner(System.in);

		// Read pom file
		PomReader pomReader = new PomReader();

		String pomPath = "";
		String directoryOuputPath = "";

		System.out.println("Direterio atual: " + CURRENT_DIRECTORY);

		do {
			System.out.print("Informe o direterio dos arquivos pom(Informe '.' para direterio atual): ");
			pomPath = sc.next();
		} while (pomPath.isEmpty());

		do {
			System.out.print("Informe o direterio dos arquivos gerados(Informe '.' para direterio atual): ");
			directoryOuputPath = sc.next();
		} while (directoryOuputPath.isEmpty());

		pomPath = pomPath.equals(".") ? CURRENT_DIRECTORY : pomPath;
		directoryOuputPath = directoryOuputPath.equals(".") ? CURRENT_DIRECTORY : directoryOuputPath;

		List<Model> models = pomReader.readPomFileFromDirectory(pomPath);

		List<String> filters = new ArrayList<String>();

		String filterIn = "";
		do {
			System.out.print("Informe os groupId das dependencias que deseja filtrar(Informe '.' para prosseguir): ");
			filterIn = sc.next();
			if (!filterIn.equals(".")) {
				filters.add(filterIn);
			}
		} while (!filterIn.equals("."));

		int filterTypeId = 0;

		do {
			System.out.print("Informe o tipo de filtro(1: CONTAINS e 2:STARTS_WITH): ");
			filterTypeId = sc.nextInt();
		} while (filterTypeId == 0);

		sc.close();

		// Filter the dependencies and create a list of projects(pom files)
		List<Project> projects = ProjectFactory.fromModelsWithFilters(models, FilterType.getById(filterTypeId),
				filters);
		StringBuilder contentFile = new StringBuilder();
		for (Project project : projects) {
			contentFile.append(project).append(System.getProperty("line.separator"));
			for (Gavt dependency : project.getDependencies()) {
				contentFile.append("--->" + dependency.toLabel()).append(System.getProperty("line.separator"));
			}
			contentFile.append(System.getProperty("line.separator"));
		}

		// Write to file
		FileWriter fileWriter = FileWriter.Builder().withOutputPath(directoryOuputPath).build();
		fileWriter.writer(POM_DESC_FILENAME, contentFile.toString());

		GraphFactory graphFactory = new GraphFactory();

		// Create graph
		SimpleGraph<Node, Edge> simpleGraph = graphFactory.fillSimpleGraphfromProjects(projects);

		// define the look and feel of the graph
		GraphProvider graphicsProvider = new GraphProvider();

		// get the gml writer
		YedGmlWriter<Node, Edge, Group> writer = new YedGmlWriter.Builder<>(graphicsProvider, PRINT_LABELS)
				.setVertexLabelProvider(Node::getLabel).setEdgeLabelProvider(Edge::getLabel).build();

		// write to file
		if (!new File(directoryOuputPath).exists()) {
			new File(directoryOuputPath).mkdir();// create folder
		}
		File outputFile = new File(directoryOuputPath + File.separator + POM_GML_FILENAME);
		try (Writer output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"))) {
			writer.export(output, simpleGraph);
		}

		System.out.println("Arquivo gerado em: " + outputFile.getAbsolutePath());

	}

}
