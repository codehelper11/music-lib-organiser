package audio.file.rules;

import com.google.common.base.Preconditions;
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v2.ID3V2Tag;
import org.blinkenlights.jid3.v2.ID3V2_3_0Tag;

import java.io.File;

public class UpdateTrackPropertyWithFileName implements Rule {

    private static final String AUDIO_FORMAT = ".mp3";

    @Override
    public void apply(File file) {
        System.out.println("processing file " + file.getAbsoluteFile());
        Preconditions.checkNotNull(file, "File is required");
        Preconditions.checkState(file.isFile(), "Not a valid file");

        if (isAudioFile(file)) {
            MediaFile mediaFile = new MP3File(file);
            try {
                String oldValue = mediaFile.getID3V2Tag() != null ? mediaFile.getID3V2Tag().getTitle() : "";
                if (oldValue == null || oldValue.toLowerCase().contains("track") || oldValue.trim().isEmpty()) {
                    ID3V2Tag tag = mediaFile.getID3V2Tag();
                    if (tag == null) {
                        tag = new ID3V2_3_0Tag();
                    }
                    tag.setTitle(getFileNameWithoutAudioExtension(file));
                    tag.setExtendedHeader(true);
                    mediaFile.setID3Tag(tag);
                    mediaFile.sync();
                    System.out.println(this.getClass().getSimpleName() + ": " +
                            String.format("For file [%1$s] replaced property value [%2$s] with [%3$s]", file.getAbsolutePath(), oldValue,
                                    getFileNameWithoutAudioExtension(file)));
                } else {
                    System.err.println(this.getClass().getSimpleName() + ": " +
                            String.format("For file [%1$s] property value is [%2$s], not updating", file.getAbsolutePath(), oldValue));
                }

            } catch (ID3Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isAudioFile(File file) {
        return file.getName().toLowerCase().endsWith(".mp3");
    }

    private String getFileNameWithoutAudioExtension(File file) {
        return file.getName().substring(0, file.getName().length() - AUDIO_FORMAT.length());
    }
}
