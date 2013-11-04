package gp_project;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Node {
	private String m_data; // root data
	private Node m_parent;
	private Node m_rightNode;
	private Node m_leftNode;
	private NodeType m_type;
	
	public Node(Node other)
	{
		this.m_type = other.getNodeType();
		this.m_data = other.getData();
		this.m_rightNode = other.getRightNode();
		this.m_leftNode = other.getLeftNode();
	}
	
	public Node(String data, NodeType type) {
		m_data = data;
		m_type = type;
	}

	public Node getRightNode() {

		return m_rightNode;
	}
	
	public void setNodeType(NodeType type)
	{
		m_type = type;
	}

	public NodeType getNodeType()
	{
		return m_type;
	}
	
	public void setRightNode(Node node) {
		m_rightNode = node;
	}

	public Node getLeftNode() {

		return m_leftNode;
	}

	public void setLeftNode(Node node) {
		m_leftNode = node;
	}

	public String getData() {

		return m_data;

	}

	public void setData(String data) {
		m_data = data;
	}

	public void printNode() {

		System.out.format("node data: %s\n", m_data);
		if (m_rightNode != null) {
			System.out.println("rightnode:");
			m_rightNode.printNode();
		}
		if (m_leftNode != null) {
			System.out.println("leftnode:");
			m_leftNode.printNode();
		}
	}

}
