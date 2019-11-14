package com.java.pomgraph.gml;

import com.java.pomgraph.model.Gavt;

public class Node {

	private String id;
	private String label;
	private Gavt gavt;
	
	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public Gavt getGav() {
		return gavt;
	}

	private Node(Builder builder) {
		this.id = builder.id;
		this.label = builder.label;
		this.gavt = builder.gavt;
	}

	/**
	 * Creates builder to build {@link Node}.
	 * 
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Node}.
	 */
	public static final class Builder {
		private String id;
		private String label;
		private Gavt gavt;

		private Builder() {
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withLabel(String label) {
			this.label = label;
			return this;
		}

		public Builder withGav(Gavt gavt) {
			this.gavt = gavt;
			return this;
		}

		public Node build() {
			return new Node(this);
		}
	}
	
	

}
