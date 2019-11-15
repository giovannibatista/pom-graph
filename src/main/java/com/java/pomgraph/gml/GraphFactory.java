package com.java.pomgraph.gml;

import java.util.ArrayList;
import java.util.List;

import com.java.pomgraph.model.Gavt;
import com.java.pomgraph.model.Project;

public class GraphFactory {
	
	private List<Node> nodes;
	private List<Edge> edges;
	private int idEdge;
	
	public GraphFactory() {
		super();
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
		this.idEdge = 0;
	}

	public void fromProjects(List<Project> projects) {
		
		Node nodeProject = null;
		Node nodeDependency = null;
		for (Project project : projects) {
			nodeProject = this.fromGavt(project.getArtifact());
			nodeProject = addNode(nodeProject);
			
			for (Gavt dependency : project.getDependencies()) {
				nodeDependency = this.fromGavt(dependency);
				nodeDependency = addNode(nodeDependency);
				
				this.addEdge(nodeProject, nodeDependency);
			}
		}
	}
	
	private Node addNode(Node node) {
		
		if(nodes.contains(node)) {
			return nodes.stream().filter(n -> n.equals(node)).findFirst().get();
		}
		
		nodes.add(node);
		return node;
	}
	
	private void addEdge(Node source, Node target) {
		this.edges.add(this.fromNodes(source, target));
	}
	
	
	private Node fromGavt(Gavt gavt) {
		return Node.Builder().withGav(gavt).withId(gavt.toString()).withLabel(gavt.toString()).build();
	}
	
	private Edge fromNodes(Node source, Node target) {
		return Edge.Builder().withSource(source).withTarget(target).withId(""+this.idEdge++).build();
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

}
