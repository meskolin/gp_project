package gp_project;

import java.util.LinkedList;
import java.util.List;

public class Population {

	private List<Tree> m_functions = new LinkedList<Tree>();
	
	public void generateFirstPopulation()
	{
		int numIndividuals = GPConfig.getInstance().getSizePopulation();
		for (int i=0; i< numIndividuals; i++)
		{
			FunctionGenerator fGen = new FunctionGenerator();
			Tree tree = fGen.GenerateFullTree(2);
			m_functions.add(tree);
			
		}
		
	}
	
	List<Tree> getTrees()
	{
		
		return m_functions;
	}
}
