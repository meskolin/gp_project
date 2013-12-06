package gp_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GPConfig {

	private static GPConfig instance = null;

	private static List<TrainingDataPair> m_trainingData;

	public static GPConfig getInstance() {
		if (instance == null) {
			instance = new GPConfig();
		}
		return instance;
	}

	private Random m_rnd;

	private Settings m_settings;

	protected GPConfig() {
		m_rnd = new Random(System.currentTimeMillis());
		m_trainingData = parseTrainingData();
		m_settings = new Settings();
	}

	public double getCrossoverPercent() {
		return m_settings.getCrossoverPercent();
	}

	public double getEliminatePercent() {
		return m_settings.getEliminatePercent();
	}

	public int getMaxDepth() {
		return m_settings.getMaxDepth();
	}

	public double getMutatePercent() {
		return m_settings.getMutatePercent();
	}

	public Random getRand() {
		return m_rnd;
	}

	public String getRandOperand() {
		if (m_rnd != null) {
			List<String> operands = m_settings.getOperands();
			int numoperands = operands.size();
			int index = m_rnd.nextInt(numoperands);

			if (index < 0 || index >= numoperands) {
				throw new IllegalArgumentException("INVALID");
			}

			return operands.get(index);
		} else {
			throw new RuntimeException(
					"Internal error: random number seed not initialized");
		}
	}

	public String getRandOperator() {

		if (m_rnd != null) {
			List<String> operators = m_settings.getOperators();
			int numOperators = operators.size();

			int index = m_rnd.nextInt(numOperators);

			if (index < 0 || index >= numOperators) {
				throw new IllegalArgumentException("INVALID");
			}

			String oper = operators.get(index);
			return oper;
		} else {
			throw new RuntimeException(
					"Internal error: random number seed not initialized");
		}
	}

	public String getRandOperatorOrOperand() {

		int flipCoin = m_rnd.nextInt(2);
		if (flipCoin == 1) {
			return getRandOperand();
		} else {
			return getRandOperator();
		}
	}

	public int getSizePopulation() {
		return m_settings.getPopulationSize();

	}

	public List<TrainingDataPair> getTrainingData() {
		return m_trainingData;
	}

	private List<TrainingDataPair> parseTrainingData() {

		List<TrainingDataPair> list = new LinkedList<TrainingDataPair>();
		String line = null;
		BufferedReader reader;
		try {
			// Print out for debugging in case we can't find the training data
			// file
			// System.out.println("Working Directory = " +
			// System.getProperty("user.dir"));
			reader = new BufferedReader(new FileReader("data.txt"));

			while ((line = reader.readLine()) != null) {
				TrainingDataPair pair = new TrainingDataPair();
				String[] parts = line.split("\\s");
				if (parts.length != 2) {
					reader.close();
					throw new RuntimeException(
							"Unexpected characters found in training data file");
				}

				pair.setxValue(Double.parseDouble(parts[0]));
				pair.setyValue(Double.parseDouble(parts[1]));
				list.add(pair);
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println(ex);
			System.out.println("Exception during training data file read");

		}
		return list;
	}

}
