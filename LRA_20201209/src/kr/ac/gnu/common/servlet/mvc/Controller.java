package kr.ac.gnu.common.servlet.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.gnu.common.servlet.ModelAndView;

public interface Controller {
	ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)throws IOException;
}
