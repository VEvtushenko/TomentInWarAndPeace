package one.nlmk;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        int counter = 0, i;
        String buferString;
        String pathToInputFile = "src/main/resources/voyna.txt";
        Path pathText = Paths.get(pathToInputFile);
        Path pathKeyWord = Paths.get("src/main/resources/kostyl.txt");
        Scanner text = new Scanner(pathText);
        Scanner keyWordScan = new Scanner(pathKeyWord);                               // Не смог разобраться с кодировками, поэтому читаю "страда" тоже из файла, чтобы кодировка совпадала с текстом
        String keyWord = keyWordScan.nextLine();
        System.out.println("Key word: " + keyWord + "\n");
        Pattern stringPattern = Pattern.compile( keyWord + "[а-я]",
                Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS);         // Регулярка для проверки наличия слова в строке
        Pattern wordPattern = Pattern.compile( "^" + keyWord + "[а-я]",
                Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS);         // Регулярка для проверки каждого слова в строке
        String[] wordsBuferString;                                                    // В этом массиве будут храниться строки, разделённые на слова
        Matcher matcher;                                                              // Обьявим matcher которым будем проверять строки и слова, чтобы не делать объявление в цикле

        while (text.hasNext()) {                                                      // До последней строки
            buferString = text.nextLine();                                            // Читаем файл построчно в буфер
            matcher = stringPattern.matcher(buferString);                             // Проверяем есть ли в строке ключевые слова

            if (matcher.find()) {
                wordsBuferString = buferString.split("\\s|\\.|,|\\?|!|]|\\[");   // Если есть, то разбиваем строку на слова

                for (i = 0; i < wordsBuferString.length; i++) {
                    matcher = wordPattern.matcher(wordsBuferString[i]);                // Проверяем каждое слово в строке
                    if (matcher.find()) {
                        System.out.println(wordsBuferString[i] + "\n");                // Выводим слово на экран для проверки и увеличиваем счётчик
                        counter = counter + 1;
                    }
                }
            }
        }
        System.out.println("counter = " + counter );                                    // Выводим счётчик на экран
    }
}
