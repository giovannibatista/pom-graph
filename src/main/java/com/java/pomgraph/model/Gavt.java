package com.java.pomgraph.model;

public class Gavt {

	private String groupId;
	private String artifactId;
	private String version;
	private String type;

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public String getVersion() {
		return version;
	}

	public String getType() {
		return type;
	}

	public static class Builder {
		public Builder() {
		}

		private String groupId;
		private String artifactId;
		private String version;
		private String type;

		public Builder setGroupId(String groupId) {
			this.groupId = groupId;
			return this;
		}

		public Builder setArtifactId(String artifactId) {
			this.artifactId = artifactId;
			return this;

		}

		public Builder setVersion(String version) {
			this.version = version;
			return this;

		}

		public Builder setType(String type) {
			this.type = type;
			return this;

		}

		public Gavt build() {
			return new Gavt(this);
		}

	}

	public Gavt(Builder builder) {
		this.groupId = builder.groupId;
		this.artifactId = builder.artifactId;
		this.version = builder.version;
		this.type = builder.type;
	}

	public String toLabel() {
		return groupId + "." + artifactId + ":" + type + ":" + version;

	}

	@Override
	public String toString() {
		return groupId + "." + artifactId + ":" + type;
	}

}
