package gp_project;

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
