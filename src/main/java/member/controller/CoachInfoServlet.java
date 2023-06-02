package member.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.service.CoachService;
import member.service.impl.CoachServiceImpl;
import member.vo.Coach;

@WebServlet("/member/coachInfo") // http://localhost:8080/ski/member/coachInfo
public class CoachInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1986650499856082940L;
	private CoachService service;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		service = new CoachServiceImpl();
		gson = new Gson();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求

		// 設置中文編碼
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		// 從 HttpServletRequest 中取得 JSON 字串
		BufferedReader br = req.getReader();
		Coach coach = gson.fromJson(br, Coach.class);
		
		coach = service.coachInfo(coach.getUserID());

		String coachJson = gson.toJson(coach);
		resp.getWriter().write(coachJson);
		resp.getWriter().flush();

	}

}