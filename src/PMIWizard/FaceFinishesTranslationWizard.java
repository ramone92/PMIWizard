package PMIWizard;

import nxopen.*;
import nxopen.blockstyler.*;

public class FaceFinishesTranslationWizard extends TranslationWizard
{
	
	private Tree masterFaceFinishesTree;

	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public FaceFinishesTranslationWizard(Tree masterFaceFinishesTree)
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
