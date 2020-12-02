package kr.ac.gnu.lecture.dao;

import java.sql.Connection;
import java.util.List;

import kr.ac.gnu.common.dao.DataAccessException;
import kr.ac.gnu.lecture.to.KeywordBean;
import kr.ac.gnu.lecture.to.LectureBean;


public interface LectureDAO {
	public List<LectureBean> selectLectureList() throws DataAccessException ;
	public List<LectureBean> selectLectureList(Connection con) throws DataAccessException ;
	public List<LectureBean> selectLecture(String keyword) throws DataAccessException ;
	public List<LectureBean> selectLecture(Connection con,String keyword) throws DataAccessException ;
	public List<KeywordBean> selectKeywordList();
	public Boolean existKeyword(Connection con, String keyword);
}
