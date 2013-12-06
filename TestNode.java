package gp_project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestNode {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {
		Node n = new Node("0", NodeType.OPERAND);
		assertEquals(n.getData(), "0");
	}

	@Test
	public void testCopyConstructor() {
		Node n1 = new Node("0", NodeType.OPERAND);
		Node n2 = new Node(n1);
		assertEquals(n2.getData(), "0");
		assertEquals(n2.getNodeType(), NodeType.OPERAND);
	}

	@Test
	public void testGettersAndSetters()
	{
		Node n1 = new Node("+", NodeType.OPERATOR);
		n1.setData("2");
		assertEquals(n1.getData(), "2");
		n1.setNodeType(NodeType.OPERAND);
		assertEquals(n1.getNodeType(), NodeType.OPERAND);
	}
}
