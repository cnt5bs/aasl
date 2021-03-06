
package gram;

import gram.antlr4.AASLLexer;
import gram.antlr4.AASLParser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args ) {
        Main main = new Main();
        main.processar( "test/af.aal" );
    }
    
private void processar(String espec) {
    InputStream is = null;
    try {
        is = new FileInputStream(espec);
        ANTLRInputStream input = new ANTLRInputStream(is);
        AASLLexer lexer = new AASLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AASLParser parser = new AASLParser(tokens);
        AASLParser.AdaptiveAutomatonContext contexto = parser.adaptiveAutomaton();
        ParseTreeWalker analisador = new ParseTreeWalker();
        AutomatonBuilder construtor = new AutomatonBuilder();
        analisador.walk(construtor, contexto);
        System.out.println(construtor.automaton);
    } catch (IOException ex) {
        System.out.println("** FATAL! Problema de acesso ao arquivo: " + espec);
        System.exit(0);
    } finally {
        try {
            is.close();
        } catch (IOException ex) {
            System.out.println("** FATAL! Problema ao fechar o arquivo: " + espec);
            System.exit(0);
        }
    }
}

}
