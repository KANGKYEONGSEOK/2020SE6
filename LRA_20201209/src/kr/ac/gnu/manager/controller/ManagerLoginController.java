package kr.ac.gnu.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.gnu.common.servlet.ModelAndView;
import kr.ac.gnu.common.servlet.mvc.Controller;
import kr.ac.gnu.manager.sf.ManagerServiceFacade;
import kr.ac.gnu.manager.sf.ManagerServiceFacadeImpl;
import net.sf.json.JSONObject;

public class ManagerLoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response){
		// TODO Auto-generated method stub
		 
		HashMap<String,Object> map=new HashMap<String,Object>();
		JSONObject jsonObject=null;
		PrintWriter out=null;
		try{
 			out=response.getWriter();
 		}catch(IOException e) { 
 			throw new RuntimeException("시스템 오류입니다."); 
 		}
	 	try {		 		
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			ManagerServiceFacade sf=ManagerServiceFacadeImpl.getInstance();
			sf.login(id,pw);
	 		map.put("errorCode", 0);
	 		map.put("errorMsg", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
	 		 map.put("errorCode", -1);
	 		 map.put("errorMsg", e.getMessage());	 
		}	
	 	jsonObject=JSONObject.fromObject(map); 
	 	out.print(jsonObject);
		return null;
	}
}
