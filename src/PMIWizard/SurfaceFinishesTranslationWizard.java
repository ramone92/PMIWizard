package PMIWizard;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nxopen.*;
import nxopen.annotations.*;
import nxopen.blockstyler.*;

public class SurfaceFinishesTranslationWizard extends PMITranslationWizard implements Tree.OnSelectCallback
{
	
	private Tree masterSurfaceFinishesTree;
	private ArrayList<SurfaceFinish> masterSurfaceFinishesList;	

	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public SurfaceFinishesTranslationWizard(PMITranslationWizard pmitw) throws NXException, RemoteException
	{
//		print("*** SurfaceFinishesTranslationWizard created ***");
		//getMasterDimensions();
		setPmiWizardDialog(pmitw.getPmiWizardDialog());
		
		setMasterPart(pmitw.getMasterPart());
		setWorkPart(pmitw.getWorkPart());
		
		setMasterSurfaceFinishesList(new ArrayList<SurfaceFinish>());
		
		Tree masterTree = pmitw.getPmiWizardDialog().getMasterSurfaceFinishesTree();
		masterTree.setOnSelectHandler(this);
		setMasterSurfaceFinishesTree(masterTree);				
		//test();				
	}
	
	//------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------

	@Deprecated
	private void getMasterSurfaceFinishes() throws RemoteException
	{
		AnnotationManager masterAnnotationManager = super.getMasterPart().annotations();
		// TODO find previous work to get dimensions information form master part
	}
	
	private PlaneBuilder.PlaneMethodType getPlaneMethodType() throws RemoteException, NXException
	{
		PlaneBuilder.PlaneMethodType planeMethodType;
		PlaneTypes.MethodType methodType = ((IPlane) getPmiWizardDialog().getAnnotationPlane().getSelectedObjects()[0]).method();
		switch (methodType.ordinal())
		{
		case PlaneTypes.MethodType._FIXED_X:
			planeMethodType = PlaneBuilder.PlaneMethodType.YZ_PLANE;
			break;
			
		case PlaneTypes.MethodType._FIXED_Y:
			planeMethodType = PlaneBuilder.PlaneMethodType.XZ_PLANE;
			break;
			
		case PlaneTypes.MethodType._FIXED_Z:
			planeMethodType = PlaneBuilder.PlaneMethodType.XY_PLANE;
			break;	

		default:
			planeMethodType = PlaneBuilder.PlaneMethodType.YZ_PLANE;
			break;
		}
		
		return planeMethodType;
	}
		
	private void fillTree() throws RemoteException, NXException
	{
		try
		{
//			print("\n---------------start of fillSurfaceFinishTree function---------------\n");
			
			AnnotationManager annotationManager = getMasterPart().annotations();
			PmiManager pmiManager = getMasterPart().pmiManager();
			PmiCollection pmiCollection = pmiManager.pmis();
			
			TaggedObjectCollection.Iterator it = pmiCollection.iterator();
			Object taggedObject;
			Pmi pmi;
			
			getMasterSurfaceFinishesTree().insertColumn(0, "Face finish", 500);
			SurfaceFinishBuilder sfb;
			
			while(it.hasNext())
			{
				taggedObject = it.next();				
				pmi = (Pmi) taggedObject;
				//print("*******************");
				Annotation an = pmi.getDisplayInstances()[0];
		
				String className;			
				String nodeText;
				
				SurfaceFinish sf;
				ArrayList<SurfaceFinish> mdl = getMasterSurfaceFinishesList();
				
				if (an instanceof SurfaceFinish)
				{
					sf = (SurfaceFinish) an;
					sfb = getMasterPart().pmiManager().pmiAttributes().createSurfaceFinishBuilder(sf);
					
					mdl.add(sf);
					
					className = an.getClass().getSimpleName();
					nodeText = className(className) + ": " + sfb.a1();
					
					Node newNode = getMasterSurfaceFinishesTree().createNode(nodeText);
					getMasterSurfaceFinishesTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);       
				}
				AssociatedObject ao = pmi.getAssociatedObject();
				DisplayableObject[] dispObjs = ao.getObjects();
				for (DisplayableObject dispObject : dispObjs)
				{
					//print(dispObject.journalIdentifier());
				}
				
				setMasterSurfaceFinishesList(mdl);
			}
		} 
		catch (RemoteException | NXException ex)
		{
			catchException(ex);					
		}
    	
	}	
	
	private void createSurfaceFinish() throws RemoteException, NXException
	{
		String surfaceFinishValue = getMasterSurfaceFinishesTree().getSelectedNodes()[0].displayText().split(":")[1];
//		String sfn = getMasterSurfaceFinishesTree().getSelectedNodes()[0].displayText().split(" ")[2];
		
		SurfaceFinish nullAnnotations_SurfaceFinish = null;
	    SurfaceFinishBuilder surfaceFinishBuilder1;
	    surfaceFinishBuilder1 = getWorkPart().pmiManager().pmiAttributes().createSurfaceFinishBuilder(nullAnnotations_SurfaceFinish);
	       
	    surfaceFinishBuilder1.origin().setAnchor(nxopen.annotations.OriginBuilder.AlignmentPosition.MID_CENTER);
	    
//	    surfaceFinishBuilder1.setA1("Ra 1.60");
//	    
//	    surfaceFinishBuilder1.setA2("Ra 1.60");
	    
	    surfaceFinishBuilder1.setAngle(0.0);
	    
//	    surfaceFinishBuilder1.origin().plane().setPlaneMethod(nxopen.annotations.PlaneBuilder.PlaneMethodType.MODEL_VIEW);
//	    Plane anP = (Plane)getPmiWizardDialog().getAnnotationPlane().getSelectedObjects()[0];
//	    
//	    Xform xf = surfaceFinishBuilder1.origin().plane().userDefinedPlane();
//	    xf.setOrientation(anP.matrix());
//	    
//	    surfaceFinishBuilder1.origin().plane().setUserDefinedPlane(xf);
	    
	    surfaceFinishBuilder1.origin().plane().setPlaneMethod(getPlaneMethodType());
	    
	    LeaderData leaderData1;
	    leaderData1 = getWorkPart().annotations().createLeaderData();
	    
	    leaderData1.setStubSize(5.0);
	    
	    leaderData1.setArrowhead(nxopen.annotations.LeaderData.ArrowheadType.FILLED_ARROW);
	    
	    surfaceFinishBuilder1.leader().leaders().append(leaderData1);
	    
	    leaderData1.setStubSide(nxopen.annotations.LeaderSide.INFERRED);
	    
//	    surfaceFinishBuilder1.setA2(" ");
//	    
//	    surfaceFinishBuilder1.setF2(" ");
//	    
//	    surfaceFinishBuilder1.setA2("Ra 1.60");
//	    
//	    surfaceFinishBuilder1.setF2("");
	    
//	    surfaceFinishBuilder1.setAngle(0.0);
	    
	    surfaceFinishBuilder1.setInvertSymbol(false);
	    
	    surfaceFinishBuilder1.origin().setInferRelativeToGeometry(true);
	    
//	    Point point1 = getWorkPart().points().createPoint(getPmiWizardDialog().getSurfaceFinishLocation().point());
	    
	    Annotation.AssociativeOriginData assocOrigin1 = new Annotation.AssociativeOriginData();
	    assocOrigin1.originType = AssociativeOriginType.DRAG;
	    View nullView = null;
	    assocOrigin1.view = nullView;
	    assocOrigin1.viewOfGeometry = nullView;
	    Point nullPoint = null;
	    assocOrigin1.pointOnGeometry = nullPoint;
	    assocOrigin1.vertAnnotation = null;
	    assocOrigin1.vertAlignmentPosition = AlignmentPosition.TOP_LEFT;
	    assocOrigin1.horizAnnotation = null;
	    assocOrigin1.horizAlignmentPosition = AlignmentPosition.TOP_LEFT;
	    assocOrigin1.alignedAnnotation = null;
	    assocOrigin1.dimensionLine = 0;
	    assocOrigin1.associatedView = nullView;
	    assocOrigin1.associatedPoint = nullPoint;
	    assocOrigin1.offsetAnnotation = null;
	    assocOrigin1.offsetAlignmentPosition = AlignmentPosition.TOP_LEFT;
	    assocOrigin1.xOffsetFactor = 0.0;
	    assocOrigin1.yOffsetFactor = 0.0;
	    assocOrigin1.stackAlignmentPosition = StackAlignmentPosition.ABOVE;
	    surfaceFinishBuilder1.origin().setAssociativeOrigin(assocOrigin1);
	    
	    // TODO 
	    // set right location point
	    
	    Point3d point2 = getPmiWizardDialog().getSurfaceFinishLocation().point();
	    surfaceFinishBuilder1.origin().origin().setValue(null, nullView, point2);
	    
	    surfaceFinishBuilder1.origin().setInferRelativeToGeometry(true);
	    
	    surfaceFinishBuilder1.setInvertSymbol(false);
	    
	    TaggedObject[] taggedSurface = getPmiWizardDialog().getSurfaceFinishFaceSelect().getSelectedObjects();
	    for(TaggedObject f : taggedSurface)
	    {
		    surfaceFinishBuilder1.associatedObjects().objects().add((Face)f);		    
	    }
	    
	    surfaceFinishBuilder1.setInvertSymbol(false);
	    
	    surfaceFinishBuilder1.setA1(surfaceFinishValue);
	    
	    surfaceFinishBuilder1.setA2(" ");
	    
	    surfaceFinishBuilder1.setF2(" ");
	    
	    surfaceFinishBuilder1.setA2(surfaceFinishValue);
	    
	    surfaceFinishBuilder1.setF2("");
	    
	    surfaceFinishBuilder1.setAngle(0.0);
	    
	    surfaceFinishBuilder1.setInvertSymbol(false);
	    
	    surfaceFinishBuilder1.setInvertSymbol(false);
	    
	    surfaceFinishBuilder1.commit();
	    
	    surfaceFinishBuilder1.destroy();
			
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
		if (getPmiWizardDialog().getSurfaceFinishFaceSelect().getSelectedObjects().length == 0)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Выберите поверхность");
			return;
		}

		if (getMasterSurfaceFinishesTree().getSelectedNodes().length == 0)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Выберите, что переносить");
			return;								
		}
		
		if (getPmiWizardDialog().getSurfaceFinishLocation().point() == null)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Выберите точку расположения");
			return;								
		}
		
//		print("\nStarting surface finish translation...");
		
		createSurfaceFinish();
		
		// Clear selection
		getPmiWizardDialog().getSurfaceFinishFaceSelect().setSelectedObjects(new TaggedObject[0]);
		getPmiWizardDialog().getSurfaceFinishLocation().setPoint(null);
	}
	
	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	
	public Tree getMasterSurfaceFinishesTree()
	{
		return masterSurfaceFinishesTree;
	}

	public void setMasterSurfaceFinishesTree(Tree masterSurfaceFinishesTree)
	{
		this.masterSurfaceFinishesTree = masterSurfaceFinishesTree;
	}

	public ArrayList<SurfaceFinish> getMasterSurfaceFinishesList()
	{
		return masterSurfaceFinishesList;
	}

	public void setMasterSurfaceFinishesList(ArrayList<SurfaceFinish> masterSurfaceFinishesList)
	{
		this.masterSurfaceFinishesList = masterSurfaceFinishesList;
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
