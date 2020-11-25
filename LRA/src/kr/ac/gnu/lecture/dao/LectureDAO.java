package kr.ac.gnu.lecture.dao;

import java.util.List;

import kr.ac.gnu.common.dao.DataAccessException;
import kr.ac.gnu.lecture.to.KeywordBean;
import kr.ac.gnu.lecture.to.LectureBean;


public interface LectureDAO {
	public List<LectureBean> selectLectureList() throws DataAccessException ;
	public List<LectureBean> selectLecture(String id) throws DataAccessException ;
	public List<KeywordBean> selectKeywordList();
}
