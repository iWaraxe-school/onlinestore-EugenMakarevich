package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;
import org.reflections.Reflections;

import java.util.Random;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

public class RandomStorePopulator {
    private Store store;
    final int MAX_NUMBER_PER_CATEGORY = 10;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        createCategories();

        for (Category category : store.getCategories()) {
            RandomProductGenerator generator = new RandomProductGenerator();
            System.out.println(category.getCategory());
            int productNum = new Random().nextInt(MAX_NUMBER_PER_CATEGORY) + 1;
            for (int i = 0; i < productNum; i++) {
                System.out.println(generator.generateProduct(category.getCategory()));
            }
        }
    }

    public void createCategories() {
        Set<Class<?>> classes = findAllClasses("com.coherentsolutions.domain.categories.subcategories");
        for (Class<?> cl : classes) {
            try {
                Category category = (Category) cl.newInstance();
                store.addCategory(category);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Set<Class<?>> findAllClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.get(SubTypes.of(Category.class).asClass());
    }
}
