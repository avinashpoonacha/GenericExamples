package playground;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;


@Getter
public class TreeNode {

    private int value;
    private Set<TreeNode> children;

    TreeNode(int value, TreeNode... children) {
        this.value = value;
        this.children = Sets.newHashSet(children);
    }

}
