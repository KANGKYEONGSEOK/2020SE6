package kr.ac.gnu.manager.sf;

import java.util.List;

import kr.ac.gnu.manager.dao.ManagerDAOImpl;
import kr.ac.gnu.manager.exception.ManagerNonExistentException;
import kr.ac.gnu.manager.exception.ManagerPwMismatchException;
import kr.ac.gnu.manager.to.ManagerBean;

public class ManagerServiceFacadeImpl implements ManagerServiceFacade{
	private static ManagerServiceFacade instance=new ManagerServiceFacadeImpl();
	public static ManagerServiceFacade getInstance(){
		return instance;
	}
	
	/*
	 * 관리자 로그인 메서드 로그인 성공여부를 판단한다.
	 * 아이디와 패스워드의 입력값에 따라 
	 * 익셉션을 던진다. 
	 */
	@Override
	public void login(String id,String pw) throws ManagerNonExistentException,ManagerPwMismatchException{
		// TODO Auto-generated method stub
		ManagerBean bean=getManager(id);
		if(bean==null) {
			throw new ManagerNonExistentException("존재하지 않는 ID입니다.");
		}else {
			if(! bean.getPw().equals(pw)) {
				throw new ManagerPwMismatchException("존재하지 않는 PW입니다.");
			}
		}
	}
	@Override
	public ManagerBean getManager(String id) {
		// TODO Auto-generated method stub
		return ManagerDAOImpl.getInstance().selectManager(id);
	}
	
}
