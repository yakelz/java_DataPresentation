package Set.ArraySet;

// Абстрактный тип данных Множество реализуемый на массиве,
// где каждый бит в ячейке массива указывает на наличие числа во множестве
public class ArraySet {
    int x; // Левая граница
    int y; // Права граница
    int[] array; // Массив

    public ArraySet() {

    }

    // Класс позиции
    private class Position{
        int index; // Адрес в массиве
        int bit; // Номер бита
        Position (int index, int bit) {
            this.index = index;
            this.bit = bit;
        }
    }

    //default конструктор
    public ArraySet(int x, int y) {
        this.x = x;
        this.y = y;
        // Если y < x, тогда ошибка
        if (y < x) {
            throw new RuntimeException("ArraySet(): не правильно указаны границы");
        }

        // Инициализация массива
        // Рассматриваем разные случаи потому что массив выходит не симметричным (допустим: -32 до -1 и от 0 до 31 => не симметричный)

        // Если x >=0 (положительный диапазон)
            // тогда размер массива = y/32 - x/32 +1

                // почему не (y-x)/32 + 1, потому что целочисленное деление
                // допустим от 31 до 32 (нужны две ячейки массива)
                //от 0 до 31 и от 32 до 63
                // по первой формуле = (32-31)/32 + 1 = 1 ячейка, а нужно две


        //Если y < 0 (отрицательный диапазон)
            // тогда размер массива = (y+1)/32 - (x+1)/32 + 1

                //допустим -33 до -32 (нужны две ячейки массива)
                // От -64 до -33 и от -32 до -1

                // к каждой границе прибавляем +1 потому что они отрицательные (чтобы могли /32 и %32)
                // Если будем пользоваться первой формулой
                // -32/32 - (-33/32) + 1 = -1 - (-1) + 1 = 1
                // а нам нужно две ячейки

                 // в конце +1 потому что мы тут считаем длину, и она не может быть нулевой

        //Если x < 0 && y >= 0 (двухсторонний диапазон)
            // тогда размер массива = y/32 - (x+1)/32 + 2
            // у двухстороннего уже минимум две ячейки нужно
            // знаем, что x (начало) у нас отрицательный, поэтому делаем + 1

            // почему +2:
            // допустим от -32 до 31 нужны две ячейки -32 до -1 и от 0 до 31
            // по формуле 31/32 - (-32+1)/32 + 1 = 0 - 0 + 1 = 1
            // а нужно минимум две ячейки, поэтому добавляем еще +1 (=> в итоговой формуле +2)

        //Все это вычисляется только в начале, при инициализации массива
        //потом при Union(), и в других методах не нужно будет вычислять смещение битов

        array = new int[y/32 - x/32 +1];
        System.out.println(x + " " + y + " " + array.length);
    }


    // Нахождение позиции элемента в массиве
    // возвращает номер ячейки массива и бит
    private Position search(int value) {
        //если значение отрицательное
            // значит граница точно начинается с отрицательных
            // Индекс массива = (value + 1) / 32 - (x + 1) / 32
            // Отталкиваемся от начала до значения, эта разница равна той ячейке массива, где это число находится
            // +1 т.к отрицательные
            // в конце не делаем + 1 т.к позиция может быть отрицательной

            // Номер бита = 31 - ( |value + 1| % 32 )
            //            = 31 - (-(value + 1) % 32 )
                // добавили +1 т.к отрицательное значение

                    // диапазон от -32 до -1
                    // допустим value = -31;
                    // (|-31| % 32 = 31 (как будто она на последней ячейке),
                    // но на самом деле она на 1 бите
                    // поэтому +1 чтобы было |-30| % 32 = 30)
                    // Но и не на 30 бите, а на 1, поэтому инвертируем => 31 - 30 = 1

                    // допустим value = -1;
                    // 31 - (-(-1+1)%32) = 31 - 0 = 31 бит

                //Берем по модулю т.к мы не можем взять остаток (%32) от отрицательного числа

        //если значение положительное и левая граница отрицательная (значит у нас диапазон двухсторонний)
            // Индекс массива = value / 32 - (x + 1) / 32 + 1
            // +1 а не +2, т.к индекс с нуля начинается

            // Номер бита = value % 32

        //если значение положительное и левая граница положительная (значит диапазон положительный)
            // значит положительные числа, начинаются с 0 и можно просто /32
            // Индекс массива = value / 32 - x / 32

            // Номер бита = value % 32
        return null;
    }



    // Возвращает новое множество в котором есть все неповторяющиеся элементы
    public ArraySet Union (ArraySet input) {
        // Если input это тоже самое множество - возвращаем копию исходного
        if (input == this) {
            return this;
        }
        // Вычисляем min от Х, и max от У
        int x = this.x > input.x ? input.x : this.x;
        int y = this.y > input.y ? this.y : input.y;
        // Создаем новый массив c новыми границами
        ArraySet union = new ArraySet(x,y);
        // Находим относительное смещение первого массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        // union.search(this.x);

        // И Копируем ЯЧЕЙКИ первого массива в новый массив

        // Находим относительное смещение второго массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        // union.search(input.x)

        // новый массив побитово умножаем (&) на элементы второго массива (учитывая смещение)
        //возвращаем новый массив
        return union;
    }

    //Проверка на пересечения границ
    // если конец 1 > старт 2 ИЛИ старт 1 < конец 2
    private boolean isIntersect(ArraySet input){
        return y > input.x || x < input.y;
    }

    //Возвращает множество общих элементов
    public ArraySet Intersection(ArraySet input){
        // Если input это тоже самое множество - возвращаем копию исходного
        if (input == this) {
            return this;
        }
        // Проверяем пересечения границ
            // Если пересечений границ нет - возвращаем пустой массив

        // Вычисляем min от У, и max от X
        // Создаем новый массив c новыми границами пересечания
        // intersection = new ArraySet(x,y)

        // Находим относительное смещение первого массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        // intersection.search(this.x);

        // Копируем все ЯЧЕЙКИ первого массива в новый массив

        // Находим относительное смещение второго массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        // intersection.search(input.x)

        // новый массив побитово умножаем (&) на элементы второго массива (учитывая смещение)

        //возвращаем новый массив
        return null;
    }

    //Возвращает множество с элементами которых нет в исходном множестве
    public ArraySet Difference (ArraySet input) {
        // Если input это тоже самое множество - возвращаем копию исходного
        if (input == this) { //создать копирующий конструктор
            return this;
        }
        //Проверка на пересечение
            // isIntersect()
        // Если множетсва не пересекаются, возвращаем копию input

        //Если пересекаются:
        // Создаем новый массив c границами как у input
        // difference = new ArraySet(input.x, input.y)

        // Находим относительное смещение первого массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        // difference.search(this.x);

        // Копируем все ЯЧЕЙКИ первого массива в новый массив

        // Чтобы найти биты, которых нет в input
        // Инвертируем биты исходного массива (~) и умножаем на биты Input (&)

        //возвращаем новый массив
        return null;
    }

    // Возвращает множество с которым есть все неповторяющиеся элементы
    // Только если множества не пересекаются
    public ArraySet Merge(ArraySet input){
        // Если input это тоже самое множество - возвращаем исходный

        //Проверка на пересечение границ
            //isIntersect()

                //Если они пересекаются тогда проверка всех битов массивов
                //isBytesIntersect()


        // Вычисляем min от Х, и max от У
        // Создаем новый массив c новыми границами

        // Находим относительное смещение первого массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        // union.search(this.x)
        // И Копируем ЯЧЕЙКИ первого массива в новый массив

        // Находим относительное смещение второго массива (относительно нового массива)
        // union.search(input.x)
        // И Копируем ЯЧЕЙКИ второго массива в новый массив


        return null;
    }

    // Побитовая проверка на пересечение значений
    private boolean isBytesIntersect() {
        // Вычисляем min от У, и max от X
        // Вычисляем длину массива: (max - min) / 32 + 1
            //границы пересечения

        // Находим относительное смещение первого массива
        // Находим относительное смещение второго массива
        // Побитово умножаем бит первого массива на второй
        // Если == 0 - тогда значит есть пересечения битов => return true
        return false;
    }


    // Возвращает true - если value есть в множестве
    // false - если его нет
    private boolean isMember(int value){
        // Проверить выход за границы
        if (outOfBounds(value)) {
            return false;
        }
        // Найти нужную ячейку массива
        Position position = search(value);
        // Вычислить и вставить нужный бит, и побитовым И сравнить c ячейкой
        // Если ячейка != 0 - true
        return (array[position.index] & (1 << 31 - position.bit)) != 0;
    }

    public boolean Member(int value) {
        return isMember(value);
    }

    // При вставке и удалении не изменяется диапазон

    // Вставка значения в множество
    public void Insert(int value){
        // Проверить выход за границы
        if (outOfBounds(value)) {
            return;
        }
        // Найти нужную ячейку массива
        Position position = search(value);
        // Вычислить и вставить нужный бит, побитовым ИЛИ
        array[position.index] |= 1 << 31 - position.bit;
    }

    //Проверка выхода за границы
    public boolean outOfBounds(int value){
        return value < x || value > y;
    }

    // Удаление элемента из множества
    public void Delete(int value){
        // Проверить выход за границы
        if (outOfBounds(value)) {
            return;
        }
        // Найти нужную ячейку массива
        Position position = search(x);
        // Вычислить и вставить нужный бит, и побитовым И с ИНВЕРСИЕЙ сравнить с ячейкой
        array[position.index] &= ~(1 << 31 - position.bit);
    }

    // Присваивание нового множества
    public void Assign(ArraySet input){
        if (input == this) {
            //возвращаем копию
        }
        // Копирующий метод
        this.x = input.x;
        this.y = input.y;
        this.array = new int[input.array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = input.array[i];
        }
    }

    // Возвращает минимальное значение в множестве
    public int Min(){
        //нужно найти самый левый бит с 1 и выяснить его значение
        //пройтись по ячейкам (от 0 до размера массива)
            //если ячейка = 0, значит все биты = 0 - continue
            //по каждому биту (от 0 до 32)
                // сдвигаем вправо пока число не станет равным 1
                // и как то надо теперь выяснить значение
        return 0;
    }

    // Возвращает максимальное значение в множестве
    public int Max(){
        //нужно найти самый правый бит с 1 и выяснить его значение
        //пройтись по ячейкам (от последней ЯЧЕЙКИ до начальной)
            //если ячейка = 0, значит все биты = 0 - continue
            //по каждому биту (от 0 до 32)
                // делим на 2 (берем крайний бит) пока число не станет равным 1
                // и как то надо теперь выяснить значение
        return 0;
    }

    //возвращает значение по позиции
    private int getValue(Position input){
        //найти начало ячейки input
        // допустим диапазон от -40 до 35, 4 ячейки, от -64 до 64
        // нужно найти ближайщее число, которое кратно 32
        // если начало (-40) отрицательное, значит к -64
        // а если было бы положительное (40) то к округлить в меньшую сторону к 32

        //находим начало и смещаемся до значения
        return 0;
    }

    // Возвращает true - если множества одинаковые.
    public boolean Equal(ArraySet input){
        // Сравнить диапазоны, если они разные - false
        if (!isIntersect(input)) {
            return false;
        }
        // Вычисляем min от У, и max от X
        // Находим границы пересечения
        // Находим смещение для исходного массива
        // Также для Input массива
        // И сравниваем ЯЧЕЙКИ первого массива со вторым, они должны быть полностью идентичные
        // если нет - false
        return true;
    }

    // Возвращает множество, где найдет X
    // A и B - непересекающиеся множества
    // Если нет такого элемента, возвращаем пустое множество.
    public ArraySet Find(ArraySet input, int x){
        // Вызвать Member() на обоих множествах
        if (isMember(x)) {
            return this;
        }
        if (input.isMember(x)) {
            return input;
        }
        return null;
    }

    // Очистить множество
    public void MakeNull(){
        //Обнулить ячейки массива
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }

    public void Print(){
        for (int i = 0; i < array.length; i++) {
            System.out.println(toBinaryString(array[i]));
        }
    }
    public String toBinaryString(int value) {
        StringBuilder result = new StringBuilder(32);
        for(int i = 0; i < 32; ++i) {
            result.append((value & 1) == 1 ? '1' : '0');
            value >>>= 1;
        }
        return result.reverse().toString();
    }

}


