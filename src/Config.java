import java.awt.Color;

interface Config {
	
	// ���-�� �������
	static int BOOBLE_COUNT_BY_X = 20;
	static int BOOBLE_COUNT_BY_Y = BOOBLE_COUNT_BY_X;

	// ������ ����
	static int FIELD_WIDTH = 600;
	static int FIELD_HEIGHT = 600;
	
	// ������ ���� �� ������ ����
	static int FIELD_INDENT_X = 10;
	static int FIELD_INDENT_Y = 10;

	// ������ �� ������
	static int BOOBLE_INDENT_X = 2; 
	static int BOOBLE_INDENT_Y = 2;
	
	static int BOOBLE_DESTROY_SPEED = 5; // �������� ��������
	
	static boolean DRAW_BOOBLE_BORDER = false; // �������� ������ ������ ������
	static int BOOBLE_BORDER = 1; // ������ ������� ������ ������
	static Color BOOBLE_BORDER_COLOR = new Color(0xF1, 0xF1, 0xF1); //���� ������� ������ ������
	
	static boolean DRAW_FIELD_LINES = false; // �������� ������ ����
	static Color DRAW_FIELD_LINES_COLOR = Color.LIGHT_GRAY; // �������� ������ ���� ���� ������
	
	static boolean CHECK_CROSS_COMBINATION = false; // ��������� ������� ����� �� �����(��� ���� ��� �������) 
	
	static String SOUND_FILE_NAME = "beep4.wav"; // ������� ��� ����������� ������
	static boolean SOUND_ON = false; // �������� �� ���� ��� �������
	
	static int BOOBLE_STYLE = 2; // ���������� ���������� �������: 1,2
	
	static int BUTTONS_WIDTH = 200; // ������ ������
	static int BUTTONS_HEIGHT = 25; // ������ ������
	static int BUTTONS_INDENT_Y = 10; // ���������� ����� ��������

	static int LABELS_WIDTH = 150; // ������ �����
	static int LABELS_HEIGHT = 16; // ������ �����
	static int LABELS_INDENT_Y = 5; // ���������� ����� �������
	static int LABELS_FONT_SIZE = 16; // ������ ������ �����
	
	static int BOOBLE_COLORS_COUNT = 2; // ���-�� ������ ������� c �������� ���������� ����
	
	static int DEFAULT_LIVES_COUNT = 3; // ���-�� ������ �� ������ ����
	
	static int DEFAULT_LEVEL_NUMBER = 1; // ����� ������ � �������� �������� ����
	
	static boolean LAST_SINGLE_BOOBLE_IS_NEXT_LEVEL = true; // ���� ������ ��������� ����� � ��� ������, �� ��� ��������� �������
}
