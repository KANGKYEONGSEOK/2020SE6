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
	
	
	@Override
	public void login(String id,String pw) throws ManagerNonExistentException,ManagerPwMismatchException{
		// TODO Auto-generated method stub
		ManagerBean bean=getManager(id);
		if(bean==null) throw new ManagerNonExistentException("존재하지 않는 ID입니다.");
		else{
			if(! bean.getPw().equals(pw))
			throw new ManagerPwMismatchException("존재하지 않는 PW입니다.");
			else;
		}
	}
	@Override
	public ManagerBean getManager(String id) {
		// TODO Auto-generated method stub
		return ManagerDAOImpl.getInstance().selectManager(id);
	}
	
}
