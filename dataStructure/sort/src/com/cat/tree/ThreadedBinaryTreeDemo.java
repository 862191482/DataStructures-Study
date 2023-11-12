package com.cat.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode1 root = new HeroNode1(1,"tom");
        HeroNode1 node2 = new HeroNode1(3,"jake");
        HeroNode1 node3 = new HeroNode1(6,"smith");
        HeroNode1 node4 = new HeroNode1(8,"mary");
        HeroNode1 node5 = new HeroNode1(10,"king");
        HeroNode1 node6 = new HeroNode1(14,"dim");
        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        HeroNode1 leftNode = node5.getLeft();
        System.out.println(leftNode);

        threadedBinaryTree.threadedList();
    }
}
//定义ThreadedBinaryTree二叉树
class ThreadedBinaryTree{
    private HeroNode1 root;
    //为实现线索化，需要创建指向当前结点的前驱结点的指针
    //递归线索化时，pre总是保留前一个结点
    private HeroNode1 pre = null;
    public void setRoot(HeroNode1 root){
        this.root = root;
    }
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    //遍历线索化二叉树
    public void threadedList(){
        //存储当前结点，从root开始
        HeroNode1 node = root;
        while(node != null){
            //循环找到leftType == 1的结点,当其 == 1时，说明该结点是按照线索化处理后的有效结点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前结点
            System.out.println(node);
            //当前结点的右指针指向的是后继结点，就一直输出
            while(node.getRightType() == 1){
                //获取当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }


    //对二叉树进行中序线索化的结点

    /**
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(HeroNode1 node){
        if(node == null){
            return;
        }
        //(一)线索化左子树
        threadedNodes(node.getLeft());
        //(二)线索化当前结点
        //前驱结点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);//表示指向前驱结点
        }
        //后继结点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);//表示指向后继结点
        }
        //!!!每处理一个结点，让当前结点是下一个结点的前驱结点
        pre = node;
        //(三)线索化右子树
        threadedNodes(node.getRight());
    }
    //删除结点
    public void delNode(int no){
        if(root != null){
            //立即判断root是否删除
            if(root.getNo() == no){
                root = null;
            }else{
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除");
        }
    }
    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //前序遍历查找
    public HeroNode1 preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历查找
    public HeroNode1 infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序遍历查找
    public HeroNode1 postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
}
class HeroNode1{
    private int no;
    private String name;
    private HeroNode1 left;
    private HeroNode1 right;
    //如果leftType == 0表示指向左子树，== 1表示指向前驱结点
    //如果rightType == 0表示指向右子树，== 1表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //递归删除结点
    //1.如果删除的结点是叶子结点，则删除该结点
    //2.如果删除的结点是非叶子结点，则删除该子树
    public void delNode(int no){
        /*  思路：
            1.因为二叉树是单向的，所以我们判断当前结点的子结点是否需要删除结点，而不能驱判断当前结点是否需要被删除
            2.如果当前结点的左子结点不为空，并且左子结点是要删除的结点，就将this.left = null;并且返回（结束递归删除）
            3.如果当前结点的右子结点不为空，并且右子结点是要删除的结点，就将this.right = null;并且返回（结束递归删除）
            4.如果2 3 步没有删除结点，那么向左子树进行递归删除
            5.如果4步也没有删除结点，则向右子树进行递归删除
        */
        //2.
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        //3.
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //4.
        if(this.left != null){
            this.left.delNode(no);
        }
        if(this.right != null){
            this.right.delNode(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder(){
        //先输出父结点
        System.out.println(this);
        //递归左子树前序遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归左子树前序遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //递归左子树前序遍历
        if(this.left != null){
            this.left.postOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.postOrder();
        }
        //输出父结点
        System.out.println(this);
    }
    //前序遍历查找
    /**
     * @param no 查找no
     * @return 找到返回该node,反之返回null
     */
    public HeroNode1 preOrderSearch(int no){
        //判断当前结点
        if(this.no == no){
            return this;
        }
        //判断当前结点的左子结点是否为空，不为空，则向左递归的前序查找
        //向左递归的前序查找 找到结点，则返回
        HeroNode1 resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        //左子树找到
        if(resNode != null){
            return resNode;
        }
        //向左递归的前序查找 没找到结点，则继续判断
        //判断当前结点的右子结点是否为空，不为空，则向右递归的前序查找
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    //中序遍历查找
    public HeroNode1 infixOrderSearch(int no){
        //判断当前结点的左子结点是否为空，不为空，则向左递归的中序查找
        //向左递归的中序查找 找到结点，则返回
        HeroNode1 resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        //判断当前结点
        if(this.no == no){
            return this;
        }
        //向左递归的中序查找 没找到结点，则继续判断
        //判断当前结点的右子结点是否为空，不为空，则向右递归的中序查找
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后序遍历查找
    public HeroNode1 postOrderSearch(int no){
        //判断当前结点的左子结点是否为空，不为空，则向左递归的后序查找
        //向左递归的后序查找 找到结点，则返回
        HeroNode1 resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        //向左递归的后序查找 没找到结点，则继续判断
        //判断当前结点的右子结点是否为空，不为空，则向右递归的后序查找
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        //判断当前结点
        if(this.no == no){
            return this;
        }
        return resNode;
    }
}