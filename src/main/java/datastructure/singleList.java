package datastructure;


import lombok.val;

import javax.sound.midi.Soundbank;
import java.util.Stack;

public class singleList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Node n1 = new Node(1, "宋江", "及时雨");
        Node n2 = new Node(2, "卢俊义", "玉麒麟");
        Node n3 = new Node(3, "吴用", "智多星");
        Node n4 = new Node(4, "林冲", "豹子头");
        Node n5 = new Node(5, "林冲", "豹子头");
        Node n6 = new Node(6, "林冲", "豹子头");
//        list.add(n1);
//        list.add(n2);
//        list.add(n3);
//        list.add(n4);
//        list.show();
        System.out.println("---------Ordered------------------");
        list.addByOrder(n1);
        list.addByOrder(n6);
        list.addByOrder(n4);
        list.addByOrder(n2);
        list.addByOrder(n3);
        list.addByOrder(n5);

//        list.delete(n4);
//        list.delete(5);
        list.show();
        System.out.println();
//        System.out.println("链表长度：" + list.getSize());
//
//        System.out.println(list.getNode(2));
//
        System.out.println("____________反转单链表-----------");
//        list.reverseLinkedList().show();
//        list.reverseLinkdList2().show();
        list.getReversedList().show();
    }


}

class LinkedList {
    public LinkedList() {
        //创建头节点
        Node head = new Node(0, "", "");
    }

    Node head = new Node();

    private Node getHead() {
        return head;
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        //头节点不能动 创建一个头节点辅助节点  用于代替头节点遍历
        Node temp = getHead();
        while (true) {
            if (temp.next != null) {
                //如果没找到 temp 向后移
                temp = temp.next;
            } else {
                break;
            }
        }
        temp.next = node;
    }

    /**
     * 插入节点并自动排序
     *
     * @param node
     */
    public void addByOrder(Node node) {
        boolean flag = false;
        Node temp = head;
        while (true) {
            //链表为空 flag不改变 可以插入
            if (temp.next == null) {
                break;
            }
            //插入的节点 no 小于前一个节点 插在该节点后面 flag不改变
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                //插入的节点 no 相同 认为节点已存在 flag 改变
                flag = true;
                break;
            } else {
                temp = temp.next; //节点后移
            }
        }
        if (flag) {
            //flag为真  节点存在无法插入
            System.out.println(node + " 该节点已存在 无法重新插入");
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 按节点删除
     *
     * @param node
     */
    public void delete(Node node) {
        Node temp = head;
//        false 不可删除 true 可删除
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next == node) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println(node + " 删除的节点不存在");
        }
    }

    /**
     * 按节点no删除
     */
    public void delete(int no) {
        Node temp = head;
//        false 不可删除  true 可以删除
        boolean flag = false;
        while (true) {
            if (temp.next == null) { //遍历到最后一个节点 结束循环
                break;
            } else if (temp.next.no == no) { //找到目标节点
                flag = true;
                break;
            }
            //未找到目标节点 继续后移遍历
            temp = temp.next;
        }

        if (true) {
            temp.next = temp.next.next;
        } else {
            System.out.println("目标节点不存在 删除失败");
        }
    }

    /**
     * 获取链表长度（不包含头节点）
     */
    public int getSize() {
        Node temp = head;
        int count = 0;
        while (true) {
            if (head.next == null) { // 链表为空 长度为0
                return count;
            } else if (temp.next != null) { // 链表非空 节点后移 长度加1
                temp = temp.next;
                count++;
            } else { // 链表遍历结束（temp.next == null） 返回长度
                return count;
            }
        }
    }

    /**
     * 获取倒数第i个节点（即 list.length + 1 - i）
     */
    public Node getNode(int i) {
        Node temp = head;
        int length = 0;
        // 循环次数
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }
        int count = length - i + 1;
        temp = head;
        if (i > length + 1 - i || i < 1) {
            throw new RuntimeException("该节点不存在");
        }
        while (count > 0) {
            count--;
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转单链表
     * 将原链表的节点依次插入新的头节点之后 实现反转
     */
    public LinkedList reverseLinkedList() {
        LinkedList list = new LinkedList();
        Node temp = head;
        Node reverse = new Node();
        Node temp1 = new Node();
        list.add(temp);
        while (temp.next != null) {
            temp1.next = temp.next.next;
            temp.next.next = reverse.next;
            reverse.next = temp.next;

            temp.next = temp1.next;
        }

        list.add(reverse.next);
        return list;
    }

    /**
     * 单链表反转
     * 将原链表的最后一个节点插入新链表的最后面
     */
    public LinkedList reverseLinkdList2() {
        Node temp = head;
//        Node reverse = new Node();
        LinkedList list = new LinkedList();
        int length = 0;
        // 从倒数第一个节点开始遍历
        int i = 1;

        // 获取链表长度
        while (temp.next != null){
            temp = temp.next;
            length++;
        }

        // 获取长度导致临时节点指针指向最后一个节点  重置头节点指针的位置为 链表头节点
        temp = head;

        // 最多遍历 length次
        while (i <= length) {
            // 获取链表的最后一个节点要遍历的次数
            int loop = length - i + 1;

            // 获取链表的最后一个节点
            while (loop > 0) {
                temp = temp.next;
                // 重置最后一个节点的位置，使最后一个节点逻辑前移
                loop--;
            }
//            System.out.println(temp+"000000");
            // 将最链表的最后一个节点添加到新的链表的末尾
            list.add(temp);
            // 获取倒数第i+1个节点
            i++;
//            reverse.next = temp;
//            reverse = reverse.next;

            // 将倒数第i个节点的后继置空，防止该节点后面还有n个其他节点
            temp.next = null;
            // 重置头节点指针 为下一次从头遍历
            temp = head;
        }
        return list;
    }

    /**
     * 逆序打印单链表，不破坏原链表
     * 使用栈
     */
    public LinkedList getReversedList(){
        LinkedList list = new LinkedList();
        Node temp = head;
        Stack stack = new Stack();

        while (temp.next != null){
            stack.push(temp.next);
            temp = temp.next;
        }

        while (stack.size() > 0){
            // 获取栈顶节点
            Node node = ((Node) stack.pop());
            // 将该节点的后继置空，否则栈中元素添加到链表时，会连同自己的后继节点一起添加到新的链表导致死循环
            // 置空则破坏了原链表
            node.next = null;
            // 输出当前栈顶元素 不破坏原链表 但无法将结果返回
//            System.out.println(stack.pop()+"#########");
            list.add(node);
        }
        return list;
    }

    /**
     * 打印节点
     */
    public void show() {
        Node temp = head.next;
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        while (true) {
            if (temp == null) {
                break;
            }
            //将temp后移
            System.out.print(temp);
            if (temp.next != null) {
                System.out.println(" --> ");
            }
            temp = temp.next;
        }
    }
}

class Node {
    public int no;
    public String name;
    public String nickname;
    public Node next;

    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public Node() {
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