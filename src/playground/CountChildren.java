package playground;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


public class CountChildren extends RecursiveTask<Integer> {

    private final TreeNode node;

    public CountChildren(TreeNode node) {
        this.node = node;
    }


    @Override
    protected Integer compute() {
        return node.getValue() + node.getChildren().stream()
                .map(childNode -> new CountChildren(childNode).fork())
                .mapToInt(ForkJoinTask::join)
                .sum();
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(5,
                new TreeNode(3), new TreeNode(2,
                        new TreeNode(2), new TreeNode(8)));

        ForkJoinPool fork = ForkJoinPool.commonPool();
        int sum = fork.invoke(new CountChildren(tree));

        System.out.println(sum);
    }
}
