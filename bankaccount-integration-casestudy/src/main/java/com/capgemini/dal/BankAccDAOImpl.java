package com.capgemini.dal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.capgemini.exceptions.UserNotFoundException;
import com.capgemini.pojo.BankAcc;
import com.capgemini.pojo.CurrentAcc;
import com.capgemini.pojo.SavingsAcc;

public class BankAccDAOImpl implements BankAccDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public void addNewAccount(BankAcc acc) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction(); 
		session.persist(acc);
		 t.commit(); 
		session.close();
		
	}

	public int deleteAccount(int accNo) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction t = session.beginTransaction();  
		Query query=session.createQuery("delete from BankAcc where id='"+accNo+"'"); 
		int ret=query.executeUpdate();
		if((ret==0))
		{
			throw new UserNotFoundException("\n\nUser With this Id Does not exist\n\n");
		}
		//specifying class name (Emp) not tablename  
		 query=session.createQuery("delete from SavingsAcc where id='"+accNo+"'");  
		query.executeUpdate();
	 query=session.createQuery("delete from CurrentAcc where id='"+accNo+"'");  
		query.executeUpdate();
		 t.commit(); 
		session.close();
		return ret;
		
	}

	public void updateAccount(BankAcc acc) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction t = session.beginTransaction(); 
		session.update(acc);
		 t.commit(); 
		session.close();
		
	}

	public List<BankAcc> getAllAccount() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction t = session.beginTransaction();  
		@SuppressWarnings("unchecked")
		List<BankAcc> accountList = session.createQuery("from BankAcc").list();
		 t.commit(); 
		session.close();
		return accountList;
		
	}

	

	public BankAcc getAccount(int accNo) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction t = session.beginTransaction();  
		String hql = "FROM BankAcc B WHERE B.id ='"+accNo+"'";
		Query query = session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List results = query.list();
		BankAcc b=null;
		b=(BankAcc)results.get(0);
		 t.commit(); 
		session.close();
		return b;
		
	}

	public SavingsAcc getSavingsAcc(int accNo) {
		Session session = this.sessionFactory.openSession();
		Transaction t = session.beginTransaction();  
		String hql = "FROM SavingsAcc B WHERE B.id ='"+accNo+"'";
		Query query = session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List results = query.list();
		SavingsAcc b=null;
		b=(SavingsAcc)results.get(0);
		 t.commit(); 
		session.close();
		return b;
	}

	public CurrentAcc getCurrentAcc(int accNo) {
		Session session = this.sessionFactory.openSession();
		Transaction t = session.beginTransaction();  
		String hql = "FROM CurrentAcc B WHERE B.id ='"+accNo+"'";
		Query query = session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List results = query.list();
		CurrentAcc b=null;
		b=(CurrentAcc)results.get(0);
		 t.commit(); 
		session.close();
		return b;
	}
	

}
