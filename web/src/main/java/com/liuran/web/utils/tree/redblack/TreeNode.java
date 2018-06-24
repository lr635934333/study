package com.liuran.web.utils.tree.redblack;

public class TreeNode<T extends Comparable<? super T>> extends Node{
    public TreeNode(Comparable comparable) {
        super(comparable);
    }

    private TreeNode parent;
    private TreeNode leftChild;
    private TreeNode rightChild;
    private boolean isRad = true;
    private boolean isLeft = false;

    public int getRotateType(){
        if (parent == null){
            if (leftChild == null){
                return 2;
            } else {
                return 3;
            }
        }

        //不需要旋转
        if (!parent.isRad){
            return 0;
        } else {
            if (parent.getBrother() == null
                    || parent.getBrother().isRad){
                return 1;
            } else {
                //左旋转
                if (!isLeft){
                    return 2;
                } else {
                    return 3;
                }
            }
        }

    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public boolean isRoot(){
        return parent == null;
    }

    public TreeNode getBrother(){
        if (parent != null){
            if (isLeft){
                return parent.getRightChild();
            } else {
                return parent.getLeftChild();
            }
        }

        return null;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isRad() {
        return isRad;
    }

    public void setRad(boolean rad) {
        isRad = rad;
    }
}
