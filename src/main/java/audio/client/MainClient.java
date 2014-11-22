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
                new File("F:\\songs\\old is gold\\Kishore & Lata"),
                true,
                new Rule[]{
                        //new RemoveExtraCharsFromFileName(),
                        //new UpdateAlbumPropertyWithGivenValue("oldies_kishore&Lata"),
                        //new UpdateGenrePropertyWithGivenValue("oldies"),
                        //new UpdateTrackPropertyWithFileName(),
                        new UpdateArtistPropertyWithGivenValue("Kishore&Lata")
                });

    }
}
