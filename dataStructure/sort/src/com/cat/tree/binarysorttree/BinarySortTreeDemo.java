package com.cat.tree.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加结点到二叉排序树
        for(int i = 0;i < arr.length;i++){
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        //删除叶子结点
//        binarySortTree.delNode(12);
//        binarySortTree.delNode(12);
//        binarySortTree.delNode(12);
//        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        System.out.println("删除后的二叉排序树~~~");
        binarySortTree.infixOrder();
    }
}
//创建二叉排序树
class BinarySortTree{
    private Node root;
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