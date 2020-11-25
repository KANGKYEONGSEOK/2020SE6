package kr.ac.gnu.lecture.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.gnu.common.servlet.ModelAndView;
import kr.ac.gnu.common.servlet.mvc.Controller;
import kr.ac.gnu.lecture.sf.LectureServiceFacade;
import kr.ac.gnu.lecture.sf.LectureServiceFacadeImpl;
import kr.ac.gnu.lecture.to.LectureBean;

public class LectureInfoController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response){
		// TODO Auto-generated method stub
		 String uri=request.getRequestURI();
		 String contextPath=request.getContextPath();
		 String viewName=uri.split("[.]")[0].substring(contextPath.length());
		 HashMap<String,Object> map=new HashMap<String,Object>();
		ModelAndView mav=null;
	 	try {		
			LectureServiceFacade sf=LectureServiceFacadeImpl.getInstance();
			List<LectureBean> list=sf.getLecture(request.getParameter("lectureKeyword"));
	 		 map.put("lectureList", list);
	 		request.setAttribute("Keyword", request.getParameter("lectureKeyword"));
	 		 mav=new ModelAndView(viewName,map);
	 		 map.put("errorCode", 0);
	 		map.put("errorMsg", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
	 		 map.put("errorCode", -1);
	 		 map.put("errorMsg", e.getMessage());
	 		 mav=new ModelAndView("error",map);
		}	
		return mav;
	}
}
