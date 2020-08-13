import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree {
    Node root;

    public Tree() {

    }

    public void add(int val){
        if(root == null){
            root = new Node(val, null, null, null);
        }
        else{
            Node currentNode = root;
            while(true){
                if(currentNode.val < val ){
                    if(currentNode.right == null) {
                        currentNode.right = new Node(val, null, null, currentNode);
                        break;
                    }
                    currentNode = currentNode.right;
                }
                if(currentNode.val >= val ){
                    if(currentNode.left == null) {
                        currentNode.left = new Node(val, null, null, currentNode);
                        break;
                    }
                    currentNode = currentNode.left;
                }
            }
        }
    }

    public void addR(int val){
        if(root == null){
            root = new Node(val, null, null, null);
        }else{
            addToTree(val, root);
        }
    }

    private void addToTree(int val, Node root){
        if(root.val > val){
            if(root.left == null) {
                Node temp = new Node(val, null, null, root);
                root.left = temp;
                balance(root);
            }
            else
                addToTree(val, root.left);
        }
        if(root.val < val){
            if(root.right == null) {
                Node temp = new Node(val, null, null, root);
                root.right = temp;
                balance(root);
            }
            else
                addToTree(val, root.right);
        }
    }



    public boolean contains(int val){
        if(root == null)
            return false;
        Node curr = root;
        while(curr != null){
            if(val == curr.val)
                return true;
            else if(val > curr.val)
                curr = curr.right;
            else if(val < curr.val)
                curr = curr.left;
        }
        return false;
    }

    public boolean containsR(int val){
        return containsRecursion(val, root);
    }

    private boolean containsRecursion(int val, Node root){
        if(root == null)
            return false;
        if(root.getVal() == val)
            return true;
        if(val > root.getVal())
            return containsRecursion(val, root.right);
        else
            return containsRecursion(val, root.left);
    }


    public List<Node> getTree(){
        List<Node> list = new ArrayList<>();
        if(root == null)
            return list;
        Node curr = root;
        Stack<Node> stack = new Stack<>();

        while (curr != null || stack.size() > 0) {
            while (curr != null ) {
                stack.push(curr);
                curr = curr.left;
            }
            Node temp = stack.pop();
            list.add(temp);
            curr = temp.right;
        }
        return list;
    }

    public List<Node> getTreeR(){
        List<Node> list = new ArrayList<>();
        getTreeRecursion(list, root);
        return list;
    }

    private void getTreeRecursion(List<Node> list, Node node){
        if(node == null)
            return;
        getTreeRecursion(list, node.left);
        list.add(node);
        getTreeRecursion(list, node.right);
    }

    public void removeR(int val){
        Node node = getNodeR(val);
        if(node == null)
            return;
        Node leftLast = getLeftRecursion(node.right);

        Node parent = node.parent;
        if(parent != null) {
            if (parent.left.equals(node))
                parent.left = leftLast;
            else
                parent.right = leftLast;
        }
        if(node.left != null) {
//            leftLast.parent.left = leftLast.right;
            leftLast.left = node.left;
//            leftLast.right = leftLast.right;
        }
    }

    private Node getLeftRecursion(Node node){
        if(node.left == null)
            return node;
        return getLeftRecursion(node.left);
    }

    public Node getNodeR(int val){
        return getNodeRecursion(root, val);
    }
    private Node getNodeRecursion(Node node, int val) {
        if(node == null)
            return null;
        if(node.getVal() == val)
            return node;
        if(val > node.getVal())
            return getNodeRecursion(node.right, val);
        else
            return getNodeRecursion(node.left, val);
    }

    public void turnLeft(Node p, Node q){
        p.right = q.left;
        q.left = p;
        Node temp = p.parent;
        p.parent = q;
        if(p.right != null)
            p.right.parent = p;
        q.parent = temp;
        if (q.parent != null)
            q.parent.right = q;
        if(p == root) {
            root = q;
            return;
        }
        if(q == root)
            root = q.right;
    }

    public void turnRight(Node p, Node q){
        p.parent = q.parent;
        if(q.parent != null){
            q.parent.left = p;
        }
        q.parent = p;
        q.left = p.right;
        p.right = q;

        if(q == root){
            root = p;
            return;
        }
    }

    public void bigTurnRL(Node q){
        Node p = q.parent;
        Node s = q.left;
        turnRight(s, q);
        turnLeft(p, s);
    }

    public void bigTurnLR(Node q){
        Node p = q.parent;
        Node s = q.left;
        turnLeft(p, s);
        turnRight(s, q);
    }

    public int getHeight(Node root){
        if(root == null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    private int bfactor(Node root){
        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);
        int balanceFactor = rHeight - lHeight;
        return balanceFactor;
    }

    private void balance(Node root){

        if(bfactor(root) == 2) {
            if (bfactor(root.right) < 0){
                bigTurnRL(root);
                return;
            }
            turnLeft(root, root.right);
        }
        if(bfactor(root) == -2){
            if (bfactor(root.left) > 0){
                bigTurnLR(root);
                return;
            }
            turnRight(root.left, root);
            return;
        }
        if(root.parent != null)
            balance(root.parent);
    }



}
