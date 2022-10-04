package recipeBook;

public class Recipe{
	
	// Name of recipe as a string
	private String name; 
	
	// Ingredients stored in an arraylist of strings 
	private String[] ingredientsList;
	
	// store instructions as a long string separated by new line
	private String instructionList;
	
	// getter for name 
	public String getName() {
		return this.name;
	}
	
	// setter for name
	public void setName(String name) {
		this.name = name;
	}
	
	// getter for ingredientsList
	public String[] getIngredientsList() {
		return ingredientsList;
	}
	
	// setter for ingredientsList
	public void setIngredientsList(String[] ingredientsList) {
		this.ingredientsList = ingredientsList;
	}
	
	// getter for instructionList
	public String getInstructionList() {
		return instructionList;
	}
	
	// setter for instructionList
	public void setInstructionList(String instructionList) {
		this.instructionList = instructionList;
	}
	
}


