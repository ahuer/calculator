package com.points.calculate.composition;

import com.points.tree.binarytree.BinaryTreeNode;

public class IntegerNode implements IntegerEval {
	private BinaryTreeNode<Integer> node;
	
	@SuppressWarnings("unused")
	private IntegerNode() {}
	
	public IntegerNode(int value) {
		node = new BinaryTreeNode<>(value);
	}

	@Override
	public int eval() {
		return 0;
	}
	
	public BinaryTreeNode<Integer> getNode() {
		return node;
	}

}
