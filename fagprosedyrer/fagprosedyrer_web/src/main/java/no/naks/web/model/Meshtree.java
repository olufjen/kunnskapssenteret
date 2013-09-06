package no.naks.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeModel;

import edu.unc.ils.mrc.hive2.api.HiveConcept;

/**
 * Meshtree er et modellobjekt som inneholder alle MeSH begreper i en tree map
 * Topp begrepene finnes i en egen ArrayList
 * 
 * @author olj
 *
 */
public class Meshtree  implements Serializable  {

	private	ArrayList<HiveConcept> hiveConcepts; // MeSH begreper i topp node

	private TreeNode rootNode = new TreeNodeBase("MESH","MESH",false);
	private String  nodeTitle = "Node";

	private TreeModelBase treemodelBase;
	private int noofNodes = 0;
	private  Map<String,QName> allConcepts; // Alle MeSH begreper
	
	public Meshtree() {
	
		
	}

	public Meshtree(ArrayList<HiveConcept> hiveConcepts) {
	
		this.hiveConcepts = hiveConcepts;
	}

	public Meshtree(ArrayList<HiveConcept> hiveConcepts,
			Map<String, QName> allConcepts) {
		
		this.hiveConcepts = hiveConcepts;
		this.allConcepts = allConcepts;
		this.noofNodes = this.hiveConcepts.size();
		addNodes(null,rootNode);
	}

	public int getNoofNodes() {
		return noofNodes;
	}

	public void setNoofNodes(int noofNodes) {
		this.noofNodes = noofNodes;
	}

	public TreeModel getTreemodelBase() {
		return treemodelBase;
	}

	public void setTreemodelBase(TreeModelBase treemodelBase) {
		this.treemodelBase = treemodelBase;
	}

	public String getNodeTitle() {
		return nodeTitle;
	}

	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}

	public Map<String, QName> getAllConcepts() {
		return allConcepts;
	}

	public void setAllConcepts(Map<String, QName> allConcepts) {
		this.allConcepts = allConcepts;
	}

	public ArrayList<HiveConcept> getHiveConcepts() {
		return hiveConcepts;
	}

	public void setHiveConcepts(ArrayList<HiveConcept> hiveConcepts) {
		this.hiveConcepts = hiveConcepts;
	}



	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	private void addNodes(String key,TreeNode node){
	//	rootNode = createNode("MESH","MESH",false);
		for (HiveConcept hiveConcept : hiveConcepts ){
			String value = hiveConcept.getPrefLabel();
			String ns = hiveConcept.getQName().getNamespaceURI();
			addNodeToTree(createNode(ns,value,true), rootNode);
		}
		treemodelBase = new TreeModelBase(rootNode);
	boolean leaf = rootNode.isLeaf();
	System.out.println("Meshtree " + rootNode.getDescription());
	
	}
	private TreeNode createNode(String facetName,String nodeText,boolean isLeaf){
		return new TreeNodeBase(facetName,nodeText,isLeaf);
		
	}
	private void addNodeToTree(TreeNode childNode,TreeNode parentNode){
		parentNode.getChildren().add(childNode);
	}



	
}
