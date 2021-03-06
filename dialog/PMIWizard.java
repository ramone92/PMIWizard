//==============================================================================
//  WARNING!!  This file is overwritten by the Block UI Styler while generating
//  the automation code. Any modifications to this file will be lost after
//  generating the code again.
//
//       Filename:  D:\EclipseWorkspace\PMIWizard\dialog\PMIWizard.java
//
//        This file was generated by the NX Block UI Styler
//        Created by: zikt
//              Version: NX 8.5
//              Date: 06-03-2015  (Format: mm-dd-yyyy)
//              Time: 00:41 (Format: hh-mm)
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

//------------------------------------------------------------------------------
//Represents Block Styler application class
//------------------------------------------------------------------------------
public class PMIWizard implements BlockDialog.Initialize, BlockDialog.DialogShown, BlockDialog.Update
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
    private nxopen.blockstyler.Group wizardStepSelectAnnotationPlane;// Block type: Group
    private nxopen.blockstyler.SpecifyPlane annotationPlane;// Block type: Specify Plane
    private nxopen.blockstyler.Group wizardStepObjects;// Block type: Group
    private nxopen.blockstyler.TabControl tabControl;// Block type: Tabs Page
    private nxopen.blockstyler.Group tabMasterAnnotations;// Block type: Group
    private nxopen.blockstyler.Tree masterAnnotationsTree;// Block type: Tree Control
    private nxopen.blockstyler.Group tabMasterDimensions;// Block type: Group
    private nxopen.blockstyler.Tree masterDimensionsTree;// Block type: Tree Control
    private nxopen.blockstyler.CurveCollector dimensionEdgeSelect;// Block type: Curve Collector
    private nxopen.blockstyler.Button translateDimensionButton;// Block type: Button
    private nxopen.blockstyler.Button dimensionsClearButton;// Block type: Button
    private nxopen.blockstyler.Group tabMasterSurfaceFinishes;// Block type: Group
    private nxopen.blockstyler.Tree masterSurfaceFinishesTree;// Block type: Tree Control
    private nxopen.blockstyler.SpecifyPoint surfaceFinishLocation;// Block type: Specify Point
    private nxopen.blockstyler.FaceCollector surfaceFinishSurfaceSelect;// Block type: Face Collector
    private nxopen.blockstyler.Button translateSurfaceFinishButton;// Block type: Button
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
    //Bit Option for Property: SnapPointTypesEnabled
    //------------------------------------------------------------------------------
    public static final int              SnapPointTypesEnabled_UserDefined = (1 << 0);
    public static final int                 SnapPointTypesEnabled_Inferred = (1 << 1);
    public static final int           SnapPointTypesEnabled_ScreenPosition = (1 << 2);
    public static final int                 SnapPointTypesEnabled_EndPoint = (1 << 3);
    public static final int                 SnapPointTypesEnabled_MidPoint = (1 << 4);
    public static final int             SnapPointTypesEnabled_ControlPoint = (1 << 5);
    public static final int             SnapPointTypesEnabled_Intersection = (1 << 6);
    public static final int                SnapPointTypesEnabled_ArcCenter = (1 << 7);
    public static final int            SnapPointTypesEnabled_QuadrantPoint = (1 << 8);
    public static final int            SnapPointTypesEnabled_ExistingPoint = (1 << 9);
    public static final int             SnapPointTypesEnabled_PointonCurve = (1 <<10);
    public static final int           SnapPointTypesEnabled_PointonSurface = (1 <<11);
    public static final int         SnapPointTypesEnabled_PointConstructor = (1 <<12);
    public static final int     SnapPointTypesEnabled_TwocurveIntersection = (1 <<13);
    public static final int             SnapPointTypesEnabled_TangentPoint = (1 <<14);
    public static final int                    SnapPointTypesEnabled_Poles = (1 <<15);
    public static final int         SnapPointTypesEnabled_BoundedGridPoint = (1 <<16);
    //------------------------------------------------------------------------------
    //Bit Option for Property: SnapPointTypesOnByDefault
    //------------------------------------------------------------------------------
    public static final int          SnapPointTypesOnByDefault_UserDefined = (1 << 0);
    public static final int             SnapPointTypesOnByDefault_Inferred = (1 << 1);
    public static final int       SnapPointTypesOnByDefault_ScreenPosition = (1 << 2);
    public static final int             SnapPointTypesOnByDefault_EndPoint = (1 << 3);
    public static final int             SnapPointTypesOnByDefault_MidPoint = (1 << 4);
    public static final int         SnapPointTypesOnByDefault_ControlPoint = (1 << 5);
    public static final int         SnapPointTypesOnByDefault_Intersection = (1 << 6);
    public static final int            SnapPointTypesOnByDefault_ArcCenter = (1 << 7);
    public static final int        SnapPointTypesOnByDefault_QuadrantPoint = (1 << 8);
    public static final int        SnapPointTypesOnByDefault_ExistingPoint = (1 << 9);
    public static final int         SnapPointTypesOnByDefault_PointonCurve = (1 <<10);
    public static final int       SnapPointTypesOnByDefault_PointonSurface = (1 <<11);
    public static final int     SnapPointTypesOnByDefault_PointConstructor = (1 <<12);
    public static final int SnapPointTypesOnByDefault_TwocurveIntersection = (1 <<13);
    public static final int         SnapPointTypesOnByDefault_TangentPoint = (1 <<14);
    public static final int                SnapPointTypesOnByDefault_Poles = (1 <<15);
    public static final int     SnapPointTypesOnByDefault_BoundedGridPoint = (1 <<16);
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
    
    //------------------------------------------------------------------------------
    //Constructor for NX Styler class
    //------------------------------------------------------------------------------
    public PMIWizard() throws Exception, RemoteException
    {
        try
        {
            theSession = (Session)SessionFactory.get("Session");
            theUI = (UI)SessionFactory.get("UI");
            theDlxFileName = "PMIWizard.dlx";
            theDialog = theUI.createDialog(theDlxFileName);
            theDialog.addUpdateHandler(this);
            theDialog.addInitializeHandler(this);
            theDialog.addDialogShownHandler(this);
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
        PMIWizard thePMIWizard = null;
        try
        {
            thePMIWizard = new PMIWizard();
            // The following method shows the dialog immediately
            thePMIWizard.show();
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
        finally
        {
            if(thePMIWizard != null)
            {
                thePMIWizard.dispose();
                thePMIWizard = null;
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
            wizardStepSelectPart = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStepSelectPart");
            partFileBrowser = (nxopen.blockstyler.FileSelection)theDialog.topBlock().findBlock("partFileBrowser");
            wizardStepSelectPMIObjects = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStepSelectPMIObjects");
            toggleAnnotations = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleAnnotations");
            toggleDimensions = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleDimensions");
            toggleSurfaceFinishes = (nxopen.blockstyler.Toggle)theDialog.topBlock().findBlock("toggleSurfaceFinishes");
            wizardStepSelectAnnotationPlane = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStepSelectAnnotationPlane");
            annotationPlane = (nxopen.blockstyler.SpecifyPlane)theDialog.topBlock().findBlock("annotationPlane");
            wizardStepObjects = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("wizardStepObjects");
            tabControl = (nxopen.blockstyler.TabControl)theDialog.topBlock().findBlock("tabControl");
            tabMasterAnnotations = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabMasterAnnotations");
            masterAnnotationsTree = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterAnnotationsTree");
            tabMasterDimensions = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabMasterDimensions");
            masterDimensionsTree = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterDimensionsTree");
            dimensionEdgeSelect = (nxopen.blockstyler.CurveCollector)theDialog.topBlock().findBlock("dimensionEdgeSelect");
            translateDimensionButton = (nxopen.blockstyler.Button)theDialog.topBlock().findBlock("translateDimensionButton");
            dimensionsClearButton = (nxopen.blockstyler.Button)theDialog.topBlock().findBlock("dimensionsClearButton");
            tabMasterSurfaceFinishes = (nxopen.blockstyler.Group)theDialog.topBlock().findBlock("tabMasterSurfaceFinishes");
            masterSurfaceFinishesTree = (nxopen.blockstyler.Tree)theDialog.topBlock().findBlock("masterSurfaceFinishesTree");
            surfaceFinishLocation = (nxopen.blockstyler.SpecifyPoint)theDialog.topBlock().findBlock("surfaceFinishLocation");
            surfaceFinishSurfaceSelect = (nxopen.blockstyler.FaceCollector)theDialog.topBlock().findBlock("surfaceFinishSurfaceSelect");
            translateSurfaceFinishButton = (nxopen.blockstyler.Button)theDialog.topBlock().findBlock("translateSurfaceFinishButton");
            surfaceFinishClearButton = (nxopen.blockstyler.Button)theDialog.topBlock().findBlock("surfaceFinishClearButton");
            //------------------------------------------------------------------------------
            //Registration of Treelist specific callbacks
            //------------------------------------------------------------------------------
            //masterAnnotationsTree.setOnExpandHandler(this);
            
            //masterAnnotationsTree.setOnInsertColumnHandler(this);
            
            //masterAnnotationsTree.setOnInsertNodeHandler(this);
            
            //masterAnnotationsTree.setOnDeleteNodeHandler(this);
            
            //masterAnnotationsTree.setOnPreSelectHandler(this);
            
            //masterAnnotationsTree.setOnSelectHandler(this);
            
            //masterAnnotationsTree.setOnStateChangeHandler(this);
            
            //masterAnnotationsTree.setToolTipTextHandler(this);
            
            //masterAnnotationsTree.setColumnSortHandler(this);
            
            //masterAnnotationsTree.setStateIconNameHandler(this);
            
            //masterAnnotationsTree.setOnBeginLabelEditHandler(this);
            
            //masterAnnotationsTree.setOnEndLabelEditHandler(this);
            
            //masterAnnotationsTree.setOnEditOptionSelectedHandler(this);
            
            //masterAnnotationsTree.setAskEditControlHandler(this);
            
            //masterAnnotationsTree.setOnMenuHandler(this);
            
            //masterAnnotationsTree.setOnMenuSelectionHandler(this);
            
            //masterAnnotationsTree.setIsDropAllowedHandler(this);
            
            //masterAnnotationsTree.setIsDragAllowedHandler(this);
            
            //masterAnnotationsTree.setOnDropHandler(this);
            
            //masterAnnotationsTree.setOnDropMenuHandler(this);
            
            //masterAnnotationsTree.setOnDefaultActionHandler(this);
            
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
            //---- Enter your callback code here -----
        }
        catch(Exception ex)
        {
            //---- Enter your exception handling code here -----
            theUI.nxmessageBox().show("Block Styler", nxopen.NXMessageBox.DialogType.ERROR, ex.getMessage());
        }
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
            else if(block == annotationPlane)
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
            }
            else if(block == dimensionsClearButton)
            {
            //---------Enter your code here-----------
            }
            else if(block == surfaceFinishLocation)
            {
            //---------Enter your code here-----------
            }
            else if(block == surfaceFinishSurfaceSelect)
            {
            //---------Enter your code here-----------
            }
            else if(block == translateSurfaceFinishButton)
            {
            //---------Enter your code here-----------
            }
            else if(block == surfaceFinishClearButton)
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
    
    //public void onSelectCallback(Tree tree, Node node, int columnID, boolean selected)throws NXException, RemoteException
    //{
    //}
    
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
    
}
