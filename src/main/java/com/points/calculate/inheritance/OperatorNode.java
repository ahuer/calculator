package com.points.calculate.inheritance;

import com.points.tree.binarytree.BinaryTreeNode;

public class OperatorNode extends BinaryTreeNode<Operator> implements IntegerEval{
	
	public OperatorNode(Operator op) {
		super(op);
	}
	
	@Override
	public Integer eval() throws IllegalArgumentException {
		if (getLeftChild() == null || getRightChild() == null ) {
			throw new IllegalArgumentException("Operator child nodes cannot be null, left '" 
					+ getLeftChild() + "' right '" + getRightChild() + "'");
		}
		
		Integer number1;
		Integer number2;
		
		try {
			IntegerEval left = (IntegerEval) getLeftChild();
			IntegerEval right = (IntegerEval) getRightChild();
			
			number1 = left.eval();
			number2 = right.eval();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex);
		} catch (Exception ex) {
			throw ex;
		}
		
		switch ((Operator) getData()) {
			case ADD:
				return number1 + number2;
			case SUBTRACT:
				return number1 - number2;
			case MULTIPLY:
				return number1 * number2;
			case DIVIDE:
				return number1 / number2;
			default:
				throw new IllegalArgumentException("Operator not found '" + getData() + "'");
		}		
	}

}
