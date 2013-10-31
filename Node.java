import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;


        public class Node {
                private String m_data; // root data
                private Node m_parent ;
                private Node m_rightNode;
		private Node m_leftNode;

                public Node(String data)
                {
                        m_data = data;
                }

		public Node getRightNode()
		{

			return m_rightNode;
		}

		public void setRightNode(Node node)
		{
			m_rightNode = node;
		}


		public Node getLeftNode()
		{

			return m_leftNode;
		}

		public void setLeftNode(Node node)
		{
			m_leftNode = node;
		}

		public String getData()
		{

			return m_data;

		}
		public void setData(String data)
		{
			m_data = data;
		}


                public void printNode()
                {
		System.out.println("printNode\n");

                System.out.format("node data: %s\n",m_data);
		if (m_rightNode != null)
                {
			m_rightNode.printNode();
		}
		if (m_leftNode != null)
		{		
			m_leftNode.printNode();
                }
		}

        }

