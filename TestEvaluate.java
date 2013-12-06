package gp_project;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestEvaluate {

	static FunctionEvaluator m_fEval;
	@Before
	public void setUp() throws Exception {
		m_fEval = new FunctionEvaluator();
	}

	@Test
	public void testEvaluateSimplePostOrderList() {
		List<String> postOrderList = Arrays.asList("2", "3", "*"); 
		double xValue = 0.0; //doesn't really matter since x is not in this tree
		EvaluationResult result = m_fEval.evaluate(postOrderList, xValue);
		assertEquals(result.yValue, 6, 0);
	}
	
	@Test
	public void testEvaluatePostOrderList() {
		List<String> postOrderList = Arrays.asList("3","2","*","6","2","/","+"); 
		double xValue = 0.0; //doesn't really matter since x is not in this tree
		EvaluationResult result = m_fEval.evaluate(postOrderList, xValue);
		assertEquals(result.yValue, 9, 0);
	}
	
	@Test
	public void testEvaluatePostOrderListWithVariable() {
		List<String> postOrderList = Arrays.asList("3","x","*","6","x","/","+"); 
		double xValue = 2.0;
		EvaluationResult result = m_fEval.evaluate(postOrderList, xValue);
		assertEquals(result.yValue, 9, 0);
	}
	
	
	@Test
	public void testGetOperator() 
	{
		assertEquals(FunctionEvaluator.getOperator("+"), OperatorType.ADD);
		assertEquals(FunctionEvaluator.getOperator("-"), OperatorType.SUBTRACT);
		assertEquals(FunctionEvaluator.getOperator("/"), OperatorType.DIVIDE);
		assertEquals(FunctionEvaluator.getOperator("*"), OperatorType.MULTIPLY);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidOperator()
	{
		FunctionEvaluator.getOperator("%");
		
	}

}
