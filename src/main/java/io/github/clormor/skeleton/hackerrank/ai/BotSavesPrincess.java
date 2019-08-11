package io.github.clormor.skeleton.hackerrank.ai;

import com.google.common.annotations.VisibleForTesting;

public class BotSavesPrincess {

    static final char bot_char = 'm';
    static final char matrix_char = '-';
    static final char peach_char = 'p';

    static final String up_move = "UP";
    static final String right_move = "RIGHT";
    static final String down_move = "DOWN";
    static final String left_move = "LEFT";

    public String displayPathToPrincess(int n, char[][] matrix) {
        StringBuilder result = new StringBuilder();
        Position peach = locateTarget(n, matrix, peach_char);
        Position bot = locateTarget(n, matrix, bot_char);
        while (!bot.equals(peach)) {
            Move lastMove = move(bot, peach);
            bot = lastMove.newPosition;
            result.append(lastMove.lastMove).append(System.lineSeparator());
        }
        return result.toString();
    }

    @VisibleForTesting
    Position locateTarget(int n, char[][] matrix, char target) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (matrix[y][x] == target) {
                    return new Position(x, y);
                }
            }
        }
        throw new RuntimeException("Target (" + target + ") is missing!");
    }

    public Move move(Position bot, Position peach) {
        String move = null;

        // do the up/down moves first
        if (bot.y > peach.y) {
            move = up_move;
            bot.y--;
        } else if (bot.y < peach.y) {
            move = down_move;
            bot.y++;
        } else if (bot.x > peach.x) {
            move = left_move;
            bot.x--;
        } else {
            move = right_move;
            bot.x++;
        }

        return new Move(bot, move);
    }

    static class Position {
        int x, y;

        Position(int _x, int _y) {
            x = _x;
            y = _y;
        }

        public boolean equals(Object o) {
            if (o instanceof Position) {
                Position p = (Position) o;
                return ((p.x == x) && (p.y == y));
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return x * y;
        }
    }

    static class Move {
        Position newPosition;
        String lastMove;

        Move(Position p, String m) {
            newPosition = p;
            lastMove = m;
        }
    }
}
