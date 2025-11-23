package org.edu.miu.cs545de.calculatorapplication;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorWebSocketHandler extends TextWebSocketHandler {

    // Supports simple expressions like "3+5", "10 - 4", "-2 * 3", "8 / 2"
    private static final Pattern EXPRESSION_PATTERN =
            Pattern.compile("\\s*([-+]?[0-9]*\\.?[0-9]+)\\s*([+\\-*/])\\s*([-+]?[0-9]*\\.?[0-9]+)\\s*");

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String expr = message.getPayload();
        String response;

        try {
            double result = evaluate(expr);
            response = expr + " = " + result;
        } catch (IllegalArgumentException e) {
            response = "Error: " + e.getMessage();
        }

        session.sendMessage(new TextMessage(response));
    }

    private double evaluate(String expression) {
        Matcher matcher = EXPRESSION_PATTERN.matcher(expression);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid expression. Use format like 3+5 or 10 - 2");
        }

        double left = Double.parseDouble(matcher.group(1));
        String op = matcher.group(2);
        double right = Double.parseDouble(matcher.group(3));

        return switch (op) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> {
                if (right == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                yield left / right;
            }
            default -> throw new IllegalArgumentException("Unsupported operator: " + op);
        };
    }
}

