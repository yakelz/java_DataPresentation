package Set.CircularLinkedSet;

public class Set {
    public class Node {
        public int number;
        public Node next;
        public Node(int number, Node next) {
            this.number = number;
            this.next = next;
        }
    }

    private Node head;

    public Set(){
        head = null;
    }

    // Возвращает множество в котором есть все неповторяющиеся элементы
    public Set Union (Set input) {
        return null;
    }

    //Возвращает множество общих элементов
    public Set Intersection(Set input){
        return null;
    }

    //Возвращает множество с элементами которых нет в исходном множестве
    public Set Difference (Set input) {
        return null;
    }

    // Возвращает множество с которым есть все неповторяющиеся элементы
    // Только если множества не пересекаются
    public Set Merge(Set input){
        return null;
    }

    // Возвращает true - если value есть в множестве
    // false - если его нет
    public boolean Member(int value){
        return false;
    }

    // Вставка значения в множество
    public void Insert(int value){
    }

    // Удаление элемента из множества
    public void Delete(int value){
    }

    // Присваивание нового множества
    public void Assign(Set input){

    }

    // Возвращает минимальное значение в множестве
    public int Min(){
        return 0;
    }

    // Возвращает максимальное значение в множестве
    public int Max(){
        return 0;
    }

    // Возвращает true - если множества одинаковые.
    public boolean Equal(){
        return false;
    }

    // Возвращает множество, где найдет X
    // A и B - непересекающиеся множества
    // Если нет такого элемента, возвращаем пустое множество.
    public Set Find(Set input, int x){
        return null;
    }

    // Очистить множество
    public void MakeNull(){
    }

    public void Print(){}
}
