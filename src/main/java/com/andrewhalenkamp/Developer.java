package com.andrewhalenkamp;

import java.math.BigDecimal;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

public class Developer implements Employee {
	final static private BigDecimal ALLOCATION = new BigDecimal(1000);
	private Manager manager;
	private String name;

	public Developer(String name) {
		this.name = name;
	}

	public Developer(String name, Manager boss) {
		this.name = name;
		this.manager = boss;
		this.manager.addWorker(this);
	}

	public Set<Employee> getWorkers() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Developers may not have workers");
	}

	public String getName() {
		return this.name;
	}

	public String getRole() {
		return "Developer";
	}

	public Manager getBoss() throws UnsupportedOperationException {
		if(manager != null)
			return this.manager;
		else
			throw new UnsupportedOperationException("No Manager Assigned");
	}

	public BigDecimal getTotalAllocation() {
		return Developer.ALLOCATION;
	}
}
