package si.ai.othello.game;

import org.junit.Before;
import org.junit.Test;
import si.ai.othello.game.utils.Pointer;

import java.util.Arrays;
import java.util.Collections;
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


    //getAvailableMoves test
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
        Pointer leftUp = new Pointer(0, 0);
        Pointer rightUp = new Pointer(7, 0);
        Pointer leftDown = new Pointer(0, 7);
        Pointer rightDown = new Pointer(7, 7);

        board.setConeAt(leftUp, WHITE);
        board.setConeAt(leftDown, WHITE);
        board.setConeAt(rightDown, WHITE);
        board.setConeAt(rightUp, WHITE);

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
            board.setConeAt(new Pointer(i, 0), WHITE);
        }

        board.setConeAt(new Pointer(0, 0), BLACK);

        Pointer[] moves = board.getAvailableMoves(BLACK);

        assertEquals(moves.length, 5);
        List<Pointer> list = Stream.of(
                new Pointer(3, 2),
                new Pointer(2, 3),
                new Pointer(5, 4),
                new Pointer(4, 5),
                new Pointer(7, 0))
                .collect(Collectors.toList());

        assertTrue(Arrays.stream(moves)
                .allMatch(list::contains));

    }

    @Test
    public void getAvailableMovesLongHorizontalWithoutMove() {
        for (int i = 0; i < 7; i++) {
            board.setConeAt(new Pointer(i, 0), WHITE);
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
            board.setConeAt(new Pointer(i, i), WHITE);
        }

        //initial cones hotfix
        board.setConeAt(new Pointer(3, 3), WHITE);
        board.setConeAt(new Pointer(3, 4), WHITE);
        board.setConeAt(new Pointer(4, 4), WHITE);
        board.setConeAt(new Pointer(4, 3), WHITE);

        Pointer[] moves = board.getAvailableMoves(BLACK);

        assertEquals(moves.length, 0);
    }

    @Test
    public void getAvailableMovesLongDiagonalWithMove() {
        for (int i = 1; i < 7; i++) {
            board.setConeAt(new Pointer(i, i), WHITE);
        }

        board.setConeAt(new Pointer(0, 0), BLACK);

        //initial cones hotfix
        board.setConeAt(new Pointer(3, 3), WHITE);
        board.setConeAt(new Pointer(3, 4), WHITE);
        board.setConeAt(new Pointer(4, 4), WHITE);
        board.setConeAt(new Pointer(4, 3), WHITE);

        Pointer[] moves = board.getAvailableMoves(BLACK);

        assertEquals(moves.length, 1);
        List<Pointer> list = Stream.of(
                new Pointer(7, 7))
                .collect(Collectors.toList());

        assertTrue(Arrays.stream(moves)
                .allMatch(list::contains));

    }


    //Move test
    @Test
    public void moveAtStartingTest() {
        board.moveAt(new Pointer(3, 2), BLACK);

        List<Pointer> blacks = Stream.of(
                new Pointer(3, 2),
                new Pointer(3, 3),
                new Pointer(4, 3),
                new Pointer(3, 4))
                .collect(Collectors.toList());

        List<Pointer> whites = Stream.of(
                new Pointer(4, 4))
                .collect(Collectors.toList());

        assertTrue(blacks.stream()
                .allMatch(pointer -> board.getValueAt(pointer) == BLACK)
        );
        assertTrue(whites.stream()
                .allMatch(pointer -> board.getValueAt(pointer) == WHITE)
        );

        board.updateCones();
        assertEquals(blacks.size(), board.getCurrentBlack());
        assertEquals(whites.size(), board.getCurrentWhite());
    }

    @Test
    public void moveAtLongHorizontal() {
        for (int i = 1; i < 7; i++) {
            board.setConeAt(new Pointer(i, 0), WHITE);
        }

        board.setConeAt(new Pointer(0, 0), BLACK);

        //move
        board.moveAt(new Pointer(7, 0), BLACK);

        List<Pointer> blacks = Stream.of(
                new Pointer(0, 0),
                new Pointer(1, 0),
                new Pointer(2, 0),
                new Pointer(3, 0),
                new Pointer(4, 0),
                new Pointer(5, 0),
                new Pointer(6, 0),
                new Pointer(7, 0),
                new Pointer(4, 3),
                new Pointer(3, 4))
                .collect(Collectors.toList());

        List<Pointer> whites = Stream.of(
                new Pointer(3, 3),
                new Pointer(4, 4))
                .collect(Collectors.toList());

        //assert
        assertTrue(blacks.stream()
                .allMatch(pointer -> board.getValueAt(pointer) == BLACK)
        );
        assertTrue(whites.stream()
                .allMatch(pointer -> board.getValueAt(pointer) == WHITE)
        );

        board.updateCones();
        assertEquals(blacks.size(), board.getCurrentBlack());
        assertEquals(whites.size(), board.getCurrentWhite());
    }


    @Test
    public void moveAtLongDiagonalTest() {
        for (int i = 1; i < 7; i++) {
            board.setConeAt(new Pointer(i, i), WHITE);
        }

        board.setConeAt(new Pointer(0, 0), BLACK);

        //initial cones hotfix
        board.setConeAt(new Pointer(3, 3), WHITE);
        board.setConeAt(new Pointer(3, 4), WHITE);
        board.setConeAt(new Pointer(4, 4), WHITE);
        board.setConeAt(new Pointer(4, 3), WHITE);


        //move
        board.moveAt(new Pointer(7, 7), BLACK);


        //assert
        List<Pointer> blacks = Stream.of(
                new Pointer(0, 0),
                new Pointer(1, 1),
                new Pointer(2, 2),
                new Pointer(3, 3),
                new Pointer(4, 4),
                new Pointer(5, 5),
                new Pointer(6, 6),
                new Pointer(7, 7))
                .collect(Collectors.toList());

        List<Pointer> whites = Stream.of(
                new Pointer(4, 3),
                new Pointer(3, 4))
                .collect(Collectors.toList());

        //assert
        assertTrue(blacks.stream()
                .allMatch(pointer -> board.getValueAt(pointer) == BLACK)
        );
        assertTrue(whites.stream()
                .allMatch(pointer -> board.getValueAt(pointer) == WHITE)
        );

        board.updateCones();
        assertEquals(blacks.size(), board.getCurrentBlack());
        assertEquals(whites.size(), board.getCurrentWhite());
    }

    @Test
    public void moveAtManyDirectionsTest() {
        //initial cones hotfix
        board.setConeAt(new Pointer(3, 3), WHITE);
        board.setConeAt(new Pointer(3, 4), WHITE);
        board.setConeAt(new Pointer(4, 3), WHITE);

        board.setConeAt(new Pointer(4, 2), BLACK);
        board.setConeAt(new Pointer(2, 2), BLACK);
        board.setConeAt(new Pointer(2, 4), BLACK);


        board.moveAt(new Pointer(4, 4), BLACK);

        //assert
        List<Pointer> blacks = Stream.of(
                new Pointer(2, 2),
                new Pointer(3, 3),
                new Pointer(4, 4),
                new Pointer(4, 2),
                new Pointer(4, 3),
                new Pointer(2, 4),
                new Pointer(3, 4))
                .collect(Collectors.toList());

        List<Pointer> whites = Collections.emptyList();

        //assert
        assertTrue(blacks.stream()
                .allMatch(pointer -> board.getValueAt(pointer) == BLACK)
        );
        assertTrue(whites.stream()
                .allMatch(pointer -> board.getValueAt(pointer) == WHITE)
        );

        board.updateCones();
        assertEquals(blacks.size(), board.getCurrentBlack());
        assertEquals(whites.size(), board.getCurrentWhite());
    }
}