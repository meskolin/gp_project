package gp_project;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Tree {

	private Node m_rootNode;

	public Tree(String rootData) {
		m_rootNode = new Node(rootData, NodeType.OPERATOR);
	}

	public void printTree() {
		System.out.println("---------Tree---------\n");

		m_rootNode.printNode();
	}

	public Node getRootNode() {
		return m_rootNode;
	}
}
