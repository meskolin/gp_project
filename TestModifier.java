package gp_project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import gp_project.*;

import org.junit.Before;
import org.junit.Test;

public class TestModifier {

	private Tree t1;
	private Tree t2;
	private FunctionGenerator fGenerator;
	private FunctionModifier fModifier;
	private FunctionEvaluator fEval;
	
	@Before
	public void setUp() throws Exception {
		fGenerator = new FunctionGenerator();
		fModifier = new FunctionModifier();
		fEval = new FunctionEvaluator();
		t1 = fGenerator.GenerateFullTree(2); 
		t2 = fGenerator.GenerateFullTree(2);
	}

	//Tests that one node in the tree is mutated
	@Test 
	public void testMutate() {
		List<String> original= new ArrayList<String>();
		List<String> mutated = new ArrayList<String>();
		fEval.getPostOrderList(t1.getRootNode(), original);
		fModifier.mutate(t1);
		fEval.getPostOrderList(t1.getRootNode(), mutated);
		
		assertEquals(original.size(), mutated.size());
		int mutatedCount = 0;
		
		for (int i = 0; i < original.size(); i++)
		{
			String originalValue = original.get(i);
			String mutatedValue = mutated.get(i);
			if (!originalValue.equals(mutatedValue))
			{
				mutatedCount ++;
			}
				
		}
		
		assertEquals(mutatedCount, 1);
			
	}


}
