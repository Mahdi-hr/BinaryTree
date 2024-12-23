import java.util.*;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {

    public static void cls (){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    public static void printTreeGraphically(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int maxLevel = getMaxLevel(root);

        for (int level = 0; level < maxLevel; level++) {
            int spaces = (int) Math.pow(2, maxLevel - level - 1) - 1;
            int nodeCount = (int) Math.pow(2, level);

            printSpaces(spaces);

            for (int i = 0; i < nodeCount; i++) {
                TreeNode node = queue.poll();

                if (node != null) {
                    System.out.print(node.value);
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    System.out.print(" ");
                    queue.add(null);
                    queue.add(null);
                }

                printSpaces(spaces * 2 + 1);
            }
            System.out.println();
        }
    }

    private static int getMaxLevel(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getMaxLevel(node.left), getMaxLevel(node.right));
    }

    private static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }


    public static int countNodesWithXAndDegree2(TreeNode root, int x) {

        if (root == null) return 0;

        int count = 0;
        if (root.value == x && root.left != null && root.right != null) {
            count = 1;
        }

        count += countNodesWithXAndDegree2(root.left, x);
        count += countNodesWithXAndDegree2(root.right, x);

        return count;
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root1 == null) return root2;
        if (root2 == null) return root1;

        TreeNode newNode = new TreeNode(root1.value + root2.value);
        newNode.left = mergeTrees(root1.left, root2.left);
        newNode.right = mergeTrees(root1.right, root2.right);

        return newNode;
    }

    public static TreeNode buildTree(Scanner scanner) {

        int value = getValidInput(scanner, "Enter node value (-1 for null): ");
        if (value == -1) return null;

        TreeNode node = new TreeNode(value);
        System.out.println("Enter left child of " + value);
        node.left = buildTree(scanner);

        System.out.println("Enter right child of " + value);
        node.right = buildTree(scanner);

        return node;
    }

    private static int getValidInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value or -1 for null.");
                scanner.next();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Build first tree:");
        TreeNode root1 = buildTree(scanner);
        System.out.println("\n\n\n");
        System.out.println("Build second tree:");
        TreeNode root2 = buildTree(scanner);

        cls();

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Count nodes with value X and degree 2 in the first tree");
            System.out.println("2. Merge the two trees and print the result");
            System.out.println("3. Print both trees graphically");
            System.out.println("4. Build tree again");
            System.out.println("5. Exit");

            int choice = getValidInput(scanner, "Enter your choice: ");
            switch (choice) {
                case 1:
                    cls();
                    int x = getValidInput(scanner, "Enter value X: ");
                    int count = countNodesWithXAndDegree2(root1, x);
                    System.out.println("Number of nodes with value " + x + " and degree 2: " + count);
                    break;

                case 2:
                    cls();
                    TreeNode mergedTree = mergeTrees(root1, root2);
                    System.out.println("Merged Tree (Graphical):");
                    printTreeGraphically(mergedTree);
                    break;

                case 3:
                    cls();
                    System.out.println("First Tree (Graphical):");
                    printTreeGraphically(root1);
                    System.out.println("Second Tree (Graphical):");
                    printTreeGraphically(root2);
                    break;

                case 4:
                    cls();
                    System.out.println("1. Rebuild tree 1");
                    System.out.println("2. Rebuild tree 2");
                    int c = getValidInput(scanner, "Enter your choice: ");
                    switch (c) {
                        case 1:
                            root1 = buildTree(scanner); // ساخت دوباره درخت اول
                            System.out.println("Tree 1 has been rebuilt.");
                            break;

                        case 2:
                            root2 = buildTree(scanner); // ساخت دوباره درخت دوم
                            System.out.println("Tree 2 has been rebuilt.");
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    break;

                case 5:
                    cls();
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
