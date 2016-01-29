package com.points.calculate.inheritance;

import java.math.BigDecimal;

import com.points.tree.binarytree.BinaryTreeNode;

public class OperatorNode extends BinaryTreeNode<Operator> implements IntegerEval{
	
	public OperatorNode(Operator op) {
		super(op);
	}
	
	@Override
	public BigDecimal eval() throws IllegalArgumentException {
		if (getLeftChild() == null && getRightChild() == null ) {
			throw new IllegalArgumentException("Both child nodes are null");
		}
		
		if (getLeftChild() == null ) {
			return (BinaryTreeNode<Operator>) getRightChild().evaluate();
		}
		
		if (getRightChild() == null ) {
			return (BinaryTreeNode<Operator>) getLeftChild().evaluate();
		}
		
		BinaryTreeNode num1 = (BinaryTreeNode) getLeftChild().evaluate();
		BinaryTreeNode num2 = (BinaryTreeNode) getRightChild().evaluate();
		IntegerNode number1;
		IntegerNode number2;
		try {
			number1 = (IntegerNode) num1;
			number2 = (IntegerNode) num2;
		} catch (Exception e) {
			throw new IllegalArgumentException("Result was not a number");
		}
		
		switch ((Operator) getData()) {
			case ADD:
				return new IntegerNode((int)number1.getData() + (int)number2.getData());
			case SUBTRACT:
				return new IntegerNode((int)number1.getData() - (int)number2.getData());
			case MULTIPLY:
				return new IntegerNode((int)number1.getData() * (int)number2.getData());
			case DIVIDE:
				return new IntegerNode((int)number1.getData() / (int)number2.getData());
			default:
				throw new IllegalArgumentException("Operator not found '" + getData() + "'");
		}		
	}

}
