package kr.ac.gnu.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.ac.gnu.common.dao.DataAccessException;
import kr.ac.gnu.common.sl.ServiceLocator;
import kr.ac.gnu.manager.to.ManagerBean;

public class ManagerDAOImpl implements ManagerDAO{
	private static ManagerDAOImpl instance;
	private ManagerDAOImpl(){}
	public static ManagerDAO getInstance(){
		if(instance==null) instance=new ManagerDAOImpl();
		return instance;
	}

	
	@Override
	public ManagerBean selectManager(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("select * from manager where id=?");
			DataSource dataSource=ServiceLocator.getInstance().getDataSource("jdbc/myoracle");
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			ManagerBean manager=null;
			while(rs.next()){
				manager=new ManagerBean();
				manager.setId(rs.getString("id"));
				manager.setPw(rs.getString("pw"));
			}
			return manager;
		} catch(Exception sqle) {
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			try{
				if(rs!=null){rs.close(); rs=null; }
				if(pstmt!=null){pstmt.close(); pstmt=null; }
				if(con!=null){con.close(); con=null; }
			}catch(Exception e){}		
		}
	}
	
}
