package com.andrewhalenkamp;

import java.math.BigDecimal;

public interface Employee {

	public Manager getBoss() throws UnsupportedOperationException;

	public String getName();

	public String getRole();
	
	public BigDecimal getTotalAllocation();
}