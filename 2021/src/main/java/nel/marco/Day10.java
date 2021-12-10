package nel.marco;

import java.util.*;

public class Day10 {

    public long part1(List<String> input) {

        Map<Character, Integer> points = new HashMap<>();
        points.put(')', 3);
        points.put(']', 57);
        points.put('}', 1197);
        points.put('>', 25137);

        long total = 0;
        for (String s : input) {
            Stack<Character> stack = buildStack(s);
            Character a = findFirstInvalidCharacter(stack);
            if (a == ' ') {
                continue;
            }
            total += points.getOrDefault(a, 0);

        }

        return total;
    }


    public long part2(List<String> input) {
        Map<Character, Integer> points = new HashMap<>();
        points.put(')', 1);
        points.put(']', 2);
        points.put('}', 3);
        points.put('>', 4);

        long total = 0;
        List<Long> scores = new ArrayList<>();
        for (String s : input) {
            Stack<Character> stack = buildStack(s);
            Character a = findFirstInvalidCharacter(stack);
            if (a != ' ') {
                continue;
            }
            char[] toCompleteStack = completeStack(stack);

            long score = 0;

            for (int i = 0; i < toCompleteStack.length; i++) {
                score *= 5;
                score += points.get(toCompleteStack[i]);
            }
            scores.add(score);
        }

        scores.sort(Long::compareTo);


        int middle = (scores.size() / 2);
        Long middleScore = scores.get(middle);

        return middleScore;
    }

    private char[] completeStack(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            Character pop = stack.pop();

            if (pop == '[') {
                sb.append("]");
            } else if (pop == '<') {
                sb.append(">");
            } else if (pop == '(') {
                sb.append(")");
            } else if (pop == '{') {
                sb.append("}");
            }
        }


        return sb.toString().toCharArray();
    }

    private Character findFirstInvalidCharacter(Stack<Character> stack) {

        Stack<Character> copyOfStack = (Stack<Character>) stack.clone();
        Character invalid = ' ';


        while (!copyOfStack.isEmpty()) {
            Character pop = copyOfStack.pop();

            switch (pop) {
                case ']' -> {
                    invalid = ']';
                }
                case '}' -> {
                    invalid = '}';
                }
                case '>' -> {
                    invalid = '>';
                }
                case ')' -> {
                    invalid = ')';
                }
                default -> {
                }
            }

        }

        return invalid;
    }

    private Stack<Character> buildStack(String s) {
        Stack<Character> stack = new Stack<>();
        String[] split = s.split("");

        for (int i = 0; i < split.length; i++) {

            if (stack.isEmpty())
                stack.add(' ');

            Character top = stack.peek();
            char current = split[i].toCharArray()[0];

            if (top == '[' && current == ']') {
                stack.pop();
            } else if (top == '<' && current == '>') {
                stack.pop();
            } else if (top == '(' && current == ')') {
                stack.pop();
            } else if (top == '{' && current == '}') {
                stack.pop();
            } else {
                if (stack.peek() == ' ') {
                    stack.pop();
                }
                stack.add(current);
            }
        }
        return stack;
    }
}
