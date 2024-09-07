package com.javarush.constants;

public class ApplicationCompletionConstants {

    public  static String PROGRAM_START_INFO = "C:\\Users\\user1\\IdeaProjects\\CRYPTOGRAF\\src\\main\\resources\\info\\description";

    public  static String INPUT_FILE_DEMO = "C:\\Users\\user1\\IdeaProjects\\CRYPTOGRAF\\src\\main\\resources\\Demonstration\\NativeFile.txt";
    public  static String CRYPTED_FILE = "C:\\Users\\user1\\IdeaProjects\\CRYPTOGRAF\\src\\main\\resources\\Demonstration\\CryptedFile.txt";

    public  static String OPEN_FILE_DEMO = "C:\\Users\\user1\\IdeaProjects\\CRYPTOGRAF\\src\\main\\resources\\Demonstration\\OpenText.txt";

    public  static String UNCRYPTED_FILE = "C:\\Users\\user1\\IdeaProjects\\CRYPTOGRAF\\src\\main\\resources\\Demonstration\\UncryptedFile.txt";
    public static final String SUCCESS = "The application completed successfully";
    public static final String EXCEPTION = "The application has exited with an error: ";
    public static final String QUIT = "The application has finished early by user.";

    public static final String PUSH_ENTER_TO_CONTINUE = "\"q\" для немедленного завершения работы программы:\n";
    public static final String PROGRAM_MODE = "Выберите режим работы программы:\n" +
            "\"1\" - консольный режим;\n" +
            "\"2\" - оконный режим;\n" +
            "\"3\" - аудиорежим.";

    public static final String FUNCTION = "Выберите требуемую задачу:\n" +
            "\"1\" - вызов функции зашифровывания файла используя ключ шифрования;\n" +
            "\"2\" - вызов функции расшифровывания файла используя ключ шифрования;\n" +
            "\"3a\" - вызов вункции \"автоматический взлом\" файла методом Брут-Форс;\n" +
            "\"3h\" - вызов вункции \"ручной взлом\" файла методом Брут-Форс;\n" +
            "\"4\" - вызов функции \"взлом\" файла методом \"Статистический анализ;\n" +
            "\"5\" - ручной режим доработки автоматически взломанного файла.\n";


    public  static final String INPUT_FILE_NAME = "Укажите адрес файла, требуещого к зашифровыванию:\n" +
            "\"0\" - демонстрационный режим, используется демо-файл (по умолчанию);\n" +
            "\"real/file/path\" - действительный путь к файлу.";

    public  static final String CRYPTED_FILE_NAME = "Укажите адрес результирующего файла (зашифрованного)\n" +
            "\"0\" - демонстрационный режим, используется путь к файлу по умолчанию;\n" +
            "\"real/file/path\" - действительный путь к файлу.";

    public  static final String OPEN_FILE_NAME = "Укажите адрес опорного файла, содержащего открытый текст:\n" +
            "\"0\" - демонстрационный режим, используется демо-файл (по умолчанию);\n" +
            "\"real/file/path\" - действительный путь к файлу.";

    public static final String CRYPTO_KEY = "Укажите ключ шифрования:\n" +
            "\"0\" - использовать встроенный ключ шифрования;\n" +
            "\"somespecificcryptokey\" - пользовательский ключ шифрования (требуется указать последовательность символов).";

    public static final String SHIFT_KEY = "Укажите шаг смещения для ключа шифрования:\n" +
            "\"0\" - шаг смещения определяет система;\n" +
            "\"N\" - любое вещественное число.";

    public static final String FINISH_TASK = "\n========================================\n";
}
