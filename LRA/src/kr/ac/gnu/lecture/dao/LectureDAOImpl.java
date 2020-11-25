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
 *  LECTURE 테이블과 관련된 DAO
 */
public class LectureDAOImpl implements LectureDAO{
	private static LectureDAOImpl instance;
	private LectureDAOImpl(){}
	public static LectureDAO getInstance(){
		if(instance==null) {
			instance=new LectureDAOImpl();
		}
		return instance;
	}

	/*
	 * 강의 리스트 조회 메서드
	 * return : 강의리스트 
	 */
	public List<LectureBean> selectLectureList(){
		// TODO Auto-generated method stub
		List<LectureBean> lectureList =new ArrayList<LectureBean>();
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
				lecture.setLectureKeyword(rs.getString("LECTURE_KEYWORD"));
				lecture.setLectureName(rs.getString("LECTURE_NAME"));
				lecture.setLectureRank(Integer.parseInt(rs.getString("LECTURE_RANK")));
				lecture.setLectureLink(rs.getString("LECUTRN_LINK"));
				lecture.setLectureCancelYN(rs.getString("LECTURE_CANCEL_YN"));
				lectureList.add(lecture);
			}
			return lectureList;
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
	
	/*
	 * 강의 검색어에 해당하는 강의 리스트 조회 메서드
	 * return : 강의리스트 
	 */
	@Override
	public  List<LectureBean> selectLecture(String lectureKeyword) throws DataAccessException {
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
				lecture.setLectureKeyword(rs.getString("LECTURE_KEYWORD"));
				lecture.setLectureName(rs.getString("LECTURE_NAME"));
				lecture.setLectureRank(Integer.parseInt(rs.getString("LECTURE_RANK")));
				lecture.setLectureLink(rs.getString("LECUTRN_LINK"));
				lecture.setLectureCancelYN(rs.getString("LECTURE_CANCEL_YN"));
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
	
	/*
	 * 강의 검색어 조회 메서드
	 * return : 강의검색어 리스트 
	 */
	@Override
	public List<KeywordBean> selectKeywordList(){
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
