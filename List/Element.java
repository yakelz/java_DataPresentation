package List;

public class Element {
    public char[] name = new char[20];
    public char[] address = new char[50];
    public Element (char[] name, char[] address) {
        for (int i = 0; i < name.length ; i++) {
            this.name[i] = name[i];
        }
        for (int i = 0; i < address.length; i++) {
            this.address[i] = address[i];
        }
    }

    public Element (Element input) {
        for (int i = 0; i < input.name.length ; i++) {
            this.name[i] = input.name[i];
        }
        for (int i = 0; i < input.address.length; i++) {
            this.address[i] = input.address[i];
        }
    }

    public Element (String name, String address) {
        this (name.toCharArray(), address.toCharArray());
    }

    public void printElement() {
        for (int j = 0; j < name.length; j++) {
            if ((int)name[j] == 0) {
                break;
            }
            System.out.print(name[j]);
        }
        System.out.print(" | ");
        for (int j = 0; j < address.length; j++) {
            if ((int) address[j] == 0) {
                break;
            }
            System.out.print(address[j]);
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;
        Element x = (Element) o;
        for (int i = 0; i < this.name.length; i++) {
            if (this.name[i] != x.name[i]) {
                return false;
            }
        }
        for (int i = 0; i < this.address.length; i++) {
            if (this.address[i] != x.address[i]) {
                return false;
            }
        }
        return true;
    }

    //for Map locate()
    public boolean compareName(char[] x) {
        for (int i = 0; i < this.name.length; i++) {
            if (this.name[i] != x[i]) {
                return false;
            }
        }
        return true;
    }
}
