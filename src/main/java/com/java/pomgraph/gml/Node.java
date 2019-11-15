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
	public static Builder Builder() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equalsIgnoreCase(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", label=" + label + "]";
	}

}
