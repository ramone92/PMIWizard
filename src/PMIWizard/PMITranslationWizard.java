package PMIWizard;

import java.rmi.RemoteException;

import nxopen.*;
import nxopen.blockstyler.*;

public class PMITranslationWizard
{
	private Session theSession;
	private UFSession theUFSession;
	
	private Part masterPart;
	
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
		// Session settings		
		setTheSession((Session)SessionFactory.get("Session"));
        setTheUFSession((UFSession)SessionFactory.get("UFSession"));		
	}
	
	public PMITranslationWizard(PMIWizardDialog wizard) throws NXException, RemoteException
	{
		this();
				
		// Initial settings
		setPmiWizardDialog(wizard);		
		
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
		if (getPmiWizardDialog().getToggleAnnotations().value())
		{
			getPmiWizardDialog().getTabMasterAnnotations().setShow(true);
			setAnnotationsTranslationWizard(new AnnotationsTranslationWizard(getPmiWizardDialog().getMasterAnnotationsTree()));			
		}
		else
		{
			getPmiWizardDialog().getTabMasterAnnotations().setShow(false);
		}
				
	}
	
	//------------------------------------------------------------------------------
    // This method called from activate() method to work with DimensionsTranslationWizard
    //------------------------------------------------------------------------------
	private void callDimensionsTranslationWizard() throws RemoteException, NXException
	{
		if (getPmiWizardDialog().getToggleDimensions().value())
		{
			getPmiWizardDialog().getTabMasterDimensions().setShow(true);
			setDimensionsTranslationWizard(new DimensionsTranslationWizard(getPmiWizardDialog().getMasterDimensionsTree()));			
		}
		else
		{
			getPmiWizardDialog().getTabMasterDimensions().setShow(false);
		}
		
	}
	
	//------------------------------------------------------------------------------
    // This method called from activate() method to work with FaceFinishesTranslationWizard
    //------------------------------------------------------------------------------
	private void callFaceFinishesTranslationWizard() throws RemoteException, NXException
	{
		/*getPmiWizardDialog().getMasterFaceFinishesTree().insertColumn(1, "Имя объекта", 500);
    	Node newNode;
    	for (int i = 0; i < 5; i++)
		{
    		newNode = getPmiWizardDialog().getMasterFaceFinishesTree().createNode("face finish node: " + i);
    		getPmiWizardDialog().getMasterFaceFinishesTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);        	
		} */		
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

	public Part getMasterPart()
	{
		return masterPart;
	}

	public void setMasterPart(Part masterPart)
	{
		this.masterPart = masterPart;
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
