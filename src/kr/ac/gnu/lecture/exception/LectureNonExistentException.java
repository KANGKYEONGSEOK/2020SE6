package kr.ac.gnu.lecture.exception;

@SuppressWarnings("serial")
public class LectureNonExistentException extends Exception {
	public LectureNonExistentException(String msg) {
		super(msg);
	}
}
