package com.java.pomgraph.gml;

import java.util.List;

import org.jgrapht.graph.SimpleGraph;

import com.java.pomgraph.model.Gavt;
import com.java.pomgraph.model.Project;

public class GraphFactory {

	public GraphFactory() {
		super();
	}

	public SimpleGraph<Node, Edge> fillSimpleGraphfromProjects(List<Project> projects) {

		SimpleGraph<Node, Edge> simpleGraph = new SimpleGraph<Node, Edge>(Edge.class);

		Node nodeProject = null;
		Node nodeDependency = null;
		for (Project project : projects) {
			nodeProject = this.fromGavt(project.getArtifact());

			simpleGraph.addVertex(nodeProject);

			for (Gavt dependency : project.getDependencies()) {
				nodeDependency = this.fromGavt(dependency);
				simpleGraph.addVertex(nodeDependency);
				simpleGraph.addEdge(nodeProject, nodeDependency,
						new Edge.Builder().setLabel(dependency.getVersion()).build());
			}
		}
		return simpleGraph;
	}

	private Node fromGavt(Gavt gavt) {
		return Node.Builder().withGav(gavt).withId(gavt.toString()).withLabel(gavt.toString()).build();
	}

}
