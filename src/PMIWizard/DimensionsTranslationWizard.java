package PMIWizard;

import nxopen.*;
import nxopen.blockstyler.*;

public class DimensionsTranslationWizard extends TranslationWizard
{

	private Tree masterDimensionsTree;
	
	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public DimensionsTranslationWizard(Tree masterDimensionsTree)
	{
		setMasterDimensionsTree(masterDimensionsTree);
	}

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	
	public Tree getMasterDimensionsTree()
	{
		return masterDimensionsTree;
	}

	public void setMasterDimensionsTree(Tree masterDimensionsTree)
	{
		this.masterDimensionsTree = masterDimensionsTree;
	}

}
