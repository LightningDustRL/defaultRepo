package com.andrewhalenkamp;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Manager implements Employee {

	private BigDecimal total;
	private static final BigDecimal ALLOCATION = new BigDecimal(300);
	private Set<Employee> workers = null;
	private String name;
	private Manager manager;

	public Manager(String name) {
		this.name = name;
		this.workers = new HashSet<Employee>();
		this.total = BigDecimal.ZERO;
	}

	public Manager(String name, Manager m) {
		this.name = name;
		this.manager = m;
		workers = new HashSet<Employee>();
		manager.addWorker(this);
		total = BigDecimal.ZERO;
	}

	public Manager(String name, Manager m, Set<Employee> existWorkers) {
		this.name = name;
		this.manager = m;
		workers = existWorkers;
		total = BigDecimal.ZERO;
	}

	public BigDecimal getAllocation() {
		return Manager.ALLOCATION;
	}

	public Set<Employee> getWorkers() {
		return Collections.unmodifiableSet(this.workers);
	}

	public void addWorker(Employee worker) {
		workers.add(worker);
	}

	public Manager getBoss() {
		try {
			return manager;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public BigDecimal getTotalAllocation() {
		total = total.add(ALLOCATION);
		for (Employee emp : workers) {
			total = total.add(emp.getTotalAllocation());
		}
		return total;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		if (manager == null)
			return "Head Manager";
		else
			return "Manager";
	}

}
