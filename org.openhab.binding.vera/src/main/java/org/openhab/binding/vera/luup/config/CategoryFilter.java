package org.openhab.binding.vera.luup.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryFilter {
    private int id;

    private List<Integer> categories;

    @JsonProperty("Label")
    private Label label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
