package com.java.pomgraph;

import java.util.List;

public class Pom {

	private Gav artifact;
	private List<Gav> dependencies;

	public Gav getArtifact() {
		return artifact;
	}

	public List<Gav> getDependencies() {
		return dependencies;
	}

	public static class Builder {

		private Gav artifact;
		private List<Gav> dependencies;

		public Builder setArtifact(Gav artifact) {
			this.artifact = artifact;
			return this;
		}

		public Builder setDependencies(List<Gav> dependencies) {
			this.dependencies = dependencies;
			return this;
		}

		public Pom build() {
			return new Pom(this);
		}
	}

	public Pom(Builder builder) {
		this.artifact = builder.artifact;
		this.dependencies = builder.dependencies;
	}

}
