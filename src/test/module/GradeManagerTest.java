package test.module;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.module.GradeManager;

class GradeManagerTest {

	@Test
	void 메시지1_테스트() {
		//사원
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("사원",0, ""));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("사원",0, null));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("사원",0, "some prize"));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("사원",499, ""));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("사원",499, null));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("사원",499, "some prize"));
		
		//사원
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2000, ""));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2000, null));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2000, "some prize"));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2499, ""));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2499, null));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2499, "some prize"));
	}

}
