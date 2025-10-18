package cs545de.lab1springmvc.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "firstname", defaultValue = "John") String firstName,
                        @RequestParam(value = "lastname", defaultValue = "Doe") String lastName,
                        Model model) {
        String message = firstName + " " + lastName;
        model.addAttribute("message", message);
        return "hello"; // resolves to src/main/resources/templates/hello.html
    }


    // --- New endpoint: /calc?num1=2&num2=8&op=* ---
    @GetMapping("/calc")
    public String calc(@RequestParam("num1") double num1,
                       @RequestParam("num2") double num2,
                       @RequestParam("op") String op,
                       Model model) {

        Double result = null;
        String error = null;

        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
            case "x":
            case "X":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0.0) {
                    error = "Division by zero is not allowed.";
                } else {
                    result = num1 / num2;
                }
                break;
            default:
                error = "Unsupported operation. Use one of: +, -, *, /";
        }

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("op", op);
        model.addAttribute("result", result);
        model.addAttribute("error", error);

        return "calc"; // resolves to src/main/resources/templates/calc.html
    }

}
