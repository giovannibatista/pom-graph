package com.java.pomgraph.gml;

public class Edge {

	private String id;
	private Node source;
	private Node target;

	private Edge(Builder builder) {
		this.id = builder.id;
		this.source = builder.source;
		this.target = builder.target;
	}

	public String getId() {
		return id;
	}

	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	/**
	 * Creates builder to build {@link Edge}.
	 * 
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Edge}.
	 */
	public static final class Builder {
		private String id;
		private Node source;
		private Node target;

		private Builder() {
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withSource(Node source) {
			this.source = source;
			return this;
		}

		public Builder withTarget(Node target) {
			this.target = target;
			return this;
		}

		public Edge build() {
			return new Edge(this);
		}
	}

}
