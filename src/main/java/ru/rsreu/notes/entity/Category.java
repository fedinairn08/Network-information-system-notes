package ru.rsreu.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Entity class representing a category for notes.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Category {
    /**
     * Unique identifier for the category.
     */
    private Long categoryId;
    /**
     * Name of the category.
     */
    private String category;
}
