package cs545de.lab1servletcalculator.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {

    // Simple record class to track history per session
    public static class CalcRecord {
        final String n1;
        final String n2;
        final String op;
        final String resultText;
        final String timestamp;

        CalcRecord(String n1, String n2, String op, String resultText) {
            this.n1 = n1;
            this.n2 = n2;
            this.op = op;
            this.resultText = resultText;
            this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showCalculatorPage(request, response, null, null, null, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String num1Str = request.getParameter("number1");
        String num2Str = request.getParameter("number2");
        String op = request.getParameter("op");

        String latestResult;

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double res;

            switch (op) {
                case "+": res = num1 + num2; break;
                case "-": res = num1 - num2; break;
                case "*": res = num1 * num2; break;
                case "/":
                    if (num2 == 0) throw new ArithmeticException("Division by zero is not allowed.");
                    res = num1 / num2; break;
                default: throw new IllegalArgumentException("Please choose a valid operation.");
            }

            latestResult = String.format("Result: %.4f %s %.4f = <strong>%.4f</strong>", num1, op, num2, res);

        } catch (NumberFormatException e) {
            latestResult = "<span style='color:red;'>Please enter valid numeric values.</span>";
        } catch (ArithmeticException | IllegalArgumentException e) {
            latestResult = "<span style='color:red;'>" + e.getMessage() + "</span>";
        }

        // Append to per-session history
        HttpSession session = request.getSession(true);
        @SuppressWarnings("unchecked")
        List<CalcRecord> history = (List<CalcRecord>) session.getAttribute("history");
        if (history == null) {
            history = new ArrayList<>();
            session.setAttribute("history", history);
        }
        history.add(new CalcRecord(
                num1Str == null ? "" : num1Str,
                num2Str == null ? "" : num2Str,
                op == null ? "" : op,
                latestResult
        ));

        showCalculatorPage(request, response, num1Str, num2Str, op, latestResult);
    }

    private void showCalculatorPage(HttpServletRequest request, HttpServletResponse response,
                                    String num1, String num2, String op, String latestResult)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        @SuppressWarnings("unchecked")
        List<CalcRecord> history = (List<CalcRecord>) request.getSession(true).getAttribute("history");
        if (history == null) history = new ArrayList<>();

        out.println("<html>");
        out.println("<head><title>Simple Calculator</title></head>");
        out.println("<body style='font-family:Arial, sans-serif; max-width:700px; margin:20px auto;'>");
        out.println("<h2>Simple Calculator</h2>");

        // Form
        out.println("<form method='post' action='calc' style='padding:12px; border:1px solid #ddd; border-radius:8px;'>");
        out.printf("First number:<br><input type='text' name='number1' value='%s' style='width:200px;'><br><br>",
                num1 == null ? "" : escape(num1));
        out.printf("Second number:<br><input type='text' name='number2' value='%s' style='width:200px;'><br><br>",
                num2 == null ? "" : escape(num2));
        out.println("Operation:<br>");
        out.printf("<label><input type='radio' name='op' value='+' %s> Add</label><br>",
                "+".equals(op) ? "checked" : "");
        out.printf("<label><input type='radio' name='op' value='-' %s> Subtract</label><br>",
                "-".equals(op) ? "checked" : "");
        out.printf("<label><input type='radio' name='op' value='*' %s> Multiply</label><br>",
                "*".equals(op) ? "checked" : "");
        out.printf("<label><input type='radio' name='op' value='/' %s> Divide</label><br><br>",
                "/".equals(op) ? "checked" : "");
        out.println("<input type='submit' value='Calculate' " +
                "style='padding:6px 14px; cursor:pointer;'>");
        out.println("</form>");

        // Latest result
        if (latestResult != null) {
            out.println("<div style='margin-top:16px; padding:12px; background:#f7faff; border:1px solid #cfe2ff; border-radius:8px;'>");
            out.println("<h3 style='margin-top:0;'>Latest Result</h3>");
            out.println("<div style='font-size:18px;'>" + latestResult + "</div>");
            out.println("</div>");
        }

        // History table
        out.println("<h3 style='margin-top:24px;'>History (this session)</h3>");
        if (history.isEmpty()) {
            out.println("<p>No calculations yet.</p>");
        } else {
            out.println("<table border='1' cellpadding='6' cellspacing='0' style='border-collapse:collapse; width:100%;'>");
            out.println("<tr style='background:#f0f0f0;'><th>#</th><th>Time</th><th>Number 1</th><th>Op</th><th>Number 2</th><th>Result</th></tr>");
            for (int i = 0; i < history.size(); i++) {
                CalcRecord r = history.get(i);
                out.println("<tr>");
                out.printf("<td>%d</td>", i + 1);
                out.printf("<td>%s</td>", escape(r.timestamp));
                out.printf("<td>%s</td>", escape(r.n1));
                out.printf("<td>%s</td>", escape(r.op));
                out.printf("<td>%s</td>", escape(r.n2));
                // r.resultText already includes formatting (e.g., <strong>), so render as-is:
                out.printf("<td>%s</td>", r.resultText);
                out.println("</tr>");
            }
            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    private static String escape(String s) {
        return s == null ? "" : s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
