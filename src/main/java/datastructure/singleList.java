package datastructure;


import javax.sound.midi.Soundbank;

public class singleList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Node n1 = new Node(1,"宋江","及时雨");
        Node n2 = new Node(2,"卢俊义","玉麒麟");
        Node n3 = new Node(3,"吴用","智多星");
        Node n4 = new Node(4,"林冲","豹子头");
//        list.add(n1);
//        list.add(n2);
//        list.add(n3);
//        list.add(n4);
//        list.show();
        System.out.println("---------Ordered------------------");
        list.addByOrder(n3);
        list.addByOrder(n1);
       // list.addByOrder(n4);
        list.addByOrder(n2);
        list.show();
    }


}
class LinkedList{
    //创建头节点
    private Node head = new Node(0,"","");

    public Node getHead(){
        return head;
    }

    //添加节点
    public void add(Node node){
        //头节点不能动 创建一个头节点辅助节点  用于代替头节点遍历
        Node temp = head;
        while (true){
            if (temp.next != null){
                //如果没找到 temp 向后移
                temp = temp.next;
            }else {
                break;
            }
        }
        temp.next = node;
    }

    //插入节点并自动排序
    public void addByOrder(Node node){
        boolean flag = false;
        Node temp = head;
        while (true){
            //链表为空 flag不改变 可以插入
            if (head.next == null){
                break;
            }
            //插入的节点 no 小于前一个节点 插在该节点后面 flag不改变
            if (temp.no > node.no){
                break;
            }else if (temp.no == node.no){
                //插入的节点 no 相同 认为节点已存在 flag 改变
                flag = true;
                break;
            }
            temp = temp.next; //节点后移
        }
        if (flag){
            //flag为真  节点存在无法插入
            System.out.println("该节点已存在");
        }else {
            node.next = temp.next;
            temp.next = node;
        }
    }
    public void show(){
        Node temp = head.next;
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        while (true){
            if (temp == null){
                break;
            }
            //将temp后移
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Node{
    public int no;
    public String name;
    public String nickname;
    public Node next;

    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}