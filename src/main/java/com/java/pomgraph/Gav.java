package com.java.pomgraph;

public class Gav {

	private String groupId;
	private String artifactId;
	private String version;

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public String getVersion() {
		return version;
	}

	public static class Builder {
		public Builder() {
		}

		private String groupId;
		private String artifactId;
		private String version;

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

		public Gav build() {
			return new Gav(this);
		}

	}

	public Gav(Builder builder) {
		this.groupId = builder.groupId;
		this.artifactId = builder.artifactId;
		this.version = builder.version;
	}

	@Override
	public String toString() {
		return groupId + "." + artifactId + ":" + version;
	}
	
	
	

}
