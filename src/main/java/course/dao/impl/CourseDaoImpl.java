package course.dao.impl;

import java.util.List;

//import org.hibernate.Session;
import org.hibernate.query.Query;

import course.dao.CourseDao;
import course.entity.Course;
//import product.vo.Product;



	    public class CourseDaoImpl implements CourseDao {
	    	
	        @Override
	    	public int insertCourse(Course course) {
	        	getSession().persist(course);
	        	return 1;
	    	}
	              
	        @Override
	    	public Course getCourseByCourseId(Integer courseId) {
	        	return getSession().get(Course.class, courseId);
	    	}
	        
	        @Override
	    	public List<Course> getAllCourses() {
		        	String hql = "FROM Course";
		        	Query<Course> query = getSession().createQuery(hql, Course.class);
		        	List<Course> resultList = query.getResultList();		        	
		        	return resultList; 
	    	}
	        
	    	@Override
	        public int updateCourse(Course course) {
	    		getSession().merge(course);
	    		return 1;
	    	}
	           
	        @Override
	        public int deleteByCourseId(Integer courseId) {
	        	Course course = getSession().get(Course.class, courseId);
	    		try {
	    			getSession().remove(course);
	    			return 1;
				} catch (Exception e) {				
					e.printStackTrace();
			        System.out.println("deleteByCourseId：" + e.getMessage());
			        return 0;
				}        	
	        }

			@Override
			public List<Course> getCourseByCourseName(String courseName) {
				String hql = "FROM Course WHERE courseName LIKE :courseName";
	        	Query<Course> query = getSession().createQuery(hql, Course.class);
	        	query.setParameter("courseName", "%" + courseName + "%");
	        	List<Course> resultList = query.getResultList();		        	
	        	return resultList; 	        		    
			}

			@Override
			public List<Course> getCourseByTag(String skill, String level) {
			    String hql = "FROM Course WHERE 1 = 1"; // Assuming the entity name is Course

			    if (!skill.isEmpty()) {
			        hql += " AND skill = :skill";
			    }

			    if (!level.isEmpty()) {
			        hql += " AND level = :level";
			    }

			    Query<Course> query = getSession().createQuery(hql, Course.class);

			    if (!skill.isEmpty()) {
			        query.setParameter("skill", skill);
			    }
			    
			    if (!level.isEmpty()) {
			        query.setParameter("level", level);
			    }

			    List<Course> resultList = query.getResultList();
			    return resultList;
			}

//			@Override
//			public int updateCourseValid(Integer courseId) {
//				// TODO Auto-generated method stub
//				return 0;
//			}

	        
	    }

			
	    


