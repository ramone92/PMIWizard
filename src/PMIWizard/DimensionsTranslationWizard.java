package PMIWizard;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
	public DimensionsTranslationWizard(Tree masterDimensionsTree) throws NXException, RemoteException
	{
		print("*** 3. DimensionsTranslationWizard created ***");
		//getMasterDimensions();
		setMasterDimensionsTree(masterDimensionsTree);
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

	private void test() throws RemoteException, NXException
	{
		try
		{
			print("---------------start of test function---------------");
			
			print("*master part = null: " + (getMasterPart() == null));
			AnnotationManager annotationManager = getMasterPart().annotations();
			PmiManager pmiManager = getMasterPart().pmiManager();
			PmiCollection pmiCollection = pmiManager.pmis();
			
			print("*an manager = null: " + annotationManager.equals(null));
			
			TaggedObjectCollection.Iterator it = pmiCollection.iterator();
			Object taggedObject;
			Annotation[] annotations;
			Pmi pmi;
			Associativity associativity;
			while(it.hasNext())
			{
				taggedObject = it.next();
				if(taggedObject instanceof Pmi)
					getTheUFSession().ui().writeListingWindow("instanceof Pmi\n");
				else
					getTheUFSession().ui().writeListingWindow("not instanceof Pmi\n");
				
				pmi = (Pmi) taggedObject;
				annotations = pmi.getDisplayInstances();
				getTheUFSession().ui().writeListingWindow("Number of annotations " + annotations.length + "\n");
								
				for(int i = 0; i < annotations.length; i++)
				{
					associativity = annotations[i].getAssociativity(i + 1);
					NXObject obj = associativity.firstObject();
					/*theUFSession.ui().writeListingWindow("an - " + annotations[i].journalIdentifier() + "\n");
					theUFSession.ui().writeListingWindow("ass - " + obj.toString() + "\n");
					theUFSession.ui().writeListingWindow("ass j - " + obj.journalIdentifier() + "\n");*/
					getTheUFSession().ui().writeListingWindow("an - " + ((Dimension)annotations[i]).computedSize()+ "\n");
				}
				
				getTheUFSession().ui().writeListingWindow(taggedObject.toString() + "\n");
				//theUFSession.ui().writeListingWindow(taggedObject.getClass().getName() + "\n");
				getTheUFSession().ui().writeListingWindow("---------------end of test function---------------\n");
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
