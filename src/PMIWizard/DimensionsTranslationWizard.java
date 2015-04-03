package PMIWizard;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import nxopen.*;
import nxopen.annotations.*;
import nxopen.blockstyler.*;

public class DimensionsTranslationWizard extends PMITranslationWizard
{

	private Tree masterDimensionsTree;
	private ArrayList<Dimension> masterDimensionsList;
	private ArrayList<String> masterDimensionsTypesList;
	
	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------

    //This constructor invokes from PMITranslationWizard.callDimensionsTranslationWizard()
	public DimensionsTranslationWizard() throws NXException, RemoteException
	{
		print("*** 3. DimensionsTranslationWizard created ***");
		//getMasterDimensions();
		//setMasterDimensionsTree(getPmiWizardDialog().getMasterDimensionsTree());
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
			print("---------------start of test function---------------");
			
			AnnotationManager annotationManager = getMasterPart().annotations();
			PmiManager pmiManager = getMasterPart().pmiManager();
			PmiCollection pmiCollection = pmiManager.pmis();
			
			TaggedObjectCollection.Iterator it = pmiCollection.iterator();
			Object taggedObject;
			Pmi pmi;
			while(it.hasNext())
			{
				taggedObject = it.next();				
				pmi = (Pmi) taggedObject;
				print("*******************");
				Annotation an = pmi.getDisplayInstances()[0];
				print(an.getClass().getName());
				print(an.getClass().getSimpleName());
				
				//--------------------
//				getMasterDimensionsTree().insertColumn(0, "Имя объекта", 500);
				//--------------------
				if (an instanceof Dimension)
				{
					/*Node newNode = getMasterDimensionsTree().createNode(an.name());
		    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);*/       
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
            getTheUFSession().ui().writeListingWindow("\nFailed");
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
		print("***getMasterDimensionsTree is null:" + (masterDimensionsTree == null));
		return masterDimensionsTree;
	}

	public void setMasterDimensionsTree(Tree masterDimensionsTree)
	{
		//super.print("setMasterDimensionsTree");
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

	public void setMasterDimensionsTypesList(
			ArrayList<String> masterDimensionsTypeList)
	{
		this.masterDimensionsTypesList = masterDimensionsTypeList;
	}

}
