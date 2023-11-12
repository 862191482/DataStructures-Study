package com.cat.avltree;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树的高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前的根结点=" + avlTree.getRoot());
    }
}
class AVLTree{
    private Node root;
    public Node getRoot(){
        return root;
    }
    //查找要删除的结点
    public Node search(int value){
        if(root == null){
            return null;
        }else{
            return root.serach(value);
        }
    }
    //查找父结点
    public Node searchParent(int value){
        if(root == null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    /**
     *
     * @param node 传入的结点（当做二叉排序树的根结点）
     * @return 以node为根结点的二叉排序树的最小结点的值
     * 删除以node为根结点的二叉排序树的最小结点
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左结点，找到最小值
        while(target.left != null){
            target = target.left;
        }
        //这时target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }
    //删除结点
    public void delNode(int value){
        if(root == null){
            return;
        }else{
            //1.找到要删除结点的targetNode
            Node targetNode = search(value);
            //没有找到要删除的结点
            if(targetNode == null){
                return;
            }
            //这棵树只有一个结点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            //查找targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除结点是叶子结点
            if(targetNode.left == null && targetNode.right == null){
                //判断targetNode是父结点的左子结点还是右子结点
                if(parent.left != null && parent.left.value == value){
                    parent.left = null;
                }else if(parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else{//删除只有一颗子树的结点
                //要删除的结点有左子结点
                if(targetNode.left != null){
                    if(parent != null){//root结点没有父结点
                        //targetNode是parent的左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.left;
                        }else{
                            parent.right = targetNode.left;
                        }
                    }else{
                        root = targetNode.left;
                    }

                }else{//要删除的结点有右子结点
                    if(parent != null){
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        }else{
                            parent.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }

                }
            }
        }
    }
    //添加结点
    public void add(Node node){
        if(this.root == null){
            root = node;
        }else{
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    //返回左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }
    //返回右子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }
    //左旋转方法
    private void leftRotate(){
        //创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        //1.把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //2.把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = right.left;
        //3.把当前结点的值替换成右子结点的值
        value = right.value;
        //4.把当前结点的右子树设置成当前结点的右子树的右子树
        right = right.right;
        //5.把当前结点的左子树（左子结点）设置成新的结点
        left = newNode;
    }
    //右旋转方法
    private void rightRotate(){
        //创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        //1.把新的结点的右子树设置成当前结点的右子树
        newNode.right = right;
        //2.把新的结点的左子树设置成当前结点的左子树的右子树
        newNode.left = left.right;
        //3.把当前结点的值替换成左子结点的值
        value = left.value;
        //4.把当前结点的左子树设置成当前结点的左子树的左子树
        left = left.left;
        //5.把当前结点的右子树（右子结点）设置成新的结点
        right = newNode;
    }
    //返回当前结点的高度，以该结点为根结点的树的高度
    public int height(){
         return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }
    /**
     * 查找要删除的结点
     * @param value 希望删除的结点
     * @return 找到返回该结点，否则返回null
     */
    public Node serach(int value){
        if(value == this.value){
            return this;
        }else if(value < this.value){//查找值小于当前结点，向左子树查找
            //如果左子结点为空.
            if(this.left == null){
                return null;
            }
            return this.left.serach(value);
        }else{//查找值大于当前结点，向右子树查找
            if(this.right == null){
                return null;
            }
            return this.right.serach(value);
        }
    }

    /**
     *查找要删除结点的父结点
     * @param value 要找到结点的值
     * @return 返回要删除结点的父结点，如果没有返回null
     */
    public Node searchParent(int value){
        if((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }else{
            //查找值小于当前结点，并且当前的结点左子结点不为空
            if(value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if(value >= this.value && this.left != null){
                return this.right.searchParent(value);
            }else{
                return null;//没有找到父结点
            }
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //递归添加结点
    //需满足二叉排序树
    public void add(Node node){
        if(node == null){
            return;
        }
        //判断传入的结点的值，和当前子树的根结点的值的关系
        if(node.value < this.value){
            //如果当前结点左子结点为null
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            //如果当前结点右子结点为null
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
        //当添加完一个结点后，如果（右子树的高度-左子树的高度）> 1，左旋转
        if(rightHeight() - leftHeight() > 1){
            //如果它的右子树的左子树的高度大于它右子树的右子树
            if(right != null && right.leftHeight() > right.rightHeight()){
                //先对当前结点的右结点（右子树）进行右旋转
                right.rightRotate();
                //再对当前结点进行左旋转
                leftRotate();
            }else{
                //直接进行左旋转
                leftRotate();
            }
            return;//必须要
        }
        //当添加完一个结点后，如果（左子树的高度-右子树的高度）> 1，右旋转
        if(leftHeight() - rightHeight() > 1){
            //如果它的左子树的右子树的高度大于它左子树的左子树
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左结点（左子树）进行左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            }else{
                //直接进行右旋转
                rightRotate();
            }
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
}