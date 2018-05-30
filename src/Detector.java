
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.jvnet.ws.wadl.Link;

import java.util.ArrayList;
import java.util.LinkedList;

public class Detector {

    public static void main(String [] args) throws Exception{
        pythonLexer lexer = new pythonLexer(CharStreams.fromFileName("input.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        pythonParser parser = new pythonParser(tokens);
        ParseTree tree = parser.file_input();//poner el elemento root de la gramatica

        //  Unused Parameters Smell
        LinkedList<couple<couple<String, Integer>, ArrayList<couple<String, Integer> > > > datos1 =
                new LinkedList<couple<couple<String, Integer>, ArrayList<couple<String, Integer> > > >();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new unusedParameterDetector1(datos1), tree);

        LinkedList<Integer[]>[] datos2 = new LinkedList[datos1.size()];
        for(int i=0; i< datos1.size(); i++)
            datos2[i] = new LinkedList<Integer[]>();
        unusedParameterDetector2<Object> loader = new unusedParameterDetector2<Object>(datos1, datos2);
        loader.visit(tree);

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


//        for (couple<couple<String, Integer>, LinkedList<String>> x : datos5)
//            System.out.println(x.toString());

    }
}