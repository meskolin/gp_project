package gp_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Settings {

	private static String crossoverString = "CROSS_PERCENT";
	private static String eliminateString = "ELIMINATE_PERCENT";
	private static String maxDepthString = "MAX_DEPTH";
	private static String mutateString = "MUTATE_PERCENT";
	private static String operandString = "OPERANDS";
	private static String operatorString = "OPERATORS";
	private static String populationString = "POPULATION_SIZE";

	private double crossoverPercent = 0.3;
	private double eliminatePercent = 0.2;
	private int maxDepth = 3;
	private double mutatePercent = 0.2;
	private List<String> operands = new LinkedList<String>(Arrays.asList("x",
			"1", "2"));
	private List<String> operators = new LinkedList<String>(Arrays.asList("-",
			"/", "*"));
	private int populationSize = 100;

	public Settings() {

		parseSettings();
	}

	public double getCrossoverPercent() {
		return crossoverPercent;
	}

	public double getEliminatePercent() {
		return eliminatePercent;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public double getMutatePercent() {
		return mutatePercent;
	}

	public List<String> getOperands() {
		return operands;
	}

	public List<String> getOperators() {
		return operators;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	private void parseOperands(String[] parts) {
		int numEntries = parts.length;

		if (parts[0].equals(operandString)) {
			operands.clear(); // clear out the default values, since there is an
								// entry to read from the config file
		} else {
			throw new RuntimeException("Error while parsing settings file");
		}

		for (int i = 1; i < numEntries; i++) {
			operands.add(parts[i]);
		}
	}

	private void parseOperators(String[] parts) {
		int numEntries = parts.length;

		if (parts[0].equals(operatorString)) {
			operators.clear(); // clear out the default values, since there is
								// an entry to read from the config file
		} else {
			throw new RuntimeException("Error while parsing settings file");
		}

		for (int i = 1; i < numEntries; i++) {
			operators.add(parts[i]);
		}
	}

	public void parseSettings() {
		String line = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("settings.txt"));

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\s");
				if (parts.length < 2) {
					reader.close();
					throw new RuntimeException(
							"Unexpected characters found in training data file");
				}

				if (parts[0].equals(mutateString)) {
					mutatePercent = Double.parseDouble(parts[1]);

				} else if (parts[0].equals(crossoverString)) {
					crossoverPercent = Double.parseDouble(parts[1]);

				} else if (parts[0].equals(eliminateString)) {
					eliminatePercent = Double.parseDouble(parts[1]);

				} else if (parts[0].equals(populationString)) {
					populationSize = Integer.parseInt(parts[1]);

				} else if (parts[0].equals(maxDepthString)) {
					maxDepth = Integer.parseInt(parts[1]);

				} else if (parts[0].equals(operatorString)) {
					parseOperators(parts);

				} else if (parts[0].equals(operandString)) {
					parseOperands(parts);

				} else {
					reader.close();
					throw new RuntimeException(
							"Unknown keyword found in settings file");

				}
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println(ex);
			System.out.println("Exception during settings file read");

		}

	}

	public void setCrossoverPercent(double crossoverPercent) {
		this.crossoverPercent = crossoverPercent;
	}

	public void setEliminatePercent(double eliminatePercent) {
		this.eliminatePercent = eliminatePercent;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public void setMutatePercent(double mutatePercent) {
		this.mutatePercent = mutatePercent;
	}

	public void setOperands(List<String> operands) {
		this.operands = operands;
	}

	public void setOperators(List<String> operators) {
		this.operators = operators;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

}
