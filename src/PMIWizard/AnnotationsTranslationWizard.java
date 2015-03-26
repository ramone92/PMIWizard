package PMIWizard;

import java.rmi.RemoteException;

import nxopen.*;
import nxopen.blockstyler.*;

public class AnnotationsTranslationWizard extends PMITranslationWizard implements Tree.OnSelectCallback
{
	
	private Tree masterAnnotationsTree;

	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public AnnotationsTranslationWizard(Tree masterAnnotationsTree) throws RemoteException, NXException
	{
		/*super.getTheUFSession().ui().openListingWindow();
		super.getTheUFSession().ui().writeListingWindow("AnnotationsTranslationWizard from AnnotationsTranslationWizard");*/
		
		setAnnotationsWizardTree(masterAnnotationsTree);
		// TODO something wrong with that handler
		//getAnnotationsWizardTree().setOnSelectHandler(this);
		
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

		
	@Override
	public void onSelectCallback(Tree tree, Node node, int columnID, boolean selected) throws NXException, RemoteException
	{
		// TODO Автоматически созданная заглушка метода
		/*super.getTheUFSession().ui().openListingWindow();
		super.getTheUFSession().ui().writeListingWindow("onSelectCallback from AnnotationsTranslationWizard");*/
	}

}
