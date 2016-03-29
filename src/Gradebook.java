import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import customTools.DBUtil;
import model.Assignment;

import java.util.List;
import java.util.Scanner;


public class Gradebook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager em = DBUtil.getEmFactory().createEntityManager(); 
		Scanner sc = new Scanner(System.in);
		int studentID = 0;
		String assignmentType = "";
		int choice1 = 0;
		int choice2 = 0;
		try {
			System.out.println("Choose one: \n\t(1)view assignments \n\t(2)view average \n\t(3)view lowest \n\t(4)view highest");
			choice1 = sc.nextInt();
			sc.nextLine();
			switch(choice1) {
			case 1: System.out.println("Would you like to see \n\t(1)all assignments by one student \n\t(2)all assignments of one type \n\t(3)all assignments by one student of one type");
				choice2 = sc.nextInt();
				sc.nextLine();
				switch(choice2) {
				case 1: System.out.print("What is the student ID?: ");
					studentID = sc.nextInt();
					sc.nextLine();
					viewAssignments(em, studentID);
					break;
				case 2: System.out.print("What is the assignment type?: ");
					assignmentType = "'" + sc.nextLine() + "'";
					viewAssignments(em, assignmentType);
					break;
				case 3:System.out.print("What is the student ID?: ");
					studentID = sc.nextInt();
					sc.nextLine();
					System.out.print("What is the assignment type?: ");
					assignmentType = "'" + sc.nextLine() + "'";
					viewAssignments(em, studentID, assignmentType);
					break;
				}
				break;
			case 2: System.out.println("Would you like to see the average of \n\t(1)all assignments by one student \n\t(2)all assignments of one type \n\t(3)all assignments by one student of one type");
				choice2 = sc.nextInt();
				sc.nextLine();
				switch(choice2) {
				case 1: System.out.print("What is the student ID?: ");
					studentID = sc.nextInt();
					sc.nextLine();
					viewAverage(em, studentID);
					break;
				case 2: System.out.print("What is the assignment type?: ");
					assignmentType = "'" + sc.nextLine() + "'";
					viewAverage(em, assignmentType);
					break;
				case 3:System.out.print("What is the student ID?: ");
					studentID = sc.nextInt();
					sc.nextLine();
					System.out.print("What is the assignment type?: ");
					assignmentType = "'" + sc.nextLine() + "'";
					viewAverage(em, studentID, assignmentType);
					break;
				}
				break;
			case 3: System.out.print("What is the assignment type?: ");
				assignmentType = "'" + sc.nextLine() + "'";
				viewLowest(em, assignmentType);
				break;
			case 4: System.out.print("What is the assignment type?: ");
				assignmentType = "'" + sc.nextLine() + "'";
				viewHighest(em, assignmentType);
				break;
			default: System.out.println("That was not a valid choice.");
				break;
			}
		} catch(java.util.InputMismatchException e) {
			e.getMessage();
		}

		sc.close();
		em.close(); 
	}

	public static void viewAssignments(EntityManager em, int studentID)
	{
		String qString = "SELECT a from Assignment a WHERE a.student.sId = " + studentID;
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
			System.out.println("aID\taType\tgrade");
			for(Assignment task: assignments) {
				System.out.println(task.getAId() + "\t" + task.getAType() + "\t" + task.getGrade());
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public static void viewAssignments(EntityManager em, String assignmentType)
	{
		String qString = "SELECT a from Assignment a WHERE a.aType = " + assignmentType;
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
			System.out.println("aID\taType\tgrade");
			for(Assignment task: assignments) {
				System.out.println(task.getAId() + "\t" + task.getAType() + "\t" + task.getGrade());
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public static void viewAssignments(EntityManager em, int studentID, String assignmentType)
	{
		String qString = "SELECT a from Assignment a WHERE a.student.sId =" + studentID + " AND a.aType = " + assignmentType;
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
			System.out.println("aID\taType\tgrade");
			for(Assignment task: assignments) {
				System.out.println(task.getAId() + "\t" + task.getAType() + "\t" + task.getGrade());
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static void viewAverage(EntityManager em, int studentID)
	{
		String qString = "SELECT a from Assignment a WHERE a.student.sId =" + studentID;
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments;
		double sum = 0;
		int count = 0;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
			for(Assignment task: assignments) {
				sum += task.getGrade().doubleValue();
				count++;
			}
			System.out.println("Average: " + sum/count);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public static void viewAverage(EntityManager em, String assignmentType)
	{
		String qString = "SELECT a from Assignment a WHERE a.aType = " + assignmentType;
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments;
		double sum = 0;
		int count = 0;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
			for(Assignment task: assignments) {
				sum += task.getGrade().doubleValue();
				count++;
			}
			System.out.println("Average: " + sum/count);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public static void viewAverage(EntityManager em, int studentID, String assignmentType)
	{
		String qString = "SELECT a from Assignment a WHERE a.student.sId =" + studentID + " AND a.aType = " + assignmentType;
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments;
		double sum = 0;
		int count = 0;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
			for(Assignment task: assignments) {
				sum += task.getGrade().doubleValue();
				count++;
			}
			System.out.println("Average: " + sum/count);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static void viewLowest(EntityManager em, String assignmentType)
	{
		String qString = "SELECT a from Assignment a WHERE a.aType = " + assignmentType;
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments;
		int lowest = 200;//there should be no grades as high as 200
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
			for(Assignment task: assignments) {
				if(task.getGrade().intValue() < lowest) {
					lowest = task.getGrade().intValue();
				}
			}
			System.out.println("Lowest: " + lowest);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static void viewHighest(EntityManager em, String assignmentType)
	{
		String qString = "SELECT a from Assignment a WHERE a.aType = " + assignmentType;
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments;
		int highest = -1;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
			for(Assignment task: assignments) {
				if(task.getGrade().intValue() > highest) {
					highest = task.getGrade().intValue();
				}
			}
			System.out.println("Highest: " + highest);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}

/*
create table students (
s_id int primary key,
firstname varchar(50),
lastname varchar(50)
);

create table assignments (
a_id int primary key,
a_type varchar(50),
grade int,
s_id int
);
alter table assignments add foreign key (s_id) references students(s_id);

insert into students (s_id, firstname, lastname) values (1, 'Eric', 'Cheng');
insert into students (s_id, firstname, lastname) values (2, 'Daniel', 'Pak');
insert into students (s_id, firstname, lastname) values (3, 'David', 'Shiao');

insert into assignments (a_id, a_type, grade, s_id) values (1, 'quiz', 95, 1);
insert into assignments (a_id, a_type, grade, s_id) values (2, 'quiz', 89, 1);
insert into assignments (a_id, a_type, grade, s_id) values (3, 'project', 90, 1);
insert into assignments (a_id, a_type, grade, s_id) values (4, 'project', 86, 1);
insert into assignments (a_id, a_type, grade, s_id) values (5, 'quiz', 92, 2);
insert into assignments (a_id, a_type, grade, s_id) values (6, 'quiz', 90, 2);
insert into assignments (a_id, a_type, grade, s_id) values (7, 'project', 96, 2);
insert into assignments (a_id, a_type, grade, s_id) values (8, 'project', 88, 2);
insert into assignments (a_id, a_type, grade, s_id) values (9, 'quiz', 98, 3);
insert into assignments (a_id, a_type, grade, s_id) values (10, 'quiz', 94, 3);
insert into assignments (a_id, a_type, grade, s_id) values (11, 'project', 96, 3);
insert into assignments (a_id, a_type, grade, s_id) values (12, 'project', 92, 3);




 */
