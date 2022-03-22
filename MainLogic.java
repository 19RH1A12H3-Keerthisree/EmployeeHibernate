package com.motivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

//import org.hibernate.cfg.Configuration;
//import org.hibernate.classic.Session;


public class MainLogic{

	public static void main(String[] args)
	{
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hib.xml").build();
		Metadata me=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=me.getSessionFactoryBuilder().build();
		Session se=sf.openSession();
		Transaction tx=se.beginTransaction();
		
		Query qr=se.createQuery("select e.employee_name,e.mobile,er.employer_id from Employer er inner join EmployeeN e on er.employer_id=e.employer");
		List li=qr.list();
		Iterator itr=li.iterator();
		while(itr.hasNext()){
			Object[] x =(Object[])itr.next();
			System.out.println(x[0]+"   "+x[1]+"   "+x[2]);
		}
		
		
		
		Employer e1 = new Employer();
		e1.setEmployer_id(3);
		e1.setEmployer_name("Abhi");
		e1.setMobile(987267867L);
		e1.setDepartment("Product Manager");
		
	
	
		EmployeeN emp1=new EmployeeN();
		emp1.setEmployee_id(5);
		emp1.setEmployee_name("Nandini");
		emp1.setMobile(8836546211l);
		emp1.setAddress("Nalgonda");
		emp1.setEmployer(e1);
		
		
		List<EmployeeN> alst1=new ArrayList<EmployeeN>();
		alst1.add(emp1);
		e1.setEmployeeN(alst1);
		
		
		//se.save(e1);
		//se.save(emp1);
		
		tx.commit();
		se.close();
	}

}