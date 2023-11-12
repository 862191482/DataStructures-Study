package com.cat.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建结点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"张三");
        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();

        //前序遍历
        System.out.println("前序遍历");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if(resNode != null){
            System.out.printf("找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
        }else{
            System.out.printf("没有找到no=%d的英雄",5);
        }
        //中序遍历
        System.out.println("中序遍历");
        HeroNode resNode1 = binaryTree.infixOrderSearch(5);
        if(resNode != null){
            System.out.printf("找到了，信息为no=%d name=%s",resNode1.getNo(),resNode1.getName());
        }else{
            System.out.printf("没有找到no=%d的英雄",5);
        }
        //后序遍历
        System.out.println("中序遍历");
        HeroNode resNode2 = binaryTree.postOrderSearch(5);
        if(resNode != null){
            System.out.printf("找到了，信息为no=%d name=%s",resNode2.getNo(),resNode2.getName());
        }else{
            System.out.printf("没有找到no=%d的英雄",5);
        }
        //删除结点
        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("删除后");
        binaryTree.preOrder();
    }
}
//定义BinaryTree二叉树
class BinaryTree{
    private HeroNode root;
    public void setRoot(HeroNode root){
        this.root = root;
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
    public HeroNode preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
}
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
    public HeroNode preOrderSearch(int no){
        //判断当前结点
        if(this.no == no){
            return this;
        }
        //判断当前结点的左子结点是否为空，不为空，则向左递归的前序查找
        //向左递归的前序查找 找到结点，则返回
        HeroNode resNode = null;
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
    public HeroNode infixOrderSearch(int no){
        //判断当前结点的左子结点是否为空，不为空，则向左递归的中序查找
        //向左递归的中序查找 找到结点，则返回
        HeroNode resNode = null;
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
    public HeroNode postOrderSearch(int no){
        //判断当前结点的左子结点是否为空，不为空，则向左递归的后序查找
        //向左递归的后序查找 找到结点，则返回
        HeroNode resNode = null;
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