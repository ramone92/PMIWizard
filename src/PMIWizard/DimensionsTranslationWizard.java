package PMIWizard;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nxopen.*;
import nxopen.blockstyler.*;

public class DimensionsTranslationWizard extends PMITranslationWizard
{

	private Tree masterDimensionsTree;
	private ArrayList<String> masterDimensionsList;
	
	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public DimensionsTranslationWizard(Tree masterDimensionsTree) throws NXException, RemoteException
	{
		super.print("DimensionsTranslationWizard created");
		
		setMasterDimensionsTree(masterDimensionsTree);
		//fillTree();
    	
	}
	
	//------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------
		
	private void fillTreeTest() throws RemoteException, NXException
	{
		getMasterDimensionsTree().insertColumn(1, "Имя объекта", 500);
    	Node newNode;
    	for (int i = 0; i < 5; i++)
		{
    		newNode = getMasterDimensionsTree().createNode("dimension node: " + i);
    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);        	
		}
		
	}

	//------------------------------------------------------------------------------
    // Public methods
    //------------------------------------------------------------------------------
	
	public void fillTree() throws RemoteException, NXException
	{
		getMasterDimensionsTree().insertColumn(1, "Имя объекта", 500);
    	Node newNode;
    	for (int i = 0; i < 5; i++)
		{
    		newNode = getMasterDimensionsTree().createNode("dimension node: " + i);
    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);        	
		}
		
	}	

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	
	public Tree getMasterDimensionsTree() throws RemoteException, NXException
	{
		//super.print("getMasterDimensionsTree");
		return masterDimensionsTree;
	}

	public void setMasterDimensionsTree(Tree masterDimensionsTree) throws RemoteException, NXException
	{
		//super.print("setMasterDimensionsTree");
		this.masterDimensionsTree = masterDimensionsTree;
	}

}
