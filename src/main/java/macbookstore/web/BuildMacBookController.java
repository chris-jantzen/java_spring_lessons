package macbookstore.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

import macbookstore.ComputerComponent;
import macbookstore.MacBookOrder;
import macbookstore.ComputerComponent.Type;
import macbookstore.MacBook;

@Slf4j
@Controller
@RequestMapping("/build")
@SessionAttributes("macBookOrder")
public class BuildMacBookController {
    @ModelAttribute
    public void addComponentsToModel(Model model) {
        List<ComputerComponent> computerComponents = Arrays.asList(
                new ComputerComponent("MBA", "MacBook Air", Type.MODEL),
                new ComputerComponent("MBP", "MacBook Pro", Type.MODEL),
                new ComputerComponent("M4ST", "M4 Processor", Type.PROCESSOR),
                new ComputerComponent("M4PR", "M4 Pro Processor", Type.PROCESSOR),
                new ComputerComponent("16GB", "16 GB Memory", Type.MEMORY),
                new ComputerComponent("32GB", "32 GB Memory", Type.MEMORY),
                new ComputerComponent("1TB", "1 TB Storage", Type.STORAGE),
                new ComputerComponent("2TB", "2 TB Storage", Type.STORAGE),
                new ComputerComponent("STD", "Standard Display", Type.DISPLAY),
                new ComputerComponent("NTD", "Nano-Texture Display", Type.DISPLAY));

        Type[] types = Type.values();
        for (Type type : types) {
            Iterable<ComputerComponent> componentsOfType = filterByType(computerComponents, type);
            model.addAttribute(type.toString().toLowerCase(), componentsOfType);
        }
    }

    @ModelAttribute(name = "macBookOrder")
    public MacBookOrder order() {
        return new MacBookOrder();
    }

    @ModelAttribute(name = "macBook")
    public MacBook macBook() {
        return new MacBook();
    }

    @GetMapping
    public String showBuildForm() {
        return "design";
    }

    @PostMapping
    public String processMacBookOrder(
            @Valid MacBook macBook,
            Errors errors,
            @ModelAttribute MacBookOrder macBookOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        macBookOrder.addMacBook(macBook);
        log.info("Processing MacBook Order: {}", macBook);

        return "redirect:/orders/current";
    }

    private Iterable<ComputerComponent> filterByType(List<ComputerComponent> computerComponents, Type type) {
        return computerComponents
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
