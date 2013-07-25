package com.project.algorithm.link;

public class Node {
	private Node preChainlinked;
	private Node nextChainlinked;
	private Object property;
	public Node getPreChainlinked() {
		return preChainlinked;
	}
	public void setPreChainlinked(Node preChainlinked) {
		this.preChainlinked = preChainlinked;
	}
	public Node getNextChainlinked() {
		return nextChainlinked;
	}
	public void setNextChainlinked(Node nextChainlinked) {
		this.nextChainlinked = nextChainlinked;
	}
	public Object getProperty() {
		return property;
	}
	public void setProperty(Object property) {
		this.property = property;
	}
}
