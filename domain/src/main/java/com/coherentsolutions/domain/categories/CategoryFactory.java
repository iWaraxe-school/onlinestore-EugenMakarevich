package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.categories.subcategories.BookCategory;
import com.coherentsolutions.domain.categories.subcategories.FoodCategory;
import com.coherentsolutions.domain.categories.subcategories.PhoneCategory;

public class CategoryFactory {
    //use getCategory method to instantiate object of the type Category
    public Category getCategory(Categories category) {
        if (category == null) {
            return null;
        }
        if (category == Categories.BOOKS) {
            return new BookCategory();

        } else if (category == Categories.FOOD) {
            return new FoodCategory();

        } else if (category == Categories.PHONES) {
            return new PhoneCategory();
        }
        return null;
    }
}
