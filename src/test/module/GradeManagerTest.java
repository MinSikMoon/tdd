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
		
		//관리자
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2000, ""));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2000, null));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2000, "some prize"));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2499, ""));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2499, null));
		assertEquals(GradeManager.MESSAGE_1, GradeManager.getGradeMessage("관리자",2499, "some prize"));
	}
	
	@Test
	void 메시지2_테스트() {
		//사원
		assertEquals(GradeManager.MESSAGE_2, GradeManager.getGradeMessage("사원",500, ""));
		assertEquals(GradeManager.MESSAGE_2, GradeManager.getGradeMessage("사원",500, "some prize"));
		assertEquals(GradeManager.MESSAGE_2, GradeManager.getGradeMessage("사원",999, ""));
		assertEquals(GradeManager.MESSAGE_2, GradeManager.getGradeMessage("사원",999, "some prize"));
		
		//관리자
		assertEquals(GradeManager.MESSAGE_2, GradeManager.getGradeMessage("관리자",2500, ""));
		assertEquals(GradeManager.MESSAGE_2, GradeManager.getGradeMessage("관리자",2500, "some prize"));
		assertEquals(GradeManager.MESSAGE_2, GradeManager.getGradeMessage("관리자",2999, ""));
		assertEquals(GradeManager.MESSAGE_2, GradeManager.getGradeMessage("관리자",2999, "some prize"));
	}

	@Test
	void 메시지3_테스트() {
		//사원
		assertEquals(GradeManager.MESSAGE_3, GradeManager.getGradeMessage("사원",1000, ""));
		assertEquals(GradeManager.MESSAGE_3, GradeManager.getGradeMessage("사원",1000, "some prize"));
		assertEquals(GradeManager.MESSAGE_3, GradeManager.getGradeMessage("사원",1499, ""));
		assertEquals(GradeManager.MESSAGE_3, GradeManager.getGradeMessage("사원",1499, "some prize"));
		
		//관리자
		assertEquals(GradeManager.MESSAGE_3, GradeManager.getGradeMessage("관리자",3000, ""));
		assertEquals(GradeManager.MESSAGE_3, GradeManager.getGradeMessage("관리자",3000, "some prize"));
		assertEquals(GradeManager.MESSAGE_3, GradeManager.getGradeMessage("관리자",3499, ""));
		assertEquals(GradeManager.MESSAGE_3, GradeManager.getGradeMessage("관리자",3499, "some prize"));
	}
}
