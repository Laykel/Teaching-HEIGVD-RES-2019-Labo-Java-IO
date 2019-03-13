package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {
        // Visit current directory
        visitor.visit(rootDirectory);

        // We stop there if the file doesn't exist
        if (!rootDirectory.exists())
            return;

        // Get list of files in directory
        File[] files = rootDirectory.listFiles();
        // Sort files so that we always visit them in the same order
        Arrays.sort(files);

        // Explore each node recursively
        for (File file : files) {
            // If node is a directory, call method recursively
            if (file.isDirectory()) {
                explore(file, visitor);
            }
            // If node is a file, visit it
            else if (file.isFile()) {
                visitor.visit(file);
            }
        }
    }

}
