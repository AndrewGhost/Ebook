package Session;

import java.util.List;

import DAO.Book;
import DAO.HibernateUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BookList extends ActionSupport{
	
	/**
	 * 查询所有的电子书名
	 * @author Guoxin
	 * @latest 2016/1/4
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String execute(){
		
		 List<Book> list=HibernateUtils.queryAll();		
		 ActionContext.getContext().getValueStack().set("booklist", list);
		 ActionContext.getContext().put("stopQuery", true);	
		// ActionContext.getContext().put("isUploaded", true);  
		 return SUCCESS;		
	}

}
