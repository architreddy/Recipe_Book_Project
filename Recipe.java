// depecrated for now
// the Recipe class is merged into Menu.Java

public class Recipe{
	
	// Name of recipe as a string
	private String name; 
	
	// Ingredients stored in an arraylist of strings 
	private String[] ingredientsList;
	
	// store instructions as a long string separated by new line
	private String instructionList;
	
	// constructor for recipe
	public Recipe(String name, String[] ingredientsList, String instructionList) {
		this.name = name;
		this.ingredientsList = ingredientsList;
		this.instructionList = instructionList;
	}
	
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
		for (int i = 0; i < ingredientsList.length; i++) {
			this.ingredientsList[i] = ingredientsList[i];
		}
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

// add getters and setters

