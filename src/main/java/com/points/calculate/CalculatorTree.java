package com.points.calculate;

import org.apache.commons.lang3.StringUtils;

import com.points.node.twochild.Node;


public class CalculatorTree {
	
	public static int readCalculationString(String calculation) {
		if (StringUtils.isEmpty(calculation) ) {
			return 0;
		}
		
		String[] elements = calculation.split(" ");
		for (String element : elements ) {
			Node node;
			try {
				int num = Integer.parseInt(element);
				node = new NumberNode(num);
			} catch (NumberFormatException ex) {
				node = new OperatorNode(Operator.fromString(element));
			}
			
		}
		return 0;
	}

}
