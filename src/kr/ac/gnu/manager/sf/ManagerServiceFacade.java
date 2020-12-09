package kr.ac.gnu.manager.sf;

import java.util.List;

import kr.ac.gnu.manager.exception.ManagerNonExistentException;
import kr.ac.gnu.manager.exception.ManagerPwMismatchException;
import kr.ac.gnu.manager.to.ManagerBean;


public interface ManagerServiceFacade {
	public void login(String id,String pw) 
			throws ManagerNonExistentException,ManagerPwMismatchException;
	public ManagerBean getManager(String id);
	
}

