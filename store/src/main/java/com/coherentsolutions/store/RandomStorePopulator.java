package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;
import org.reflections.Reflections;

import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

public class RandomStorePopulator {
    private Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        createCategories();
    }

    public void createCategories() {
        Set<Class<?>> classes = findAllClasses("com.coherentsolutions.domain.categories.subcategories");
        for (Class<?> cl : classes) {
            try {
                Category category = (Category) cl.newInstance();
                store.addCategory(category);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        //3. For each class create an instance
        //4. Add this instance to the List<Category> categories
    }

    public Set<Class<?>> findAllClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.get(SubTypes.of(Category.class).asClass());
    }
}
