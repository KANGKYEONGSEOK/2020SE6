package kr.ac.gnu.lecture.sf;

import java.util.List;

import kr.ac.gnu.lecture.dao.LectureDAOImpl;
import kr.ac.gnu.lecture.exception.LectureNonExistentException;
import kr.ac.gnu.lecture.to.KeywordBean;
import kr.ac.gnu.lecture.to.LectureBean;



public class LectureServiceFacadeImpl implements LectureServiceFacade{
	private static LectureServiceFacade instance=new LectureServiceFacadeImpl();
	public static LectureServiceFacade getInstance(){
		return instance;
	}
	@Override
	public List<LectureBean> getLectureList() {
		// TODO Auto-generated method stub
		return LectureDAOImpl.getInstance().lectureList();
	}

	@Override
	public List<LectureBean>  getLecture(String lectureKeyword) throws LectureNonExistentException {
		// TODO Auto-generated method stub
		List<LectureBean> lectureBeans = LectureDAOImpl.getInstance().findLecture(lectureKeyword);
		if(lectureBeans==null || lectureBeans.size()<=0) { 
			throw new LectureNonExistentException("존재하지 않는 강의 검색어 입니다.");
		}
		return lectureBeans;
	}
	@Override
	public List<KeywordBean> getKeywordList(){
		List<KeywordBean> keywordBeans = LectureDAOImpl.getInstance().getKeywordList();
		return keywordBeans;
		
	}

}
