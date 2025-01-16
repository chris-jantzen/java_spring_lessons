package macbookstore.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import macbookstore.ComputerComponent;
import macbookstore.ComputerComponent.Type;

@Component
public class ComponentByIdConverter implements Converter<String, ComputerComponent> {
    private final Map<String, ComputerComponent> computerComponentMap = new HashMap<>();

    public ComponentByIdConverter() {
        computerComponentMap.put("MBA", new ComputerComponent("MBA", "MacBook Air", Type.MODEL));
        computerComponentMap.put("MBP", new ComputerComponent("MBP", "MacBook Pro", Type.MODEL));
        computerComponentMap.put("M4ST", new ComputerComponent("M4ST", "M4 Processor", Type.PROCESSOR));
        computerComponentMap.put("M4PR", new ComputerComponent("M4PR", "M4 Pro Processor", Type.PROCESSOR));
        computerComponentMap.put("16GB", new ComputerComponent("16GB", "16 GB Memory", Type.MEMORY));
        computerComponentMap.put("32GB", new ComputerComponent("32GB", "32 GB Memory", Type.MEMORY));
        computerComponentMap.put("1TB", new ComputerComponent("1TB", "1 TB Storage", Type.STORAGE));
        computerComponentMap.put("2TB", new ComputerComponent("2TB", "2 TB Storage", Type.STORAGE));
        computerComponentMap.put("STD", new ComputerComponent("STD", "Standard Display", Type.DISPLAY));
        computerComponentMap.put("NTD", new ComputerComponent("NTD", "Nano-Texture Display", Type.DISPLAY));
    }

    @Override
    public ComputerComponent convert(String id) {
        return computerComponentMap.get(id);
    }
}
