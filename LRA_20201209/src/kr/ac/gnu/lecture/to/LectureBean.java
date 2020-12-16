package kr.ac.gnu.lecture.to;

/*
 * Lecture 정보를 담는 class
 * 칼럼 : 아이디, 강의키워드, 강의이름, 강의링크, 강의순위, 강의취소여부
 */
public class LectureBean {
	private String id;
	private String lectureKeyword;
	private String lectureName;
	private String lectureLink;
	private String lectureRank;
	private String lectureCancelYN;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLectureKeyword() {
		return lectureKeyword;
	}
	public void setLectureKeyword(String lectureKeyword) {
		this.lectureKeyword = lectureKeyword;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getLectureLink() {
		return lectureLink;
	}
	public void setLectureLink(String lectureLink) {
		this.lectureLink = lectureLink;
	}
	public String getLectureRank() {
		return lectureRank;
	}
	public void setLectureRank(String lectureRank) {
		this.lectureRank = lectureRank;
	}
	public String getLectureCancelYN() {
		return lectureCancelYN;
	}
	public void setLectureCancelYN(String lectureCancelYN) {
		this.lectureCancelYN = lectureCancelYN;
	}
	@Override
	public String toString() {
		return "LectureBean [id=" + id + ", lectureKeyword=" + lectureKeyword + ", lectureName=" + lectureName
				+ ", lectureLink=" + lectureLink + ", lectureRank=" + lectureRank + ", lectureCancelYN="
				+ lectureCancelYN + "]";
	}
	
	
	
	
	
	
	
	
	
}

