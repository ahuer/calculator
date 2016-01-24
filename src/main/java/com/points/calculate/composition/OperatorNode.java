package com.points.calculate.composition;


import com.points.tree.binarytree.BinaryTreeNode;

public class OperatorNode implements IntegerEval {
	private BinaryTreeNode<Operator> node;
	
	@SuppressWarnings("unused")
	private OperatorNode() {}
	
	public OperatorNode(Operator op) {
		node = new BinaryTreeNode<>(op);
	}
	
	@Override
	public int eval() {
		return 0;
	}

	public BinaryTreeNode<Operator> getNode() {
		return node;
	}
}
