import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;


public class Tree {

	private Node m_rootNode;

	public Tree(String rootData) {
		m_rootNode = new Node(rootData);
	}


       public void printTree()
       {
		System.out.println("---------Tree---------\n");

                System.out.format("node data: %s\n", m_rootNode.getData());

                m_rootNode.printNode();
       }
	
	public Node getRootNode()
	{
		return m_rootNode;
	}
}
