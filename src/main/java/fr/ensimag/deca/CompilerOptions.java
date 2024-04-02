package fr.ensimag.deca;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * User-specified options influencing the compilation.
 *
 * @author gl31
 * @date 01/01/2024
 */
public class CompilerOptions {
    public static final int QUIET = 0;
    public static final int INFO  = 1;
    public static final int DEBUG = 2;
    public static final int TRACE = 3;

    public int getDebug() {
        return debug;
    }

    public int getRegLimit() {
        return registers;
    }

    public boolean getParallel() {
        return parallel;
    }

    public boolean getPrintBanner() {
        return printBanner;
    }

    public boolean getParser() {
        return parser;
    }

    public boolean getVerify() {
        return verification;
    }

    public boolean getNoCheck() {
        return noCheck;
    }
    
    public List<File> getSourceFiles() {
        return Collections.unmodifiableList(sourceFiles);
    }

    private boolean verifyFileName(String fileName){
        return fileName.endsWith(".deca");
    }

    private int debug = 0;
    private int registers = -1;
    private boolean parallel = false;
    private boolean printBanner = false;
    private boolean parser = false;
    private boolean verification = false;
    private boolean noCheck = false;
    private List<File> sourceFiles = new ArrayList<File>();

    
    public void parseArgs(String[] args) throws CLIException {
        if (args.length == 0){
            displayUsage();
            System.exit(0);
        } else if(args.length == 1) {
            if(args[0].equals("-b")) {
                printBanner = true;

            } else if(verifyFileName(args[0])) {
                sourceFiles.add(new File(args[0]));

            } else {
                System.err.println("error : Invalid options for decac");
                this.displayUsage();
                System.exit(1);
            }

        } else {
            for(int i = 0; i < args.length; i++) {
                switch(args[i]) {
                    case "-p" :
                        if(!verification) {
                            parser = true;
                        } else {
                            throw new CLIException("error : Les options '-p' et '-v' sont incompatibles");
                        }
                        break;
                    case "-v" :
                        if(!parser) {
                            verification = true;
                        } else {
                            throw new CLIException("error : Les options '-p' et '-v' sont incompatibles");
                        }
                        break;
                    case "-n" :
                        noCheck = true;
                        break;
                    case "-r" :
                        i++;
                        try {
                            registers = Integer.parseInt(args[i]);
                        } catch(NumberFormatException e) {
                            throw new CLIException("error : -r X : X doit etre integer");
                        }
                        break;
                    case "-d" :
                        debug++;
                        break;
                    case "-P" :
                        parallel = true;
                        break;
                    default :
                            if(verifyFileName(args[i])) {
                                sourceFiles.add(new File(args[i]));

                            } else {
                                throw new CLIException("error : Invalid options for decac");
                            }
                }
            }
            if(getRegLimit() != -1) {
                if(!((getRegLimit() <= 16) && (getRegLimit() >= 4))){
                    throw new CLIException("error : -r X : il faut que 4 <= X <= 16");           
                }
            }
        }
        Logger logger = Logger.getRootLogger();
        // map command-line debug option to log4j's level.
        switch (getDebug()) {
        case QUIET: break; // keep default
        case INFO:
            logger.setLevel(Level.INFO); break;
        case DEBUG:
            logger.setLevel(Level.DEBUG); break;
        case TRACE:
            logger.setLevel(Level.TRACE); break;
        default:
            logger.setLevel(Level.ALL); break;
        }
        logger.info("Application-wide trace level set to " + logger.getLevel());

        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect!!!
        if (assertsEnabled) {
            logger.info("Java assertions enabled");
        } else {
            logger.info("Java assertions disabled");
        }
    }

    protected void displayUsage() {
        System.out.println("Utilisation : decac [[-p | -v] [-n] [-r X] [-d]* [-P] [-w] <fichier deca>...] | [-b]");
        System.out.println("Options :");
        System.out.println("  -b (banner) : affiche une bannière indiquant le nom de l'équipe");
        System.out.println("  -p (parse) : arrête decac après l'étape de construction de l'arbre, et affiche la décompilation de ce dernier");
        System.out.println("  -v (verification) : arrête decac après l'étape de vérifications (ne produit aucune sortie en l'absence d'erreur)");
        System.out.println("  -n (no check) : supprime les tests à l'exécution spécifiés dans les points 11.1 et 11.3 de la sémantique de Deca.");
        System.out.println("  -r X (registers) : limite les registres banalisés disponibles à R0 ... R{X-1}, avec 4 <= X <= 16");
        System.out.println("  -d (debug) : active les traces de debug. Répéter l'option plusieurs fois pour avoir plus de traces.");
        System.out.println("  -P (parallel) : s'il y a plusieurs fichiers sources, lance la compilation des fichiers en parallèle (pour accélérer la compilation)");
        System.out.println("NB : Les fichiers doivent se terminer par l'extension '.deca'.");
    }
}
