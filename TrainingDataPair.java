package gp_project;

public class TrainingDataPair {

	private int xValue;

	private double yValue;

	public int getxValue() {
		return xValue;
	}

	public double getyValue() {
		return yValue;
	}

	public void setxValue(int xValue) {
		this.xValue = xValue;
	}

	public void setyValue(double yValue) {
		this.yValue = yValue;
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append("X:" + xValue + ",");
		result.append("Y:" + yValue);

		return result.toString();
	}
}
