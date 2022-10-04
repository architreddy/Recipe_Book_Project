package recipeBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import recipeBook.Recipe;

public class Menu{

	    public static void main(String[] args){
	        
	        //Initialing default list to test user functions before transitioning to filewriting
	        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	        
	        Recipe pasta = new Recipe();
	        pasta.setName("Alfredo");
	        String[] alfredoingredients = {"Papardelle" , "Alfredo Sauce" , "Butter" , "Chicken" };
	        pasta.setIngredientsList(alfredoingredients);
	        String alfredoInstructions = "1. Boil Papardelle" + "\n" + "2. Melt butter" + 
	        "\n" + "3. Fry chicken in butter with alfredo sauce" + "\n" + "4. Mix in pan";
	        pasta.setInstructionList(alfredoInstructions);

	        Recipe Rice = new Recipe();
	        Rice.setName("Rice");
	        String[] friedRiceIngredients = {"Rice", "Beans", "Carrots", "Olive Oil", "Egg"};
	        Rice.setIngredientsList(friedRiceIngredients);
	        String friedRiceInstructions = "1. Wash and cook rice" + "\n" + "2. Heat Olive Oil in Wok" + 
	        "\n" + "3. Fry egg in olive oil" + "\n" + "4. Mix in carrots, beans, and rice to pan and stir";
	        Rice.setInstructionList(friedRiceInstructions);

	        Recipe Steak = new Recipe();
	        Steak.setName("Wagyu");
	        String[] wagyuIngredients = {"wagyu", "butter", "garlic", "thyme"};
	        Steak.setIngredientsList(wagyuIngredients);
	        String wagyuInstructions = "1. Melt butter to pan" + "\n" + "2. Mix in garlic and thyme" + 
	        "\n" + "3. Drop Wagyu to pan for 3 minutes" + "\n" + "4. Flip for 2 more minutes";
	        Steak.setInstructionList(wagyuInstructions);

	        recipeList.add(pasta);
	        recipeList.add(Rice);
	        recipeList.add(Steak);        

	        
	        boolean counter = true;
	        
	        // continue prompting user 
	        while(counter == true){
	            
	            // print user options
	            menuScreen();
	            Scanner menu = new Scanner(System.in);

	            // store user int input as option
	            int option = menu.nextInt();
				
	            // Option 1 -- add a recipe
	            if (option == 1) {
	                /*
	                // construct recipe
	                Recipe a = new Recipe();


	                // ask user for name of recipe
	                System.out.println("What is the recipe's name?");
	                String Name = (String) menu.next();
	                a.setName(Name);


	                // ask user for ingredients 
	                System.out.println("What are ingredients required for this recipe? Please enter one by one. If done please type 'done'.");
	                // user ingredient input 
	                String ingredientBuild = (String) menu.next();
	                List<String> list = new ArrayList<String>();
	                // build ingredient arraylist
	                while (ingredientBuild.toLowerCase() != 'done'){
	                    list.add(ingredientBuild);
	                    ingredientBuild = (String) menu.next();
	                }
	                // convert to array
	                String[] ingredientArray = list.toArray();
	                // construct recipe ingredients
	                a.setIngredientsList(ingredientArray);


	                // ask user for instructions
	                System.out.println("What are the recipe's instructions? Please enter one by one. If done please type 'done'");
	                String recipeInstructions = null;
	                int count = 1;
	                String instructionBuild = (String) menu.next();
	                // build instructions string
	                while (instructionBuild.toLowerCase() != 'done'){
	                    recipeInstructions += (String) count;
	                    recipeInstructions += instructionBuild;
	                    recipeInstructions += '\n';

	                    count++;
	                    instructionBuild = (String) menu.next();
	                }
	                a.setInstructionList(recipeInstructions);


	                addARecipe(a);
	                */
				}

	            // Option 2 -- view a singular recipe
				else if (option == 2) {
	                System.out.println("What would you like to search for?");
	                String recipeNameSearch = menu.next(); // name
	                
	                // cycle through all recipes in default recipeList
	                for(Recipe recipe : recipeList) {
	                    if (recipeNameSearch.equals(recipe.getName())){
	                        System.out.println(recipe.getName());
	                        System.out.println(recipe.getIngredientsList());
	                        System.out.println(recipe.getInstructionList());
	                    }
	                }
				}
	            
	            // Option 3 -- view all recipes 
	            else if (option == 3) {
	                
				}
				     
				// if user chooses anything else, then exit program
				else {
				    counter = false;
					menu.close();
				    System.exit(0);
				}

	        }


	    }

	    public static void menuScreen(){
	        System.out.println("Welcome to your Recipe Book");
		    System.out.println("To add a recipe, please press 1");
		    System.out.println("To search a recipe, please press 2");
		    System.out.println("To view all recipes, please press 3");
		    System.out.println("To exit, press 0");
	    }
	}

