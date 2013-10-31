package gp_project;

import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.logging.*;

public class GPConfig {

	private static Logger logger = Logger.getLogger("GPConfig");

	private static GPConfig instance = null;

	private Random m_rnd;
	
	protected GPConfig() {
		// Exists only to defeat instantiation.
		 m_rnd = new Random(System.currentTimeMillis());
	}

	public static GPConfig getInstance() {
		if (instance == null) {
			instance = new GPConfig();
		}
		return instance;
	}

	public String getRandOperator() {
	
	
		
		if (m_rnd != null) {
			
			// todo read this in
			List<String> operators = Arrays.asList("+", "-", "/", "*");
			int numOperators = operators.size();
	
			int index = m_rnd.nextInt(numOperators - 1);
	

			if (index < 0 || index >= numOperators) {
				throw new IllegalArgumentException("INVALID");
			}

			String oper = operators.get(index);
			return oper;
		}

		return "0";
	}

	public String getRandOperand() {
		System.out.println("---------getRandOperand---------\n");

		// todo read this in

		List<String> operands = Arrays.asList("0", "1", "2", "3");
		int numoperands = operands.size();
		int index = m_rnd.nextInt(numoperands - 1);
		return operands.get(index);
	}
}
