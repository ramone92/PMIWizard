package PMIWizard;

import java.rmi.RemoteException;

import nxopen.*;
import nxopen.blockstyler.*;

public class PMITranslationWizard
{
	public Session theSession;
    public UFSession theUFSession;
	
	private PMIWizardDialog pmiWizardDialog;
	private AnnotationsTranslationWizard annotationsTranslationWizard;
	private DimensionsTranslationWizard dimensionsTranslationWizard;
	private FaceFinishesTranslationWizard faceFinishesTranslationWizard;
	
	private int currentWizardStep;
	
	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public PMITranslationWizard() throws NXException, RemoteException
	{
		// TODO to be written		
		
	}
	
	public PMITranslationWizard(PMIWizardDialog wizard) throws NXException, RemoteException
	{
		// Session settings
		setTheSession((Session)SessionFactory.get("Session"));
        setTheUFSession((UFSession)SessionFactory.get("UFSession"));
		
		// Initial settings
		setPmiWizardDialog(wizard);		
		setAnnotationsTranslationWizard(new AnnotationsTranslationWizard(getPmiWizardDialog().getMasterAnnotationsTree()));
		setDimensionsTranslationWizard(new DimensionsTranslationWizard(getPmiWizardDialog().getMasterDimensionsTree()));
		setFaceFinishesTranslationWizard(new FaceFinishesTranslationWizard(getPmiWizardDialog().getMasterFaceFinishesTree()));
		
		//TODO to be written		
		
	}
	
	
	//------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------
	
	//------------------------------------------------------------------------------
    // This method called from activate() method to work with AnnotationsTranslationWizard
    //------------------------------------------------------------------------------
	private void callAnnotationsTranslationWizard() throws RemoteException, NXException
	{
		getPmiWizardDialog().getMasterAnnotationsTree().insertColumn(1, "Имя объекта", 500);
    	//Node rootNode = getPmiWizardDialog().getMasterAnnotationsTree().createNode("root node");
    	//getPmiWizardDialog().getMasterAnnotationsTree().insertNode(rootNode, null, null, NodeInsertOption.FIRST);
    	//rootNode.expand(ExpandOption.TOGGLE);
    	
    	Node newNode;
    	for (int i = 0; i < 5; i++)
		{
    		newNode = getPmiWizardDialog().getMasterAnnotationsTree().createNode("annotation node: " + i);
    		getPmiWizardDialog().getMasterAnnotationsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);        	
		}    
    			
	}
	
	//------------------------------------------------------------------------------
    // This method called from activate() method to work with DimensionsTranslationWizard
    //------------------------------------------------------------------------------
	private void callDimensionsTranslationWizard() throws RemoteException, NXException
	{
		getPmiWizardDialog().getMasterDimensionsTree().insertColumn(1, "Имя объекта", 500);
    	//Node rootNode = getPmiWizardDialog().getMasterAnnotationsTree().createNode("root node");
    	//getPmiWizardDialog().getMasterAnnotationsTree().insertNode(rootNode, null, null, NodeInsertOption.FIRST);
    	//rootNode.expand(ExpandOption.TOGGLE);
    	
    	Node newNode;
    	for (int i = 0; i < 5; i++)
		{
    		newNode = getPmiWizardDialog().getMasterDimensionsTree().createNode("dimension node: " + i);
    		getPmiWizardDialog().getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);        	
		}
		
	}
	
	//------------------------------------------------------------------------------
    // This method called from activate() method to work with FaceFinishesTranslationWizard
    //------------------------------------------------------------------------------
	private void callFaceFinishesTranslationWizard() throws RemoteException, NXException
	{
		getPmiWizardDialog().getMasterFaceFinishesTree().insertColumn(1, "Имя объекта", 500);
    	//Node rootNode = getPmiWizardDialog().getMasterAnnotationsTree().createNode("root node");
    	//getPmiWizardDialog().getMasterAnnotationsTree().insertNode(rootNode, null, null, NodeInsertOption.FIRST);
    	//rootNode.expand(ExpandOption.TOGGLE);
    	
    	Node newNode;
    	for (int i = 0; i < 5; i++)
		{
    		newNode = getPmiWizardDialog().getMasterFaceFinishesTree().createNode("face finish node: " + i);
    		getPmiWizardDialog().getMasterFaceFinishesTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);        	
		} 		
	}
	
	//------------------------------------------------------------------------------
    // Public methods
    //------------------------------------------------------------------------------	
	
	//------------------------------------------------------------------------------
    // This method called at changing wizard step
    //------------------------------------------------------------------------------
	public void activate() throws RemoteException, NXException
	{
		setCurrentWizardStep(getPmiWizardDialog().getWizard().currentStep());
		
		theUFSession.ui().openListingWindow();
    	theUFSession.ui().writeListingWindow("currentWizardStep is " + getCurrentWizardStep() + "\n");
    	
		switch (getCurrentWizardStep())
		{
		case 0:
			
			break;
			
		case 1:		
			
			break;
			
		case 2:
			callAnnotationsTranslationWizard();
			callDimensionsTranslationWizard();
			callFaceFinishesTranslationWizard();
			
			break;
			
		default:
			break;
		}
		
	}
	

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	
	public Session getTheSession()
	{
		return theSession;
	}

	public void setTheSession(Session theSession)
	{
		this.theSession = theSession;
	}

	public UFSession getTheUFSession()
	{
		return theUFSession;
	}

	public void setTheUFSession(UFSession theUFSession)
	{
		this.theUFSession = theUFSession;
	}

	public PMIWizardDialog getPmiWizardDialog()
	{
		return pmiWizardDialog;
	}

	public void setPmiWizardDialog(PMIWizardDialog wizard)
	{
		this.pmiWizardDialog = wizard;
	}

	public AnnotationsTranslationWizard getAnnotationsTranslationWizard()
	{
		return annotationsTranslationWizard;
	}

	public void setAnnotationsTranslationWizard(AnnotationsTranslationWizard annotationsTranslationWizard)
	{
		this.annotationsTranslationWizard = annotationsTranslationWizard;
	}

	public DimensionsTranslationWizard getDimensionsTranslationWizard()
	{
		return dimensionsTranslationWizard;
	}

	public void setDimensionsTranslationWizard(DimensionsTranslationWizard dimensionsTranslationWizard)
	{
		this.dimensionsTranslationWizard = dimensionsTranslationWizard;
	}

	public FaceFinishesTranslationWizard getFaceFinishesTranslationWizard()
	{
		return faceFinishesTranslationWizard;
	}

	public void setFaceFinishesTranslationWizard(FaceFinishesTranslationWizard faceFinishesTranslationWizard)
	{
		this.faceFinishesTranslationWizard = faceFinishesTranslationWizard;
	}

	public int getCurrentWizardStep()
	{
		return currentWizardStep;
	}

	public void setCurrentWizardStep(int currentWizardStep)
	{
		this.currentWizardStep = currentWizardStep;
	}

}
