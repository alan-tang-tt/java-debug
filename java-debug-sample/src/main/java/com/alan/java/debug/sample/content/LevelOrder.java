package com.alan.java.debug.sample.content;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 *       3
 *     /   \
 *   9      20
 *         /  \
 *       15    7
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) return resultList;

        // 迭代法
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> result = new ArrayList<>();
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                result.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            resultList.add(result);
        }

        return resultList;
    }

    public static void main(String[] args) {
        new LevelOrder().levelOrder(
                new TreeNode(3,
                        new TreeNode(9), new TreeNode(20,
                            new TreeNode(15), new TreeNode(7))));
    }


}
