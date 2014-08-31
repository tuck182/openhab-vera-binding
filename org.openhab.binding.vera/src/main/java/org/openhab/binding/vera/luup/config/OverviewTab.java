package org.openhab.binding.vera.luup.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OverviewTab {
    @JsonProperty("Label")
    private Label label;

    @JsonProperty("content_function")
    private String contentFunction;

    private boolean visible;

    @JsonProperty("menu_trigger")
    private String menuTrigger;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getContentFunction() {
        return contentFunction;
    }

    public void setContentFunction(String contentFunction) {
        this.contentFunction = contentFunction;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getMenuTrigger() {
        return menuTrigger;
    }

    public void setMenuTrigger(String menuTrigger) {
        this.menuTrigger = menuTrigger;
    }
}
