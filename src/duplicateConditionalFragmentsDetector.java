import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class duplicateConditionalFragmentsDetector extends pythonBaseListener{
    Boolean isInIf;
    int ifSize;
    int currIf;
    LinkedList<couple<String, Integer>> aux1;
    LinkedList<LinkedList<couple<String, Integer>> > aux2;
    LinkedList<LinkedList<LinkedList<couple<String, Integer>> > > datos1;
    public duplicateConditionalFragmentsDetector(LinkedList<LinkedList<LinkedList<couple<String, Integer>> > > datos1){
        System.out.println("Esto hace algo");
        this.isInIf = false;
        this.ifSize = 0;
        this.currIf = -1;
        this.aux1 = null;
        this.aux2 = null;
        this.datos1 = datos1;
    }

    @Override
    public void enterIf_stmt(pythonParser.If_stmtContext ctx) {
        List<TerminalNode> terminalNodes = new LinkedList<TerminalNode>();
        isInIf = true;
        ifSize = 0;
        currIf = -1;
        for(int i=0; i<ctx.getChildCount(); i++)
            if(ctx.getChild(i) instanceof  TerminalNode)
                terminalNodes.add((TerminalNode)ctx.getChild(i));
        for (int i=0; i<terminalNodes.size(); i++) {
            if(terminalNodes.get(i).toString().equals("if") || terminalNodes.get(i).toString().equals("elif") || terminalNodes.get(i).toString().equals("else"))
                ifSize++;
        }
        aux2 = new LinkedList<LinkedList<couple<String, Integer>>>();
    }

    @Override
    public void exitIf_stmt(pythonParser.If_stmtContext ctx) {
        datos1.add(aux2);
        isInIf = false;
        //super.exitIf_stmt(ctx);
    }

    @Override
    public void enterSuite(pythonParser.SuiteContext ctx) {
        if(isInIf){
            currIf++;
            aux1 = new LinkedList<couple<String, Integer>>();
            aux1.add(new couple<>("",ctx.NEWLINE().getSymbol().getLine()));
//            System.out.println(ctx.NEWLINE().getSymbol().getLine());
        }
//        super.enterSuite(ctx);

    }

    @Override
    public void exitSuite(pythonParser.SuiteContext ctx) {
        if(isInIf) {
            aux2.add(aux1);
        }
//        super.exitSuite(ctx);
    }


}
