import java.util.List;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
//        tree.addR(30);
//        tree.addR(10);
//        tree.addR(5);
//        tree.addR(6);
//        tree.addR(4);
//        tree.addR(20);
//        tree.addR(40);
//        tree.addR(39);
//        tree.addR(17);
        tree.addR(6);
        tree.addR(5);
        tree.addR(4);
        tree.addR(3);
        tree.addR(2);
        tree.addR(1);
//        tree.turnLeft(tree.getNodeR(10), tree.getNodeR(15));
//        tree.turnRight(tree.getNodeR(10), tree.getNodeR(15));

//        tree.bigTurn(tree.getNodeR(15));

        for(Node n : tree.getTreeR()){
            System.out.println(n.getVal());
        }



    }




    public static void test1(Node node){
        node =  new Node(0, null, null, null);

//        node.setVal(0);
        System.out.println(node.getVal() + " in method");

    }

    public static void test2(Node node) {
        node.left = new Node(0, null, null, node);
        node.right = new Node(2, null, null, node);
    }
}
