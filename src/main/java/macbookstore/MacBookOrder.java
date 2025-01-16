package macbookstore;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MacBookOrder {
    @NotBlank(message="Customer name is required")
    private String customerName;

    @NotBlank(message="Street is required")
    private String customerStreet;

    @NotBlank(message="City is required")
    private String customerCity;

    @NotBlank(message="State is required")
    private String customerState;

    @NotBlank(message="Zip is required")
    private String customerZip;

    @NotNull
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private List<MacBook> macBooks = new ArrayList<>();

    // Maybe this is an enterprise store that orders computers in bulk for their employees, students, or for resale
    public void addMacBook(MacBook macBook) {
        this.macBooks.add(macBook);
    }
}
