public class Item {
    private String description;
    public Item(String description) {
        this.description = description;
        if (description == null) this.description = "no description";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
