package com.milo.questionpaper.xml.utils;

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.dom4j.Branch;
import org.dom4j.Node;

public interface BoundingBox {

	public Rectangle2D getBoundary();
	
	public Node getAsNode();
	
	public List<Node> getAsNodes();
	
}
