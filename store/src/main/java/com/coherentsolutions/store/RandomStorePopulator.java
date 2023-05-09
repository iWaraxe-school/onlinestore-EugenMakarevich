package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;
import org.reflections.Reflections;

import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

public class RandomStorePopulator {

    public void fillStoreRandomly() {
        createCategories();
    }

    public void createCategories() {
        Set<Class<?>> classes = findAllClasses("com/coherentsolutions/domain/categories/subcategories");
        //2. Get all Classes from this package
        //3. For each class create an instance
        //4. Add this instance to the List<Category> categories
    }

    public Set<Class<?>> findAllClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.get(SubTypes.of(Category.class).asClass());
    }
}
