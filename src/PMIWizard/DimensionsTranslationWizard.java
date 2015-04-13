package PMIWizard;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nxopen.*;
import nxopen.annotations.*;
import nxopen.blockstyler.*;

public class DimensionsTranslationWizard extends PMITranslationWizard implements Tree.OnSelectCallback
{

	private Tree masterDimensionsTree;
	private ArrayList<Dimension> masterDimensionsList;
	private ArrayList<String> masterDimensionsTypesList;
	
	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------

    //This constructor invokes from PMITranslationWizard.callDimensionsTranslationWizard()
	public DimensionsTranslationWizard(PMITranslationWizard pmitw) throws NXException, RemoteException
	{
		print("*** 3. DimensionsTranslationWizard created ***");
		//getMasterDimensions();
		setPmiWizardDialog(pmitw.getPmiWizardDialog());
		
		setMasterPart(pmitw.getMasterPart());
		setWorkPart(pmitw.getWorkPart());
		
		Tree masterTree = pmitw.getPmiWizardDialog().getMasterDimensionsTree();
		masterTree.setOnSelectHandler(this);
		setMasterDimensionsTree(masterTree);				
		//test();
	}
	
	//------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------
	@Deprecated
	private void fillTreeTest() throws RemoteException, NXException
	{
		getMasterDimensionsTree().insertColumn(1, "Имя объекта", 500);
    	Node newNode;
    	for (int i = 0; i < 5; i++)
		{
    		newNode = getMasterDimensionsTree().createNode("dimension node: " + i);
    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);        	
		}
		
	}

	private void getMasterDimensions() throws RemoteException
	{
		AnnotationManager masterAnnotationManager = super.getMasterPart().annotations();
		// TODO find previous work to get dimensions information form master part
	}
	
	// Detects types of all master part dimensions (parallel dim, radius dim)
	// This types should be nodes in mastedDimensionsTree
	@Deprecated
	private void getMasterDimensionsTypes()
	{
		String className;
		for (Dimension dimension : getMasterDimensionsList())
		{
			className = dimension.getClass().getName();
			if (!getMasterDimensionsTypesList().contains(className))
				getMasterDimensionsTypesList().add(className);
		}
		
	}

	public void test() throws RemoteException, NXException
	{
		try
		{
			print("\n---------------start of test function---------------\n");
			
			AnnotationManager annotationManager = getMasterPart().annotations();
			PmiManager pmiManager = getMasterPart().pmiManager();
			PmiCollection pmiCollection = pmiManager.pmis();
			
			TaggedObjectCollection.Iterator it = pmiCollection.iterator();
			Object taggedObject;
			Pmi pmi;
			
			getMasterDimensionsTree().insertColumn(0, "Dimension", 500);
			
			while(it.hasNext())
			{
				taggedObject = it.next();				
				pmi = (Pmi) taggedObject;
				print("*******************");
				Annotation an = pmi.getDisplayInstances()[0];
		
				String className;			
				String nodeText;
				
				Dimension dim;
				
				if (an instanceof Dimension)
				{
					dim = (Dimension) an;
					
					className = an.getClass().getSimpleName();
					nodeText = className + ": " + dim.computedSize();
					
					Node newNode = getMasterDimensionsTree().createNode(nodeText);
		    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);       
				}
				AssociatedObject ao = pmi.getAssociatedObject();
				DisplayableObject[] dispObjs = ao.getObjects();
				for (DisplayableObject dispObject : dispObjs)
				{
					print(dispObject.journalIdentifier());
				}
			}
		} 
		catch (RemoteException | NXException ex)
		{
			StringWriter s = new StringWriter();
            PrintWriter p = new PrintWriter(s);
            p.println("Caught exception " + ex );
            ex.printStackTrace(p);
            getTheUFSession().ui().writeListingWindow("\n***Failed***");
            getTheUFSession().ui().writeListingWindow("\n"+ex.getMessage());
            getTheUFSession().ui().writeListingWindow("\n"+s.getBuffer().toString());		
		}
	}

	public void createDimension() throws RemoteException, NXException
	{
		try
		{	
			nxopen.annotations.DimensionData dimensionData1;
		    dimensionData1 = getWorkPart().annotations().newDimensionData();
		    
		    nxopen.annotations.Associativity associativity1;
		    associativity1 = getWorkPart().annotations().newAssociativity();
		    
		    nxopen.features.Extrude extrude1 = ((nxopen.features.Extrude)getWorkPart().features().findObject("EXTRUDE(2)"));
		    Edge edge1 = ((Edge)extrude1.findObject("EDGE * 140 * 150 {(100,0,100)(100,0,50)(100,0,0) EXTRUDE(2)}"));
		    associativity1.setFirstObject(edge1);
		    
		    NXObject nullNXObject = null;
		    associativity1.setSecondObject(nullNXObject);
		    
		    associativity1.setObjectView(getWorkPart().modelingViews().workView());
		    
		    associativity1.setPointOption(nxopen.annotations.AssociativityPointOption.CONTROL);
		    
		    associativity1.setLineOption(nxopen.annotations.AssociativityLineOption.NONE);
		    
		    Point3d firstDefinitionPoint1 = new Point3d(0.0, 0.0, 0.0);
		    associativity1.setFirstDefinitionPoint(firstDefinitionPoint1);
		    
		    Point3d secondDefinitionPoint1 = new Point3d(0.0, 0.0, 0.0);
		    associativity1.setSecondDefinitionPoint(secondDefinitionPoint1);
		    
		    associativity1.setAngle(0.0);
		    
		    Point3d pickPoint1 = new Point3d(100.0, 0.0, 100.0);
		    associativity1.setPickPoint(pickPoint1);
		    
		    nxopen.annotations.Associativity [] associativity2  = new nxopen.annotations.Associativity[1];
		    associativity2[0] = associativity1;
		    dimensionData1.setAssociativity(1, associativity2);
		    
		    associativity1.dispose();
		    associativity1 = null;
		    
		    nxopen.annotations.Associativity associativity3;
		    associativity3 = getWorkPart().annotations().newAssociativity();
		    
		    associativity3.setFirstObject(edge1);
		    
		    associativity3.setSecondObject(nullNXObject);
		    
		    associativity3.setObjectView(getWorkPart().modelingViews().workView());
		    
		    associativity3.setPointOption(nxopen.annotations.AssociativityPointOption.CONTROL);
		    
		    associativity3.setLineOption(nxopen.annotations.AssociativityLineOption.NONE);
		    
		    Point3d firstDefinitionPoint2 = new Point3d(0.0, 0.0, 0.0);
		    associativity3.setFirstDefinitionPoint(firstDefinitionPoint2);
		    
		    Point3d secondDefinitionPoint2 = new Point3d(0.0, 0.0, 0.0);
		    associativity3.setSecondDefinitionPoint(secondDefinitionPoint2);
		    
		    associativity3.setAngle(0.0);
		    
		    Point3d pickPoint2 = new Point3d(100.0, 0.0, 0.0);
		    associativity3.setPickPoint(pickPoint2);
		    
		    nxopen.annotations.Associativity [] associativity4  = new nxopen.annotations.Associativity[1];
		    associativity4[0] = associativity3;
		    dimensionData1.setAssociativity(2, associativity4);
		    
		    associativity3.dispose();
		    associativity3 = null;
		    
		    nxopen.annotations.DimensionPreferences dimensionPreferences1;
		    dimensionPreferences1 = getWorkPart().annotations().preferences().getDimensionPreferences();
		    
		    nxopen.annotations.OrdinateDimensionPreferences ordinateDimensionPreferences1;
		    ordinateDimensionPreferences1 = dimensionPreferences1.getOrdinateDimensionPreferences();
		    
		    ordinateDimensionPreferences1.dispose();
		    ordinateDimensionPreferences1 = null;
		    
		    nxopen.annotations.ChamferDimensionPreferences chamferDimensionPreferences1;
		    chamferDimensionPreferences1 = dimensionPreferences1.getChamferDimensionPreferences();
		    
		    chamferDimensionPreferences1.dispose();
		    chamferDimensionPreferences1 = null;
		    
		    nxopen.annotations.NarrowDimensionPreferences narrowDimensionPreferences1;
		    narrowDimensionPreferences1 = dimensionPreferences1.getNarrowDimensionPreferences();
		    
		    narrowDimensionPreferences1.dispose();
		    narrowDimensionPreferences1 = null;
		    
		    nxopen.annotations.UnitsFormatPreferences unitsFormatPreferences1;
		    unitsFormatPreferences1 = dimensionPreferences1.getUnitsFormatPreferences();
		    
		    unitsFormatPreferences1.dispose();
		    unitsFormatPreferences1 = null;
		    
		    nxopen.annotations.DiameterRadiusPreferences diameterRadiusPreferences1;
		    diameterRadiusPreferences1 = dimensionPreferences1.getDiameterRadiusPreferences();
		    
		    diameterRadiusPreferences1.dispose();
		    diameterRadiusPreferences1 = null;
		    
		    dimensionData1.setDimensionPreferences(dimensionPreferences1);
		    
		    dimensionPreferences1.dispose();
		    dimensionPreferences1 = null;
		    
		    nxopen.annotations.LineAndArrowPreferences lineAndArrowPreferences1;
		    lineAndArrowPreferences1 = getWorkPart().annotations().preferences().getLineAndArrowPreferences();
		    
		    dimensionData1.setLineAndArrowPreferences(lineAndArrowPreferences1);
		    
		    lineAndArrowPreferences1.dispose();
		    lineAndArrowPreferences1 = null;
		    
		    nxopen.annotations.LetteringPreferences letteringPreferences1;
		    letteringPreferences1 = getWorkPart().annotations().preferences().getLetteringPreferences();
		    
		    dimensionData1.setLetteringPreferences(letteringPreferences1);
		    
		    letteringPreferences1.dispose();
		    letteringPreferences1 = null;
		    
		    nxopen.annotations.UserSymbolPreferences userSymbolPreferences1;
		    userSymbolPreferences1 = getWorkPart().annotations().newUserSymbolPreferences(nxopen.annotations.UserSymbolPreferences.SizeType.SCALE_ASPECT_RATIO, 1.0, 1.0);
		    
		    dimensionData1.setUserSymbolPreferences(userSymbolPreferences1);
		    
		    userSymbolPreferences1.dispose();
		    userSymbolPreferences1 = null;
		    
		    nxopen.annotations.LinearTolerance linearTolerance1;
		    linearTolerance1 = getWorkPart().annotations().preferences().getLinearTolerances();
		    
		    dimensionData1.setLinearTolerance(linearTolerance1);
		    
		    linearTolerance1.dispose();
		    linearTolerance1 = null;
		    
		    nxopen.annotations.AngularTolerance angularTolerance1;
		    angularTolerance1 = getWorkPart().annotations().preferences().getAngularTolerances();
		    
		    nxopen.annotations.Value lowerToleranceDegrees1 = new nxopen.annotations.Value();
		    lowerToleranceDegrees1.itemValue = -0.1;
		    Expression nullExpression = null;
		    lowerToleranceDegrees1.valueExpression = nullExpression;
		    lowerToleranceDegrees1.valuePrecision = 2;
		    angularTolerance1.setLowerToleranceDegrees(lowerToleranceDegrees1);
		    
		    nxopen.annotations.Value upperToleranceDegrees1 = new nxopen.annotations.Value();
		    upperToleranceDegrees1.itemValue = 0.1;
		    upperToleranceDegrees1.valueExpression = nullExpression;
		    upperToleranceDegrees1.valuePrecision = 2;
		    angularTolerance1.setUpperToleranceDegrees(upperToleranceDegrees1);
		    
		    dimensionData1.setAngularTolerance(angularTolerance1);
		    
		    angularTolerance1.dispose();
		    angularTolerance1 = null;
		    
		    nxopen.annotations.AppendedText appendedText1;
		    appendedText1 = getWorkPart().annotations().newAppendedText();
		    
		    String [] lines1  = new String[0];
		    appendedText1.setAboveText(lines1);
		    
		    String [] lines2  = new String[0];
		    appendedText1.setAfterText(lines2);
		    
		    String [] lines3  = new String[0];
		    appendedText1.setBeforeText(lines3);
		    
		    String [] lines4  = new String[0];
		    appendedText1.setBelowText(lines4);
		    
		    dimensionData1.setAppendedText(appendedText1);
		    
		    appendedText1.dispose();
		    appendedText1 = null;
		    
		    nxopen.annotations.PmiData pmiData1;
		    pmiData1 = getWorkPart().annotations().newPmiData();
		    
		    nxopen.annotations.BusinessModifier [] businessModifiers1  = new nxopen.annotations.BusinessModifier[0];
		    pmiData1.setBusinessModifiers(businessModifiers1);
		    
		    Xform xform3;
		    xform3 = dimensionData1.getInferredPlane(nxopen.annotations.PmiDefaultPlane.YZ_OF_WCS, nxopen.annotations.DimensionType.VERTICAL);
		    
		    Point3d origin1 = new Point3d(0.0, 0.0, 0.0);
		    nxopen.annotations.PmiVerticalDimension pmiVerticalDimension1;
		    pmiVerticalDimension1 = getWorkPart().dimensions().createPmiVerticalDimension(dimensionData1, pmiData1, xform3, origin1);
		    
		    dimensionData1.dispose();
		    dimensionData1 = null;
		    
		    pmiData1.dispose();
		    pmiData1 = null;
		   
		    pmiVerticalDimension1.setOriginCentered(true);
		    
		    Point3d origin2 = new Point3d(100.0, -315.991663808061, 50.0);
		    pmiVerticalDimension1.setAnnotationOrigin(origin2);
		    
		    pmiVerticalDimension1.setLeaderOrientation(nxopen.annotations.LeaderOrientation.FROM_LEFT);
		    
		}
		catch (NXException ex)
		{
			StringWriter s = new StringWriter();
            PrintWriter p = new PrintWriter(s);
            p.println("Caught exception " + ex );
            ex.printStackTrace(p);
            getTheUFSession().ui().writeListingWindow("\n***Failed***");
            getTheUFSession().ui().writeListingWindow("\n"+ex.getMessage());
            getTheUFSession().ui().writeListingWindow("\n"+s.getBuffer().toString());
		}
	}
	
	//------------------------------------------------------------------------------
    // Public methods
    //------------------------------------------------------------------------------
	
	public void translate() throws RemoteException, NXException
	{
		if (getPmiWizardDialog().getDimensionEdgeSelect().getSelectedObjects().length == 0)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Укажите ребро или грань");
			return;
		}

		if (getMasterDimensionsTree().getSelectedNodes().length == 0)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Выберите размер для трансляции");
			return;								
		}
		
		print("\nStarting dimension translation...");
		
		//TODO Make dimension creation
		createDimension();
		
		// Clear selection
		getPmiWizardDialog().getDimensionEdgeSelect().setSelectedObjects(new TaggedObject[0]);		
	}
	
	public void fillTree() throws RemoteException, NXException
	{
		getMasterDimensionsTree().insertColumn(1, "Имя объекта", 500);
    	Node newNode;
    	Node rootNode;

    	//Currently not used
    	/*for (String dimensionType : getMasterDimensionsTypesList())
		{	
    		rootNode = getMasterDimensionsTree().createNode(dimensionType);			
    		getMasterDimensionsTree().insertNode(rootNode, null, null, Tree.NodeInsertOption.LAST);
		}*/

    	for (Dimension dimension : masterDimensionsList)
		{
    		newNode = getMasterDimensionsTree().createNode(dimension.name());
    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);
		}
    	
	}	

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	
	public Tree getMasterDimensionsTree()
	{
		//print("***getMasterDimensionsTree is null:" + (masterDimensionsTree == null));
		return masterDimensionsTree;
	}

	public void setMasterDimensionsTree(Tree masterDimensionsTree)
	{
		//print("setMasterDimensionsTree");
		this.masterDimensionsTree = masterDimensionsTree;
	}

	public ArrayList<Dimension> getMasterDimensionsList()
	{
		return masterDimensionsList;
	}

	public void setMasterDimensionsList(ArrayList<Dimension> masterDimensionsList)
	{
		this.masterDimensionsList = masterDimensionsList;
	}

	public ArrayList<String> getMasterDimensionsTypesList()
	{
		return masterDimensionsTypesList;
	}

	public void setMasterDimensionsTypesList(ArrayList<String> masterDimensionsTypeList)
	{
		this.masterDimensionsTypesList = masterDimensionsTypeList;
	}

	@Override
	public void onSelectCallback(Tree tree, Node node, int columnID, boolean selected) throws NXException, RemoteException
	{
		/*if (selected)
    	{
    		print(node.displayText());    		
    	}*/		
	}

}
