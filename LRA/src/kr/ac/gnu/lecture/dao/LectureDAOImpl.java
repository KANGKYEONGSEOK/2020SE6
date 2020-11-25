package kr.ac.gnu.lecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.ac.gnu.common.dao.DataAccessException;
import kr.ac.gnu.common.sl.ServiceLocator;
import kr.ac.gnu.lecture.to.KeywordBean;
import kr.ac.gnu.lecture.to.LectureBean;

/*  LectureDAO 
 *  Lecture과 관련된 조회, 수정, 생성, 변경이 일어나는 클래스
 *  
 * 
 */
public class LectureDAOImpl implements LectureDAO{
	private static LectureDAOImpl i;
	private LectureDAOImpl(){}
	public static LectureDAO getInstance(){
		if(i==null) i=new LectureDAOImpl();
		return i;
	}

	public List<LectureBean> lectureList(){
		// TODO Auto-generated method stub
		List<LectureBean> v=new ArrayList<LectureBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("select * from Lecture");
			DataSource dataSource=ServiceLocator.getInstance().getDataSource("jdbc/myoracle");
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				LectureBean lecture=new LectureBean();
				lecture.setId(rs.getString("ID"));
				lecture.setKeyword(rs.getString("LECTURE_KEYWORD"));
				lecture.setName(rs.getString("LECTURE_NAME"));
				lecture.setRank(Integer.parseInt(rs.getString("LECTURE_RANK")));
				lecture.setLink(rs.getString("LECUTRN_LINK"));
				lecture.setCancelYN(rs.getString("LECTURE_CANCEL_YN"));
				v.add(lecture);
			}
			return v;
		} catch(Exception sqle) {
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			try{
				if(rs!=null){rs.close(); rs=null; }
				if(pstmt!=null){pstmt.close(); pstmt=null; }
				if(con!=null){con.close(); con=null; }
			}catch(Exception e){}		
		}
	}
	
	@Override
	public  List<LectureBean> findLecture(String lectureKeyword) throws DataAccessException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("select * from LECTURE where LECTURE_KEYWORD = ?");
			DataSource dataSource=ServiceLocator.getInstance().getDataSource("jdbc/myoracle");
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1,lectureKeyword);
			rs = pstmt.executeQuery();
			LectureBean lecture=null;
			List<LectureBean> lectureList = new ArrayList<LectureBean>();
			while(rs.next()){
				lecture=new LectureBean();
				lecture.setId(rs.getString("ID"));
				lecture.setKeyword(rs.getString("LECTURE_KEYWORD"));
				lecture.setName(rs.getString("LECTURE_NAME"));
				lecture.setRank(Integer.parseInt(rs.getString("LECTURE_RANK")));
				lecture.setLink(rs.getString("LECUTRN_LINK"));
				lecture.setCancelYN(rs.getString("LECTURE_CANCEL_YN"));
				lectureList.add(lecture);
			}
			return lectureList;
		} catch(Exception sqle) {
			System.out.println(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			try{
				if(rs!=null){rs.close(); rs=null; }
				if(pstmt!=null){pstmt.close(); pstmt=null; }
				if(con!=null){con.close(); con=null; }
			}catch(Exception e){}		
		}
	}
	@Override
	public List<KeywordBean> getKeywordList(){
		List<KeywordBean> v=new ArrayList<KeywordBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("select * from LECTUREKEYWORD order by LECTURE_KEYWORD");

			DataSource dataSource=ServiceLocator.getInstance().getDataSource("jdbc/myoracle");
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				KeywordBean lecture=new KeywordBean();
				lecture.setId(rs.getString("ID"));
				lecture.setKeyword(rs.getString("LECTURE_KEYWORD"));
				v.add(lecture);
			}
			return v;
		} catch(Exception sqle) {
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			try{
				if(rs!=null){rs.close(); rs=null; }
				if(pstmt!=null){pstmt.close(); pstmt=null; }
				if(con!=null){con.close(); con=null; }
			}catch(Exception e){}		
		}
	}
	
}
