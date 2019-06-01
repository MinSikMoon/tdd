package main.module;

public class GradeManager {

	public static final String MESSAGE_1 = "메시지1";
	public static final String MESSAGE_2 = "메시지2";
	public static final String MESSAGE_3 = "메시지3";
	private static double[] employeePoints = {500, 1000, 1500};

	public static Object getGradeMessage(String position, double point, String prize ) {
		if(position.equals("사원")) {
			if(employeePoints [0] <= point && point < employeePoints[1])
				return MESSAGE_2;
			if(employeePoints[1] <= point && point < employeePoints[2])
				return MESSAGE_3;
		}else if(position.equals("관리자")) {
			if(2500 <= point && point < 3000)
				return MESSAGE_2;
			if(3000 <= point && point < 3500)
				return MESSAGE_3;
		}
		return MESSAGE_1;
	}
	
}
