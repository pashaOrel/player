package com.nc_player.MetaData;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.nc_player.hibernate.model.AlbumEntity;
import com.nc_player.hibernate.model.ArtistEntity;
import com.nc_player.hibernate.model.TrackEntity;
import com.nc_player.hibernate.utils.Instance;
import com.nc_player.hibernate.utils.Metadata;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by INDIGO-ПС on 09.04.2016.
 */
public class AudioParser {
    private static final String JBOSS_PATH = "http://jbossewstestappplaye-rsatestapp.rhcloud.com";
    private static final String DEFAULT_COVER_PATH = "http://jbossewstestappplaye-rsatestapp.rhcloud.com/data/files/cover.jpg";
    private static Logger logger = Logger.getLogger(AudioParser.class);
    public void checkMetadata() {
        logger.info("checkmetadta");
        ArrayList<File> files = new CheckUpdateFolder().getNewFiles();
        logger.info("new get files : "+ files.size());
        ArrayList<Metadata> metadataList = new ArrayList<>();
        if (files.size() > 0) {
            for (File file : files) {
                try {
                    MP3File mp3File = (MP3File) AudioFileIO.read(file);
                    ID3v24Tag v24tag = (ID3v24Tag) mp3File.getID3v2TagAsv24();
                    if (!v24tag.getFirst(ID3v24FieldKey.TITLE).isEmpty()) {
                        logger.info(file.getName() + " title in not empty");
                        String filePath = JBOSS_PATH + file.getAbsolutePath().substring(52);
                        //пустой артист добавляем только в конструктор трек
                        if (v24tag.getFirst(ID3v24FieldKey.ARTIST).isEmpty()) {
                            logger.info(file.getName() + " artist is empty");
                            if (v24tag.getFirst(ID3v24FieldKey.GENRE).isEmpty()) {
                                metadataList.add(new Metadata(new TrackEntity(v24tag.getFirst(ID3v24FieldKey.TITLE), filePath)));
                                logger.info(file.getName() + " add in collection artist and ganre is empty ");
                            } else {
                                metadataList.add(new Metadata(new TrackEntity(v24tag.getFirst(ID3v24FieldKey.TITLE), filePath, v24tag.getFirst(ID3v24FieldKey.GENRE), false)));
                                logger.info(file.getName() + " add in collection artist is empty but  ganre is not empty ");
                            }
                        }
                        // артист не пустой проверяем поле альбом
                        else {
                            //поле альбом пустое добавлем в конструктор трек + артист
                            logger.info(file.getName() + " artist is not  empty");
                            if (v24tag.getFirst(ID3v24FieldKey.ALBUM).isEmpty()) {
                                logger.info(file.getName() + " album is  empty");
                                if (v24tag.getFirst(ID3v24FieldKey.GENRE).isEmpty()) {
                                    metadataList.add(new Metadata(new TrackEntity(v24tag.getFirst(ID3v24FieldKey.TITLE), filePath, v24tag.getFirst(ID3v24FieldKey.ARTIST), true),
                                            new ArtistEntity(v24tag.getFirst(ID3v24FieldKey.ARTIST))));
                                    logger.info(file.getName() + " add in collection artist is not empty but ganre and album is empty ");
                                } else {
                                    metadataList.add(new Metadata(new TrackEntity(v24tag.getFirst(ID3v24FieldKey.TITLE), filePath, v24tag.getFirst(ID3v24FieldKey.ARTIST), v24tag.getFirst(ID3v24FieldKey.GENRE)),
                                            new ArtistEntity(v24tag.getFirst(ID3v24FieldKey.ARTIST))));
                                    logger.info(file.getName() + " add in collection artist and ganre is not empty but album is empty ");
                                }
                            }
                            // поле альбом не пустое добавляем в конструктор трек + артист + альбом
                            else {
                                logger.info(file.getName() + " album is not empty");
                                String coverPath;
                                if (new File(file.getParentFile() + "/cover.png").exists()) {
                                    coverPath = JBOSS_PATH + file.getParent().substring(52) + "/cover.png";
                                    logger.info(file.getName() + " file cover.png is exist, add exist path ");
                                } else {
                                    String path = getImage(file);
                                    if (path==null){
                                        logger.info(" can't get image from file " + file.getName() + " " +new Date());
                                        coverPath = null;
                                    }else {
                                        logger.info("get image from file " + file.getName() + " " +new Date());
                                        coverPath = JBOSS_PATH + path.substring(52);
                                    }
                                }
                                if (v24tag.getFirst(ID3v24FieldKey.GENRE).isEmpty()) {
                                    logger.info(file.getName() + " ganre is  empty");
                                    if (coverPath == null ) {
                                        metadataList.add(new Metadata(new TrackEntity(v24tag.getFirst(ID3v24FieldKey.TITLE), filePath, v24tag.getFirst(ID3v24FieldKey.ARTIST), true),
                                                new AlbumEntity( DEFAULT_COVER_PATH, v24tag.getFirst(ID3v24FieldKey.ALBUM))
                                                , new ArtistEntity(v24tag.getFirst(ID3v24FieldKey.ARTIST))));
                                        logger.info(file.getName() + " add in collection artist and album is not empty but genre is empty and coverPath == null ");

                                    } else {
                                        metadataList.add(new Metadata(new TrackEntity(v24tag.getFirst(ID3v24FieldKey.TITLE), filePath, v24tag.getFirst(ID3v24FieldKey.ARTIST), true),
                                                new AlbumEntity(coverPath, v24tag.getFirst(ID3v24FieldKey.ALBUM))
                                                , new ArtistEntity(v24tag.getFirst(ID3v24FieldKey.ARTIST))));
                                        logger.info(file.getName() + " add in collection artist and album is not empty but genre is empty and coverPath != null ");
                                    }
                                } else {
                                    if (coverPath == null) {
                                        metadataList.add(new Metadata(new TrackEntity(v24tag.getFirst(ID3v24FieldKey.TITLE), filePath, v24tag.getFirst(ID3v24FieldKey.ARTIST), v24tag.getFirst(ID3v24FieldKey.GENRE)),
                                                new AlbumEntity( DEFAULT_COVER_PATH, v24tag.getFirst(ID3v24FieldKey.ALBUM)),
                                                new ArtistEntity(v24tag.getFirst(ID3v24FieldKey.ARTIST))));
                                        logger.info(file.getName() + " add in collection artist , album ,ganre is not empty  but coverPath == null ");
                                    } else {
                                        metadataList.add(new Metadata(new TrackEntity(v24tag.getFirst(ID3v24FieldKey.TITLE), filePath, v24tag.getFirst(ID3v24FieldKey.ARTIST), v24tag.getFirst(ID3v24FieldKey.GENRE)),
                                                new AlbumEntity(coverPath, v24tag.getFirst(ID3v24FieldKey.ALBUM)),
                                                new ArtistEntity(v24tag.getFirst(ID3v24FieldKey.ARTIST))));
                                        logger.info(file.getName() + " add in collection artist , album ,ganre is not empty  but coverPath != null ");
                                    }
                                }
                            }
                        }
                    }
                } catch (CannotReadException | IOException | ReadOnlyFileException | InvalidAudioFrameException | TagException e) {
                    logger.info(" get metadata exception ");
                }
            }
            logger.info( "add ne metadata : " + metadataList.size());
            Instance instance = Instance.getInstance();
            instance.insertMetadata(metadataList);

            logger.info("get metadata " + new Date());
        }
    }

    public String getImage(File mp3File)
    {
        logger.info("start get image from file " + mp3File.getName() + " " +new Date());
        Mp3File song = null;
        BufferedImage img = null;
        String path = null;
        try {
            song = new Mp3File(mp3File.getAbsolutePath());
            if (song.hasId3v2Tag()) {
                ID3v2 id3v2tag = song.getId3v2Tag();
                byte[] imageData = id3v2tag.getAlbumImage();
                if (imageData != null) {
                    img = ImageIO.read(new ByteArrayInputStream(imageData));
                    path  = mp3File.getParent() + "/cover.png";
                    File outputfile = new File(path);
                    ImageIO.write(img, "png", outputfile);
                }
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            path = null;
            logger.info("exception get image from file " + mp3File.getName() + " " +new Date());
        }

        return path;
    }
}
