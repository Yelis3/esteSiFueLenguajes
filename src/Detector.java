
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Detector {

    public static void main(String [] args) throws Exception{
        pythonLexer lexer = new pythonLexer(CharStreams.fromFileName("input.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        pythonParser parser = new pythonParser(tokens);
        ParseTree tree = parser.file_input();//poner el elemento root de la gramatica


        LinkedList<couple<couple<String, Integer>, ArrayList<couple<String, Integer> > > > couples =
                new LinkedList<couple<couple<String, Integer>, ArrayList<couple<String, Integer> > > >();


        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new unusedParameter(couples), tree);

        for(couple<couple<String, Integer>, ArrayList<couple<String, Integer> > >  x : couples){
            System.out.println(x.toString());
        }

    }
}