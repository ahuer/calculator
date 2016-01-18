package com.points.calculate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.points.node.twochild.Node;


public class CalculatorTree {
	
	private Node topNode;
	
	public CalculatorTree() {
		topNode = null;
	}
	
	public CalculatorTree(String equation) {
		if (StringUtils.isEmpty(equation) ) {
			topNode = null;
		} else {
			topNode = createCalculatorTreeFromString(equation);
		}		
	}
	
	private static Node createCalculatorTreeFromString(String equation) {	
		String[] elements = equation.split(" ");
		List<Node> nodeList = new ArrayList<>();
		
		for (String element : elements ) {
			Node node;
			try {
				int num = Integer.parseInt(element);
				node = new NumberNode(num);
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
		
		if (!nodeList.get(0).getClass().equals(NumberNode.class) ) {
			return null;
		}
		
		return createTree(nodeList);
	}
	
	private static Node createTree(List<Node> nodeList) {
		
		if (nodeList.size() == 1 ) {
			return nodeList.get(0);
		}
		
		int middle = nodeList.size() / 2;
		if (middle % 2 == 0 ) {
			middle += 1;
		}
		Node currentNode = nodeList.get(middle);
		
		currentNode.setLeftChild(createTree(nodeList.subList(0, middle)));
		currentNode.setRightChild(createTree(nodeList.subList(middle + 1, nodeList.size())));
		
		return currentNode;		
	}
	
	public Node getTopNode() {
		return topNode;
	}
	
	public int calculate() throws IllegalArgumentException {
		if (topNode == null ) {
			throw new IllegalArgumentException("topNode is null");
		}
		
		Node result;
		
		try {
			result = topNode.evaluate();
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}		
		
		if (result.getClass() != NumberNode.class ) {
			throw new IllegalArgumentException("Result was not a number");
		}
		
		return (int)result.getData(); 
	}

}
