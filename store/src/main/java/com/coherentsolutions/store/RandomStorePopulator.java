package com.coherentsolutions.store;

public class RandomStorePopulator {

    public void fillStoreRandomly() {
        createCategories();
    }

    private void createCategories() {
        //1. Put all categories class in to one package
        //2. Use loop for to get all Classes from this package https://www.baeldung.com/java-find-all-classes-in-package#2-reflections-library
        //3. For each class create an instance
        //Do I need to cast this class to the Category class?
        //4. Add this instance to the List<Category> categories
    }
}
