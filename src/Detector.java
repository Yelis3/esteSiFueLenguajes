
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;


public class Detector {

    public void detect(String [] auxArray) throws Exception {
        String input = "input.txt";
        Path path = Paths.get(input);
        List<String> lines = new LinkedList<String>();
        for(String x:auxArray)
            lines.add(x);
        System.out.println(lines);
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pythonLexer lexer = new pythonLexer(CharStreams.fromFileName("input.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        pythonParser parser = new pythonParser(tokens);
        ParseTree tree = parser.file_input();//poner el elemento root de la gramatica

        //  Unused Parameters Smell
        LinkedList<couple<couple<String, Integer>, ArrayList<couple<String, Integer> > > > datos1 =
                new LinkedList<couple<couple<String, Integer>, ArrayList<couple<String, Integer> > > >();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new unusedParameterDetector1(datos1), tree);
        for(couple<couple<String, Integer>, ArrayList<couple<String, Integer> > >  x : datos1)
            System.out.println(x.toString());

        LinkedList<Integer[]>[] datos2 = new LinkedList[datos1.size()];
        for(int i=0; i< datos1.size(); i++)
            datos2[i] = new LinkedList<Integer[]>();
        unusedParameterDetector2<Object> loader = new unusedParameterDetector2<Object>(datos1, datos2);
        loader.visit(tree);
        for(int i=0; i<datos2.length; i++)
            for (Integer[] x : datos2[i])
                System.out.println(Arrays.toString(x));

        new unusedParameterRefactor(datos1, datos2);
        unusedParameterRefactor.generateOutputCode("input.txt", "output.txt");

        //  Duplicate Conditional Fragments Smell
//        LinkedList<LinkedList<LinkedList<couple<String, Integer>> > > datos3 = new LinkedList<LinkedList<LinkedList<couple<String, Integer>> > >();
//        walker.walk(new duplicateConditionalFragmentsDetector(datos3), tree);
//
//        for(LinkedList<LinkedList<couple<String, Integer>> > x : datos3){
//            for(LinkedList<couple<String, Integer>> y : x )
//        }


        //  Long Parameter List Smell
        LinkedList<couple<couple<String, Integer>, LinkedList<String> > > datos5 = new LinkedList<couple<couple<String, Integer>, LinkedList<String> > >();
        int limit = 5;

        longParameterListDetector<Object> loader2 = new longParameterListDetector<Object>(datos5, limit);
        loader2.visit(tree);


        for (couple<couple<String, Integer>, LinkedList<String>> x : datos5)
            System.out.println(x.toString());

    }
}