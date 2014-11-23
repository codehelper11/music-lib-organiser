package audio.file;

import audio.file.rules.Rule;
import com.google.common.base.Preconditions;

import java.io.File;


public class FileOperation {

    public void applyOperationOnEachFileInDirectory(File directory, boolean isRecursive, Rule... rules) {
        Preconditions.checkNotNull(directory, "directory missing");
        Preconditions.checkArgument(directory.isDirectory(), "Not a valid directory");


        for (Rule rule : rules) {
            applyRuleToFiles(directory, rule);
        }
    }

    private void applyRuleToFiles(File directory, Rule rule) {
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                try {
                    rule.apply(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (file.isDirectory()) {
                applyRuleToFiles(file, rule);
            }
        }
    }
}
