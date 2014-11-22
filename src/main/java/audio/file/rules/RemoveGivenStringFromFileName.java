package audio.file.rules;

import com.google.common.base.Preconditions;

import java.io.File;

public class RemoveGivenStringFromFileName implements Rule {

    private final String textToReplace;
    private static final String EMPTY = "";
    private static final String AUDIO_FORMAT = ".mp3";

    public RemoveGivenStringFromFileName(String textToReplace) {
        this.textToReplace = textToReplace;
    }

    @Override
    public void apply(File file) {
        Preconditions.checkNotNull(file, "File is required");
        Preconditions.checkState(file.isFile(), "Not a valid file");

        if (isAudioFile(file)) {
            String newFileName = file.getName().replace(textToReplace, EMPTY).trim();
            renameFile(file, newFileName);
            System.out.println(this.getClass().getSimpleName() + ": " + String.format("replaced [%1$s] with [%2$s]", file.getName(), newFileName));
        } else {
            System.err.println(this.getClass().getSimpleName() + ": " + String.format("name format [%1$s] not supported", file.getName()));
        }
    }

    private void renameFile(File file, String newFileName) {
        file.renameTo(new File(file.getAbsolutePath().replace(file.getName(), newFileName)));
    }

    private boolean isAudioFile(File file) {
        return file.getName().toLowerCase().endsWith(".mp3");
    }
}
