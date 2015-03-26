package PMIWizard;

public class PMITranslationWizard
{
	private PMIWizardDialog wizard;
	private AnnotationsTranslationWizard annotationsTranslationWizard;
	private DimensionsTranslationWizard dimensionsTranslationWizard;
	private FaceFinishesTranslationWizard faceFinishesTranslationWizard;
	
	//------------------------------------------------------------------------------
    //Constructor for TranslationWizard class
    //------------------------------------------------------------------------------
	public PMITranslationWizard(PMIWizardDialog wizard)
	{
		// Initial settings
		setWizard(wizard);		
		setAnnotationsTranslationWizard(new AnnotationsTranslationWizard());
		setDimensionsTranslationWizard(new DimensionsTranslationWizard());
		setFaceFinishesTranslationWizard(new FaceFinishesTranslationWizard());
		
		
	}

	//------------------------------------------------------------------------------
    //Getters and Setters
    //------------------------------------------------------------------------------
	public PMIWizardDialog getWizard()
	{
		return wizard;
	}

	public void setWizard(PMIWizardDialog wizard)
	{
		this.wizard = wizard;
	}

	public AnnotationsTranslationWizard getAnnotationsTranslationWizard()
	{
		return annotationsTranslationWizard;
	}

	public void setAnnotationsTranslationWizard(AnnotationsTranslationWizard annotationsTranslationWizard)
	{
		this.annotationsTranslationWizard = annotationsTranslationWizard;
	}

	public DimensionsTranslationWizard getDimensionsTranslationWizard()
	{
		return dimensionsTranslationWizard;
	}

	public void setDimensionsTranslationWizard(DimensionsTranslationWizard dimensionsTranslationWizard)
	{
		this.dimensionsTranslationWizard = dimensionsTranslationWizard;
	}

	public FaceFinishesTranslationWizard getFaceFinishesTranslationWizard()
	{
		return faceFinishesTranslationWizard;
	}

	public void setFaceFinishesTranslationWizard(FaceFinishesTranslationWizard faceFinishesTranslationWizard)
	{
		this.faceFinishesTranslationWizard = faceFinishesTranslationWizard;
	}

}
