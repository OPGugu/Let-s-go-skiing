package ad.course.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ad.course.service.Impl.CourseAdServiceImpl;
import course.entity.Course;

@WebServlet("/courseAdsearch")
public class SearchServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
//	 public void init() {
			CourseAdServiceImpl cAdService = new CourseAdServiceImpl();

//	}
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 設置跨域
		 resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		 resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		 resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		 resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		 // 設置返回格式
		 req.setCharacterEncoding("UTF-8");
		 resp.setContentType("application/json;charset=UTF-8");
		 
		 String input = req.getParameter("input");

		 if (input != null && !input.isEmpty()) {
		     // 呼叫查詢方法進行相應的處理
		     List<Course> result = cAdService.search(input);

		     resp.setContentType("application/json");
		     Gson gson = new Gson();
		     String json = gson.toJson(result);
		     
		     resp.getWriter().print(json);
		     
		 } else {
		     // 輸入為空值，處理相應的錯誤情況或提示訊息
		     resp.getWriter().print("請輸入關鍵字");
		 }

		 
	}
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
