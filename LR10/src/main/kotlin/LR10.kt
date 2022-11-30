package com.mtpms.lr10

/**1**/
object StringDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val palindrome = "Dot saw I was Tod"
        val len = palindrome.length
        val tempCharArray = CharArray(len)
        val charArray = CharArray(len)
        for (i in 0 until len) {
            tempCharArray[i] = palindrome[i]
        }
        for (j in 0 until len) {
            charArray[j] = tempCharArray[len - 1 - j]
        }
        val reversePalindrome = String(charArray)
        println(reversePalindrome)
    }
}


/**3b**/
enum class Holdidays(val date: String) {
    Christmas("25.12"),
    NewYear("31.12"),
    FreedomDay("25.03"),
    VictoryDay("09.05");

    fun isHoliday() {
        val TAG = "LR10_DEBUG"

        if (date.isEmpty() || date.length != 5) {
            throw Exception()
        } else {
            when (date) {
                Christmas.date -> println("Christmas")
                NewYear.date -> println("New Year")
                VictoryDay.date -> println("Victory Day")
                FreedomDay.date -> println("Freedom Day of the Republic of Belarus")
            }
        }
    }

}

fun main()
{
    val TAG = "LR10_DEBUG"

    /**2**/
    //2a. Определите несколько переменных с val и var с явным указанием типа и без (Int, Double, String) на уровне файла.

    //var - можно менять значение после инициализации
    var age: Int = 19;
    var name: String = "Gleb"
    var faculty = "IT"

    //val - нельзя менять значение после инициализации
    val USER_NAME_FIELD = "UserName";
    val UNIVERSITY = "BSTU"

    val MEMORY_SIZE = 1234.0000000;

    println("-------------------- 2a -------------------- ");
    println("$name, $age, $UNIVERSITY, $faculty");
    println("$USER_NAME_FIELD");
    println(MEMORY_SIZE);

    //2b. Выполните преобразования переменных из типа Byte в Int, из Int в String.
    val ByteToInt = Byte.MAX_VALUE.toInt()
    val IntToStr = Int.MAX_VALUE.toString()

    println("-------------------- 2b -------------------- ");
    println(ByteToInt);
    println(IntToStr);

    //2c. Объявите переменную типа Int? Введите с консоли число (или пустую строку)
    val CONSTANT_STRING = "Constant"
    //13 вопрос
    var input: Int? = null //No more NullPointerException

    println("-------------------- 2c -------------------- ");
    println(CONSTANT_STRING);
    println(input);

    /**3**/
//    3a.
//    Напишите функцию isValid проверки корректности ввода логина – пароля (параметры функции).
//    Логин должно иметь формат email.
//    Пароль от 6 до 12 символов, без пробелов. Логин-пароль не могут быть пустыми.
//    Для этой проверки напишите локальную функцию notNull. Функция notNull должна иметь
//    тело-выражение (без блока) на основе if.

    fun isValid(login: String, password: String): Boolean
    {
        //24 вопрос
        val notNull: () -> Boolean = { !login.isEmpty() && !password.isEmpty() }

        return (notNull() &&
                password.trim().length >= 6 && password.trim().length <= 12)
    }
    println("-------------------- 3a -------------------- ");
    println(isValid("malder800@gmail.com", "Password").toString())

    //3b.
    //Задайте через перечисление праздничные дни в году.
    //Напишите функцию с использованием when для проверки по введенной дате (день, месяц, год) - будний или праздничный день.
    //Предусмотрите вариант, когда пользователь передал null строку или формат не соответствует.

    var christmas = Holdidays.Christmas;
    println("-------------------- 3b -------------------- ");
    println("Christmas" + christmas.isHoliday()) //true

    //3c.
    //Допишите функцию
    //fun doOperation (a:Int , b:Int, operation:Char): Double
    //Используя конструкцию when, вычислите значение операции, указанной в operation.
    //Функция должна корректно обрабатывать любую допустимую в Kotlin бинарную операцию.
    //Если операция допустима – генерируйте исключение. Продемонстрируйте работу функции.

    fun doOperation(a: Int, b: Int, operation: Char): Double {
        if (operation != '+' && operation != '-' && operation != '*' && operation != '/')
            throw Exception();

        //8 вопрос
        when (operation) {
            '+' -> return a.toDouble() + b.toDouble();
            '-' -> return a.toDouble() - b.toDouble();
            '*' -> return a.toDouble() * b.toDouble();
            '/' -> return a.toDouble() / b.toDouble();
            else -> return 0.0;
        }
    }

    println("-------------------- 3c -------------------- ");
    println(doOperation(10, 10, '+').toString())
    println(doOperation(100, 5, '-').toString())
    println(doOperation(20, 2, '*').toString())
    println(doOperation(20, 5, '/').toString())

    //3d
    //Реализуйте функцию indexOfMax(), чтобы она возвращала индекс самого большого элемента в массиве,
    //или null, если массив пуст или таких элементов несколько. Сделайте ее потом функцией расширения для IntArray
    //fun indexOfMax(a: IntArray): Int? {}
    println("-------------------- 3d -------------------- ");

    fun indexOfMax(a: IntArray): Int? {
        var maxElem: Int? = a.maxOrNull();
        return maxElem;
    }

    println(indexOfMax(intArrayOf()).toString())
    println(indexOfMax(intArrayOf(1, 2, 3, 4, 5, 100, 4)).toString())

    //20 вопрос
    //функция расширения
    fun IntArray.indexOfMax(a: IntArray): Int? {
        var maxElem: Int? = a.maxOrNull();
        return maxElem;
    }

    //3e.
    //Напишите функцию расширения coincidence для String, которая проверяет сколько позиций совпало
    //со строкой переданной в аргументе и возвращает количеством совпавших символов.

    fun String.coincidence(str: String): Int {
        var i = 0;
        var sameSymnCount = 0;
        for (symb in str) {
            if (this.get(i) == symb) {
                sameSymnCount++
            }
            i++
        }
        return sameSymnCount
    }

    println("-------------------- 3e -------------------- ");
    val sameSymbolsCheck = "Hello"
    println(sameSymbolsCheck.coincidence("He").toString())

    /**4**/
    //4a
    //Определите функцию вычисления факториала в двух вариантах:
    //1) с циклом и диапазоном
    //2) рекурсивную на основе индуктивного определения
    //fun factorial(n: Int): Double

    // 1) с циклом и диапазоном
    fun factorialCycle(number: Int): Double {
        var result = 1.0;
        var calculated_num = number;

        while (calculated_num > 1) {
            result *= calculated_num;
            calculated_num--
        }

        return result;
    }
    println("-------------------- 4a -------------------- ");
    println(factorialCycle(5).toString())

    //2) рекурсивную на основе индуктивного определения
    fun factorialRecursive(n: Int): Double = if (n < 2) 1.0 else n * factorialRecursive(n - 1)
    println(factorialRecursive(5).toString())

    //4b.
    //Определите функцию isPrime, проверки является ли число простым.
    //(Напишите ее оптимально. В частности, достаточно проверить делимость числа n на все числа в интервале от 2 до n/2,
    //так как на большие числа n всё равно делится не будет. Достаточно ограничится интервалом от 2 до √n — если n и делится
    //на какое-то большее √n число (например, 50 делится на 10), то оно будет делится и на какое-то меньшее число (в данном случае, 50 делится на 5=50/10).)

    fun isPrime(num: Int) {
        var flag = false
        for (i in 2..num / 2) {
            // condition for nonprime number
            if (num % i == 0) {
                flag = true
                break
            }
        }

        if (!flag) {
            println("$num is a prime")
        }
        else {
            println("$num is not a prime")
        }
    }

    println("-------------------- 4b -------------------- ");
    isPrime(199).toString()

    /**5**/
//5a.
//Напишите 2 лямбды для передачи в функцию, чтобы проверить, содержит ли задуманное коллекция.
//Функция any получает предикат в качестве аргумента и возвращает true, если хотя бы один элемент удовлетворяет предикату.
    var number = 11;
    fun containsIn(collections: Collection<Int>): Boolean =
        collections.any { collections.contains(0) && collections.contains(1) }

    var nums = listOf(0, 1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 12)
    var nums_2 = listOf(0, 2)

    println("-------------------- 5a -------------------- ");
    println(containsIn(nums).toString()) //true
    println(containsIn(nums_2).toString()) //false

    //5b.
    //Используя listOf сформируйте список целых. Примените несколько разных способов добавления элемента в коллекцию (add, +=).
    //Оставьте только уникальные элементы.
    //Отфильтруйте и оставьте только нечетные.
    //Выведите элементы через forEach.
    //Передайте ссылку на функцию проверки на простое число в filter для проверки элементов списка.
    //val numbers = listOf(1, 2, 3,.....)
    //println(numbers.filter(::isPrime))
    //Примените к списку find, groupBy, all, any
    //Выполните деструктуризацию первых 2-х элементов списка.

    var numbers = listOf(10, 20, 30, 1, 2, 3, 4, 5, 5, 1, 2);
    numbers += 999

    //Оставьте только уникальные элементы.
    var set: Set<Int> = LinkedHashSet(numbers);

    //Выведите элементы через forEach.
    println("-------------------- 5b -------------------- ");

    for (i in set)
    {
        println(i.toString());
    }

    //Отфильтруйте и оставьте только нечетные.
    println("---------- Filter ---------- ");
    println(set.filter { it % 2 != 0 });

    //Примените к списку find, groupBy, all, any
    println("---------- Find ---------- ");
    println(set.find { it == 2 });
    println("---------- GroupBy ---------- ");
    println(set.groupBy { it });
    println("---------- All ---------- ");
    //возвращает true если все элементы соответствуют условию
    println(set.all { it > 10 });
    println("---------- Any ---------- ");
    //возвращает true если хотя бы один элемент соответствуют условию
    println(set.any { it < 20 });

    //5c. Сформируйте map с фамилией и количеством правильных
    //ответов в тесте (число от 1 до 40 максимально). Выполните
    //преобразование – замените число правильных ответов на
    //оценку. Используйте следующие критерии
    println("-------------------- 5c -------------------- ");
    var mapMarks = mapOf("First" to 6, "Second" to 40, "Third" to 26, "Fourth" to 35, "Fifth" to 19,
        "Sixth" to 22, "Seventh" to 2, "Eighth" to 15, "Ninth" to 31, "Tenth" to 9)

    //11 вопрос
    mapMarks.mapValues {
        when {

            it.value in 37..41 -> {
                println("${it.key} - mark is 10")
            }
            it.value in 33..36 -> {
                println("${it.key} - mark is 9")
            }
            it.value in 29..32 -> {
                println("${it.key} - mark is 8")
            }
            it.value in 25..28 -> {
                println("${it.key} - mark is 7")
            }
            it.value in 21..24 -> {
                println("${it.key} - mark is 6")
            }
            it.value in 17..20 -> {
                println("${it.key} - mark is 5")
            }
            it.value in 13..16 -> {
                println("${it.key} - mark is 4")
            }
            it.value in 9..12 -> {
                println("${it.key} - mark is 3")
            }
            it.value in 5..8 -> {
                println("${it.key} - mark is 2")
            }
            it.value in 1..4 -> {
                println("${it.key} - mark is 1")
            }
            else -> println("Error")
        }
    }

    println("-------------------- ");
    mapMarks.forEach { (key, value) -> println(key + " " + value)}
}

