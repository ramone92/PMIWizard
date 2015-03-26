package PMIWizard;

import java.rmi.RemoteException;

import nxopen.*;
import nxopen.blockstyler.*;

public class FaceFinishesTranslationWizard extends PMITranslationWizard
{
	
	private Tree masterFaceFinishesTree;

	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public FaceFinishesTranslationWizard(Tree masterFaceFinishesTree) throws NXException, RemoteException
	{
		setMasterFaceFinishesTree(masterFaceFinishesTree);				
	}

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	
	public Tree getMasterFaceFinishesTree()
	{
		return masterFaceFinishesTree;
	}

	public void setMasterFaceFinishesTree(Tree masterFaceFinishesTree)
	{
		this.masterFaceFinishesTree = masterFaceFinishesTree;
	}

}
