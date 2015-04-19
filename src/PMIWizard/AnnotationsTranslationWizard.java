package PMIWizard;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nxopen.*;
import nxopen.annotations.*;
import nxopen.blockstyler.*;

public class AnnotationsTranslationWizard extends PMITranslationWizard
{
	
	private Tree masterAnnotationsTree;
	private ArrayList<Annotation> masterAnnotationsList;	

	//------------------------------------------------------------------------------
    //Constructors
    //------------------------------------------------------------------------------
	
	public AnnotationsTranslationWizard(PMITranslationWizard pmitw) throws NXException, RemoteException
	{
		print("*** AnnotationsTranslationWizard created ***");
		//getMasterDimensions();
		setPmiWizardDialog(pmitw.getPmiWizardDialog());
		
		setMasterPart(pmitw.getMasterPart());
		setWorkPart(pmitw.getWorkPart());
		
		setMasterAnnotationsList(new ArrayList<Annotation>());
		
		Tree masterTree = pmitw.getPmiWizardDialog().getMasterAnnotationsTree();
		
		setMasterAnnotationsTree(masterTree);				
		//test();
	}
	
	@Deprecated
	private void getMasterAnnotations() throws RemoteException
	{
		AnnotationManager masterAnnotationManager = super.getMasterPart().annotations();
		// TODO find previous work to get dimensions information form master part
	}
		
	private void fillTree() throws RemoteException, NXException
	{
		try
		{
			print("\n---------------start of fillAnnotationTree function---------------\n");
			
			AnnotationManager annotationManager = getMasterPart().annotations();
			PmiManager pmiManager = getMasterPart().pmiManager();
			PmiCollection pmiCollection = pmiManager.pmis();
			
			TaggedObjectCollection.Iterator it = pmiCollection.iterator();
			Object taggedObject;
			Pmi pmi;
			
			getMasterAnnotationsTree().insertColumn(0, "Annotation", 500);
			
			while(it.hasNext())
			{
				taggedObject = it.next();				
				pmi = (Pmi) taggedObject;
				//print("*******************");
				Annotation an = pmi.getDisplayInstances()[0];
		
				String className;			
				String nodeText;
				
				SurfaceFinish sf;
				ArrayList<Annotation> mdl = getMasterAnnotationsList();
				
				if (an instanceof Annotation)
				{
					// TODO
					// Here must be searching for master annotations not surface finishes
					/*sf = (SurfaceFinish) an;
					
					mdl.add(sf);
					
					className = an.getClass().getSimpleName();
					nodeText = className(className) + ": " + "annotation";
					
					Node newNode = getMasterAnnotationsTree().createNode(nodeText);
					getMasterAnnotationsTree().insertNode(newNode, null, null, Tree.NodeInsertOption.LAST);*/       
				}
				AssociatedObject ao = pmi.getAssociatedObject();
				DisplayableObject[] dispObjs = ao.getObjects();
				for (DisplayableObject dispObject : dispObjs)
				{
					//print(dispObject.journalIdentifier());
				}
				
				setMasterAnnotationsList(mdl);
			}
		} 
		catch (RemoteException | NXException ex)
		{
			catchException(ex);					
		}
    	
	}	
	
	private void createAnnotation() throws RemoteException, NXException
	{
		String annotationName = getMasterAnnotationsTree().getSelectedNodes()[0].displayText().split(":")[0];
		//print("dimensionName = " + dimensionName);
		switch (annotationName)
		{
		case "PmiVerticalDimension":
			//createVerticalDimension();			
			break;
			
		case "PmiHorizontalDimension":
			//createHorizontalDimension();			
			break;
			
		case "PmiDiameterDimension":
			//createDiameterDimension();			
			break;	

		default:
			print("No switch branch for such annotation");
			break;
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
		// TODO
		// There is no selection for annotations yet
		/*if (getPmiWizardDialog().getSurfaceFinishFaceSelect().getSelectedObjects().length == 0)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Укажите поверхность");
			return;
		}

		if (getMasterAnnotationsTree().getSelectedNodes().length == 0)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Выберите аннотацию для трансляции");
			return;								
		}*/
		
		print("\nStarting annotations translation...");
		
		createAnnotation();
		
		// Clear selection
		// TODO
		// There is no selection for annotations yet
		//getPmiWizardDialog().getSurfaceFinishFaceSelect().setSelectedObjects(new TaggedObject[0]);		
	}

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	
	public Tree getMasterAnnotationsTree()
	{
		return masterAnnotationsTree;
	}

	public void setMasterAnnotationsTree(Tree masterAnnotationsTree)
	{
		this.masterAnnotationsTree = masterAnnotationsTree;
	}

	public ArrayList<Annotation> getMasterAnnotationsList()
	{
		return masterAnnotationsList;
	}

	public void setMasterAnnotationsList(ArrayList<Annotation> masterAnnotationsList)
	{
		this.masterAnnotationsList = masterAnnotationsList;
	}
		
}
