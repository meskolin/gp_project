import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.logging.*;


public class GPConfig {

   private static Logger logger = Logger.getLogger("GPConfig");

   private static GPConfig instance = null;
   protected GPConfig() {
      // Exists only to defeat instantiation.
   }
   public static GPConfig getInstance() {
      if(instance == null) {
         instance = new GPConfig();
      }
      return instance;
   }

   public String getRandOperator()
	{
	logger.log(Level.INFO, "getRandOperator");
	
	try{
	Handler handler = new FileHandler("test.log");
	Logger.getLogger("GPConfig").addHandler(handler);
	}
	catch(java.io.IOException ex)
	{	
		logger.log(Level.WARNING, "Exception opening log file", ex);
	}
	//todo read this in
	Random rnd = new Random(System.currentTimeMillis());
	if (rnd != null)
	{
		List<String> operators = Arrays.asList("+", "-", "/", "*");
		int numOperators = operators.size();
		logger.log(Level.INFO, "num operators: %s\n", numOperators);
		int index = rnd.nextInt(numOperators-1);
		logger.log(Level.INFO, "rand num: %s\n", index);
		
		if(index < 0 || index >= numOperators)
		{
		logger.log(Level.INFO, "invalid index: %s\n", index);
		}
		
		String oper = operators.get(index);	
		return oper;
	}
	
	return "0";		
	}

   public String getRandOperand()
	{
	System.out.println("---------getRandOperand---------\n");
	
		//todo read this in
		Random rnd = new Random(System.currentTimeMillis());

		List<String> operands = Arrays.asList("0", "1", "2", "3");
		int numoperands = operands.size();
		int index = rnd.nextInt(numoperands - 1);
		return operands.get(index);
	}
}
