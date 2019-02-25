public enum Color {
    WHITE("wh"),
    BLUE("bl"),
    RED("re"),
    YELLOW("ye"),
    GREEN("gr"),
    ORANGE("or");

    private String name;

    Color(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
