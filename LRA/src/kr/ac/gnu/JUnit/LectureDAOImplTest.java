package kr.ac.gnu.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.jupiter.api.Test;
import kr.ac.gnu.lecture.dao.LectureDAO;
import kr.ac.gnu.lecture.dao.LectureDAOImpl;
import kr.ac.gnu.lecture.to.LectureBean;


class LectureDAOImplTest {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";	
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String USER = "lectureRecomand";
	private static final String PWD = "lectureRecomand";

	@Test
	public void getAllLectureListSize() throws Exception{
		Class.forName(DRIVER);
		try{
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			System.out.println(conn);
			LectureDAO dao=LectureDAOImpl.getInstance();
			List<LectureBean> list=dao.selectLectureList(conn);
			assertEquals(10, list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void getKeywordLectureListSize() throws Exception{
		Class.forName(DRIVER);
		try{
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			LectureDAO dao=LectureDAOImpl.getInstance();
			List<LectureBean> list=dao.selectLecture(conn,"영어");
			assertEquals(5, list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void getExistKeyword() throws Exception{
		Class.forName(DRIVER);
		try{
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			LectureDAO dao=LectureDAOImpl.getInstance();
			Boolean bool = dao.existKeyword(conn,"수학");
			assertTrue(bool);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	


}
