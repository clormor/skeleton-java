package io.github.clormor.skeleton.hackerrank.ai;

import org.junit.Before;
import org.junit.Test;

import static io.github.clormor.skeleton.hackerrank.ai.BotSavesPrincess.bot_char;
import static io.github.clormor.skeleton.hackerrank.ai.BotSavesPrincess.down_move;
import static io.github.clormor.skeleton.hackerrank.ai.BotSavesPrincess.left_move;
import static io.github.clormor.skeleton.hackerrank.ai.BotSavesPrincess.matrix_char;
import static io.github.clormor.skeleton.hackerrank.ai.BotSavesPrincess.peach_char;
import static org.junit.Assert.assertEquals;

public class TestBotSavesPrincess {

    BotSavesPrincess b;

    static char[][] simple_matrix = {
            {matrix_char, matrix_char, matrix_char},
            {matrix_char, bot_char, matrix_char},
            {peach_char, matrix_char, matrix_char},
    };

    @Before
    public void init() {
        b = new BotSavesPrincess();
    }

    @Test
    public void test_next_move() {
        String expected = new StringBuilder()
                .append(down_move)
                .toString();

        runNextMoveTest(simple_matrix,expected);
    }

    @Test
    public void test_find() {
        String expected = new StringBuilder()
                .append(down_move)
                .append(System.lineSeparator())
                .append(left_move)
                .append(System.lineSeparator())
                .toString();

        runFindTest(simple_matrix,expected);
    }

    private void runNextMoveTest(char[][] matrix, String expected) {
        BotSavesPrincess.Position peach = b.locateTarget(matrix.length, matrix, peach_char);
        BotSavesPrincess.Position bot = b.locateTarget(matrix.length, matrix, bot_char);
        String result = b.move(bot, peach).lastMove;
        assertEquals(expected, result);
    }

    private void runFindTest(char[][] matrix, String expected) {
        String result = b.displayPathToPrincess(matrix.length, matrix);
        assertEquals(expected, result);
    }
}
