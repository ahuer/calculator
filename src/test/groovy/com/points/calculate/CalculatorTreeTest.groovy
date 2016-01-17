package com.points.calculate

import static org.junit.Assert.*

import org.junit.Test

class CalculatorTreeTest {
	
	@Test
	public void createCalculatorTreeForSingleNumber() {
		def tree = new CalculatorTree("3")
		assertEquals(3, tree.getTopNode().getData())
	}
	
	@Test
	public void createCalculatorTreeForSymbolReturnsNull() {
		def tree = new CalculatorTree("+")
		assertEquals(null, tree.getTopNode())
	}
	
	@Test
	public void createCalculatorTreeFromSimpleEquation() {
		def tree = new CalculatorTree("3 + 4")
		assertEquals(Operator.ADD, tree.getTopNode().getData())
		assertEquals(3, tree.getTopNode().getLeftChild().getData())
		assertEquals(4, tree.getTopNode().getRightChild().getData())
	}
	
	@Test
	public void createCalculatorTreeForLongerEquation() {
		def tree = new CalculatorTree("3 * 5 - 7 + 4")
		assertEquals(Operator.SUBTRACT, tree.getTopNode().getData())
		def left = tree.getTopNode().getLeftChild()
		def right = tree.getTopNode().getRightChild()
		assertEquals(Operator.MULTIPLY, left.getData())
		assertEquals(Operator.ADD, right.getData())
		assertEquals(3, left.getLeftChild().getData())
		assertEquals(5, left.getRightChild().getData())
		assertEquals(7, right.getLeftChild().getData())
		assertEquals(4, right.getRightChild().getData())
	}
	
	@Test
	public void createCalculatorTreeCreatesUnevenTree() {
		def tree = new CalculatorTree("3 * 5 - 7 + 4 / 2")
		assertEquals(Operator.ADD, tree.getTopNode().getData())
		def left = tree.getTopNode().getLeftChild()
		def right = tree.getTopNode().getRightChild()
		assertEquals(Operator.SUBTRACT, left.getData())
		assertEquals(Operator.DIVIDE, right.getData())
		def leftleft = left.getLeftChild()
		assertEquals(7, left.getRightChild().getData())
		assertEquals(3, leftleft.getLeftChild().getData())
		assertEquals(5, leftleft.getRightChild().getData())
		assertEquals(4, right.getLeftChild().getData())
		assertEquals(2, right.getRightChild().getData())
	}

}
