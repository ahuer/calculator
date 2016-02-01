package com.points.calculate.inheritance

import static org.junit.Assert.*

import org.junit.Test

import com.points.tree.binarytree.BinaryTreeNode;

class CalculatorTreeTest {
	
	// Tree Structure
	@Test
	public void createCalculatorTreeForSingleNumber() {
		def tree = new CalculatorTree("3")
		assertEquals(3, tree.getRootNode().getData())
	}
	
	@Test
	public void createCalculatorTreeFromSimpleEquation() {
		def tree = new CalculatorTree("3 + 4")
		def root = (BinaryTreeNode) tree.getRootNode()
		assertEquals(Operator.ADD, root.getData())
		assertEquals(3, root.getLeftChild().getData())
		assertEquals(4, root.getRightChild().getData())
	}
	
	@Test
	public void createCalculatorTreeForLongerEquation() {
		def tree = new CalculatorTree("3 * 5 - 7 + 4")
		assertEquals(Operator.SUBTRACT, tree.getRootNode().getData())
		def left = tree.getRootNode().getLeftChild()
		def right = tree.getRootNode().getRightChild()
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
		assertEquals(Operator.ADD, tree.getRootNode().getData())
		def left = tree.getRootNode().getLeftChild()
		def right = tree.getRootNode().getRightChild()
		assertEquals(Operator.SUBTRACT, left.getData())
		assertEquals(Operator.DIVIDE, right.getData())
		def leftleft = left.getLeftChild()
		assertEquals(7, left.getRightChild().getData())
		assertEquals(3, leftleft.getLeftChild().getData())
		assertEquals(5, leftleft.getRightChild().getData())
		assertEquals(4, right.getLeftChild().getData())
		assertEquals(2, right.getRightChild().getData())
	}
	
	// Evaluate Equations	
	@Test
	public void calculateSingleNumber() {
		def tree = new CalculatorTree("3")
		assertEquals(3, tree.calculate())
	}
	
	@Test
	public void calcuateSimpleEquation() {
		def tree = new CalculatorTree("3 + 4")
		assertEquals(7, tree.calculate())
	}
	
	@Test
	public void calcuateLongerEquation() {
		def tree = new CalculatorTree("3 * 5 - 7 + 4")
		// grouping 7+4 first = 4 instead of 12
		assertEquals(4, tree.calculate())
	}
	
	@Test
	public void calcuateUnevenTreeEquation() {
		def tree = new CalculatorTree("3 * 5 - 7 + 4 / 2")
		assertEquals(10, tree.calculate())
	}

}
