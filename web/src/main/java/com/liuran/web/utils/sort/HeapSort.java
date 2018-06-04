package com.liuran.web.utils.sort;
/**
 * 堆排序
 * 1、堆编码规则(逻辑上):从上到下,从左到右,按照数组顺序建立一个完全二叉树
 * 2、大顶堆定义:Ai > A(i+1) 且 Ai > A(i+2),即堆的父节点大于两个子节点(二叉树的部分性质)
 * 3、第n个非叶子节点计算公式:node = array.length/2 - n (非叶子节点前面的节点都是非叶子节点);
 * 4、调整规程:
 *    A、建立初始堆,从最后一个非叶子节点开始,以该节点开始作为子树,调整子树为大顶堆(或小顶堆)。循环array.length/2 - 1次,直到结束。
 *    B、在初始堆的的基础之上进行调整
 *      B1、堆顶元素和堆末尾元素进行交换(找到一个最大的元素,并放到该放的位置)
 *      B2、调整以0节点开始调整堆(不需要调整array.length/2 - 1次,因为B1步骤只有一个元素破坏了堆结构)
 *      B3、重复B1、B2过程直到结束(每次循排序好的数不需要加入堆调整过程中,因此随着循环的进行,堆在不断的减小)
 *    C、堆调整算法
 *      算法:找到parent节点的叶子节点,并根据2中堆的定义进行交换,依次循环直到结束
 *      结束条件:当前节点满足堆的定义 或 循环到堆得底部
 * */
public class HeapSort extends AbstractSort{

    public HeapSort(){
        super();
    }

    public HeapSort(boolean invert){
        super(invert);
    }

    @Override
    public Comparable[] sort(Comparable[] array) {
        /*
        * i = array.length / 2 - 1,找到最后一个非叶子节点,并从该非叶子节点起遍历所有的非叶子节点,并进行堆调整
        * */
        for (int i = array.length / 2 -1; i >= 0; i --){
            //从当前非叶子节点开始,从上至下调整堆
            adjustHeap(array, i, array.length);
        }

        for (int i = 1; i < array.length ;i ++){
            swap(array, 0, array.length - i);
            adjustHeap(array, 0, array.length - i);
        }
        return array;
    }

    /**
     * @param array 待排序数组
     * @param node 开始调整的非叶子节点
     * @param length 数组长度(每次调整堆,数组的大小可能不一样)
     * */
    public void adjustHeap(Comparable[] array, int node, int length){
        //父节点
        int parent = node;
        //parent较大的子节点,每次循环默认子节点是左子节点(childMax < length保证左子节点一定存在,否则循环自动结束)
        int childMax;
        for (childMax = parent * 2 + 1 ;childMax < length; childMax = parent * 2 +1){
            //childMax parent较大的子节点,
            //判断是否有右子节点,如果存在右子节点,且右子节点大于左子节点，把childMax指向右子节点,否则childMax仍然指向左子节点
            if (childMax + 1 < length){
                Comparable leftChild = array[childMax];
                Comparable rightChild  = array[childMax + 1];
                if (fistGtSecond(rightChild, leftChild)){
                    childMax ++;
                }
            }

            //判断最大的子节点(k可能是左子节点也可能是右子节点)是否需要和当前节点进行交换
            if (fistGtSecond(array[childMax], parent)){
                swap(array, parent, childMax);
                //更新当前parent
                parent = childMax;
            } else {
                //循环结束
                break;
            }
        }
    }
}
