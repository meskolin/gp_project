package gp_project;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Tree implements Comparable<Tree>{

	private Node m_rootNode;
	private double fitnessValue = 99999;
	
	public double getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

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
	
	public int compareTo(Tree other)
	{
		if(other.getFitnessValue() > this.fitnessValue)
			return 1;
		else if (other.getFitnessValue () == this.fitnessValue)
			return 0;
		else
			return -1;
	}
}
