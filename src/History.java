import java.util.Stack;

public class History {
    Stack<String> stack = new Stack<>();

    void add(String t) {
        stack.push(t);
    }

    void undo() {
        if (stack.isEmpty()) System.out.println("No transactions.");
        else System.out.println("Undo -> " + stack.pop() + " removed");
    }

    void showLast() {
        if (stack.isEmpty()) System.out.println("No transactions.");
        else System.out.println("Last: " + stack.peek());
    }

    String getLast() {
        if (stack.isEmpty()) return null;
        return stack.peek();
    }
}