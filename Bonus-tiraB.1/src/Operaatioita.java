public class Operaatioita {

    public static Node reverse(Node root) {
        Node edellinen = null;
        Node tamaSolmu = root;
        Node seuraava;
        while (tamaSolmu != null) {
            seuraava = tamaSolmu.getNext();
            tamaSolmu.setNext(edellinen);
            edellinen = tamaSolmu;
            tamaSolmu = seuraava;
        }
        return edellinen;
    }

    public static Node cut(Node root, int i) {
        if (i == 0) {
            return root;
        }
        Node nyt = root;
        for (int j = 0; j < i-1; j++) {
            nyt = nyt.getNext();
        }
        Node edellinen = nyt;
        nyt = nyt.getNext();
        edellinen.setNext(null);
        
        return nyt;
    }

    public static void main(String[] args) {

        Node root = new Node(6,new Node(7, new Node(8, new Node(9, new Node(10)))));
        System.out.println("lista: "+root);

        root = reverse(root);
        System.out.println("reverse(lista): "+root);

        root = new Node(6,new Node(7, new Node(8, new Node(9, new Node(10)))));
        Node root_loppu = Operaatioita.cut(root, 2);
        System.out.println("cut(lista,2): "+root);
        System.out.println("loppu: "+root_loppu);
    }

}
