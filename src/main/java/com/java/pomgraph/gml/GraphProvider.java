package com.java.pomgraph.gml;

import java.awt.Color;
import java.util.Set;

import com.github.systemdir.gml.model.EdgeGraphicDefinition;
import com.github.systemdir.gml.model.GraphicDefinition;
import com.github.systemdir.gml.model.NodeGraphicDefinition;
import com.github.systemdir.gml.model.YedGmlGraphicsProvider;

public class GraphProvider implements YedGmlGraphicsProvider<Node, Edge, Group> {

	private final NodeGraphicDefinition nodeDefinition;
	private final EdgeGraphicDefinition edgeDefinition;

	public GraphProvider() {
		nodeDefinition = new NodeGraphicDefinition.Builder().setFill(Color.white)
				.setForm(NodeGraphicDefinition.Form.rectangle).build();

		edgeDefinition = new EdgeGraphicDefinition.Builder().setTargetArrow(EdgeGraphicDefinition.ArrowType.DIAMOND)
				.setLineType(GraphicDefinition.LineType.NORMAL).setLineColour(Color.BLACK).build();

	}

	@Override
	public NodeGraphicDefinition getVertexGraphics(Node vertex) {
		return nodeDefinition;
	}

	@Override
	public EdgeGraphicDefinition getEdgeGraphics(Edge edge, Node edgeSource, Node edgeTarget) {
		return edgeDefinition;
	}

	@Override
	public NodeGraphicDefinition getGroupGraphics(Group group, Set<Node> groupElements) {
		return null;
	}

}
