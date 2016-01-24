package com.points.calculate.inheritance;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.points.tree.binarytree.BinaryTreeNode;


public class CalculatorTree {
	
	private BinaryTreeNode rootNode;
	
	public CalculatorTree() {}
	
	public CalculatorTree(String equation) {
		if (!StringUtils.isEmpty(equation) ) {
			rootNode = populateRootNode(equation);
		}		
	}
	
	private BinaryTreeNode populateRootNode(String equation) {	
		String[] elements = equation.split(" ");
		List<BinaryTreeNode> nodeList = new ArrayList<>();
		
		for (String element : elements ) {
			BinaryTreeNode node;
			try {
				int num = Integer.parseInt(element);
				node = new IntegerNode(num);
			} catch (NumberFormatException ex) {
				try {
					node = new OperatorNode(Operator.fromString(element));
				} catch (IllegalArgumentException e) {
					continue;
				}
			}
			nodeList.add(node);			
		}
		
		if (nodeList.size() < 1 ) {
			return null;
		}
		
		if (!nodeList.get(0).getClass().equals(IntegerNode.class) ) {
			return null;
		}
		
		return createTree(nodeList);
	}
	
	private BinaryTreeNode createTree(List<BinaryTreeNode> nodeList) {
		
		if (nodeList.size() == 1 ) {
			return nodeList.get(0);
		}
		
		int middle = nodeList.size() / 2;
		if (middle % 2 == 0 ) {
			middle += 1;
		}
		BinaryTreeNode currentNode = nodeList.get(middle);
		
		currentNode.setLeftChild(createTree(nodeList.subList(0, middle)));
		currentNode.setRightChild(createTree(nodeList.subList(middle + 1, nodeList.size())));
		
		return currentNode;		
	}
	
	public BinaryTreeNode getRootNode() {
		return rootNode;
	}
	
	public int calculate() throws IllegalArgumentException {
		if (rootNode == null ) {
			throw new IllegalArgumentException("topNode is null");
		}
		
		BinaryTreeNode result;
		
		try {
			result = (BinaryTreeNode) rootNode.evaluate();
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}		
		
		if (result.getClass() != IntegerNode.class ) {
			throw new IllegalArgumentException("Result was not a number");
		}
		
		return (int)result.getData(); 
	}

}
