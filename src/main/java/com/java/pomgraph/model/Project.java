package com.java.pomgraph.model;

import java.util.List;

public class Project {

	private Gavt artifact;
	private List<Gavt> dependencies;

	public Gavt getArtifact() {
		return artifact;
	}

	public List<Gavt> getDependencies() {
		return dependencies;
	}

	public static class Builder {

		private Gavt artifact;
		private List<Gavt> dependencies;

		public Builder setArtifact(Gavt artifact) {
			this.artifact = artifact;
			return this;
		}

		public Builder setDependencies(List<Gavt> dependencies) {
			this.dependencies = dependencies;
			return this;
		}

		public Project build() {
			return new Project(this);
		}
	}

	public Project(Builder builder) {
		this.artifact = builder.artifact;
		this.dependencies = builder.dependencies;
	}

	@Override
	public String toString() {
		return "Project [artifact=" + artifact + ", dependencies.size=" + (dependencies != null ? dependencies.size() : 0) + "]";
	}
	
	

}
