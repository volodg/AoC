package aoc16.d01;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Day01 {

    enum Direction { North, West, South, East };
    final Direction [] list = { Direction.North, Direction.West, Direction.South, Direction.East };

    static AbstractMap.SimpleEntry<Integer,Integer> point( int x, int y ) {
        return new AbstractMap.SimpleEntry<Integer,Integer>( x, y );
    }

    @Test
    public void part12() throws IOException {
        final var input = Files.readString( Path.of("/Users/fedoy/projects/AoC/AoC16/src/test/java/aoc16/d01/input.txt") );
        // final var input = "R8, R4, R4, R8";
        final var c = input.split(", ");
        Direction d = Direction.North;
        int x = 0;
        int y = 0;
        final var visited = new HashSet< AbstractMap.SimpleEntry<Integer,Integer>>();
        visited.add( point(x,y) );
        int answer2 = -1;
        for ( var cc : c ) {
            final boolean isLeft = cc.charAt(0) == 'L';
            final int index = Arrays.binarySearch( list, d );
            int newIndex = index + ( isLeft ? -1 : 1 );
            newIndex += list.length;
            newIndex %= list.length;
            final var nd = list[newIndex];
            final int dx = nd == Direction.East ? 1 : nd == Direction.West ? -1 : 0;
            final int dy = nd == Direction.North ? 1 : nd == Direction.South ? -1 : 0;
            final int delta = Integer.parseInt( cc.substring(1).trim() );
            for ( int i = 1; i <= delta; i++ ) {
                final var x1 = x + ( dx * i );
                final var y1 = y + ( dy * i );
                if ( !visited.add( point(x1,y1) ) && answer2 < 0 ) {
                    answer2 = Math.abs(x1) + Math.abs(y1);
                }
            }
            x += ( dx * delta );
            y += ( dy * delta );
            d = nd;
        }
        assertEquals( "answer 1", 291, x + y );
        assertEquals( "answer 2", 159, answer2 ); // 274 -- to high
    }
}