package com.points.calculate;

import com.points.node.twochild.Node;

public class OperatorNode extends Node<Operator>{
	
	public OperatorNode(Operator op) {
		super(op);
	}
	
	@Override
	public Node evaluate() throws IllegalArgumentException {
		if (getLeftChild() == null && getRightChild() == null ) {
			throw new IllegalArgumentException("Both child nodes are null");
		}
		
		if (getLeftChild() == null ) {
			return getRightChild().evaluate();
		}
		
		if (getRightChild() == null ) {
			return getLeftChild().evaluate();
		}
		
		Node num1 = getLeftChild().evaluate();
		Node num2 = getRightChild().evaluate();
		NumberNode number1;
		NumberNode number2;
		try {
			number1 = (NumberNode) num1;
			number2 = (NumberNode) num2;
		} catch (Exception e) {
			throw new IllegalArgumentException("Result was not a number");
		}
		
		switch (getData()) {
			case ADD:
				return new NumberNode((int)number1.getData() + (int)number2.getData());
			case SUBTRACT:
				return new NumberNode((int)number1.getData() - (int)number2.getData());
			case MULTIPLY:
				return new NumberNode((int)number1.getData() * (int)number2.getData());
			case DIVIDE:
				return new NumberNode((int)number1.getData() / (int)number2.getData());
			default:
				throw new IllegalArgumentException("Operator not found '" + getData() + "'");
		}		
	}
	
}
