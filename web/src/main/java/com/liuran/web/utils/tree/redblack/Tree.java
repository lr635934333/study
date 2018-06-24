package com.liuran.web.utils.tree.redblack;

public class Tree {
    private TreeNode root;
    public void insert(TreeNode node){
        if (root == null){
            node.setRad(false);
            root = node;
            return;
        }

        insert(root, node);

        rotate(node);
    }

    private void rotate(TreeNode current){

        int rotateType = current.getRotateType();

        if (rotateType == 0){
            return ;
        }

        if (rotateType == 1){
            rotateType1(current);
        }

        if (rotateType == 2){

        }

        if (rotateType == 3){

        }

    }

    private void rotateType1(TreeNode current){
        current.getParent().setRad(false);
        current.getParent().getBrother().setRad(false);
        current.getParent().getParent().setRad(true);
        current = current.getParent().getParent();
        rotate(current);
    }

    private void rotateType2(TreeNode current){
        if (current.getParent() == null){
            root = current.getRightChild();
            current.setRightChild(null);
            root.setLeftChild(current);
            current = null;
            return;
        } else {
            current = current.getParent();
        }
    }

    private void rotateType3(TreeNode current){

    }

    private void insert(TreeNode parent, TreeNode node){

        node.setRad(true);

        if (parent.compareTo(node) <= 0){
            if (parent.getLeftChild() == null){
                node.setLeft(true);
                parent.setLeftChild(node);
            } else {
                insert(parent.getLeftChild(), node);
            }
        } else {
            if (parent.getRightChild() == null){
                parent.setLeft(false);
                parent.setRightChild(node);
            } else {
                insert(parent.getRightChild(), node);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode<String> node1 = new TreeNode<>("b");
        TreeNode<String> node2 = new TreeNode<>("a");
        TreeNode<String> node3 = new TreeNode<>("c");
        Tree tree = new Tree();
        tree.insert(node1);
        tree.insert(node2);
        tree.insert(node3);

        return;
    }
}
