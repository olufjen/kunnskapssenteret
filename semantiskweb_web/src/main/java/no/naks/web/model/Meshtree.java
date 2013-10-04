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
	private ArrayList<HiveConcept> antropologi;
	private ArrayList<HiveConcept> choseConcepts;
	

	private TreeNode rootNode = new TreeNodeBase("MESH","MESH",false);
	private String  nodeTitle = "Node";
	private String searchNode ="term";
	private TreeModelBase treemodelBase;
	private int noofNodes = 10;
	private int totalNodes = 0;
	private HiveConcept chosenConcept;
	private SemantiskTree chosenTree;
	private  Map<String,QName> allConcepts; // Alle MeSH begreper
	private String[] treeElements;
	private ArrayList<SemantiskTree> trees;
	private String[] anatomi; // Inneholder øverste nivå definerte MeSH termer for anatomi
	
	
	public Meshtree() {
	
		
	}

	public Meshtree(ArrayList<HiveConcept> hiveConcepts) {
	
		this.hiveConcepts = hiveConcepts;
	}

	public Meshtree(ArrayList<HiveConcept> hiveConcepts,
			Map<String, QName> allConcepts) {
		
		this.hiveConcepts = hiveConcepts;
		this.allConcepts = allConcepts;
		this.totalNodes = this.hiveConcepts.size();
		addNodes(null,rootNode);

	}

	public SemantiskTree getChosenTree() {
		return chosenTree;
	}

	public void setChosenTree(SemantiskTree chosenTree) {
		this.chosenTree = chosenTree;
	}

	public ArrayList<HiveConcept> getChoseConcepts() {
		return choseConcepts;
	}

	public void setChoseConcepts(ArrayList<HiveConcept> choseConcepts) {
		this.choseConcepts = choseConcepts;
	}

	public String[] getAnatomi() {
		return anatomi;
	}

	public void setAnatomi(String[] anatomi) {
		this.anatomi = anatomi;
		addTreeNodes(treeElements[0]);
	}

	public String[] getTreeElements() {
		return treeElements;
	}

	public void setTreeElements(String[] treeElements) {
		this.treeElements = treeElements;
		int size = this.treeElements.length;
		trees = new ArrayList<SemantiskTree>(size);
		int i = 0;
		
		for (i = 0;i<size;i++ ){
			SemantiskTree tree = new SemantiskTree();
			tree.setBaseName(this.treeElements[i]);
			trees.add(tree);
			
		}
	}

	public ArrayList<SemantiskTree> getTrees() {
		return trees;
	}

	public void setTrees(ArrayList<SemantiskTree> trees) {
		this.trees = trees;
	}

	public HiveConcept getChosenConcept() {
		return chosenConcept;
	}

	public void setChosenConcept(HiveConcept chosenConcept) {
		this.chosenConcept = chosenConcept;
	}

	public int getTotalNodes() {
		return totalNodes;
	}

	public void setTotalNodes(int totalNodes) {
		this.totalNodes = totalNodes;
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
	public String getSearchNode() {
		return searchNode;
	}

	public void setSearchNode(String searchNode) {
		this.searchNode = searchNode;
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
//	System.out.println("Meshtree " + rootNode.getDescription());
	
	}
	private TreeNode createNode(String facetName,String nodeText,boolean isLeaf){
		return new TreeNodeBase(facetName,nodeText,isLeaf);
		
	}
	private void addNodeToTree(TreeNode childNode,TreeNode parentNode){
		parentNode.getChildren().add(childNode);
	}

	/**
	 * addTreeNodes
	 * Denne rutinen bygger opp en trestruktur av MeSH termer på øverste nivå 
	 * @param base Benevning øverste nivå
	 */
	private void addTreeNodes(String base){
		int i = 0;
		SemantiskTree tree = null;
		String baseName = "";
		do
		{
			tree = trees.get(i);
			baseName = tree.getBaseName();
			i++;
		}while(i<trees.size() && !baseName.equals(base));
		int j = 0;
		for (HiveConcept hiveConcept : hiveConcepts ){
			 
			List<String> altLabels = hiveConcept.getAltLabels();
			if (altLabels != null && altLabels.size() > 0){
				boolean found = false;
				do
				{
					String altLabel = altLabels.get(j);
					for (int k = 0;k< anatomi.length;k++){
						if (altLabel.startsWith(anatomi[k]) && !found){
							tree.getHiveConcepts().add(hiveConcept);
							found = true;
						}
					}
					j++;
				}while(j<altLabels.size());
				j = 0;
				found = false;
			}
		}
	}


	
}
