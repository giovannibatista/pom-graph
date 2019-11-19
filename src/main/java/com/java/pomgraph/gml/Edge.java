package com.java.pomgraph.gml;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {
	private static final long serialVersionUID = 1L;

	private String label;

	private Edge(Builder builder) {
		this.label = builder.label;
	}

	public static final class Builder {

		private String label;

		public Builder() {
		}

		public Builder setLabel(String label) {
			this.label = label;
			return this;
		}

		public Edge build() {
			return new Edge(this);
		}
	}

	public String getLabel() {
		return label;
	}

}
