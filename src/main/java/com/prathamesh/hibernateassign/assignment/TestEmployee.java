package com.prathamesh.hibernateassign.assignment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestEmployee {

	/**
	 * PROBLEM STATEMENT: There are 2 databases. 
	 *                    We have to save object of first database into second.
	 * 
	 * SOLUTION  : hsqldb + MySQL
	 *       save object to hsql
	 *       get it in other session and save to MySQL DB
	 *       
	 * @author prathamesh
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Employee e1= new Employee(101,"Prathamesh");
		
		
		Configuration configuration = new Configuration();
		
		// Session Factory for hsqldb
		SessionFactory sf1=configuration.configure("/hibernate.hsqldb.cfg.xml").buildSessionFactory();
		Session s1=sf1.openSession();
		Transaction tr1=s1.beginTransaction();
		s1.save(e1);
		s1.flush();
		tr1.commit();
        
		// Session Factory for mysql
		SessionFactory sf2=configuration.configure("hibernate.mysqldb.cfg.xml").buildSessionFactory();
		Session s2=sf2.openSession();
		Transaction tr2=s2.beginTransaction();
		Employee e2=s1.get(Employee.class, 101);
		s2.save(e2);
		s2.flush();
		tr2.commit();
	}

}

@Entity
@Table
class Employee{
	@Id
	int empId;
	String empName;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + "]";
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
}