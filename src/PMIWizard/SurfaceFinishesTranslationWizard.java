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
		print("*** SurfaceFinishesTranslationWizard created ***");
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
		
	private void fillTree() throws RemoteException, NXException
	{
		try
		{
			print("\n---------------start of fillSurfaceFinishTree function---------------\n");
			
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
		String dimensionName = getMasterSurfaceFinishesTree().getSelectedNodes()[0].displayText().split(":")[0];
		//print("dimensionName = " + dimensionName);
		switch (dimensionName)
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
			print("No switch branch for such surface finish");
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
		if (getPmiWizardDialog().getSurfaceFinishFaceSelect().getSelectedObjects().length == 0)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Укажите поверхность");
			return;
		}

		if (getMasterSurfaceFinishesTree().getSelectedNodes().length == 0)
		{
			showMessage("Информация", NXMessageBox.DialogType.INFORMATION, "Выберите поверхность для трансляции");
			return;								
		}
		
		print("\nStarting surface finish translation...");
		
		//createSurfaceFinish();
		
		// Clear selection
		getPmiWizardDialog().getSurfaceFinishFaceSelect().setSelectedObjects(new TaggedObject[0]);		
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
