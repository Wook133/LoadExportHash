package nmu.devilliers;

import javafx.util.Pair;
import nmu.devilliers.Trash.deVillSource;
import nmu.devilliers.Tree.LinkedMultiTreeNode;
import nmu.devilliers.Tree.TreeNode;

import java.util.ArrayList;
import java.util.Collection;

public class TestingClass {
    public static void main(String[] args)
    {

        createTreenode();
        //Node<String> root = createTree();
        //root.printTree(root, " . ");
       // Node<String> r = root.getChildren().get(0);
        //r.printTree(r, " - ");

    }

    public static void devillsourceCompare()
    {
        deVillSource dvS1 =LeafSource();
        deVillSource dvS2 =LeafSource();
        System.out.println(dvS2.compareTo(dvS1));
    }

    public static void merkleTreeTest()
    {
        ArrayList<String> HashFunctions = new ArrayList<String>();
        HashFunctions.add("SHA-1");
        MerkleTree mt = new MerkleTree();

        for (int i = 0; i <= 15; i++)
        {
            mt.addHashes(String.valueOf(i));
        }

        mt.buildTree(HashFunctions.get(0));
        System.out.println("I am a Merkle Root: "+ mt.getMerkleRoot());
        System.out.println("Size: " + mt.lengthList());
        MerkleTree mt2 = new MerkleTree();

        for (int i = 3; i <= 18; i++)
        {
            mt2.addHashes(String.valueOf(i));
        }

        mt2.buildTree(HashFunctions.get(0));
        mt2.setMerkleRoot("c386a00f6aebf8c25a97c8ea8fbd90d1a256c408");
        System.out.println("I am a Merkle Root: "+ mt2.getMerkleRoot());
        System.out.println("Size: " + mt2.lengthList());
        System.out.println("Equal Trees: " + mt.compareTrees(mt2));
    }

    public static deVillSource LeafSource()
    {
        deVillSource dvS1 = new deVillSource("c386a00f6aebf8c25a97c8ea8fbd90d1a256c408", "1530352180638", "535", "Hello", "https://www.youtube.com/watch?v=vq-QyemBGuI");
        System.out.println(dvS1.toString());
        ArrayList<Pair<String, String>> listout = new ArrayList<Pair<String, String>>();

        dvS1.addLink("https://commons.apache.org/proper/commons-jcs/");
        dvS1.addLink("https://www.cell.com/trends/cognitive-sciences/abstract/S1364-6613(04)00243-8?_returnURL=http%3A%2F%2Flinkinghub.elsevier.com%2Fretrieve%2Fpii%2FS1364661304002438%3Fshowall%3Dtrue");
        dvS1.addMeta(new Pair("Title", "Peter"));
        dvS1.addMeta(new Pair("Date", "Today"));

        listout = dvS1.toArraylistPairForSending();
        for (int i = 0; i <= listout.size() - 1; i++)
        {
            System.out.println(listout.get(i).getKey() + " : " + listout.get(i).getValue());
        }
        return dvS1;
    }

    public static deVillSource LeafSource2()
    {
        deVillSource dvS1 = new deVillSource("c386a00f6aebf8c25a97c8ea8fbd90d1a256c408", TimerServer.toStringGetTime(), "535", "Hello", "https://www.youtube.com/watch?v=vq-QyemBGuI");
        System.out.println(dvS1.toString());
        ArrayList<Pair<String, String>> listout = new ArrayList<Pair<String, String>>();

        dvS1.addLink("https://commons.apache.org/proper/commons-jcs/");
        dvS1.addLink("https://www.cell.com/trends/cognitive-sciences/abstract/S1364-6613(04)00243-8?_returnURL=http%3A%2F%2Flinkinghub.elsevier.com%2Fretrieve%2Fpii%2FS1364661304002438%3Fshowall%3Dtrue");
        dvS1.addMeta(new Pair("Title", "Peter"));
        dvS1.addMeta(new Pair("Date", "Today"));

        listout = dvS1.toArraylistPairForSending();
        for (int i = 0; i <= listout.size() - 1; i++)
        {
            System.out.println(listout.get(i).getKey() + " : " + listout.get(i).getValue());
        }
        return dvS1;
    }

    private static Node<String> createTree() {
        Node<String> root = new Node<>("root");

        Node<String> node1 = root.addChild(new Node<String>("node 1"));

        Node<String> node11 = node1.addChild(new Node<String>("node 11"));
        Node<String> node111 = node11.addChild(new Node<String>("node 111"));
        Node<String> node112 = node11.addChild(new Node<String>("node 112"));

        Node<String> node12 = node1.addChild(new Node<String>("node 12"));

        Node<String> node13 = node1.addChild(new Node<String>("node 13"));


        Node<String> node2 = root.addChild(new Node<String>("node 2"));

        Node<String> node21 = node2.addChild(new Node<String>("node 21"));
        Node<String> node211 = node2.addChild(new Node<String>("node 22"));
        return root;
    }

    private static void createTreenode() {
        /*TreeNode<String> n1 = new LinkedMultiTreeNode<>("n1");
        TreeNode<String> n2 = new LinkedMultiTreeNode <>("n2");
        TreeNode<String> n3 = new LinkedMultiTreeNode <>("n3");
        TreeNode<String> n4 = new LinkedMultiTreeNode <>("n4");
        TreeNode<String> n5 = new LinkedMultiTreeNode <>("n5");
        TreeNode<String> n6 = new LinkedMultiTreeNode <>("n6");
        TreeNode<String> n7 = new LinkedMultiTreeNode <>("n7");
        TreeNode<String> n8 = new LinkedMultiTreeNode <>("n8");
        TreeNode<String> n9 = new LinkedMultiTreeNode <>("n9");
        TreeNode<String> n10 = new LinkedMultiTreeNode <>("n10");

        n1.add(n2);
        n1.add(n3);
        n1.add(n4);
        n2.add(n5);
        n2.add(n6);
        n4.add(n7);

        n1.add(n2);
        n1.add(n3);
        n1.add(n4);

        n2.add(n5);
        n2.add(n6);

        n3.add(n7);

        n6.add(n8);
        n6.add(n9);
        n6.add(n10);*/

        TreeNode<String> a = new LinkedMultiTreeNode<>("A");
        TreeNode<String> b = new LinkedMultiTreeNode <>("B");
        TreeNode<String> c = new LinkedMultiTreeNode <>("C");
        TreeNode<String> d = new LinkedMultiTreeNode <>("D");
        TreeNode<String> e = new LinkedMultiTreeNode <>("E");
        TreeNode<String> f = new LinkedMultiTreeNode <>("F");
        TreeNode<String> g = new LinkedMultiTreeNode <>("G");
        TreeNode<String> h = new LinkedMultiTreeNode <>("H");
        TreeNode<String> i = new LinkedMultiTreeNode <>("I");
        TreeNode<String> j = new LinkedMultiTreeNode <>("J");
        TreeNode<String> k = new LinkedMultiTreeNode <>("K");

        f.add(b);
        f.add(g);
        f.add(k);

        b.add(a);
        b.add(d);

        g.add(i);

        d.add(c);
        d.add(e);

        i.add(h);

        c.add(j);




        System.out.println(((LinkedMultiTreeNode<String>) f).toString());

        System.out.println("Height: " + f.height());
        System.out.println("Level: " + j.level());
        TreeNode<String> furthest = ((LinkedMultiTreeNode<String>) f).findFurthestLeaf();
        System.out.println("Furthest Node: " + furthest.data());

        TreeNode<String> ca = c.commonAncestor(g);
        System.out.println("Common Node: " + ca.data());

        ca = g.commonAncestor(c);
        System.out.println("Common Node: " + ca.data());
        Collection<? extends TreeNode<String>> path = j.pathBetweenNodes(b);
        for (TreeNode<String> tns : path)
        {
            System.out.print(tns.data().toString() + " -> ");
            //Path: f -> b -> d -> e
        }
        System.out.println();
        path = k.pathBetweenNodes(j);
        for (TreeNode<String> tns : path)
        {
            System.out.print(tns.data().toString() + " -> ");
            //Path: f -> b -> d -> e
        }
        System.out.println();
        path = j.pathBetweenNodes(k);
        for (TreeNode<String> tns : path)
        {
            System.out.print(tns.data().toString() + " -> ");
            //Path: f -> b -> d -> e
        }




        /*Collection<? extends TreeNode<String>> preOrderedCollection = f.preOrdered();
        Collection<? extends TreeNode<String>> postOrderedCollection = f.postOrdered();
        //Collection<? extends TreeNode<String>> inOrderedCollection = f.inOrdered();

        for (TreeNode<String> tns : preOrderedCollection)
        {
            System.out.print(tns.data().toString() + " . ");
            //Pre-order: F, B, A, D, C, E, G, I, H.
        }

        System.out.println();

        for (TreeNode<String> tns : postOrderedCollection)
        {
            System.out.print(tns.data().toString() + " - ");
            //Post-order: A, C, E, D, B, H, I, G, F.
        }

        System.out.println();


// Iterating over the tree elements using foreach
        /*for (TreeNode<String> node : f) {
            System.out.println(node.data()); // any other action goes here
        }*/

// Iterating over the tree elements using Iterator
       /* Iterator<TreeNode<String>> iterator = f.iterator();
        while (iterator.hasNext()) {
            TreeNode<String> node = iterator.next();
        }*/










    }

    /*public static void preorder(LinkedMultiTreeNode node)
    {
        if (node.isLeaf())
        {
            return;
        }
        System.out.println(node.data().toString() + " ");
        preorder(node.leftMostNode);
        preorder(node.rightSiblingNode);
    }*/



}
