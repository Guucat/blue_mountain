package week_1.task_2;

/**
 * @author shengyi
 * @create 2021/7/24 - 13:56
 */
public class DoublyCircularLinkedList {
    private String element;
    public DoublyCircularLinkedList next;
    public DoublyCircularLinkedList prior;

    public DoublyCircularLinkedList(String element){
        this.element = element;
        this.next = this;
        this.prior = this;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    /**
     * 只能增加单结点
     *     public void addNextNode(DoublyCircularLinkedList node){
     *         this.next.prior = node;
     *         node.prior = this;
     *         node.next = this.next;
     *         this.next= node;
     *     }
     *     public void addPriorNode(DoublyCircularLinkedList node){
     *         this.prior.next = node;
     *         node.next = this;
     *         node.prior = this.prior;
     *         this.prior = node;
     *     }
     */

    /**
     *
     * @param node 增强版 可以是另一个循环链表的结点
     */
    public void addNextNode(DoublyCircularLinkedList node){
        node.prior.next = this.next;
        this.next.prior = node.prior;
        this.next = node;
        node.prior = this;
    }

    /**
     *
     * @param node 增强版 可以是另一个循环链表的结点
     */
    public void addPriorNode(DoublyCircularLinkedList node){
        node.next.prior = this.prior;
        this.prior.next = node.next;
        this.prior = node;
        node.next = this;
    }

    /**
     * 删除当前结点的后一个结点
     */
    public void deleteNextNode(){
        this.next = this.next.next;
        this.next.prior = this;
    }

    /**
     * 删除当前结点的前一个结点
     */
    public void deletePriorNode(){
        this.prior = this.prior.prior;
        this.prior.next = this;
    }

    /**
     * 删除当前结点
     */
    public void deleteNode(){
        this.prior.next = this.next;
        this.next.prior = this.prior;
        this.next = this;
        this.prior = this;
    }

    /**
     *
     * @param n n<0删除当前结点第前n个结点,n>0删除当前结点第后n个结点，n=0删除当前结点
     */
    public void deleteNodeByIndex(int n){
        DoublyCircularLinkedList temporary = this;
        if (n == 0){
            temporary.deleteNode();
        } else if (n > 0) {
            for (int i = 0; i < n; i++) {
                temporary = temporary.next;
            }
            temporary.deleteNode();
        } else {
            for (int i = 0; i > n; i--) {
                temporary = temporary.next;
            }
            temporary.deleteNode();
        }

    }
    /**
     *
     * @param n n<0修改当前结点第前n个结点,n>0修改当前结点第后n个结点，n=0修改当前结点
     * @param newElement 新的结点值
     */
    public void updateNodeByIndex(int n,String newElement) {
        DoublyCircularLinkedList temporary = this;
        if (n == 0) {
            this.setElement(newElement);
            System.out.println("修改成功");
        }
        else if (n > 0) {
            for (int i = 0; i < n; i++) {
                temporary = temporary.next;
            }
            temporary.setElement(newElement);
            System.out.println("修改成功");
        }
        else {
            for (int i = 0; i > n; i--) {
                temporary = temporary.prior;
            }
            temporary.setElement(newElement);
            System.out.println("修改成功");
        }
    }

    /**
     *
     * @param element 需要查找的值
     * @return 含有该值的结点,若值不存在则返回null
     */
    public DoublyCircularLinkedList getNodeByValue(String element){
        DoublyCircularLinkedList temporary = this;
        while (true){
            if (temporary.element.equals(element)) {
                return temporary;
            }
            temporary = temporary.next;
            if (temporary == this) {
                System.out.println("该值不存在！返回当前结点");
                return this;
            }
        }
    }

    /**
     *
     * @return 链表的长度
     */
    public int getNodesLength(){
        int l = 1;
        DoublyCircularLinkedList flag = this;
        DoublyCircularLinkedList temporary = this;
        while (temporary.next != flag){
            l++;
            temporary = temporary.next;
        }
        return l;
    }

    /**
     * 依次打印当前结点到最后一个结点的值
     */
    public void viewNodes(){
        DoublyCircularLinkedList temporary = this;
        StringBuilder  graph = new StringBuilder("--> ");
        for (int i = 0; i < this.getNodesLength(); i++) {
            graph.append(temporary.element).append(" --> ");
            temporary = temporary.next;
        }
        System.out.println(graph);
    }
}
