package main.module;

public class GradeManager {

	public static final String MESSAGE_1 = "메시지1";
	public static final String MESSAGE_2 = "메시지2";
	public static final String MESSAGE_3 = "메시지3";
	private static double[] employeePoints = { 0, 500, 1000, 1500 };
	private static double[] managerPoints = { 0, 2500, 3000, 3500 };
	private static String[] messages = { MESSAGE_1, MESSAGE_2, MESSAGE_3 };

	public static Object getGradeMessage(String position, double point, String prize) {
		String resultMessage = messages[0];
		double[] points = getPointsArrayByPosition(position);
		for (int i = 0; i < points.length - 1; i++) {
			if (isBetween(point, points[i], points[i + 1]))
				resultMessage = messages[i];
		}
		return resultMessage;
	}

	private static double[] getPointsArrayByPosition(String position) {
		return position.equals("사원") ? employeePoints : managerPoints;
	}

	private static boolean isBetween(double point, double sameAndUpperThan, double belowThan) {
		return (sameAndUpperThan <= point && point < belowThan) ? true : false;
	}

}
