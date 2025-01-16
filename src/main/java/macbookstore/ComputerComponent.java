package macbookstore;

import lombok.Data;

@Data
public class ComputerComponent {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        MODEL, STORAGE, MEMORY, PROCESSOR, DISPLAY
    }
}