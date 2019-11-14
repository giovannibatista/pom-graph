package com.java.pomgraph;

public class Node {

	private String id;
	private String label;
	private Gav gav;
	
	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public Gav getGav() {
		return gav;
	}

	private Node(Builder builder) {
		this.id = builder.id;
		this.label = builder.label;
		this.gav = builder.gav;
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
		private Gav gav;

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

		public Builder withGav(Gav gav) {
			this.gav = gav;
			return this;
		}

		public Node build() {
			return new Node(this);
		}
	}
	
	

}
