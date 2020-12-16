package kr.ac.gnu.manager.exception;

@SuppressWarnings("serial")
public class ManagerNonExistentException extends Exception {
	public ManagerNonExistentException(String msg) {
		super(msg);
	}
}
