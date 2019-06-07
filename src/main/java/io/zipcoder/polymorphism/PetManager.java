package io.zipcoder.polymorphism;

import io.zipcoder.pets.Cat;
import io.zipcoder.pets.Dog;
import io.zipcoder.pets.Dragon;
import io.zipcoder.pets.Pet;
import io.zipcoder.polymorphism.Console;

class PetManager {
    private Integer numPets;
    private Pet[] thePets;
 //   private Console console = new Console();

    void run(){
        requestNumPets();
        thePets = new Pet[numPets];
        populatePets();
        spitItOut();
    }

    private void spitItOut() {
        StringBuilder output = new StringBuilder();
        for (Pet pet:this.thePets) {
            output.append(pet.toString());
        }
        Console.println(output.toString());
    }

    private void populatePets() {
        for (int i = 0; i < numPets; i++) {
            addOnePet(i);
        }
        Console.println("Thank you.");
    }

    private void addOnePet(int i) {
        String userInput = requestPetSpecies(i);
        createPetInArray(i, userInput);
        namePet(this.thePets[i], userInput);
    }

    private void namePet(Pet thePet, String userInput) {
        userInput= Console.getStringInput("Super. And what is the name of this "+userInput+"?");
        thePet.setName(userInput);
    }

    private void createPetInArray(int i, String userInput) {
        switch (userInput.toLowerCase()){
            case "dog":{
                this.thePets[i]=new Dog();
                break;
            }
            case "cat":{
                this.thePets[i]=new Cat();
                break;
            }
            default:{
                this.thePets[i]=new Dragon();
                break;
            }
        }
    }

    private String requestPetSpecies(int i) {
        String userInput= Console.getStringInput("Thank you. Now, what species is your "+ordinal(i+1)+" pet? (cat, dog, dragon)");
        while(!isValidSpecies(userInput)){
            userInput= Console.getStringInput("That is not a valid species, please try again.");
        }
        return userInput;
    }

    private void requestNumPets() {
        String userInput= Console.getStringInput("How many pets do you have?");
        while(!isInteger(userInput)){
            userInput= Console.getStringInput("That is not a valid number of pets, please try again.");
        }
    }

    private  Boolean isInteger(String input){

        try{
            numPets = Integer.parseInt(input);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private Boolean isValidSpecies(String input){
        return input.toLowerCase().equals("cat") || !input.toLowerCase().equals("dog") || !input.toLowerCase().equals("dragon");
    }

    private static String ordinal(int i) { // I got this method from stackoverflow -- BUT I do understand what it is doing :)
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }
}
