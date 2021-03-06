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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;

import nxopen.*;
import nxopen.blockstyler.*;

//------------------------------------------------------------------------------
//Represents Block Styler application class
//------------------------------------------------------------------------------
public class PMIWizardDialog implements BlockDialog.Initialize, BlockDialog.DialogShown, BlockDialog.Apply, BlockDialog.Ok, BlockDialog.Update
//,Tree.OnExpandCallback
//,Tree.OnInsertColumnCallback
//,Tree.OnInsertNodeCallback
//,Tree.OnPreSelectCallback
//,Tree.OnDeleteNodeCallback
//,Tree.OnSelectCallback
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
    private nxopen.blockstyler.FileSelection partFileBrowser;// Block type: NativeFileBrowser
    private nxopen.blockstyler.Group wizardStepSelectPMIObjects;// Block type: Group
    private nxopen.blockstyler.Toggle toggleAnnotations;// Block type: Toggle
    private nxopen.blockstyler.Toggle toggleDimensions;// Block type: Toggle
    private nxopen.blockstyler.Toggle toggleSurfaceFinishes;// Block type: Toggle
    private nxopen.blockstyler.Group wizardStepObjects;// Block type: Group
    private nxopen.blockstyler.Group tabMasterAnnotations;// Block type: Group
    private nxopen.blockstyler.TabControl tabControl;// Block type: Tabs Page
	private nxopen.blockstyler.Tree masterAnnotationsTree;// Block type: Tree Control
    private nxopen.blockstyler.Group tabMasterDimensions;// Block type: Group
    private nxopen.blockstyler.Tree masterDimensionsTree;// Block type: Tree Control
    private nxopen.blockstyler.CurveCollector dimensionEdgeSelect;// Block type: Curve Collector
    private nxopen.blockstyler.Button translateDimensionButton;// Block type: Button
    private nxopen.blockstyler.Group tabMasterSurfaceFinishes;// Block type: Group
    private nxopen.blockstyler.Tree masterSurfaceFinishesTree;// Block type: Tree Control
    private nxopen.blockstyler.FaceCollector surfaceFinishSurfaceSelect;// Block type: Face Collector
    private nxopen.blockstyler.Button translateSurfaceFinishButton;// Block type: Button
    private nxopen.blockstyler.Group wizardStepSelectAnnotationPlane;// Block type: Group
    private nxopen.blockstyler.SpecifyPlane annotationPlane;// Block type: Specify Plane
    private nxopen.blockstyler.SpecifyPoint surfaceFinishLocation;// Block type: Specify Point
    private nxopen.blockstyler.Button dimensionsClearButton;// Block type: Button
    private nxopen.blockstyler.Button surfaceFinishClearButton;// Block type: Button
    
    //------------------------------------------------------------------------------
    //Bit Option for Property: EntityType
    //------------------------------------------------------------------------------
    public static final int                          EntityType_AllowEdges = (1 << 0);
    public static final int                         EntityType_AllowCurves = (1 << 2);
    public static final int                          EntityType_AllowPoint = (1 << 3);
    public static final int                          EntityType_AllowFaces = (1 << 4);
    public static final int                         EntityType_AllowDatums = (1 << 5);
    public static final int                         EntityType_AllowBodies = (1 << 6);
    //------------------------------------------------------------------------------
    //Bit Option for Property: CurveRules
    //------------------------------------------------------------------------------
    public static final int                         CurveRules_SingleCurve = (1 << 0);
    public static final int                     CurveRules_ConnectedCurves = (1 << 1);
    public static final int                       CurveRules_TangentCurves = (1 << 2);
    public static final int                           CurveRules_FaceEdges = (1 << 3);
    public static final int                           CurveRules_BodyEdges = (1 << 4);
    public static final int                          CurveRules_SheetEdges = (1 << 5);
    public static final int                       CurveRules_FeatureCurves = (1 << 6);
    public static final int                         CurveRules_VertexEdges = (1 << 8);
    public static final int                  CurveRules_VertexTangentEdges = (1 << 9);
    public static final int                CurveRules_RegionBoundaryCurves = (1 <<11);
    public static final int                   CurveRules_OuterEdgesofFaces = (1 <<13);
    public static final int                     CurveRules_RibTopFaceEdges = (1 <<14);
    //------------------------------------------------------------------------------
    //Bit Option for Property: FaceRules
    //------------------------------------------------------------------------------
    public static final int                           FaceRules_SingleFace = (1 << 0);
    public static final int                          FaceRules_RegionFaces = (1 << 1);
    public static final int                         FaceRules_TangentFaces = (1 << 2);
    public static final int                   FaceRules_TangentRegionFaces = (1 << 3);
    public static final int                            FaceRules_BodyFaces = (1 << 4);
    public static final int                         FaceRules_FeatureFaces = (1 << 5);
    public static final int                        FaceRules_AdjacentFaces = (1 << 6);
    public static final int                  FaceRules_ConnectedBlendFaces = (1 << 7);
    public static final int                        FaceRules_AllBlendFaces = (1 << 8);
    public static final int                             FaceRules_RibFaces = (1 << 9);
    public static final int                            FaceRules_SlotFaces = (1 <<10);
    public static final int                   FaceRules_BossandPocketFaces = (1 <<11);
    public static final int                       FaceRules_MergedRibFaces = (1 <<12);
    public static final int                  FaceRules_RegionBoundaryFaces = (1 <<13);
    public static final int                 FaceRules_FaceandAdjacentFaces = (1 <<14);
        
    private static PMITranslationWizard pmiTranslationWizard; 
    
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
            
            setPMITranslationWizard(new PMITranslationWizard(this));
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            throw new Exception(ex);
        }
        finally
        {
        	//getPMITranslationWizard().print("PMIWizardDialog");
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
    public static final int getUnloadOption() throws RemoteException, NXException
    {    	
    	//getPMITranslationWizard().print("***getUnloadOption***");
    	getPMITranslationWizard().closeMasterPart();
    	return BaseSession.LibraryUnloadOption.IMMEDIATELY;        
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
            
            wizardStepSelectPart = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStepSelectPart");
            wizardStepSelectPMIObjects = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStepSelectPMIObjects");
            wizardStepObjects = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStepObjects");
            
            partFileBrowser = (nxopen.blockstyler.FileSelection)theDialog.topBlock().findBlock("partFileBrowser");
            
            toggleAnnotations = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleAnnotations");
            toggleDimensions = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleDimensions");
            toggleSurfaceFinishes = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleSurfaceFinishes");
            
            tabControl = (nxopen.blockstyler.TabControl)theDialog.topBlock().findBlock("tabControl");
            tabMasterAnnotations = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabMasterAnnotations");
            tabMasterDimensions = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabMasterDimensions");
            tabMasterSurfaceFinishes = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabMasterSurfaceFinishes");
            
            masterAnnotationsTree = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterAnnotationsTree");            
            masterDimensionsTree = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterDimensionsTree");            
            masterSurfaceFinishesTree = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterSurfaceFinishesTree");
            
            dimensionEdgeSelect = (nxopen.blockstyler.CurveCollector)theDialog.topBlock().findBlock("dimensionEdgeSelect");
            translateDimensionButton = (nxopen.blockstyler.Button)theDialog.topBlock().findBlock("translateDimensionButton");
            surfaceFinishSurfaceSelect = (nxopen.blockstyler.FaceCollector)theDialog.topBlock().findBlock("surfaceFinishSurfaceSelect");
            translateSurfaceFinishButton = (nxopen.blockstyler.Button)theDialog.topBlock().findBlock("translateSurfaceFinishButton");
            
            wizardStepSelectAnnotationPlane = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStepSelectAnnotationPlane");
            annotationPlane = (nxopen.blockstyler.SpecifyPlane)theDialog.topBlock().findBlock("annotationPlane");
            
            surfaceFinishLocation = (nxopen.blockstyler.SpecifyPoint)theDialog.topBlock().findBlock("surfaceFinishLocation");
            dimensionsClearButton = (nxopen.blockstyler.Button)theDialog.topBlock().findBlock("dimensionsClearButton");
            surfaceFinishClearButton = (nxopen.blockstyler.Button)theDialog.topBlock().findBlock("surfaceFinishClearButton");
            
            //------------------------------------------------------------------------------
            //Registration of Treelist specific callbacks
            //------------------------------------------------------------------------------
            //masterAnnotations.setOnExpandHandler(this);
            
            //masterAnnotations.setOnInsertColumnHandler(this);
            
            //masterAnnotations.setOnInsertNodeHandler(this);
            
            //masterAnnotations.setOnDeleteNodeHandler(this);
            
            //masterAnnotations.setOnPreSelectHandler(this);
            
            /*masterAnnotationsTree.setOnSelectHandler(this);
            masterDimensionsTree.setOnSelectHandler(this);
            masterFaceFinishesTree.setOnSelectHandler(this);*/
            
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
        	PMITranslationWizard pmiTW = getPMITranslationWizard();
        	pmiTW.activate();        	
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
        	//getPMITranslationWizard().closeMasterPart();
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
        	if(getTheUFSession()!=null)
            {
                StringWriter s = new StringWriter();
                PrintWriter p = new PrintWriter(s);
                p.println("Caught exception " + ex );
                ex.printStackTrace(p);
                getTheUFSession().ui().writeListingWindow("\nFailed");
                getTheUFSession().ui().writeListingWindow("\n"+ex.getMessage());
                getTheUFSession().ui().writeListingWindow("\n"+s.getBuffer().toString());
            }    	
        	
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
            if(block == partFileBrowser)
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
            else if(block == toggleSurfaceFinishes)
            {
            //---------Enter your code here-----------
            }
            else if(block == dimensionEdgeSelect)
            {
            //---------Enter your code here-----------
            }
            else if(block == translateDimensionButton)
            {
            //---------Enter your code here-----------
            	getPMITranslationWizard().getDimensionsTranslationWizard().translate();
            }
            else if(block == surfaceFinishSurfaceSelect)
            {
            //---------Enter your code here-----------
            }
            else if(block == translateSurfaceFinishButton)
            {
            //---------Enter your code here-----------
            	getPMITranslationWizard().getSurfaceFinishesTranslationWizard().translate();
            }
            else if(block == annotationPlane)
            {
            //---------Enter your code here-----------
            }
            else if(block == surfaceFinishLocation)
            {
            //---------Enter your code here-----------
            }
            else if(block == dimensionsClearButton)
            {
            	getDimensionEdgeSelect().setSelectedObjects(new TaggedObject[0]);	
            }
            else if(block == surfaceFinishClearButton)
            {
            	getSurfaceFinishFaceSelect().setSelectedObjects(new TaggedObject[0]);
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
    
    /*public void onSelectCallback(Tree tree, Node node, int columnID, boolean selected) throws NXException, RemoteException
    {
    	if (selected)
    	{
    		theUFSession.ui().openListingWindow();
    		theUFSession.ui().writeListingWindow(tree.name() + "/" + node.toString() + "/" + columnID + "/" + selected + "\n\n");
    	}	    
    }*/
    
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
    
	public static PMITranslationWizard getPMITranslationWizard()
	{
		return pmiTranslationWizard;
	}
	public static void setPMITranslationWizard(PMITranslationWizard translationWizard)
	{
		PMIWizardDialog.pmiTranslationWizard = translationWizard;
	}
	public static Session getTheSession()
	{
		return theSession;
	}
	public static void setTheSession(Session theSession)
	{
		PMIWizardDialog.theSession = theSession;
	}
	public static UFSession getTheUFSession()
	{
		return theUFSession;
	}
	public static void setTheUFSession(UFSession theUFSession)
	{
		PMIWizardDialog.theUFSession = theUFSession;
	}
	public static UI getTheUI()
	{
		return theUI;
	}
	public static void setTheUI(UI theUI)
	{
		PMIWizardDialog.theUI = theUI;
	}
	public String getTheDlxFileName()
	{
		return theDlxFileName;
	}
	public void setTheDlxFileName(String theDlxFileName)
	{
		this.theDlxFileName = theDlxFileName;
	}
	public nxopen.blockstyler.BlockDialog getTheDialog()
	{
		return theDialog;
	}
	public void setTheDialog(nxopen.blockstyler.BlockDialog theDialog)
	{
		this.theDialog = theDialog;
	}
	public nxopen.blockstyler.Wizard getWizard()
	{
		return wizard;
	}
	public void setWizard(nxopen.blockstyler.Wizard wizard)
	{
		this.wizard = wizard;
	}
	public nxopen.blockstyler.Group getWizardStepSelectPart()
	{
		return wizardStepSelectPart;
	}
	public void setWizardStepSelectPart(nxopen.blockstyler.Group wizardStepSelectPart)
	{
		this.wizardStepSelectPart = wizardStepSelectPart;
	}
	public FileSelection getPartFileBrowser()
	{
		return partFileBrowser;
	}
	public void setPartFileBrowser(FileSelection nativeFileBrowser0)
	{
		this.partFileBrowser = nativeFileBrowser0;
	}
	public nxopen.blockstyler.Group getWizardStepSelectPMIObjects()
	{
		return wizardStepSelectPMIObjects;
	}
	public void setWizardStepSelectPMIObjects(nxopen.blockstyler.Group wizardStepSelectPMIObjects)
	{
		this.wizardStepSelectPMIObjects = wizardStepSelectPMIObjects;
	}
	public Toggle getToggleAnnotations()
	{
		return toggleAnnotations;
	}
	public void setToggleAnnotations(Toggle toggleAnnotations)
	{
		this.toggleAnnotations = toggleAnnotations;
	}
	public Toggle getToggleDimensions()
	{
		return toggleDimensions;
	}
	public void setToggleDimensions(Toggle toggleDimensions)
	{
		this.toggleDimensions = toggleDimensions;
	}
	public Toggle getToggleSurfaceFinishes()
	{
		return toggleSurfaceFinishes;
	}
	public void setToggleSurfaceFinishes(Toggle toggleFaceFinishes)
	{
		this.toggleSurfaceFinishes = toggleFaceFinishes;
	}
	public nxopen.blockstyler.Group getWizardStepObjects()
	{
		return wizardStepObjects;
	}
	public void setWizardStepObjects(nxopen.blockstyler.Group wizardStepObjects)
	{
		this.wizardStepObjects = wizardStepObjects;
	}
	public TabControl getTabControl()
	{
		return tabControl;
	}
	public void setTabControl(TabControl tabControl)
	{
		this.tabControl = tabControl;
	}
	public nxopen.blockstyler.Group getTabMasterAnnotations()
	{
		return tabMasterAnnotations;
	}
	public nxopen.blockstyler.Group getTabMasterDimensions()
	{
		return tabMasterDimensions;
	}
	public void setTabMasterDimensions(nxopen.blockstyler.Group tabPage1)
	{
		this.tabMasterAnnotations = tabPage1;
	}
	public Tree getMasterAnnotationsTree()
	{
		return masterAnnotationsTree;
	}
	public void setMasterAnnotationsTree(Tree masterAnnotations)
	{
		this.masterAnnotationsTree = masterAnnotations;
	}
	public void setTabPage2(nxopen.blockstyler.Group tabPage2)
	{
		this.tabMasterDimensions = tabPage2;
	}
	public Tree getMasterDimensionsTree()
	{
		return masterDimensionsTree;
	}
	public void setMasterDimensionsTree(Tree masterDimensions)
	{
		this.masterDimensionsTree = masterDimensions;
	}
	public nxopen.blockstyler.Group getTabMasterSurfaceFinishes()
	{
		return tabMasterSurfaceFinishes;
	}
	public void setTabMasterSurfaceFinishes(nxopen.blockstyler.Group tabPage)
	{
		this.tabMasterSurfaceFinishes = tabPage;
	}
	public Tree getMasterSurfaceFinishesTree()
	{
		return masterSurfaceFinishesTree;
	}
	public void setMasterSurfaceFinishesTree(Tree masterFaceFinishes)
	{
		this.masterSurfaceFinishesTree = masterFaceFinishes;
	}
	public CurveCollector getDimensionEdgeSelect()
	{
		return dimensionEdgeSelect;
	}
	public void setDimensionEdgeSelect(CurveCollector dimensionEdgeSelect)
	{
		this.dimensionEdgeSelect = dimensionEdgeSelect;
	}
	public Button getTranslateDimensionButton()
	{
		return translateDimensionButton;
	}
	public void setTranslateDimensionButton(Button translateDimensionButton)
	{
		this.translateDimensionButton = translateDimensionButton;
	}
	public FaceCollector getSurfaceFinishFaceSelect()
	{
		return surfaceFinishSurfaceSelect;
	}
	public void setSurfaceFinishFaceSelect(FaceCollector surfaceFinishFaceSelect)
	{
		this.surfaceFinishSurfaceSelect = surfaceFinishFaceSelect;
	}
	public nxopen.blockstyler.SpecifyPlane getAnnotationPlane()
	{
		return annotationPlane;
	}
	public void setAnnotationPlane(nxopen.blockstyler.SpecifyPlane annotationPlane)
	{
		this.annotationPlane = annotationPlane;
	}
	public nxopen.blockstyler.SpecifyPoint getSurfaceFinishLocation()
	{
		return surfaceFinishLocation;
	}
	public void setSurfaceFinishLocation(
			nxopen.blockstyler.SpecifyPoint surfaceFinishLocation)
	{
		this.surfaceFinishLocation = surfaceFinishLocation;
	}
    
}
