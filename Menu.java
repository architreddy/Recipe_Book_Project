package recipeBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import recipeBook.Recipe;
import java.io.PrintWriter;

public class Menu{
	public static void main(String[] args){
		//Initialing default list to test user functions before transitioning to filewriting
		List<Recipe> recipeList = new ArrayList<Recipe>();
		// Read file and add to recipeList
		try{
			recipeList = readRecipes();
		} catch (FileNotFoundException e){
			System.err.println("File: 'recipes.txt' not found in current directory.");
			System.exit(0);
		} 
		boolean counter = true;
		// continue prompting user 
		while(counter == true){
			// print main menu
			menuScreen();
			Scanner menu = new Scanner(System.in);
			int option = menu.nextInt();
			// Option 1 -- add a recipe
			if (option == 1) {
				try{
					Recipe a = addRecipe(menu);
				} catch (IOException e){
					e.printStackTrace();
				}
				// update recipeList
				try{
					recipeList = readRecipes();
				} catch (FileNotFoundException e){
					System.err.println("File: 'recipes.txt' not found in current directory.");
					System.exit(0);
				} 
			}
			// Option 2 -- search and view a full singular recipe
			else if (option == 2) {
				searchRecipe(menu, recipeList);
			}
			// Option 3 -- search and view a recipe step by step
			else if (option == 3) {
				searchRecipeStepByStep(menu, recipeList);
			}	            
			// Option 4 -- view all recipes 
			else if (option == 4) {
				viewAllRecipes(menu, recipeList);
			}
			// if user chooses 0, then exit program
			else if (option == 0){
				counter = false;
				menu.close();
				System.exit(0);
			}
			// anything else then reprompt
			else{
				String errorMSG = "> ERROR: Invalid Command!";
				printHeader(errorMSG);
				System.out.println(errorMSG);
				printHeader(errorMSG);
				continue;
			}	
		}
	}
	// function to add a recipe
	public static Recipe addRecipe(Scanner menu) throws IOException{
		// Recipe Name
		String prompt = "> What is the recipe's name?";
		printHeader(prompt);
		System.out.println(prompt);
		printHeader(prompt);
		Recipe a = new Recipe();
		menu.nextLine();
		String Name = (String) menu.nextLine();
		a.setName(Name);
		// Ingredients 
		String prompt1 = "> What are ingredients required for this recipe? Please enter one by one. If done please type 'done'.";
		printHeader(prompt1);
		System.out.println(prompt1);
		printHeader(prompt1);
		// user ingredient input 
		String ingredientBuild = menu.nextLine();
		List<String> list = new ArrayList<String>();
		// build ingredient arraylist
		while (!(ingredientBuild.toLowerCase().equals("done"))){
			list.add(ingredientBuild);
			ingredientBuild = (String) menu.nextLine();
		}
		String[] ingredientsArray = list.toArray(new String[0]);
		// construct recipe ingredients
		a.setIngredientsList(ingredientsArray);
		// Instructions
		String prompt2 = "> What are the recipe's instructions? Please enter one by one. If done please type 'done'";
		printHeader(prompt2);
		System.out.println(prompt2);
		printHeader(prompt2);
		String recipeInstructions = "";
		int count = 1;
		String instructionBuild = (String) menu.nextLine();
		// build instructions string
		while (!(instructionBuild.toLowerCase().equals("done"))){
			recipeInstructions += Integer.toString(count) + ". ";
			recipeInstructions += instructionBuild;
			recipeInstructions += "\n";
			count++;
			instructionBuild = (String) menu.nextLine();
		}
		a.setInstructionList(recipeInstructions);
		// Name write
		String nameSTR = "\nNAME: " + a.getName();
		// Ingreds write
		String ingredientsSTR = "\nINGREDIENTS: ";
		for(int i = 0; i < list.size(); i++){
			if (i == list.size()-1){
				ingredientsSTR+= list.get(i);
			}
			else{
				ingredientsSTR += list.get(i) + ",";
			}
		}
		String instructSTR = "\n" + a.getInstructionList();
		File file = new File("recipeBook/recipes.txt");
		FileWriter fw = null;
		try{
			fw = new FileWriter(file, true);
			fw.write("\n{");
			fw.write(nameSTR);
			fw.write(ingredientsSTR);
			fw.write("\nINSTRUCTIONS: ");
			fw.write(instructSTR);
			fw.write("\n}");
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try{
				fw.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		// add recipe 
		return(a);
	}
	// function to search and view a singular recipe
	public static void searchRecipe(Scanner menu, List<Recipe> recipeList){
		String prompt = "> What would you like to search for?";
		printHeader(prompt);
		System.out.println(prompt);
		printHeader(prompt);
		menu.nextLine();
		String recipeNameSearch = menu.nextLine(); 
		// store matches to either name of recipe or ingredients of recipes
		List<Recipe> recipeNameMatches = new ArrayList<>();
		List<Recipe> recipeIngredMatches = new ArrayList<>();
		// cycle through all recipes in default recipeList
		for(Recipe recipe : recipeList) {
			if (recipeNameSearch.toLowerCase().equals(recipe.getName().toLowerCase())){
				recipeNameMatches.add(recipe);
			}
			else {
				// cycle through all ingredients of each recipe
				List<String> ingredients = recipe.getIngredientsList();
				for(String ingredient : ingredients){
					if (recipeNameSearch.toLowerCase().equals(ingredient.toLowerCase())){
						recipeIngredMatches.add(recipe);
					}
				}
			}
		}
		int nameMatchCount = recipeNameMatches.size();
		int ingredMatchCount = recipeIngredMatches.size();
		String nameMatchString = String.format("%d", nameMatchCount);
		String ingredMatchString = String.format("%d", ingredMatchCount);
		// if search word matches to name of at least one recipe
		if (nameMatchCount>0){
			// if no match count
			for(int i = 0; i < nameMatchCount; i++){
				if (nameMatchCount == 1){
					String nameMatches = "> NAME MATCHES: " + nameMatchString;
					printHeader(nameMatches);
					System.out.println(nameMatches);
					printHeader(nameMatches);
					System.out.println(recipeNameMatches.get(i));
					String[] lastInstruct = recipeNameMatches.get(i).getInstructionList().split("\n");
					int lastInstructIndex = lastInstruct.length-1;
					printHeader(lastInstruct[lastInstructIndex]);
				}
				else if (i == 0){
					String nameMatches = "> NAME MATCHES: " + nameMatchString;
					printHeader(nameMatches);
					System.out.println(nameMatches);
					printHeader(nameMatches);
					System.out.println(recipeNameMatches.get(i));
					System.out.println();
				}
				else if (i == nameMatchCount-1){
					String[] lastInstruct = recipeNameMatches.get(i).getInstructionList().split("\n");
					int lastInstructIndex = lastInstruct.length-1;
					System.out.println(recipeNameMatches.get(i));
					printHeader(lastInstruct[lastInstructIndex]);
				}
				else{
					System.out.println(recipeNameMatches.get(i));
					System.out.println();
				}
			}
		}
		if (ingredMatchCount > 0){
			for(int i = 0; i < ingredMatchCount; i++){
				if (ingredMatchCount == 1){
					String ingredMatches = "> INGREDIENT MATCHES: " + ingredMatchString;
					printHeader(ingredMatches);
					System.out.println(ingredMatches);
					printHeader(ingredMatches);
					System.out.println(recipeIngredMatches.get(i));
					String[] lastInstruct = recipeIngredMatches.get(i).getInstructionList().split("\n");
					int lastInstructIndex = lastInstruct.length-1;
					printHeader(lastInstruct[lastInstructIndex]);
				}
				else if (i == 0){
					String ingredMatches = "> INGREDIENT MATCHES: " + ingredMatchString;
					printHeader(ingredMatches);
					System.out.println(ingredMatches);
					printHeader(ingredMatches);
					System.out.println(recipeIngredMatches.get(i));
					System.out.println();
				}
				else if (i == ingredMatchCount-1){
					String[] lastInstruct = recipeIngredMatches.get(i).getInstructionList().split("\n");
					int lastInstructIndex = lastInstruct.length-1;
					System.out.println(recipeIngredMatches.get(i));
					printHeader(lastInstruct[lastInstructIndex]);
				}
				else{
					System.out.println(recipeIngredMatches.get(i));
					System.out.println();
				}
			}
		}
		return;
	}
	// function to search and view a recipe step by step
	public static void searchRecipeStepByStep(Scanner menu, List<Recipe> recipeList){
		String promptt = "> What recipe would you like to look at?";
		printHeader(promptt);
		System.out.println(promptt);
		printHeader(promptt);
		menu.nextLine();
		String recipeNameSearch = menu.nextLine(); // name
		for(Recipe recipe : recipeList) {
			if (recipeNameSearch.toLowerCase().equals(recipe.getName().toLowerCase())){
				// instructions stored in instructionString
				String instructionString = recipe.getInstructionList();
				// convert to array of strings
				String[] instructionSteps = instructionString.split("\n");
				for (String step : instructionSteps){ 
					String promptt1 = "> Press enter to reveal next step. Enter any character to return to Menu.";
					printHeader(promptt1);
					System.out.println(promptt1);
					printHeader(promptt1);
					String prompt = menu.nextLine();
					if (prompt.equals("")){
						printHeader(step);
						System.out.println(step);
						printHeader(step);
					}
					else{
						break;
					}
				}
			}
		}
	}
	// function for viewing all recipes
	public static void viewAllRecipes(Scanner menu, List<Recipe> recipeList){
		System.out.println();
		for(int i = 0; i < recipeList.size(); i++) {
			if (i == 0){
				String namee = "NAME: " + recipeList.get(i).getName();
				printHeader(namee);
				System.out.println(recipeList.get(i));
				System.out.println();
			}
			else if(i == (recipeList.size() - 1)){
				String[] instructionListtt = recipeList.get(i).getInstructionList().split("\n");
				int lastInstructionIndex = instructionListtt.length - 1;
				System.out.println(recipeList.get(i));
				printHeader(instructionListtt[lastInstructionIndex]);
			}
			else{
				System.out.println(recipeList.get(i));
				System.out.println();
			}
		}
		return;
	}
	// readRecipes and return them in an arraylist
	public static List<Recipe> readRecipes() throws FileNotFoundException{
		// construct new recipeList 
		List<Recipe> recipeList = new ArrayList<Recipe>(); 
		File recipeFile = new File("recipeBook/recipes.txt");
		try{
			Scanner inFile = new Scanner(recipeFile);
			// skip first line
			inFile.nextLine();
			while (inFile.hasNextLine()){
				String currentLine = inFile.nextLine();
				// valid recipe
				if (currentLine.equals("{")){
					// create recipe
					Recipe b = new Recipe();
					// move one line down 
					String nameLine = inFile.nextLine();
					// set name
					String name = nameLine.split(": ", -1)[1];
					if (!name.isEmpty()){
						b.setName(name);
					}
					else{
						b.setName("Empty");
					}
					// move one line down
					String ingredientsline = inFile.nextLine();
					// set ingredients
					String ingredientsString = ingredientsline.split(": ", -1)[1];
					String[] ingredients = ingredientsString.split(",");
					if (ingredients.length != 0){
						b.setIngredientsList(ingredients);
					} 
					else{
						String[] emptyIngredients = {"none"};
						b.setIngredientsList(emptyIngredients);
					}
					// move two lines down
					inFile.nextLine();
					// construct instructionLine
					String instructionLine = inFile.nextLine();
					String instructionsString = ""; 
					while (!instructionLine.equals("}")){
						instructionsString += instructionLine + "\n";
						instructionLine = inFile.nextLine();
					}
					String stripped = instructionsString.strip();
					b.setInstructionList(stripped);
					recipeList.add(b);
				} 
			}
		} catch (FileNotFoundException e){
			System.err.println("File: 'recipes.txt' not found in current directory.");
			System.exit(0);
		}
		return recipeList;
	}
	public static void printHeader(String a){
		String b = "-";
		for (int i = 0; i < a.length(); i++){
			b += "-";
		}
		System.out.println(b);
	}
	// display main options
	public static void menuScreen(){
		System.out.println();
		printHeader("> Welcome to your Recipe Book");
		System.out.println("> Welcome to your Recipe Book");
		System.out.println("> To add a recipe, please press 1");
		System.out.println("> To search a recipe, please press 2");
		System.out.println("> To view a recipe step-by-step, please press 3");
		System.out.println("> To view all recipes, please press 4");
		System.out.println("> To exit, press 0");
		printHeader("> To exit, press 0");
	}
}

