package course.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.dao.impl.CourseDaoImpl;


@WebServlet("/course_DL")
public class DeleteCourseByIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			Integer CourseID = Integer.parseInt(request.getParameter("CourseID"));
			CourseDaoImpl dao = new CourseDaoImpl();
			System.out.println(CourseID);
			int result = dao.deleteByCourseId(CourseID);
			
//			if (result > 0) {
//				// 刪除成功
//				response.getWriter().println("刪除成功");
//			} else {
//				// 刪除失敗
//				response.getWriter().println("刪除失敗");
//			}
			return ;					
	}
}

