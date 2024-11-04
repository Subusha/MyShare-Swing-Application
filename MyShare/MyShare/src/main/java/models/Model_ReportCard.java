package models;

public class Model_ReportCard {
    
    private String title;
    private String value;
    private String description;

    public Model_ReportCard() {
    }

    public Model_ReportCard(String title, String values, String description) {
        this.title = title;
        this.value = values;
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String values) {
        this.value = values;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
