package kr.ac.gnu.lecture.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.gnu.common.servlet.ModelAndView;
import kr.ac.gnu.common.servlet.mvc.Controller;
import kr.ac.gnu.lecture.sf.LectureServiceFacade;
import kr.ac.gnu.lecture.sf.LectureServiceFacadeImpl;
import kr.ac.gnu.lecture.to.LectureBean;
import net.sf.json.JSONObject;

public class LectureCancelConetroller implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response){
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<>();
		JSONObject jsonObject=null;
		PrintWriter out=null;
		try{
 			out=response.getWriter();
 		}catch(IOException e) { 
 			throw new RuntimeException("실행중 오류발생"); 
 		}
	 	try {	
			LectureServiceFacade sf=LectureServiceFacadeImpl.getInstance();
			System.out.println("EEEEEEEEEEE"+ request.getParameter("id"));
			sf.lectureCancel(request.getParameter("id"));
	 		map.put("errorCode", 0);
	 		map.put("errorMsg", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
	 		map.put("errorCode", -1);
	 		map.put("errorMsg", e.getMessage());
	 		System.out.println(e.getMessage());
		}
 		jsonObject=JSONObject.fromObject(map);
	 	out.print(jsonObject);
		return null;
	}
}
