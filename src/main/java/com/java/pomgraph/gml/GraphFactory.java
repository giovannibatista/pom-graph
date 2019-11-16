package com.java.pomgraph.gml;

import java.util.List;

import org.jgrapht.graph.SimpleGraph;

import com.java.pomgraph.model.Gavt;
import com.java.pomgraph.model.Project;

public class GraphFactory {

	private int edgeLabel;

	public GraphFactory() {
		super();
		this.edgeLabel = 0;
	}

	public SimpleGraph<Node, Edge> fillSimpleGraphfromProjects(List<Project> projects) {

		SimpleGraph<Node, Edge> simpleGraph = new SimpleGraph<Node, Edge>(Edge.class);

		Node nodeProject = null;
		Node nodeDependency = null;
		for (Project project : projects) {
			nodeProject = this.fromGavt(project.getArtifact());
			// nodeProject = addNode(nodeProject);

			simpleGraph.addVertex(nodeProject);

			for (Gavt dependency : project.getDependencies()) {
				nodeDependency = this.fromGavt(dependency);
				// nodeDependency = addNode(nodeDependency);

				simpleGraph.addVertex(nodeDependency);
				simpleGraph.addEdge(nodeProject, nodeDependency, new Edge(1, "" + edgeLabel++));
			}
			this.edgeLabel = 0;
		}
		return simpleGraph;
	}

	/*
	 * private Node addNode(Node node) {
	 * 
	 * if(nodes.contains(node)) { return nodes.stream().filter(n ->
	 * n.equals(node)).findFirst().get(); }
	 * 
	 * nodes.add(node); return node; }
	 */

	private Node fromGavt(Gavt gavt) {
		return Node.Builder().withGav(gavt).withId(gavt.toString()).withLabel(gavt.toString()).build();
	}

}
