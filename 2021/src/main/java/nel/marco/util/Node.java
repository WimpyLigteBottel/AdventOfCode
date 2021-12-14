package nel.marco.util;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public String current;
    public List<Node> next = new ArrayList<>();

    public Node(String[] parts) {
        this.current = parts[0];
        next(new Node(parts[1]));
    }

    public Node(String current) {
        this.current = current;
    }
    public void next(Node next) {
        this.next.add(next);
    }

    /**
     * This only works if its linked 1 -1
     *
     * @return
     */
    public Node invert() {
        String next = new String(current);
        this.current = this.next.get(0).current;
        this.next.clear();
        this.next.add(new Node(next));
        return this;
    }

    /**
     * This only works if its linked 1 -1
     *
     * @return
     */
    public String getNextValue() {
        return this.next.get(0).current;
    }

    @Override
    public String toString() {

        if(next.isEmpty())
            return current + "->?";
        return current + "->" + next;
    }

    /**
     * Only works if its linked 1-1
     *
     * @return
     */
    public String directMapping() {
        return current + "->" + next.get(0).current;
    }
}
