package com.points.calculate.inheritance;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.points.tree.Node;
import com.points.tree.binarytree.BinaryTreeNode;


public class CalculatorTree {
	
	private IntegerEval rootNode;
	
	public CalculatorTree() {}
	
	public CalculatorTree(String equation) {
		if (!StringUtils.isEmpty(equation) ) {
			rootNode = populateRootNode(equation);
		}		
	}
	
	private IntegerEval populateRootNode(String equation) {	
		String[] elements = equation.split(" ");
		List<IntegerEval> nodeList = new ArrayList<>();
		
		for (String element : elements ) {
			IntegerEval node;
			try {
				int num = Integer.parseInt(element);
				node = new IntegerNode(num);
			} catch (NumberFormatException ex) {
				try {
					node = new OperatorNode(Operator.valueOfString(element));
				} catch (IllegalArgumentException e) {
					throw e;
				}
			}
			nodeList.add(node);			
		}
				
		return generateCalculatorTreeRootNode(nodeList);
	}
	
	private IntegerEval generateCalculatorTreeRootNode(List<IntegerEval> nodeList) {
		
		if (nodeList.size() == 1 ) {
			return nodeList.get(0);
		}
		
		int middle = nodeList.size() / 2;
		if (middle % 2 == 0 ) {
			middle += 1;
		}
		BinaryTreeNode currentNode = (BinaryTreeNode) nodeList.get(middle);
		
		currentNode.setLeftChild((Node) generateCalculatorTreeRootNode(nodeList.subList(0, middle)));
		currentNode.setRightChild((Node) generateCalculatorTreeRootNode(nodeList.subList(middle + 1, nodeList.size())));
		
		return (IntegerEval) currentNode;		
	}
	
	public IntegerEval getRootNode() {
		return rootNode;
	}
	
	public Integer calculate() throws IllegalArgumentException {
		if (rootNode == null ) {
			throw new IllegalArgumentException("rootNode is null");
		}
		
		Integer result;
		
		try {
			result = rootNode.eval();
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}	
		
		return result; 
	}

}
