package Set.ArraySet;

// Абстрактный тип данных Множество реализуемый на массиве,
// где каждый бит в ячейке массива указывает на наличие числа во множестве
public class ArraySet {
    int x; // Левая граница
    int y; // Права граница
    int[] array; // Массив

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
        array = new int[countArraySize(x,y)];
    }

    private int countArraySize(int x, int y){
        // Инициализация массива
        // Рассматриваем разные случаи потому что массив выходит не симметричным (допустим: -32 до -1 и от 0 до 31 => не симметричный)
        int length;
        // Если x >=0 (положительный диапазон)
        if (x >= 0) {
            // тогда размер массива = y/32 - x/32 + 1
            length = y/32 - x/32 +1;
            System.out.println("Положительный диапазон, длина массива = " + length);
            return length;

            // почему не (y-x)/32 + 1, потому что целочисленное деление
            // допустим от 31 до 32 (нужны две ячейки массива)
            //от 0 до 31 и от 32 до 63
            // по первой формуле = (32-31)/32 + 1 = 1 ячейка, а нужно две
        }
        //Если y < 0 (отрицательный диапазон)
        if (y < 0) {
            // тогда размер массива = (y+1)/32 - (x+1)/32 + 1
            length = (y+1)/32 - (x+1)/32 + 1;
            System.out.println("Отрицательный диапазон, длина массива = " + length);

            //допустим -33 до -32 (нужны две ячейки массива)
            // От -64 до -33 и от -32 до -1

            // к каждой границе прибавляем +1 потому что они отрицательные (чтобы могли /32 и %32)
            // Если будем пользоваться первой формулой
            // -32/32 - (-33/32) + 1 = -1 - (-1) + 1 = 1
            // а нам нужно две ячейки

            // в конце +1 потому что мы тут считаем длину, и она не может быть нулевой
        }


        //Если x < 0 && y >= 0 (двухсторонний диапазон)
        else {
            // тогда размер массива = y/32 - (x+1)/32 + 2
            length = y/32 - (x+1)/32 + 2;
            System.out.println("Двухсторонний диапазон, длина массива = " + length);

            // у двухстороннего уже минимум две ячейки нужно
            // знаем, что x (начало) у нас отрицательный, поэтому делаем + 1

            // почему +2:
            // допустим от -32 до 31 нужны две ячейки -32 до -1 и от 0 до 31
            // по формуле 31/32 - (-32+1)/32 + 1 = 0 - 0 + 1 = 1
            // а нужно минимум две ячейки, поэтому добавляем еще +1 (=> в итоговой формуле +2)
        }
        return length;

        //Все это вычисляется только в начале, при инициализации массива
        //потом при Union(), и в других методах не нужно будет вычислять смещение битов
    }

    // Копирующий конструктор
    public ArraySet(ArraySet input) {
        copySet(input);
    }

    // Копирующий метод
    private void copySet(ArraySet input){
        this.x = input.x;
        this.y = input.y;
        array = new int[input.array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = input.array[i];
        }
    }


    // Нахождение позиции элемента в массиве
    // возвращает номер ячейки массива и бит
    private Position search(int value) {
        //если значение отрицательное
        if (value < 0) {
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

            return new Position((value + 1) / 32 - (x + 1) / 32, 31 - (-(value + 1) % 32 ));
        }


        //если значение положительное и левая граница отрицательная (значит у нас диапазон двухсторонний)
        if (x < 0) {
            // Индекс массива = value / 32 - (x + 1) / 32 + 1
            // +1 а не +2, т.к индекс с нуля начинается

            // Номер бита = value % 32
            return new Position(value / 32 - (x + 1) / 32 + 1, value % 32);

        }
        //если значение положительное и левая граница положительная (значит диапазон положительный)
        else {
            // значит положительные числа, начинаются с 0 и можно просто /32
            // Индекс массива = value / 32 - x / 32

            // Номер бита = value % 32
            return new Position(value / 32 - x / 32, value % 32);
        }
    }

    // Нахождение индекса элемента в массиве, где находится число
    // тот же самый search() только без нахождения бита
    private int searchIndex(int value) {
        if (value < 0) {
            return (value + 1) / 32 - (x + 1) / 32;
        }
        if (x < 0) {
            return value / 32 - (x + 1) / 32 + 1;
        }
        else {
            return value / 32 - x / 32;
        }
    }



    // Возвращает новое множество в котором есть все неповторяющиеся элементы
    public ArraySet Union (ArraySet input) {
        // Если input это тоже самое множество - возвращаем копию исходного
        if (this == input) {
            return new ArraySet(this);
        }
        // Вычисляем min от Х, и max от У
        int x = this.x > input.x ? input.x : this.x;
        int y = this.y > input.y ? this.y : input.y;

        // Создаем новый массив c новыми границами
        ArraySet union = new ArraySet(x,y);
        // Находим относительное смещение первого массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        int start1 = union.searchIndex(this.x);
        // И Копируем ЯЧЕЙКИ первого массива в новый массив
        for (int i = 0; i < array.length; i++) {
            union.array[start1 + i] = array[i];
        }

        // Находим относительное смещение второго массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        int start2 = union.searchIndex(input.x);
        // новый массив побитово складываем (|) на элементы второго массива (учитывая смещение)
        for (int i = 0; i < input.array.length; i++) {
            union.array[start2 + i] |= input.array[i];
        }
        //возвращаем новый массив
        return union;
    }

    //Проверка на пересечения границ
    private boolean isIntersect(ArraySet input){
//        return y > input.x || x < input.y;
        return (this.x - input.y) * (input.x - this.y) > 0;
    }

    //Возвращает множество общих элементов
    public ArraySet Intersection(ArraySet input){
        // Если input это тоже самое множество - возвращаем копию исходного
        if (this == input) {
            return new ArraySet(this);
        }

        // Проверяем пересечения границ
        // Если пересечений границ нет - возвращаем множество с минимальным диапазоном
        if (!isIntersect(input)) {
            int currentLength = y - x;
            int inputLength = input.y - input.x;
            return currentLength > inputLength ?
                    new ArraySet(input.x, input.y) :
                    new ArraySet(x, y);
        }

        // Вычисляем min от У, и max от X
        int x = this.x > input.x ? this.x : input.x;
        int y = this.y > input.y ? input.y : this.y;

        // Создаем новый массив c новыми границами пересечания
        ArraySet intersection = new ArraySet(x,y);

        // Находим относительное смещение первого и второго массива
        // нахождением в какой ячейке будет элемент относительно нового массива пересечения
        int start1 = this.searchIndex(x);
        int start2 = input.searchIndex(x);

        // новый массив побитово умножаем (&) на элементы второго массива (учитывая смещение)
        for (int i = 0; i < intersection.array.length; i++) {
            intersection.array[i] = array[start1 + i] & input.array[start2 + i];
        }

        //возвращаем новый массив
        return intersection;
    }

    //Возвращает множество с элементами которых нет в исходном множестве
    public ArraySet Difference (ArraySet input) {
        // Если input это тоже самое множество - возвращаем копию исходного
        if (this == input) {
            return new ArraySet(this);
        }
        //Проверка на пересечение
        // Если множетсва не пересекаются, возвращаем копию input
        if (!isIntersect(input)) {
            return new ArraySet(input);
        }

        //Если пересекаются:

        // Создаем новый массив c границами как у input
        ArraySet difference = new ArraySet(input.x, input.y);

        // Копируем ЯЧЕЙКИ первого массива
        for (int i = 0; i < input.array.length; i++) {
            difference.array[i] = array[i];
        }

        // Находим смещение первого массива относительно второго
        int start = this.searchIndex(input.x);
        // Минимум от длины массивов
        int end = array.length > input.array.length ?
                input.array.length :
                array.length;

        // Чтобы найти биты, которых нет в input
        // Инвертируем биты исходного массива (~) и умножаем на биты Input (&)
        for (int i = 0; i < end; i++) {
            difference.array[start + i] = (array[start + i] & ~input.array[i]);
        }
        //возвращаем новый массив
        return difference;
    }

    // Возвращает множество с которым есть все неповторяющиеся элементы
    // Только если множества не пересекаются
    public ArraySet Merge(ArraySet input){
        // Если input это тоже самое множество - возвращаем копию исходного
        if (this == input) {
            return new ArraySet(this);
        }
        //Проверка на пересечение границ
            //Если они пересекаются тогда проверка всех битов массивов
                //Если и биты пересекаются -> ошибка
        if (isIntersect(input) && isBytesIntersect(input)) {
            throw new RuntimeException("Merge(): множества пересекаются");
        }

        // Вычисляем min от Х, и max от У
        int x = this.x > input.x ? input.x : this.x;
        int y = this.y > input.y ? this.y : input.y;

        // Создаем новый массив c новыми границами
        ArraySet merge = new ArraySet(x,y);
        // Находим относительное смещение первого массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        int start1 = merge.searchIndex(this.x);
        // И Копируем ЯЧЕЙКИ первого массива в новый массив
        for (int i = 0; i < array.length; i++) {
            merge.array[start1 + i] = array[i];
        }

        // Находим относительное смещение второго массива
        // нахождением в какой ячейке будет элемент относительно нового массива
        int start2 = merge.searchIndex(input.x);
        // новый массив побитово складываем (|) на элементы второго массива (учитывая смещение)
        for (int i = 0; i < input.array.length; i++) {
            merge.array[start2 + i] |= input.array[i];
        }
        //возвращаем новый массив
        return merge;
    }

    // Побитовая проверка на пересечение значений
    public boolean isBytesIntersect(ArraySet input) {
        // Вычисляем min от У, и max от X
        int x = this.x > input.x ? this.x : input.x;
        int y = this.y > input.y ? input.y : this.y;
        // Вычисляем длину массива:
        int length = countArraySize(x,y);
        // Находим относительное смещение первого массива
        int start1 = this.searchIndex(x);
        // Находим относительное смещение второго массива
        int start2 = input.searchIndex(x);
        // Побитово умножаем бит первого массива на второй
        // Если != 0 - тогда значит есть пересечения битов => return true
        for (int i = 0; i < length; i++) {
            if((array[start1 + i] & input.array[start2 + i]) != 0) {
                return true;
            }
        }
        return false;
    }

    //Проверка выхода за границы
    public boolean outOfBounds(int value){
        return value < x || value > y;
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


    // Удаление элемента из множества
    public void Delete(int value){
        // Проверить выход за границы
        if (outOfBounds(value)) {
            return;
        }
        // Найти нужную ячейку массива
        Position position = search(value);
        // Вычислить и вставить нужный бит, и побитовым И с ИНВЕРСИЕЙ сравнить с ячейкой
        array[position.index] &= ~(1 << 31 - position.bit);
    }

    // Присваивание нового множества
    public void Assign(ArraySet input){
        //Копирующий метод
        copySet(input);
    }

    // Возвращает минимальное значение в множестве
    public int Min(){
        //нужно найти самый левый бит с 1 и выяснить его значение
        //пройтись по ячейкам (от 0 до размера массива)
        for (int i = 0; i < array.length; i++) {
            //если ячейка = 0, значит все биты = 0 - continue
            if (array[i] != 0) {
                //по каждому биту (от 0 до 32)
                int cell = array[i];
                int bit = 0;
                // сдвигаем вправо пока число не станет равным 1
                while (cell != 1) {
                    cell >>= 1;
                    bit++;
                }
                bit = 32 - bit;
                // выясняем значение бита
                return getValue(new Position(i, bit));
            }
        }

        return 0;
    }

    // Возвращает максимальное значение в множестве
    public int Max(){
        //нужно найти самый правый бит с 1 и выяснить его значение
        //пройтись по ячейкам (от последней ЯЧЕЙКИ до начальной)
        for (int i = array.length - 1; i >= 0; i--) {
            //если ячейка = 0, значит все биты = 0 - continue
            if (array[i] != 0) {
                //по каждому биту (от 0 до 32)
                int cell = array[i];
                int bit = 0;
                // делим на 2 (берем крайний бит) пока число не станет равным 1
                while (cell % 2 == 0) {
                    cell >>= 1;
                    bit++;
                }
                bit = 32 - bit;
                // выясняем значение бита
                return getValue(new Position(i, bit));
            }
        }
        return 0;
    }

    //возвращает значение по позиции
    private int getValue(Position input){
        //найти начало ячейки input
        // допустим диапазон от -40 до 35, 4 ячейки, от -64 до 64
        // нужно найти ближайщее число, которое кратно 32
        // если начало (-40) отрицательное, значит к -64
        // а если было бы положительное (40) то к округлить в меньшую сторону к 32
        int startValue = x < 0 ? -((-x + 31) & ~31) : ((x + 31) & ~31) - 32;
        //находим начало и смещаемся до значения
        return startValue  + 32 * input.index + input.bit - 1;
    }

    // Возвращает true - если множества одинаковые.
    public boolean Equal(ArraySet input){
        // Сравнить диапазоны, если они разные - false
        if (!isIntersect(input)) {
            return false;
        }
        // Вычисляем min от У, и max от X
        int x = this.x > input.x ? this.x : input.x;
        int y = this.y > input.y ? input.y : this.y;
        // Вычисляем длину массива:
        int length = countArraySize(x,y);

        // Находим смещение для исходного массива
        int start1 = this.searchIndex(x);
        // Также для Input массива
        int start2 = input.searchIndex(x);

        // Проверяем на ноль не пересекающиеся промежутки
        for (int i = 0; i < start1; i++) {
            if(array[i] != 0) {
                return false;
            }
        }
        for (int i = start1 + length; i < array.length; i++) {
            if(array[i] != 0) {
                return false;
            }
        }
        for (int i = 0; i < start2; i++) {
            if(input.array[i] != 0) {
                return false;
            }
        }
        for (int i = start2 + length; i < input.array.length; i++) {
            if(input.array[i] != 0) {
                return false;
            }
        }

        // И сравниваем ЯЧЕЙКИ первого массива со вторым, они должны быть полностью идентичные
        // если нет - false
        for (int i = 0; i < length; i++) {
            if((array[start1 + i] != input.array[start2 + i])) {
                return false;
            }
        }

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

    public void binaryPrint(){
        for (int i = 0; i < array.length; i++) {
            System.out.println(toBinaryString(array[i]));
        }
    }

    public void Print(){
        System.out.println("Диапазон: от " + x + " до " + y);
        StringBuilder result = new StringBuilder();
        result.append("{ ");
        for (int i = x; i <= y; i++) {
            if (isMember(i)) {
                result.append(i + ", ");
            }
        }
        if (result.length() > 2) {
            result.deleteCharAt(result.length() - 2);
        }
        result.append("}\n");
        System.out.println(result);
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


