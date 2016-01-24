package com.points.calculate.inheritance

import static org.junit.Assert.*

import org.junit.Test

import com.points.calculate.inheritance.Operator;
import com.points.calculate.inheritance.OperatorNode;

class OperatorNodeTest extends GroovyTestCase {

	@Test
	public void testEnumVal() {
		def node = new OperatorNode(Operator.ADD)
		assertEquals(Operator.ADD, node.getData())
		assertEquals(null, node.getLeftChild())
		assertEquals(null, node.getRightChild())
	}
	
	@Test
	public void testEnumValFromString() {
		def node = new OperatorNode(Operator.fromString("+"))
		assertEquals(Operator.ADD, node.getData())
		assertEquals(null, node.getLeftChild())
		assertEquals(null, node.getRightChild())
	}
	
	@Test
	public void testEnumValFromStringExceptionThrown() {
		def msg = shouldFail {
			def node = OperatorNode(Operator.fromString("@"))
		}
		assertEquals("Symbol provided is not an Operator '@'", msg)
	}
}
