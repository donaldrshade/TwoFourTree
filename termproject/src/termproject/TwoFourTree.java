package termproject;

/**
 * Title: Term Project 2-4 Trees Description: This project is designed to build
 * a 2-4 tree data structure.
 *
 * @author Daniel Garcia
 * @author Donald Shade
 * @version 1.0 File: TwoFourTree.java Created: NOV 2017 Summary of
 * Modifications: Previous - D.G - created class and main 09 DEC 2017 – D&D –
 * added overided functions and implemented find with a search and FFGTE 11 DEC
 * 2017 - D&D - implemented the insert function debugged and works. Description:
 * This class instantiates a 2-4 tree.
 */
public class TwoFourTree
        implements Dictionary {

    private Comparator treeComp;
    private int size = 0;
    private TFNode treeRoot = null;

    public TwoFourTree(Comparator comp) {
        treeComp = comp;
    }

    private TFNode root() {
        return treeRoot;
    }

    private void setRoot(TFNode root) {
        treeRoot = root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Searches dictionary to determine if key is present
     *
     * @param key to be searched for
     * @return object corresponding to key; null if not found
     */
    @Override
    public Object findElement(Object key) throws TwoFourTreeException {
        if (!treeComp.isComparable(key)) {
            throw new InvalidKeyException("Data is not comparable.");
        }
        Object result = searchElement(key, treeRoot);
        TFNode resultNode = (TFNode) result;
        int ind = FFGTE(key, resultNode);
        if (treeComp.isEqual((resultNode.getItem(ind)).key(), key)) {
            return resultNode.getItem(ind);
        } else {
            throw new ElementNotFoundException("Element not found.");
        }
    }

    /**
     * Inserts provided element into the Dictionary
     *
     * @param key of object to be inserted
     * @param element to be inserted
     */
    @Override
    public void insertElement(Object key, Object element) {
        size++;
        TFNode node;
        if (treeRoot == null) {
            treeRoot = new TFNode();
            node = treeRoot;
        } else {
            node = (TFNode) searchElement(key, treeRoot);
        }
        //TODO: Search
        Item myItem = new Item(key, element);
        //TODO: Add

        //If node has at least one item in it, use FFGTE to find insert location
        //Otherwise, insert into location 0
        int insertLocation = 0;
        if (node.getNumItems() != 0) {
            insertLocation = FFGTE(key, node);
        }
        node.insertItem(insertLocation, myItem);

        //Check for and resolve overflow
        while (node.getNumItems() == 4) {
            Item pushItem = node.getItem(2);
            TFNode parent = node.getParent();
            if (parent == null) {
                parent = new TFNode();
                setRoot(parent);
                parent.setChild(0, node);
            }
            int ind = FFGTE(pushItem.key(), parent);
            parent.insertItem(ind, pushItem);

            //Create new node and attach it to parent's last child spot
            TFNode newNode = new TFNode();
            newNode.addItem(0, node.getItem(3));
            parent.setChild(ind + 1, newNode);
            TFNode child3 = node.getChild(3);
            TFNode child4 = node.getChild(4);
            newNode.setChild(0, child3);
            newNode.setChild(1, child4);
            if (child3 != null) {
                child3.setParent(newNode);
                child4.setParent(newNode);
            }

            //Fix remains
            node.setChild(4, null);
            node.setChild(3, null);
            node.deleteItem(3);
            node.deleteItem(2);

            node.setParent(parent);
            newNode.setParent(parent);

            node = parent;
        }
    }

    /**
     * Searches dictionary to determine if key is present, then removes and
     * returns corresponding object
     *
     * @param key of data to be removed
     * @return object corresponding to key
     * @exception ElementNotFoundException if the key is not in dictionary
     */
    @Override
    public Object removeElement(Object key) throws ElementNotFoundException {
        if (!treeComp.isComparable(key)) {
            throw new InvalidKeyException("Data is not comparable.");
        }
        Object result = searchElement(key, treeRoot);
        TFNode node = (TFNode) result;
        int ind = FFGTE(key, node);
        if (treeComp.isEqual((node.getItem(ind)).key(), key)) {
            Item myItem = node.getItem(ind);

            TFNode inOrder = node.getChild(ind + 1);
            if (inOrder == null) {
                node.removeItem(ind);
                if (node.getNumItems() == 0) {
                    fixUnderflow(node);
                }
            } else {
                while (inOrder.getChild(0) != null) {
                    inOrder = inOrder.getChild(0);
                }
                node.addItem(ind, inOrder.removeItem(0));
                if (inOrder.getNumItems() == 0) {
                    fixUnderflow(inOrder);
                }
            }

            size--;
            return myItem.key();

        } else {
            throw new ElementNotFoundException("Element not found.");
        }

    }

    public static void main(String[] args) {
        Comparator myComp = new IntegerComparator();
        TwoFourTree myTree = new TwoFourTree(myComp);
        /*
        Integer myInt1 = new Integer(47);
        myTree.insertElement(myInt1, myInt1);
        Integer myInt2 = new Integer(83);
        myTree.insertElement(myInt2, myInt2);
        Integer myInt3 = new Integer(22);
        myTree.insertElement(myInt3, myInt3);
        
        Integer myInt4 = new Integer(16);
        myTree.insertElement(myInt4, myInt4);

        Integer myInt5 = new Integer(49);
        myTree.insertElement(myInt5, myInt5);
        
        Integer myInt6 = new Integer(100);
        myTree.insertElement(myInt6, myInt6);

        Integer myInt7 = new Integer(38);
        myTree.insertElement(myInt7, myInt7);

        Integer myInt8 = new Integer(3);
        myTree.insertElement(myInt8, myInt8);
        
        Integer myInt9 = new Integer(53);
        myTree.insertElement(myInt9, myInt9);

        Integer myInt10 = new Integer(66);
        myTree.insertElement(myInt10, myInt10);

        Integer myInt11 = new Integer(19);
        myTree.insertElement(myInt11, myInt11);

        Integer myInt12 = new Integer(23);
        myTree.insertElement(myInt12, myInt12);

        Integer myInt13 = new Integer(24);
        myTree.insertElement(myInt13, myInt13);

        Integer myInt14 = new Integer(88);
        myTree.insertElement(myInt14, myInt14);

        Integer myInt15 = new Integer(1);
        myTree.insertElement(myInt15, myInt15);

        Integer myInt16 = new Integer(97);
        myTree.insertElement(myInt16, myInt16);

        Integer myInt17 = new Integer(94);
        myTree.insertElement(myInt17, myInt17);

        Integer myInt18 = new Integer(35);
        myTree.insertElement(myInt18, myInt18);

        Integer myInt19 = new Integer(51);
        myTree.insertElement(myInt19, myInt19);
        myTree.printAllElements();
        
        System.out.println("done");
         */
        myTree = new TwoFourTree(myComp);
        final int TEST_SIZE = 50;

        for (int i = 0; i < TEST_SIZE; i++) {
            myTree.insertElement(new Integer(i), new Integer(i));
            //myTree.printAllElements();
            myTree.checkTree();
        }
        myTree.printAllElements();
        System.out.println("removing");
        for (int i = 0; i < TEST_SIZE; i++) {
            /*if(i == 4){
                myTree.printAllElements();
            }*/
            int out = (Integer) myTree.removeElement(new Integer(i));
            myTree.checkTree();
            System.out.println(out);
            //if (i == 2) {
                myTree.printAllElements();
            //}

            if (out != i) {
                throw new TwoFourTreeException("main: wrong element removed");
            }
            if (i > TEST_SIZE - 15) {
                myTree.printAllElements();
            }
        }
        System.out.println("done");
    }

    //print functions
    //<editor-fold>
    public void printAllElements() {
        int indent = 0;
        if (root() == null) {
            System.out.println("The tree is empty");
        } else {
            printTree(root(), indent);
        }
    }

    public void printTree(TFNode start, int indent) {
        if (start == null) {
            return;
        }
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        printTFNode(start);
        indent += 4;
        int numChildren = start.getNumItems() + 1;
        for (int i = 0; i < numChildren; i++) {
            printTree(start.getChild(i), indent);
        }
    }

    public void printTFNode(TFNode node) {
        int numItems = node.getNumItems();
        for (int i = 0; i < numItems; i++) {
            System.out.print(((Item) node.getItem(i)).element() + " ");
        }
        System.out.println();
    }//</editor-fold>

    //checking functions
    //<editor-fold>
    // checks if tree is properly hooked up, i.e., children point to parents
    public void checkTree() {
        checkTreeFromNode(treeRoot);
    }

    private void checkTreeFromNode(TFNode start) {
        if (start == null) {
            return;
        }

        if (start.getParent() != null) {
            TFNode parent = start.getParent();
            int childIndex = 0;
            for (childIndex = 0; childIndex <= parent.getNumItems(); childIndex++) {
                if (parent.getChild(childIndex) == start) {
                    break;
                }
            }
            // if child wasn't found, print problem
            if (childIndex > parent.getNumItems()) {
                System.out.println("Child to parent confusion");
                printTFNode(start);
            }
        }

        if (start.getChild(0) != null) {
            for (int childIndex = 0; childIndex <= start.getNumItems(); childIndex++) {
                if (start.getChild(childIndex) == null) {
                    System.out.println("Mixed null and non-null children");
                    printTFNode(start);
                } else {
                    if (start.getChild(childIndex).getParent() != start) {
                        System.out.println("Parent to child confusion");
                        printTFNode(start);
                    }
                    for (int i = childIndex - 1; i >= 0; i--) {
                        if (start.getChild(i) == start.getChild(childIndex)) {
                            System.out.println("Duplicate children of node");
                            printTFNode(start);
                        }
                    }
                }

            }
        }

        int numChildren = start.getNumItems() + 1;
        for (int childIndex = 0; childIndex < numChildren; childIndex++) {
            checkTreeFromNode(start.getChild(childIndex));
        }

    }
    //</editor-fold>

    private Object searchElement(Object key, TFNode node) {
        if (node != null) {
            //Get the FFGTE of the current key
            int location = FFGTE(key, node);
            if (location < node.getNumItems()) {
                Item nodesItem = node.getItem(location);

                //If item was found, returns the correct TFNode in Object form
                if (treeComp.isEqual(nodesItem.key(), key)) {
                    return node;

                    //If item wasn't found, search its child
                } else {
                    if (node.getChild(location) == null) {
                        return node;
                    }
                    return searchElement(key, node.getChild(location));
                }
                //If we hit the end of the array, search the last child
            } else {
                if (node.getChild(location) == null) {
                    return node;
                }
                return searchElement(key, node.getChild(node.getNumItems()));
            }
        } else {
            return node;
        }

    }

    private int FFGTE(Object key, TFNode node) {
        for (int i = 0; i < node.getNumItems(); i++) {
            Item myItem = node.getItem(i);
            if (treeComp.isGreaterThanOrEqualTo(myItem.key(), key)) {
                return i;
            }
        }
        return node.getNumItems();
    }

    private void fixUnderflow(TFNode node) {
        if(node == treeRoot){
            treeRoot = node.getChild(0);
            return;
        }
        TFNode parent = node.getParent();
        int myNumber = WCIT(node);
        TFNode leftSib = null;
        TFNode rightSib = null;
        if (myNumber > 0) {
            leftSib = parent.getChild(myNumber - 1);
        }
        if (myNumber < 2) {
            rightSib = parent.getChild(myNumber + 1);
        }

        //LXFER
        if (canLeftXfer(myNumber, leftSib)) {
                node.insertItem(node.getNumItems(), parent.deleteItem(myNumber));
                node.setChild(0, leftSib.getChild(leftSib.getNumItems()));
                if (node.getChild(0) != null) {
                    node.getChild(0).setParent(node);;
                }
                parent.addItem(myNumber, leftSib.removeItem(leftSib.getNumItems()));
        } //RXFER
        else if (canRightXfer(myNumber, rightSib)) {
                node.addItem(0, parent.deleteItem(myNumber));
                node.setChild(1, rightSib.getChild(0));
                if (node.getChild(1) != null) {
                    node.getChild(1).setParent(node);
                }
                parent.addItem(myNumber, rightSib.removeItem(0));
        } //LFUSION
        else if (leftSib != null) {
            leftSib.insertItem(leftSib.getNumItems(), parent.removeItem(myNumber));
            leftSib.setChild(leftSib.getNumItems(), node.getChild(0));
            if (node.getChild(leftSib.getNumItems()) != null) {
                leftSib.getChild(leftSib.getNumItems()).setParent(rightSib);
            }
            
            if(parent.getNumItems() == 0){
                fixUnderflow(parent);
            }
        } //RFUSION
        else if (rightSib != null) {
            rightSib.insertItem(0, parent.removeItem(myNumber));
            rightSib.setChild(0, node.getChild(0));
            if (node.getChild(0) != null) {
                rightSib.getChild(0).setParent(rightSib);
            }
            
            if (parent.getNumItems() == 0) {
                fixUnderflow(parent);
            }
        }
    }

    private int WCIT(TFNode node) {
        TFNode parent = node.getParent();
        for (int i = 0; i < 3; i++) {
            if (parent.getChild(i) == node) {
                return i;
            }
        }
        throw new TwoFourTreeException();
    }

    private boolean canLeftXfer(int myNumber, TFNode sib) {
        if (myNumber >= 1 && sib != null) {
            if (sib.getNumItems() > 1) {
                return true;
            }
        }
        return false;
    }

    private boolean canRightXfer(int myNumber, TFNode sib) {
        if (myNumber <= 2 && sib != null) {
            if (sib.getNumItems() > 1) {
                return true;
            }
        }
        return false;
    }
}
