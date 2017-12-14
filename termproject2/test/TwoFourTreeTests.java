/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import termproject.Comparator;
import termproject.IntegerComparator;
import termproject.TwoFourTree;
import termproject.TwoFourTreeException;

/**
 *
 * @author donaldrshade
 */
public class TwoFourTreeTests {

    @Test
    public void testDuplicates() {
        System.out.println("testDuplicates");
        Comparator myComp = new IntegerComparator();
        TwoFourTree myTree = new TwoFourTree(myComp);
        final int TEST_SIZE = 10;
        for (int i = 0; i < TEST_SIZE; i++) {
            myTree.insertElement(new Integer(i), new Integer(i));
            myTree.checkTree();
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            myTree.insertElement(new Integer(i), new Integer(i));
            myTree.checkTree();
        }
        myTree.printAllElements();
        for (int i = 0; i < TEST_SIZE; i++) {
            int out = (Integer) myTree.removeElement(new Integer(i));
            if (out != i) {
                fail();
            }
            myTree.checkTree();
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            int out = (Integer) myTree.removeElement(new Integer(i));
            if (out != i) {
                fail();
            }
            myTree.checkTree();
        }
    }

    /*@Test
    public void testManyNumbers() {
        System.out.println("testManyNumbers");
        Comparator myComp = new IntegerComparator();
        TwoFourTree myTree = new TwoFourTree(myComp);
        final int TEST_SIZE = 10000;
        for (int i = 0; i < TEST_SIZE; i++) {
            myTree.insertElement(new Integer(i), new Integer(i));
            myTree.checkTree();
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            int out = (Integer) myTree.removeElement(new Integer(i));
            if (out != i) {
                fail();
            }
            myTree.checkTree();
        }
    }*/

//    @Test
//    public void testManyNumbersWithDuplicates() {
//        System.out.println("testManyNumbersWithDuplicates");
//        Comparator myComp = new IntegerComparator();
//        TwoFourTree myTree = new TwoFourTree(myComp);
//        final int TEST_SIZE = 50;
//        for (int i = 0; i < TEST_SIZE; i++) {
//            myTree.insertElement(new Integer(i), new Integer(i));
//            myTree.checkTree();
//        }
//        for (int i = 0; i < TEST_SIZE; i++) {
//            myTree.insertElement(new Integer(i), new Integer(i));
//            myTree.checkTree();
//        }
//        for (int i = 0; i < TEST_SIZE; i++) {
//            int out = (Integer) myTree.removeElement(new Integer(i));
//            if (out != i) {
//                fail();
//            }
//            myTree.checkTree();
//        }
//        for (int i = 0; i < TEST_SIZE; i++) {
//            int out = (Integer) myTree.removeElement(new Integer(i));
//            if (out != i) {
//                fail();
//            }
//            myTree.checkTree();
//        }
//    }

    /*@Test
    public void testInsertFewNumbersWithDuplicates() {
        System.out.println("testInsertFewNumbersWithDuplicates");
        Comparator myComp = new IntegerComparator();
        TwoFourTree myTree = new TwoFourTree(myComp);
        
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

        Integer myInt8 = new Integer(22);
        myTree.insertElement(myInt8, myInt8);

        Integer myInt9 = new Integer(53);
        myTree.insertElement(myInt9, myInt9);

        Integer myInt10 = new Integer(66);
        myTree.insertElement(myInt10, myInt10);

        Integer myInt11 = new Integer(19);
        myTree.insertElement(myInt11, myInt11);

        Integer myInt12 = new Integer(83);
        myTree.insertElement(myInt12, myInt12);

        Integer myInt13 = new Integer(24);
        myTree.insertElement(myInt13, myInt13);

        Integer myInt14 = new Integer(88);
        myTree.insertElement(myInt14, myInt14);

        Integer myInt15 = new Integer(1);
        myTree.insertElement(myInt15, myInt15);

        Integer myInt16 = new Integer(88);
        myTree.insertElement(myInt16, myInt16);

        Integer myInt17 = new Integer(94);
        myTree.insertElement(myInt17, myInt17);

        Integer myInt18 = new Integer(35);
        myTree.insertElement(myInt18, myInt18);

        Integer myInt19 = new Integer(53);
        myTree.insertElement(myInt19, myInt19);
        //myTree.printAllElements();
        myTree.checkTree();
    }*/
}
