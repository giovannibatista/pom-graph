package com.java.pomgraph;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;

import com.java.pomgraph.model.Gavt;
import com.java.pomgraph.model.Project;

public class ProjectFactory {

	public static List<Project> fromModels(List<Model> models) {
		List<Project> projects = new ArrayList<Project>();
		for (Model model : models) {
			projects.add(new Project.Builder().setArtifact(ProjectFactory.fromModel(model))
					.setDependencies(ProjectFactory.fromDependenciesModel(model.getDependencies())).build());
		}
		return projects;
	}

	public static List<Project> fromModelsWithFilters(List<Model> models, FilterType filterDpendencyType,
			String... filterPatterns) {
		List<Project> projects = new ArrayList<Project>();
		for (Model model : models) {
			projects.add(new Project.Builder()
					.setArtifact(ProjectFactory.fromModel(model)).setDependencies(ProjectFactory
							.fromDependenciesModel(model.getDependencies(), filterDpendencyType, filterPatterns))
					.build());
		}
		return projects;
	}

	private static List<Gavt> fromDependenciesModel(List<Dependency> dependenciesModel, FilterType filterDpendencyType,
			String[] filterPatterns) {
		List<Gavt> dependencies = new ArrayList<Gavt>();

		for (Dependency dependency : dependenciesModel) {
			dependencies.add(fromDependency(dependency, filterDpendencyType, filterPatterns));
		}
		return dependencies;
	}

	

	private static List<Gavt> fromDependenciesModel(List<Dependency> dependenciesModel) {
		List<Gavt> dependencies = new ArrayList<Gavt>();

		for (Dependency dependency : dependenciesModel) {
			dependencies.add(fromDependency(dependency));
		}
		return dependencies;
	}

	private static Gavt fromModel(Model model) {
		return new Gavt.Builder().setArtifactId(model.getArtifactId()).setGroupId(model.getGroupId())
				.setVersion(model.getVersion()).setType(model.getPackaging()).build();
	}

	private static Gavt fromDependency(Dependency dependency) {
		return new Gavt.Builder().setArtifactId(dependency.getArtifactId()).setGroupId(dependency.getGroupId())
				.setVersion(dependency.getVersion()).setType(dependency.getType()).build();
	}
	
	private static Gavt fromDependency(Dependency dependency, FilterType filterDpendencyType, String[] filterPatterns) {
		
		for (String filter : filterPatterns) {
			//if(ProjectFactory.is))
			
		}
		return new Gavt.Builder().setArtifactId(dependency.getArtifactId()).setGroupId(dependency.getGroupId())
				.setVersion(dependency.getVersion()).setType(dependency.getType()).build();
	}

	public enum FilterType {
		CONTAINS, STARTSWITH, ENDSWITH, EQUAL, EQUALIGNORECASE;
	}

}
