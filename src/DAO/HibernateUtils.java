package DAO;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sessionfactory;
	private HibernateUtils(){}
	static{
		Configuration cfg=new Configuration().configure();
		sessionfactory=cfg.buildSessionFactory();
	}
    public static Session getSession(){
	   
	   return sessionfactory.openSession();
   }
   //���
    public static void add(Object obj){
    	Session session=null;
    	Transaction ts=null;
    	try{
    		session=HibernateUtils.getSession();
    		ts=session.beginTransaction();
    		session.save(obj);
    		ts.commit();
    	}
    	finally{ 		
    		if(session!=null)
    			session.close();
    		  System.out.println("��Ϣ�Ѳ������ݿ�");
    		  System.out.println("*******************************************"); 
    	}
    }
    //�޸�
    public static void update(Object obj){
    	Session session=null;
    	Transaction ts=null;
    	try{
    		session=HibernateUtils.getSession();
    		ts=session.beginTransaction();
    		session.update(obj);
    		ts.commit();
    	}finally{
    		if(session!=null)
    			session.close();
    	}  	
    }
    //ɾ��
    public static void delete(Object obj){
    	Session session=null;
    	Transaction ts=null;
    	try{
    		session=HibernateUtils.getSession();
    		ts=session.beginTransaction();
    		session.delete(obj);
    		ts.commit();
    	}finally{
    		if(session!=null)
    			session.close();
    	}  	
    }
    //����
    @SuppressWarnings("unchecked")
	public static List queryAll(){
    	Session session=null;
    	List<Book> list;
    	try{
    		session=HibernateUtils.getSession();
    		String hql="from Book";
			Query query=session.createQuery(hql);
		    list=query.list();	
		    for(Book book:list){
				 System.out.println("bookname:"+book.getBookname()+";author:"+book.getAuthor());
			 }
		  
    	}finally{
    		 if(session!=null)
    		 {
    			 session.close();
    			 System.out.println("����ɲ�ѯ�������嵥");
    		 }
    	}
    	return list;
    }//
    
}
