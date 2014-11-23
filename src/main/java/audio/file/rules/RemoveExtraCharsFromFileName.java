package audio.file.rules;

import com.google.common.base.Preconditions;

import java.io.File;

public class RemoveExtraCharsFromFileName implements Rule {

    private static final String INVALID_CHARS = "[^A-Za-z -]";
    private static final String AUDIO_FORMAT = ".mp3";
    private static final String EMPTY = "";

    @Override
    public void apply(File file) {
        Preconditions.checkNotNull(file, "File is required");
        Preconditions.checkState(file.isFile(), "Not a valid file");

        if (isAudioFile(file) && !file.getName().toLowerCase().contains("track")) {
            String newFileName = removeExtraCharsFromName(file);
            if (isNameChanged(file.getName(), newFileName)) {
                renameFile(file, newFileName);
                printNameChangedMessage(file, newFileName);
            }
        } else {
            System.err.println(this.getClass().getSimpleName() + ": " + String.format("name format [%1$s] not supported", file.getName()));
        }

    }

    private boolean isAudioFile(File file) {
        return file.getName().toLowerCase().endsWith(".mp3");
    }

    private String removeExtraCharsFromName(File file) {
        return getFileNameWithoutAudioExtension(file).replaceAll(INVALID_CHARS, EMPTY).trim() + AUDIO_FORMAT;
    }

    private String getFileNameWithoutAudioExtension(File file) {
        return file.getName().substring(0, file.getName().length() - AUDIO_FORMAT.length());
    }

    private boolean isNameChanged(String oldName, String newName) {
        return !oldName.equals(newName);
    }

    private void renameFile(File file, String newFileName) {
        file.renameTo(new File(file.getAbsolutePath().replace(file.getName(), newFileName)));
    }

    private void printNameChangedMessage(File file, String newFileName) {
        System.out.println(this.getClass().getSimpleName() + ": " + String.format("replaced [%1$s] with [%2$s]", file.getName(), newFileName));
    }
}
