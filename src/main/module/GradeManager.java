package main.module;

public class GradeManager {

	public static final String MESSAGE_1 = "메시지1";
	public static final String MESSAGE_2 = "메시지2";
	public static final String MESSAGE_3 = "메시지3";

	public static Object getGradeMessage(String position, double point, String prize ) {
		if(position.equals("사원")) {
			if(500 <= point && point < 1000)
				return MESSAGE_2;
			if(1000 <= point && point < 1500)
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
