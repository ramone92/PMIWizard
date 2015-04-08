package PMIWizard;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nxopen.*;
import nxopen.annotations.*;
import nxopen.blockstyler.*;

public class DimensionsTranslationWizard extends PMITranslationWizard implements Tree.OnSelectCallback
{

	private Tree masterDimensionsTree;
	private ArrayList<Dimension> masterDimensionsList;
	private ArrayList<String> masterDimensionsTypesList;
	
	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------

    //This constructor invokes from PMITranslationWizard.callDimensionsTranslationWizard()
	public DimensionsTranslationWizard(PMITranslationWizard pmitw) throws NXException, RemoteException
	{
		print("*** 3. DimensionsTranslationWizard created ***");
		//getMasterDimensions();
		setMasterPart(pmitw.getMasterPart());
		
		Tree masterTree = pmitw.getPmiWizardDialog().getMasterDimensionsTree();
		masterTree.setOnSelectHandler(this);
		setMasterDimensionsTree(masterTree);				
		//test();
	}
	
	//------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------
	@Deprecated
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

	private void getMasterDimensions() throws RemoteException
	{
		AnnotationManager masterAnnotationManager = super.getMasterPart().annotations();
		// TODO find previous work to get dimensions information form master part
	}
	
	// Detects types of all master part dimensions (parallel dim, radius dim)
	// This types should be nodes in mastedDimensionsTree
	@Deprecated
	private void getMasterDimensionsTypes()
	{
		String className;
		for (Dimension dimension : getMasterDimensionsList())
		{
			className = dimension.getClass().getName();
			if (!getMasterDimensionsTypesList().contains(className))
				getMasterDimensionsTypesList().add(className);
		}
		
	}

	public void test() throws RemoteException, NXException
	{
		try
		{
			print("\n---------------start of test function---------------\n");
			
			AnnotationManager annotationManager = getMasterPart().annotations();
			PmiManager pmiManager = getMasterPart().pmiManager();
			PmiCollection pmiCollection = pmiManager.pmis();
			
			TaggedObjectCollection.Iterator it = pmiCollection.iterator();
			Object taggedObject;
			Pmi pmi;
			
			getMasterDimensionsTree().insertColumn(0, "Dimension", 500);
			
			while(it.hasNext())
			{
				taggedObject = it.next();				
				pmi = (Pmi) taggedObject;
				print("*******************");
				Annotation an = pmi.getDisplayInstances()[0];
		
				String className;			
				String nodeText;
				
				Dimension dim;
				
				if (an instanceof Dimension)
				{
					dim = (Dimension) an;
					
					className = an.getClass().getSimpleName();
					nodeText = className + ": " + dim.computedSize();
					
					Node newNode = getMasterDimensionsTree().createNode(nodeText);
		    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);       
				}
				AssociatedObject ao = pmi.getAssociatedObject();
				DisplayableObject dispObjs[] = ao.getObjects();
				for (DisplayableObject dispObject : dispObjs)
				{
					print(dispObject.journalIdentifier());
				}
			}
		} 
		catch (RemoteException | NXException ex)
		{
			StringWriter s = new StringWriter();
            PrintWriter p = new PrintWriter(s);
            p.println("Caught exception " + ex );
            ex.printStackTrace(p);
            getTheUFSession().ui().writeListingWindow("\n***Failed***");
            getTheUFSession().ui().writeListingWindow("\n"+ex.getMessage());
            getTheUFSession().ui().writeListingWindow("\n"+s.getBuffer().toString());		
		}
	}

	
	//------------------------------------------------------------------------------
    // Public methods
    //------------------------------------------------------------------------------
	
	public void fillTree() throws RemoteException, NXException
	{
		getMasterDimensionsTree().insertColumn(1, "Имя объекта", 500);
    	Node newNode;
    	Node rootNode;

    	//Currently not used
    	/*for (String dimensionType : getMasterDimensionsTypesList())
		{	
    		rootNode = getMasterDimensionsTree().createNode(dimensionType);			
    		getMasterDimensionsTree().insertNode(rootNode, null, null, Tree.NodeInsertOption.LAST);
		}*/

    	for (Dimension dimension : masterDimensionsList)
		{
    		newNode = getMasterDimensionsTree().createNode(dimension.name());
    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);
		}
    	
	}	

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	
	public Tree getMasterDimensionsTree()
	{
		//print("***getMasterDimensionsTree is null:" + (masterDimensionsTree == null));
		return masterDimensionsTree;
	}

	public void setMasterDimensionsTree(Tree masterDimensionsTree)
	{
		//print("setMasterDimensionsTree");
		this.masterDimensionsTree = masterDimensionsTree;
	}

	public ArrayList<Dimension> getMasterDimensionsList()
	{
		return masterDimensionsList;
	}

	public void setMasterDimensionsList(ArrayList<Dimension> masterDimensionsList)
	{
		this.masterDimensionsList = masterDimensionsList;
	}

	public ArrayList<String> getMasterDimensionsTypesList()
	{
		return masterDimensionsTypesList;
	}

	public void setMasterDimensionsTypesList(ArrayList<String> masterDimensionsTypeList)
	{
		this.masterDimensionsTypesList = masterDimensionsTypeList;
	}

	@Override
	public void onSelectCallback(Tree tree, Node node, int columnID, boolean selected) throws NXException, RemoteException
	{
		if (selected)
    	{
    		print(node.displayText());
    	}		
	}

}
