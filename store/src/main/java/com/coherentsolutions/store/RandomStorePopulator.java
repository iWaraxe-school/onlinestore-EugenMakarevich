package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.products.Product;
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
        StringBuilder storeBuilder = new StringBuilder();
        storeBuilder.append("Store:\n");
        createCategories();

        for (Category category : store.getCategories()) {
            RandomProductGenerator generator = new RandomProductGenerator();
            storeBuilder.append("Category: ").append(category.getCategory()).append("\n");
            int productNum = new Random().nextInt(MAX_NUMBER_PER_CATEGORY) + 1;

            for (int i = 0; i < productNum; i++) {
                Product product = generator.generateProduct(category.getCategory());
                storeBuilder.append("- ")
                        .append(String.format("Name: %s, Rate: %.2f, Price: %.2f", product.getName(), product.getRate(), product.getPrice()))
                        .append("\n");
            }
        }
        System.out.println(storeBuilder);
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
