package si.ai.othello.game;

import org.junit.Before;
import org.junit.Test;
import si.ai.othello.game.utils.Pointer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static si.ai.othello.game.Game.*;

/**
 * @author Jakub Licznerski
 *         Created on 23.05.2017.
 */
public class BoardTest {

    private Board board;


    @Before
    public void init() {
        board = new Board(new Game(null, null, null));
    }

    @Test
    public void setInitialConesTest() {
        assertTrue(board.getValueAt(new Pointer(3, 3)));
        assertFalse(board.getValueAt(new Pointer(3, 4)));
        assertTrue(board.getValueAt(new Pointer(4, 4)));
        assertFalse(board.getValueAt(new Pointer(4, 3)));
    }

    @Test
    public void getAvailableMovesWhiteTest() {
        Pointer[] moves = board.getAvailableMoves(WHITE);
        assertEquals(moves.length, 4);

        List<Pointer> list = Stream.of(
                new Pointer(4, 2),
                new Pointer(5, 3),
                new Pointer(2, 4),
                new Pointer(3, 5))
                .collect(Collectors.toList());

        assertTrue(Arrays.stream(moves)
                .allMatch(list::contains));
    }

    @Test
    public void getAvailableMovesBlackTest() {
        Pointer[] moves = board.getAvailableMoves(BLACK);
        assertEquals(moves.length, 4);

        List<Pointer> list = Stream.of(
                new Pointer(3, 2),
                new Pointer(2, 3),
                new Pointer(5, 4),
                new Pointer(4, 5))
                .collect(Collectors.toList());

        assertTrue(Arrays.stream(moves)
                .allMatch(list::contains));
    }

    @Test
    public void getAvailableMovesCornerTest() {
        Pointer leftUp = new Pointer(0,0);
        Pointer rightUp = new Pointer(7,0);
        Pointer leftDown = new Pointer(0,7);
        Pointer rightDown = new Pointer(7,7);

        board.setConeAt(WHITE, leftUp);
        board.setConeAt(WHITE, leftDown);
        board.setConeAt(WHITE, rightDown);
        board.setConeAt(WHITE, rightUp);

        Pointer[] moves = board.getAvailableMoves(BLACK);

        assertEquals(moves.length, 4);
        List<Pointer> list = Stream.of(
                new Pointer(3, 2),
                new Pointer(2, 3),
                new Pointer(5, 4),
                new Pointer(4, 5))
                .collect(Collectors.toList());

        assertTrue(Arrays.stream(moves)
                .allMatch(list::contains));
    }

    @Test
    public void getAvailableMovesLongHorizontalWithMove() {
        for (int i = 1; i < 7; i++) {
            board.setConeAt(WHITE, new Pointer(i, 0));
        }

        board.setConeAt(BLACK, new Pointer(0,0));

        Pointer[] moves = board.getAvailableMoves(BLACK);

        assertEquals(moves.length, 5);
        List<Pointer> list = Stream.of(
                new Pointer(3, 2),
                new Pointer(2, 3),
                new Pointer(5, 4),
                new Pointer(4, 5),
                new Pointer(7,0))
                .collect(Collectors.toList());

        assertTrue(Arrays.stream(moves)
                .allMatch(list::contains));

    }

    @Test
    public void getAvailableMovesLongHorizontalWithoutMove() {
        for (int i = 0; i < 7; i++) {
            board.setConeAt(WHITE, new Pointer(i, 0));
        }

        Pointer[] moves = board.getAvailableMoves(BLACK);

        assertEquals(moves.length, 4);
        List<Pointer> list = Stream.of(
                new Pointer(3, 2),
                new Pointer(2, 3),
                new Pointer(5, 4),
                new Pointer(4, 5))
                .collect(Collectors.toList());

        assertTrue(Arrays.stream(moves)
                .allMatch(list::contains));

    }

    @Test
    public void getAvailableMovesLongDiagonalWithoutMove() {
        for (int i = 0; i < 7; i++) {
            board.setConeAt(WHITE, new Pointer(i, i));
        }

        //initial cones hotfix
        board.setConeAt(WHITE, new Pointer(3, 3));
        board.setConeAt(WHITE, new Pointer(3, 4));
        board.setConeAt(WHITE, new Pointer(4, 4));
        board.setConeAt(WHITE, new Pointer(4, 3));

        Pointer[] moves = board.getAvailableMoves(BLACK);

        assertEquals(moves.length, 0);
    }

    @Test
    public void getAvailableMovesLongDiagonalWithMove() {
        for (int i = 1; i < 7; i++) {
            board.setConeAt(WHITE, new Pointer(i, i));
        }

        board.setConeAt(BLACK, new Pointer(0,0));

        //initial cones hotfix
        board.setConeAt(WHITE, new Pointer(3, 3));
        board.setConeAt(WHITE, new Pointer(3, 4));
        board.setConeAt(WHITE, new Pointer(4, 4));
        board.setConeAt(WHITE, new Pointer(4, 3));

        Pointer[] moves = board.getAvailableMoves(BLACK);

        assertEquals(moves.length, 1);
        List<Pointer> list = Stream.of(
                new Pointer(7,7))
                .collect(Collectors.toList());

        assertTrue(Arrays.stream(moves)
                .allMatch(list::contains));

    }
}