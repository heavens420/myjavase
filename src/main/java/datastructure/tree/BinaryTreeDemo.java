package datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        Node node1 = new Node(1,"宋江");
        Node node2 = new Node(2,"吴用");
        Node node3 = new Node(3,"玉麒麟");
        Node node4 = new Node(4,"林冲");
        Node node5 = new Node(5,"林小冲");
        Node node6 = new Node(6,"林二冲");
        Node node7 = new Node(7,"林大冲");

        node1.setLchild(node2);
        node1.setRchild(node3);
        node2.setLchild(node6);
        node2.setRchild(node7);
        node3.setLchild(node4);
        node3.setRchild(node5);

        binaryTree.setRoot(node1);
        System.out.println("先序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.midOrder();
        System.out.println("后序遍历");
        binaryTree.afterOrder();

        System.out.println("先序搜索");
        System.out.println(binaryTree.preSearch(5));
        System.out.println("中序搜索");
        System.out.println(binaryTree.midSearch(5));
        System.out.println("后序搜索");
        System.out.println(binaryTree.afterSearch(5));

    }

}

class BinaryTree{
    private Node root = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder(){
        if (this.root != null) {
            root.preOrder();
        }
    }

    public void midOrder(){
        if (this.root != null) {
            root.midOrder();
        }
    }

    public void afterOrder(){
        if (root != null) {
            this.root.afterOrder();
        }
    }

    public Node preSearch(int no){
        if (root != null) {
            return this.root.preSearch(no);
        }else {
            return null;
        }
    }

    public Node midSearch(int no){
        if (root != null) {
            return this.root.midSearch(no);
        }else {
            return null;
        }
    }

    public Node afterSearch(int no){
        if (root != null){
            return this.root.afterSearch(no);
        }else {
            return null;
        }
    }
}

@Data
@AllArgsConstructor
class Node {
    private int id;
    private String name;
    private Node lchild;
    private Node rchild;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // 先序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.lchild != null){
            this.lchild.preOrder();
        }
        if (this.rchild != null) {
            this.rchild.preOrder();
        }
    }

    // 中序遍历
    public void midOrder(){
        if (this.lchild != null) {
            this.lchild.midOrder();
        }
        System.out.println(this);
        if (this.rchild != null) {
            this.rchild.midOrder();
        }
    }

    // 后序遍历
    public void afterOrder(){
        if (this.lchild != null) {
            this.lchild.afterOrder();
        }
        if (this.rchild != null) {
            this.rchild.afterOrder();
        }
        System.out.println(this);
    }

    // 定义要查找的目标节点
    Node node = null;

    // 先序查找
    public Node preSearch(int no){
        node = null; // 重置目标节点，防止其他查找方式找到后，干扰本次查找
        if (this.id == no){
            return this;
        }

        if (this.lchild != null) {
            node = this.lchild.preSearch(no);
        }

        if (this.rchild != null) {
            node = this.rchild.preSearch(no);
        }

        return this.node;
    }

    // 中序查找
    public Node midSearch(int no){
        node = null;
        if (this.lchild != null){
            node = this.lchild.midSearch(no);
        }
        if (this.id == no){
            return this;
        }
        if (this.rchild != null) {
            node = this.rchild.midSearch(no);
        }
        return node;
    }

    // 后序查找
    public Node afterSearch(int no){
        node = null;
        if (this.lchild != null) {
            this.node = this.lchild.afterSearch(no);
        }
        if (this.rchild != null) {
            this.node = this.rchild.afterSearch(no);
        }
        if (this.id == no) {
            return this;
        }
        return node;
    }
}