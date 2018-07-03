package com.liuran.web.utils.question.judgesubtree;

public class Solution {
    public static boolean hasSubTree(TreeNode root, TreeNode subTree){
        if (subTree == null){
            return false;
        }
        if (root == null){
            return false;
        }
        //判断是否子树
        if (isSubTree(root, subTree)){
            return true;
        }

        //递归左子树
        if (hasSubTree(root.left, subTree)){
            return true;
        }
        //递归有子树
        if (hasSubTree(root.right, subTree)){
            return true;
        }

        return false;

    }

    public static boolean isSubTree(TreeNode root, TreeNode subTree){
        if (subTree == null){
            return true;
        }

        if (root == null){
            return false;
        }

        //递归左右子节点
        if (root.val == subTree.val){
            return isSubTree(root.right, subTree.right)
                    && isSubTree(root.left, subTree.left);
        }

        return false;

    }
}
