package com.points.calculate.inheritance;

import com.points.tree.Node;


public class IntegerNode extends Node<Integer> implements IntegerEval {
	
	public IntegerNode(int num) {
		super(num);
	}

	@Override
	public Integer eval() {
		return this.getData();
	}
	
}
