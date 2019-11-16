package com.java.pomgraph.gml;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int accessQuantity;
	private String label;

	public Edge(int accessQuantity, String label) {
		super();
		this.accessQuantity = accessQuantity;
		this.label = label;
	}

	public int getAccessQuantity() {
		return accessQuantity;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
