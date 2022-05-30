package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
	
	//필드
	private static final long serialVersionUID = 1L;
	
	//생성자 (기본생성자 사용)
	//GS
	
	//일반
	//get방식으로 요청시 호출 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//포스트 방식일때
		request.setCharacterEncoding("UTF-8");
		
		//코드작성
		System.out.println("controller");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {	//리스트일때
			//데이터 가져오기
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> phoneList = phoneDao.getPersonList();
			System.out.println(phoneList);
			
			//request에 데이터 추가
			request.setAttribute("pList", phoneList);
			
			//데이터 + html  -->  jsp 시킨다
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			//System.out.println("html");
			
		} else if("writeForm".equals(action)) {	//등록폼일때

			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
		} else if("write".equals(action)) {
			
			//파라미터에서 값 꺼내기(name, hp, company)
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//Vo 만들기
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			//phoneDao.personInsert()를 통해 저장하기
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personInsert(personVo);
			System.out.println(count);
			
			//리다이렉트 리스트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		} else if("delete".equals(action)) {
			
			int personId = Integer.parseInt(request.getParameter("personId"));
			
			PhoneDao phoneDao= new PhoneDao();
			phoneDao.personDelete(personId);
			
			//리다이렉트 리스트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if("updateForm".equals(action)) {
			
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> phoneList = phoneDao.getPersonList();
			request.setAttribute("pList", phoneList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/updateForm.jsp");
			rd.forward(request, response);
			
		} else if("update".equals(action)) {
			
			//파라미터에서 값 꺼내기(name, hp, company)
			int personId = Integer.parseInt(request.getParameter("personId"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//Vo 만들기
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			System.out.println(personVo);
			
			//phoneDao.personInsert()를 통해 저장하기
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personUpdate(personVo);
			System.out.println(count);
			
			//리다이렉트 리스트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		} else {
			System.out.println("action 파라미터 없음"); 
		}
		
	}
	
	//post방식으로 요청시 호출 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기는 post");
		
		doGet(request, response);
	}

}
