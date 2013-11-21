package gp_project;

public class Tree implements Comparable<Tree> {

	private double fitnessValue = 99999;
	private Node m_rootNode;

	public Tree(String rootData) {
		m_rootNode = new Node(rootData, NodeType.OPERATOR);
	}

	@Override
	public int compareTo(Tree other) {
		if (other.getFitnessValue() > this.fitnessValue)
			return 1;
		else if (other.getFitnessValue() == this.fitnessValue)
			return 0;
		else
			return -1;
	}

	public double getFitnessValue() {
		return fitnessValue;
	}

	public Node getRootNode() {
		return m_rootNode;
	}

	public void printTree() {
		m_rootNode.printNode();
	}

	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}
}
