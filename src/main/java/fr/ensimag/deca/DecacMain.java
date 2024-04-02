package fr.ensimag.deca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import org.apache.log4j.Logger;

/**
 * Main class for the command-line Deca compiler.
 *
 * @author gl31
 * @date 01/01/2024
 */
public class DecacMain {
    private static Logger LOG = Logger.getLogger(DecacMain.class);

    public static void main(String[] args) {
        // example log4j message.
        LOG.info("Decac compiler started");
        boolean error = false;
        final CompilerOptions options = new CompilerOptions();
        try {
            options.parseArgs(args);
        } catch (CLIException e) {
            System.err.println("Error during option parsing:\n"
                    + e.getMessage());
            options.displayUsage();
            System.exit(1);
        }

        if (options.getPrintBanner()) {
            String path = "./docs/developers";
            try {
                File file = new File(path);
                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                reader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }

        if (options.getSourceFiles().isEmpty()) {
            System.err.println("error : Invalid options for decac");
            options.displayUsage();
            System.exit(1);
        }

        if (options.getParallel()) {
            // A FAIRE : instancier DecacCompiler pour chaque fichier à
            // compiler, et lancer l'exécution des méthodes compile() de chaque
            // instance en parallèle. Il est conseillé d'utiliser
            // java.util.concurrent de la bibliothèque standard Java.
            ExecutorService executorService = Executors.newFixedThreadPool(options.getSourceFiles().size());
            List<Future<Boolean>> futures = new LinkedList<Future<Boolean>>();
            for (int i = 0; i < options.getSourceFiles().size(); i++) {
                final int j = i;
                futures.add(executorService.submit(() -> {
                    DecacCompiler compiler = new DecacCompiler(options, options.getSourceFiles().get(j));
                    if (options.getRegLimit() != 0) {
                        compiler.processor.setRmax(options.getRegLimit());
                    }
                    return compiler.compile();
                }));
            }
            executorService.shutdown();
            for (Future<Boolean> future : futures) {
                try {
                    System.out.println(future.get());
                    if (future.get()) {
                        System.exit(1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.exit(0);

        } else {
            for (File source : options.getSourceFiles()) {
                DecacCompiler compiler = new DecacCompiler(options, source);
                if (options.getRegLimit() != -1) {
                    compiler.processor.setRmax(options.getRegLimit());
                }
                if (compiler.compile()) {
                    error = true;
                }
            }
            System.exit(error ? 1 : 0);
        }
    }
}
