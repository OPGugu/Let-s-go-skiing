package article.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Action;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;

import com.google.gson.Gson;

//import core.HibernateUtil;
import article.dao.ArticleTypeDao;
import article.dao.impl.ArticleTypeDaoImpl;
import article.vo.ArticleType;



// http://localhost:8080/ski/BackendArticleType
@WebServlet("/BackendArticleType")
public class BackendArticleTypeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action=req.getParameter("action");
		String type=req.getParameter("type");
		System.out.println(action);
		String fowardPath="";
		switch(action) {
			case "showAll":
				fowardPath=showAll(req,res);
				break;
			case "addContent":
				fowardPath=addContent(req,res);
				break;
			case "updateContent":
				fowardPath=updateContent(req,res);
				break;
			case "deleteContent":
				fowardPath=deleteContent(req,res);
				break;
			case "deleteContentAndShowAll":
				fowardPath=deleteContentAndShowAll(req,res);
				break;
			case "searchIDAndContent":
				if(type.equals("Number")) {
					fowardPath=searchID(req,res,1);
				}else if(type.equals("String")) {
					fowardPath=searchContent(req,res,"2");
				}
				
				break;
			default:{
				System.out.println("收到未知請求");
				break;
			}
				
			
		}
		
		
	}
	
	private String showAll(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		List<ArticleType> list = new ArrayList<ArticleType>();
		try {
			list = dao.selectAll();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		res.getWriter().write(jsonStr);
		System.out.println("success");
		return jsonStr;
	}
	
	private String addContent(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		try {
			ArticleType articleType1 = new ArticleType(null, req.getParameter("articleTypeContent"));
			dao.insert(articleType1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(dao);
		res.getWriter().write(jsonStr);
		System.out.println("success");
		return jsonStr;
	}
	
	private String updateContent(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		try {
			ArticleType articleType2 = new ArticleType(Integer.parseInt(req.getParameter("articleTypeID")), req.getParameter("articleTypeContent"));
			dao.updateByArticleTypeID(articleType2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(dao);
		res.getWriter().write(jsonStr);
		System.out.println("success");
		return jsonStr;
	}
	
	private String deleteContent(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		try {
			Integer articleType3 = Integer.parseInt(req.getParameter("articleTypeID"));
			dao.deleteByArticleTypeID(articleType3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(dao);
		res.getWriter().write(jsonStr);
		System.out.println("success");
		return jsonStr;
	}
	
	private String deleteContentAndShowAll(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		List<ArticleType> list2 = new ArrayList<ArticleType>();
		try {
			list2 = dao.deleteAllAndInsertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list2);
		res.getWriter().write(jsonStr);
		System.out.println("success");
		return jsonStr;
	}
	
	// 搜尋ID
	private String searchID(HttpServletRequest req,HttpServletResponse res ,Integer type) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		try {
			Integer articleType4 = Integer.parseInt(req.getParameter("articleTypeID"));
			dao.selectByArticleTypeID(articleType4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(dao);
		res.getWriter().write(jsonStr);
		System.out.println("success");
		return jsonStr;
	}
	
	// 搜尋內容
	private String searchContent(HttpServletRequest req,HttpServletResponse res ,String type) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		List<ArticleType> list3 = new ArrayList<ArticleType>();
		try {
			String articleType5 = req.getParameter("articleTypeContent");
			list3 = dao.selectByArticleTypeContent(articleType5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list3);
		res.getWriter().write(jsonStr);
		System.out.println("success");
		return jsonStr;
	}

}
