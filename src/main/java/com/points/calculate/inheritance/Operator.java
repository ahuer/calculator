package com.points.calculate.inheritance;

import org.apache.commons.lang3.StringUtils;

public enum Operator {
	ADD("+"),
	SUBTRACT("-"),
	MULTIPLY("*"),
	DIVIDE("/");
	private String op;
	
	private Operator(String op) {
		this.op = op;
	}
	
	public static Operator fromString(String symbol) throws IllegalArgumentException {
		if (StringUtils.isEmpty(symbol) ) {
			throw new IllegalArgumentException("Symbol provided was null or blank");
		}
		
		for (Operator operator : Operator.values() ) {
			if (symbol.equals(operator.op) ) {
				return operator;
			}
		}
		
		throw new IllegalArgumentException("Symbol provided is not an Operator '" + symbol + "'");
	}
}
