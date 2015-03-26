package PMIWizard;

import java.rmi.RemoteException;

import nxopen.*;
import nxopen.blockstyler.*;

public class DimensionsTranslationWizard extends PMITranslationWizard
{

	private Tree masterDimensionsTree;
	
	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public DimensionsTranslationWizard(Tree masterDimensionsTree) throws NXException, RemoteException
	{
		setMasterDimensionsTree(masterDimensionsTree);
		// TODO throws nullPointerException
		//fillTree();
    	
	}
	
	//------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------
	
	private void fillTree() throws RemoteException, NXException
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
	
	public Tree getMasterDimensionsTree()
	{
		return masterDimensionsTree;
	}

	public void setMasterDimensionsTree(Tree masterDimensionsTree)
	{
		this.masterDimensionsTree = masterDimensionsTree;
	}

}
