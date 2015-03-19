package com.andrewhalenkamp;

import java.math.BigDecimal;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

public class QATester implements Employee {
	private static final BigDecimal ALLOCATION = new BigDecimal(500);
	final private String name;
	private Manager manager;

	public QATester(String name) {
		this.name = name;
	}

	public QATester(String name, Manager m) {
		this.name = name;
		this.manager = m;
		this.manager.addWorker(this);
	}

	public Set<Employee> getWorkers() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("QA Testers may not have workers");
	}

	public String getName() {
		return this.name;
	}

	public String getRole() {
		return "QA Tester";
	}

	public Manager getBoss() throws UnsupportedOperationException {
		if(manager != null)
			return manager;
		else
			throw new UnsupportedOperationException("No Manager Assigned");
	}

	public BigDecimal getTotalAllocation() {
		return QATester.ALLOCATION;
	}
}
