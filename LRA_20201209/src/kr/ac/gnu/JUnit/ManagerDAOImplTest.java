package kr.ac.gnu.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.jupiter.api.Test;

import kr.ac.gnu.manager.dao.ManagerDAO;
import kr.ac.gnu.manager.dao.ManagerDAOImpl;
import kr.ac.gnu.manager.to.ManagerBean;

class ManagerDAOImplTest {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";	
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String USER = "lectureRecomand";
	private static final String PWD = "lectureRecomand";

	@Test
	void managerLoginTest() throws Exception {
		Class.forName(DRIVER);
		try{
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			ManagerDAO dao=ManagerDAOImpl.getInstance();
			ManagerBean manager = dao.selectManager(conn,"mm");
			assertEquals(manager.getPw(), "pw");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
