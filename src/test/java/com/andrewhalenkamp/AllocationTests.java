package com.andrewhalenkamp;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.andrewhalenkamp.Developer;
import com.andrewhalenkamp.Manager;
import com.andrewhalenkamp.QATester;

public class AllocationTests {

	@Test
	public void givenExample() {
		/*
		 * Scenario 1: Example given in Problem Statement
		 * Manager->Manager-> QA, Dev			
		 */
		Manager tOpdawg = new Manager("T. Opdawg");
		Manager normManager = new Manager("Norm M. Anager", tOpdawg);
		QATester midQA = new QATester("Mr. QA", normManager);
		Developer topDev = new Developer("Joe Dev", normManager);

		assertEquals(new BigDecimal(2100), tOpdawg.getTotalAllocation());
	}

	@Test
	public void simpleManagerTest(){
		/*
		 * Scenario 2: Simple Manager Tree
		 * Manager->Manager->Manager
		 */
		Manager tOpdawg = new Manager("T. Opdawg");
		Manager normManager = new Manager("Norm M. Anager", tOpdawg);
		Manager lOwerman = new Manager("L. Owerman", normManager);
		
		assertEquals(new BigDecimal(900), tOpdawg.getTotalAllocation());
	}
	
	@Test
	public void managerCopiesAllocTest() {
		/*
		 * Scenario 3: new Managers declared as parameters
		 * Manager->Manager->Manager->Manager->Manager->Manager
		 */
		Manager tOpdawg = new Manager("T. Opdawg");
		Manager normManager = new Manager("Norm M. Anager", tOpdawg);
		new Manager("L. Owerman", new Manager("L. Owerman", new Manager(
				"L. Owerman", new Manager("L. Owerman", normManager))));

		assertEquals(new BigDecimal(1800), tOpdawg.getTotalAllocation());
	}

	@Test
	public void complexDepartmentAllocTests() {
		/*
		 * Scenario 4: Complex worker tree with multiple managers and workers under each
		 * Manager-> QA, Tester, Dev, Dev, Manager-> QA, QA, Dev, Dev, Dev
		 */
		Manager tOpdawg = new Manager("T. Opdawg");
		Manager normManager = new Manager("Norm M. Anager", tOpdawg);
		QATester midQA = new QATester("Mr. QA", tOpdawg);
		Developer topDev = new Developer("Tom Dev", tOpdawg);
		Developer topDev2 = new Developer("Tim Dev", tOpdawg);
		QATester lowQA1 = new QATester("Lowe Quinn Armor", normManager);
		QATester lowQA2 = new QATester("Small QA", normManager);
		Developer jrDev1 = new Developer("John Dev", normManager);
		Developer jrDev2 = new Developer("Jim Dev", normManager);
		Developer jrDev3 = new Developer("Joe Dev", normManager);

		assertEquals(new BigDecimal(7100), tOpdawg.getTotalAllocation());
	}
	
	@Test
	public void addedWorkersAllocTests(){
		/*
		 * Scenario 5: Workers added post-construction
		 * Manager -> Manager -> Dev, QA
		 */
		Manager tOpdawg = new Manager("T. Opdawg");
		Manager manRam = new Manager("Manny Ramierez");
		Manager lowe = new Manager("Rob Lowe");		
		Developer jrDev1 = new Developer("John Dev");
		
		lowe.addWorker(jrDev1);
		tOpdawg.addWorker(manRam);
		tOpdawg.addWorker(lowe);
		
		assertEquals(new BigDecimal(1900), tOpdawg.getTotalAllocation());
	}
	
	@Test
	public void existingSetTest(){
		/*
		 * Scenario 6: Workers are added with the Constructor that passes the worker set as an argument
		 * Manager-> Manager, Dev, Dev
		 */
		Set<Employee> existWorkers = new HashSet<Employee>();
		existWorkers.add(new Manager("NormManager"));
		existWorkers.add(new Developer("Joe Dev"));
		existWorkers.add(new Developer("John Dev"));		
		Manager tOpdawg = new Manager("T. Opdawg", null, existWorkers);
		
		assertEquals(new BigDecimal(2600), tOpdawg.getTotalAllocation());
	}
}
