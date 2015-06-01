package PMIWizard;

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

	private Plane annotationPlane;
	
	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------

    //This constructor invokes from PMITranslationWizard.callDimensionsTranslationWizard()
	public DimensionsTranslationWizard(PMITranslationWizard pmitw) throws NXException, RemoteException
	{
//		print("*** DimensionsTranslationWizard created ***");
		//getMasterDimensions();
		setPmiWizardDialog(pmitw.getPmiWizardDialog());
		
		setMasterPart(pmitw.getMasterPart());
		setWorkPart(pmitw.getWorkPart());
		
		setMasterDimensionsList(new ArrayList<Dimension>());
		
		Tree masterTree = pmitw.getPmiWizardDialog().getMasterDimensionsTree();
		masterTree.setOnSelectHandler(this);
		setMasterDimensionsTree(masterTree);				
		//test();
		
		setAnnotationPlane((Plane)pmitw.getPmiWizardDialog().getAnnotationPlane().getSelectedObjects()[0]);
	}
	
	//------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------

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
	
	private void fillTree() throws RemoteException, NXException
	{
		try
		{
//			print("\n---------------start of fillDimensionTree function---------------\n");
			
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
				//print("*******************");
				Annotation an = pmi.getDisplayInstances()[0];
		
				String className;			
				String nodeText;
				
				Dimension dim;
				ArrayList<Dimension> mdl = getMasterDimensionsList();
				
				if (an instanceof Dimension)
				{
					dim = (Dimension) an;
					
					mdl.add(dim);
					
					className = an.getClass().getSimpleName();
					nodeText = className(className) + ": " + dim.computedSize();
					
					Node newNode = getMasterDimensionsTree().createNode(nodeText);
		    		getMasterDimensionsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);       
				}
				AssociatedObject ao = pmi.getAssociatedObject();
				DisplayableObject[] dispObjs = ao.getObjects();
				for (DisplayableObject dispObject : dispObjs)
				{
					//print(dispObject.journalIdentifier());
				}
				
				setMasterDimensionsList(mdl);
			}
		} 
		catch (RemoteException | NXException ex)
		{
			catchException(ex);					
		}
    	
	}	
	
	private void createDimension() throws RemoteException, NXException
	{
		String dimensionName = getMasterDimensionsTree().getSelectedNodes()[0].displayText().split(":")[0];
		//print("dimensionName = " + dimensionName);
//		print("AnnotationPlane = " + getAnnotationPlane().method().name());
		switch (dimensionName)
		{
		case "PmiVerticalDimension":
			createVerticalDimension();			
			break;
			
		case "PmiHorizontalDimension":
			createHorizontalDimension();			
			break;
			
		case "PmiDiameterDimension":
			createDiameterDimension();			
			break;	

		default:
			print("No switch branch for such dimension");
			break;
		}		
	}
		
	private void createVerticalDimension() throws RemoteException, NXException
	{
//		print("createVerticalDimension");
		try
		{	
			Dimension dim = getMasterDimensionsList().get(0);
						
			nxopen.annotations.DimensionData dimensionData1;
		    dimensionData1 = getWorkPart().annotations().newDimensionData();
		    
		    nxopen.annotations.Associativity associativity1;
		    associativity1 = getWorkPart().annotations().newAssociativity();
		    
		    //nxopen.features.Extrude extrude1 = ((nxopen.features.Extrude)getWorkPart().features().findObject("EXTRUDE(2)"));
		    //Edge edge1 = ((Edge)extrude1.findObject("EDGE * 140 * 150 {(100,0,100)(100,0,50)(100,0,0) EXTRUDE(2)}"));
		    Edge edge1 = (Edge) getPmiWizardDialog().getDimensionEdgeSelect().getSelectedObjects()[0];
		    associativity1.setFirstObject(edge1);
		    
		    NXObject nullNXObject = null;
		    associativity1.setSecondObject(null);
		    
		    Edge.VerticesData verticesData = edge1.getVertices();
		    Point3d vertex1 = verticesData.vertex1;
		    Point3d vertex2 = verticesData.vertex2;
		    
		    associativity1.setObjectView(getWorkPart().modelingViews().workView());
		    
		    associativity1.setPointOption(nxopen.annotations.AssociativityPointOption.CONTROL);
		    
		    associativity1.setLineOption(nxopen.annotations.AssociativityLineOption.NONE);
		    
		    Point3d firstDefinitionPoint1 = new Point3d(0.0, 0.0, 0.0);
		    associativity1.setFirstDefinitionPoint(firstDefinitionPoint1);
		    
		    Point3d secondDefinitionPoint1 = new Point3d(0.0, 0.0, 0.0);
		    associativity1.setSecondDefinitionPoint(secondDefinitionPoint1);
		    
		    associativity1.setAngle(0.0);
		    
		    //Point3d pickPoint1 = new Point3d(100.0, 0.0, 100.0);
		    Point3d pickPoint1 = vertex1;
		    associativity1.setPickPoint(pickPoint1);
		    
		    nxopen.annotations.Associativity [] associativity2  = new nxopen.annotations.Associativity[1];
		    associativity2[0] = associativity1;
		    dimensionData1.setAssociativity(1, associativity2);
		    
		    associativity1.dispose();
		    associativity1 = null;
		    
		    nxopen.annotations.Associativity associativity3;
		    associativity3 = getWorkPart().annotations().newAssociativity();
		    
		    associativity3.setFirstObject(edge1);
		    
		    associativity3.setSecondObject(null);
		    
		    associativity3.setObjectView(getWorkPart().modelingViews().workView());
		    
		    associativity3.setPointOption(nxopen.annotations.AssociativityPointOption.CONTROL);
		    
		    associativity3.setLineOption(nxopen.annotations.AssociativityLineOption.NONE);
		    
		    Point3d firstDefinitionPoint2 = new Point3d(0.0, 0.0, 0.0);
		    associativity3.setFirstDefinitionPoint(firstDefinitionPoint2);
		    
		    Point3d secondDefinitionPoint2 = new Point3d(0.0, 0.0, 0.0);
		    associativity3.setSecondDefinitionPoint(secondDefinitionPoint2);
		    
		    associativity3.setAngle(0.0);
		    
		    //Point3d pickPoint2 = new Point3d(100.0, 0.0, 0.0);
		    Point3d pickPoint2 = vertex2;
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
		    
		    String[] lines1  = new String[0];
		    appendedText1.setAboveText(lines1);
		    
		    String[] lines2  = new String[0];
		    appendedText1.setAfterText(lines2);
		    
		    String[] lines3  = new String[0];
		    appendedText1.setBeforeText(lines3);
		    
		    String[] lines4  = new String[0];
		    appendedText1.setBelowText(lines4);
		    
		    dimensionData1.setAppendedText(appendedText1);
		    
		    appendedText1.dispose();
		    appendedText1 = null;
		    
		    nxopen.annotations.PmiData pmiData1;
		    pmiData1 = getWorkPart().annotations().newPmiData();
		    
		    /*nxopen.annotations.BusinessModifier [] businessModifiers1  = new nxopen.annotations.BusinessModifier[0];
		    pmiData1.setBusinessModifiers(businessModifiers1);
		    */
		    Xform xform3;
		    PmiDefaultPlane pmiDefaultPlane = PmiDefaultPlane.YZ_OF_WCS;
		    xform3 = dimensionData1.getInferredPlane(pmiDefaultPlane, nxopen.annotations.DimensionType.VERTICAL);
		    
		    Point3d origin1 = new Point3d(0.0, 0.0, 0.0);
		    nxopen.annotations.PmiVerticalDimension pmiVerticalDimension;
		    pmiVerticalDimension = getWorkPart().dimensions().createPmiVerticalDimension(dimensionData1, pmiData1, xform3, origin1);
		    
		    dimensionData1.dispose();
		    dimensionData1 = null;
		    
		    pmiData1.dispose();
		    pmiData1 = null;
		   
		    pmiVerticalDimension.setOriginCentered(true);
		    
		    // Dimension distance from part 
		    Point3d distancePoint = new Point3d(0.0, 0.0, 0.0);
		    switch (pmiDefaultPlane.ordinal())
			{
			case PmiDefaultPlane._XY_OF_WCS:
				distancePoint = new Point3d(pickPoint1.x+100.0, pickPoint1.y, pickPoint1.z);
				break;
				
			case PmiDefaultPlane._XZ_OF_WCS:
				distancePoint = new Point3d(pickPoint1.x, pickPoint1.y, pickPoint1.z+100.0);
				break;	

			case PmiDefaultPlane._YZ_OF_WCS:
				distancePoint = new Point3d(pickPoint1.x, pickPoint1.y-100.0, pickPoint1.z);
				break;
				
			}
		    //Point3d origin2 = new Point3d(100.0, -100.0, 50.0);
		    Point3d origin2 = distancePoint;
		    pmiVerticalDimension.setAnnotationOrigin(origin2);
		    
		    pmiVerticalDimension.setLeaderOrientation(nxopen.annotations.LeaderOrientation.FROM_LEFT);
		    
		}
		catch (NXException ex)
		{
			catchException(ex);
		}
	}
	
	private void createHorizontalDimension() throws RemoteException, NXException
	{
//		print("createHorizontalDimension");
		try
		{	
			Dimension dim = getMasterDimensionsList().get(0);
						
			nxopen.annotations.DimensionData dimensionData1;
		    dimensionData1 = getWorkPart().annotations().newDimensionData();
		    
		    nxopen.annotations.Associativity associativity1;
		    associativity1 = getWorkPart().annotations().newAssociativity();
		    
		    //nxopen.features.Extrude extrude1 = ((nxopen.features.Extrude)getWorkPart().features().findObject("EXTRUDE(2)"));
		    //Edge edge1 = ((Edge)extrude1.findObject("EDGE * 140 * 150 {(100,0,100)(100,0,50)(100,0,0) EXTRUDE(2)}"));
		    Edge edge1 = (Edge) getPmiWizardDialog().getDimensionEdgeSelect().getSelectedObjects()[0];
		    associativity1.setFirstObject(edge1);
		    
		    NXObject nullNXObject = null;
		    associativity1.setSecondObject(null);
		    
		    Edge.VerticesData verticesData = edge1.getVertices();
		    Point3d vertex1 = verticesData.vertex1;
		    Point3d vertex2 = verticesData.vertex2;
		    
		    associativity1.setObjectView(getWorkPart().modelingViews().workView());
		    
		    associativity1.setPointOption(nxopen.annotations.AssociativityPointOption.CONTROL);
		    
		    associativity1.setLineOption(nxopen.annotations.AssociativityLineOption.NONE);
		    
		    Point3d firstDefinitionPoint1 = new Point3d(0.0, 0.0, 0.0);
		    associativity1.setFirstDefinitionPoint(firstDefinitionPoint1);
		    
		    Point3d secondDefinitionPoint1 = new Point3d(0.0, 0.0, 0.0);
		    associativity1.setSecondDefinitionPoint(secondDefinitionPoint1);
		    
		    associativity1.setAngle(0.0);
		    
		    //Point3d pickPoint1 = new Point3d(100.0, 0.0, 100.0);
		    Point3d pickPoint1 = vertex1;
		    associativity1.setPickPoint(pickPoint1);
		    
		    nxopen.annotations.Associativity [] associativity2  = new nxopen.annotations.Associativity[1];
		    associativity2[0] = associativity1;
		    dimensionData1.setAssociativity(1, associativity2);
		    
		    associativity1.dispose();
		    associativity1 = null;
		    
		    nxopen.annotations.Associativity associativity3;
		    associativity3 = getWorkPart().annotations().newAssociativity();
		    
		    associativity3.setFirstObject(edge1);
		    
		    associativity3.setSecondObject(null);
		    
		    associativity3.setObjectView(getWorkPart().modelingViews().workView());
		    
		    associativity3.setPointOption(nxopen.annotations.AssociativityPointOption.CONTROL);
		    
		    associativity3.setLineOption(nxopen.annotations.AssociativityLineOption.NONE);
		    
		    Point3d firstDefinitionPoint2 = new Point3d(0.0, 0.0, 0.0);
		    associativity3.setFirstDefinitionPoint(firstDefinitionPoint2);
		    
		    Point3d secondDefinitionPoint2 = new Point3d(0.0, 0.0, 0.0);
		    associativity3.setSecondDefinitionPoint(secondDefinitionPoint2);
		    
		    associativity3.setAngle(0.0);
		    
		    //Point3d pickPoint2 = new Point3d(100.0, 0.0, 0.0);
		    Point3d pickPoint2 = vertex2;
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
		    
		    String[] lines1  = new String[0];
		    appendedText1.setAboveText(lines1);
		    
		    String[] lines2  = new String[0];
		    appendedText1.setAfterText(lines2);
		    
		    String[] lines3  = new String[0];
		    appendedText1.setBeforeText(lines3);
		    
		    String[] lines4  = new String[0];
		    appendedText1.setBelowText(lines4);
		    
		    dimensionData1.setAppendedText(appendedText1);
		    
		    appendedText1.dispose();
		    appendedText1 = null;
		    
		    nxopen.annotations.PmiData pmiData1;
		    pmiData1 = getWorkPart().annotations().newPmiData();
		    
		    /*nxopen.annotations.BusinessModifier [] businessModifiers1  = new nxopen.annotations.BusinessModifier[0];
		    pmiData1.setBusinessModifiers(businessModifiers1);
		    */
		    Xform xform3;
		    PmiDefaultPlane pmiDefaultPlane;
		    PlaneTypes.MethodType methodType = ((IPlane) getPmiWizardDialog().getAnnotationPlane().getSelectedObjects()[0]).method();
		    switch (methodType.ordinal())
			{
			case PlaneTypes.MethodType._FIXED_X:
				pmiDefaultPlane = PmiDefaultPlane.YZ_OF_WCS;
				break;
				
			case PlaneTypes.MethodType._FIXED_Y:
				pmiDefaultPlane = PmiDefaultPlane.XZ_OF_WCS;
				break;
				
			case PlaneTypes.MethodType._FIXED_Z:
				pmiDefaultPlane = PmiDefaultPlane.XY_OF_WCS;
				break;	

			default:
				pmiDefaultPlane = PmiDefaultPlane.YZ_OF_WCS;
				break;
			}
		    
		    xform3 = dimensionData1.getInferredPlane(pmiDefaultPlane, nxopen.annotations.DimensionType.HORIZONTAL);
		    
		    Point3d origin1 = new Point3d(0.0, 0.0, 0.0);
		    nxopen.annotations.PmiHorizontalDimension pmiHorizontalDimension;
		    pmiHorizontalDimension = getWorkPart().dimensions().createPmiHorizontalDimension(dimensionData1, pmiData1, xform3, origin1);
		    
		    dimensionData1.dispose();
		    dimensionData1 = null;
		    
		    pmiData1.dispose();
		    pmiData1 = null;
		   
		    pmiHorizontalDimension.setOriginCentered(true);
		    
		    // Dimension distance from part 
		    Point3d distancePoint = new Point3d(0.0, 0.0, 0.0);
		    switch (pmiDefaultPlane.ordinal())
			{
			case PmiDefaultPlane._XY_OF_WCS:
				distancePoint = new Point3d(pickPoint1.x+100.0, pickPoint1.y, pickPoint1.z);
				break;
				
			case PmiDefaultPlane._XZ_OF_WCS:
				distancePoint = new Point3d(pickPoint1.x, pickPoint1.y, pickPoint1.z+100.0);
				break;	

			case PmiDefaultPlane._YZ_OF_WCS:
				distancePoint = new Point3d(pickPoint1.x, pickPoint1.y, pickPoint1.z+100.0);
				break;
				
			}
		    //Point3d origin2 = new Point3d(100.0, -100.0, 50.0);
		    Point3d origin2 = distancePoint;
		    pmiHorizontalDimension.setAnnotationOrigin(origin2);
		    
		    pmiHorizontalDimension.setLeaderOrientation(nxopen.annotations.LeaderOrientation.FROM_LEFT);
		    
		}
		catch (NXException ex)
		{
			catchException(ex);
		}
	}
	
	private void createDiameterDimension() throws RemoteException, NXException
	{
//		print("createDiameterDimension");
		try
		{	
			Dimension dim = getMasterDimensionsList().get(0);
						
			nxopen.annotations.DimensionData dimensionData1;
		    dimensionData1 = getWorkPart().annotations().newDimensionData();
		    
		    nxopen.annotations.Associativity associativity1;
		    associativity1 = getWorkPart().annotations().newAssociativity();
		    
		    //nxopen.features.Extrude extrude1 = ((nxopen.features.Extrude)getWorkPart().features().findObject("EXTRUDE(2)"));
		    //Edge edge1 = ((Edge)extrude1.findObject("EDGE * 140 * 150 {(100,0,100)(100,0,50)(100,0,0) EXTRUDE(2)}"));
		    Edge edge1 = (Edge) getPmiWizardDialog().getDimensionEdgeSelect().getSelectedObjects()[0];
		    associativity1.setFirstObject(edge1);
		    
		    NXObject nullNXObject = null;
		    associativity1.setSecondObject(null);
		    
		    Edge.VerticesData verticesData = edge1.getVertices();
		    Point3d vertex1 = verticesData.vertex1;
		    Point3d vertex2 = verticesData.vertex2;
		    
		    associativity1.setObjectView(getWorkPart().modelingViews().workView());
		    
		    associativity1.setPointOption(nxopen.annotations.AssociativityPointOption.CONTROL);
		    
		    associativity1.setLineOption(nxopen.annotations.AssociativityLineOption.NONE);
		    
		    Point3d firstDefinitionPoint1 = new Point3d(0.0, 0.0, 0.0);
		    associativity1.setFirstDefinitionPoint(firstDefinitionPoint1);
		    
		    Point3d secondDefinitionPoint1 = new Point3d(0.0, 0.0, 0.0);
		    associativity1.setSecondDefinitionPoint(secondDefinitionPoint1);
		    
		    associativity1.setAngle(0.0);
		    
		    //Point3d pickPoint1 = new Point3d(100.0, 0.0, 100.0);
		    Point3d pickPoint1 = vertex1;
		    associativity1.setPickPoint(pickPoint1);
		    
		    nxopen.annotations.Associativity [] associativity2  = new nxopen.annotations.Associativity[1];
		    associativity2[0] = associativity1;
		    dimensionData1.setAssociativity(1, associativity2);
		    
		    associativity1.dispose();
		    associativity1 = null;
		    
		    nxopen.annotations.Associativity associativity3;
		    associativity3 = getWorkPart().annotations().newAssociativity();
		    
		    associativity3.setFirstObject(edge1);
		    
		    associativity3.setSecondObject(null);
		    
		    associativity3.setObjectView(getWorkPart().modelingViews().workView());
		    
		    associativity3.setPointOption(nxopen.annotations.AssociativityPointOption.CONTROL);
		    
		    associativity3.setLineOption(nxopen.annotations.AssociativityLineOption.NONE);
		    
		    Point3d firstDefinitionPoint2 = new Point3d(0.0, 0.0, 0.0);
		    associativity3.setFirstDefinitionPoint(firstDefinitionPoint2);
		    
		    Point3d secondDefinitionPoint2 = new Point3d(0.0, 0.0, 0.0);
		    associativity3.setSecondDefinitionPoint(secondDefinitionPoint2);
		    
		    associativity3.setAngle(0.0);
		    
		    //Point3d pickPoint2 = new Point3d(100.0, 0.0, 0.0);
		    Point3d pickPoint2 = vertex2;
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
		    
		    String[] lines1  = new String[0];
		    appendedText1.setAboveText(lines1);
		    
		    String[] lines2  = new String[0];
		    appendedText1.setAfterText(lines2);
		    
		    String[] lines3  = new String[0];
		    appendedText1.setBeforeText(lines3);
		    
		    String[] lines4  = new String[0];
		    appendedText1.setBelowText(lines4);
		    
		    dimensionData1.setAppendedText(appendedText1);
		    
		    appendedText1.dispose();
		    appendedText1 = null;
		    
		    nxopen.annotations.PmiData pmiData1;
		    pmiData1 = getWorkPart().annotations().newPmiData();
		    
		    /*nxopen.annotations.BusinessModifier [] businessModifiers1  = new nxopen.annotations.BusinessModifier[0];
		    pmiData1.setBusinessModifiers(businessModifiers1);
		    */
		    Xform xform3;
		    PmiDefaultPlane pmiDefaultPlane = PmiDefaultPlane.YZ_OF_WCS;
//		    PmiDefaultPlane pmiDefaultPlane = getAnnotationPlane().method();
		    xform3 = dimensionData1.getInferredPlane(pmiDefaultPlane, nxopen.annotations.DimensionType.DIAMETER);
		    
		    Point3d origin1 = new Point3d(0.0, 0.0, 0.0);
		    nxopen.annotations.PmiDiameterDimension pmiDiameterDimension;
		    pmiDiameterDimension = getWorkPart().dimensions().createPmiDiameterDimension(dimensionData1, pmiData1, xform3, origin1);
		    
		    dimensionData1.dispose();
		    dimensionData1 = null;
		    
		    pmiData1.dispose();
		    pmiData1 = null;
		   
		    pmiDiameterDimension.setOriginCentered(true);
		    
		    // Dimension distance from part 
		    Point3d distancePoint = new Point3d(0.0, 0.0, 0.0);
		    Point3d origin2 = distancePoint;
		    pmiDiameterDimension.setAnnotationOrigin(origin2);
		    
		    pmiDiameterDimension.setLeaderOrientation(nxopen.annotations.LeaderOrientation.FROM_LEFT);
		    
		}
		catch (NXException ex)
		{
			catchException(ex);
		}
	}
	
	//------------------------------------------------------------------------------
    // Public methods
    //------------------------------------------------------------------------------
	
	public void run() throws RemoteException, NXException
	{
		fillTree();
	}
	
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
		
//		print("\nStarting dimension translation...");
		
		createDimension();
		
		// Clear selection
		getPmiWizardDialog().getDimensionEdgeSelect().setSelectedObjects(new TaggedObject[0]);		
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

	public Plane getAnnotationPlane()
	{
//		Plane ap;
//		try
//		{
//			ap = (Plane)getPmiWizardDialog().getAnnotationPlane().getSelectedObjects()[0];
//			print("ap == null " + (ap == null));
//		} catch (RemoteException | NXException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return annotationPlane;
	}

	public void setAnnotationPlane(Plane annotationPlane)
	{
		this.annotationPlane = annotationPlane;
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
