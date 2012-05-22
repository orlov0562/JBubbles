import java.awt.Color;

interface Config {
	
	// кол-во шариков
	static int BOOBLE_COUNT_BY_X = 20;
	static int BOOBLE_COUNT_BY_Y = BOOBLE_COUNT_BY_X;

	// размер поля
	static int FIELD_WIDTH = 600;
	static int FIELD_HEIGHT = 600;
	
	// отступ поля от границ окна
	static int FIELD_INDENT_X = 10;
	static int FIELD_INDENT_Y = 10;

	// отступ от клеток
	static int BOOBLE_INDENT_X = 2; 
	static int BOOBLE_INDENT_Y = 2;
	
	static int BOOBLE_DESTROY_SPEED = 5; // скорость стирания
	
	static boolean DRAW_BOOBLE_BORDER = false; // рисовать бордюр вокруг кружка
	static int BOOBLE_BORDER = 1; // ширина бордюра вокруг кружка
	static Color BOOBLE_BORDER_COLOR = new Color(0xF1, 0xF1, 0xF1); //цвет бордюра вокруг кружка
	
	static boolean DRAW_FIELD_LINES = false; // рисовать клетки поля
	static Color DRAW_FIELD_LINES_COLOR = Color.LIGHT_GRAY; // рисовать клетки поля этим цветом
	
	static boolean CHECK_CROSS_COMBINATION = false; // учитываем соседей крест на крест(Тип игры при запуске) 
	
	static String SOUND_FILE_NAME = "beep4.wav"; // мелодия при уничтожении шарика
	static boolean SOUND_ON = false; // включить ли звук при запуске
	
	static int BOOBLE_STYLE = 2; // визуальное оформление шариков: 1,2
	
	static int BUTTONS_WIDTH = 200; // ширина кнопок
	static int BUTTONS_HEIGHT = 25; // высота кнопок
	static int BUTTONS_INDENT_Y = 10; // расстояние между кнопками

	static int LABELS_WIDTH = 150; // ширина меток
	static int LABELS_HEIGHT = 16; // высота меток
	static int LABELS_INDENT_Y = 5; // расстояние между метками
	static int LABELS_FONT_SIZE = 16; // размер шрифта меток
	
	static int BOOBLE_COLORS_COUNT = 2; // кол-во цветов шариков c которыми начинается игра
	
	static int DEFAULT_LIVES_COUNT = 3; // кол-во жизней на старте игры
	
	static int DEFAULT_LEVEL_NUMBER = 1; // номер уровня с которого стартует игра
	
	static boolean LAST_SINGLE_BOOBLE_IS_NEXT_LEVEL = true; // если лопнут последний шарик и нет жизней, то это следующий уровень
}
