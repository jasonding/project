package com.project.algorithm.link;

import java.util.List;

public class ChainlinkedList {
	private List<Node> linkedList;
	
	public Node get(int i) {
		if(linkedList != null) {
			Node chainlinked = linkedList.get(i);
			return chainlinked;
		}else {
			return null;
		}
	}
	
	public void insert(Node newNode,int pos) {
		Node oldNode = this.get(pos);
		Node oldPreNode = oldNode.getPreChainlinked();
		
		newNode.setPreChainlinked(oldPreNode);
		newNode.setNextChainlinked(oldNode);
		
		oldPreNode.setPreChainlinked(newNode);
	}
	
	public void delete(int pos) {
		Node oldNode = this.get(pos);
		Node oldPreNode = oldNode.getPreChainlinked();
		Node oldNextNode = oldNode.getNextChainlinked();
		
		oldPreNode.setNextChainlinked(oldNextNode);
		oldNextNode.setPreChainlinked(oldPreNode);
		
		
	}
}
