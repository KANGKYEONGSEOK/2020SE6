package kr.ac.gnu.lecture.sf;

import java.sql.Connection;
import java.util.List;

import kr.ac.gnu.lecture.exception.LectureNonExistentException;
import kr.ac.gnu.lecture.to.KeywordBean;
import kr.ac.gnu.lecture.to.LectureBean;



public interface LectureServiceFacade {
	public List<LectureBean> getLectureList();
	public List<LectureBean> getLecture(String lectureKeyword)throws LectureNonExistentException;
	public List<KeywordBean> getKeywordList();
}

