package PMIWizard;

import java.io.PrintWriter;
import java.io.StringWriter;
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
			if (getAnnotationsTranslationWizard() == null)
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
			//print("callDimensionsTranslationWizard");			
			getPmiWizardDialog().getTabMasterDimensions().setShow(true);
			if (getDimensionsTranslationWizard() == null)
			{
				DimensionsTranslationWizard dtw = new DimensionsTranslationWizard();
				dtw.setMasterPart(masterPart);
				dtw.test();
				setDimensionsTranslationWizard(dtw);
			}
				
			//setDimensionsTranslationWizard(new DimensionsTranslationWizard(getPmiWizardDialog().getMasterDimensionsTree()));	
			//getDimensionsTranslationWizard().fillTree();			
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
		
		print("*** currentWizardStep = " + getCurrentWizardStep());
		
		switch (getCurrentWizardStep())
		{
		case 0:
			
			break;
			
		case 1:		
			openMasterPart();
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
    // This method called from activate() constructor to open part from file system
    //------------------------------------------------------------------------------
	public void openMasterPart() throws RemoteException, NXException
	{
		print("*** 1. Opening master part ***");
		
		if (getMasterPart() != null)
		{
			print("Master part is already opened");
			return;
		}
		
		Part part;
		String partPath = getPmiWizardDialog().getPartFileBrowser().path();
		/*if (partPath.isEmpty())
		{
			print("Part path is empty");
			return;
		}
		else
		{
			print("Path is " + partPath);
		}*/
		
		PartCollection.OpenBaseData openBaseData = getTheSession().parts().openBase(partPath);
		part = (Part) openBaseData.part;
		setMasterPart(part);
		
		openBaseData.loadStatus.dispose();
	    openBaseData.loadStatus = null;
			
	}
	
	//------------------------------------------------------------------------------
    // This method called to close master part
    //------------------------------------------------------------------------------
	public void closeMasterPart() throws RemoteException, NXException
	{
		//print("Closing master part");
		((BasePart)getMasterPart()).close(BasePart.CloseWholeTree.TRUE, BasePart.CloseModified.CLOSE_MODIFIED, null);		
	}
	
	//------------------------------------------------------------------------------
    // This method opens listing window and print string from message parameter
    //------------------------------------------------------------------------------
	public void print(String message)
	{
		try
		{
			if (!getTheUFSession().ui().isListingWindowOpen())
				getTheUFSession().ui().openListingWindow();
			
			getTheUFSession().ui().writeListingWindow(message);
			getTheUFSession().ui().writeListingWindow("\n");
			
		} catch (RemoteException | NXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		print("*** 4. getMasterPart *** is null = " + (masterPart == null));
		if (masterPart != null)
			print("* master part is " + masterPart.toString());
		return masterPart;
	}

	public void setMasterPart(Part masterPart)
	{
		print("*** 2. setMasterPart ***");
		print("* master part is " + masterPart.toString());
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
