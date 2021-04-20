package escape.the.forest;
import java.util.Random;
import java.util.Scanner;

// Coded by Dylan Sloan
// Ideas to Add On: Keep track of high scores, Upgrades, Menu, Problem Solving/Puzzles
// RENAME THE GAME

public class EscapeTheForest {
    public static void main(String[] args) {
        // System Objects (integral to game)
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        
        // Enemy Variables
        String[] enemies = {"Bandit", "Bobcat", "Bear", "Snake", "Bigfoot", "Coyote"}; //Array with the different enemy types.
        int maxEnemyHealth = 100;
        int enemyAttackDamage = 30;
        
        // Player Variables
        int health = 200;
        int maxAttackDamage = 50;
        int numHealthPotions = 3;
        int healthPotionHealAmount = 50;
        int healthPotionDropChance = 20; // Percentage
        int energy = 5; // Used up when you choose to run
        int numEnergyBars = 1; // Starting Amount
        int energyBarBoost = 5; // Boosts energy by +5
        int energyBarDropChance = 13; // Percentage
        String name; // Later used as input for the player's name.
        
        // Other Variables
        int quickSandChance = 5; // Percentage you encounter quicksand when you continue in the forest.
        int numEnemiesKilled = 0;  // Original (Added on to with each kill)
        int coins = 0; //Initial coin count
        int coinChance = 10; // Percent chance to get coins after each enemy is killed
        
        boolean running = true;
        
        // Welcome Message
        System.out.println("Welcome to the forest! Good luck on your journey!");
        System.out.println("----ENTER YOUR NAME----");
        name = in.nextLine();
        
        GAME:
        while(running) {
            System.out.println("--------------------------------");
            
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# A " + enemy + " has appeared! #\n");
            
            while(enemyHealth > 0) {
                System.out.println("\tYour Health: " + health);
                System.out.println("\t" + enemy + "'s Health: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink Health Potion");
                System.out.println("\t3. Eat Energy Bar");
                System.out.println("\t4. Run!");
                
                String input = in.nextLine();
                if(input.equals("1")) {
                    int damageGiven = rand.nextInt(maxAttackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);
                    
                    enemyHealth -= damageGiven;
                    health -= damageTaken;
                    
                    System.out.println("\t> You strike the " + enemy + " for " + damageGiven + " damage.");
                    System.out.println("\t> You recieve " + damageTaken + " in return.");
                    
                    if(health < 1) {
                        System.out.println("\t> You have been killed by the " + enemy + ".");
                        break;
                    }
                }
                else if(input.equals("2")) {
                    if(numHealthPotions > 0) {
                        health += healthPotionHealAmount;
                        numHealthPotions --;
                        System.out.println("\t> You have healed yourself for " + healthPotionHealAmount
                                           + "\n\t> You now have " + health + " HP."
                                           + "\n\t> You have " + numHealthPotions + " health potions remaining.\n");
                        
                    }
                    else {
                        System.out.println("\t> You have no health potions left. Defeat enemies for a chance to get one.");
                    }
                }
                else if(input.equals("3")) {
                    if(numEnergyBars > 0) {
                        energy += energyBarBoost;
                        numEnergyBars--;
                        System.out.println("\t> You have increased your energy by " + energyBarBoost + ".");
                        System.out.println("\t> You have " + numEnergyBars + " energy bar(s) remaining.");
                    }
                    else {
                        System.out.println("\t> You have 0 energy bars. Defeat enemies for a chance to get one.");
                    }
                }
                else if(input.equals("4")) {
                    if(energy > 0) {
                        System.out.println("\tYou run away from the " + enemy + "!");
                        energy--; // Takes away energy for running away
                        System.out.println("\t> You now have " + energy + " energy left to run away from enemies!");
                        continue GAME;
                    }              
                    else {
                        System.out.println("\t> You have 0 energy remaining. Eat an energy bar for more.");
                    }
                    
                }
                else {
                    System.out.println("\tInvalid command.");
                }
            }
            if(health < 1) {
                System.out.println("You manage to crawl out of the forest, covered in scratches.");
                break;
            }
            
            numEnemiesKilled++;     // Keeps count of how many enemies are killed.
            System.out.println("--------------------------------");
            System.out.println(" # " + enemy + " was defeated! #");
            System.out.println(" # You have " + health + " HP left. #");
            if(rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You now have " + numHealthPotions + " health potion(s)! # ");
            }
            // Code for Energy Bars being dropped
            if(rand.nextInt(100) < energyBarDropChance) {
                numEnergyBars++;
                System.out.println(" # The " + enemy + " dropped an energy bar! # ");
                System.out.println(" # You now have " + numEnergyBars + " energy bars! # ");
            }
            if(rand.nextInt(100) < coinChance) {
                coins++; // Adds a coin to the player's balance
                System.out.println(" # You have found a coin!  #");
                System.out.println(" # You now have " + coins + " coin(s). #");
            }
            System.out.println("--------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue Fighting");
            System.out.println("2. Go to Shop");
            System.out.println("3. QUIT GAME");
            
            String input = in.nextLine();
            
            while(!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                System.out.println("Invalid Command.");
                input = in.nextLine();
            }
            
            // Code for After Enemies are Killed and the Quicksand
            
            if(input.equals("1")) {
                System.out.println("You continue into the forest.");
                if(rand.nextInt(100) < quickSandChance) {
                    System.out.println("\t> You have stepped in quicksand!"
                            + "\n\t> What do you do now?");
                    System.out.println("\t1. Reach for a vine");
                    System.out.println("\t2. Try to pull yourself out");
                    
                    input = in.nextLine();
                    if(input.equals("1")) {
                        System.out.println("# You pull yourself out of the quicksand with the vine! #");
                        continue GAME;
                    }
                    else if(input.equals("2")) {
                        System.out.println("# You use all your energy and die trying to pull yourself out of the quicksand. #");
                        break;
                    }
                    else {
                        System.out.println("Invalid Command.");
                    }
                }
            }
            
            // The Shop Code       ---------------------------------------
            
            else if(input.equals("2")) {
                int shopRunning = 0;
                while(shopRunning == 0) {
                    System.out.println("+++++++ Welcome to the shop! BALANCE: " + coins + " Coins +++++++");
                    System.out.println("\n1. Health Potion: 2 coins");
                    System.out.println("2. Energy Bar: 2 coins");
                    System.out.println("3. Health Potions now heal 100 health instead of 50: 10 coins");
                    System.out.println("4. Increase your max attack damage from 50 to 100: 20 coins");
                    System.out.println("5. EXIT THE SHOP");
                    input = in.nextLine();
                    if(input.equals("1")) {
                        if(coins >= 2) {
                            numHealthPotions++;
                            System.out.println("You now have " + numHealthPotions + " health potions.");
                            coins -= 2;
                            System.out.println("You now have " + coins + " coins left.");
                        }
                        else {
                            System.out.println("You do not have enough coins.");
                        }
                    }
                    else if(input.equals("2")) {
                        if(coins >= 2) {
                            numEnergyBars++;
                            System.out.println("You now have " + numEnergyBars + " energy bars.");
                            coins -= 2;
                            System.out.println("You now have " + coins + " coins left.");
                        }
                        else {
                            System.out.println("You do not have enough coins.");
                        }
                    }
                    else if(input.equals("3")) {
                        if(coins >= 10) {
                            healthPotionHealAmount = 100;
                            coins -= 10;
                            System.out.println("Successfully purchased.");
                            System.out.println("You now have " + coins + " coins left.");
                        }
                        else {
                            System.out.println("You do not have enough coins.");
                        }
                    }
                    else if(input.equals("4")) {
                        if(coins >= 20) {
                            maxAttackDamage = 100;
                            coins -= 20;
                            System.out.println("Successfully purchased.");
                            System.out.println("You now have " + coins + " coins left.");
                        }
                        else {
                            System.out.println("You do not have enough coins.");
                        }
                    }
                    else if(input.equals("5")) {
                        shopRunning++; // Exits the shop
                    }
                    else {
                        System.out.println("Invalid input.");
                    }
                }
            }
            
            // End of the Shop Code --------------------------------------
            
            else if(input.equals("3")) {
                System.out.println("> You leave the forest after a successful adventure!");
                break;
            }
        }
        
        // Ending Messages
        
        System.out.println("###############################################################");
        System.out.println("THANKS FOR PLAYING CONQUER THE FOREST, " + name + "!");
        System.out.println("*************YOUR STATS*************");
        System.out.println("> Enemies Killed: " + numEnemiesKilled);
        System.out.println("###############################################################");
    }
}