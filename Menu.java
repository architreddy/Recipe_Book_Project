package recipeBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import recipeBook.Recipe;

public class Menu{

	    public static void main(String[] args){
	        
	        //Initialing default list to test user functions before transitioning to filewriting
	        List<Recipe> recipeList = new ArrayList<Recipe>();
	        
	        Recipe pasta = new Recipe();
	        pasta.setName("Alfredo");
	        String[] alfredoingredients = {"Papardelle" , "Alfredo Sauce" , "Butter" , "Chicken"};
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
	        String[] wagyuIngredients = {"Wagyu", "Butter", "Garlic", "Thyme"};
	        Steak.setIngredientsList(wagyuIngredients);
	        String wagyuInstructions = "1. Melt butter to pan" + "\n" + "2. Mix in garlic and thyme" + 
	        "\n" + "3. Drop Wagyu to pan for 3 minutes" + "\n" + "4. Flip for 2 more minutes";
	        Steak.setInstructionList(wagyuInstructions);

			// for testing multiple words
			Recipe Ramen = new Recipe();
			Ramen.setName("Instant Ramen");
			String[] ramenIngredients = {"Instant Ramen", "Water"};
			Ramen.setIngredientsList(ramenIngredients);
			String ramenInstructions = "1. Boil water\n2. Pour boiling water into ramen cup\n3. Wait 5 minutes\n4. Enjoy";
			Ramen.setInstructionList(ramenInstructions);

	        recipeList.add(pasta);
	        recipeList.add(Rice);
	        recipeList.add(Steak);        
			recipeList.add(Ramen);
	        
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
	                
	                // construct recipe
					System.out.println("What is the recipe's name?");
	                Recipe a = new Recipe();
	                // ask user for name of recipe
					menu.nextLine();
	                String Name = (String) menu.nextLine();
					a.setName(Name);
	                
	                // ask user for ingredients 
	                System.out.println("What are ingredients required for this recipe? Please enter one by one. If done please type 'done'.");
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


	                // ask user for instructions
	                System.out.println("What are the recipe's instructions? Please enter one by one. If done please type 'done'");
	                String recipeInstructions = "";
	                int count = 1;
	                String instructionBuild = (String) menu.nextLine();
	                // build instructions string
	                while (!(instructionBuild.toLowerCase().equals("done"))){
	                    recipeInstructions += Integer.toString(count) + ". ";
	                    recipeInstructions += instructionBuild;
	                    recipeInstructions += "\r\n";

	                    count++;
	                    instructionBuild = (String) menu.nextLine();
	                }
	                a.setInstructionList(recipeInstructions);

					// add recipe 
	                recipeList.add(a);
				}

	            // Option 2 -- view a singular recipe
				else if (option == 2) {
	                System.out.println();
					System.out.println("What would you like to search for?");
					menu.nextLine();
	                String recipeNameSearch = menu.nextLine(); // name
					System.out.println();
	                
	                // cycle through all recipes in default recipeList
	                for(Recipe recipe : recipeList) {
	                    if (recipeNameSearch.toLowerCase().equals(recipe.getName().toLowerCase())){
	                        System.out.println(recipe);
	                    }

						// cycle through all ingredients of each recipe
						List<String> ingredients = recipe.getIngredientsList();
						for(String ingredient : ingredients){
							if (recipeNameSearch.toLowerCase().equals(ingredient.toLowerCase())){
								System.out.println(recipe);
							}
						}
	                }
				}
		    else if (option == 3) {

                    System.out.println();
                    System.out.println("What recipe would you like to look at?");
                    menu.nextLine();
                    String recipeNameSearch = menu.nextLine(); // name
                    System.out.println();


                    for(Recipe recipe : recipeList) {
                        if (recipeNameSearch.toLowerCase().equals(recipe.getName().toLowerCase())){
							// instructions stored in instructionString
							String instructionString = recipe.getInstructionList();
							// convert to array of strings
							String[] instructionSteps = instructionString.split("\n");
							
                            for (String step : instructionSteps){ 

                                System.out.println("Press enter to reveal next step. Enter any character to return to Menu.");

                                String prompt = menu.nextLine();

                                if (prompt.equals("")){
                                    System.out.println(step);
									System.out.println();
                                }
								else{
									break;
								}
                            }
                        }
                }
            }	            
	            // Option 4 -- view all recipes 
	            else if (option == 4) {

					System.out.println();
					for(Recipe recipe : recipeList) {
						System.out.println(recipe);
	                    }
	            }
				     
				// if user chooses anything else, then exit program
				else {
				    counter = false;
					menu.close();
				    System.exit(0);
				}

	        }


	    }


		// display main options
	    public static void menuScreen(){
			System.out.println();
			System.out.println("Welcome to your Recipe Book");
		    System.out.println("To add a recipe, please press 1");
		    System.out.println("To search a recipe, please press 2");
		    System.out.println("To view a recipe step-by-step, please press 3");
		    System.out.println("To view all recipes, please press 4");
		    System.out.println("To exit, press 0");
	    }
	}
