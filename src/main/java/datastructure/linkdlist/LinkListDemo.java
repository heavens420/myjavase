package datastructure.linkdlist;


public class LinkListDemo {
    public static void main(String[] args) {
        MyNode node1 = new MyNode(1, "Black Ace", "黑桃A");
        MyNode node2 = new MyNode(2, "Black King", "黑桃K");
        MyNode node3 = new MyNode(3, "Black Quenen", "黑桃Q");
        MyNode node4 = new MyNode(4, "Black Jack", "黑桃J");
        MyNode node5 = new MyNode(5, "Black Ten", "黑桃10");

        MyLinkedList linkedList = new MyLinkedList();
        linkedList.add(node1);
        linkedList.add(node3);
        linkedList.add(node4);
        linkedList.insert(node3);
        linkedList.insert(node5);
        linkedList.insert(node2);

//        MyNode newNode = new MyNode(20,"Red King","红桃K");
//        linkedList.update(newNode);
//
//        MyNode node = new MyNode(5,null,null);
//        linkedList.delete(node);

        linkedList.reverseLinkList(linkedList.getHead());
        linkedList.printLinkList();

        //统计有效节点个数
//        System.out.println("链表的有效长度为"+linkedList.getLength(linkedList.getHead()));

        //查找倒数第n个节点
//        int index = 10;
//        MyNode targetNode = linkedList.getNode(linkedList.getHead(), index);
//        System.out.println("倒数第"+index+"个节点是"+targetNode);
    }
}

//构造结点
class MyNode{
    //数据域
    public int no;
    public String name;
    public String nickName;

    //指向下一个结点
    public MyNode next;

    public MyNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

//定义一个单向链表
class MyLinkedList{
    //定义头结点(不存放东西)
    private MyNode head = new MyNode(0,"","");

    public MyNode getHead() {
        return head;
    }

    //新增节点（加在最后）
    public void add(MyNode newNode){
        //因为头节点不能动，定义一个临时节点用于遍历链表
        MyNode temp = head;

        //遍历链表，当节点的next域不为空，temp后移，为空则遍历结束
        while (temp.next != null){
            temp = temp.next;
        }
        //将新节点加到链表末尾
        temp.next = newNode;
    }

    //插入节点（根据节点的 no 按顺序排列插入）
    public void insert(MyNode newNode){
        //定义临时节点
        MyNode temp = head;
        MyNode targetNode = null;
        boolean flag = false;//用于判断是否可以插入

        //遍历节点
        while (true){
            //链表为空 不能插入
            if (temp.next == null)
            {
                break;
            }
            else {
                //临时节点后移
                temp = temp.next;
                if (temp.no < newNode.no && (temp.next  == null || temp.next.no > newNode.no)){
                        //此时的temp正是newNode的前一个节点（即新节点应该插入此temp节点之后），
                        // 此时停止遍历，后面方便获取到此时的temp节点
                        targetNode = temp;
                    break;
                }else if (temp.no == newNode.no){
                    //newNode的no已存在
                    flag = true;
                    System.out.println("当前序号的节点已存在"+temp.no);
                }
            }
        }
        if (flag){
            //存在相同no的节点 无法插入
            return;
        }
        //将新节点插入
        //假设此时节点排列为（a->b->c),newNode要插入a,b之间则此时的temp节点相当于a,temp.next相当于b
        //此时将newNode的next域指向temp.next相当于newNode的next指向b
        if (targetNode.next != null){
            //若插入链表尾部 则newNode的next无法指向下一个节点
            newNode.next = targetNode.next;
        }
        //此时的temp相当于a，temp.next指向newNode，相当于a的next域指向newNode，此时newNode左右均连接完毕，插入完成
        targetNode.next = newNode;
    }

    //修改节点
    public void update(MyNode newNode){
        MyNode temp = head.next;

        //true表示找到要修改的节点 false表示没找到
        boolean isFind = false;

        //链表为空
        if (temp == null){
            System.out.println("链表为空");
            return;
        }

        //链表不为空
        while (true){
            //循环遍历找到需要修改的节点
            if (temp.no == newNode.no){
                isFind = true;
                break;
            }
            //链表节点后移
            temp = temp.next;

            if (temp == null){
                System.out.println("节点不存在");
                break;
            }
        }

        //找到了才修改节点信息
        if (isFind){
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        }else {
            System.out.println(newNode.no+"号节点不存在");
        }
    }

    //删除节点
    public void delete(MyNode node){
        MyNode temp = head;
        //是否找到待删除的节点
        boolean isFind = false;

        //节点为空
        if (temp.next == null){
            return;
        }

        //节点不为空
        while (true){
            if (temp.next == null){
                //未找到待删除节点
                System.out.println(node.no+"节点不存在，删除失败");
                break;
            }

            //寻找待删除结点的前一个结点 让该节点直接指向待删除结点的后一个节点，未被指向的节点会被垃圾回收机制自动回收
            if (temp.next.no == node.no){
                //找到待删除节点
                isFind = true;
                break;
            }

            //找不到 节点后移 继续遍历
            temp = temp.next;

        }

        //找到节点才能删除
        if (isFind){
            //跳过待删除节点 该节点自动被回收
            temp.next = temp.next.next;
        }
    }
    //显示链表
    public void printLinkList(){
        //定义临时结点 用于遍历链表
        MyNode temp = head;

        //遍历开始
        //链表不为空
        while (temp.next != null){
            temp = temp.next;
            System.out.println(temp);
        }
        //链表为空
//        System.out.println("链表为空");
    }


    //1.获取有效节点的个数(有头节点的去掉头节点)
    public int getLength(MyNode head){
        int length = 0;
        MyNode temp = head;

        if (head.next == null){
            return length;
        }else {
            while (true){
                if (temp.next != null){
                    ++length;
                }else {
                    break;
                }
                temp = temp.next;
            }
            return length;
        }
    }

    //2.查找倒数第index个节点
    public MyNode getNode(MyNode head,int index){
        int length = getLength(head);
        int no = length - index + 1;
        MyNode temp = head;
        if (temp.next == null){
            System.out.println("链表为空");
            return null;
        }
        if (index >length || index < 1){
//            System.out.println("index 输入有误");
            throw new RuntimeException("index 输入有误");
        }
        for (int i = 0; i < no; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //3.反转单链表
    public void reverseLinkList(MyNode head){
        MyNode temp = head.next;
        MyNode next = null;
        //新建反转后链表的头节点
        MyNode reverseNode = new MyNode(0,null,null);
        if (temp.next == null){
            System.out.println("链表为空");
            return;
        }

        //遍历链表
        while (temp.next != null){
            //保存反转节点的下一个节点，防止打开链表之后，该节点丢失
            next = temp.next;
            temp.next = reverseNode.next;
            reverseNode.next = next;
            //节点后移
            temp = next;
        }
        head.next = reverseNode.next;
    }
}