package audio.client;

import audio.file.FileOperation;
import audio.file.rules.*;

import java.io.File;

public class MainClient {

    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------WELCOME-----------------------");
        System.out.println("-----------------------------------------------");

        new FileOperation().applyOperationOnEachFileInDirectory(
                new File("F:\\songs\\hindi movie songs"),
                true,
                new Rule[]{
                        //new RemoveExtraCharsFromFileName()
                        //new UpdateAlbumPropertyWithGivenValue("oldies_kishore&Lata"),
                        //new UpdateGenrePropertyWithGivenValue("gazals"),
                        //new UpdateTrackPropertyWithFileName()
                        //new UpdateArtistPropertyWithGivenValue("John Denver")
                        //new PrintFileHavingGivenText("track")
                        new UpdateAlbumPropertyWithFolderName()
                });

    }
}
