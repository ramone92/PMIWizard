package PMIWizard;

import java.rmi.RemoteException;

import nxopen.*;
import nxopen.blockstyler.*;

public class AnnotationsTranslationWizard extends PMITranslationWizard
{
	
	private Tree masterAnnotationsTree;

	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public AnnotationsTranslationWizard(Tree masterAnnotationsTree) throws NXException, RemoteException
	{
		
		setAnnotationsWizardTree(masterAnnotationsTree);
		// TODO something wrong with that handler
		super.getTheUFSession().ui().openListingWindow();
		super.getTheUFSession().ui().writeListingWindow("AnnotationsTranslationWizard\n");
	}
	
	//------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------
	
	private void fillTree()
	{
		
	}

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	public Tree getAnnotationsWizardTree()
	{
		return masterAnnotationsTree;
	}

	public void setAnnotationsWizardTree(Tree masterAnnotationsTree)
	{
		this.masterAnnotationsTree = masterAnnotationsTree;
	}
		
}
