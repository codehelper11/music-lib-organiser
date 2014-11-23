package audio.file.rules;

import com.google.common.base.Preconditions;

import java.io.File;

public class PrintFileHavingGivenText implements Rule {

    private String textToSearch;

    public PrintFileHavingGivenText(String textToSearch) {
        this.textToSearch = textToSearch;
    }

    @Override
    public void apply(File file) {
        Preconditions.checkNotNull(file, "File is required");
        Preconditions.checkState(file.isFile(), "Not a valid file");
        if (file.getName().toLowerCase().contains(textToSearch.toLowerCase())) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
