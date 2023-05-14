package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:17
 **/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class P36_PrintQueue {
    public static void main( String[] args )
    {
        /* 处理输入 */
        Scanner scanner = new Scanner( System.in );
        int	count	= scanner.nextInt();
        scanner.nextLine();
        /* 创建5台打印机的打印清单 */
        List < TreeSet < ArrayList < Integer >>> printMachines = new ArrayList<>();
        for ( int i = 0; i < 5; i++ )
        {
            TreeSet<ArrayList<Integer> > printQueue = new TreeSet<>( new Comparator<ArrayList<Integer> >()
            {
                @Override
                public int compare( ArrayList<Integer> o1, ArrayList<Integer> o2 )
                {
                    int result = o2.get( 0 ) - o1.get( 0 );
                    if ( result == 0 )
                    {
                        return(o1.get( 1 ) - o2.get( 1 ) );
                    }
                    return(result);
                }
            } );
            printMachines.add( printQueue );
        }

        int fileCount = 0;
        for ( int i = 0; i < count; i++ )
        {
            String[] operationInfo = scanner.nextLine().split( " " );

            if ( operationInfo[0].equals( "IN" ) )
            {
                fileCount++;
                ArrayList<Integer> file = new ArrayList<>();
                file.add( Integer.parseInt( operationInfo[2] ) );
                file.add( fileCount );
                /* 放入文件 */
                printMachines.get( Integer.parseInt( operationInfo[1] ) ).add( file );
            } else {
                /* 打印 */
                TreeSet<ArrayList<Integer> > printQueue = printMachines.get( Integer.parseInt( operationInfo[1] ) );
                if ( printQueue.size() > 0 )
                {
                    System.out.println( printQueue.first().get( 1 ) );
                    printQueue.pollFirst();
                } else {
                    System.out.println( "NULL" );
                }
            }
        }
    }
}

