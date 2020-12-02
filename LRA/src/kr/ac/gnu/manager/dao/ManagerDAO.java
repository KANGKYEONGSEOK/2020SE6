package kr.ac.gnu.manager.dao;

import java.sql.Connection;
import java.util.List;

import kr.ac.gnu.common.dao.DataAccessException;
import kr.ac.gnu.manager.to.ManagerBean;

public interface ManagerDAO {
	public ManagerBean selectManager(String id) throws DataAccessException ;
	public ManagerBean selectManager(Connection con, String id) throws DataAccessException;
}
