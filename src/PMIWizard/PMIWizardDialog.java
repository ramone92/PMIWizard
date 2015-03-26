package PMIWizard;
//==============================================================================
//  WARNING!!  This file is overwritten by the Block UI Styler while generating
//  the automation code. Any modifications to this file will be lost after
//  generating the code again.
//
//       Filename:  E:\NX\wizard\wizard.java
//
//        This file was generated by the NX Block UI Styler
//        Created by: zikt
//              Version: NX 8.5
//              Date: 03-26-2015  (Format: mm-dd-yyyy)
//              Time: 00:24 (Format: hh-mm)
//
//==============================================================================

//==============================================================================
//  Purpose:  This TEMPLATE file contains JAVA source to guide you in the
//  construction of your Block application dialog. The generation of your
//  dialog file (.dlx extension) is the first step towards dialog construction
//  within NX.  You must now create a NX Open application that
//  utilizes this file (.dlx).
//
//  The information in this file provides you with the following:
//
//  1.  Help on how to load and display your Block UI Styler dialog in NX
//      using APIs provided in NXOpen.BlockStyler namespace
//  2.  The empty callback methods (stubs) associated with your dialog items
//      have also been placed in this file. These empty methods have been
//      created simply to start you along with your coding requirements.
//      The method name, argument list and possible return values have already
//      been provided for you.
//==============================================================================

//------------------------------------------------------------------------------
//These imports are needed for the following template code
//------------------------------------------------------------------------------
import java.rmi.RemoteException;

import nxopen.*;
import nxopen.blockstyler.*;
import nxopen.blockstyler.Node.ExpandOption;
import nxopen.blockstyler.Tree.ColumnDisplay;
import nxopen.blockstyler.Tree.NodeInsertOption;

//------------------------------------------------------------------------------
//Represents Block Styler application class
//------------------------------------------------------------------------------
public class PMIWizardDialog implements BlockDialog.Initialize, BlockDialog.DialogShown, BlockDialog.Apply, BlockDialog.Ok, BlockDialog.Update
//,Tree.OnExpandCallback
//,Tree.OnInsertColumnCallback
//,Tree.OnInsertNodeCallback
//,Tree.OnPreSelectCallback
//,Tree.OnDeleteNodeCallback
,Tree.OnSelectCallback
//,Tree.OnStateChangeCallback
//,Tree.ToolTipTextCallback
//,Tree.ColumnSortCallback
//,Tree.StateIconNameCallback
//,Tree.OnBeginLabelEditCallback
//,Tree.OnEndLabelEditCallback
//,Tree.OnEditOptionSelectedCallback
//,Tree.AskEditControlCallback
//,Tree.OnMenuCallback
//,Tree.OnMenuSelectionCallback
//,Tree.IsDropAllowedCallback
//,Tree.IsDragAllowedCallback
//,Tree.OnDropCallback
//,Tree.OnDropMenuCallback
//,Tree.OnDefaultActionCallback
//,Wizard.StepNotifyPreCallback
//,Wizard.StepNotifyPostCallback
//,Wizard.IsStepOkayCallback
//,Wizard.OnSubNodeCallback
//,Wizard.OnMenuCallback
//,Wizard.OnMenuSelectionCallback
{
    //class members
    public static Session theSession = null;
    public static UFSession theUFSession = null;
    public static UI theUI = null;
    private String theDlxFileName;
    private nxopen.blockstyler.BlockDialog theDialog;
    private nxopen.blockstyler.Wizard wizard;// Block type: Wizard
    private nxopen.blockstyler.Group wizardStepSelectPart;// Block type: Group
    private nxopen.blockstyler.FileSelection nativeFileBrowser0;// Block type: NativeFileBrowser
    private nxopen.blockstyler.Group wizardStepSelectPMIObjects;// Block type: Group
    private nxopen.blockstyler.Toggle toggleAnnotations;// Block type: Toggle
    private nxopen.blockstyler.Toggle toggleDimensions;// Block type: Toggle
    private nxopen.blockstyler.Toggle toggleFaceFinishes;// Block type: Toggle
    private nxopen.blockstyler.Group wizardStepObjects;// Block type: Group
    private nxopen.blockstyler.TabControl tabControl;// Block type: Tabs Page
    private nxopen.blockstyler.Group tabPage1;// Block type: Group
    private nxopen.blockstyler.Tree masterAnnotations;// Block type: Tree Control
    private nxopen.blockstyler.Group tabPage2;// Block type: Group
    private nxopen.blockstyler.Tree masterDimensions;// Block type: Tree Control
    private nxopen.blockstyler.Group tabPage;// Block type: Group
    private nxopen.blockstyler.Tree masterFaceFinishes;// Block type: Tree Control
    
    private PMITranslationWizard translationWizard; 
        
    //------------------------------------------------------------------------------
    //Constructor for NX Styler class
    //------------------------------------------------------------------------------
    public PMIWizardDialog() throws Exception, RemoteException
    {
        try
        {
            theSession = (Session)SessionFactory.get("Session");
            theUFSession = (UFSession)SessionFactory.get("UFSession");
            theUI = (UI)SessionFactory.get("UI");
            theDlxFileName = "PMIWizard.dlx";
            theDialog = theUI.createDialog(theDlxFileName);
            theDialog.addApplyHandler(this);
            theDialog.addOkHandler(this);
            theDialog.addUpdateHandler(this);
            theDialog.addInitializeHandler(this);
            theDialog.addDialogShownHandler(this);
            
            setTranslationWizard(new PMITranslationWizard(this));
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            throw new Exception(ex);
        }
    }
    //------------------------------- DIALOG LAUNCHING ---------------------------------
    //
    //    Before invoking this application one needs to open any part/empty part in NX
    //    because of the behavior of the blocks.
    //
    //    Make sure the dlx file is in one of the following locations:
    //        1.) From where NX session is launched
    //        2.) $UGII_USER_DIR/application
    //        3.) For released applications, using UGII_CUSTOM_DIRECTORY_FILE is highly
    //            recommended. This variable is set to a full directory path to a file 
    //            containing a list of root directories for all custom applications.
    //            e.g., UGII_CUSTOM_DIRECTORY_FILE=$UGII_ROOT_DIR\menus\custom_dirs.dat
    //
    //    You can create the dialog using one of the following way:
    //
    //    1. USER EXIT
    //
    //        1) Create the Shared Library -- Refer "Block UI Styler programmer's guide"
    //        2) Invoke the Shared Library through File->Execute->NX Open menu.
    //
    //------------------------------------------------------------------------------
    public static void main(String [] argv) throws Exception
    {
    	PMIWizardDialog thewizard = null;
        try
        {
            thewizard = new PMIWizardDialog();
            // The following method shows the dialog immediately
            thewizard.show();
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
        finally
        {
            if(thewizard != null)
            {
                thewizard.dispose();
                thewizard = null;
            }
        }
    }
    
    //------------------------------------------------------------------------------
    // This method specifies how a shared image is unloaded from memory
    // within NX. This method gives you the capability to unload an
    // internal NX Open application or user  exit from NX. Specify any
    // one of the three constants as a return value to determine the type
    // of unload to perform:
    //
    //
    //    Immediately : unload the library as soon as the automation program has completed
    //    Explicitly  : unload the library from the "Unload Shared Image" dialog
    //    AtTermination : unload the library when the NX session terminates
    //
    //
    // NOTE:  A program which associates NX Open applications with the menubar
    // MUST NOT use this option since it will UNLOAD your NX Open application image
    // from the menubar.
    //------------------------------------------------------------------------------
    public static final int getUnloadOption()
    {
        //return BaseSession.LibraryUnloadOption.EXPLICITLY;
         return BaseSession.LibraryUnloadOption.IMMEDIATELY;
        // return BaseSession.LibraryUnloadOption.ATTERMINATION;
    }
    
    //------------------------------------------------------------------------------
    // Following method cleanup any housekeeping chores that may be needed.
    // This method is automatically called by NX.
    //------------------------------------------------------------------------------
    public static void unloadLibrary() throws NXException, RemoteException
    {
        try
        {
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
    }
    
    //------------------------------------------------------------------------------
    //This method shows the dialog on the screen
    //------------------------------------------------------------------------------
    public int show() throws NXException, RemoteException
    {
        try
        {
            theDialog.show();
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
        return 0;
    }
    
    //------------------------------------------------------------------------------
    //Method Name: dispose
    //------------------------------------------------------------------------------
    public void dispose() throws NXException, RemoteException
    {
        if(theDialog != null)
        {
            theDialog.dispose();
            theDialog = null;
        }
    }
    
    //------------------------------------------------------------------------------
    //---------------------Block UI Styler Callback Functions--------------------------
    //------------------------------------------------------------------------------
    
    //------------------------------------------------------------------------------
    //Callback Name: initialize
    //------------------------------------------------------------------------------
    public void initialize() throws NXException, RemoteException
    {
        try
        {
            wizard = (nxopen.blockstyler.Wizard)theDialog.topBlock().findBlock("wizard");
            wizardStepSelectPart = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStep1");
            nativeFileBrowser0 = (nxopen.blockstyler.FileSelection)theDialog.topBlock().findBlock("nativeFileBrowser0");
            wizardStepSelectPMIObjects = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStep2");
            toggleAnnotations = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleAnnotations");
            toggleDimensions = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleDimensions");
            toggleFaceFinishes = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleFaceFinishes");
            wizardStepObjects = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStep");
            tabControl = (nxopen.blockstyler.TabControl)theDialog.topBlock().findBlock("tabControl");
            tabPage1 = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabPage1");
            masterAnnotations = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterAnnotations");
            tabPage2 = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabPage2");
            masterDimensions = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterDimensions");
            tabPage = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabPage");
            masterFaceFinishes = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterFaceFinishes");
            //------------------------------------------------------------------------------
            //Registration of Treelist specific callbacks
            //------------------------------------------------------------------------------
            //masterAnnotations.setOnExpandHandler(this);
            
            //masterAnnotations.setOnInsertColumnHandler(this);
            
            //masterAnnotations.setOnInsertNodeHandler(this);
            
            //masterAnnotations.setOnDeleteNodeHandler(this);
            
            //masterAnnotations.setOnPreSelectHandler(this);
            
            masterAnnotations.setOnSelectHandler(this);
            
            //masterAnnotations.setOnStateChangeHandler(this);
            
            //masterAnnotations.setToolTipTextHandler(this);
            
            //masterAnnotations.setColumnSortHandler(this);
            
            //masterAnnotations.setStateIconNameHandler(this);
            
            //masterAnnotations.setOnBeginLabelEditHandler(this);
            
            //masterAnnotations.setOnEndLabelEditHandler(this);
            
            //masterAnnotations.setOnEditOptionSelectedHandler(this);
            
            //masterAnnotations.setAskEditControlHandler(this);
            
            //masterAnnotations.setOnMenuHandler(this);
            
            //masterAnnotations.setOnMenuSelectionHandler(this);
            
            //masterAnnotations.setIsDropAllowedHandler(this);
            
            //masterAnnotations.setIsDragAllowedHandler(this);
            
            //masterAnnotations.setOnDropHandler(this);
            
            //masterAnnotations.setOnDropMenuHandler(this);
            
            //masterAnnotations.setOnDefaultActionHandler(this);
            
            //------------------------------------------------------------------------------
            //------------------------------------------------------------------------------
            //Registration of Wizard specific callbacks
            //------------------------------------------------------------------------------
            //wizard.setStepNotifyPreHandler(this);
            
            //wizard.setStepNotifyPostHandler(this);
            
            //wizard.setIsStepOkayHandler(this);
            
            //wizard.setOnSubNodeHandler(this);
            
            //wizard.setOnMenuHandler(this);
            
            //wizard.setOnMenuSelectionHandler(this);
            
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
    }
    
    //------------------------------------------------------------------------------
    //Callback Name: dialogShown
    //This callback is executed just before the dialog launch. Thus any value set 
    //here will take precedence and dialog will be launched showing that value. 
    //------------------------------------------------------------------------------
    public void dialogShown() throws NXException, RemoteException
    {
        try
        {
        	//TODO this throws error when opening steps without tree
        	theUFSession.ui().openListingWindow();
        	theUFSession.ui().writeListingWindow("currentStep is " + wizard.currentStep() + "\n");
        	switch (wizard.currentStep())
			{
			case 0:
				
				break;
				
			case 1:		
				
				break;
				
			case 2:
				masterAnnotations.insertColumn(1, "��� �������", 500);
	        	//masterAnnotations.insertColumn(2, "�������", 100);
	        	//masterAnnotations.setColumnDisplayType(1, ColumnDisplay.ICON);
	        	Node rootNode = masterAnnotations.createNode("root node");
	        	masterAnnotations.insertNode(rootNode, null, null, NodeInsertOption.FIRST);
	        	rootNode.expand(ExpandOption.TOGGLE);
	        	
	        	Node newNode;
	        	for (int i = 0; i < 5; i++)
				{
	        		newNode = masterAnnotations.createNode("node text " + i);
	        		
	            	masterAnnotations.insertNode(newNode, rootNode, null, NodeInsertOption.LAST);
	            	//newNode.expand(ExpandOption.TOGGLE);
				}     
	        	//masterAnnotations.setShowExpandCollapseMarker(false);
	        	masterAnnotations.setShowToolTips(true);
	        	masterAnnotations.setShowHeader(true);
	        	masterAnnotations.setShowMultipleColumns(true);
				break;
				
			default:
				break;
			}
        	
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
    }
    
    //------------------------------------------------------------------------------
    //Callback Name: apply
    //Following callback is associated with the "theDialog" Block.
    //------------------------------------------------------------------------------
    public int apply() throws NXException, RemoteException
    {
        int errorCode = 0;
        try
        {
            //---- Enter your callback code here -----
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            errorCode = 1;
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
        return errorCode;
    }
    //------------------------------------------------------------------------------
    //Callback Name: update
    //Following callback is associated with the "theDialog" Block.
    //------------------------------------------------------------------------------
    public int update( nxopen.blockstyler.UIBlock block) throws NXException, RemoteException
    {
        try
        {
            if(block == nativeFileBrowser0)
            {
            //---------Enter your code here-----------
            }
            else if(block == toggleAnnotations)
            {
            //---------Enter your code here-----------
            }
            else if(block == toggleDimensions)
            {
            //---------Enter your code here-----------
            }
            else if(block == toggleFaceFinishes)
            {
            //---------Enter your code here-----------
            }
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
        return 0;
    }
    
    //------------------------------------------------------------------------------
    //Callback Name: ok
    //------------------------------------------------------------------------------
    public int ok() throws NXException, RemoteException
    {
        int errorCode = 0;
        try
        {
            //---- Enter your callback code here -----
            errorCode = apply();
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            errorCode = 1;
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
        return errorCode;
    }
    //------------------------------------------------------------------------------
    //Treelist specific callbacks
    //------------------------------------------------------------------------------
    //public void onExpandCallback(Tree tree, Node node) throws NXException,RemoteException
    //{
    //}
    
    //public void onInsertColumnCallback(Tree tree, Node node, int columnID)throws NXException, RemoteException
    //{
    //}
    
    //public void onInsertNodeCallback(Tree tree, Node node) throws NXException,RemoteException
    //{
    //}
    
    //public void onDeleteNodeCallback(Tree tree, Node node) throws NXException,RemoteException
    //{
    //}
    
    //public void onPreSelectCallback(Tree tree, Node node, int columnID, boolean selected)throws NXException, RemoteException
    //{
    //}
    
    public void onSelectCallback(Tree tree, Node node, int columnID, boolean selected)throws NXException, RemoteException
    {
    	if (selected) 
    	{
	    	theUFSession.ui().openListingWindow();
	    	theUFSession.ui().writeListingWindow(tree.toString() + "/" + node.toString() + "/" + columnID + "/" + selected + "\n\n");
    	}
    }
    
    //public void onStateChangeCallback(Tree tree, Node node, int columnID)throws NXException, RemoteException
    //{
    //}
    
    //public String toolTipTextCallback(Tree tree, Node node, int columnID)throws NXException, RemoteException
    //{
    //}
    
    //public int columnSortCallback(Tree tree, int columnID, Node node1, Node node2) throws NXException, RemoteException
    //{
    //}
    
    //public String stateIconNameCallback(Tree tree, Node node, int columnID)throws NXException, RemoteException
    //{
    //}
    
    //public nxopen.blockstyler.Tree.BeginLabelEditState onBeginLabelEditCallback(Tree tree, Node node,int columnID) throws NXException, RemoteException
    //{
    //}
    
    //public nxopen.blockstyler.Tree.EndLabelEditState onEndLabelEditCallback(Tree tree, Node node,int columnID, String editedText) throws NXException, RemoteException
    //{
    //}
    
    //public nxopen.blockstyler.Tree.EditControlOption onEditOptionSelectedCallback(Tree tree, Node node,int columnID, int selectedOptionID, String selectedOptionText, nxopen.blockstyler.Tree.ControlType type)throws NXException, RemoteException
    //{
    //}
    
    //public nxopen.blockstyler.Tree.ControlType askEditControlCallback(Tree tree, Node node, int columnID)throws NXException, RemoteException
    //{
    //}
    
    //public void onMenuCallback(Tree tree, Node node, int columnID)throws NXException, RemoteException
    //{
    //}
    
    //public void onMenuSelectionCallback(Tree tree, Node node, int menuItemID)throws NXException, RemoteException
    //{
    //}
    
    //public nxopen.blockstyler.Node.DropType isDropAllowedCallback(Tree tree, Node node, int columnID,Node targetNode, int targetColumnID) throws NXException, RemoteException
    //{
    //}
    
    //public nxopen.blockstyler.Node.DragType isDragAllowedCallback(Tree tree, Node node, int columnID)throws NXException, RemoteException
    //{
    //}
    
    //public boolean onDropCallback(Tree tree, Node[] node, int columnID, Node targetNode,int targetColumnID, nxopen.blockstyler.Node.DropType dropType, int dropMenuItemId) throws NXException,RemoteException
    //{
    //}
    
    //public void onDropMenuCallback(Tree tree, Node node, int columnID, Node targetNode, int targetColumnID) throws NXException,RemoteException
    //{
    //}
    
    //public void onDefaultActionCallback(Tree tree, Node node, int columnID) throws NXException,RemoteException
    //{
    //}
    
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //Wizard specific callbacks
    //------------------------------------------------------------------------------
    //public int stepNotifyPreCallback(Wizard wizard, int nextStep) throws NXException,RemoteException
    //{
    //}
    
    //public void stepNotifyPostCallback(Wizard wizard, int previousStep) throws NXException,RemoteException
    //{
    //}
    
    //public boolean isStepOkayCallback(Wizard wizard, int step) throws NXException,RemoteException
    //{
    //}
    
    //public void onSubNodeCallback(Wizard wizard, int step, int subNodeId, Wizard.SubNodeAction action) throws NXException,RemoteException
    //{
    //}
    
    //public void onMenuCallback(Wizard wizard, Wizard.TaskNavigatorItem item, int step, int subNodeId) throws NXException,RemoteException
    //{
    //}
    
    //public void onMenuSelectionCallback(Wizard wizard, Wizard.TaskNavigatorItem item, int step, int subNodeId, int commandIndex) throws NXException,RemoteException
    //{
    //}
    
    
    //------------------------------------------------------------------------------
    //Function Name: GetBlockProperties
    //------------------------------------------------------------------------------
    public PropertyList getBlockProperties(String blockID) throws NXException, RemoteException
    {
        PropertyList plist = null;
        try
        {
            plist = theDialog.getBlockProperties(blockID);
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
        return plist;
    }
    
    //------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------    
	public PMITranslationWizard getTranslationWizard()
	{
		return translationWizard;
	}
	public void setTranslationWizard(PMITranslationWizard translationWizard)
	{
		this.translationWizard = translationWizard;
	}
    
}
